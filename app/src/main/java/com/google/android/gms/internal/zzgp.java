package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzv;
import java.util.HashMap;

public final class zzgp extends zzki<zzgp> {
    private String zzFf;
    private String zzFg;
    private String zzFh;
    private String zzFi;
    private boolean zzFj;
    private String zzFk;
    private boolean zzFl;
    private double zzFm;

    public String getClientId() {
        return this.zzFg;
    }

    public void setClientId(String clientId) {
        this.zzFg = clientId;
    }

    public void setSampleRate(double percentage) {
        zzv.zzb(percentage >= 0.0d && percentage <= 100.0d, (Object) "Sample rate must be between 0% and 100%");
        this.zzFm = percentage;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("hitType", this.zzFf);
        hashMap.put("clientId", this.zzFg);
        hashMap.put("userId", this.zzFh);
        hashMap.put("androidAdId", this.zzFi);
        hashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzFj));
        hashMap.put("sessionControl", this.zzFk);
        hashMap.put("nonInteraction", Boolean.valueOf(this.zzFl));
        hashMap.put("sampleRate", Double.valueOf(this.zzFm));
        return zzu(hashMap);
    }

    public void zzE(boolean z) {
        this.zzFj = z;
    }

    public void zzF(boolean z) {
        this.zzFl = z;
    }

    public void zza(zzgp zzgp) {
        if (!TextUtils.isEmpty(this.zzFf)) {
            zzgp.zzay(this.zzFf);
        }
        if (!TextUtils.isEmpty(this.zzFg)) {
            zzgp.setClientId(this.zzFg);
        }
        if (!TextUtils.isEmpty(this.zzFh)) {
            zzgp.zzaz(this.zzFh);
        }
        if (!TextUtils.isEmpty(this.zzFi)) {
            zzgp.zzaA(this.zzFi);
        }
        if (this.zzFj) {
            zzgp.zzE(true);
        }
        if (!TextUtils.isEmpty(this.zzFk)) {
            zzgp.zzaB(this.zzFk);
        }
        if (this.zzFl) {
            zzgp.zzF(this.zzFl);
        }
        if (this.zzFm != 0.0d) {
            zzgp.setSampleRate(this.zzFm);
        }
    }

    public void zzaA(String str) {
        this.zzFi = str;
    }

    public void zzaB(String str) {
        this.zzFk = str;
    }

    public void zzay(String str) {
        this.zzFf = str;
    }

    public void zzaz(String str) {
        this.zzFh = str;
    }

    public String zzgk() {
        return this.zzFf;
    }

    public String zzgl() {
        return this.zzFh;
    }

    public String zzgm() {
        return this.zzFi;
    }

    public boolean zzgn() {
        return this.zzFj;
    }

    public String zzgo() {
        return this.zzFk;
    }

    public boolean zzgp() {
        return this.zzFl;
    }

    public double zzgq() {
        return this.zzFm;
    }
}
