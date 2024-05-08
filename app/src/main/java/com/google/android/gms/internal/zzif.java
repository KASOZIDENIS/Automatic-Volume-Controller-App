package com.google.android.gms.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzv;
import java.util.Set;

public final class zzif {
    public static String[] zzb(Scope[] scopeArr) {
        zzv.zzb(scopeArr, (Object) "scopes can't be null.");
        String[] strArr = new String[scopeArr.length];
        for (int i = 0; i < scopeArr.length; i++) {
            strArr[i] = scopeArr[i].zzle();
        }
        return strArr;
    }

    public static String[] zzc(Set<Scope> set) {
        return zzb((Scope[]) set.toArray(new Scope[set.size()]));
    }
}
