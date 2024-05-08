package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zza;
import com.google.android.gms.internal.zzd;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class zzbb extends zzak {
    private static final String ID = zza.LANGUAGE.toString();

    public zzbb() {
        super(ID, new String[0]);
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzdf.zzxW();
        }
        String language = locale.getLanguage();
        return language == null ? zzdf.zzxW() : zzdf.zzE(language.toLowerCase());
    }

    public /* bridge */ /* synthetic */ String zzwS() {
        return super.zzwS();
    }

    public /* bridge */ /* synthetic */ Set zzwT() {
        return super.zzwT();
    }

    public boolean zzwn() {
        return false;
    }
}
