package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzmy;
import com.google.android.gms.tagmanager.zzbg;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class zzml {
    private final Context mContext;
    private String zzaCO;
    /* access modifiers changed from: private */
    public final zzms zzaGQ;
    Map<String, zzc<zzmq.zzc>> zzaGR;
    private final Map<String, zzna> zzaGS;
    private final zzht zznR;

    public interface zza {
        void zza(zzmp zzmp);
    }

    class zzb extends zzmy {
        private final zza zzaGW;

        zzb(zzmo zzmo, zzmm zzmm, zza zza) {
            super(zzmo, zzmm);
            this.zzaGW = zza;
        }

        /* access modifiers changed from: protected */
        public zzmy.zzb zza(zzmj zzmj) {
            return null;
        }

        /* access modifiers changed from: protected */
        public void zza(zzmp zzmp) {
            zzmp.zza zzym = zzmp.zzym();
            zzml.this.zza(zzym);
            if (zzym.getStatus() != Status.zzQU || zzym.zzyn() != zzmp.zza.C0019zza.NETWORK || zzym.zzyo() == null || zzym.zzyo().length <= 0) {
                zzbg.zzam("Response status: " + (zzym.getStatus().isSuccess() ? "SUCCESS" : "FAILURE"));
                if (zzym.getStatus().isSuccess()) {
                    zzbg.zzam("Response source: " + zzym.zzyn().toString());
                    zzbg.zzam("Response size: " + zzym.zzyo().length);
                }
                zzml.this.zza(zzym.zzyp(), this.zzaGW);
                return;
            }
            zzml.this.zzaGQ.zze(zzym.zzyp().zzyh(), zzym.zzyo());
            zzbg.zzam("Resource successfully load from Network.");
            this.zzaGW.zza(zzmp);
        }
    }

    static class zzc<T> {
        private T mData;
        private Status zzKr;
        private long zzaGX;

        public zzc(Status status, T t, long j) {
            this.zzKr = status;
            this.mData = t;
            this.zzaGX = j;
        }

        public void zzK(T t) {
            this.mData = t;
        }

        public void zzT(long j) {
            this.zzaGX = j;
        }

        public void zzaP(Status status) {
            this.zzKr = status;
        }

        public long zzyk() {
            return this.zzaGX;
        }
    }

    public zzml(Context context) {
        this(context, new HashMap(), new zzms(context), zzhv.zznd());
    }

    zzml(Context context, Map<String, zzna> map, zzms zzms, zzht zzht) {
        this.zzaCO = null;
        this.zzaGR = new HashMap();
        this.mContext = context;
        this.zznR = zzht;
        this.zzaGQ = zzms;
        this.zzaGS = map;
    }

    private void zza(zzmo zzmo, zza zza2) {
        boolean z = true;
        List<zzmj> zzyl = zzmo.zzyl();
        if (zzyl.size() != 1) {
            z = false;
        }
        zzv.zzQ(z);
        zza(zzyl.get(0), zza2);
    }

    /* access modifiers changed from: package-private */
    public void zza(final zzmj zzmj, final zza zza2) {
        this.zzaGQ.zza(zzmj.zzyh(), zzmj.zzyf(), zzmn.zzaGY, new zzmr() {
            public void zza(Status status, Object obj, Integer num, long j) {
                zzmp.zza zza;
                if (status.isSuccess()) {
                    zza = new zzmp.zza(Status.zzQU, zzmj, (byte[]) null, (zzmq.zzc) obj, num == zzms.zzaHy ? zzmp.zza.C0019zza.DEFAULT : zzmp.zza.C0019zza.DISK, j);
                } else {
                    zza = new zzmp.zza(new Status(16, "There is no valid resource for the container: " + zzmj.getContainerId()), (zzmj) null, zzmp.zza.C0019zza.DISK);
                }
                zza2.zza(new zzmp(zza));
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void zza(zzmo zzmo, zza zza2, zzmy zzmy) {
        boolean z;
        zzna zzna;
        boolean z2 = false;
        Iterator<zzmj> it = zzmo.zzyl().iterator();
        while (true) {
            z = z2;
            if (!it.hasNext()) {
                break;
            }
            zzmj next = it.next();
            zzc zzc2 = this.zzaGR.get(next.getContainerId());
            z2 = (zzc2 != null ? zzc2.zzyk() : this.zzaGQ.zzes(next.getContainerId())) + 900000 < this.zznR.currentTimeMillis() ? true : z;
        }
        if (z) {
            zzna zzna2 = this.zzaGS.get(zzmo.getId());
            if (zzna2 == null) {
                zzna zzna3 = this.zzaCO == null ? new zzna() : new zzna(this.zzaCO);
                this.zzaGS.put(zzmo.getId(), zzna3);
                zzna = zzna3;
            } else {
                zzna = zzna2;
            }
            zzna.zza(this.mContext, zzmo, 0, zzmy);
            return;
        }
        zza(zzmo, zza2);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzmp.zza zza2) {
        String containerId = zza2.zzyp().getContainerId();
        Status status = zza2.getStatus();
        zzmq.zzc zzyq = zza2.zzyq();
        if (this.zzaGR.containsKey(containerId)) {
            zzc zzc2 = this.zzaGR.get(containerId);
            zzc2.zzT(this.zznR.currentTimeMillis());
            if (status == Status.zzQU) {
                zzc2.zzaP(status);
                zzc2.zzK(zzyq);
                return;
            }
            return;
        }
        this.zzaGR.put(containerId, new zzc(status, zzyq, this.zznR.currentTimeMillis()));
    }

    public void zza(String str, Integer num, String str2, zza zza2) {
        zzmo zzb2 = new zzmo().zzb(new zzmj(str, num, str2, false));
        zza(zzb2, zza2, new zzb(zzb2, zzmn.zzaGY, zza2));
    }

    public void zzem(String str) {
        this.zzaCO = str;
    }
}
