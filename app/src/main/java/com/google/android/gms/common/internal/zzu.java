package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class zzu {

    public static final class zza {
        private final Object zzCG;
        private final List<String> zzTZ;

        private zza(Object obj) {
            this.zzCG = zzv.zzr(obj);
            this.zzTZ = new ArrayList();
        }

        public String toString() {
            StringBuilder append = new StringBuilder(100).append(this.zzCG.getClass().getSimpleName()).append('{');
            int size = this.zzTZ.size();
            for (int i = 0; i < size; i++) {
                append.append(this.zzTZ.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }

        public zza zzg(String str, Object obj) {
            this.zzTZ.add(((String) zzv.zzr(str)) + "=" + String.valueOf(obj));
            return this;
        }
    }

    public static boolean equal(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(Object... objects) {
        return Arrays.hashCode(objects);
    }

    public static zza zzq(Object obj) {
        return new zza(obj);
    }
}
