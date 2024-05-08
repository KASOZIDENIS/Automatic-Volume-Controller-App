package com.google.android.gms.internal;

import android.content.res.Configuration;
import android.content.res.Resources;

public final class zzhw {
    public static boolean zzb(Resources resources) {
        if (resources == null) {
            return false;
        }
        return (zzic.zzne() && ((resources.getConfiguration().screenLayout & 15) > 3)) || zzc(resources);
    }

    private static boolean zzc(Resources resources) {
        Configuration configuration = resources.getConfiguration();
        return zzic.zzng() && (configuration.screenLayout & 15) <= 3 && configuration.smallestScreenWidthDp >= 600;
    }
}
