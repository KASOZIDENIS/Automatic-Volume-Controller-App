package com.google.android.gms.tagmanager;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.AbstractPendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.internal.zzmi;
import com.google.android.gms.internal.zzml;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzbf;
import com.google.android.gms.tagmanager.zzcb;
import com.google.android.gms.tagmanager.zzo;

public class zzp extends AbstractPendingResult<ContainerHolder> {
    /* access modifiers changed from: private */
    public final Context mContext;
    /* access modifiers changed from: private */
    public final Looper zzPx;
    /* access modifiers changed from: private */
    public final zzcd zzaCA;
    private final int zzaCB;
    private zzf zzaCC;
    private zzml zzaCD;
    /* access modifiers changed from: private */
    public volatile zzo zzaCE;
    /* access modifiers changed from: private */
    public volatile boolean zzaCF;
    /* access modifiers changed from: private */
    public zzc.zzj zzaCG;
    private String zzaCH;
    private zze zzaCI;
    private zza zzaCJ;
    /* access modifiers changed from: private */
    public final String zzaCk;
    /* access modifiers changed from: private */
    public long zzaCp;
    /* access modifiers changed from: private */
    public final TagManager zzaCw;
    private final zzd zzaCz;
    /* access modifiers changed from: private */
    public final zzht zznR;

    interface zza {
        boolean zzb(Container container);
    }

    private class zzb implements zzbf<zzmi.zza> {
        private zzb() {
        }

        /* renamed from: zza */
        public void zzv(zzmi.zza zza) {
            zzc.zzj zzj;
            if (zza.zzaGM != null) {
                zzj = zza.zzaGM;
            } else {
                zzc.zzf zzf = zza.zzhh;
                zzj = new zzc.zzj();
                zzj.zzhh = zzf;
                zzj.zzhg = null;
                zzj.zzhi = zzf.version;
            }
            zzp.this.zza(zzj, zza.zzaGL, true);
        }

        public void zza(zzbf.zza zza) {
            if (!zzp.this.zzaCF) {
                zzp.this.zzQ(0);
            }
        }

        public void zzwB() {
        }
    }

    private class zzc implements zzbf<zzc.zzj> {
        private zzc() {
        }

