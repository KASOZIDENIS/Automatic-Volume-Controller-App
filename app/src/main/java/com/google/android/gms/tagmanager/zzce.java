package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzce extends zzak {
    private static final String ID = zza.REGEX_GROUP.toString();
    private static final String zzaEA = zzb.ARG0.toString();
    private static final String zzaEB = zzb.ARG1.toString();
    private static final String zzaEC = zzb.IGNORE_CASE.toString();
    private static final String zzaED = zzb.GROUP.toString();

    public zzce() {
        super(ID, zzaEA, zzaEB);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        int i;
        zzd.zza zza = map.get(zzaEA);
        zzd.zza zza2 = map.get(zzaEB);
        if (zza == null || zza == zzdf.zzxW() || zza2 == null || zza2 == zzdf.zzxW()) {
            return zzdf.zzxW();
        }
        int i2 = 64;
        if (zzdf.zzk(map.get(zzaEC)).booleanValue()) {
            i2 = 66;
        }
        zzd.zza zza3 = map.get(zzaED);
        if (zza3 != null) {
            Long zzi = zzdf.zzi(zza3);
            if (zzi == zzdf.zzxR()) {
                return zzdf.zzxW();
            }
            i = zzi.intValue();
            if (i < 0) {
                return zzdf.zzxW();
            }
        } else {
            i = 1;
        }
        try {
            String zzg = zzdf.zzg(zza);
            String str = null;
            Matcher matcher = Pattern.compile(zzdf.zzg(zza2), i2).matcher(zzg);
            if (matcher.find() && matcher.groupCount() >= i) {
                str = matcher.group(i);
            }
            return str == null ? zzdf.zzxW() : zzdf.zzE(str);
        } catch (PatternSyntaxException e) {
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return true;
    }
}
