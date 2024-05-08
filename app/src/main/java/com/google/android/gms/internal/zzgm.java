package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzgm extends zzki<zzgm> {
    private Map<Integer, String> zzFd = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzFd.entrySet()) {
            hashMap.put("dimension" + next.getKey(), next.getValue());
        }
        return zzu(hashMap);
    }

    public void zza(zzgm zzgm) {
        zzgm.zzFd.putAll(this.zzFd);
    }

    public Map<Integer, String> zzgh() {
        return Collections.unmodifiableMap(this.zzFd);
    }
}
