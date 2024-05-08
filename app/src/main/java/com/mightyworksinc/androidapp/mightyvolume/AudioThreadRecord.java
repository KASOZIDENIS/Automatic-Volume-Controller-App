package com.mightyworksinc.androidapp.mightyvolume;

import android.media.AudioRecord;
import java.nio.ByteBuffer;

public class AudioThreadRecord {
    /* access modifiers changed from: private */
    public volatile boolean _isRunning = false;
    /* access modifiers changed from: private */
    public volatile boolean _isThreadExit = false;
    /* access modifiers changed from: private */
    public int _noiseGain = 0;
    /* access modifiers changed from: private */
    public boolean isBlueToothHeadSetConnected = false;
    /* access modifiers changed from: private */
    public boolean isMusicPlaying = false;
    /* access modifiers changed from: private */
    public boolean isRecordReadingStarted = false;
    /* access modifiers changed from: private */
    public boolean isWiredHeadsetConnected = false;
    private Thread mRecordingThread = null;

    public boolean isRunning() {
        return this._isRunning;
    }

    public int getNoiseGain() {
        return this._noiseGain;
    }

    public int startRecord() {
        if (this._isRunning) {
            return 0;
        }
        this._isThreadExit = false;
        this.mRecordingThread = new RecordAudioThread();
        this.mRecordingThread.start();
        return 1;
    }

    public void stopRecord() {
        this._isThreadExit = true;
        if (this.mRecordingThread != null) {
            try {
                this.mRecordingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mRecordingThread = null;
        }
        this._noiseGain = 0;
    }

    public void setIsWiredHeadsetOn(boolean state) {
        this.isWiredHeadsetConnected = state;
    }

    public void setIsMusicActive(boolean isPlaying) {
        this.isMusicPlaying = isPlaying;
    }

    public void setBlueToothHeadSetConnected(boolean Connected) {
        this.isBlueToothHeadSetConnected = Connected;
    }

    public boolean isRecordReadingStarted() {
        return this.isRecordReadingStarted;
    }

    private class RecordAudioThread extends Thread {
        private RecordAudioThread() {
        }

        public void run() {
            boolean unused = AudioThreadRecord.this._isRunning = true;
            int _frame_len = (int) (0.02d * ((double) 16000));
            int _bufferSize_in = AudioRecord.getMinBufferSize(16000, 16, 2);
            AudioRecord _audioRecord = new AudioRecord(6, 16000, 16, 2, _bufferSize_in);
            byte[] audioIn = new byte[_bufferSize_in];
            byte[] frame_data = new byte[_frame_len];
            ByteBuffer buffer = ByteBuffer.allocate(_bufferSize_in + _frame_len);
            buffer.clear();
            _audioRecord.startRecording();
            VMWProcess.NEInit(16000, _frame_len);
            while (true) {
                if (AudioThreadRecord.this._isThreadExit) {
                    break;
                }
                int numSamples = _audioRecord.read(audioIn, 0, _bufferSize_in);
                if (numSamples <= 0) {
                    boolean unused2 = AudioThreadRecord.this.isRecordReadingStarted = true;
                    AutoVolumeControl.setMicroPhoneUsed(true);
                    MightyService.autoButtonClick(true);
                    break;
                }
                boolean unused3 = AudioThreadRecord.this.isRecordReadingStarted = true;
                AutoVolumeControl.setMicroPhoneUsed(false);
                buffer.put(audioIn, 0, numSamples);
                buffer.flip();
                while (buffer.remaining() > _frame_len) {
                    buffer.get(frame_data);
                    if (AudioThreadRecord.this.isBlueToothHeadSetConnected || AudioThreadRecord.this.isWiredHeadsetConnected || !AudioThreadRecord.this.isMusicPlaying) {
                        int tmpNoise = VMWProcess.NEProcess(frame_data);
                        if (tmpNoise >= 0) {
                            int unused4 = AudioThreadRecord.this._noiseGain = tmpNoise;
                        }
                    } else {
                        int unused5 = AudioThreadRecord.this._noiseGain = -100;
                    }
                }
                if (AudioThreadRecord.this._noiseGain == 0) {
                    VMWProcess.Free();
                    VMWProcess.NEInit(16000, _frame_len);
                }
                buffer.compact();
            }
            VMWProcess.Free();
            if (_audioRecord != null) {
                _audioRecord.stop();
                _audioRecord.release();
            }
            boolean unused6 = AudioThreadRecord.this._isRunning = false;
            boolean unused7 = AudioThreadRecord.this.isRecordReadingStarted = false;
        }
    }
}
