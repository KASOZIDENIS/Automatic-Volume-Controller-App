package com.google.android.gms.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzgn extends zzki<zzgn> {
    private Map<Integer, Double> zzFe = new HashMap(4);

    public String toString() {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzFe.entrySet()) {
            hashMap.put("metric" + next.getKey(), next.getValue());
        }
        return zzu(hashMap);
    }

    public void zza(zzgn zzgn) {
        zzgn.zzFe.putAll(this.zzFe);
    }

    public Map<Integer, Double> zzgi() {
        return Collections.unmodifiableMap(this.zzFe);
    }
}
