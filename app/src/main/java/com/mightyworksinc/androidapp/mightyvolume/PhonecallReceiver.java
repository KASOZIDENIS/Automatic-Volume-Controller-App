package com.mightyworksinc.androidapp.mightyvolume;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.telephony.TelephonyManager;
import com.mightyworksinc.androidapp.mightyvolume.Constants;
import timber.log.Timber;

public class PhonecallReceiver extends BroadcastReceiver {
    SharedPreferences _LastAvcStateValue;
    SharedPreferences.Editor _LastAvcStateValueEditor;
    private boolean _iSLastAvcRunning = false;
    private boolean isIncoming;
    private int lastState = 0;

    public void onReceive(Context context, Intent intent) {
        this._LastAvcStateValue = context.getSharedPreferences("lastavcstate", 0);
        this._LastAvcStateValueEditor = this._LastAvcStateValue.edit();
        this._iSLastAvcRunning = this._LastAvcStateValue.getBoolean("last_avc_state", this._iSLastAvcRunning);
        Timber.d("onReceive create = " + this._iSLastAvcRunning, new Object[0]);
        if (intent != null && intent.getAction().equals(Constants.ACTION.Phone_STATE)) {
            String stateStr = intent.getExtras().getString("state");
            int state = 0;
            if (stateStr != null) {
                if (stateStr.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                    state = 0;
                } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                    state = 2;
                } else if (stateStr.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                    state = 1;
                }
            }
            onCallStateChanged(state);
        }
    }

    public void onCallStateChanged(int state) {
        if (this.lastState != state) {
            switch (state) {
                case 0:
                    if (this.lastState != 1) {
                        if (!this.isIncoming) {
                            Timber.d("CALL_STATE_IDLE", new Object[0]);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    PhonecallReceiver.this.autoRun();
                                }
                            }, 10000);
                            break;
                        } else {
                            Timber.d("CALL_STATE_IDLE", new Object[0]);
                            Timber.d("CALL_STATE_IDLE = " + this._iSLastAvcRunning, new Object[0]);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    PhonecallReceiver.this.autoRun();
                                }
                            }, 10000);
                            break;
                        }
                    }
                    break;
                case 1:
                    this.isIncoming = true;
                    break;
                case 2:
                    if (this.lastState != 1) {
                        this.isIncoming = false;
                        if (MightyService.isAVCRunning()) {
                            this._iSLastAvcRunning = true;
                            this._LastAvcStateValueEditor.putBoolean("last_avc_state", true);
                            this._LastAvcStateValueEditor.commit();
                            Timber.d("CALL_STATE_OFFHOOK = " + this._iSLastAvcRunning, new Object[0]);
                            MightyService.autoRunStop();
                            break;
                        }
                    }
                    break;
            }
            this.lastState = state;
        }
    }

    /* access modifiers changed from: private */
    public void autoRun() {
        if (this._iSLastAvcRunning) {
            this._iSLastAvcRunning = false;
            this._LastAvcStateValueEditor.putBoolean("last_avc_state", false);
            this._LastAvcStateValueEditor.commit();
            if (!MightyService.isAVCRunning()) {
                MightyService.autoRunStart();
            }
        }
    }
}
