package com.google.android.gms.internal;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzks extends zzki<zzks> {
    private ProductAction zzEF;
    private final Map<String, List<Product>> zzEG = new HashMap();
    private final List<Promotion> zzEH = new ArrayList();
    private final List<Product> zzEI = new ArrayList();

    public String toString() {
        HashMap hashMap = new HashMap();
        if (!this.zzEI.isEmpty()) {
            hashMap.put("products", this.zzEI);
        }
        if (!this.zzEH.isEmpty()) {
            hashMap.put("promotions", this.zzEH);
        }
        if (!this.zzEG.isEmpty()) {
            hashMap.put("impressions", this.zzEG);
        }
        hashMap.put("productAction", this.zzEF);
        return zzu(hashMap);
    }

    public void zza(Product product, String str) {
        if (product != null) {
            if (str == null) {
                str = "";
            }
            if (!this.zzEG.containsKey(str)) {
                this.zzEG.put(str, new ArrayList());
            }
            this.zzEG.get(str).add(product);
        }
    }

    public void zza(zzks zzks) {
        zzks.zzEI.addAll(this.zzEI);
        zzks.zzEH.addAll(this.zzEH);
        for (Map.Entry next : this.zzEG.entrySet()) {
            String str = (String) next.getKey();
            for (Product zza : (List) next.getValue()) {
                zzks.zza(zza, str);
            }
        }
        if (this.zzEF != null) {
            zzks.zzEF = this.zzEF;
        }
    }

    public ProductAction zzuE() {
        return this.zzEF;
    }

    public List<Product> zzuF() {
        return Collections.unmodifiableList(this.zzEI);
    }

    public Map<String, List<Product>> zzuG() {
        return this.zzEG;
    }

    public List<Promotion> zzuH() {
        return Collections.unmodifiableList(this.zzEH);
    }
}
