package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzr implements Logger {
    private boolean zzEC;
    private int zzGV = 2;

    zzr() {
    }

    public void error(Exception exception) {
    }

    public void error(String msg) {
    }

    public int getLogLevel() {
        return this.zzGV;
    }

    public void info(String msg) {
    }

    public void setLogLevel(int level) {
        this.zzGV = level;
        if (!this.zzEC) {
            Log.i(zzx.zzHf.get(), "Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag." + zzx.zzHf.get() + " DEBUG");
            this.zzEC = true;
        }
    }

    public void verbose(String msg) {
    }

    public void warn(String msg) {
    }
}
