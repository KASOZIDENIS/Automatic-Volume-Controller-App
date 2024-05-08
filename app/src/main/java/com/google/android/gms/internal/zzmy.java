package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzbg;

public abstract class zzmy {
    private zzmo zzaHH;
    private zzmm zzaHI;
    private zzht zznR;

    public enum zza {
        NOT_AVAILABLE,
        IO_ERROR,
        SERVER_ERROR
    }

    public class zzb {
        private final Object zzaHN;
        private final zzmp.zza.C0019zza zzaHb;
        private final long zzaHd;

        public Object zzyN() {
            return this.zzaHN;
        }

        public zzmp.zza.C0019zza zzyn() {
            return this.zzaHb;
        }

        public long zzyr() {
            return this.zzaHd;
        }
    }

    public zzmy(zzmo zzmo, zzmm zzmm) {
        this(zzmo, zzmm, zzhv.zznd());
    }

    public zzmy(zzmo zzmo, zzmm zzmm, zzht zzht) {
        zzv.zzQ(zzmo.zzyl().size() != 1 ? false : true);
        this.zzaHH = zzmo;
        this.zzaHI = zzmm;
        this.zznR = zzht;
    }

    /* access modifiers changed from: protected */
    public abstract zzb zza(zzmj zzmj);

    /* access modifiers changed from: protected */
    public abstract void zza(zzmp zzmp);

    public void zza(zza zza2) {
        zzbg.zzak("ResourceManager: Failed to download a resource: " + zza2.name());
        zzmj zzmj = this.zzaHH.zzyl().get(0);
        zzb zza3 = zza(zzmj);
        zza(new zzmp((zza3 == null || !(zza3.zzyN() instanceof zzmq.zzc)) ? new zzmp.zza(Status.zzQW, zzmj, zzmp.zza.C0019zza.NETWORK) : new zzmp.zza(Status.zzQU, zzmj, (byte[]) null, (zzmq.zzc) zza3.zzyN(), zza3.zzyn(), zza3.zzyr())));
    }

    public void zzq(byte[] bArr) {
        long j;
        zzmp.zza.C0019zza zza2;
        zzmq.zzc zzc;
        zzmp.zza zza3;
        zzbg.zzam("ResourceManager: Resource downloaded from Network: " + this.zzaHH.getId());
        zzmj zzmj = this.zzaHH.zzyl().get(0);
        zzmp.zza.C0019zza zza4 = zzmp.zza.C0019zza.NETWORK;
        try {
            Object zzp = this.zzaHI.zzp(bArr);
            long currentTimeMillis = this.zznR.currentTimeMillis();
            if (zzp == null) {
                zzbg.zzal("Parsed resource from network is null");
                zzb zza5 = zza(zzmj);
                if (zza5 != null) {
                    zzp = zza5.zzyN();
                    zza4 = zza5.zzyn();
                    currentTimeMillis = zza5.zzyr();
                }
            }
            j = currentTimeMillis;
            zza2 = zza4;
            zzc = zzp;
        } catch (zzmq.zzg e) {
            zzbg.zzal("Resource from network is corrupted");
            zzb zza6 = zza(zzmj);
            if (zza6 != null) {
                Object zzyN = zza6.zzyN();
                j = 0;
                zza2 = zza6.zzyn();
                zzc = zzyN;
            } else {
                j = 0;
                zza2 = zza4;
                zzc = null;
            }
        }
        if (zzc != null) {
            zza3 = new zzmp.zza(Status.zzQU, zzmj, bArr, zzc, zza2, j);
        } else {
            zza3 = new zzmp.zza(Status.zzQW, zzmj, zzmp.zza.C0019zza.NETWORK);
        }
        zza(new zzmp(zza3));
    }
}
