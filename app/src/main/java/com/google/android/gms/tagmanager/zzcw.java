package com.google.android.gms.tagmanager;

import com.google.android.gms.tagmanager.zzm;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class zzcw<K, V> implements zzl<K, V> {
    private final Map<K, V> zzaFD = new HashMap();
    private final int zzaFE;
    private final zzm.zza<K, V> zzaFF;
    private int zzaFG;

    zzcw(int i, zzm.zza<K, V> zza) {
        this.zzaFE = i;
        this.zzaFF = zza;
    }

    public synchronized V get(K key) {
        return this.zzaFD.get(key);
    }

    public synchronized void zze(K k, V v) {
        if (k == null || v == null) {
            throw new NullPointerException("key == null || value == null");
        }
        this.zzaFG += this.zzaFF.sizeOf(k, v);
        if (this.zzaFG > this.zzaFE) {
            Iterator<Map.Entry<K, V>> it = this.zzaFD.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry next = it.next();
                this.zzaFG -= this.zzaFF.sizeOf(next.getKey(), next.getValue());
                it.remove();
                if (this.zzaFG <= this.zzaFE) {
                    break;
                }
            }
        }
        this.zzaFD.put(k, v);
    }
}
