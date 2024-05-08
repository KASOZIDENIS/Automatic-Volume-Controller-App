package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzkt extends zzki<zzkt> {
    private String mCategory;
    private long zzahF;
    private String zzawK;
    private String zzso;

    public String getAction() {
        return this.zzso;
    }

    public String getLabel() {
        return this.zzawK;
    }

    public long getValue() {
        return this.zzahF;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put("action", this.zzso);
        hashMap.put("label", this.zzawK);
        hashMap.put("value", Long.valueOf(this.zzahF));
        return zzu(hashMap);
    }

    public void zzL(long j) {
        this.zzahF = j;
    }

    public void zza(zzkt zzkt) {
        if (!TextUtils.isEmpty(this.mCategory)) {
            zzkt.zzda(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzso)) {
            zzkt.zzdb(this.zzso);
        }
        if (!TextUtils.isEmpty(this.zzawK)) {
            zzkt.zzdc(this.zzawK);
        }
        if (this.zzahF != 0) {
            zzkt.zzL(this.zzahF);
        }
    }

    public void zzda(String str) {
        this.mCategory = str;
    }

    public void zzdb(String str) {
        this.zzso = str;
    }

    public void zzdc(String str) {
        this.zzawK = str;
    }

    public String zzuI() {
        return this.mCategory;
    }
}
