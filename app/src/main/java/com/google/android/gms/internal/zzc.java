package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import com.google.android.gms.internal.zzd;
import java.io.IOException;

public interface zzc {

    public static final class zza extends zzns<zza> {
        public int level;
        public int zzgc;
        public int zzgd;

        public zza() {
            zzb();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            if (this.level == zza.level && this.zzgc == zza.zzgc && this.zzgd == zza.zzgd) {
                return zza(zza);
            }
            return false;
        }

        public int hashCode() {
            return ((((((this.level + 527) * 31) + this.zzgc) * 31) + this.zzgd) * 31) + zzzP();
        }

        /* renamed from: zza */
        public zza zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        int zzzB = zznq.zzzB();
                        switch (zzzB) {
                            case 1:
                            case 2:
                            case 3:
                                this.level = zzzB;
                                break;
                            default:
                                continue;
                        }
                    case 16:
                        this.zzgc = zznq.zzzB();
                        continue;
                    case 24:
                        this.zzgd = zznq.zzzB();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public void zza(zznr zznr) throws IOException {
            if (this.level != 1) {
                zznr.zzx(1, this.level);
            }
            if (this.zzgc != 0) {
                zznr.zzx(2, this.zzgc);
            }
            if (this.zzgd != 0) {
                zznr.zzx(3, this.zzgd);
            }
            super.zza(zznr);
        }

        public zza zzb() {
            this.level = 1;
            this.zzgc = 0;
            this.zzgd = 0;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (this.level != 1) {
                zzc += zznr.zzz(1, this.level);
            }
            if (this.zzgc != 0) {
                zzc += zznr.zzz(2, this.zzgc);
            }
            return this.zzgd != 0 ? zzc + zznr.zzz(3, this.zzgd) : zzc;
        }
    }

    public static final class zzb extends zzns<zzb> {
        private static volatile zzb[] zzge;
        public int name;
        public int[] zzgf;
        public int zzgg;
        public boolean zzgh;
        public boolean zzgi;

        public zzb() {
            zze();
        }

        public static zzb[] zzd() {
            if (zzge == null) {
                synchronized (zznw.zzaNS) {
                    if (zzge == null) {
                        zzge = new zzb[0];
                    }
                }
            }
            return zzge;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) o;
            if (zznw.equals(this.zzgf, zzb.zzgf) && this.zzgg == zzb.zzgg && this.name == zzb.name && this.zzgh == zzb.zzgh && this.zzgi == zzb.zzgi) {
                return zza(zzb);
            }
            return false;
        }

