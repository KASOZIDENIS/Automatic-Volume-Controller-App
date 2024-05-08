package com.google.android.gms.tagmanager;

import android.util.LruCache;
import com.google.android.gms.tagmanager.zzm;

class zzba<K, V> implements zzl<K, V> {
    private LruCache<K, V> zzaDQ;

    zzba(int i, final zzm.zza<K, V> zza) {
        this.zzaDQ = new LruCache<K, V>(i) {
            /* access modifiers changed from: protected */
            public int sizeOf(K key, V value) {
                return zza.sizeOf(key, value);
            }
        };
    }

    public V get(K key) {
        return this.zzaDQ.get(key);
    }

    public void zze(K k, V v) {
        this.zzaDQ.put(k, v);
    }
}
