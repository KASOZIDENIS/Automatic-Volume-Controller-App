package com.google.android.gms.analytics;

import com.google.android.gms.analytics.internal.zzad;

public final class zzc {
    public static String zzE(int i) {
        return zzb("&cd", i);
    }

    public static String zzF(int i) {
        return zzb("cd", i);
    }

    public static String zzG(int i) {
        return zzb("&cm", i);
    }

    public static String zzH(int i) {
        return zzb("cm", i);
    }

    public static String zzI(int i) {
        return zzb("&pr", i);
    }

    public static String zzJ(int i) {
        return zzb("pr", i);
    }

    public static String zzK(int i) {
        return zzb("&promo", i);
    }

    public static String zzL(int i) {
        return zzb("promo", i);
    }

    public static String zzM(int i) {
        return zzb("pi", i);
    }

    public static String zzN(int i) {
        return zzb("&il", i);
    }

    public static String zzO(int i) {
        return zzb("il", i);
    }

    public static String zzP(int i) {
        return zzb("cd", i);
    }

    public static String zzQ(int i) {
        return zzb("cm", i);
    }

    private static String zzb(String str, int i) {
        if (i >= 1) {
            return str + i;
        }
        zzad.zzf("index out of range for prefix", str);
        return "";
    }
}
