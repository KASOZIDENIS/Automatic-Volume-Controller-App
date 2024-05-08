package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzkg {
    private final zzkj zzavX;
    private boolean zzavY;
    private long zzavZ;
    private long zzawa;
    private long zzawb;
    private long zzawc;
    private long zzawd;
    private boolean zzawe;
    private final Map<Class<? extends zzki>, zzki> zzawf;
    private final List<zzkm> zzawg;
    private final zzht zznR;

    zzkg(zzkg zzkg) {
        this.zzavX = zzkg.zzavX;
        this.zznR = zzkg.zznR;
        this.zzavZ = zzkg.zzavZ;
        this.zzawa = zzkg.zzawa;
        this.zzawb = zzkg.zzawb;
        this.zzawc = zzkg.zzawc;
        this.zzawd = zzkg.zzawd;
        this.zzawg = new ArrayList(zzkg.zzawg);
        this.zzawf = new HashMap(zzkg.zzawf.size());
        for (Map.Entry next : zzkg.zzawf.entrySet()) {
            zzki zzf = zzf((Class) next.getKey());
            ((zzki) next.getValue()).zza(zzf);
            this.zzawf.put(next.getKey(), zzf);
        }
    }

    zzkg(zzkj zzkj, zzht zzht) {
        zzv.zzr(zzkj);
        zzv.zzr(zzht);
        this.zzavX = zzkj;
        this.zznR = zzht;
        this.zzawc = 1800000;
        this.zzawd = 3024000000L;
        this.zzawf = new HashMap();
        this.zzawg = new ArrayList();
    }

    private static <T extends zzki> T zzf(Class<T> cls) {
        try {
            return (zzki) cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("dataType doesn't have default constructor", e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("dataType default constructor is not accessible", e2);
        }
    }

    public void zzK(long j) {
        this.zzawa = j;
    }

    public void zzb(zzki zzki) {
        zzv.zzr(zzki);
        Class cls = zzki.getClass();
        if (cls.getSuperclass() != zzki.class) {
            throw new IllegalArgumentException();
        }
        zzki.zza(zze(cls));
    }

    public <T extends zzki> T zzd(Class<T> cls) {
        return (zzki) this.zzawf.get(cls);
    }

    public <T extends zzki> T zze(Class<T> cls) {
        T t = (zzki) this.zzawf.get(cls);
        if (t != null) {
            return t;
        }
        T zzf = zzf(cls);
        this.zzawf.put(cls, zzf);
        return zzf;
    }

    public zzkg zztZ() {
        return new zzkg(this);
    }

    public Collection<zzki> zzua() {
        return this.zzawf.values();
    }

    public List<zzkm> zzub() {
        return this.zzawg;
    }

    public long zzuc() {
        return this.zzavZ;
    }

    public void zzud() {
        zzuh().zze(this);
    }

    public boolean zzue() {
        return this.zzavY;
    }

    /* access modifiers changed from: package-private */
    public void zzuf() {
        this.zzawb = this.zznR.elapsedRealtime();
        if (this.zzawa != 0) {
            this.zzavZ = this.zzawa;
        } else {
            this.zzavZ = this.zznR.currentTimeMillis();
        }
        this.zzavY = true;
    }

    /* access modifiers changed from: package-private */
    public zzkj zzug() {
        return this.zzavX;
    }

    /* access modifiers changed from: package-private */
    public zzkk zzuh() {
        return this.zzavX.zzuh();
    }

    /* access modifiers changed from: package-private */
    public boolean zzui() {
        return this.zzawe;
    }

    /* access modifiers changed from: package-private */
    public void zzuj() {
        this.zzawe = true;
    }
}
