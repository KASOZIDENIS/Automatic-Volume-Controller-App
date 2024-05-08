package com.google.android.gms.analytics.internal;

import android.app.Activity;
import java.util.HashMap;
import java.util.Map;

public class zzak implements zzo {
    public String zzEm;
    public double zzII = -1.0d;
    public int zzIJ = -1;
    public int zzIK = -1;
    public int zzIL = -1;
    public int zzIM = -1;
    public Map<String, String> zzIN = new HashMap();

    public int getSessionTimeout() {
        return this.zzIJ;
    }

    public String zzaT(String str) {
        String str2 = this.zzIN.get(str);
        return str2 != null ? str2 : str;
    }

    public boolean zzjA() {
        return this.zzIM == 1;
    }

    public boolean zzjr() {
        return this.zzEm != null;
    }

    public String zzjs() {
        return this.zzEm;
    }

    public boolean zzjt() {
        return this.zzII >= 0.0d;
    }

    public double zzju() {
        return this.zzII;
    }

    public boolean zzjv() {
        return this.zzIJ >= 0;
    }

    public boolean zzjw() {
        return this.zzIK != -1;
    }

    public boolean zzjx() {
        return this.zzIK == 1;
    }

    public boolean zzjy() {
        return this.zzIL != -1;
    }

    public boolean zzjz() {
        return this.zzIL == 1;
    }

    public String zzq(Activity activity) {
        return zzaT(activity.getClass().getCanonicalName());
    }
}
