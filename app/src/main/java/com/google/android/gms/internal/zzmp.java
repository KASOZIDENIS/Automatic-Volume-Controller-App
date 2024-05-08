package com.google.android.gms.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzmq;

public class zzmp implements Result {
    private final zza zzaHa;

    public static class zza {
        private final Status zzKr;
        private final C0019zza zzaHb;
        private final byte[] zzaHc;
        private final long zzaHd;
        private final zzmj zzaHe;
        private final zzmq.zzc zzaHf;

        /* renamed from: com.google.android.gms.internal.zzmp$zza$zza  reason: collision with other inner class name */
        public enum C0019zza {
            NETWORK,
            DISK,
            DEFAULT
        }

        public zza(Status status, zzmj zzmj, C0019zza zza) {
            this(status, zzmj, (byte[]) null, (zzmq.zzc) null, zza, 0);
        }

        public zza(Status status, zzmj zzmj, byte[] bArr, zzmq.zzc zzc, C0019zza zza, long j) {
            this.zzKr = status;
            this.zzaHe = zzmj;
            this.zzaHc = bArr;
            this.zzaHf = zzc;
            this.zzaHb = zza;
            this.zzaHd = j;
        }

        public Status getStatus() {
            return this.zzKr;
        }

        public C0019zza zzyn() {
            return this.zzaHb;
        }

        public byte[] zzyo() {
            return this.zzaHc;
        }

        public zzmj zzyp() {
            return this.zzaHe;
        }

        public zzmq.zzc zzyq() {
            return this.zzaHf;
        }

        public long zzyr() {
            return this.zzaHd;
        }
    }

    public zzmp(zza zza2) {
        this.zzaHa = zza2;
    }

    public Status getStatus() {
        return this.zzaHa.getStatus();
    }

    public zza zzym() {
        return this.zzaHa;
    }
}
