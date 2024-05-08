package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzap extends zzak {
    private static final String ID = zza.HASH.toString();
    private static final String zzaDq = zzb.ARG0.toString();
    private static final String zzaDs = zzb.INPUT_FORMAT.toString();
    private static final String zzaDw = zzb.ALGORITHM.toString();

    public zzap() {
        super(ID, zzaDq);
    }

    private byte[] zzd(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        byte[] zzdw;
        zzd.zza zza = map.get(zzaDq);
        if (zza == null || zza == zzdf.zzxW()) {
            return zzdf.zzxW();
        }
        String zzg = zzdf.zzg(zza);
        zzd.zza zza2 = map.get(zzaDw);
        String zzg2 = zza2 == null ? "MD5" : zzdf.zzg(zza2);
        zzd.zza zza3 = map.get(zzaDs);
        String zzg3 = zza3 == null ? "text" : zzdf.zzg(zza3);
        if ("text".equals(zzg3)) {
            zzdw = zzg.getBytes();
        } else if ("base16".equals(zzg3)) {
            zzdw = zzk.zzdw(zzg);
        } else {
            zzbg.zzak("Hash: unknown input format: " + zzg3);
            return zzdf.zzxW();
        }
        try {
            return zzdf.zzE(zzk.zzg(zzd(zzg2, zzdw)));
        } catch (NoSuchAlgorithmException e) {
            zzbg.zzak("Hash: unknown algorithm: " + zzg2);
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return true;
    }
}
