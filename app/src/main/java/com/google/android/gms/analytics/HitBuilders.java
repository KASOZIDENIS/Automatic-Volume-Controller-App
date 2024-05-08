package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HitBuilders {

    @Deprecated
    public static class AppViewBuilder extends HitBuilder<AppViewBuilder> {
        public AppViewBuilder() {
            set("&t", "screenview");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    public static class EventBuilder extends HitBuilder<EventBuilder> {
        public EventBuilder() {
            set("&t", "event");
        }

        public EventBuilder(String category, String action) {
            this();
            setCategory(category);
            setAction(action);
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public EventBuilder setAction(String action) {
            set("&ea", action);
            return this;
        }

        public EventBuilder setCategory(String category) {
            set("&ec", category);
            return this;
        }

        public EventBuilder setLabel(String label) {
            set("&el", label);
            return this;
        }

        public EventBuilder setValue(long value) {
            set("&ev", Long.toString(value));
            return this;
        }
    }

    public static class ExceptionBuilder extends HitBuilder<ExceptionBuilder> {
        public ExceptionBuilder() {
            set("&t", "exception");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public ExceptionBuilder setDescription(String description) {
            set("&exd", description);
            return this;
        }

        public ExceptionBuilder setFatal(boolean fatal) {
            set("&exf", zzal.zzH(fatal));
            return this;
        }
    }

    protected static class HitBuilder<T extends HitBuilder> {
        private Map<String, String> zzEE = new HashMap();
        ProductAction zzEF;
        Map<String, List<Product>> zzEG = new HashMap();
        List<Promotion> zzEH = new ArrayList();
        List<Product> zzEI = new ArrayList();

        protected HitBuilder() {
        }

        public T addImpression(Product product, String impressionList) {
            if (product == null) {
                zzad.zzan("product should be non-null");
            } else {
                if (impressionList == null) {
                    impressionList = "";
                }
                if (!this.zzEG.containsKey(impressionList)) {
                    this.zzEG.put(impressionList, new ArrayList());
                }
                this.zzEG.get(impressionList).add(product);
            }
            return this;
        }

        public T addProduct(Product product) {
            if (product == null) {
                zzad.zzan("product should be non-null");
            } else {
                this.zzEI.add(product);
            }
            return this;
        }

        public T addPromotion(Promotion promotion) {
            if (promotion == null) {
                zzad.zzan("promotion should be non-null");
            } else {
                this.zzEH.add(promotion);
            }
            return this;
        }

        public Map<String, String> build() {
            HashMap hashMap = new HashMap(this.zzEE);
            if (this.zzEF != null) {
                hashMap.putAll(this.zzEF.build());
            }
            int i = 1;
            for (Promotion zzaC : this.zzEH) {
                hashMap.putAll(zzaC.zzaC(zzc.zzK(i)));
                i++;
            }
            int i2 = 1;
            for (Product zzaC2 : this.zzEI) {
                hashMap.putAll(zzaC2.zzaC(zzc.zzI(i2)));
                i2++;
            }
            int i3 = 1;
            for (Map.Entry next : this.zzEG.entrySet()) {
                String zzN = zzc.zzN(i3);
                int i4 = 1;
                for (Product zzaC3 : (List) next.getValue()) {
                    hashMap.putAll(zzaC3.zzaC(zzN + zzc.zzM(i4)));
                    i4++;
                }
                if (!TextUtils.isEmpty((CharSequence) next.getKey())) {
                    hashMap.put(zzN + "nm", next.getKey());
                }
                i3++;
            }
            return hashMap;
        }

        /* access modifiers changed from: protected */
        public String get(String paramName) {
            return this.zzEE.get(paramName);
        }

        public final T set(String paramName, String paramValue) {
            if (paramName != null) {
                this.zzEE.put(paramName, paramValue);
            } else {
                zzad.zzan(" HitBuilder.set() called with a null paramName.");
            }
            return this;
        }

        public final T setAll(Map<String, String> params) {
            if (params != null) {
                this.zzEE.putAll(new HashMap(params));
            }
            return this;
        }

        public T setCampaignParamsFromUrl(String utmParams) {
            String zzaW = zzal.zzaW(utmParams);
            if (!TextUtils.isEmpty(zzaW)) {
                Map<String, String> zzaU = zzal.zzaU(zzaW);
                set("&cc", zzaU.get("utm_content"));
                set("&cm", zzaU.get("utm_medium"));
                set("&cn", zzaU.get("utm_campaign"));
                set("&cs", zzaU.get("utm_source"));
                set("&ck", zzaU.get("utm_term"));
                set("&ci", zzaU.get("utm_id"));
                set("&anid", zzaU.get("anid"));
                set("&gclid", zzaU.get("gclid"));
                set("&dclid", zzaU.get("dclid"));
                set("&aclid", zzaU.get("aclid"));
                set("&gmob_t", zzaU.get("gmob_t"));
            }
            return this;
        }

        public T setCustomDimension(int index, String dimension) {
            set(zzc.zzE(index), dimension);
            return this;
        }

        public T setCustomMetric(int index, float metric) {
            set(zzc.zzG(index), Float.toString(metric));
            return this;
        }

        /* access modifiers changed from: protected */
        public T setHitType(String hitType) {
            set("&t", hitType);
            return this;
        }

        public T setNewSession() {
            set("&sc", "start");
            return this;
        }

        public T setNonInteraction(boolean nonInteraction) {
            set("&ni", zzal.zzH(nonInteraction));
            return this;
        }

        public T setProductAction(ProductAction action) {
            this.zzEF = action;
            return this;
        }

        public T setPromotionAction(String action) {
            this.zzEE.put("&promoa", action);
            return this;
        }
    }

    @Deprecated
    public static class ItemBuilder extends HitBuilder<ItemBuilder> {
        public ItemBuilder() {
            set("&t", "item");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public ItemBuilder setCategory(String category) {
            set("&iv", category);
            return this;
        }

        public ItemBuilder setCurrencyCode(String currencyCode) {
            set("&cu", currencyCode);
            return this;
        }

        public ItemBuilder setName(String name) {
            set("&in", name);
            return this;
        }

        public ItemBuilder setPrice(double price) {
            set("&ip", Double.toString(price));
            return this;
        }

        public ItemBuilder setQuantity(long quantity) {
            set("&iq", Long.toString(quantity));
            return this;
        }

        public ItemBuilder setSku(String sku) {
            set("&ic", sku);
            return this;
        }

        public ItemBuilder setTransactionId(String transactionid) {
            set("&ti", transactionid);
            return this;
        }
    }

    public static class ScreenViewBuilder extends HitBuilder<ScreenViewBuilder> {
        public ScreenViewBuilder() {
            set("&t", "screenview");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }
    }

    public static class SocialBuilder extends HitBuilder<SocialBuilder> {
        public SocialBuilder() {
            set("&t", "social");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public SocialBuilder setAction(String action) {
            set("&sa", action);
            return this;
        }

        public SocialBuilder setNetwork(String network) {
            set("&sn", network);
            return this;
        }

        public SocialBuilder setTarget(String target) {
            set("&st", target);
            return this;
        }
    }

    public static class TimingBuilder extends HitBuilder<TimingBuilder> {
        public TimingBuilder() {
            set("&t", "timing");
        }

        public TimingBuilder(String category, String variable, long value) {
            this();
            setVariable(variable);
            setValue(value);
            setCategory(category);
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public TimingBuilder setCategory(String category) {
            set("&utc", category);
            return this;
        }

        public TimingBuilder setLabel(String label) {
            set("&utl", label);
            return this;
        }

        public TimingBuilder setValue(long value) {
            set("&utt", Long.toString(value));
            return this;
        }

        public TimingBuilder setVariable(String variable) {
            set("&utv", variable);
            return this;
        }
    }

    @Deprecated
    public static class TransactionBuilder extends HitBuilder<TransactionBuilder> {
        public TransactionBuilder() {
            set("&t", "transaction");
        }

        public /* bridge */ /* synthetic */ Map build() {
            return super.build();
        }

        public TransactionBuilder setAffiliation(String affiliation) {
            set("&ta", affiliation);
            return this;
        }

        public TransactionBuilder setCurrencyCode(String currencyCode) {
            set("&cu", currencyCode);
            return this;
        }

        public TransactionBuilder setRevenue(double revenue) {
            set("&tr", Double.toString(revenue));
            return this;
        }

        public TransactionBuilder setShipping(double shipping) {
            set("&ts", Double.toString(shipping));
            return this;
        }

        public TransactionBuilder setTax(double tax) {
            set("&tt", Double.toString(tax));
            return this;
        }

        public TransactionBuilder setTransactionId(String transactionid) {
            set("&ti", transactionid);
            return this;
        }
    }
}
