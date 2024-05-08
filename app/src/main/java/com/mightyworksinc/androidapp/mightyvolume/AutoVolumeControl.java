package com.mightyworksinc.androidapp.mightyvolume;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;
import java.util.Calendar;
import java.util.HashMap;
import timber.log.Timber;

public class AutoVolumeControl {
    private static boolean _AVCLongState = false;
    /* access modifiers changed from: private */
    public static boolean _BlueToothHeadSetCheck = false;
    public static final String _BtHeadSet = "headset_bt";
    public static final String _HeadSet = "headset";
    public static final String _HeadSetNoMic = "headset_nomic";
    static boolean _IsMicroPhoneUsed = false;
    public static final String _Phone = "phone";
    static int _minMediaVolumeLevel = 0;
    private static SensitivityLevel _sensitivityLevel;
    private static int maxMusicVolumeIndex;
    private static String sCurrentMicConfiguration = _Phone;
    /* access modifiers changed from: private */
    public int _AppAvcStartCount = 0;
    /* access modifiers changed from: private */
    public int _NotiAvcStartCount = 0;
    /* access modifiers changed from: private */
    public Context _ServiceContext = null;
    SharedPreferences _StartCountValue;
    /* access modifiers changed from: private */
    public volatile boolean _isRunning = false;
    /* access modifiers changed from: private */
    public volatile boolean _isThreadExit = false;
    /* access modifiers changed from: private */
    public AudioManager _manager = null;
    /* access modifiers changed from: private */
    public int _noiseGain = -1;
    /* access modifiers changed from: private */
    public AudioThreadRecord _recorder = null;
    /* access modifiers changed from: private */
    public int _volumeidx_media = -1;
    private float averageVol = -1.0f;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            Timber.d("count = " + (AutoVolumeControl.this._NotiAvcStartCount + AutoVolumeControl.this._AppAvcStartCount), new Object[0]);
            if (msg != null) {
                Toast toast = Toast.makeText(AutoVolumeControl.this._ServiceContext, (String) msg.obj, 1);
                toast.setGravity(17, 0, 0);
                toast.show();
                super.handleMessage(msg);
            }
        }
    };
    private AccelerateDecelerateInterpolator mAccelDecelInterpolator = new AccelerateDecelerateInterpolator();
    private HashMap<String, MicConfiguration> mConfigurationMap = new HashMap<>();
    private Thread mVolumeControl = null;
    /* access modifiers changed from: private */
    public boolean startedByNavigation = false;

    public enum SensitivityLevel {
        Very_Low,
        Low,
        Middle,
        High,
        Very_High
    }

    public static void setSensitivityLevel(SensitivityLevel sensitivityLevel) {
        _sensitivityLevel = sensitivityLevel;
    }

    public void setStartedByNavigation(boolean startedNavigation) {
        this.startedByNavigation = startedNavigation;
    }

    private class MicConfiguration {
        public int noiseBandMax;
        public int noiseBandMin;
        public int responseWindowSize;
        public int volumeRange;

        public MicConfiguration(int noiseMin, int noiseMax, int volRange, int resWinSize) {
            this.noiseBandMin = noiseMin;
            this.noiseBandMax = noiseMax;
            this.volumeRange = volRange;
            this.responseWindowSize = resWinSize;
        }
    }

    public AutoVolumeControl(Context parentContext) {
        if (parentContext == null) {
            throw new NullPointerException("parentContext is null");
        } else if (this._recorder != null) {
            throw new RuntimeException("AutoVolumeControl duplicates created");
        } else {
            this._recorder = new AudioThreadRecord();
            this._ServiceContext = parentContext;
            this._manager = (AudioManager) this._ServiceContext.getSystemService("audio");
            maxMusicVolumeIndex = this._manager.getStreamMaxVolume(3);
            _minMediaVolumeLevel = this._manager.getStreamVolume(3);
            this._StartCountValue = MightyService.mMightyService.getSharedPreferences("count", 0);
            this._NotiAvcStartCount = this._StartCountValue.getInt("noti_avcstart", this._NotiAvcStartCount);
            this._AppAvcStartCount = this._StartCountValue.getInt("avcstart", this._AppAvcStartCount);
            this.mConfigurationMap.put("phone." + SensitivityLevel.Very_Low, new MicConfiguration(50, 120, 20, 22));
            this.mConfigurationMap.put("phone." + SensitivityLevel.Low, new MicConfiguration(55, 115, 20, 18));
            this.mConfigurationMap.put("phone." + SensitivityLevel.Middle, new MicConfiguration(60, 110, 20, 14));
            this.mConfigurationMap.put("phone." + SensitivityLevel.High, new MicConfiguration(65, 105, 20, 10));
            this.mConfigurationMap.put("phone." + SensitivityLevel.Very_High, new MicConfiguration(70, 100, 20, 6));
            this.mConfigurationMap.put("headset." + SensitivityLevel.Very_Low, new MicConfiguration(50, 120, 4, 22));
            this.mConfigurationMap.put("headset." + SensitivityLevel.Low, new MicConfiguration(55, 115, 4, 18));
            this.mConfigurationMap.put("headset." + SensitivityLevel.Middle, new MicConfiguration(60, 110, 5, 14));
            this.mConfigurationMap.put("headset." + SensitivityLevel.High, new MicConfiguration(65, 105, 5, 10));
            this.mConfigurationMap.put("headset." + SensitivityLevel.Very_High, new MicConfiguration(70, 100, 6, 6));
            this.mConfigurationMap.put("headset_nomic." + SensitivityLevel.Very_Low, new MicConfiguration(50, 120, 4, 22));
            this.mConfigurationMap.put("headset_nomic." + SensitivityLevel.Low, new MicConfiguration(55, 115, 4, 18));
            this.mConfigurationMap.put("headset_nomic." + SensitivityLevel.Middle, new MicConfiguration(60, 110, 5, 14));
            this.mConfigurationMap.put("headset_nomic." + SensitivityLevel.High, new MicConfiguration(65, 105, 5, 10));
            this.mConfigurationMap.put("headset_nomic." + SensitivityLevel.Very_High, new MicConfiguration(70, 100, 6, 6));
            this.mConfigurationMap.put("headset_bt." + SensitivityLevel.Very_Low, new MicConfiguration(50, 120, 4, 22));
            this.mConfigurationMap.put("headset_bt." + SensitivityLevel.Low, new MicConfiguration(55, 115, 4, 18));
            this.mConfigurationMap.put("headset_bt." + SensitivityLevel.Middle, new MicConfiguration(60, 110, 5, 14));
            this.mConfigurationMap.put("headset_bt." + SensitivityLevel.High, new MicConfiguration(65, 105, 5, 10));
            this.mConfigurationMap.put("headset_bt." + SensitivityLevel.Very_High, new MicConfiguration(70, 100, 6, 6));
        }
    }

    public int start() {
        if (this._recorder == null) {
            return -1;
        }
        if (this._recorder.startRecord() <= 0) {
            return -2;
        }
        this._isThreadExit = false;
        this.mVolumeControl = new VolumeControlThread();
        this.mVolumeControl.start();
        return 1;
    }

    public boolean isRunning() {
        return this._isRunning && this._recorder.isRunning();
    }

    public void stop() {
        this._isThreadExit = true;
        if (this.mVolumeControl != null) {
            try {
                this.mVolumeControl.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.mVolumeControl = null;
        }
        this.averageVol = -1.0f;
        if (this._recorder != null && this._recorder.isRunning()) {
            this._recorder.stopRecord();
        }
        this._recorder = null;
        this.startedByNavigation = false;
    }

    public int getNoiseGain() {
        return this._noiseGain;
    }

    public int getMediaVolumeIndex() {
        return this._volumeidx_media;
    }

    private class VolumeControlThread extends Thread {
        private VolumeControlThread() {
        }

        public void run() {
            int THRESHOLD;
            boolean unused = AutoVolumeControl.this._isRunning = true;
            int _TimeCount = 0;
            int toastCount = 0;
            if (AutoVolumeControl.this.startedByNavigation) {
                THRESHOLD = 7200;
            } else {
                THRESHOLD = 600;
            }
            while (!AutoVolumeControl.this._isThreadExit) {
                long beforeTimeinMillis = Calendar.getInstance().getTimeInMillis();
                if (AutoVolumeControl.this._recorder.isRunning() && AutoVolumeControl.this._recorder.isRecordReadingStarted()) {
                    AutoVolumeControl.this._recorder.setIsWiredHeadsetOn(AutoVolumeControl.this._manager.isWiredHeadsetOn());
                    AutoVolumeControl.this._recorder.setIsMusicActive(AutoVolumeControl.this._manager.isMusicActive());
                    AutoVolumeControl.this._recorder.setBlueToothHeadSetConnected(AutoVolumeControl._BlueToothHeadSetCheck);
                    if (AutoVolumeControl._BlueToothHeadSetCheck || AutoVolumeControl.this._manager.isWiredHeadsetOn() || !AutoVolumeControl.this._manager.isMusicActive()) {
                        if (toastCount == 0) {
                            toastCount++;
                            AutoVolumeControl.this.toastPlayApp();
                        }
                        AutoVolumeControl.this.music_AVC();
                        if (AutoVolumeControl.this._manager.isMusicActive()) {
                            _TimeCount = 0;
                        } else {
                            _TimeCount++;
                        }
                        if (_TimeCount > THRESHOLD) {
                            AutoVolumeControl.setAvcLongtime(true);
                        }
                    } else if (toastCount == 0) {
                        toastCount++;
                        AutoVolumeControl.this.toastUseHeadset();
                    }
                }
                long timeToSleep = ((long) 500) - (Calendar.getInstance().getTimeInMillis() - beforeTimeinMillis);
                if (timeToSleep > 0) {
                    try {
                        sleep(timeToSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            int unused2 = AutoVolumeControl.this._volumeidx_media = -1;
            int unused3 = AutoVolumeControl.this._noiseGain = -1;
            boolean unused4 = AutoVolumeControl.this._isRunning = false;
        }
    }

    /* access modifiers changed from: private */
    public void toastUseHeadset() {
        if (!this.startedByNavigation && !_IsMicroPhoneUsed) {
            String message = MightyService.mMightyService.getString(R.string.avc_headset);
            Message msg = Message.obtain();
            msg.obj = message;
            msg.setTarget(this.handler);
            msg.sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    public void toastPlayApp() {
        if (this.startedByNavigation) {
            String message = MightyService.mMightyService.getString(R.string.autovolumeon);
            Message msg = Message.obtain();
            msg.obj = message;
            msg.setTarget(this.handler);
            msg.sendToTarget();
        } else if (!_IsMicroPhoneUsed) {
            boolean usingHeadset = _BlueToothHeadSetCheck || this._manager.isWiredHeadsetOn();
            String playapp_message = MightyService.mMightyService.getString(R.string.playapp);
            String playmusicapp_message = MightyService.mMightyService.getString(R.string.playmusicapp);
            if (!this._manager.isMusicActive()) {
                Message msg2 = Message.obtain();
                if (usingHeadset) {
                    msg2.obj = playapp_message;
                } else {
                    msg2.obj = playmusicapp_message;
                }
                msg2.setTarget(this.handler);
                msg2.sendToTarget();
            }
        }
    }

    private MicConfiguration getCurrentMicConfig() {
        MicConfiguration conf = this.mConfigurationMap.get(sCurrentMicConfiguration + "." + _sensitivityLevel);
        if (conf != null) {
            return conf;
        }
        if (this.mConfigurationMap.size() == 0) {
            throw new RuntimeException("Configuration map not initialized");
        }
        throw new RuntimeException("Wrong MicFrom key value : " + sCurrentMicConfiguration + "." + _sensitivityLevel);
    }

    private float getAdjustedVolumeGain(int noiseGain, int minVolume, int volumeRange) {
        MicConfiguration micConfig = getCurrentMicConfig();
        if (minVolume == 0) {
            return 0.0f;
        }
        if (minVolume + volumeRange > maxMusicVolumeIndex) {
            volumeRange = maxMusicVolumeIndex - minVolume;
        }
        if (noiseGain < micConfig.noiseBandMin) {
            return (float) minVolume;
        }
        if (noiseGain >= micConfig.noiseBandMax) {
            return (float) (minVolume + volumeRange);
        }
        return (((float) volumeRange) * 1.0f * this.mAccelDecelInterpolator.getInterpolation((((float) (noiseGain - micConfig.noiseBandMin)) * 1.0f) / ((float) (micConfig.noiseBandMax - micConfig.noiseBandMin)))) + ((float) minVolume);
    }

    private int getVolumeIndexToApply(float volumeInFloat) {
        if (this.averageVol < 0.0f) {
            this.averageVol = volumeInFloat;
        } else {
            MicConfiguration micConfig = getCurrentMicConfig();
            this.averageVol -= this.averageVol / ((float) micConfig.responseWindowSize);
            this.averageVol += volumeInFloat / ((float) micConfig.responseWindowSize);
        }
        return Math.round(this.averageVol);
    }

    /* access modifiers changed from: private */
    public void music_AVC() {
        int noiseGain = this._recorder.getNoiseGain();
        this._noiseGain = noiseGain;
        int minMediaVolumeLevel = getMinMediaVolumeLevel();
        int index = getVolumeIndexToApply(getAdjustedVolumeGain(noiseGain, minMediaVolumeLevel, getCurrentMicConfig().volumeRange));
        Timber.v("minMediaVolumeLevel = " + minMediaVolumeLevel, new Object[0]);
        if (this._manager.getStreamVolume(3) != index) {
            this._manager.setStreamVolume(3, index, 0);
            this._volumeidx_media = index;
        }
    }

    public static boolean isMicroPhoneUsed() {
        return _IsMicroPhoneUsed;
    }

    public static void setMicroPhoneUsed(boolean microphone) {
        _IsMicroPhoneUsed = microphone;
    }

    public static boolean getAVCLongtime() {
        return _AVCLongState;
    }

    public static void setAvcLongtime(boolean avcLongtime) {
        _AVCLongState = avcLongtime;
    }

    public static void setISBlueToothHeadSetCheck(boolean isBlueToothHeadSetCheck) {
        _BlueToothHeadSetCheck = isBlueToothHeadSetCheck;
        if (isBlueToothHeadSetCheck) {
            setCurrentMicConfiguration(_BtHeadSet);
        } else if (MightyService._ServiceAudioManager == null) {
        } else {
            if (MightyService._ServiceAudioManager.isWiredHeadsetOn()) {
                setCurrentMicConfiguration(_HeadSet);
            } else {
                setCurrentMicConfiguration(_Phone);
            }
        }
    }

    public static int getMinMediaVolumeLevel() {
        return _minMediaVolumeLevel;
    }

    public static void setMinMediaVolumeLevel(int getSeekBarLevel) {
        Timber.d("getSeekBarLevel1 " + getSeekBarLevel, new Object[0]);
        if (getSeekBarLevel < 0) {
            getSeekBarLevel = 0;
            Timber.d("getSeekBarLevel2 " + 0, new Object[0]);
        } else if (getSeekBarLevel > maxMusicVolumeIndex) {
            getSeekBarLevel = maxMusicVolumeIndex;
        }
        _minMediaVolumeLevel = getSeekBarLevel;
    }

    public static void setCurrentMicConfiguration(String newConfigurationName) {
        sCurrentMicConfiguration = newConfigurationName;
        Timber.d("set mic configuratino to " + newConfigurationName, new Object[0]);
    }
}
