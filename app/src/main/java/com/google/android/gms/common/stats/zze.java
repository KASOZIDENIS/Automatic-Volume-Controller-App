package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

public class zze {
    private final SimpleArrayMap<Long, Long> zzVA;
    private final long zzVy;
    private final int zzVz;

    public zze() {
        this.zzVy = 60000;
        this.zzVz = 10;
        this.zzVA = new SimpleArrayMap<>(10);
    }

    public zze(int i, long j) {
        this.zzVy = j;
        this.zzVz = i;
        this.zzVA = new SimpleArrayMap<>();
    }

    private void zzc(long j, long j2) {
        for (int size = this.zzVA.size() - 1; size >= 0; size--) {
            if (j2 - this.zzVA.valueAt(size).longValue() > j) {
                this.zzVA.removeAt(size);
            }
        }
    }

    public boolean zzA(long j) {
        boolean z;
        synchronized (this) {
            z = this.zzVA.remove(Long.valueOf(j)) != null;
        }
        return z;
    }

    public Long zza(Long l) {
        Long put;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.zzVy;
        synchronized (this) {
            while (this.zzVA.size() >= this.zzVz) {
                zzc(j, elapsedRealtime);
                j /= 2;
                Log.w("PassiveTimedConnectionMap", "The max capacity " + this.zzVz + " is not enough. Current durationThreshold is: " + j);
            }
            put = this.zzVA.put(l, Long.valueOf(elapsedRealtime));
        }
        return put;
    }
}
