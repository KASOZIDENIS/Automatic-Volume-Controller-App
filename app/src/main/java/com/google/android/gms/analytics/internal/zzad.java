package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

@Deprecated
public class zzad {
    private static volatile Logger zzIo;

    static {
        setLogger(new zzr());
    }

    public static Logger getLogger() {
        return zzIo;
    }

    public static void setLogger(Logger logger) {
        zzIo = logger;
    }

    public static boolean zzC(int i) {
        return getLogger() != null && getLogger().getLogLevel() <= i;
    }

    public static void zzal(String str) {
        zzae zziV = zzae.zziV();
        if (zziV != null) {
            zziV.zzaH(str);
        } else if (zzC(1)) {
            Log.i(zzx.zzHf.get(), str);
        }
        Logger logger = zzIo;
        if (logger != null) {
            logger.info(str);
        }
    }

    public static void zzam(String str) {
        zzae zziV = zzae.zziV();
        if (zziV != null) {
            zziV.zzaF(str);
        } else if (zzC(0)) {
            Log.v(zzx.zzHf.get(), str);
        }
        Logger logger = zzIo;
        if (logger != null) {
            logger.verbose(str);
        }
    }

    public static void zzan(String str) {
        zzae zziV = zzae.zziV();
        if (zziV != null) {
            zziV.zzaI(str);
        } else if (zzC(2)) {
            Log.w(zzx.zzHf.get(), str);
        }
        Logger logger = zzIo;
        if (logger != null) {
            logger.warn(str);
        }
    }

    public static void zzf(String str, Object obj) {
        zzae zziV = zzae.zziV();
        if (zziV != null) {
            zziV.zze(str, obj);
        } else if (zzC(3)) {
            Log.e(zzx.zzHf.get(), obj != null ? str + ":" + obj : str);
        }
        Logger logger = zzIo;
        if (logger != null) {
            logger.error(str);
        }
    }
}
