package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkr extends zzki<zzkr> {
    private String zzMf;
    public int zzawH;
    public int zzawI;
    public int zzawJ;
    public int zzvR;
    public int zzvS;

    public String getLanguage() {
        return this.zzMf;
    }

    public void setLanguage(String language) {
        this.zzMf = language;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("language", this.zzMf);
        hashMap.put("screenColors", Integer.valueOf(this.zzawH));
        hashMap.put("screenWidth", Integer.valueOf(this.zzvR));
        hashMap.put("screenHeight", Integer.valueOf(this.zzvS));
        hashMap.put("viewportWidth", Integer.valueOf(this.zzawI));
        hashMap.put("viewportHeight", Integer.valueOf(this.zzawJ));
        return zzu(hashMap);
    }

    public void zza(zzkr zzkr) {
        if (this.zzawH != 0) {
            zzkr.zzgD(this.zzawH);
        }
        if (this.zzvR != 0) {
            zzkr.zzgE(this.zzvR);
        }
        if (this.zzvS != 0) {
            zzkr.zzgF(this.zzvS);
        }
        if (this.zzawI != 0) {
            zzkr.zzgG(this.zzawI);
        }
        if (this.zzawJ != 0) {
            zzkr.zzgH(this.zzawJ);
        }
        if (!TextUtils.isEmpty(this.zzMf)) {
            zzkr.setLanguage(this.zzMf);
        }
    }

    public void zzgD(int i) {
        this.zzawH = i;
    }

    public void zzgE(int i) {
        this.zzvR = i;
    }

    public void zzgF(int i) {
        this.zzvS = i;
    }

    public void zzgG(int i) {
        this.zzawI = i;
    }

    public void zzgH(int i) {
        this.zzawJ = i;
    }

    public int zzuA() {
        return this.zzvR;
    }

    public int zzuB() {
        return this.zzvS;
    }

    public int zzuC() {
        return this.zzawI;
    }

    public int zzuD() {
        return this.zzawJ;
    }

    public int zzuz() {
        return this.zzawH;
    }
}