        public int hashCode() {
            int i = 1231;
            int hashCode = ((this.zzgh ? 1231 : 1237) + ((((((zznw.hashCode(this.zzgf) + 527) * 31) + this.zzgg) * 31) + this.name) * 31)) * 31;
            if (!this.zzgi) {
                i = 1237;
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzgi) {
                zznr.zzb(1, this.zzgi);
            }
            zznr.zzx(2, this.zzgg);
            if (this.zzgf != null && this.zzgf.length > 0) {
                for (int zzx : this.zzgf) {
                    zznr.zzx(3, zzx);
                }
            }
            if (this.name != 0) {
                zznr.zzx(4, this.name);
            }
            if (this.zzgh) {
                zznr.zzb(6, this.zzgh);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int i;
            int i2 = 0;
            int zzc = super.zzc();
            if (this.zzgi) {
                zzc += zznr.zzc(1, this.zzgi);
            }
            int zzz = zznr.zzz(2, this.zzgg) + zzc;
            if (this.zzgf == null || this.zzgf.length <= 0) {
                i = zzz;
            } else {
                for (int zzju : this.zzgf) {
                    i2 += zznr.zzju(zzju);
                }
                i = zzz + i2 + (this.zzgf.length * 1);
            }
            if (this.name != 0) {
                i += zznr.zzz(4, this.name);
            }
            return this.zzgh ? i + zznr.zzc(6, this.zzgh) : i;
        }

        /* renamed from: zzc */
        public zzb zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        this.zzgi = zznq.zzzC();
                        continue;
                    case 16:
                        this.zzgg = zznq.zzzB();
                        continue;
                    case 24:
                        int zzb = zzob.zzb(zznq, 24);
                        int length = this.zzgf == null ? 0 : this.zzgf.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzgf, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zznq.zzzB();
                            zznq.zzzy();
                            length++;
                        }
                        iArr[length] = zznq.zzzB();
                        this.zzgf = iArr;
                        continue;
                    case 26:
                        int zzjn = zznq.zzjn(zznq.zzzF());
                        int position = zznq.getPosition();
                        int i = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i++;
                        }
                        zznq.zzjp(position);
                        int length2 = this.zzgf == null ? 0 : this.zzgf.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzgf, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zznq.zzzB();
                            length2++;
                        }
                        this.zzgf = iArr2;
                        zznq.zzjo(zzjn);
                        continue;
                    case 32:
                        this.name = zznq.zzzB();
                        continue;
                    case 48:
                        this.zzgh = zznq.zzzC();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzb zze() {
            this.zzgf = zzob.zzaNV;
            this.zzgg = 0;
            this.name = 0;
            this.zzgh = false;
            this.zzgi = false;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    /* renamed from: com.google.android.gms.internal.zzc$zzc  reason: collision with other inner class name */
    public static final class C0014zzc extends zzns<C0014zzc> {
        private static volatile C0014zzc[] zzgj;
        public String zzgk;
        public long zzgl;
        public long zzgm;
        public boolean zzgn;
        public long zzgo;

        public C0014zzc() {
            zzg();
        }

        public static C0014zzc[] zzf() {
            if (zzgj == null) {
                synchronized (zznw.zzaNS) {
                    if (zzgj == null) {
                        zzgj = new C0014zzc[0];
                    }
                }
            }
            return zzgj;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof C0014zzc)) {
                return false;
            }
            C0014zzc zzc = (C0014zzc) o;
            if (this.zzgk == null) {
                if (zzc.zzgk != null) {
                    return false;
                }
            } else if (!this.zzgk.equals(zzc.zzgk)) {
                return false;
            }
            if (this.zzgl == zzc.zzgl && this.zzgm == zzc.zzgm && this.zzgn == zzc.zzgn && this.zzgo == zzc.zzgo) {
                return zza(zzc);
            }
            return false;
        }

        public int hashCode() {
            return (((((this.zzgn ? 1231 : 1237) + (((((((this.zzgk == null ? 0 : this.zzgk.hashCode()) + 527) * 31) + ((int) (this.zzgl ^ (this.zzgl >>> 32)))) * 31) + ((int) (this.zzgm ^ (this.zzgm >>> 32)))) * 31)) * 31) + ((int) (this.zzgo ^ (this.zzgo >>> 32)))) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (!this.zzgk.equals("")) {
                zznr.zzb(1, this.zzgk);
            }
            if (this.zzgl != 0) {
                zznr.zzb(2, this.zzgl);
            }
            if (this.zzgm != 2147483647L) {
                zznr.zzb(3, this.zzgm);
            }
            if (this.zzgn) {
                zznr.zzb(4, this.zzgn);
            }
            if (this.zzgo != 0) {
                zznr.zzb(5, this.zzgo);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (!this.zzgk.equals("")) {
                zzc += zznr.zzj(1, this.zzgk);
            }
            if (this.zzgl != 0) {
                zzc += zznr.zzd(2, this.zzgl);
            }
            if (this.zzgm != 2147483647L) {
                zzc += zznr.zzd(3, this.zzgm);
            }
            if (this.zzgn) {
                zzc += zznr.zzc(4, this.zzgn);
            }
            return this.zzgo != 0 ? zzc + zznr.zzd(5, this.zzgo) : zzc;
        }

        /* renamed from: zzd */
        public C0014zzc zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        this.zzgk = zznq.readString();
                        continue;
                    case 16:
                        this.zzgl = zznq.zzzA();
                        continue;
                    case 24:
                        this.zzgm = zznq.zzzA();
                        continue;
                    case 32:
                        this.zzgn = zznq.zzzC();
                        continue;
                    case 40:
                        this.zzgo = zznq.zzzA();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public C0014zzc zzg() {
            this.zzgk = "";
            this.zzgl = 0;
            this.zzgm = 2147483647L;
            this.zzgn = false;
            this.zzgo = 0;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzd extends zzns<zzd> {
        public zzd.zza[] zzgp;
        public zzd.zza[] zzgq;
        public C0014zzc[] zzgr;

        public zzd() {
            zzh();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) o;
            if (!zznw.equals((Object[]) this.zzgp, (Object[]) zzd.zzgp) || !zznw.equals((Object[]) this.zzgq, (Object[]) zzd.zzgq) || !zznw.equals((Object[]) this.zzgr, (Object[]) zzd.zzgr)) {
                return false;
            }
            return zza(zzd);
        }

        public int hashCode() {
            return ((((((zznw.hashCode((Object[]) this.zzgp) + 527) * 31) + zznw.hashCode((Object[]) this.zzgq)) * 31) + zznw.hashCode((Object[]) this.zzgr)) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzgp != null && this.zzgp.length > 0) {
                for (zzd.zza zza : this.zzgp) {
                    if (zza != null) {
                        zznr.zza(1, (zzny) zza);
                    }
                }
            }
            if (this.zzgq != null && this.zzgq.length > 0) {
                for (zzd.zza zza2 : this.zzgq) {
                    if (zza2 != null) {
                        zznr.zza(2, (zzny) zza2);
                    }
                }
            }
            if (this.zzgr != null && this.zzgr.length > 0) {
                for (C0014zzc zzc : this.zzgr) {
                    if (zzc != null) {
                        zznr.zza(3, (zzny) zzc);
                    }
                }
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (this.zzgp != null && this.zzgp.length > 0) {
                int i = zzc;
                for (zzd.zza zza : this.zzgp) {
                    if (zza != null) {
                        i += zznr.zzc(1, (zzny) zza);
                    }
                }
                zzc = i;
            }
            if (this.zzgq != null && this.zzgq.length > 0) {
                int i2 = zzc;
                for (zzd.zza zza2 : this.zzgq) {
                    if (zza2 != null) {
                        i2 += zznr.zzc(2, (zzny) zza2);
                    }
                }
                zzc = i2;
            }
            if (this.zzgr != null && this.zzgr.length > 0) {
                for (C0014zzc zzc2 : this.zzgr) {
                    if (zzc2 != null) {
                        zzc += zznr.zzc(3, (zzny) zzc2);
                    }
                }
            }
            return zzc;
        }

        /* renamed from: zze */
        public zzd zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzob.zzb(zznq, 10);
                        int length = this.zzgp == null ? 0 : this.zzgp.length;
                        zzd.zza[] zzaArr = new zzd.zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzgp, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new zzd.zza();
                            zznq.zza(zzaArr[length]);
                            zznq.zzzy();
                            length++;
                        }
                        zzaArr[length] = new zzd.zza();
                        zznq.zza(zzaArr[length]);
                        this.zzgp = zzaArr;
                        continue;
                    case 18:
                        int zzb2 = zzob.zzb(zznq, 18);
                        int length2 = this.zzgq == null ? 0 : this.zzgq.length;
                        zzd.zza[] zzaArr2 = new zzd.zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzgq, 0, zzaArr2, 0, length2);
                        }
                        while (length2 < zzaArr2.length - 1) {
                            zzaArr2[length2] = new zzd.zza();
                            zznq.zza(zzaArr2[length2]);
                            zznq.zzzy();
                            length2++;
                        }
                        zzaArr2[length2] = new zzd.zza();
                        zznq.zza(zzaArr2[length2]);
                        this.zzgq = zzaArr2;
                        continue;
                    case 26:
                        int zzb3 = zzob.zzb(zznq, 26);
                        int length3 = this.zzgr == null ? 0 : this.zzgr.length;
                        C0014zzc[] zzcArr = new C0014zzc[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzgr, 0, zzcArr, 0, length3);
                        }
                        while (length3 < zzcArr.length - 1) {
                            zzcArr[length3] = new C0014zzc();
                            zznq.zza(zzcArr[length3]);
                            zznq.zzzy();
                            length3++;
                        }
                        zzcArr[length3] = new C0014zzc();
                        zznq.zza(zzcArr[length3]);
                        this.zzgr = zzcArr;
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzd zzh() {
            this.zzgp = zzd.zza.zzr();
            this.zzgq = zzd.zza.zzr();
            this.zzgr = C0014zzc.zzf();
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zze extends zzns<zze> {
        private static volatile zze[] zzgs;
        public int key;
        public int value;

        public zze() {
            zzj();
        }

        public static zze[] zzi() {
            if (zzgs == null) {
                synchronized (zznw.zzaNS) {
                    if (zzgs == null) {
                        zzgs = new zze[0];
                    }
                }
            }
            return zzgs;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zze)) {
                return false;
            }
            zze zze = (zze) o;
            if (this.key == zze.key && this.value == zze.value) {
                return zza(zze);
            }
            return false;
        }

        public int hashCode() {
            return ((((this.key + 527) * 31) + this.value) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            zznr.zzx(1, this.key);
            zznr.zzx(2, this.value);
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            return super.zzc() + zznr.zzz(1, this.key) + zznr.zzz(2, this.value);
        }

        /* renamed from: zzf */
        public zze zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        this.key = zznq.zzzB();
                        continue;
                    case 16:
                        this.value = zznq.zzzB();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zze zzj() {
            this.key = 0;
            this.value = 0;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzf extends zzns<zzf> {
        public String version;
        public zzg[] zzgA;
        public String zzgB;
        public String zzgC;
        public String zzgD;
        public zza zzgE;
        public float zzgF;
        public boolean zzgG;
        public String[] zzgH;
        public int zzgI;
        public String[] zzgt;
        public String[] zzgu;
        public zzd.zza[] zzgv;
        public zze[] zzgw;
        public zzb[] zzgx;
        public zzb[] zzgy;
        public zzb[] zzgz;

        public zzf() {
            zzk();
        }

        public static zzf zza(byte[] bArr) throws zznx {
            return (zzf) zzny.zza(new zzf(), bArr);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzf)) {
                return false;
            }
            zzf zzf = (zzf) o;
            if (!zznw.equals((Object[]) this.zzgt, (Object[]) zzf.zzgt) || !zznw.equals((Object[]) this.zzgu, (Object[]) zzf.zzgu) || !zznw.equals((Object[]) this.zzgv, (Object[]) zzf.zzgv) || !zznw.equals((Object[]) this.zzgw, (Object[]) zzf.zzgw) || !zznw.equals((Object[]) this.zzgx, (Object[]) zzf.zzgx) || !zznw.equals((Object[]) this.zzgy, (Object[]) zzf.zzgy) || !zznw.equals((Object[]) this.zzgz, (Object[]) zzf.zzgz) || !zznw.equals((Object[]) this.zzgA, (Object[]) zzf.zzgA)) {
                return false;
            }
            if (this.zzgB == null) {
                if (zzf.zzgB != null) {
                    return false;
                }
            } else if (!this.zzgB.equals(zzf.zzgB)) {
                return false;
            }
            if (this.zzgC == null) {
                if (zzf.zzgC != null) {
                    return false;
                }
            } else if (!this.zzgC.equals(zzf.zzgC)) {
                return false;
            }
            if (this.zzgD == null) {
                if (zzf.zzgD != null) {
                    return false;
                }
            } else if (!this.zzgD.equals(zzf.zzgD)) {
                return false;
            }
            if (this.version == null) {
                if (zzf.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzf.version)) {
                return false;
            }
            if (this.zzgE == null) {
                if (zzf.zzgE != null) {
                    return false;
                }
            } else if (!this.zzgE.equals(zzf.zzgE)) {
                return false;
            }
            if (Float.floatToIntBits(this.zzgF) == Float.floatToIntBits(zzf.zzgF) && this.zzgG == zzf.zzgG && zznw.equals((Object[]) this.zzgH, (Object[]) zzf.zzgH) && this.zzgI == zzf.zzgI) {
                return zza(zzf);
            }
            return false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzgD == null ? 0 : this.zzgD.hashCode()) + (((this.zzgC == null ? 0 : this.zzgC.hashCode()) + (((this.zzgB == null ? 0 : this.zzgB.hashCode()) + ((((((((((((((((zznw.hashCode((Object[]) this.zzgt) + 527) * 31) + zznw.hashCode((Object[]) this.zzgu)) * 31) + zznw.hashCode((Object[]) this.zzgv)) * 31) + zznw.hashCode((Object[]) this.zzgw)) * 31) + zznw.hashCode((Object[]) this.zzgx)) * 31) + zznw.hashCode((Object[]) this.zzgy)) * 31) + zznw.hashCode((Object[]) this.zzgz)) * 31) + zznw.hashCode((Object[]) this.zzgA)) * 31)) * 31)) * 31)) * 31)) * 31;
            if (this.zzgE != null) {
                i = this.zzgE.hashCode();
            }
            return (((((((this.zzgG ? 1231 : 1237) + ((((hashCode + i) * 31) + Float.floatToIntBits(this.zzgF)) * 31)) * 31) + zznw.hashCode((Object[]) this.zzgH)) * 31) + this.zzgI) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzgu != null && this.zzgu.length > 0) {
                for (String str : this.zzgu) {
                    if (str != null) {
                        zznr.zzb(1, str);
                    }
                }
            }
            if (this.zzgv != null && this.zzgv.length > 0) {
                for (zzd.zza zza : this.zzgv) {
                    if (zza != null) {
                        zznr.zza(2, (zzny) zza);
                    }
                }
            }
            if (this.zzgw != null && this.zzgw.length > 0) {
                for (zze zze : this.zzgw) {
                    if (zze != null) {
                        zznr.zza(3, (zzny) zze);
                    }
                }
            }
            if (this.zzgx != null && this.zzgx.length > 0) {
                for (zzb zzb : this.zzgx) {
                    if (zzb != null) {
                        zznr.zza(4, (zzny) zzb);
                    }
                }
            }
            if (this.zzgy != null && this.zzgy.length > 0) {
                for (zzb zzb2 : this.zzgy) {
                    if (zzb2 != null) {
                        zznr.zza(5, (zzny) zzb2);
                    }
                }
            }
            if (this.zzgz != null && this.zzgz.length > 0) {
                for (zzb zzb3 : this.zzgz) {
                    if (zzb3 != null) {
                        zznr.zza(6, (zzny) zzb3);
                    }
                }
            }
            if (this.zzgA != null && this.zzgA.length > 0) {
                for (zzg zzg : this.zzgA) {
                    if (zzg != null) {
                        zznr.zza(7, (zzny) zzg);
                    }
                }
            }
            if (!this.zzgB.equals("")) {
                zznr.zzb(9, this.zzgB);
            }
            if (!this.zzgC.equals("")) {
                zznr.zzb(10, this.zzgC);
            }
            if (!this.zzgD.equals("0")) {
                zznr.zzb(12, this.zzgD);
            }
            if (!this.version.equals("")) {
                zznr.zzb(13, this.version);
            }
            if (this.zzgE != null) {
                zznr.zza(14, (zzny) this.zzgE);
            }
            if (Float.floatToIntBits(this.zzgF) != Float.floatToIntBits(0.0f)) {
                zznr.zzb(15, this.zzgF);
            }
            if (this.zzgH != null && this.zzgH.length > 0) {
                for (String str2 : this.zzgH) {
                    if (str2 != null) {
                        zznr.zzb(16, str2);
                    }
                }
            }
            if (this.zzgI != 0) {
                zznr.zzx(17, this.zzgI);
            }
            if (this.zzgG) {
                zznr.zzb(18, this.zzgG);
            }
            if (this.zzgt != null && this.zzgt.length > 0) {
                for (String str3 : this.zzgt) {
                    if (str3 != null) {
                        zznr.zzb(19, str3);
                    }
                }
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int i;
            int zzc = super.zzc();
            if (this.zzgu == null || this.zzgu.length <= 0) {
                i = zzc;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.zzgu) {
                    if (str != null) {
                        i3++;
                        i2 += zznr.zzeB(str);
                    }
                }
                i = zzc + i2 + (i3 * 1);
            }
            if (this.zzgv != null && this.zzgv.length > 0) {
                int i4 = i;
                for (zzd.zza zza : this.zzgv) {
                    if (zza != null) {
                        i4 += zznr.zzc(2, (zzny) zza);
                    }
                }
                i = i4;
            }
            if (this.zzgw != null && this.zzgw.length > 0) {
                int i5 = i;
                for (zze zze : this.zzgw) {
                    if (zze != null) {
                        i5 += zznr.zzc(3, (zzny) zze);
                    }
                }
                i = i5;
            }
            if (this.zzgx != null && this.zzgx.length > 0) {
                int i6 = i;
                for (zzb zzb : this.zzgx) {
                    if (zzb != null) {
                        i6 += zznr.zzc(4, (zzny) zzb);
                    }
                }
                i = i6;
            }
            if (this.zzgy != null && this.zzgy.length > 0) {
                int i7 = i;
                for (zzb zzb2 : this.zzgy) {
                    if (zzb2 != null) {
                        i7 += zznr.zzc(5, (zzny) zzb2);
                    }
                }
                i = i7;
            }
            if (this.zzgz != null && this.zzgz.length > 0) {
                int i8 = i;
                for (zzb zzb3 : this.zzgz) {
                    if (zzb3 != null) {
                        i8 += zznr.zzc(6, (zzny) zzb3);
                    }
                }
                i = i8;
            }
            if (this.zzgA != null && this.zzgA.length > 0) {
                int i9 = i;
                for (zzg zzg : this.zzgA) {
                    if (zzg != null) {
                        i9 += zznr.zzc(7, (zzny) zzg);
                    }
                }
                i = i9;
            }
            if (!this.zzgB.equals("")) {
                i += zznr.zzj(9, this.zzgB);
            }
            if (!this.zzgC.equals("")) {
                i += zznr.zzj(10, this.zzgC);
            }
            if (!this.zzgD.equals("0")) {
                i += zznr.zzj(12, this.zzgD);
            }
            if (!this.version.equals("")) {
                i += zznr.zzj(13, this.version);
            }
            if (this.zzgE != null) {
                i += zznr.zzc(14, (zzny) this.zzgE);
            }
            if (Float.floatToIntBits(this.zzgF) != Float.floatToIntBits(0.0f)) {
                i += zznr.zzc(15, this.zzgF);
            }
            if (this.zzgH != null && this.zzgH.length > 0) {
                int i10 = 0;
                int i11 = 0;
                for (String str2 : this.zzgH) {
                    if (str2 != null) {
                        i11++;
                        i10 += zznr.zzeB(str2);
                    }
                }
                i = i + i10 + (i11 * 2);
            }
            if (this.zzgI != 0) {
                i += zznr.zzz(17, this.zzgI);
            }
            if (this.zzgG) {
                i += zznr.zzc(18, this.zzgG);
            }
            if (this.zzgt == null || this.zzgt.length <= 0) {
                return i;
            }
            int i12 = 0;
            int i13 = 0;
            for (String str3 : this.zzgt) {
                if (str3 != null) {
                    i13++;
                    i12 += zznr.zzeB(str3);
                }
            }
            return i + i12 + (i13 * 2);
        }

        /* renamed from: zzg */
        public zzf zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzob.zzb(zznq, 10);
                        int length = this.zzgu == null ? 0 : this.zzgu.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzgu, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zznq.readString();
                            zznq.zzzy();
                            length++;
                        }
                        strArr[length] = zznq.readString();
                        this.zzgu = strArr;
                        continue;
                    case 18:
                        int zzb2 = zzob.zzb(zznq, 18);
                        int length2 = this.zzgv == null ? 0 : this.zzgv.length;
                        zzd.zza[] zzaArr = new zzd.zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzgv, 0, zzaArr, 0, length2);
                        }
                        while (length2 < zzaArr.length - 1) {
                            zzaArr[length2] = new zzd.zza();
                            zznq.zza(zzaArr[length2]);
                            zznq.zzzy();
                            length2++;
                        }
                        zzaArr[length2] = new zzd.zza();
                        zznq.zza(zzaArr[length2]);
                        this.zzgv = zzaArr;
                        continue;
                    case 26:
                        int zzb3 = zzob.zzb(zznq, 26);
                        int length3 = this.zzgw == null ? 0 : this.zzgw.length;
                        zze[] zzeArr = new zze[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzgw, 0, zzeArr, 0, length3);
                        }
                        while (length3 < zzeArr.length - 1) {
                            zzeArr[length3] = new zze();
                            zznq.zza(zzeArr[length3]);
                            zznq.zzzy();
                            length3++;
                        }
                        zzeArr[length3] = new zze();
                        zznq.zza(zzeArr[length3]);
                        this.zzgw = zzeArr;
                        continue;
                    case 34:
                        int zzb4 = zzob.zzb(zznq, 34);
                        int length4 = this.zzgx == null ? 0 : this.zzgx.length;
                        zzb[] zzbArr = new zzb[(zzb4 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzgx, 0, zzbArr, 0, length4);
                        }
                        while (length4 < zzbArr.length - 1) {
                            zzbArr[length4] = new zzb();
                            zznq.zza(zzbArr[length4]);
                            zznq.zzzy();
                            length4++;
                        }
                        zzbArr[length4] = new zzb();
                        zznq.zza(zzbArr[length4]);
                        this.zzgx = zzbArr;
                        continue;
                    case 42:
                        int zzb5 = zzob.zzb(zznq, 42);
                        int length5 = this.zzgy == null ? 0 : this.zzgy.length;
                        zzb[] zzbArr2 = new zzb[(zzb5 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzgy, 0, zzbArr2, 0, length5);
                        }
                        while (length5 < zzbArr2.length - 1) {
                            zzbArr2[length5] = new zzb();
                            zznq.zza(zzbArr2[length5]);
                            zznq.zzzy();
                            length5++;
                        }
                        zzbArr2[length5] = new zzb();
                        zznq.zza(zzbArr2[length5]);
                        this.zzgy = zzbArr2;
                        continue;
                    case 50:
                        int zzb6 = zzob.zzb(zznq, 50);
                        int length6 = this.zzgz == null ? 0 : this.zzgz.length;
                        zzb[] zzbArr3 = new zzb[(zzb6 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzgz, 0, zzbArr3, 0, length6);
                        }
                        while (length6 < zzbArr3.length - 1) {
                            zzbArr3[length6] = new zzb();
                            zznq.zza(zzbArr3[length6]);
                            zznq.zzzy();
                            length6++;
                        }
                        zzbArr3[length6] = new zzb();
                        zznq.zza(zzbArr3[length6]);
                        this.zzgz = zzbArr3;
                        continue;
                    case 58:
                        int zzb7 = zzob.zzb(zznq, 58);
                        int length7 = this.zzgA == null ? 0 : this.zzgA.length;
                        zzg[] zzgArr = new zzg[(zzb7 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzgA, 0, zzgArr, 0, length7);
                        }
                        while (length7 < zzgArr.length - 1) {
                            zzgArr[length7] = new zzg();
                            zznq.zza(zzgArr[length7]);
                            zznq.zzzy();
                            length7++;
                        }
                        zzgArr[length7] = new zzg();
                        zznq.zza(zzgArr[length7]);
                        this.zzgA = zzgArr;
                        continue;
                    case 74:
                        this.zzgB = zznq.readString();
                        continue;
                    case 82:
                        this.zzgC = zznq.readString();
                        continue;
                    case 98:
                        this.zzgD = zznq.readString();
                        continue;
                    case 106:
                        this.version = zznq.readString();
                        continue;
                    case 114:
                        if (this.zzgE == null) {
                            this.zzgE = new zza();
                        }
                        zznq.zza(this.zzgE);
                        continue;
                    case 125:
                        this.zzgF = zznq.readFloat();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        int zzb8 = zzob.zzb(zznq, TransportMediator.KEYCODE_MEDIA_RECORD);
                        int length8 = this.zzgH == null ? 0 : this.zzgH.length;
                        String[] strArr2 = new String[(zzb8 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzgH, 0, strArr2, 0, length8);
                        }
                        while (length8 < strArr2.length - 1) {
                            strArr2[length8] = zznq.readString();
                            zznq.zzzy();
                            length8++;
                        }
                        strArr2[length8] = zznq.readString();
                        this.zzgH = strArr2;
                        continue;
                    case 136:
                        this.zzgI = zznq.zzzB();
                        continue;
                    case 144:
                        this.zzgG = zznq.zzzC();
                        continue;
                    case 154:
                        int zzb9 = zzob.zzb(zznq, 154);
                        int length9 = this.zzgt == null ? 0 : this.zzgt.length;
                        String[] strArr3 = new String[(zzb9 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.zzgt, 0, strArr3, 0, length9);
                        }
                        while (length9 < strArr3.length - 1) {
                            strArr3[length9] = zznq.readString();
                            zznq.zzzy();
                            length9++;
                        }
                        strArr3[length9] = zznq.readString();
                        this.zzgt = strArr3;
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzf zzk() {
            this.zzgt = zzob.zzaOa;
            this.zzgu = zzob.zzaOa;
            this.zzgv = zzd.zza.zzr();
            this.zzgw = zze.zzi();
            this.zzgx = zzb.zzd();
            this.zzgy = zzb.zzd();
            this.zzgz = zzb.zzd();
            this.zzgA = zzg.zzl();
            this.zzgB = "";
            this.zzgC = "";
            this.zzgD = "0";
            this.version = "";
            this.zzgE = null;
            this.zzgF = 0.0f;
            this.zzgG = false;
            this.zzgH = zzob.zzaOa;
            this.zzgI = 0;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzg extends zzns<zzg> {
        private static volatile zzg[] zzgJ;
        public int[] zzgK;
        public int[] zzgL;
        public int[] zzgM;
        public int[] zzgN;
        public int[] zzgO;
        public int[] zzgP;
        public int[] zzgQ;
        public int[] zzgR;
        public int[] zzgS;
        public int[] zzgT;

        public zzg() {
            zzm();
        }

        public static zzg[] zzl() {
            if (zzgJ == null) {
                synchronized (zznw.zzaNS) {
                    if (zzgJ == null) {
                        zzgJ = new zzg[0];
                    }
                }
            }
            return zzgJ;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzg)) {
                return false;
            }
            zzg zzg = (zzg) o;
            if (!zznw.equals(this.zzgK, zzg.zzgK) || !zznw.equals(this.zzgL, zzg.zzgL) || !zznw.equals(this.zzgM, zzg.zzgM) || !zznw.equals(this.zzgN, zzg.zzgN) || !zznw.equals(this.zzgO, zzg.zzgO) || !zznw.equals(this.zzgP, zzg.zzgP) || !zznw.equals(this.zzgQ, zzg.zzgQ) || !zznw.equals(this.zzgR, zzg.zzgR) || !zznw.equals(this.zzgS, zzg.zzgS) || !zznw.equals(this.zzgT, zzg.zzgT)) {
                return false;
            }
            return zza(zzg);
        }

        public int hashCode() {
            return ((((((((((((((((((((zznw.hashCode(this.zzgK) + 527) * 31) + zznw.hashCode(this.zzgL)) * 31) + zznw.hashCode(this.zzgM)) * 31) + zznw.hashCode(this.zzgN)) * 31) + zznw.hashCode(this.zzgO)) * 31) + zznw.hashCode(this.zzgP)) * 31) + zznw.hashCode(this.zzgQ)) * 31) + zznw.hashCode(this.zzgR)) * 31) + zznw.hashCode(this.zzgS)) * 31) + zznw.hashCode(this.zzgT)) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzgK != null && this.zzgK.length > 0) {
                for (int zzx : this.zzgK) {
                    zznr.zzx(1, zzx);
                }
            }
            if (this.zzgL != null && this.zzgL.length > 0) {
                for (int zzx2 : this.zzgL) {
                    zznr.zzx(2, zzx2);
                }
            }
            if (this.zzgM != null && this.zzgM.length > 0) {
                for (int zzx3 : this.zzgM) {
                    zznr.zzx(3, zzx3);
                }
            }
            if (this.zzgN != null && this.zzgN.length > 0) {
                for (int zzx4 : this.zzgN) {
                    zznr.zzx(4, zzx4);
                }
            }
            if (this.zzgO != null && this.zzgO.length > 0) {
                for (int zzx5 : this.zzgO) {
                    zznr.zzx(5, zzx5);
                }
            }
            if (this.zzgP != null && this.zzgP.length > 0) {
                for (int zzx6 : this.zzgP) {
                    zznr.zzx(6, zzx6);
                }
            }
            if (this.zzgQ != null && this.zzgQ.length > 0) {
                for (int zzx7 : this.zzgQ) {
                    zznr.zzx(7, zzx7);
                }
            }
            if (this.zzgR != null && this.zzgR.length > 0) {
                for (int zzx8 : this.zzgR) {
                    zznr.zzx(8, zzx8);
                }
            }
            if (this.zzgS != null && this.zzgS.length > 0) {
                for (int zzx9 : this.zzgS) {
                    zznr.zzx(9, zzx9);
                }
            }
            if (this.zzgT != null && this.zzgT.length > 0) {
                for (int zzx10 : this.zzgT) {
                    zznr.zzx(10, zzx10);
                }
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int i;
            int zzc = super.zzc();
            if (this.zzgK == null || this.zzgK.length <= 0) {
                i = zzc;
            } else {
                int i2 = 0;
                for (int zzju : this.zzgK) {
                    i2 += zznr.zzju(zzju);
                }
                i = zzc + i2 + (this.zzgK.length * 1);
            }
            if (this.zzgL != null && this.zzgL.length > 0) {
                int i3 = 0;
                for (int zzju2 : this.zzgL) {
                    i3 += zznr.zzju(zzju2);
                }
                i = i + i3 + (this.zzgL.length * 1);
            }
            if (this.zzgM != null && this.zzgM.length > 0) {
                int i4 = 0;
                for (int zzju3 : this.zzgM) {
                    i4 += zznr.zzju(zzju3);
                }
                i = i + i4 + (this.zzgM.length * 1);
            }
            if (this.zzgN != null && this.zzgN.length > 0) {
                int i5 = 0;
                for (int zzju4 : this.zzgN) {
                    i5 += zznr.zzju(zzju4);
                }
                i = i + i5 + (this.zzgN.length * 1);
            }
            if (this.zzgO != null && this.zzgO.length > 0) {
                int i6 = 0;
                for (int zzju5 : this.zzgO) {
                    i6 += zznr.zzju(zzju5);
                }
                i = i + i6 + (this.zzgO.length * 1);
            }
            if (this.zzgP != null && this.zzgP.length > 0) {
                int i7 = 0;
                for (int zzju6 : this.zzgP) {
                    i7 += zznr.zzju(zzju6);
                }
                i = i + i7 + (this.zzgP.length * 1);
            }
            if (this.zzgQ != null && this.zzgQ.length > 0) {
                int i8 = 0;
                for (int zzju7 : this.zzgQ) {
                    i8 += zznr.zzju(zzju7);
                }
                i = i + i8 + (this.zzgQ.length * 1);
            }
            if (this.zzgR != null && this.zzgR.length > 0) {
                int i9 = 0;
                for (int zzju8 : this.zzgR) {
                    i9 += zznr.zzju(zzju8);
                }
                i = i + i9 + (this.zzgR.length * 1);
            }
            if (this.zzgS != null && this.zzgS.length > 0) {
                int i10 = 0;
                for (int zzju9 : this.zzgS) {
                    i10 += zznr.zzju(zzju9);
                }
                i = i + i10 + (this.zzgS.length * 1);
            }
            if (this.zzgT == null || this.zzgT.length <= 0) {
                return i;
            }
            int i11 = 0;
            for (int zzju10 : this.zzgT) {
                i11 += zznr.zzju(zzju10);
            }
            return i + i11 + (this.zzgT.length * 1);
        }

        /* renamed from: zzh */
        public zzg zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        int zzb = zzob.zzb(zznq, 8);
                        int length = this.zzgK == null ? 0 : this.zzgK.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzgK, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zznq.zzzB();
                            zznq.zzzy();
                            length++;
                        }
                        iArr[length] = zznq.zzzB();
                        this.zzgK = iArr;
                        continue;
                    case 10:
                        int zzjn = zznq.zzjn(zznq.zzzF());
                        int position = zznq.getPosition();
                        int i = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i++;
                        }
                        zznq.zzjp(position);
                        int length2 = this.zzgK == null ? 0 : this.zzgK.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzgK, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zznq.zzzB();
                            length2++;
                        }
                        this.zzgK = iArr2;
                        zznq.zzjo(zzjn);
                        continue;
                    case 16:
                        int zzb2 = zzob.zzb(zznq, 16);
                        int length3 = this.zzgL == null ? 0 : this.zzgL.length;
                        int[] iArr3 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzgL, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zznq.zzzB();
                            zznq.zzzy();
                            length3++;
                        }
                        iArr3[length3] = zznq.zzzB();
                        this.zzgL = iArr3;
                        continue;
                    case 18:
                        int zzjn2 = zznq.zzjn(zznq.zzzF());
                        int position2 = zznq.getPosition();
                        int i2 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i2++;
                        }
                        zznq.zzjp(position2);
                        int length4 = this.zzgL == null ? 0 : this.zzgL.length;
                        int[] iArr4 = new int[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzgL, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = zznq.zzzB();
                            length4++;
                        }
                        this.zzgL = iArr4;
                        zznq.zzjo(zzjn2);
                        continue;
                    case 24:
                        int zzb3 = zzob.zzb(zznq, 24);
                        int length5 = this.zzgM == null ? 0 : this.zzgM.length;
                        int[] iArr5 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzgM, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = zznq.zzzB();
                            zznq.zzzy();
                            length5++;
                        }
                        iArr5[length5] = zznq.zzzB();
                        this.zzgM = iArr5;
                        continue;
                    case 26:
                        int zzjn3 = zznq.zzjn(zznq.zzzF());
                        int position3 = zznq.getPosition();
                        int i3 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i3++;
                        }
                        zznq.zzjp(position3);
                        int length6 = this.zzgM == null ? 0 : this.zzgM.length;
                        int[] iArr6 = new int[(i3 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzgM, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = zznq.zzzB();
                            length6++;
                        }
                        this.zzgM = iArr6;
                        zznq.zzjo(zzjn3);
                        continue;
                    case 32:
                        int zzb4 = zzob.zzb(zznq, 32);
                        int length7 = this.zzgN == null ? 0 : this.zzgN.length;
                        int[] iArr7 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzgN, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = zznq.zzzB();
                            zznq.zzzy();
                            length7++;
                        }
                        iArr7[length7] = zznq.zzzB();
                        this.zzgN = iArr7;
                        continue;
                    case 34:
                        int zzjn4 = zznq.zzjn(zznq.zzzF());
                        int position4 = zznq.getPosition();
                        int i4 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i4++;
                        }
                        zznq.zzjp(position4);
                        int length8 = this.zzgN == null ? 0 : this.zzgN.length;
                        int[] iArr8 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzgN, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zznq.zzzB();
                            length8++;
                        }
                        this.zzgN = iArr8;
                        zznq.zzjo(zzjn4);
                        continue;
                    case 40:
                        int zzb5 = zzob.zzb(zznq, 40);
                        int length9 = this.zzgO == null ? 0 : this.zzgO.length;
                        int[] iArr9 = new int[(zzb5 + length9)];
                        if (length9 != 0) {
                            System.arraycopy(this.zzgO, 0, iArr9, 0, length9);
                        }
                        while (length9 < iArr9.length - 1) {
                            iArr9[length9] = zznq.zzzB();
                            zznq.zzzy();
                            length9++;
                        }
                        iArr9[length9] = zznq.zzzB();
                        this.zzgO = iArr9;
                        continue;
                    case 42:
                        int zzjn5 = zznq.zzjn(zznq.zzzF());
                        int position5 = zznq.getPosition();
                        int i5 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i5++;
                        }
                        zznq.zzjp(position5);
                        int length10 = this.zzgO == null ? 0 : this.zzgO.length;
                        int[] iArr10 = new int[(i5 + length10)];
                        if (length10 != 0) {
                            System.arraycopy(this.zzgO, 0, iArr10, 0, length10);
                        }
                        while (length10 < iArr10.length) {
                            iArr10[length10] = zznq.zzzB();
                            length10++;
                        }
                        this.zzgO = iArr10;
                        zznq.zzjo(zzjn5);
                        continue;
                    case 48:
                        int zzb6 = zzob.zzb(zznq, 48);
                        int length11 = this.zzgP == null ? 0 : this.zzgP.length;
                        int[] iArr11 = new int[(zzb6 + length11)];
                        if (length11 != 0) {
                            System.arraycopy(this.zzgP, 0, iArr11, 0, length11);
                        }
                        while (length11 < iArr11.length - 1) {
                            iArr11[length11] = zznq.zzzB();
                            zznq.zzzy();
                            length11++;
                        }
                        iArr11[length11] = zznq.zzzB();
                        this.zzgP = iArr11;
                        continue;
                    case 50:
                        int zzjn6 = zznq.zzjn(zznq.zzzF());
                        int position6 = zznq.getPosition();
                        int i6 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i6++;
                        }
                        zznq.zzjp(position6);
                        int length12 = this.zzgP == null ? 0 : this.zzgP.length;
                        int[] iArr12 = new int[(i6 + length12)];
                        if (length12 != 0) {
                            System.arraycopy(this.zzgP, 0, iArr12, 0, length12);
                        }
                        while (length12 < iArr12.length) {
                            iArr12[length12] = zznq.zzzB();
                            length12++;
                        }
                        this.zzgP = iArr12;
                        zznq.zzjo(zzjn6);
                        continue;
                    case 56:
                        int zzb7 = zzob.zzb(zznq, 56);
                        int length13 = this.zzgQ == null ? 0 : this.zzgQ.length;
                        int[] iArr13 = new int[(zzb7 + length13)];
                        if (length13 != 0) {
                            System.arraycopy(this.zzgQ, 0, iArr13, 0, length13);
                        }
                        while (length13 < iArr13.length - 1) {
                            iArr13[length13] = zznq.zzzB();
                            zznq.zzzy();
                            length13++;
                        }
                        iArr13[length13] = zznq.zzzB();
                        this.zzgQ = iArr13;
                        continue;
                    case 58:
                        int zzjn7 = zznq.zzjn(zznq.zzzF());
                        int position7 = zznq.getPosition();
                        int i7 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i7++;
                        }
                        zznq.zzjp(position7);
                        int length14 = this.zzgQ == null ? 0 : this.zzgQ.length;
                        int[] iArr14 = new int[(i7 + length14)];
                        if (length14 != 0) {
                            System.arraycopy(this.zzgQ, 0, iArr14, 0, length14);
                        }
                        while (length14 < iArr14.length) {
                            iArr14[length14] = zznq.zzzB();
                            length14++;
                        }
                        this.zzgQ = iArr14;
                        zznq.zzjo(zzjn7);
                        continue;
                    case 64:
                        int zzb8 = zzob.zzb(zznq, 64);
                        int length15 = this.zzgR == null ? 0 : this.zzgR.length;
                        int[] iArr15 = new int[(zzb8 + length15)];
                        if (length15 != 0) {
                            System.arraycopy(this.zzgR, 0, iArr15, 0, length15);
                        }
                        while (length15 < iArr15.length - 1) {
                            iArr15[length15] = zznq.zzzB();
                            zznq.zzzy();
                            length15++;
                        }
                        iArr15[length15] = zznq.zzzB();
                        this.zzgR = iArr15;
                        continue;
                    case 66:
                        int zzjn8 = zznq.zzjn(zznq.zzzF());
                        int position8 = zznq.getPosition();
                        int i8 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i8++;
                        }
                        zznq.zzjp(position8);
                        int length16 = this.zzgR == null ? 0 : this.zzgR.length;
                        int[] iArr16 = new int[(i8 + length16)];
                        if (length16 != 0) {
                            System.arraycopy(this.zzgR, 0, iArr16, 0, length16);
                        }
                        while (length16 < iArr16.length) {
                            iArr16[length16] = zznq.zzzB();
                            length16++;
                        }
                        this.zzgR = iArr16;
                        zznq.zzjo(zzjn8);
                        continue;
                    case 72:
                        int zzb9 = zzob.zzb(zznq, 72);
                        int length17 = this.zzgS == null ? 0 : this.zzgS.length;
                        int[] iArr17 = new int[(zzb9 + length17)];
                        if (length17 != 0) {
                            System.arraycopy(this.zzgS, 0, iArr17, 0, length17);
                        }
                        while (length17 < iArr17.length - 1) {
                            iArr17[length17] = zznq.zzzB();
                            zznq.zzzy();
                            length17++;
                        }
                        iArr17[length17] = zznq.zzzB();
                        this.zzgS = iArr17;
                        continue;
                    case 74:
                        int zzjn9 = zznq.zzjn(zznq.zzzF());
                        int position9 = zznq.getPosition();
                        int i9 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i9++;
                        }
                        zznq.zzjp(position9);
                        int length18 = this.zzgS == null ? 0 : this.zzgS.length;
                        int[] iArr18 = new int[(i9 + length18)];
                        if (length18 != 0) {
                            System.arraycopy(this.zzgS, 0, iArr18, 0, length18);
                        }
                        while (length18 < iArr18.length) {
                            iArr18[length18] = zznq.zzzB();
                            length18++;
                        }
                        this.zzgS = iArr18;
                        zznq.zzjo(zzjn9);
                        continue;
                    case 80:
                        int zzb10 = zzob.zzb(zznq, 80);
                        int length19 = this.zzgT == null ? 0 : this.zzgT.length;
                        int[] iArr19 = new int[(zzb10 + length19)];
                        if (length19 != 0) {
                            System.arraycopy(this.zzgT, 0, iArr19, 0, length19);
                        }
                        while (length19 < iArr19.length - 1) {
                            iArr19[length19] = zznq.zzzB();
                            zznq.zzzy();
                            length19++;
                        }
                        iArr19[length19] = zznq.zzzB();
                        this.zzgT = iArr19;
                        continue;
                    case 82:
                        int zzjn10 = zznq.zzjn(zznq.zzzF());
                        int position10 = zznq.getPosition();
                        int i10 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i10++;
                        }
                        zznq.zzjp(position10);
                        int length20 = this.zzgT == null ? 0 : this.zzgT.length;
                        int[] iArr20 = new int[(i10 + length20)];
                        if (length20 != 0) {
                            System.arraycopy(this.zzgT, 0, iArr20, 0, length20);
                        }
                        while (length20 < iArr20.length) {
                            iArr20[length20] = zznq.zzzB();
                            length20++;
                        }
                        this.zzgT = iArr20;
                        zznq.zzjo(zzjn10);
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzg zzm() {
            this.zzgK = zzob.zzaNV;
            this.zzgL = zzob.zzaNV;
            this.zzgM = zzob.zzaNV;
            this.zzgN = zzob.zzaNV;
            this.zzgO = zzob.zzaNV;
            this.zzgP = zzob.zzaNV;
            this.zzgQ = zzob.zzaNV;
            this.zzgR = zzob.zzaNV;
            this.zzgS = zzob.zzaNV;
            this.zzgT = zzob.zzaNV;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzh extends zzns<zzh> {
        public static final zznt<zzd.zza, zzh> zzgU = zznt.zza(11, zzh.class, 810);
        private static final zzh[] zzgV = new zzh[0];
        public int[] zzgW;
        public int[] zzgX;
        public int[] zzgY;
        public int zzgZ;
        public int[] zzha;
        public int zzhb;
        public int zzhc;

        public zzh() {
            zzn();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzh)) {
                return false;
            }
            zzh zzh = (zzh) o;
            if (!zznw.equals(this.zzgW, zzh.zzgW) || !zznw.equals(this.zzgX, zzh.zzgX) || !zznw.equals(this.zzgY, zzh.zzgY) || this.zzgZ != zzh.zzgZ || !zznw.equals(this.zzha, zzh.zzha) || this.zzhb != zzh.zzhb || this.zzhc != zzh.zzhc) {
                return false;
            }
            return zza(zzh);
        }

        public int hashCode() {
            return ((((((((((((((zznw.hashCode(this.zzgW) + 527) * 31) + zznw.hashCode(this.zzgX)) * 31) + zznw.hashCode(this.zzgY)) * 31) + this.zzgZ) * 31) + zznw.hashCode(this.zzha)) * 31) + this.zzhb) * 31) + this.zzhc) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzgW != null && this.zzgW.length > 0) {
                for (int zzx : this.zzgW) {
                    zznr.zzx(1, zzx);
                }
            }
            if (this.zzgX != null && this.zzgX.length > 0) {
                for (int zzx2 : this.zzgX) {
                    zznr.zzx(2, zzx2);
                }
            }
            if (this.zzgY != null && this.zzgY.length > 0) {
                for (int zzx3 : this.zzgY) {
                    zznr.zzx(3, zzx3);
                }
            }
            if (this.zzgZ != 0) {
                zznr.zzx(4, this.zzgZ);
            }
            if (this.zzha != null && this.zzha.length > 0) {
                for (int zzx4 : this.zzha) {
                    zznr.zzx(5, zzx4);
                }
            }
            if (this.zzhb != 0) {
                zznr.zzx(6, this.zzhb);
            }
            if (this.zzhc != 0) {
                zznr.zzx(7, this.zzhc);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int i;
            int zzc = super.zzc();
            if (this.zzgW == null || this.zzgW.length <= 0) {
                i = zzc;
            } else {
                int i2 = 0;
                for (int zzju : this.zzgW) {
                    i2 += zznr.zzju(zzju);
                }
                i = zzc + i2 + (this.zzgW.length * 1);
            }
            if (this.zzgX != null && this.zzgX.length > 0) {
                int i3 = 0;
                for (int zzju2 : this.zzgX) {
                    i3 += zznr.zzju(zzju2);
                }
                i = i + i3 + (this.zzgX.length * 1);
            }
            if (this.zzgY != null && this.zzgY.length > 0) {
                int i4 = 0;
                for (int zzju3 : this.zzgY) {
                    i4 += zznr.zzju(zzju3);
                }
                i = i + i4 + (this.zzgY.length * 1);
            }
            if (this.zzgZ != 0) {
                i += zznr.zzz(4, this.zzgZ);
            }
            if (this.zzha != null && this.zzha.length > 0) {
                int i5 = 0;
                for (int zzju4 : this.zzha) {
                    i5 += zznr.zzju(zzju4);
                }
                i = i + i5 + (this.zzha.length * 1);
            }
            if (this.zzhb != 0) {
                i += zznr.zzz(6, this.zzhb);
            }
            return this.zzhc != 0 ? i + zznr.zzz(7, this.zzhc) : i;
        }

        /* renamed from: zzi */
        public zzh zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        int zzb = zzob.zzb(zznq, 8);
                        int length = this.zzgW == null ? 0 : this.zzgW.length;
                        int[] iArr = new int[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzgW, 0, iArr, 0, length);
                        }
                        while (length < iArr.length - 1) {
                            iArr[length] = zznq.zzzB();
                            zznq.zzzy();
                            length++;
                        }
                        iArr[length] = zznq.zzzB();
                        this.zzgW = iArr;
                        continue;
                    case 10:
                        int zzjn = zznq.zzjn(zznq.zzzF());
                        int position = zznq.getPosition();
                        int i = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i++;
                        }
                        zznq.zzjp(position);
                        int length2 = this.zzgW == null ? 0 : this.zzgW.length;
                        int[] iArr2 = new int[(i + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzgW, 0, iArr2, 0, length2);
                        }
                        while (length2 < iArr2.length) {
                            iArr2[length2] = zznq.zzzB();
                            length2++;
                        }
                        this.zzgW = iArr2;
                        zznq.zzjo(zzjn);
                        continue;
                    case 16:
                        int zzb2 = zzob.zzb(zznq, 16);
                        int length3 = this.zzgX == null ? 0 : this.zzgX.length;
                        int[] iArr3 = new int[(zzb2 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzgX, 0, iArr3, 0, length3);
                        }
                        while (length3 < iArr3.length - 1) {
                            iArr3[length3] = zznq.zzzB();
                            zznq.zzzy();
                            length3++;
                        }
                        iArr3[length3] = zznq.zzzB();
                        this.zzgX = iArr3;
                        continue;
                    case 18:
                        int zzjn2 = zznq.zzjn(zznq.zzzF());
                        int position2 = zznq.getPosition();
                        int i2 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i2++;
                        }
                        zznq.zzjp(position2);
                        int length4 = this.zzgX == null ? 0 : this.zzgX.length;
                        int[] iArr4 = new int[(i2 + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzgX, 0, iArr4, 0, length4);
                        }
                        while (length4 < iArr4.length) {
                            iArr4[length4] = zznq.zzzB();
                            length4++;
                        }
                        this.zzgX = iArr4;
                        zznq.zzjo(zzjn2);
                        continue;
                    case 24:
                        int zzb3 = zzob.zzb(zznq, 24);
                        int length5 = this.zzgY == null ? 0 : this.zzgY.length;
                        int[] iArr5 = new int[(zzb3 + length5)];
                        if (length5 != 0) {
                            System.arraycopy(this.zzgY, 0, iArr5, 0, length5);
                        }
                        while (length5 < iArr5.length - 1) {
                            iArr5[length5] = zznq.zzzB();
                            zznq.zzzy();
                            length5++;
                        }
                        iArr5[length5] = zznq.zzzB();
                        this.zzgY = iArr5;
                        continue;
                    case 26:
                        int zzjn3 = zznq.zzjn(zznq.zzzF());
                        int position3 = zznq.getPosition();
                        int i3 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i3++;
                        }
                        zznq.zzjp(position3);
                        int length6 = this.zzgY == null ? 0 : this.zzgY.length;
                        int[] iArr6 = new int[(i3 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzgY, 0, iArr6, 0, length6);
                        }
                        while (length6 < iArr6.length) {
                            iArr6[length6] = zznq.zzzB();
                            length6++;
                        }
                        this.zzgY = iArr6;
                        zznq.zzjo(zzjn3);
                        continue;
                    case 32:
                        this.zzgZ = zznq.zzzB();
                        continue;
                    case 40:
                        int zzb4 = zzob.zzb(zznq, 40);
                        int length7 = this.zzha == null ? 0 : this.zzha.length;
                        int[] iArr7 = new int[(zzb4 + length7)];
                        if (length7 != 0) {
                            System.arraycopy(this.zzha, 0, iArr7, 0, length7);
                        }
                        while (length7 < iArr7.length - 1) {
                            iArr7[length7] = zznq.zzzB();
                            zznq.zzzy();
                            length7++;
                        }
                        iArr7[length7] = zznq.zzzB();
                        this.zzha = iArr7;
                        continue;
                    case 42:
                        int zzjn4 = zznq.zzjn(zznq.zzzF());
                        int position4 = zznq.getPosition();
                        int i4 = 0;
                        while (zznq.zzzK() > 0) {
                            zznq.zzzB();
                            i4++;
                        }
                        zznq.zzjp(position4);
                        int length8 = this.zzha == null ? 0 : this.zzha.length;
                        int[] iArr8 = new int[(i4 + length8)];
                        if (length8 != 0) {
                            System.arraycopy(this.zzha, 0, iArr8, 0, length8);
                        }
                        while (length8 < iArr8.length) {
                            iArr8[length8] = zznq.zzzB();
                            length8++;
                        }
                        this.zzha = iArr8;
                        zznq.zzjo(zzjn4);
                        continue;
                    case 48:
                        this.zzhb = zznq.zzzB();
                        continue;
                    case 56:
                        this.zzhc = zznq.zzzB();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzh zzn() {
            this.zzgW = zzob.zzaNV;
            this.zzgX = zzob.zzaNV;
            this.zzgY = zzob.zzaNV;
            this.zzgZ = 0;
            this.zzha = zzob.zzaNV;
            this.zzhb = 0;
            this.zzhc = 0;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzi extends zzns<zzi> {
        private static volatile zzi[] zzhd;
        public String name;
        public zzd.zza zzhe;
        public zzd zzhf;

        public zzi() {
            zzp();
        }

        public static zzi[] zzo() {
            if (zzhd == null) {
                synchronized (zznw.zzaNS) {
                    if (zzhd == null) {
                        zzhd = new zzi[0];
                    }
                }
            }
            return zzhd;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzi)) {
                return false;
            }
            zzi zzi = (zzi) o;
            if (this.name == null) {
                if (zzi.name != null) {
                    return false;
                }
            } else if (!this.name.equals(zzi.name)) {
                return false;
            }
            if (this.zzhe == null) {
                if (zzi.zzhe != null) {
                    return false;
                }
            } else if (!this.zzhe.equals(zzi.zzhe)) {
                return false;
            }
            if (this.zzhf == null) {
                if (zzi.zzhf != null) {
                    return false;
                }
            } else if (!this.zzhf.equals(zzi.zzhf)) {
                return false;
            }
            return zza(zzi);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzhe == null ? 0 : this.zzhe.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + 527) * 31)) * 31;
            if (this.zzhf != null) {
                i = this.zzhf.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (!this.name.equals("")) {
                zznr.zzb(1, this.name);
            }
            if (this.zzhe != null) {
                zznr.zza(2, (zzny) this.zzhe);
            }
            if (this.zzhf != null) {
                zznr.zza(3, (zzny) this.zzhf);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (!this.name.equals("")) {
                zzc += zznr.zzj(1, this.name);
            }
            if (this.zzhe != null) {
                zzc += zznr.zzc(2, (zzny) this.zzhe);
            }
            return this.zzhf != null ? zzc + zznr.zzc(3, (zzny) this.zzhf) : zzc;
        }

        /* renamed from: zzj */
        public zzi zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        this.name = zznq.readString();
                        continue;
                    case 18:
                        if (this.zzhe == null) {
                            this.zzhe = new zzd.zza();
                        }
                        zznq.zza(this.zzhe);
                        continue;
                    case 26:
                        if (this.zzhf == null) {
                            this.zzhf = new zzd();
                        }
                        zznq.zza(this.zzhf);
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzi zzp() {
            this.name = "";
            this.zzhe = null;
            this.zzhf = null;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }

    public static final class zzj extends zzns<zzj> {
        public zzi[] zzhg;
        public zzf zzhh;
        public String zzhi;

        public zzj() {
            zzq();
        }

        public static zzj zzb(byte[] bArr) throws zznx {
            return (zzj) zzny.zza(new zzj(), bArr);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzj)) {
                return false;
            }
            zzj zzj = (zzj) o;
            if (!zznw.equals((Object[]) this.zzhg, (Object[]) zzj.zzhg)) {
                return false;
            }
            if (this.zzhh == null) {
                if (zzj.zzhh != null) {
                    return false;
                }
            } else if (!this.zzhh.equals(zzj.zzhh)) {
                return false;
            }
            if (this.zzhi == null) {
                if (zzj.zzhi != null) {
                    return false;
                }
            } else if (!this.zzhi.equals(zzj.zzhi)) {
                return false;
            }
            return zza(zzj);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzhh == null ? 0 : this.zzhh.hashCode()) + ((zznw.hashCode((Object[]) this.zzhg) + 527) * 31)) * 31;
            if (this.zzhi != null) {
                i = this.zzhi.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            if (this.zzhg != null && this.zzhg.length > 0) {
                for (zzi zzi : this.zzhg) {
                    if (zzi != null) {
                        zznr.zza(1, (zzny) zzi);
                    }
                }
            }
            if (this.zzhh != null) {
                zznr.zza(2, (zzny) this.zzhh);
            }
            if (!this.zzhi.equals("")) {
                zznr.zzb(3, this.zzhi);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (this.zzhg != null && this.zzhg.length > 0) {
                for (zzi zzi : this.zzhg) {
                    if (zzi != null) {
                        zzc += zznr.zzc(1, (zzny) zzi);
                    }
                }
            }
            if (this.zzhh != null) {
                zzc += zznr.zzc(2, (zzny) this.zzhh);
            }
            return !this.zzhi.equals("") ? zzc + zznr.zzj(3, this.zzhi) : zzc;
        }

        /* renamed from: zzk */
        public zzj zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzob.zzb(zznq, 10);
                        int length = this.zzhg == null ? 0 : this.zzhg.length;
                        zzi[] zziArr = new zzi[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzhg, 0, zziArr, 0, length);
                        }
                        while (length < zziArr.length - 1) {
                            zziArr[length] = new zzi();
                            zznq.zza(zziArr[length]);
                            zznq.zzzy();
                            length++;
                        }
                        zziArr[length] = new zzi();
                        zznq.zza(zziArr[length]);
                        this.zzhg = zziArr;
                        continue;
                    case 18:
                        if (this.zzhh == null) {
                            this.zzhh = new zzf();
                        }
                        zznq.zza(this.zzhh);
                        continue;
                    case 26:
                        this.zzhi = zznq.readString();
                        continue;
                    default:
                        if (!zza(zznq, zzzy)) {
                            break;
                        } else {
                            continue;
                        }
                }
            }
            return this;
        }

        public zzj zzq() {
            this.zzhg = zzi.zzo();
            this.zzhh = null;
            this.zzhi = "";
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }
}
