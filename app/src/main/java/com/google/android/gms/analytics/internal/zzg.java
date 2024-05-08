package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzv;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class zzg {
    private final long zzFW;
    private final String zzFX;
    private final boolean zzFY;
    private long zzFZ;
    private final String zzFg;
    private final Map<String, String> zzvi;

    public zzg(long j, String str, String str2, boolean z, long j2, Map<String, String> map) {
        zzv.zzbS(str);
        zzv.zzbS(str2);
        this.zzFW = j;
        this.zzFg = str;
        this.zzFX = str2;
        this.zzFY = z;
        this.zzFZ = j2;
        if (map != null) {
            this.zzvi = new HashMap(map);
        } else {
            this.zzvi = Collections.emptyMap();
        }
    }

    public String getClientId() {
        return this.zzFg;
    }

    public long zzha() {
        return this.zzFW;
    }

    public String zzhb() {
        return this.zzFX;
    }

    public boolean zzhc() {
        return this.zzFY;
    }

    public long zzhd() {
        return this.zzFZ;
    }

    public Map<String, String> zzhe() {
        return this.zzvi;
    }

    public void zzl(long j) {
        this.zzFZ = j;
    }
}
