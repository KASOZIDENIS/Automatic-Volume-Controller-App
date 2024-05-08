package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzj extends zzdd {
    private static final String ID = com.google.android.gms.internal.zza.ARBITRARY_PIXEL.toString();
    private static final String URL = zzb.URL.toString();
    private static final String zzaCd = zzb.ADDITIONAL_PARAMS.toString();
    private static final String zzaCe = zzb.UNREPEATABLE.toString();
    static final String zzaCf = ("gtm_" + ID + "_unrepeatable");
    private static final Set<String> zzaCg = new HashSet();
    private final Context mContext;
    private final zza zzaCh;

    public interface zza {
        zzar zzwo();
    }

    public zzj(final Context context) {
        this(context, new zza() {
            public zzar zzwo() {
                return zzz.zzao(context);
            }
        });
    }

    zzj(Context context, zza zza2) {
        super(ID, URL);
        this.zzaCh = zza2;
        this.mContext = context;
    }

    private synchronized boolean zzdt(String str) {
        boolean z = true;
        synchronized (this) {
            if (!zzdv(str)) {
                if (zzdu(str)) {
                    zzaCg.add(str);
                } else {
                    z = false;
                }
            }
        }
        return z;
    }

    public void zzF(Map<String, zzd.zza> map) {
        String zzg = map.get(zzaCe) != null ? zzdf.zzg(map.get(zzaCe)) : null;
        if (zzg == null || !zzdt(zzg)) {
            Uri.Builder buildUpon = Uri.parse(zzdf.zzg(map.get(URL))).buildUpon();
            zzd.zza zza2 = map.get(zzaCd);
            if (zza2 != null) {
                Object zzl = zzdf.zzl(zza2);
                if (!(zzl instanceof List)) {
                    zzbg.zzak("ArbitraryPixel: additional params not a list: not sending partial hit: " + buildUpon.build().toString());
                    return;
                }
                for (Object next : (List) zzl) {
                    if (!(next instanceof Map)) {
                        zzbg.zzak("ArbitraryPixel: additional params contains non-map: not sending partial hit: " + buildUpon.build().toString());
                        return;
                    }
                    for (Map.Entry entry : ((Map) next).entrySet()) {
                        buildUpon.appendQueryParameter(entry.getKey().toString(), entry.getValue().toString());
                    }
                }
            }
            String uri = buildUpon.build().toString();
            this.zzaCh.zzwo().zzdK(uri);
            zzbg.zzam("ArbitraryPixel: url = " + uri);
            if (zzg != null) {
                synchronized (zzj.class) {
                    zzaCg.add(zzg);
                    zzcv.zza(this.mContext, zzaCf, zzg, "true");
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzdu(String str) {
        return this.mContext.getSharedPreferences(zzaCf, 0).contains(str);
    }

    /* access modifiers changed from: package-private */
    public boolean zzdv(String str) {
        return zzaCg.contains(str);
    }
}
