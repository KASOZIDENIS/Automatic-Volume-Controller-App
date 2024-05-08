package com.google.android.gms.internal;

import com.google.android.gms.internal.zzc;
import java.io.IOException;

public interface zzmi {

    public static final class zza extends zzns<zza> {
        public long zzaGL;
        public zzc.zzj zzaGM;
        public zzc.zzf zzhh;

        public zza() {
            zzye();
        }

        public static zza zzo(byte[] bArr) throws zznx {
            return (zza) zzny.zza(new zza(), bArr);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            if (this.zzaGL != zza.zzaGL) {
                return false;
            }
            if (this.zzhh == null) {
                if (zza.zzhh != null) {
                    return false;
                }
            } else if (!this.zzhh.equals(zza.zzhh)) {
                return false;
            }
            if (this.zzaGM == null) {
                if (zza.zzaGM != null) {
                    return false;
                }
            } else if (!this.zzaGM.equals(zza.zzaGM)) {
                return false;
            }
            return zza(zza);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.zzhh == null ? 0 : this.zzhh.hashCode()) + ((((int) (this.zzaGL ^ (this.zzaGL >>> 32))) + 527) * 31)) * 31;
            if (this.zzaGM != null) {
                i = this.zzaGM.hashCode();
            }
            return ((hashCode + i) * 31) + zzzP();
        }

        public void zza(zznr zznr) throws IOException {
            zznr.zzb(1, this.zzaGL);
            if (this.zzhh != null) {
                zznr.zza(2, (zzny) this.zzhh);
            }
            if (this.zzaGM != null) {
                zznr.zza(3, (zzny) this.zzaGM);
            }
            super.zza(zznr);
        }

        /* access modifiers changed from: protected */
        public int zzc() {
            int zzc = super.zzc() + zznr.zzd(1, this.zzaGL);
            if (this.zzhh != null) {
                zzc += zznr.zzc(2, (zzny) this.zzhh);
            }
            return this.zzaGM != null ? zzc + zznr.zzc(3, (zzny) this.zzaGM) : zzc;
        }

        /* renamed from: zzu */
        public zza zzb(zznq zznq) throws IOException {
            while (true) {
                int zzzy = zznq.zzzy();
                switch (zzzy) {
                    case 0:
                        break;
                    case 8:
                        this.zzaGL = zznq.zzzA();
                        continue;
                    case 18:
                        if (this.zzhh == null) {
                            this.zzhh = new zzc.zzf();
                        }
                        zznq.zza(this.zzhh);
                        continue;
                    case 26:
                        if (this.zzaGM == null) {
                            this.zzaGM = new zzc.zzj();
                        }
                        zznq.zza(this.zzaGM);
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

        public zza zzye() {
            this.zzaGL = 0;
            this.zzhh = null;
            this.zzaGM = null;
            this.zzaNI = null;
            this.zzaNT = -1;
            return this;
        }
    }
}
