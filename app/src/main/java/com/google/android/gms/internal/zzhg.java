package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import com.google.android.gms.common.internal.zzu;

public final class zzhg extends zzhp<zza, Drawable> {

    public static final class zza {
        public final int zzSP;
        public final int zzSQ;

        public zza(int i, int i2) {
            this.zzSP = i;
            this.zzSQ = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof zza)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            zza zza = (zza) obj;
            return zza.zzSP == this.zzSP && zza.zzSQ == this.zzSQ;
        }

        public int hashCode() {
            return zzu.hashCode(Integer.valueOf(this.zzSP), Integer.valueOf(this.zzSQ));
        }
    }

    public zzhg() {
        super(10);
    }
}
