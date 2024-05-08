package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzhc<T> {
    /* access modifiers changed from: private */
    public static zza zzRo = null;
    private static int zzRp = 0;
    private static final Object zznu = new Object();
    private T zzHW = null;
    protected final String zzra;
    protected final T zzrb;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zzb(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzhc(String str, T t) {
        this.zzra = str;
        this.zzrb = t;
    }

    public static boolean isInitialized() {
        return zzRo != null;
    }

    public static zzhc<Float> zza(String str, Float f) {
        return new zzhc<Float>(str, f) {
            /* access modifiers changed from: protected */
            /* renamed from: zzbD */
            public Float zzbz(String str) {
                return zzhc.zzRo.zzb(this.zzra, (Float) this.zzrb);
            }
        };
    }

    public static zzhc<Integer> zza(String str, Integer num) {
        return new zzhc<Integer>(str, num) {
            /* access modifiers changed from: protected */
            /* renamed from: zzbC */
            public Integer zzbz(String str) {
                return zzhc.zzRo.zzb(this.zzra, (Integer) this.zzrb);
            }
        };
    }

    public static zzhc<Long> zza(String str, Long l) {
        return new zzhc<Long>(str, l) {
            /* access modifiers changed from: protected */
            /* renamed from: zzbB */
            public Long zzbz(String str) {
                return zzhc.zzRo.getLong(this.zzra, (Long) this.zzrb);
            }
        };
    }

    public static zzhc<Boolean> zzg(String str, boolean z) {
        return new zzhc<Boolean>(str, Boolean.valueOf(z)) {
            /* access modifiers changed from: protected */
            /* renamed from: zzbA */
            public Boolean zzbz(String str) {
                return zzhc.zzRo.zzb(this.zzra, (Boolean) this.zzrb);
            }
        };
    }

    public static int zzlj() {
        return zzRp;
    }

    public static zzhc<String> zzr(String str, String str2) {
        return new zzhc<String>(str, str2) {
            /* access modifiers changed from: protected */
            /* renamed from: zzbE */
            public String zzbz(String str) {
                return zzhc.zzRo.getString(this.zzra, (String) this.zzrb);
            }
        };
    }

    public final T get() {
        return this.zzHW != null ? this.zzHW : zzbz(this.zzra);
    }

    /* access modifiers changed from: protected */
    public abstract T zzbz(String str);

    public final T zzlk() {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return get();
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
