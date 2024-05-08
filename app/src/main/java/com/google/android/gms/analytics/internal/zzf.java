package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.internal.zzkk;

public class zzf {
    private final Context zzFV;
    private final Context zzoh;

    public zzf(Context context) {
        zzv.zzr(context);
        Context applicationContext = context.getApplicationContext();
        zzv.zzb(applicationContext, (Object) "Application context can't be null");
        this.zzoh = applicationContext;
        this.zzFV = applicationContext;
    }

    public Context getApplicationContext() {
        return this.zzoh;
    }

    /* access modifiers changed from: protected */
    public zzkk zzK(Context context) {
        return zzkk.zzak(context);
    }

    /* access modifiers changed from: protected */
    public zzt zza(zze zze) {
        return new zzt(zze);
    }

    /* access modifiers changed from: protected */
    public zzj zzb(zze zze) {
        return new zzj(zze);
    }

    /* access modifiers changed from: protected */
    public zza zzc(zze zze) {
        return new zza(zze);
    }

    /* access modifiers changed from: protected */
    public zzm zzd(zze zze) {
        return new zzm(zze);
    }

    /* access modifiers changed from: protected */
    public zzam zze(zze zze) {
        return new zzam(zze);
    }

    /* access modifiers changed from: protected */
    public zzae zzf(zze zze) {
        return new zzae(zze);
    }

    /* access modifiers changed from: protected */
    public zzq zzg(zze zze) {
        return new zzq(zze);
    }

    public Context zzgT() {
        return this.zzFV;
    }

    /* access modifiers changed from: protected */
    public zzht zzh(zze zze) {
        return zzhv.zznd();
    }

    /* access modifiers changed from: protected */
    public GoogleAnalytics zzi(zze zze) {
        return new GoogleAnalytics(zze);
    }

    /* access modifiers changed from: package-private */
    public zzk zzj(zze zze) {
        return new zzk(zze, this);
    }

    /* access modifiers changed from: package-private */
    public zzaf zzk(zze zze) {
        return new zzaf(zze);
    }

    /* access modifiers changed from: protected */
    public zzb zzl(zze zze) {
        return new zzb(zze, this);
    }

    public zzi zzm(zze zze) {
        return new zzi(zze);
    }

    public zzag zzn(zze zze) {
        return new zzag(zze);
    }

    public zzh zzo(zze zze) {
        return new zzh(zze);
    }

    public zzu zzp(zze zze) {
        return new zzu(zze);
    }

    public zzah zzq(zze zze) {
        return new zzah(zze);
    }
}
