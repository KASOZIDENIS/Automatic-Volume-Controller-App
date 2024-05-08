package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzd;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

abstract class zzak {
    private final Set<String> zzaDu;
    private final String zzaDv;

    public zzak(String str, String... strArr) {
        this.zzaDv = str;
        this.zzaDu = new HashSet(strArr.length);
        for (String add : strArr) {
            this.zzaDu.add(add);
        }
    }

    public abstract zzd.zza zzD(Map<String, zzd.zza> map);

    /* access modifiers changed from: package-private */
    public boolean zzg(Set<String> set) {
        return set.containsAll(this.zzaDu);
    }

    public String zzwS() {
        return this.zzaDv;
    }

    public Set<String> zzwT() {
        return this.zzaDu;
    }

    public abstract boolean zzwn();
}
