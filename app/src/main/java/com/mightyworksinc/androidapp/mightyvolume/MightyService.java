package com.mightyworksinc.androidapp.mightyvolume;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.mightyworksinc.androidapp.mightyvolume.AutoVolumeControl;
import com.mightyworksinc.androidapp.mightyvolume.Constants;
import com.mightyworksinc.androidapp.mightyvolume.MightyworksAnalyticsApplication;
import timber.log.Timber;

public class MightyService extends Service {
    static boolean _AVCOFF = false;
    static boolean _AutoButtonState = false;
    static boolean _RunState = false;
    static AudioManager _ServiceAudioManager;
    static long _StartTime = 0;
    static long _StopTime = 0;
    static long _TimeValue = 0;
    private static AutoVolumeControl _avc = null;
    static MightyService mMightyService;
    static Tracker mTracker;
    static SharedPreferences.Editor minMediaGain;
    static SharedPreferences minMediaValue;
    static RemoteViews remote;
    /* access modifiers changed from: private */
    public boolean _AlarmOff = false;
    private String _getPreferencesValue = "normal";
    /* access modifiers changed from: private */
    public Handler mHandler;
    BroadcastReceiver mHeadSetCheckReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.HEADSET_PLUG".equals(intent.getAction())) {
                int flugState = intent.getIntExtra("state", -1);
                int microphoneValue = intent.getIntExtra("microphone", -1);
                if (flugState != 1) {
                    Timber.d("HeadSetPlugInTest", "PHONEMICRO");
                    AutoVolumeControl.setCurrentMicConfiguration(AutoVolumeControl._Phone);
                } else if (microphoneValue == 1) {
                    Timber.d("HeadSetPlugInTest", "HEADSETMICRO");
                    AutoVolumeControl.setCurrentMicConfiguration(AutoVolumeControl._HeadSet);
                } else if (microphoneValue == 0) {
                    Timber.d("HeadSetPlugInTest", "PHONEMICRO");
                    AutoVolumeControl.setCurrentMicConfiguration(AutoVolumeControl._HeadSetNoMic);
                }
            }
        }
    };
    private int mNavigationAppPID = 0;
    PhonecallReceiver mPhonecallReceiver;
    Runnable mStatusAlram = new Runnable() {
        public void run() {
            if (Build.VERSION.SDK.equals(Integer.toString(21))) {
                MightyService.this.AlarmCheckDate();
                if (MightyService.this._AlarmOff) {
                    boolean unused = MightyService.this._AlarmOff = false;
                    if (MightyService.isAVCRunning()) {
                        MightyService.autoRunStop();
                    }
                }
            }
            MightyService.this.mHandler.postDelayed(MightyService.this.mStatusAlram, 60000);
        }
    };
    Runnable mStatusChecker = new Runnable() {
        String message;

        public void run() {
            MightyService.this.getRunningProcessList();
            try {
                if (MightyService._AutoButtonState) {
                    MightyService._AutoButtonState = false;
                    if (MightyService.isAVCRunning()) {
                        this.message = MightyService.this.getString(R.string.autovolumeon);
                    } else {
                        this.message = MightyService.this.getString(R.string.autovolumeoff);
                    }
                    MightyService.this.showNotify(this.message);
                }
                if (AutoVolumeControl.getAVCLongtime()) {
                    MightyService.runState(false);
                    MightyService.avcStop();
                    AutoVolumeControl.setAvcLongtime(false);
                    MightyService._AVCOFF = true;
                    MightyService.this.showNotify(MightyService.this.getString(R.string.noti_avc_long_time));
                    MightyService.mMightyService.sendBroadcast(new Intent(Constants.ACTION.IntentNotificationUserAction));
                }
                if (AutoVolumeControl.isMicroPhoneUsed()) {
                    MightyService.runState(false);
                    MightyService.avcStop();
                    AutoVolumeControl.setMicroPhoneUsed(false);
                    MightyService.this.showNotify(MightyService.this.getString(R.string.noti_avc_another_app));
                    MightyService.mMightyService.sendBroadcast(new Intent(Constants.ACTION.IntentNotificationUserAction));
                }
            } catch (Exception e) {
            }
            MightyService.this.mHandler.postDelayed(MightyService.this.mStatusChecker, 300);
        }
    };
    VolumeChangeReceiver mVolumeChangeReceiver;

    @SuppressLint({"NewApi"})
    public void AlarmCheckDate() {
        long now = System.currentTimeMillis();
        AlarmManager alarmManager = (AlarmManager) getSystemService("alarm");
        if (alarmManager.getNextAlarmClock() != null && now >= alarmManager.getNextAlarmClock().getTriggerTime() - 300000) {
            this._AlarmOff = true;
        }
    }

    public void onCreate() {
        super.onCreate();
        getApplicationContext().registerReceiver(this.mHeadSetCheckReceiver, new IntentFilter("android.intent.action.HEADSET_PLUG"));
        Timber.i("In onCreate", new Object[0]);
        this.mPhonecallReceiver = new PhonecallReceiver();
        registerReceiver(this.mPhonecallReceiver, new IntentFilter(Constants.ACTION.Phone_STATE));
        _ServiceAudioManager = (AudioManager) getSystemService("audio");
        this.mVolumeChangeReceiver = new VolumeChangeReceiver();
        registerReceiver(this.mVolumeChangeReceiver, new IntentFilter(Constants.ACTION.VOLUME_ACTION));
        exceptionReporter();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        mMightyService = this;
        this.mHandler = new Handler();
        getPreferences();
        mTracker = ((MightyworksAnalyticsApplication) mMightyService.getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER);
        Timber.i("In onStartCommand", new Object[0]);
        sendBroadcast(new Intent(this, BlueToothReceiver.class));
        if (intent == null) {
            return 1;
        }
        if (intent.getAction() == null) {
            this.mStatusChecker.run();
            this.mStatusAlram.run();
            showNotify(getString(R.string.autovolumeoff));
            return 1;
        } else if (!intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
            return 1;
        } else {
            Timber.i("Received Start Foreground Intent ", new Object[0]);
            this.mStatusChecker.run();
            this.mStatusAlram.run();
            if (isAVCRunning()) {
                showNotify(getString(R.string.autovolumeon));
                return 1;
            }
            showNotify(getString(R.string.autovolumeoff));
            return 1;
        }
    }

    public void showNotify(String message) {
        int i;
        int i2 = 4;
        Intent notificationIntent = new Intent(this, TabMainActivity.class);
        Intent runIntent = new Intent(this, RunStartListner.class);
        Intent stopIntent = new Intent(this, RunStopListner.class);
        notificationIntent.setAction(Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(872415232);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
        PendingIntent runContent = PendingIntent.getBroadcast(this, 0, runIntent, 0);
        PendingIntent stopContent = PendingIntent.getBroadcast(this, 0, stopIntent, 0);
        remote = new RemoteViews(getPackageName(), R.layout.notification);
        RemoteViews remoteViews = remote;
        if (_RunState) {
            i = 0;
        } else {
            i = 4;
        }
        remoteViews.setViewVisibility(R.id.btn_noiton, i);
        RemoteViews remoteViews2 = remote;
        if (!_RunState) {
            i2 = 0;
        }
        remoteViews2.setViewVisibility(R.id.btn_noitoff, i2);
        remote.setTextViewText(R.id.textView2, message);
        remote.setOnClickPendingIntent(R.id.btn_noiton, stopContent);
        remote.setOnClickPendingIntent(R.id.btn_noitoff, runContent);
        remote.setOnClickPendingIntent(R.id.noti1, pendingIntent);
        startForeground(Constants.NOTIFICATION_ID.FOREGROUND_SERVICE, new NotificationCompat.Builder(this).setContentTitle("The Volume").setTicker("The Volume").setContentText("AVC").setSmallIcon(R.drawable.logo).setContent(remote).setOngoing(true).build());
    }

    public void onDestroy() {
        super.onDestroy();
        Timber.i("In onDestroy", new Object[0]);
        getApplicationContext().unregisterReceiver(this.mHeadSetCheckReceiver);
        getApplicationContext().unregisterReceiver(this.mPhonecallReceiver);
        getApplicationContext().unregisterReceiver(this.mVolumeChangeReceiver);
        _avc.stop();
        _avc = null;
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public static void autoButtonClick(boolean state) {
        _AutoButtonState = state;
    }

    public static void runState(boolean state) {
        _RunState = state;
    }

    public static boolean getRunState() {
        return _RunState;
    }

    public static boolean isAVCRunning() {
        return _avc != null && _avc.isRunning();
    }

    public static int getServiceNoiseGain() {
        return _avc.getNoiseGain();
    }

    public static int getServiceMediaGain() {
        return _avc.getMediaVolumeIndex();
    }

    public static void avcStart() {
        _StartTime = System.currentTimeMillis();
        int getMemiaGain = _ServiceAudioManager.getStreamVolume(3);
        minMediaGain.putInt("mingain", getMemiaGain);
        minMediaGain.commit();
        AutoVolumeControl.setMinMediaVolumeLevel(getMemiaGain);
        Timber.d("getMemiaGain " + getMemiaGain, new Object[0]);
        if (_avc == null) {
            _avc = new AutoVolumeControl(mMightyService);
            _avc.start();
        }
    }

    public static void avcStop() {
        _StopTime = System.currentTimeMillis();
        _TimeValue = _StopTime - _StartTime;
        mTracker.send(new HitBuilders.TimingBuilder().setCategory("User Behavior").setValue(_TimeValue).setVariable("AVC on").build());
        if (_avc != null) {
            _avc.stop();
            _avc = null;
        }
    }

    private void getPreferences() {
        AutoVolumeControl.SensitivityLevel level;
        minMediaValue = getSharedPreferences("minmediagain", 0);
        minMediaGain = minMediaValue.edit();
        this._getPreferencesValue = getSharedPreferences("senstivity", 0).getString("min", this._getPreferencesValue);
        if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Very_Low.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Very_Low;
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Low.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Low;
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Middle.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Middle;
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.High.toString())) {
            level = AutoVolumeControl.SensitivityLevel.High;
        } else if (this._getPreferencesValue.equals(AutoVolumeControl.SensitivityLevel.Very_High.toString())) {
            level = AutoVolumeControl.SensitivityLevel.Very_High;
        } else {
            level = AutoVolumeControl.SensitivityLevel.Middle;
        }
        AutoVolumeControl.setSensitivityLevel(level);
    }

    public static boolean _LongTimeAvcOff() {
        return _AVCOFF;
    }

    public static void set_LongTimeAvcOff(boolean off) {
        _AVCOFF = off;
    }

    private String getProcessImportance(int importance) {
        if (400 == importance) {
            return "IMPORTANCE_BACKGROUND";
        }
        if (300 == importance) {
            return "IMPORTANCE_SERVICE";
        }
        if (200 == importance) {
            return "IMPORTANCE_VISIBLE";
        }
        if (100 == importance) {
            return Constants.ACTION.FOREGRUOND;
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void getRunningProcessList() {
        boolean isNavigationForeground = false;
        for (ActivityManager.RunningAppProcessInfo rap : ((ActivityManager) getSystemService("activity")).getRunningAppProcesses()) {
            for (String packageName : NavigationApps.apps) {
                if (rap.processName.equals(packageName) && getProcessImportance(rap.importance).equals(Constants.ACTION.FOREGRUOND) && rap.pid != this.mNavigationAppPID) {
                    isNavigationForeground = true;
                    this.mNavigationAppPID = rap.pid;
                    Timber.d("run = " + getProcessImportance(rap.importance), new Object[0]);
                }
            }
        }
        if (isNavigationForeground && _avc == null) {
            autoRunStart();
            _avc.setStartedByNavigation(true);
        }
    }

    public static void autoRunStart() {
        autoButtonClick(true);
        avcStart();
        runState(true);
        mMightyService.sendBroadcast(new Intent(Constants.ACTION.IntentNotificationUserAction));
    }

    public static void autoRunStop() {
        runState(false);
        avcStop();
        autoButtonClick(true);
        mMightyService.sendBroadcast(new Intent(Constants.ACTION.IntentNotificationUserAction));
    }

    private void exceptionReporter() {
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionReporter(((MightyworksAnalyticsApplication) getApplication()).getTracker(MightyworksAnalyticsApplication.TrackerName.APP_TRACKER), Thread.getDefaultUncaughtExceptionHandler(), this));
    }
}
