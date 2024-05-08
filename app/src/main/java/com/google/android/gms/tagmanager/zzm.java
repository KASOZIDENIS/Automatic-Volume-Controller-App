package com.google.android.gms.tagmanager;

import android.os.Build;

class zzm<K, V> {
    final zza<K, V> zzaCi = new zza<K, V>() {
        public int sizeOf(K k, V v) {
            return 1;
        }
    };

    public interface zza<K, V> {
        int sizeOf(K k, V v);
    }

    public zzl<K, V> zza(int i, zza<K, V> zza2) {
        if (i > 0) {
            return zzwp() < 12 ? new zzcw(i, zza2) : new zzba(i, zza2);
        }
        throw new IllegalArgumentException("maxSize <= 0");
    }

    /* access modifiers changed from: package-private */
    public int zzwp() {
        return Build.VERSION.SDK_INT;
    }
}
