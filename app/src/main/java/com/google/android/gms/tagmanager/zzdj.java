package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzd;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdj {
    private static zzbw<zzd.zza> zza(zzbw<zzd.zza> zzbw) {
        try {
            return new zzbw<>(zzdf.zzE(zzei(zzdf.zzg(zzbw.getObject()))), zzbw.zzxg());
        } catch (UnsupportedEncodingException e) {
            zzbg.zzb("Escape URI: unsupported encoding", e);
            return zzbw;
        }
    }

    private static zzbw<zzd.zza> zza(zzbw<zzd.zza> zzbw, int i) {
        if (!zzn(zzbw.getObject())) {
            zzbg.zzak("Escaping can only be applied to strings.");
            return zzbw;
        }
        switch (i) {
            case 12:
                return zza(zzbw);
            default:
                zzbg.zzak("Unsupported Value Escaping: " + i);
                return zzbw;
        }
    }

    static zzbw<zzd.zza> zza(zzbw<zzd.zza> zzbw, int... iArr) {
        for (int zza : iArr) {
            zzbw = zza(zzbw, zza);
        }
        return zzbw;
    }

    static String zzei(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }

    private static boolean zzn(zzd.zza zza) {
        return zzdf.zzl(zza) instanceof String;
    }
}
