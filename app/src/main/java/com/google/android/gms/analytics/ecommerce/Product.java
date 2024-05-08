package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.analytics.zzc;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzki;
import java.util.HashMap;
import java.util.Map;

public class Product {
    Map<String, String> zzFn = new HashMap();

    /* access modifiers changed from: package-private */
    public void put(String name, String value) {
        zzv.zzb(name, (Object) "Name should be non-null");
        this.zzFn.put(name, value);
    }

    public Product setBrand(String value) {
        put("br", value);
        return this;
    }

    public Product setCategory(String value) {
        put("ca", value);
        return this;
    }

    public Product setCouponCode(String value) {
        put("cc", value);
        return this;
    }

    public Product setCustomDimension(int index, String value) {
        put(zzc.zzP(index), value);
        return this;
    }

    public Product setCustomMetric(int index, int value) {
        put(zzc.zzQ(index), Integer.toString(value));
        return this;
    }

    public Product setId(String value) {
        put("id", value);
        return this;
    }

    public Product setName(String value) {
        put("nm", value);
        return this;
    }

    public Product setPosition(int value) {
        put("ps", Integer.toString(value));
        return this;
    }

    public Product setPrice(double value) {
        put("pr", Double.toString(value));
        return this;
    }

    public Product setQuantity(int value) {
        put("qt", Integer.toString(value));
        return this;
    }

    public Product setVariant(String value) {
        put("va", value);
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
