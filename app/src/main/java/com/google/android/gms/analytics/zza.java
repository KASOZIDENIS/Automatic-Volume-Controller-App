package com.google.android.gms.analytics;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzgp;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzkm;
import java.util.ListIterator;

public class zza extends zzkj<zza> {
    private final zze zzEb;
    private boolean zzEc;

    public zza(zze zze) {
        super(zze.zzgJ(), zze.zzgG());
        this.zzEb = zze;
    }

    public void enableAdvertisingIdCollection(boolean enable) {
        this.zzEc = enable;
    }

    /* access modifiers changed from: protected */
    public void zza(zzkg zzkg) {
        zzgp zzgp = (zzgp) zzkg.zze(zzgp.class);
        if (TextUtils.isEmpty(zzgp.getClientId())) {
            zzgp.setClientId(this.zzEb.zzgY().zzhI());
        }
        if (this.zzEc && TextUtils.isEmpty(zzgp.zzgm())) {
            com.google.android.gms.analytics.internal.zza zzgX = this.zzEb.zzgX();
            zzgp.zzaA(zzgX.zzgr());
            zzgp.zzE(zzgX.zzgn());
        }
    }

    public void zzat(String str) {
        zzv.zzbS(str);
        zzau(str);
        zzul().add(new zzb(this.zzEb, str));
    }

    public void zzau(String str) {
        Uri zzav = zzb.zzav(str);
        ListIterator<zzkm> listIterator = zzul().listIterator();
        while (listIterator.hasNext()) {
            if (zzav.equals(listIterator.next().zzfR())) {
                listIterator.remove();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public zze zzfO() {
        return this.zzEb;
    }

    public zzkg zzfP() {
        zzkg zztZ = zzuk().zztZ();
        zztZ.zzb(this.zzEb.zzgO().zzhq());
        zztZ.zzb(this.zzEb.zzgP().zzix());
        zzd(zztZ);
        return zztZ;
    }
}