        public void zza(zzbf.zza zza) {
            synchronized (zzp.this) {
                if (!zzp.this.isReady()) {
                    if (zzp.this.zzaCE != null) {
                        zzp.this.setResult(zzp.this.zzaCE);
                    } else {
                        zzp.this.setResult(zzp.this.createFailedResult(Status.zzQX));
                    }
                }
            }
            zzp.this.zzQ(3600000);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* renamed from: zzb */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void zzv(com.google.android.gms.internal.zzc.zzj r6) {
            /*
                r5 = this;
                com.google.android.gms.tagmanager.zzp r1 = com.google.android.gms.tagmanager.zzp.this
                monitor-enter(r1)
                com.google.android.gms.internal.zzc$zzf r0 = r6.zzhh     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x002a
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.zzc$zzj r0 = r0.zzaCG     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.zzc$zzf r0 = r0.zzhh     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x0020
                java.lang.String r0 = "Current resource is null; network resource is also null"
                com.google.android.gms.tagmanager.zzbg.zzak(r0)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                r2 = 3600000(0x36ee80, double:1.7786363E-317)
                r0.zzQ(r2)     // Catch:{ all -> 0x0065 }
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
            L_0x001f:
                return
            L_0x0020:
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.zzc$zzj r0 = r0.zzaCG     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.zzc$zzf r0 = r0.zzhh     // Catch:{ all -> 0x0065 }
                r6.zzhh = r0     // Catch:{ all -> 0x0065 }
            L_0x002a:
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.zzp r2 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                com.google.android.gms.internal.zzht r2 = r2.zznR     // Catch:{ all -> 0x0065 }
                long r2 = r2.currentTimeMillis()     // Catch:{ all -> 0x0065 }
                r4 = 0
                r0.zza(r6, r2, r4)     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0065 }
                r0.<init>()     // Catch:{ all -> 0x0065 }
                java.lang.String r2 = "setting refresh time to current time: "
                java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.zzp r2 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                long r2 = r2.zzaCp     // Catch:{ all -> 0x0065 }
                java.lang.StringBuilder r0 = r0.append(r2)     // Catch:{ all -> 0x0065 }
                java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.zzbg.zzam(r0)     // Catch:{ all -> 0x0065 }
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                boolean r0 = r0.zzwA()     // Catch:{ all -> 0x0065 }
                if (r0 != 0) goto L_0x0063
                com.google.android.gms.tagmanager.zzp r0 = com.google.android.gms.tagmanager.zzp.this     // Catch:{ all -> 0x0065 }
                r0.zza((com.google.android.gms.internal.zzc.zzj) r6)     // Catch:{ all -> 0x0065 }
            L_0x0063:
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                goto L_0x001f
            L_0x0065:
                r0 = move-exception
                monitor-exit(r1)     // Catch:{ all -> 0x0065 }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zzc.zzv(com.google.android.gms.internal.zzc$zzj):void");
        }

        public void zzwB() {
        }
    }

    private class zzd implements zzo.zza {
        private zzd() {
        }

        public void zzdB(String str) {
            zzp.this.zzdB(str);
        }

        public String zzwu() {
            return zzp.this.zzwu();
        }

        public void zzww() {
            if (zzp.this.zzaCA.zziU()) {
                zzp.this.zzQ(0);
            }
        }
    }

    interface zze extends Releasable {
        void zza(zzbf<zzc.zzj> zzbf);

        void zzdE(String str);

        void zzf(long j, String str);
    }

    interface zzf extends Releasable {
        void zza(zzbf<zzmi.zza> zzbf);

        void zzb(zzmi.zza zza);

        zzmq.zzc zzhC(int i);

        void zzwC();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzf zzf2, zze zze2, zzml zzml, zzht zzht, zzcd zzcd) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.zzaCw = tagManager;
        this.zzPx = looper == null ? Looper.getMainLooper() : looper;
        this.zzaCk = str;
        this.zzaCB = i;
        this.zzaCC = zzf2;
        this.zzaCI = zze2;
        this.zzaCD = zzml;
        this.zzaCz = new zzd();
        this.zzaCG = new zzc.zzj();
        this.zznR = zzht;
        this.zzaCA = zzcd;
        if (zzwA()) {
            zzdB(zzcb.zzxl().zzxn());
        }
    }

    public zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzs zzs) {
        this(context, tagManager, looper, str, i, new zzcn(context, str), new zzcm(context, str, zzs), new zzml(context), zzhv.zznd(), new zzbe(30, 900000, 5000, "refreshing", zzhv.zznd()));
        this.zzaCD.zzem(zzs.zzwD());
    }

    /* access modifiers changed from: private */
    public synchronized void zzQ(long j) {
        if (this.zzaCI == null) {
            zzbg.zzan("Refresh requested, but no network load scheduler.");
        } else {
            this.zzaCI.zzf(j, this.zzaCG.zzhi);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void zza(zzc.zzj zzj) {
        if (this.zzaCC != null) {
            zzmi.zza zza2 = new zzmi.zza();
            zza2.zzaGL = this.zzaCp;
            zza2.zzhh = new zzc.zzf();
            zza2.zzaGM = zzj;
            this.zzaCC.zzb(zza2);
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0008, code lost:
        if (r8.zzaCF != false) goto L_0x000a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void zza(com.google.android.gms.internal.zzc.zzj r9, long r10, boolean r12) {
        /*
            r8 = this;
            r6 = 43200000(0x2932e00, double:2.1343636E-316)
            monitor-enter(r8)
            if (r12 == 0) goto L_0x000c
            boolean r0 = r8.zzaCF     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x000c
        L_0x000a:
            monitor-exit(r8)
            return
        L_0x000c:
            boolean r0 = r8.isReady()     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.tagmanager.zzo r0 = r8.zzaCE     // Catch:{ all -> 0x006a }
            if (r0 != 0) goto L_0x0016
        L_0x0016:
            r8.zzaCG = r9     // Catch:{ all -> 0x006a }
            r8.zzaCp = r10     // Catch:{ all -> 0x006a }
            r0 = 0
            r2 = 43200000(0x2932e00, double:2.1343636E-316)
            long r4 = r8.zzaCp     // Catch:{ all -> 0x006a }
            long r4 = r4 + r6
            com.google.android.gms.internal.zzht r6 = r8.zznR     // Catch:{ all -> 0x006a }
            long r6 = r6.currentTimeMillis()     // Catch:{ all -> 0x006a }
            long r4 = r4 - r6
            long r2 = java.lang.Math.min(r2, r4)     // Catch:{ all -> 0x006a }
            long r0 = java.lang.Math.max(r0, r2)     // Catch:{ all -> 0x006a }
            r8.zzQ(r0)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.Container r0 = new com.google.android.gms.tagmanager.Container     // Catch:{ all -> 0x006a }
            android.content.Context r1 = r8.mContext     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.TagManager r2 = r8.zzaCw     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.DataLayer r2 = r2.getDataLayer()     // Catch:{ all -> 0x006a }
            java.lang.String r3 = r8.zzaCk     // Catch:{ all -> 0x006a }
            r4 = r10
            r6 = r9
            r0.<init>((android.content.Context) r1, (com.google.android.gms.tagmanager.DataLayer) r2, (java.lang.String) r3, (long) r4, (com.google.android.gms.internal.zzc.zzj) r6)     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.zzo r1 = r8.zzaCE     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x006d
            com.google.android.gms.tagmanager.zzo r1 = new com.google.android.gms.tagmanager.zzo     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.TagManager r2 = r8.zzaCw     // Catch:{ all -> 0x006a }
            android.os.Looper r3 = r8.zzPx     // Catch:{ all -> 0x006a }
            com.google.android.gms.tagmanager.zzp$zzd r4 = r8.zzaCz     // Catch:{ all -> 0x006a }
            r1.<init>(r2, r3, r0, r4)     // Catch:{ all -> 0x006a }
            r8.zzaCE = r1     // Catch:{ all -> 0x006a }
        L_0x0056:
            boolean r1 = r8.isReady()     // Catch:{ all -> 0x006a }
            if (r1 != 0) goto L_0x000a
            com.google.android.gms.tagmanager.zzp$zza r1 = r8.zzaCJ     // Catch:{ all -> 0x006a }
            boolean r0 = r1.zzb(r0)     // Catch:{ all -> 0x006a }
            if (r0 == 0) goto L_0x000a
            com.google.android.gms.tagmanager.zzo r0 = r8.zzaCE     // Catch:{ all -> 0x006a }
            r8.setResult(r0)     // Catch:{ all -> 0x006a }
            goto L_0x000a
        L_0x006a:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        L_0x006d:
            com.google.android.gms.tagmanager.zzo r1 = r8.zzaCE     // Catch:{ all -> 0x006a }
            r1.zza(r0)     // Catch:{ all -> 0x006a }
            goto L_0x0056
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zza(com.google.android.gms.internal.zzc$zzj, long, boolean):void");
    }

    private void zzah(final boolean z) {
        this.zzaCC.zza(new zzb());
        this.zzaCI.zza(new zzc());
        zzmq.zzc zzhC = this.zzaCC.zzhC(this.zzaCB);
        if (zzhC != null) {
            this.zzaCE = new zzo(this.zzaCw, this.zzPx, new Container(this.mContext, this.zzaCw.getDataLayer(), this.zzaCk, 0, zzhC), this.zzaCz);
        }
        this.zzaCJ = new zza() {
            public boolean zzb(Container container) {
                return z ? container.getLastRefreshTime() + 43200000 >= zzp.this.zznR.currentTimeMillis() : !container.isDefault();
            }
        };
        if (zzwA()) {
            this.zzaCI.zzf(0, "");
        } else {
            this.zzaCC.zzwC();
        }
    }

    /* access modifiers changed from: private */
    public boolean zzwA() {
        zzcb zzxl = zzcb.zzxl();
        return (zzxl.zzxm() == zzcb.zza.CONTAINER || zzxl.zzxm() == zzcb.zza.CONTAINER_DEBUG) && this.zzaCk.equals(zzxl.getContainerId());
    }

    public void load(final String resourceIdParameterName) {
        this.zzaCD.zza(this.zzaCk, this.zzaCB != -1 ? Integer.valueOf(this.zzaCB) : null, resourceIdParameterName, new zzml.zza() {
            public void zza(zzmp zzmp) {
                if (zzmp.getStatus() != Status.zzQU) {
                    zzbg.zzak("Load request failed for the container " + zzp.this.zzaCk);
                    zzp.this.setResult(zzp.this.createFailedResult(Status.zzQW));
                    return;
                }
                zzmq.zzc zzyq = zzmp.zzym().zzyq();
                if (zzyq == null) {
                    zzbg.zzak("Response doesn't have the requested container");
                    zzp.this.setResult(zzp.this.createFailedResult(new Status(8, "Response doesn't have the requested container", (PendingIntent) null)));
                    return;
                }
                zzo unused = zzp.this.zzaCE = new zzo(zzp.this.zzaCw, zzp.this.zzPx, new Container(zzp.this.mContext, zzp.this.zzaCw.getDataLayer(), zzp.this.zzaCk, zzmp.zzym().zzyr(), zzyq), new zzo.zza() {
                    public void zzdB(String str) {
                        zzp.this.zzdB(str);
                    }

                    public String zzwu() {
                        return zzp.this.zzwu();
                    }

                    public void zzww() {
                        if (zzp.this.zzaCA.zziU()) {
                            zzp.this.load(resourceIdParameterName);
                        }
                    }
                });
                zzp.this.setResult(zzp.this.zzaCE);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: zzaO */
    public ContainerHolder createFailedResult(Status status) {
        if (this.zzaCE != null) {
            return this.zzaCE;
        }
        if (status == Status.zzQX) {
            zzbg.zzak("timer expired: setting result to failure");
        }
        return new zzo(status);
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzdB(String str) {
        this.zzaCH = str;
        if (this.zzaCI != null) {
            this.zzaCI.zzdE(str);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String zzwu() {
        return this.zzaCH;
    }

    public void zzwx() {
        zzmq.zzc zzhC = this.zzaCC.zzhC(this.zzaCB);
        if (zzhC != null) {
            setResult(new zzo(this.zzaCw, this.zzPx, new Container(this.mContext, this.zzaCw.getDataLayer(), this.zzaCk, 0, zzhC), new zzo.zza() {
                public void zzdB(String str) {
                    zzp.this.zzdB(str);
                }

                public String zzwu() {
                    return zzp.this.zzwu();
                }

                public void zzww() {
                    zzbg.zzan("Refresh ignored: container loaded as default only.");
                }
            }));
        } else {
            zzbg.zzak("Default was requested, but no default container was found");
            setResult(createFailedResult(new Status(10, "Default was requested, but no default container was found", (PendingIntent) null)));
        }
        this.zzaCI = null;
        this.zzaCC = null;
    }

    public void zzwy() {
        zzah(false);
    }

    public void zzwz() {
        zzah(true);
    }
}
