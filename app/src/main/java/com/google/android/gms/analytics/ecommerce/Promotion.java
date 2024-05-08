package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzki;
import java.util.HashMap;
import java.util.Map;

public class Promotion {
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    Map<String, String> zzFn = new HashMap();

    /* access modifiers changed from: package-private */
    public void put(String name, String value) {
        zzv.zzb(name, (Object) "Name should be non-null");
        this.zzFn.put(name, value);
    }

    public Promotion setCreative(String value) {
        put("cr", value);
        return this;
    }

    public Promotion setId(String value) {
        put("id", value);
        return this;
    }

    public Promotion setName(String value) {
        put("nm", value);
        return this;
    }

    public Promotion setPosition(String value) {
        put("ps", value);
        return this;
    }

    public String toString() {
        return zzki.zzC(this.zzFn);
    }

    public Map<String, String> zzaC(String str) {
        HashMap hashMap = new HashMap();
        for (Map.Entry next : this.zzFn.entrySet()) {
            hashMap.put(str + ((String) next.getKey()), next.getValue());
        }
        return hashMap;
    }
}
