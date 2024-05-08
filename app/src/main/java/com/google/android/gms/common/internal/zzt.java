package com.google.android.gms.common.internal;

import java.util.Iterator;

public class zzt {
    private final String separator;

    private zzt(String str) {
        this.separator = str;
    }

    public static zzt zzbR(String str) {
        return new zzt(str);
    }

    public final String zza(Iterable<?> iterable) {
        return zza(new StringBuilder(), iterable).toString();
    }

    public final StringBuilder zza(StringBuilder sb, Iterable<?> iterable) {
        Iterator<?> it = iterable.iterator();
        if (it.hasNext()) {
            sb.append(zzp(it.next()));
            while (it.hasNext()) {
                sb.append(this.separator);
                sb.append(zzp(it.next()));
            }
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public CharSequence zzp(Object obj) {
        return obj instanceof CharSequence ? (CharSequence) obj : obj.toString();
    }
}
