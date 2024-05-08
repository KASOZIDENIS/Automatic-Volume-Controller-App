package com.google.android.gms.analytics.internal;

public enum zzn {
    NONE,
    GZIP;

    public static zzn zzaP(String str) {
        return "GZIP".equalsIgnoreCase(str) ? GZIP : NONE;
    }
}
