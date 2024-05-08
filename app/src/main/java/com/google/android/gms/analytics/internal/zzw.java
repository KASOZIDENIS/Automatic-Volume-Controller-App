package com.google.android.gms.analytics.internal;

import android.os.Build;
import java.io.File;

public class zzw {
    public static int version() {
        try {
            return Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException e) {
            zzad.zzf("Invalid version number", Build.VERSION.SDK);
            return 0;
        }
    }

    public static boolean zzaQ(String str) {
        if (version() < 9) {
            return false;
        }
        File file = new File(str);
        file.setReadable(false, false);
        file.setWritable(false, false);
        file.setReadable(true, true);
        file.setWritable(true, true);
        return true;
    }
}
