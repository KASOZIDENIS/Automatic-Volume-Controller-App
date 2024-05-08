package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.util.Arrays;

public interface zzoc {

    public static final class zza extends zzns<zza> {
        public String[] zzaOd;
        public String[] zzaOe;
        public int[] zzaOf;

        public zza() {
            zzAd();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            if (!zznw.equals((Object[]) this.zzaOd, (Object[]) zza.zzaOd) || !zznw.equals((Object[]) this.zzaOe, (Object[]) zza.zzaOe) || !zznw.equals(this.zzaOf, zza.zzaOf)) {
                return false;
            }
            return zza(zza);
        }

        public int hashCode() {
            return ((((((zznw.hashCode((Object[]) this.zzaOd) + 527) * 31) + zznw.hashCode((Object[]) this.zzaOe)) * 31) + zznw.hashCode(this.zzaOf)) * 31) + zzzP();
        }

        public zza zzAd() {
            this.zzaOd = zzob.zzaOa;
            this.zzaOe = zzob.zzaOa;
            this.zzaOf = zzob.zzaNV;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* renamed from: zzB */
        public zza zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        int zzb = zzob.zzb(zznq, 10);
                        int length = this.zzaOd == null ? 0 : this.zzaOd.length;
                        String[] strArr = new String[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzaOd, 0, strArr, 0, length);
                        }
                        while (length < strArr.length - 1) {
                            strArr[length] = zznq.readString();
                            zznq.zzzy();
                            length++;
                        }
                        strArr[length] = zznq.readString();
                        this.zzaOd = strArr;
                        continue;
                    case 18:
                        int zzb2 = zzob.zzb(zznq, 18);
                        int length2 = this.zzaOe == null ? 0 : this.zzaOe.length;
                        String[] strArr2 = new String[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzaOe, 0, strArr2, 0, length2);
                        }
                        while (length2 < strArr2.length - 1) {
                            strArr2[length2] = zznq.readString();
                            zznq.zzzy();
                            length2++;
                        }
                        strArr2[length2] = zznq.readString();
                        this.zzaOe = strArr2;
                        continue;
                    case 24:
                        int zzb3 = zzob.zzb(zznq, 24);
                        int length3 = this.zzaOf == null ? 0 : this.zzaOf.length;
                        int[] iArr = new int[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzaOf, 0, iArr, 0, length3);
                        }
                        while (length3 < iArr.length - 1) {
                            iArr[length3] = zznq.zzzB();
                            zznq.zzzy();
                            length3++;
                        }
                        iArr[length3] = zznq.zzzB();
                        this.zzaOf = iArr;
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
                        int length4 = this.zzaOf == null ? 0 : this.zzaOf.length;
                        int[] iArr2 = new int[(i + length4)];
                        if (length4 != 0) {
                            System.arraycopy(this.zzaOf, 0, iArr2, 0, length4);
                        }
                        while (length4 < iArr2.length) {
                            iArr2[length4] = zznq.zzzB();
                            length4++;
                        }
                        this.zzaOf = iArr2;
                        zznq.zzjo(zzjn);
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
            if (this.zzaOd != null && this.zzaOd.length > 0) {
                for (String str : this.zzaOd) {
                    if (str != null) {
                        zznr.zzb(1, str);
                    }
                }
            }
            if (this.zzaOe != null && this.zzaOe.length > 0) {
                for (String str2 : this.zzaOe) {
                    if (str2 != null) {
                        zznr.zzb(2, str2);
                    }
                }
            }
            if (this.zzaOf != null && this.zzaOf.length > 0) {
                for (int zzx : this.zzaOf) {
                    zznr.zzx(3, zzx);
                }
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int i;
            int zzc = super.zzc();
            if (this.zzaOd == null || this.zzaOd.length <= 0) {
                i = zzc;
            } else {
                int i2 = 0;
                int i3 = 0;
                for (String str : this.zzaOd) {
                    if (str != null) {
                        i3++;
                        i2 += zznr.zzeB(str);
                    }
                }
                i = zzc + i2 + (i3 * 1);
            }
            if (this.zzaOe != null && this.zzaOe.length > 0) {
                int i4 = 0;
                int i5 = 0;
                for (String str2 : this.zzaOe) {
                    if (str2 != null) {
                        i5++;
                        i4 += zznr.zzeB(str2);
                    }
                }
                i = i + i4 + (i5 * 1);
            }
            if (this.zzaOf == null || this.zzaOf.length <= 0) {
                return i;
            }
            int i6 = 0;
            for (int zzju : this.zzaOf) {
                i6 += zznr.zzju(zzju);
            }
            return i + i6 + (this.zzaOf.length * 1);
        }
    }

    public static final class zzb extends zzns<zzb> {
        public String version;
        public int zzaOg;
        public String zzaOh;

        public zzb() {
            zzAe();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzb)) {
                return false;
            }
            zzb zzb = (zzb) o;
            if (this.zzaOg != zzb.zzaOg) {
                return false;
            }
            if (this.zzaOh == null) {
                if (zzb.zzaOh != null) {
                    return false;
                }
            } else if (!this.zzaOh.equals(zzb.zzaOh)) {
                return false;
            }
            if (this.version == null) {
                if (zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(zzb.version)) {
                return false;
            }
            return zza(zzb);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzaOh == null ? 0 : this.zzaOh.hashCode()) + ((this.zzaOg + 527) * 31)) * 31;
            if (this.version != null) {
                i = this.version.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public zzb zzAe() {
            this.zzaOg = 0;
            this.zzaOh = "";
            this.version = "";
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* renamed from: zzC */
        public zzb zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        int zzzB = zznq.zzzB();
                        switch (zzzB) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9:
                            case 10:
                            case 11:
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                            case 16:
                            case 17:
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                                this.zzaOg = zzzB;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzaOh = zznq.readString();
                        continue;
                    case 26:
                        this.version = zznq.readString();
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
            if (this.zzaOg != 0) {
                zznr.zzx(1, this.zzaOg);
            }
            if (!this.zzaOh.equals("")) {
                zznr.zzb(2, this.zzaOh);
            }
            if (!this.version.equals("")) {
                zznr.zzb(3, this.version);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (this.zzaOg != 0) {
                zzc += zznr.zzz(1, this.zzaOg);
            }
            if (!this.zzaOh.equals("")) {
                zzc += zznr.zzj(2, this.zzaOh);
            }
            return !this.version.equals("") ? zzc + zznr.zzj(3, this.version) : zzc;
        }
    }

    public static final class zzc extends zzns<zzc> {
        public byte[] zzaOi;
        public byte[][] zzaOj;
        public boolean zzaOk;

        public zzc() {
            zzAf();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzc)) {
                return false;
            }
            zzc zzc = (zzc) o;
            if (!Arrays.equals(this.zzaOi, zzc.zzaOi) || !zznw.zza(this.zzaOj, zzc.zzaOj) || this.zzaOk != zzc.zzaOk) {
                return false;
            }
            return zza(zzc);
        }

        public int hashCode() {
            return (((this.zzaOk ? 1231 : 1237) + ((((Arrays.hashCode(this.zzaOi) + 527) * 31) + zznw.zza(this.zzaOj)) * 31)) * 31) + zzzP();
        }

        public zzc zzAf() {
            this.zzaOi = zzob.zzaOc;
            this.zzaOj = zzob.zzaOb;
            this.zzaOk = false;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* renamed from: zzD */
        public zzc zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        this.zzaOi = zznq.readBytes();
                        continue;
                    case 18:
                        int zzb = zzob.zzb(zznq, 18);
                        int length = this.zzaOj == null ? 0 : this.zzaOj.length;
                        byte[][] bArr = new byte[(zzb + length)][];
                        if (length != 0) {
                            System.arraycopy(this.zzaOj, 0, bArr, 0, length);
                        }
                        while (length < bArr.length - 1) {
                            bArr[length] = zznq.readBytes();
                            zznq.zzzy();
                            length++;
                        }
                        bArr[length] = zznq.readBytes();
                        this.zzaOj = bArr;
                        continue;
                    case 24:
                        this.zzaOk = zznq.zzzC();
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
            if (!Arrays.equals(this.zzaOi, zzob.zzaOc)) {
                zznr.zza(1, this.zzaOi);
            }
            if (this.zzaOj != null && this.zzaOj.length > 0) {
                for (byte[] bArr : this.zzaOj) {
                    if (bArr != null) {
                        zznr.zza(2, bArr);
                    }
                }
            }
            if (this.zzaOk) {
                zznr.zzb(3, this.zzaOk);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (!Arrays.equals(this.zzaOi, zzob.zzaOc)) {
                zzc += zznr.zzb(1, this.zzaOi);
            }
            if (this.zzaOj != null && this.zzaOj.length > 0) {
                int i = 0;
                int i2 = 0;
                for (byte[] bArr : this.zzaOj) {
                    if (bArr != null) {
                        i2++;
                        i += zznr.zzy(bArr);
                    }
                }
                zzc = zzc + i + (i2 * 1);
            }
            return this.zzaOk ? zzc + zznr.zzc(3, this.zzaOk) : zzc;
        }
    }

    public static final class zzd extends zzns<zzd> {
        public String tag;
        public long zzaOl;
        public long zzaOm;
        public int zzaOn;
        public int zzaOo;
        public boolean zzaOp;
        public zze[] zzaOq;
        public zzb zzaOr;
        public byte[] zzaOs;
        public byte[] zzaOt;
        public byte[] zzaOu;
        public zza zzaOv;
        public String zzaOw;
        public long zzaOx;
        public zzc zzaOy;

        public zzd() {
            zzAg();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzd)) {
                return false;
            }
            zzd zzd = (zzd) o;
            if (this.zzaOl != zzd.zzaOl || this.zzaOm != zzd.zzaOm) {
                return false;
            }
            if (this.tag == null) {
                if (zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(zzd.tag)) {
                return false;
            }
            if (this.zzaOn != zzd.zzaOn || this.zzaOo != zzd.zzaOo || this.zzaOp != zzd.zzaOp || !zznw.equals((Object[]) this.zzaOq, (Object[]) zzd.zzaOq)) {
                return false;
            }
            if (this.zzaOr == null) {
                if (zzd.zzaOr != null) {
                    return false;
                }
            } else if (!this.zzaOr.equals(zzd.zzaOr)) {
                return false;
            }
            if (!Arrays.equals(this.zzaOs, zzd.zzaOs) || !Arrays.equals(this.zzaOt, zzd.zzaOt) || !Arrays.equals(this.zzaOu, zzd.zzaOu)) {
                return false;
            }
            if (this.zzaOv == null) {
                if (zzd.zzaOv != null) {
                    return false;
                }
            } else if (!this.zzaOv.equals(zzd.zzaOv)) {
                return false;
            }
            if (this.zzaOw == null) {
                if (zzd.zzaOw != null) {
                    return false;
                }
            } else if (!this.zzaOw.equals(zzd.zzaOw)) {
                return false;
            }
            if (this.zzaOx != zzd.zzaOx) {
                return false;
            }
            if (this.zzaOy == null) {
                if (zzd.zzaOy != null) {
                    return false;
                }
            } else if (!this.zzaOy.equals(zzd.zzaOy)) {
                return false;
            }
            return zza(zzd);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((this.zzaOw == null ? 0 : this.zzaOw.hashCode()) + (((this.zzaOv == null ? 0 : this.zzaOv.hashCode()) + (((((((((this.zzaOr == null ? 0 : this.zzaOr.hashCode()) + (((((this.zzaOp ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((int) (this.zzaOl ^ (this.zzaOl >>> 32))) + 527) * 31) + ((int) (this.zzaOm ^ (this.zzaOm >>> 32)))) * 31)) * 31) + this.zzaOn) * 31) + this.zzaOo) * 31)) * 31) + zznw.hashCode((Object[]) this.zzaOq)) * 31)) * 31) + Arrays.hashCode(this.zzaOs)) * 31) + Arrays.hashCode(this.zzaOt)) * 31) + Arrays.hashCode(this.zzaOu)) * 31)) * 31)) * 31) + ((int) (this.zzaOx ^ (this.zzaOx >>> 32)))) * 31;
            if (this.zzaOy != null) {
                i = this.zzaOy.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public zzd zzAg() {
            this.zzaOl = 0;
            this.zzaOm = 0;
            this.tag = "";
            this.zzaOn = 0;
            this.zzaOo = 0;
            this.zzaOp = false;
            this.zzaOq = zze.zzAh();
            this.zzaOr = null;
            this.zzaOs = zzob.zzaOc;
            this.zzaOt = zzob.zzaOc;
            this.zzaOu = zzob.zzaOc;
            this.zzaOv = null;
            this.zzaOw = "";
            this.zzaOx = 180000;
            this.zzaOy = null;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* renamed from: zzE */
        public zzd zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        this.zzaOl = zznq.zzzA();
                        continue;
                    case 18:
                        this.tag = zznq.readString();
                        continue;
                    case 26:
                        int zzb = zzob.zzb(zznq, 26);
                        int length = this.zzaOq == null ? 0 : this.zzaOq.length;
                        zze[] zzeArr = new zze[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzaOq, 0, zzeArr, 0, length);
                        }
                        while (length < zzeArr.length - 1) {
                            zzeArr[length] = new zze();
                            zznq.zza(zzeArr[length]);
                            zznq.zzzy();
                            length++;
                        }
                        zzeArr[length] = new zze();
                        zznq.zza(zzeArr[length]);
                        this.zzaOq = zzeArr;
                        continue;
                    case 50:
                        this.zzaOs = zznq.readBytes();
                        continue;
                    case 58:
                        if (this.zzaOv == null) {
                            this.zzaOv = new zza();
                        }
                        zznq.zza(this.zzaOv);
                        continue;
                    case 66:
                        this.zzaOt = zznq.readBytes();
                        continue;
                    case 74:
                        if (this.zzaOr == null) {
                            this.zzaOr = new zzb();
                        }
                        zznq.zza(this.zzaOr);
                        continue;
                    case 80:
                        this.zzaOp = zznq.zzzC();
                        continue;
                    case 88:
                        this.zzaOn = zznq.zzzB();
                        continue;
                    case 96:
                        this.zzaOo = zznq.zzzB();
                        continue;
                    case 106:
                        this.zzaOu = zznq.readBytes();
                        continue;
                    case 114:
                        this.zzaOw = zznq.readString();
                        continue;
                    case 120:
                        this.zzaOx = zznq.zzzE();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD:
                        if (this.zzaOy == null) {
                            this.zzaOy = new zzc();
                        }
                        zznq.zza(this.zzaOy);
                        continue;
                    case 136:
                        this.zzaOm = zznq.zzzA();
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
            if (this.zzaOl != 0) {
                zznr.zzb(1, this.zzaOl);
            }
            if (!this.tag.equals("")) {
                zznr.zzb(2, this.tag);
            }
            if (this.zzaOq != null && this.zzaOq.length > 0) {
                for (zze zze : this.zzaOq) {
                    if (zze != null) {
                        zznr.zza(3, (zzny) zze);
                    }
                }
            }
            if (!Arrays.equals(this.zzaOs, zzob.zzaOc)) {
                zznr.zza(6, this.zzaOs);
            }
            if (this.zzaOv != null) {
                zznr.zza(7, (zzny) this.zzaOv);
            }
            if (!Arrays.equals(this.zzaOt, zzob.zzaOc)) {
                zznr.zza(8, this.zzaOt);
            }
            if (this.zzaOr != null) {
                zznr.zza(9, (zzny) this.zzaOr);
            }
            if (this.zzaOp) {
                zznr.zzb(10, this.zzaOp);
            }
            if (this.zzaOn != 0) {
                zznr.zzx(11, this.zzaOn);
            }
            if (this.zzaOo != 0) {
                zznr.zzx(12, this.zzaOo);
            }
            if (!Arrays.equals(this.zzaOu, zzob.zzaOc)) {
                zznr.zza(13, this.zzaOu);
            }
            if (!this.zzaOw.equals("")) {
                zznr.zzb(14, this.zzaOw);
            }
            if (this.zzaOx != 180000) {
                zznr.zzc(15, this.zzaOx);
            }
            if (this.zzaOy != null) {
                zznr.zza(16, (zzny) this.zzaOy);
            }
            if (this.zzaOm != 0) {
                zznr.zzb(17, this.zzaOm);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (this.zzaOl != 0) {
                zzc += zznr.zzd(1, this.zzaOl);
            }
            if (!this.tag.equals("")) {
                zzc += zznr.zzj(2, this.tag);
            }
            if (this.zzaOq != null && this.zzaOq.length > 0) {
                int i = zzc;
                for (zze zze : this.zzaOq) {
                    if (zze != null) {
                        i += zznr.zzc(3, (zzny) zze);
                    }
                }
                zzc = i;
            }
            if (!Arrays.equals(this.zzaOs, zzob.zzaOc)) {
                zzc += zznr.zzb(6, this.zzaOs);
            }
            if (this.zzaOv != null) {
                zzc += zznr.zzc(7, (zzny) this.zzaOv);
            }
            if (!Arrays.equals(this.zzaOt, zzob.zzaOc)) {
                zzc += zznr.zzb(8, this.zzaOt);
            }
            if (this.zzaOr != null) {
                zzc += zznr.zzc(9, (zzny) this.zzaOr);
            }
            if (this.zzaOp) {
                zzc += zznr.zzc(10, this.zzaOp);
            }
            if (this.zzaOn != 0) {
                zzc += zznr.zzz(11, this.zzaOn);
            }
            if (this.zzaOo != 0) {
                zzc += zznr.zzz(12, this.zzaOo);
            }
            if (!Arrays.equals(this.zzaOu, zzob.zzaOc)) {
                zzc += zznr.zzb(13, this.zzaOu);
            }
            if (!this.zzaOw.equals("")) {
                zzc += zznr.zzj(14, this.zzaOw);
            }
            if (this.zzaOx != 180000) {
                zzc += zznr.zze(15, this.zzaOx);
            }
            if (this.zzaOy != null) {
                zzc += zznr.zzc(16, (zzny) this.zzaOy);
            }
            return this.zzaOm != 0 ? zzc + zznr.zzd(17, this.zzaOm) : zzc;
        }
    }

    public static final class zze extends zzns<zze> {
        private static volatile zze[] zzaOz;
        public String value;
        public String zzgk;

        public zze() {
            zzAi();
        }

        public static zze[] zzAh() {
            if (zzaOz == null) {
                synchronized (zznw.zzaNS) {
                    if (zzaOz == null) {
                        zzaOz = new zze[0];
                    }
                }
            }
            return zzaOz;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zze)) {
                return false;
            }
            zze zze = (zze) o;
            if (this.zzgk == null) {
                if (zze.zzgk != null) {
                    return false;
                }
            } else if (!this.zzgk.equals(zze.zzgk)) {
                return false;
            }
            if (this.value == null) {
                if (zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(zze.value)) {
                return false;
            }
            return zza(zze);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzgk == null ? 0 : this.zzgk.hashCode()) + 527) * 31;
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public zze zzAi() {
            this.zzgk = "";
            this.value = "";
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }

        /* renamed from: zzF */
        public zze zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 10:
                        this.zzgk = zznq.readString();
                        continue;
                    case 18:
                        this.value = zznq.readString();
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
            if (!this.zzgk.equals("")) {
                zznr.zzb(1, this.zzgk);
            }
            if (!this.value.equals("")) {
                zznr.zzb(2, this.value);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc();
            if (!this.zzgk.equals("")) {
                zzc += zznr.zzj(1, this.zzgk);
            }
            return !this.value.equals("") ? zzc + zznr.zzj(2, this.value) : zzc;
        }
    }
}
