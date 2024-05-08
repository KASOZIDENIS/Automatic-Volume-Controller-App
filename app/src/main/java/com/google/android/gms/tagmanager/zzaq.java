package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class zzaq {
    private final long zzIf;
    private final long zzaDx;
    private final long zzaDy;
    private String zzaDz;

    zzaq(long j, long j2, long j3) {
        this.zzaDx = j;
        this.zzIf = j2;
        this.zzaDy = j3;
    }

    /* access modifiers changed from: package-private */
    public void zzdN(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            this.zzaDz = str;
        }
    }

    /* access modifiers changed from: package-private */
    public long zzwU() {
        return this.zzaDx;
    }

    /* access modifiers changed from: package-private */
    public long zzwV() {
        return this.zzaDy;
    }

    /* access modifiers changed from: package-private */
    public String zzwW() {
        return this.zzaDz;
    }
}
