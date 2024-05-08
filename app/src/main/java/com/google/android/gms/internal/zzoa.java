package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

final class zzoa {
    final int tag;
    final byte[] zzaNU;

    zzoa(int i, byte[] bArr) {
        this.tag = i;
        this.zzaNU = bArr;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof zzoa)) {
            return false;
        }
        zzoa zzoa = (zzoa) o;
        return this.tag == zzoa.tag && Arrays.equals(this.zzaNU, zzoa.zzaNU);
    }

    public int hashCode() {
        return ((this.tag + 527) * 31) + Arrays.hashCode(this.zzaNU);
    }

    /* access modifiers changed from: package-private */
    public void zza(zznr zznr) throws IOException {
        zznr.zzjy(this.tag);
        zznr.zzz(this.zzaNU);
    }

    /* access modifiers changed from: package-private */
    public int zzc() {
        return 0 + zznr.zzjz(this.tag) + this.zzaNU.length;
    }
}
