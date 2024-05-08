package com.mightyworksinc.androidapp.mightyvolume;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.Constants;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import timber.log.Timber;

public class ManualFragment extends Fragment implements SeekBar.OnSeekBarChangeListener, View.OnClickListener {
    private int _AlarmMax;
    /* access modifiers changed from: private */
    public AudioManager _AudioManager;
    private int _BellMax;
    private int _CallMax;
    private int _MediaMax;
    private int _NotificationMax;
    /* access modifiers changed from: private */
    public int _SOUND = 2;
    private int _SystemMax;
    private String _UserAction = "User Action";
    /* access modifiers changed from: private */
    public ImageButton btn_Max_off_normal;
    /* access modifiers changed from: private */
    public ImageButton btn_Max_on_normal;
    /* access modifiers changed from: private */
    public ImageView img_changeRing;
    BroadcastReceiver mChangeRingerMode = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            Timber.d("mRingerModeBroadcastReceiver.getRingerMode() = " + ManualFragment.this._AudioManager.getRingerMode(), new Object[0]);
            if (ManualFragment.this._AudioManager.getRingerMode() == 0) {
                ManualFragment.this.getAudioManagerGain();
                ManualFragment.this.seekbar_Noti.setEnabled(false);
                ManualFragment.this.seekbar_System.setEnabled(false);
                ManualFragment.this.img_changeRing.setImageResource(R.drawable.icon_mute);
            } else if (ManualFragment.this._AudioManager.getRingerMode() == 1) {
                ManualFragment.this.getAudioManagerGain();
                ManualFragment.this.seekbar_Noti.setEnabled(false);
                ManualFragment.this.seekbar_System.setEnabled(false);
                ManualFragment.this.img_changeRing.setImageResource(R.drawable.icon_vibrate);
            } else if (ManualFragment.this._AudioManager.getRingerMode() == ManualFragment.this._SOUND) {
                ManualFragment.this.seekbar_Noti.setEnabled(true);
                ManualFragment.this.seekbar_System.setEnabled(true);
                ManualFragment.this.getAudioManagerGain();
                ManualFragment.this.img_changeRing.setImageResource(R.drawable.icon_ring);
            }
        }
    };
    BroadcastReceiver mChangeVolumeGain = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", 0) != intent.getIntExtra("android.media.EXTRA_PREV_VOLUME_STREAM_VALUE", 0)) {
                ManualFragment.this.getAudioManagerGain();
            }
        }
    };
    Tracker mTracker;
    private SeekBar seekbar_Alarm;
    private SeekBar seekbar_Bell;
    private SeekBar seekbar_Call;
    private SeekBar seekbar_Media;
    /* access modifiers changed from: private */
    public SeekBar seekbar_Noti;
    /* access modifiers changed from: private */
    public SeekBar seekbar_System;
    private TextView text_Alarm;
    private TextView text_Bell;
    private TextView text_Call;
    private TextView text_Media;
    private TextView text_Noti;
    private TextView text_System;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.volume, (ViewGroup) null);
        this.mTracker = ((MightyworksAnalyticsApplication) getActivity().getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        getActivity().registerReceiver(this.mChangeRingerMode, new IntentFilter("android.media.RINGER_MODE_CHANGED"));
        IntentFilter filterVolume = new IntentFilter();
        filterVolume.addAction(Constants.ACTION.VOLUME_ACTION);
        getActivity().registerReceiver(this.mChangeVolumeGain, filterVolume);
        this._AudioManager = (AudioManager) getActivity().getSystemService("audio");
        getAudioManagerMaxGain();
        this.seekbar_Media = (SeekBar) view.findViewById(R.id.seekbar_media);
        this.seekbar_Media.setMax(this._MediaMax);
        this.seekbar_Media.setOnSeekBarChangeListener(this);
        this.seekbar_Bell = (SeekBar) view.findViewById(R.id.seekbar_bell);
        this.seekbar_Bell.setMax(this._BellMax);
        this.seekbar_Bell.setOnSeekBarChangeListener(this);
        this.seekbar_Noti = (SeekBar) view.findViewById(R.id.seekbar_noti);
        this.seekbar_Noti.setMax(this._NotificationMax);
        this.seekbar_Noti.setOnSeekBarChangeListener(this);
        this.seekbar_System = (SeekBar) view.findViewById(R.id.seekbar_system);
        this.seekbar_System.setMax(this._SystemMax);
        this.seekbar_System.setOnSeekBarChangeListener(this);
        this.seekbar_Call = (SeekBar) view.findViewById(R.id.seekbar_call);
        this.seekbar_Call.setMax(this._CallMax);
        this.seekbar_Call.setOnSeekBarChangeListener(this);
        this.seekbar_Alarm = (SeekBar) view.findViewById(R.id.seekbar_alarm);
        this.seekbar_Alarm.setMax(this._AlarmMax);
        this.seekbar_Alarm.setOnSeekBarChangeListener(this);
        this.img_changeRing = (ImageView) view.findViewById(R.id.image_bell);
        this.text_Media = (TextView) view.findViewById(R.id.text_media);
        this.text_Bell = (TextView) view.findViewById(R.id.text_bell);
        this.text_Noti = (TextView) view.findViewById(R.id.text_noti);
        this.text_Call = (TextView) view.findViewById(R.id.text_call);
        this.text_Alarm = (TextView) view.findViewById(R.id.text_alarm);
        this.text_System = (TextView) view.findViewById(R.id.text_system);
        this.btn_Max_on_normal = (ImageButton) view.findViewById(R.id.btn_max_on_normal);
        this.btn_Max_off_normal = (ImageButton) view.findViewById(R.id.btn_max_off_normal);
        this.btn_Max_off_normal.setOnClickListener(this);
        this.btn_Max_off_normal.setSelected(true);
        getAudioManagerGain();
        return view;
    }

    public SeekBar getSeekBarMedia() {
        return this.seekbar_Media;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void onResume() {
        super.onResume();
        Timber.d("onResume()", new Object[0]);
        getAudioManagerGain();
    }

    public void getAudioManagerMaxGain() {
        this._MediaMax = this._AudioManager.getStreamMaxVolume(3);
        this._BellMax = this._AudioManager.getStreamMaxVolume(2);
        this._NotificationMax = this._AudioManager.getStreamMaxVolume(5);
        this._SystemMax = this._AudioManager.getStreamMaxVolume(1);
        this._CallMax = this._AudioManager.getStreamMaxVolume(0);
        this._AlarmMax = this._AudioManager.getStreamMaxVolume(1);
    }

    public void getAudioManagerGain() {
        this.seekbar_Bell.setProgress(this._AudioManager.getStreamVolume(2));
        this.seekbar_Noti.setProgress(this._AudioManager.getStreamVolume(5));
        this.seekbar_System.setProgress(this._AudioManager.getStreamVolume(1));
        this.seekbar_Call.setProgress(this._AudioManager.getStreamVolume(0));
        this.seekbar_Alarm.setProgress(this._AudioManager.getStreamVolume(4));
    }

    public void maxControl() {
        this.seekbar_Media.setProgress(this._MediaMax);
        this.seekbar_Bell.setProgress(this._BellMax);
        this.seekbar_Noti.setProgress(this._NotificationMax);
        this.seekbar_System.setProgress(this._SystemMax);
        this.seekbar_Call.setProgress(this._CallMax);
        this.seekbar_Alarm.setProgress(this._AlarmMax);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mChangeRingerMode != null) {
            getActivity().unregisterReceiver(this.mChangeRingerMode);
        }
        if (this.mChangeVolumeGain != null) {
            getActivity().unregisterReceiver(this.mChangeVolumeGain);
        }
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar == this.seekbar_Media) {
            if (MightyService.isAVCRunning()) {
                AutoVolumeControl.setMinMediaVolumeLevel(progress);
            } else {
                this._AudioManager.setStreamVolume(3, progress, 0);
            }
            this.text_Media.setText(Integer.toString(progress) + "/" + Integer.toString(this._MediaMax));
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("Media Volume").setValue(1).build());
        } else if (seekBar == this.seekbar_Bell) {
            this._AudioManager.setStreamVolume(2, progress, 0);
            int _Notification_Volume = this._AudioManager.getStreamVolume(5);
            int _SystemVolume = this._AudioManager.getStreamVolume(1);
            this.text_Bell.setText(Integer.toString(progress) + "/" + Integer.toString(this._BellMax));
            if (progress <= 1) {
                this.seekbar_Noti.setProgress(_Notification_Volume);
                this.seekbar_System.setProgress(_SystemVolume);
            }
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("Ring Volume").setValue(1).build());
        } else if (seekBar == this.seekbar_Noti) {
            if (this._AudioManager.getRingerMode() == this._SOUND) {
                this._AudioManager.setStreamVolume(5, progress, 0);
                this.text_Noti.setText(Integer.toString(progress) + "/" + Integer.toString(this._NotificationMax));
            }
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("Notification Volume").setValue(1).build());
        } else if (seekBar == this.seekbar_System) {
            if (this._AudioManager.getRingerMode() == this._SOUND) {
                this._AudioManager.setStreamVolume(1, progress, 0);
                this.text_System.setText(Integer.toString(progress) + "/" + Integer.toString(this._SystemMax));
            }
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("System Volume").setValue(1).build());
        } else if (seekBar == this.seekbar_Call) {
            if (1 <= progress) {
                this._AudioManager.setStreamVolume(0, progress, 0);
                this.text_Call.setText(Integer.toString(progress) + "/" + Integer.toString(this._CallMax));
            } else {
                this.seekbar_Call.setProgress(1);
            }
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("Voice_Call Volume").setValue(1).build());
        } else if (seekBar == this.seekbar_Alarm) {
            this._AudioManager.setStreamVolume(4, progress, 0);
            this.text_Alarm.setText(Integer.toString(progress) + "/" + Integer.toString(this._AlarmMax));
            this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel("Alarm Volume").setValue(1).build());
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_max_off_normal /*2131624098*/:
                max_image();
                maxControl();
                getActivity().sendBroadcast(new Intent(Constants.ACTION.IntentMaxAction));
                this.mTracker.send(new HitBuilders.EventBuilder().setCategory(this._UserAction).setAction("Manual").setLabel(Constants.ACTION.IntentMaxAction).setValue(1).build());
                RingtoneManager.getRingtone(getActivity(), RingtoneManager.getDefaultUri(2)).play();
                return;
            default:
                return;
        }
    }

    public void max_image() {
        this.btn_Max_off_normal.setVisibility(4);
        this.btn_Max_on_normal.setVisibility(0);
        max_off_image();
    }

    public void max_off_image() {
        new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ManualFragment.this.btn_Max_on_normal.setVisibility(4);
                ManualFragment.this.btn_Max_off_normal.setVisibility(0);
            }
        }.sendEmptyMessageDelayed(0, 1500);
    }
}
