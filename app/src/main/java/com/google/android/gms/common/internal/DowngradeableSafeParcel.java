package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public abstract class DowngradeableSafeParcel implements SafeParcelable {
    private static final Object zzTd = new Object();
    private static ClassLoader zzTe = null;
    private static Integer zzTf = null;
    private boolean zzTg = false;

    private static boolean zza(Class<?> cls) {
        try {
            return SafeParcelable.NULL.equals(cls.getField("NULL").get((Object) null));
        } catch (IllegalAccessException | NoSuchFieldException e) {
            return false;
        }
    }

    protected static boolean zzbK(String str) {
        ClassLoader zzlO = zzlO();
        if (zzlO == null) {
            return true;
        }
        try {
            return zza(zzlO.loadClass(str));
        } catch (Exception e) {
            return false;
        }
    }

    protected static ClassLoader zzlO() {
        ClassLoader classLoader;
        synchronized (zzTd) {
            classLoader = zzTe;
        }
        return classLoader;
    }

    protected static Integer zzlP() {
        Integer num;
        synchronized (zzTd) {
            num = zzTf;
        }
        return num;
    }

    /* access modifiers changed from: protected */
    public boolean zzlQ() {
        return this.zzTg;
    }
}
