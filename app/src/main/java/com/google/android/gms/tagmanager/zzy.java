package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy implements zzbh {
    private int zzGV = 5;

    public void setLogLevel(int logLevel) {
        this.zzGV = logLevel;
    }

    public void zzaj(String str) {
        if (this.zzGV <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public void zzak(String str) {
        if (this.zzGV <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public void zzal(String str) {
        if (this.zzGV <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public void zzam(String str) {
        if (this.zzGV <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public void zzan(String str) {
        if (this.zzGV <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    public void zzb(String str, Throwable th) {
        if (this.zzGV <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public void zzd(String str, Throwable th) {
        if (this.zzGV <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }
}
