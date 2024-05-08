package com.google.android.gms.tagmanager;

import android.util.Base64;
import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;

class zzad extends zzak {
    private static final String ID = zza.ENCODE.toString();
    private static final String zzaDq = zzb.ARG0.toString();
    private static final String zzaDr = zzb.NO_PADDING.toString();
    private static final String zzaDs = zzb.INPUT_FORMAT.toString();
    private static final String zzaDt = zzb.OUTPUT_FORMAT.toString();

    public zzad() {
        super(ID, zzaDq);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        byte[] decode;
        String encodeToString;
        zzd.zza zza = map.get(zzaDq);
        if (zza == null || zza == zzdf.zzxW()) {
            return zzdf.zzxW();
        }
        String zzg = zzdf.zzg(zza);
        zzd.zza zza2 = map.get(zzaDs);
        String zzg2 = zza2 == null ? "text" : zzdf.zzg(zza2);
        zzd.zza zza3 = map.get(zzaDt);
        String zzg3 = zza3 == null ? "base16" : zzdf.zzg(zza3);
        zzd.zza zza4 = map.get(zzaDr);
        int i = (zza4 == null || !zzdf.zzk(zza4).booleanValue()) ? 2 : 3;
        try {
            if ("text".equals(zzg2)) {
                decode = zzg.getBytes();
            } else if ("base16".equals(zzg2)) {
                decode = zzk.zzdw(zzg);
            } else if ("base64".equals(zzg2)) {
                decode = Base64.decode(zzg, i);
            } else if ("base64url".equals(zzg2)) {
                decode = Base64.decode(zzg, i | 8);
            } else {
                zzbg.zzak("Encode: unknown input format: " + zzg2);
                return zzdf.zzxW();
            }
            if ("base16".equals(zzg3)) {
                encodeToString = zzk.zzg(decode);
            } else if ("base64".equals(zzg3)) {
                encodeToString = Base64.encodeToString(decode, i);
            } else if ("base64url".equals(zzg3)) {
                encodeToString = Base64.encodeToString(decode, i | 8);
            } else {
                zzbg.zzak("Encode: unknown output format: " + zzg3);
                return zzdf.zzxW();
            }
            return zzdf.zzE(encodeToString);
        } catch (IllegalArgumentException e) {
            zzbg.zzak("Encode: invalid input:");
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return true;
    }
}
