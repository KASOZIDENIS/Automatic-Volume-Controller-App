package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

class zzcf extends zzcz {
    private static final String ID = zza.REGEX.toString();
    private static final String zzaEC = zzb.IGNORE_CASE.toString();

    public zzcf() {
        super(ID);
    }

    /* access modifiers changed from: protected */
    public boolean zza(String str, String str2, Map<String, zzd.zza> map) {
        try {
            return Pattern.compile(str2, zzdf.zzk(map.get(zzaEC)).booleanValue() ? 66 : 64).matcher(str).find();
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
