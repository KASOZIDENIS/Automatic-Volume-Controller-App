package com.google.android.gms.internal;

import android.os.Build;

public final class zzic {
    private static boolean zzbl(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    public static boolean zzne() {
        return zzbl(11);
    }

    public static boolean zznf() {
        return zzbl(12);
    }

    public static boolean zzng() {
        return zzbl(13);
    }

    public static boolean zznh() {
        return zzbl(14);
    }

    public static boolean zzni() {
        return zzbl(16);
    }

    public static boolean zznj() {
        return zzbl(17);
    }

    public static boolean zznk() {
        return zzbl(19);
    }

    @Deprecated
    public static boolean zznl() {
        return zznm();
    }

    public static boolean zznm() {
        return zzbl(21);
    }
}
