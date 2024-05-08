package com.google.android.gms.internal;

import android.net.Uri;
import android.text.TextUtils;
import android.util.LogPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public final class zzkf implements zzkm {
    private static final Uri zzavU;
    private final LogPrinter zzavV = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("uri");
        builder.authority("local");
        zzavU = builder.build();
    }

    public void zzb(zzkg zzkg) {
        ArrayList<zzki> arrayList = new ArrayList<>(zzkg.zzua());
        Collections.sort(arrayList, new Comparator<zzki>() {
            /* renamed from: zza */
            public int compare(zzki zzki, zzki zzki2) {
                return zzki.getClass().getCanonicalName().compareTo(zzki2.getClass().getCanonicalName());
            }
        });
        StringBuilder sb = new StringBuilder();
        for (zzki obj : arrayList) {
            String obj2 = obj.toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (sb.length() != 0) {
                    sb.append(", ");
                }
                sb.append(obj2);
            }
        }
        this.zzavV.println(sb.toString());
    }

    public Uri zzfR() {
        return zzavU;
    }
}
