package com.google.android.gms.internal;

import android.text.TextUtils;
import java.util.HashMap;

public final class zzku extends zzki<zzku> {
    public String zzadH;
    public boolean zzawL;

    public String getDescription() {
        return this.zzadH;
    }

    public void setDescription(String description) {
        this.zzadH = description;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("description", this.zzadH);
        hashMap.put("fatal", Boolean.valueOf(this.zzawL));
        return zzu(hashMap);
    }

    public void zza(zzku zzku) {
        if (!TextUtils.isEmpty(this.zzadH)) {
            zzku.setDescription(this.zzadH);
        }
        if (this.zzawL) {
            zzku.zzab(this.zzawL);
        }
    }

    public void zzab(boolean z) {
        this.zzawL = z;
    }

    public boolean zzuJ() {
        return this.zzawL;
    }
}
