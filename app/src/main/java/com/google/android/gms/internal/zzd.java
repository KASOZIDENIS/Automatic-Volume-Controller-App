package com.google.android.gms.internal;

import java.io.IOException;

public interface zzd {

    public static final class zza extends zzns<zza> {
        private static volatile zza[] zzhj;
        public int type;
        public String zzhk;
        public zza[] zzhl;
        public zza[] zzhm;
        public zza[] zzhn;
        public String zzho;
        public String zzhp;
        public long zzhq;
        public boolean zzhr;
        public zza[] zzhs;
        public int[] zzht;
        public boolean zzhu;

        public zza() {
            zzs();
        }

        public static zza[] zzr() {
            if (zzhj == null) {
                synchronized (zznw.zzaNS) {
                    if (zzhj == null) {
                        zzhj = new zza[0];
                    }
                }
            }
            return zzhj;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            if (this.type != zza.type) {
                return false;
            }
            if (this.zzhk == null) {
                if (zza.zzhk != null) {
                    return false;
                }
            } else if (!this.zzhk.equals(zza.zzhk)) {
                return false;
            }
            if (!zznw.equals((Object[]) this.zzhl, (Object[]) zza.zzhl) || !zznw.equals((Object[]) this.zzhm, (Object[]) zza.zzhm) || !zznw.equals((Object[]) this.zzhn, (Object[]) zza.zzhn)) {
                return false;
            }
            if (this.zzho == null) {
                if (zza.zzho != null) {
                    return false;
                }
            } else if (!this.zzho.equals(zza.zzho)) {
                return false;
            }
            if (this.zzhp == null) {
                if (zza.zzhp != null) {
                    return false;
                }
            } else if (!this.zzhp.equals(zza.zzhp)) {
                return false;
            }
            if (this.zzhq == zza.zzhq && this.zzhr == zza.zzhr && zznw.equals((Object[]) this.zzhs, (Object[]) zza.zzhs) && zznw.equals(this.zzht, zza.zzht) && this.zzhu == zza.zzhu) {
                return zza(zza);
            }
            return false;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((this.zzho == null ? 0 : this.zzho.hashCode()) + (((((((((this.zzhk == null ? 0 : this.zzhk.hashCode()) + ((this.type + 527) * 31)) * 31) + zznw.hashCode((Object[]) this.zzhl)) * 31) + zznw.hashCode((Object[]) this.zzhm)) * 31) + zznw.hashCode((Object[]) this.zzhn)) * 31)) * 31;
            if (this.zzhp != null) {
                i2 = this.zzhp.hashCode();
            }
            int hashCode2 = ((((((this.zzhr ? 1231 : 1237) + ((((hashCode + i2) * 31) + ((int) (this.zzhq ^ (this.zzhq >>> 32)))) * 31)) * 31) + zznw.hashCode((Object[]) this.zzhs)) * 31) + zznw.hashCode(this.zzht)) * 31;
            if (!this.zzhu) {
                i = 1237;
            }
            return ((hashCode2 + i) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            zznr.zzx(1, this.type);
            if (!this.zzhk.equals("")) {
                zznr.zzb(2, this.zzhk);
            }
            if (this.zzhl != null && this.zzhl.length > 0) {
                for (zza zza : this.zzhl) {
                    if (zza != null) {
                        zznr.zza(3, (zzny) zza);
                    }
                }
            }
            if (this.zzhm != null && this.zzhm.length > 0) {
                for (zza zza2 : this.zzhm) {
                    if (zza2 != null) {
                        zznr.zza(4, (zzny) zza2);
                    }
                }
            }
            if (this.zzhn != null && this.zzhn.length > 0) {
                for (zza zza3 : this.zzhn) {
                    if (zza3 != null) {
                        zznr.zza(5, (zzny) zza3);
                    }
                }
            }
            if (!this.zzho.equals("")) {
                zznr.zzb(6, this.zzho);
            }
            if (!this.zzhp.equals("")) {
                zznr.zzb(7, this.zzhp);
            }
            if (this.zzhq != 0) {
                zznr.zzb(8, this.zzhq);
            }
            if (this.zzhu) {
                zznr.zzb(9, this.zzhu);
            }
            if (this.zzht != null && this.zzht.length > 0) {
                for (int zzx : this.zzht) {
                    zznr.zzx(10, zzx);
                }
            }
            if (this.zzhs != null && this.zzhs.length > 0) {
                for (zza zza4 : this.zzhs) {
                    if (zza4 != null) {
                        zznr.zza(11, (zzny) zza4);
                    }
                }
            }
            if (this.zzhr) {
                zznr.zzb(12, this.zzhr);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc() + zznr.zzz(1, this.type);
            if (!this.zzhk.equals("")) {
                zzc += zznr.zzj(2, this.zzhk);
            }
            if (this.zzhl != null && this.zzhl.length > 0) {
                int i = zzc;
                for (zza zza : this.zzhl) {
                    if (zza != null) {
                        i += zznr.zzc(3, (zzny) zza);
                    }
                }
                zzc = i;
            }
            if (this.zzhm != null && this.zzhm.length > 0) {
                int i2 = zzc;
                for (zza zza2 : this.zzhm) {
                    if (zza2 != null) {
                        i2 += zznr.zzc(4, (zzny) zza2);
                    }
                }
                zzc = i2;
            }
            if (this.zzhn != null && this.zzhn.length > 0) {
                int i3 = zzc;
                for (zza zza3 : this.zzhn) {
                    if (zza3 != null) {
                        i3 += zznr.zzc(5, (zzny) zza3);
                    }
                }
                zzc = i3;
            }
            if (!this.zzho.equals("")) {
                zzc += zznr.zzj(6, this.zzho);
            }
            if (!this.zzhp.equals("")) {
                zzc += zznr.zzj(7, this.zzhp);
            }
            if (this.zzhq != 0) {
                zzc += zznr.zzd(8, this.zzhq);
            }
            if (this.zzhu) {
                zzc += zznr.zzc(9, this.zzhu);
            }
            if (this.zzht != null && this.zzht.length > 0) {
                int i4 = 0;
                for (int zzju : this.zzht) {
                    i4 += zznr.zzju(zzju);
                }
                zzc = zzc + i4 + (this.zzht.length * 1);
            }
            if (this.zzhs != null && this.zzhs.length > 0) {
                for (zza zza4 : this.zzhs) {
                    if (zza4 != null) {
                        zzc += zznr.zzc(11, (zzny) zza4);
                    }
                }
            }
            return this.zzhr ? zzc + zznr.zzc(12, this.zzhr) : zzc;
        }

        /* renamed from: zzl */
        public zza zzb(zznq zznq) throws IOException {
            int i;
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
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = zzzB;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.zzhk = zznq.readString();
                        continue;
                    case 26:
                        int zzb = zzob.zzb(zznq, 26);
                        int length = this.zzhl == null ? 0 : this.zzhl.length;
                        zza[] zzaArr = new zza[(zzb + length)];
                        if (length != 0) {
                            System.arraycopy(this.zzhl, 0, zzaArr, 0, length);
                        }
                        while (length < zzaArr.length - 1) {
                            zzaArr[length] = new zza();
                            zznq.zza(zzaArr[length]);
                            zznq.zzzy();
                            length++;
                        }
                        zzaArr[length] = new zza();
                        zznq.zza(zzaArr[length]);
                        this.zzhl = zzaArr;
                        continue;
                    case 34:
                        int zzb2 = zzob.zzb(zznq, 34);
                        int length2 = this.zzhm == null ? 0 : this.zzhm.length;
                        zza[] zzaArr2 = new zza[(zzb2 + length2)];
                        if (length2 != 0) {
                            System.arraycopy(this.zzhm, 0, zzaArr2, 0, length2);
                        }
                        while (length2 < zzaArr2.length - 1) {
                            zzaArr2[length2] = new zza();
                            zznq.zza(zzaArr2[length2]);
                            zznq.zzzy();
                            length2++;
                        }
                        zzaArr2[length2] = new zza();
                        zznq.zza(zzaArr2[length2]);
                        this.zzhm = zzaArr2;
                        continue;
                    case 42:
                        int zzb3 = zzob.zzb(zznq, 42);
                        int length3 = this.zzhn == null ? 0 : this.zzhn.length;
                        zza[] zzaArr3 = new zza[(zzb3 + length3)];
                        if (length3 != 0) {
                            System.arraycopy(this.zzhn, 0, zzaArr3, 0, length3);
                        }
                        while (length3 < zzaArr3.length - 1) {
                            zzaArr3[length3] = new zza();
                            zznq.zza(zzaArr3[length3]);
                            zznq.zzzy();
                            length3++;
                        }
                        zzaArr3[length3] = new zza();
                        zznq.zza(zzaArr3[length3]);
                        this.zzhn = zzaArr3;
                        continue;
                    case 50:
                        this.zzho = zznq.readString();
                        continue;
                    case 58:
                        this.zzhp = zznq.readString();
                        continue;
                    case 64:
                        this.zzhq = zznq.zzzA();
                        continue;
                    case 72:
                        this.zzhu = zznq.zzzC();
                        continue;
                    case 80:
                        int zzb4 = zzob.zzb(zznq, 80);
                        int[] iArr = new int[zzb4];
                        int i2 = 0;
                        int i3 = 0;
                        while (i2 < zzb4) {
                            if (i2 != 0) {
                                zznq.zzzy();
                            }
                            int zzzB2 = zznq.zzzB();
                            switch (zzzB2) {
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
                                    i = i3 + 1;
                                    iArr[i3] = zzzB2;
                                    break;
                                default:
                                    i = i3;
                                    break;
                            }
                            i2++;
                            i3 = i;
                        }
                        if (i3 != 0) {
                            int length4 = this.zzht == null ? 0 : this.zzht.length;
                            if (length4 != 0 || i3 != iArr.length) {
                                int[] iArr2 = new int[(length4 + i3)];
                                if (length4 != 0) {
                                    System.arraycopy(this.zzht, 0, iArr2, 0, length4);
                                }
                                System.arraycopy(iArr, 0, iArr2, length4, i3);
                                this.zzht = iArr2;
                                break;
                            } else {
                                this.zzht = iArr;
                                break;
                            }
                        } else {
                            continue;
                        }
                    case 82:
                        int zzjn = zznq.zzjn(zznq.zzzF());
                        int position = zznq.getPosition();
                        int i4 = 0;
                        while (zznq.zzzK() > 0) {
                            switch (zznq.zzzB()) {
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
                                    i4++;
                                    break;
                            }
                        }
                        if (i4 != 0) {
                            zznq.zzjp(position);
                            int length5 = this.zzht == null ? 0 : this.zzht.length;
                            int[] iArr3 = new int[(i4 + length5)];
                            if (length5 != 0) {
                                System.arraycopy(this.zzht, 0, iArr3, 0, length5);
                            }
                            while (zznq.zzzK() > 0) {
                                int zzzB3 = zznq.zzzB();
                                switch (zzzB3) {
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
                                        iArr3[length5] = zzzB3;
                                        length5++;
                                        break;
                                }
                            }
                            this.zzht = iArr3;
                        }
                        zznq.zzjo(zzjn);
                        continue;
                    case 90:
                        int zzb5 = zzob.zzb(zznq, 90);
                        int length6 = this.zzhs == null ? 0 : this.zzhs.length;
                        zza[] zzaArr4 = new zza[(zzb5 + length6)];
                        if (length6 != 0) {
                            System.arraycopy(this.zzhs, 0, zzaArr4, 0, length6);
                        }
                        while (length6 < zzaArr4.length - 1) {
                            zzaArr4[length6] = new zza();
                            zznq.zza(zzaArr4[length6]);
                            zznq.zzzy();
                            length6++;
                        }
                        zzaArr4[length6] = new zza();
                        zznq.zza(zzaArr4[length6]);
                        this.zzhs = zzaArr4;
                        continue;
                    case 96:
                        this.zzhr = zznq.zzzC();
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

        public zza zzs() {
            this.type = 1;
            this.zzhk = "";
            this.zzhl = zzr();
            this.zzhm = zzr();
            this.zzhn = zzr();
            this.zzho = "";
            this.zzhp = "";
            this.zzhq = 0;
            this.zzhr = false;
            this.zzhs = zzr();
            this.zzht = zzob.zzaNV;
            this.zzhu = false;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }
}
