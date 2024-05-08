package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class zzgo extends zzki<zzgo> {
    private final Map<String, Object> zzvi = new HashMap();

    private String zzax(String str) {
        zzv.zzbS(str);
        if (str != null && str.startsWith("&")) {
            str = str.substring(1);
        }
        zzv.zzh(str, "Name can not be empty or \"&\"");
        return str;
    }

    public void set(String name, String value) {
        this.zzvi.put(zzax(name), value);
    }

    public String toString() {
        return zzu(this.zzvi);
    }

    public void zza(zzgo zzgo) {
        zzv.zzr(zzgo);
        zzgo.zzvi.putAll(this.zzvi);
    }

    public Map<String, Object> zzgj() {
        return Collections.unmodifiableMap(this.zzvi);
    }
}
