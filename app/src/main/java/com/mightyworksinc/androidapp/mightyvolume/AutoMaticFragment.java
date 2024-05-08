package com.mightyworksinc.androidapp.mightyvolume;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.AutoVolumeControl;
import com.mightyworksinc.androidapp.mightyvolume.Constants;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import timber.log.Timber;

public class AutoMaticFragment extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {
    private int _AppAvcStartCount = 0;
    SharedPreferences.Editor _AppAvcStartCountEditor;
    SharedPreferences _AppAvcStartCountValue;
    private int _AppAvcStopCount = 0;
    AudioManager _AudioManager;
    /* access modifiers changed from: private */
    public int _AvcMediaGain;
    /* access modifiers changed from: private */
    public int _AvcNoiseGain;
    private int _BadValue = 999;
    /* access modifiers changed from: private */
    public int _MediaGain;
    /* access modifiers changed from: private */
    public int _MediaMax;
    private int _getPreferencesMediaValue = 0;
    private String _getPreferencesValue = "normal";
    /* access modifiers changed from: private */
    public int _oldMediaGain = 0;
    /* access modifiers changed from: private */
    public int _oldNoiseGain = 0;
    private ImageButton btn_AVC_off_normal;
    private ImageButton btn_AVC_on_normal;
    private ImageView img_NoiseBar0;
    private ImageView img_NoiseBar1;
    private ImageView img_NoiseBar10;
    private ImageView img_NoiseBar11;
    private ImageView img_NoiseBar12;
    private ImageView img_NoiseBar13;
    private ImageView img_NoiseBar14;
    private ImageView img_NoiseBar15;
    private ImageView img_NoiseBar16;
    private ImageView img_NoiseBar17;
    private ImageView img_NoiseBar18;
    private ImageView img_NoiseBar2;
    private ImageView img_NoiseBar3;
    private ImageView img_NoiseBar4;
    private ImageView img_NoiseBar5;
    private ImageView img_NoiseBar6;
    private ImageView img_NoiseBar7;
    private ImageView img_NoiseBar8;
    private ImageView img_NoiseBar9;
    private ImageView img_Senstivity;
    BroadcastReceiver mChangeReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (MightyService.isAVCRunning()) {
                AutoMaticFragment.this.avc_manual_MediaVolumeUpdate(AutoVolumeControl.getMinMediaVolumeLevel(), AutoMaticFragment.this._AudioManager.getStreamVolume(3));
            } else {
                int unused = AutoMaticFragment.this._MediaGain = AutoMaticFragment.this._AudioManager.getStreamVolume(3);
                AutoMaticFragment.this.avc_manual_MediaVolumeUpdate(AutoMaticFragment.this._MediaGain, AutoMaticFragment.this._MediaGain);
            }
            if (!intent.getAction().equals(Constants.ACTION.IntentNotificationUserAction)) {
                return;
            }
            if (AutoVolumeControl.isMicroPhoneUsed()) {
                AutoMaticFragment.this.avcAutoOFF();
            } else if (MightyService.getRunState()) {
                AutoMaticFragment.this.avc_On_UiUpdate();
                AutoMaticFragment.this.start_Service();
                AutoMaticFragment.this.mStatusChecker.run();
            } else {
                AutoMaticFragment.this.avcAutoOFF();
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler mHandler;
    BroadcastReceiver mMaxReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            AutoVolumeControl.setMinMediaVolumeLevel(AutoMaticFragment.this._MediaMax);
            AutoMaticFragment.this.avc_manual_MediaVolumeUpdate(AutoVolumeControl.getMinMediaVolumeLevel(), AutoMaticFragment.this._AudioManager.getStreamVolume(3));
        }
    };
    Runnable mStatusChecker = new Runnable() {
        public void run() {
            try {
                int unused = AutoMaticFragment.this._AvcNoiseGain = MightyService.getServiceNoiseGain();
                int unused2 = AutoMaticFragment.this._AvcMediaGain = MightyService.getServiceMediaGain();
                if (AutoMaticFragment.this._AvcNoiseGain > -1 && AutoMaticFragment.this._oldNoiseGain != AutoMaticFragment.this._AvcNoiseGain && AutoMaticFragment.this._AvcNoiseGain > 0) {
                    AutoMaticFragment.this.noiseUiUpdate();
                }
                if (AutoMaticFragment.this._MediaGain > -1 && AutoMaticFragment.this._oldMediaGain != AutoMaticFragment.this._MediaGain && AutoVolumeControl.getMinMediaVolumeLevel() > 0) {
                    AutoMaticFragment.this.mediaUiUpdate();
                }
                int unused3 = AutoMaticFragment.this._oldNoiseGain = AutoMaticFragment.this._AvcNoiseGain;
                int unused4 = AutoMaticFragment.this._oldMediaGain = AutoMaticFragment.this._AvcMediaGain;
            } catch (Exception e) {
            }
            if (MightyService._LongTimeAvcOff()) {
                MightyService.set_LongTimeAvcOff(false);
                AutoMaticFragment.this.avcAutoOFF();
            }
            AutoMaticFragment.this.mHandler.postDelayed(AutoMaticFragment.this.mStatusChecker, (long) 500);
        }
    };
    Tracker mTracker;
    SharedPreferences.Editor minMediaGain;
    SharedPreferences minMediaValue;
    SharedPreferences.Editor minSenstivityValue;
    private SeekBar seek_Auto;
    SharedPreferences senstivityValue;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.automatic, (ViewGroup) null);
        this.mTracker = ((MightyworksAnalyticsApplication) getActivity().getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        this._AudioManager = (AudioManager) getActivity().getSystemService("audio");
        this._MediaMax = this._AudioManager.getStreamMaxVolume(3);
        this._MediaGain = this._AudioManager.getStreamVolume(3);
        IntentFilter filterVolume = new IntentFilter();
        filterVolume.addAction(Constants.ACTION.VOLUME_ACTION);
        filterVolume.addAction(Constants.ACTION.IntentNotificationUserAction);
        getActivity().registerReceiver(this.mChangeReceiver, filterVolume);
        IntentFilter filterMax = new IntentFilter();
        filterMax.addAction(Constants.ACTION.IntentMaxAction);
        getActivity().registerReceiver(this.mMaxReceiver, filterMax);
        this.senstivityValue = getActivity().getSharedPreferences("senstivity", 0);
        this.minSenstivityValue = this.senstivityValue.edit();
        this._getPreferencesValue = this.senstivityValue.getString("min", this._getPreferencesValue);
        Timber.d("Value: " + this._getPreferencesValue, new Object[0]);
        this.minMediaValue = getActivity().getSharedPreferences("minmediagain", 0);
        this.minMediaGain = this.minMediaValue.edit();
        this._getPreferencesMediaValue = this.minMediaValue.getInt("mingain", this._getPreferencesMediaValue);
        Timber.d("MediaValue: " + this._getPreferencesMediaValue, new Object[0]);
        this._AppAvcStartCountValue = getActivity().getSharedPreferences("count", 0);
        this._AppAvcStartCountEditor = this._AppAvcStartCountValue.edit();
        start_Service();
        boolean _isRec = MightyService.isAVCRunning();
        Timber.i("onCreateView:: isAVCRunning is " + _isRec, new Object[0]);
        this.mHandler = new Handler();
        ((Button) view.findViewById(R.id.btn__sensitivity1)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btn__sensitivity2)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btn__sensitivity3)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btn__sensitivity4)).setOnClickListener(this);
        ((Button) view.findViewById(R.id.btn__sensitivity5)).setOnClickListener(this);
        this.img_Senstivity = (ImageView) view.findViewById(R.id.img__sensitivity);
        this.btn_AVC_on_normal = (ImageButton) view.findViewById(R.id.btn_avc_on_normal);
        this.btn_AVC_on_normal.setOnClickListener(this);
        this.btn_AVC_off_normal = (ImageButton) view.findViewById(R.id.btn_avc_off_normal);
        this.btn_AVC_off_normal.setOnClickListener(this);
        getPreferences();
        this.img_NoiseBar0 = (ImageView) view.findViewById(R.id.img_noise_bar0);
        this.img_NoiseBar1 = (ImageView) view.findViewById(R.id.img_noise_bar1);
        this.img_NoiseBar2 = (ImageView) view.findViewById(R.id.img_noise_bar2);
        this.img_NoiseBar3 = (ImageView) view.findViewById(R.id.img_noise_bar3);
        this.img_NoiseBar4 = (ImageView) view.findViewById(R.id.img_noise_bar4);
        this.img_NoiseBar5 = (ImageView) view.findViewById(R.id.img_noise_bar5);
        this.img_NoiseBar6 = (ImageView) view.findViewById(R.id.img_noise_bar6);
        this.img_NoiseBar7 = (ImageView) view.findViewById(R.id.img_noise_bar7);
        this.img_NoiseBar8 = (ImageView) view.findViewById(R.id.img_noise_bar8);
        this.img_NoiseBar9 = (ImageView) view.findViewById(R.id.img_noise_bar9);
        this.img_NoiseBar10 = (ImageView) view.findViewById(R.id.img_noise_bar10);
        this.img_NoiseBar11 = (ImageView) view.findViewById(R.id.img_noise_bar11);
        this.img_NoiseBar12 = (ImageView) view.findViewById(R.id.img_noise_bar12);
        this.img_NoiseBar13 = (ImageView) view.findViewById(R.id.img_noise_bar13);
        this.img_NoiseBar14 = (ImageView) view.findViewById(R.id.img_noise_bar14);
        this.img_NoiseBar15 = (ImageView) view.findViewById(R.id.img_noise_bar15);
        this.img_NoiseBar16 = (ImageView) view.findViewById(R.id.img_noise_bar16);
        this.img_NoiseBar17 = (ImageView) view.findViewById(R.id.img_noise_bar17);
        this.img_NoiseBar18 = (ImageView) view.findViewById(R.id.img_noise_bar18);
        this.seek_Auto = (SeekBar) view.findViewById(R.id.auto_seekbar);
        this.seek_Auto.setMax(this._MediaMax);
        this.seek_Auto.setOnSeekBarChangeListener(this);
        this.btn_AVC_off_normal.setSelected(true);
        this.btn_AVC_on_normal.setSelected(true);
        if (_isRec) {
            avc_manual_MediaVolumeUpdate(this._getPreferencesMediaValue, this._MediaGain);
            avc_On_UiUpdate();
            this.mStatusChecker.run();
        } else {
            avc_manual_MediaVolumeUpdate(this._getPreferencesMediaValue, this._MediaGain);
            avc_Off_UiUpdate();
        }
        return view;
    }

    private SeekBar getManualMediaSeekbar() {
        return ((ManualFragment) ((TabMainActivity) getActivity()).getSectionPageAdapter().getItem(1)).getSeekBarMedia();
    }

    public void avcAutoOFF() {
        avc_Off_UiUpdate();
        this.minMediaGain.putInt("mingain", AutoVolumeControl.getMinMediaVolumeLevel());
        this.minMediaGain.commit();
        this._AudioManager.setStreamVolume(3, AutoVolumeControl.getMinMediaVolumeLevel(), 0);
        avc_manual_MediaVolumeUpdate(this._BadValue, this.seek_Auto.getProgress());
        this.mHandler.removeCallbacks(this.mStatusChecker);
        noisebarStop();
    }

    public void getPreferences() {
        AutoVolumeControl.SensitivityLevel level;
        Timber.d("AutoVolumeControl.SensitivityLevel.Low = " + AutoVolumeControl.SensitivityLevel.Low, new Object[0]);
        Timber.d("_getPreferencesValue = " + this._getPreferencesValue, new Object[0]);
        if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Very_Low.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Very_Low;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level1);
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Low.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Low;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level2);
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Middle.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Middle;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level3);
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.High.toString())) {
            level = AutoVolumeControl.SensitivityLevel.High;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level4);
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Very_High.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Very_High;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level5);
        } else {
            level = AutoVolumeControl.SensitivityLevel.Middle;
            this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level3);
            Timber.d("getPreferencesbtn__sensitivity else = " + this._getPreferencesValue, new Object[0]);
        }
        AutoVolumeControl.setSensitivityLevel(level);
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mChangeReceiver != null) {
            getActivity().unregisterReceiver(this.mChangeReceiver);
        }
        if (this.mMaxReceiver != null) {
            getActivity().unregisterReceiver(this.mMaxReceiver);
        }
        this.mHandler.removeCallbacks(this.mStatusChecker);
    }

    public void onResume() {
        super.onResume();
        this.minMediaGain.putInt("mingain", AutoVolumeControl.getMinMediaVolumeLevel());
        this.minMediaGain.commit();
    }

    public void start_Service() {
        Intent startIntent = new Intent(getActivity(), MightyService.class);
        startIntent.setAction(Constants.ACTION.STARTFOREGROUND_ACTION);
        getActivity().startService(startIntent);
    }

    public void onClick(View v) {
        AutoVolumeControl.SensitivityLevel level = AutoVolumeControl.SensitivityLevel.Middle;
        switch (v.getId()) {
            case R.id.btn_avc_off_normal /*2131624016*/:
                MightyService.avcStart();
                MightyService.runState(true);
                MightyService.autoButtonClick(true);
                avc_On_UiUpdate();
                this._AppAvcStartCount = this._AppAvcStartCountValue.getInt("avcstart", this._AppAvcStartCount);
                this._AppAvcStartCount++;
                this._AppAvcStartCountEditor.putInt("avcstart", this._AppAvcStartCount);
                this._AppAvcStartCountEditor.commit();
                this.mTracker.send(new HitBuilders.EventBuilder().setCategory("User Action").setAction("AVC Start").setLabel("In App").setValue(1).build());
                noiseUiUpdate();
                this.mStatusChecker.run();
                break;
            case R.id.btn_avc_on_normal /*2131624017*/:
                avc_Off_UiUpdate();
                this._AppAvcStopCount = this._AppAvcStartCountValue.getInt("avcstop", this._AppAvcStopCount);
                this._AppAvcStopCount++;
                this._AppAvcStartCountEditor.putInt("avcstop", this._AppAvcStopCount);
                this._AppAvcStartCountEditor.commit();
                this.mTracker.send(new HitBuilders.EventBuilder().setCategory("User Action").setAction("AVC Stop").setLabel("In App").setValue(1).build());
                MightyService.runState(false);
                MightyService.autoButtonClick(true);
                MightyService.avcStop();
                this.minMediaGain.putInt("mingain", AutoVolumeControl.getMinMediaVolumeLevel());
                this.minMediaGain.commit();
                this._AudioManager.setStreamVolume(3, AutoVolumeControl.getMinMediaVolumeLevel(), 0);
                avc_manual_MediaVolumeUpdate(this._BadValue, this.seek_Auto.getProgress());
                this.mHandler.removeCallbacks(this.mStatusChecker);
                noisebarStop();
                break;
            case R.id.btn__sensitivity1 /*2131624062*/:
                this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level1);
                level = AutoVolumeControl.SensitivityLevel.Very_Low;
                break;
            case R.id.btn__sensitivity2 /*2131624063*/:
                this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level2);
                level = AutoVolumeControl.SensitivityLevel.Low;
                break;
            case R.id.btn__sensitivity3 /*2131624064*/:
                this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level3);
                level = AutoVolumeControl.SensitivityLevel.Middle;
                break;
            case R.id.btn__sensitivity4 /*2131624065*/:
                this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level4);
                level = AutoVolumeControl.SensitivityLevel.High;
                break;
            case R.id.btn__sensitivity5 /*2131624066*/:
                this.img_Senstivity.setImageResource(R.drawable.img_sensitivity_level5);
                level = AutoVolumeControl.SensitivityLevel.Very_High;
                break;
        }
        AutoVolumeControl.setSensitivityLevel(level);
        this.minSenstivityValue.putString("min", level.toString());
        this.minSenstivityValue.commit();
    }

    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        avc_manual_MediaVolumeUpdate(progress, this._BadValue);
        AutoVolumeControl.setMinMediaVolumeLevel(progress);
        this.minMediaGain.putInt("mingain", progress);
        this.minMediaGain.commit();
        if (!MightyService.isAVCRunning()) {
            this._AudioManager.setStreamVolume(3, progress, 0);
        }
    }

    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    /* access modifiers changed from: private */
    public void avc_manual_MediaVolumeUpdate(int progress, int secondaryProgress) {
        if (this._BadValue != progress) {
            this.seek_Auto.setProgress(progress);
            if (getManualMediaSeekbar() != null) {
                getManualMediaSeekbar().setProgress(progress);
            }
        }
        if (this._BadValue != secondaryProgress) {
            this.seek_Auto.setSecondaryProgress(secondaryProgress);
            if (getManualMediaSeekbar() != null) {
                getManualMediaSeekbar().setSecondaryProgress(secondaryProgress);
            }
        }
    }

    public void avc_On_UiUpdate() {
        this.btn_AVC_on_normal.setVisibility(0);
        this.btn_AVC_off_normal.setVisibility(4);
    }

    public void avc_Off_UiUpdate() {
        this.btn_AVC_on_normal.setVisibility(4);
        this.btn_AVC_off_normal.setVisibility(0);
    }

    public void mediaUiUpdate() {
        Timber.d("mediaUiUpdate()", new Object[0]);
        avc_manual_MediaVolumeUpdate(this._BadValue, this._AvcMediaGain);
    }

    public void noiseUiUpdate() {
        if (MightyService.getServiceNoiseGain() == -1) {
            this._AvcNoiseGain = 40;
        }
        if (this._AvcNoiseGain > 0 && this._AvcNoiseGain < 50) {
            noisebar0();
        } else if (51 <= this._AvcNoiseGain && this._AvcNoiseGain <= 54) {
            noisebar1();
        } else if (55 <= this._AvcNoiseGain && this._AvcNoiseGain <= 58) {
            noisebar2();
        } else if (59 <= this._AvcNoiseGain && this._AvcNoiseGain <= 62) {
            noisebar3();
        } else if (63 <= this._AvcNoiseGain && this._AvcNoiseGain <= 66) {
            noisebar4();
        } else if (67 <= this._AvcNoiseGain && this._AvcNoiseGain <= 70) {
            noisebar5();
        } else if (71 <= this._AvcNoiseGain && this._AvcNoiseGain <= 74) {
            noisebar6();
        } else if (75 <= this._AvcNoiseGain && this._AvcNoiseGain <= 78) {
            noisebar7();
        } else if (79 <= this._AvcNoiseGain && this._AvcNoiseGain <= 82) {
            noisebar8();
        } else if (83 <= this._AvcNoiseGain && this._AvcNoiseGain <= 86) {
            noisebar9();
        } else if (87 <= this._AvcNoiseGain && this._AvcNoiseGain <= 90) {
            noisebar10();
        } else if (91 <= this._AvcNoiseGain && this._AvcNoiseGain <= 94) {
            noisebar11();
        } else if (95 <= this._AvcNoiseGain && this._AvcNoiseGain <= 98) {
            noisebar12();
        } else if (99 <= this._AvcNoiseGain && this._AvcNoiseGain <= 102) {
            noisebar13();
        } else if (103 <= this._AvcNoiseGain && this._AvcNoiseGain <= 106) {
            noisebar14();
        } else if (107 <= this._AvcNoiseGain && this._AvcNoiseGain <= 110) {
            noisebar15();
        } else if (111 <= this._AvcNoiseGain && this._AvcNoiseGain <= 114) {
            noisebar16();
        } else if (115 <= this._AvcNoiseGain && this._AvcNoiseGain <= 118) {
            noisebar17();
        } else if (119 > this._AvcNoiseGain || this._AvcNoiseGain > 130) {
            noisebarStop();
        } else {
            noisebar18();
        }
    }

    public void noisebarStop() {
        this.img_NoiseBar0.setVisibility(4);
        this.img_NoiseBar1.setVisibility(4);
        this.img_NoiseBar2.setVisibility(4);
        this.img_NoiseBar3.setVisibility(4);
        this.img_NoiseBar4.setVisibility(4);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar0() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(4);
        this.img_NoiseBar2.setVisibility(4);
        this.img_NoiseBar3.setVisibility(4);
        this.img_NoiseBar4.setVisibility(4);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar1() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(4);
        this.img_NoiseBar3.setVisibility(4);
        this.img_NoiseBar4.setVisibility(4);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar2() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(4);
        this.img_NoiseBar4.setVisibility(4);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar3() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(4);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar4() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(4);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar5() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(4);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar6() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(4);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar7() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(4);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar8() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(4);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar9() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(4);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar10() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(4);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar11() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(4);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar12() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(4);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar13() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(4);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar14() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(0);
        this.img_NoiseBar15.setVisibility(4);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar15() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(0);
        this.img_NoiseBar15.setVisibility(0);
        this.img_NoiseBar16.setVisibility(4);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar16() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(0);
        this.img_NoiseBar15.setVisibility(0);
        this.img_NoiseBar16.setVisibility(0);
        this.img_NoiseBar17.setVisibility(4);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar17() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(0);
        this.img_NoiseBar15.setVisibility(0);
        this.img_NoiseBar16.setVisibility(0);
        this.img_NoiseBar17.setVisibility(0);
        this.img_NoiseBar18.setVisibility(4);
    }

    public void noisebar18() {
        this.img_NoiseBar0.setVisibility(0);
        this.img_NoiseBar1.setVisibility(0);
        this.img_NoiseBar2.setVisibility(0);
        this.img_NoiseBar3.setVisibility(0);
        this.img_NoiseBar4.setVisibility(0);
        this.img_NoiseBar5.setVisibility(0);
        this.img_NoiseBar6.setVisibility(0);
        this.img_NoiseBar7.setVisibility(0);
        this.img_NoiseBar8.setVisibility(0);
        this.img_NoiseBar9.setVisibility(0);
        this.img_NoiseBar10.setVisibility(0);
        this.img_NoiseBar11.setVisibility(0);
        this.img_NoiseBar12.setVisibility(0);
        this.img_NoiseBar13.setVisibility(0);
        this.img_NoiseBar14.setVisibility(0);
        this.img_NoiseBar15.setVisibility(0);
        this.img_NoiseBar16.setVisibility(0);
        this.img_NoiseBar17.setVisibility(0);
        this.img_NoiseBar18.setVisibility(0);
    }
}
