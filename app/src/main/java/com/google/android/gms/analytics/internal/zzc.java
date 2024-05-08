package com.google.android.gms.analytics.internal;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzkk;

public class zzc {
    private final zze zzFD;

    protected zzc(zze zze) {
        zzv.zzr(zze);
        this.zzFD = zze;
    }

    private void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zzae zzae = null;
        if (this.zzFD != null) {
            zzae = this.zzFD.zzgU();
        }
        if (zzae != null) {
            zzae.zza(i, str, obj, obj2, obj3);
            return;
        }
        String str2 = zzx.zzHf.get();
        if (Log.isLoggable(str2, i)) {
            Log.println(i, str2, zzc(str, obj, obj2, obj3));
        }
    }

    protected static String zzc(String str, Object obj, Object obj2, Object obj3) {
        if (str == null) {
            str = "";
        }
        String zzf = zzf(obj);
        String zzf2 = zzf(obj2);
        String zzf3 = zzf(obj3);
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
            str2 = ": ";
        }
        if (!TextUtils.isEmpty(zzf)) {
            sb.append(str2);
            sb.append(zzf);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzf2)) {
            sb.append(str2);
            sb.append(zzf2);
            str2 = ", ";
        }
        if (!TextUtils.isEmpty(zzf3)) {
            sb.append(str2);
            sb.append(zzf3);
        }
        return sb.toString();
    }

    private static String zzf(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Throwable ? ((Throwable) obj).toString() : obj.toString();
        }
        return obj == Boolean.TRUE ? "true" : "false";
    }

    /* access modifiers changed from: protected */
    public Context getContext() {
        return this.zzFD.getContext();
    }

    public void zza(String str, Object obj) {
        zza(2, str, obj, (Object) null, (Object) null);
    }

    public void zza(String str, Object obj, Object obj2) {
        zza(2, str, obj, obj2, (Object) null);
    }

    public void zza(String str, Object obj, Object obj2, Object obj3) {
        zza(3, str, obj, obj2, obj3);
    }

    public void zzaF(String str) {
        zza(2, str, (Object) null, (Object) null, (Object) null);
    }

    public void zzaG(String str) {
        zza(3, str, (Object) null, (Object) null, (Object) null);
    }

    public void zzaH(String str) {
        zza(4, str, (Object) null, (Object) null, (Object) null);
    }

    public void zzaI(String str) {
        zza(5, str, (Object) null, (Object) null, (Object) null);
    }

    public void zzaJ(String str) {
        zza(6, str, (Object) null, (Object) null, (Object) null);
    }

    public void zzb(String str, Object obj) {
        zza(3, str, obj, (Object) null, (Object) null);
    }

    public void zzb(String str, Object obj, Object obj2) {
        zza(3, str, obj, obj2, (Object) null);
    }

    public void zzb(String str, Object obj, Object obj2, Object obj3) {
        zza(5, str, obj, obj2, obj3);
    }

    public void zzc(String str, Object obj) {
        zza(4, str, obj, (Object) null, (Object) null);
    }

    public void zzc(String str, Object obj, Object obj2) {
        zza(5, str, obj, obj2, (Object) null);
    }

    public void zzd(String str, Object obj) {
        zza(5, str, obj, (Object) null, (Object) null);
    }

    public void zzd(String str, Object obj, Object obj2) {
        zza(6, str, obj, obj2, (Object) null);
    }

    public void zze(String str, Object obj) {
        zza(6, str, obj, (Object) null, (Object) null);
    }

    public GoogleAnalytics zzfT() {
        return this.zzFD.zzgV();
    }

    /* access modifiers changed from: protected */
    public zzb zzfZ() {
        return this.zzFD.zzfZ();
    }

    public zze zzgD() {
        return this.zzFD;
    }

    /* access modifiers changed from: protected */
    public void zzgE() {
        if (zzgI().zzhP()) {
            throw new IllegalStateException("Call only supported on the client side");
        }
    }

    /* access modifiers changed from: protected */
    public void zzgF() {
        this.zzFD.zzgF();
    }

    /* access modifiers changed from: protected */
    public zzht zzgG() {
        return this.zzFD.zzgG();
    }

    /* access modifiers changed from: protected */
    public zzae zzgH() {
        return this.zzFD.zzgH();
    }

    /* access modifiers changed from: protected */
    public zzq zzgI() {
        return this.zzFD.zzgI();
    }

    /* access modifiers changed from: protected */
    public zzkk zzgJ() {
        return this.zzFD.zzgJ();
    }

    /* access modifiers changed from: protected */
    public zzu zzgK() {
        return this.zzFD.zzgK();
    }

    /* access modifiers changed from: protected */
    public zzah zzgL() {
        return this.zzFD.zzgL();
    }

    /* access modifiers changed from: protected */
    public zzm zzgM() {
        return this.zzFD.zzgY();
    }

    /* access modifiers changed from: protected */
    public zza zzgN() {
        return this.zzFD.zzgX();
    }

    /* access modifiers changed from: protected */
    public zzj zzgO() {
        return this.zzFD.zzgO();
    }

    /* access modifiers changed from: protected */
    public zzt zzgP() {
        return this.zzFD.zzgP();
    }

    public boolean zzgQ() {
        return Log.isLoggable(zzx.zzHf.get(), 2);
    }

    /* access modifiers changed from: protected */
    public zzam zzga() {
        return this.zzFD.zzga();
    }
}
