package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.internal.widget.ActivityChooserView;
import android.text.TextUtils;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.internal.zzaa;
import com.google.android.gms.analytics.internal.zzac;
import com.google.android.gms.analytics.internal.zzak;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzg;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzkp;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class Tracker extends zzd {
    private boolean zzEK;
    private final Map<String, String> zzEL = new HashMap();
    /* access modifiers changed from: private */
    public final zzac zzEM;
    /* access modifiers changed from: private */
    public final zza zzEN;
    private ExceptionReporter zzEO;
    /* access modifiers changed from: private */
    public zzak zzEP;
    private final Map<String, String> zzvi = new HashMap();

    private class zza extends zzd implements GoogleAnalytics.zza {
        private boolean zzEY;
        private int zzEZ;
        private long zzFa = -1;
        private boolean zzFb;
        private long zzFc;

        protected zza(zze zze) {
            super(zze);
        }

        private void zzgf() {
            if (this.zzFa >= 0 || this.zzEY) {
                zzfT().zza(Tracker.this.zzEN);
            } else {
                zzfT().zzb(Tracker.this.zzEN);
            }
        }

        public void enableAutoActivityTracking(boolean enabled) {
            this.zzEY = enabled;
            zzgf();
        }

        public void setSessionTimeout(long sessionTimeout) {
            this.zzFa = sessionTimeout;
            zzgf();
        }

        /* access modifiers changed from: protected */
        public void zzgb() {
        }

        public synchronized boolean zzge() {
            boolean z;
            z = this.zzFb;
            this.zzFb = false;
            return z;
        }

        /* access modifiers changed from: package-private */
        public boolean zzgg() {
            return zzgG().elapsedRealtime() >= this.zzFc + Math.max(1000, this.zzFa);
        }

        public void zzn(Activity activity) {
            if (this.zzEZ == 0 && zzgg()) {
                this.zzFb = true;
            }
            this.zzEZ++;
            if (this.zzEY) {
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Tracker.this.setCampaignParamsOnNextHit(intent.getData());
                }
                HashMap hashMap = new HashMap();
                hashMap.put("&t", "screenview");
                Tracker.this.set("&cd", Tracker.this.zzEP != null ? Tracker.this.zzEP.zzq(activity) : activity.getClass().getCanonicalName());
                if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                    String zzp = Tracker.zzp(activity);
                    if (!TextUtils.isEmpty(zzp)) {
                        hashMap.put("&dr", zzp);
                    }
                }
                Tracker.this.send(hashMap);
            }
        }

        public void zzo(Activity activity) {
            this.zzEZ--;
            this.zzEZ = Math.max(0, this.zzEZ);
            if (this.zzEZ == 0) {
                this.zzFc = zzgG().elapsedRealtime();
            }
        }
    }

    Tracker(zze analytics, String trackingId, zzac rateLimiter) {
        super(analytics);
        if (trackingId != null) {
            this.zzvi.put("&tid", trackingId);
        }
        this.zzvi.put("useSecure", "1");
        this.zzvi.put("&a", Integer.toString(new Random().nextInt(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) + 1));
        if (rateLimiter == null) {
            this.zzEM = new zzac("tracking");
        } else {
            this.zzEM = rateLimiter;
        }
        this.zzEN = new zza(analytics);
    }

    private static void zza(Map<String, String> map, Map<String, String> map2) {
        zzv.zzr(map2);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String zzb = zzb((Map.Entry<String, String>) next);
                if (zzb != null) {
                    map2.put(zzb, next.getValue());
                }
            }
        }
    }

    private static boolean zza(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        String value = entry.getValue();
        return key.startsWith("&") && key.length() >= 2;
    }

    private static String zzb(Map.Entry<String, String> entry) {
        if (!zza(entry)) {
            return null;
        }
        return entry.getKey().substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzv.zzr(map2);
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String zzb = zzb((Map.Entry<String, String>) next);
                if (zzb != null && !map2.containsKey(zzb)) {
                    map2.put(zzb, next.getValue());
                }
            }
        }
    }

    private boolean zzgc() {
        return this.zzEO != null;
    }

    static String zzp(Activity activity) {
        zzv.zzr(activity);
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        String stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        if (!TextUtils.isEmpty(stringExtra)) {
            return stringExtra;
        }
        return null;
    }

    public void enableAdvertisingIdCollection(boolean enabled) {
        this.zzEK = enabled;
    }

    public void enableAutoActivityTracking(boolean enabled) {
        this.zzEN.enableAutoActivityTracking(enabled);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            boolean r0 = r3.zzgc()     // Catch:{ all -> 0x0026 }
            if (r0 != r4) goto L_0x0009
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
        L_0x0008:
            return
        L_0x0009:
            if (r4 == 0) goto L_0x0029
            android.content.Context r0 = r3.getContext()     // Catch:{ all -> 0x0026 }
            java.lang.Thread$UncaughtExceptionHandler r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler()     // Catch:{ all -> 0x0026 }
            com.google.android.gms.analytics.ExceptionReporter r2 = new com.google.android.gms.analytics.ExceptionReporter     // Catch:{ all -> 0x0026 }
            r2.<init>(r3, r1, r0)     // Catch:{ all -> 0x0026 }
            r3.zzEO = r2     // Catch:{ all -> 0x0026 }
            com.google.android.gms.analytics.ExceptionReporter r0 = r3.zzEO     // Catch:{ all -> 0x0026 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r0)     // Catch:{ all -> 0x0026 }
            java.lang.String r0 = "Uncaught exceptions will be reported to Google Analytics"
            r3.zzaF(r0)     // Catch:{ all -> 0x0026 }
        L_0x0024:
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            goto L_0x0008
        L_0x0026:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x0026 }
            throw r0
        L_0x0029:
            com.google.android.gms.analytics.ExceptionReporter r0 = r3.zzEO     // Catch:{ all -> 0x0026 }
            java.lang.Thread$UncaughtExceptionHandler r0 = r0.zzfU()     // Catch:{ all -> 0x0026 }
            java.lang.Thread.setDefaultUncaughtExceptionHandler(r0)     // Catch:{ all -> 0x0026 }
            java.lang.String r0 = "Uncaught exceptions will not be reported to Google Analytics"
            r3.zzaF(r0)     // Catch:{ all -> 0x0026 }
            goto L_0x0024
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public String get(String key) {
        zzgR();
        if (TextUtils.isEmpty(key)) {
            return null;
        }
        if (this.zzvi.containsKey(key)) {
            return this.zzvi.get(key);
        }
        if (key.equals("&ul")) {
            return zzal.zza(Locale.getDefault());
        }
        if (key.equals("&cid")) {
            return zzgM().zzhI();
        }
        if (key.equals("&sr")) {
            return zzgP().zziy();
        }
        if (key.equals("&aid")) {
            return zzgO().zzhq().zzqT();
        }
        if (key.equals("&an")) {
            return zzgO().zzhq().zziE();
        }
        if (key.equals("&av")) {
            return zzgO().zzhq().zziG();
        }
        if (key.equals("&aiid")) {
            return zzgO().zzhq().zzus();
        }
        return null;
    }

    public void send(Map<String, String> params) {
        final long currentTimeMillis = zzgG().currentTimeMillis();
        if (zzfT().getAppOptOut()) {
            zzaG("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        final boolean isDryRunEnabled = zzfT().isDryRunEnabled();
        final HashMap hashMap = new HashMap();
        zza(this.zzvi, hashMap);
        zza(params, hashMap);
        final boolean zze = zzal.zze(this.zzvi.get("useSecure"), true);
        zzb(this.zzEL, hashMap);
        this.zzEL.clear();
        final String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzgH().zze(hashMap, "Missing hit type parameter");
            return;
        }
        final String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzgH().zze(hashMap, "Missing tracking id parameter");
            return;
        }
        final boolean zzgd = zzgd();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt(this.zzvi.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzvi.put("&a", Integer.toString(parseInt));
            }
        }
        zzgJ().zze((Runnable) new Runnable() {
            public void run() {
                boolean z = true;
                if (Tracker.this.zzEN.zzge()) {
                    hashMap.put("sc", "start");
                }
                zzal.zzc(hashMap, "cid", Tracker.this.zzfT().getClientId());
                if (hashMap.get("&sf") != null) {
                    double zza = zzal.zza((String) hashMap.get("&sf"), 100.0d);
                    if (zzal.zza(zza, (String) hashMap.get("&cid"))) {
                        Tracker.this.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                        return;
                    }
                }
                com.google.android.gms.analytics.internal.zza zzb = Tracker.this.zzgN();
                if (zzgd) {
                    zzal.zzb((Map<String, String>) hashMap, "ate", zzb.zzgn());
                    zzal.zzb((Map<String, String>) hashMap, "adid", zzb.zzgr());
                } else {
                    hashMap.remove("ate");
                    hashMap.remove("adid");
                }
                zzkp zzhq = Tracker.this.zzgO().zzhq();
                zzal.zzb((Map<String, String>) hashMap, "an", zzhq.zziE());
                zzal.zzb((Map<String, String>) hashMap, "av", zzhq.zziG());
                zzal.zzb((Map<String, String>) hashMap, "aid", zzhq.zzqT());
                zzal.zzb((Map<String, String>) hashMap, "aiid", zzhq.zzus());
                hashMap.put("v", "1");
                hashMap.put("_v", "ma4.5.0");
                zzal.zzb((Map<String, String>) hashMap, "ul", Tracker.this.zzgP().zzix().getLanguage());
                zzal.zzb((Map<String, String>) hashMap, "sr", Tracker.this.zzgP().zziy());
                if ((str.equals("transaction") || str.equals("item")) || Tracker.this.zzEM.zziU()) {
                    long zzaV = zzal.zzaV((String) hashMap.get("ht"));
                    if (zzaV == 0) {
                        zzaV = currentTimeMillis;
                    }
                    if (isDryRunEnabled) {
                        Tracker.this.zzgH().zzc("Dry run enabled. Would have sent hit", new zzaa(Tracker.this, hashMap, zzaV, zze));
                        return;
                    }
                    String str = (String) hashMap.get("cid");
                    HashMap hashMap = new HashMap();
                    zzal.zza((Map<String, String>) hashMap, "uid", (Map<String, String>) hashMap);
                    zzal.zza((Map<String, String>) hashMap, "an", (Map<String, String>) hashMap);
                    zzal.zza((Map<String, String>) hashMap, "aid", (Map<String, String>) hashMap);
                    zzal.zza((Map<String, String>) hashMap, "av", (Map<String, String>) hashMap);
                    zzal.zza((Map<String, String>) hashMap, "aiid", (Map<String, String>) hashMap);
                    String str2 = str2;
                    if (TextUtils.isEmpty((CharSequence) hashMap.get("adid"))) {
                        z = false;
                    }
                    hashMap.put("_s", String.valueOf(Tracker.this.zzfZ().zza(new zzg(0, str, str2, z, 0, hashMap))));
                    Tracker.this.zzfZ().zza(new zzaa(Tracker.this, hashMap, zzaV, zze));
                    return;
                }
                Tracker.this.zzgH().zze(hashMap, "Too many hits sent too quickly, rate limiting invoked");
            }
        });
    }

    public void set(String key, String value) {
        zzv.zzb(key, (Object) "Key should be non-null");
        if (!TextUtils.isEmpty(key)) {
            this.zzvi.put(key, value);
        }
    }

    public void setAnonymizeIp(boolean anonymize) {
        set("&aip", zzal.zzH(anonymize));
    }

    public void setAppId(String appId) {
        set("&aid", appId);
    }

    public void setAppInstallerId(String appInstallerId) {
        set("&aiid", appInstallerId);
    }

    public void setAppName(String appName) {
        set("&an", appName);
    }

    public void setAppVersion(String appVersion) {
        set("&av", appVersion);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            String queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                Uri parse = Uri.parse("http://hostname/?" + queryParameter);
                String queryParameter2 = parse.getQueryParameter("utm_id");
                if (queryParameter2 != null) {
                    this.zzEL.put("&ci", queryParameter2);
                }
                String queryParameter3 = parse.getQueryParameter("anid");
                if (queryParameter3 != null) {
                    this.zzEL.put("&anid", queryParameter3);
                }
                String queryParameter4 = parse.getQueryParameter("utm_campaign");
                if (queryParameter4 != null) {
                    this.zzEL.put("&cn", queryParameter4);
                }
                String queryParameter5 = parse.getQueryParameter("utm_content");
                if (queryParameter5 != null) {
                    this.zzEL.put("&cc", queryParameter5);
                }
                String queryParameter6 = parse.getQueryParameter("utm_medium");
                if (queryParameter6 != null) {
                    this.zzEL.put("&cm", queryParameter6);
                }
                String queryParameter7 = parse.getQueryParameter("utm_source");
                if (queryParameter7 != null) {
                    this.zzEL.put("&cs", queryParameter7);
                }
                String queryParameter8 = parse.getQueryParameter("utm_term");
                if (queryParameter8 != null) {
                    this.zzEL.put("&ck", queryParameter8);
                }
                String queryParameter9 = parse.getQueryParameter("dclid");
                if (queryParameter9 != null) {
                    this.zzEL.put("&dclid", queryParameter9);
                }
                String queryParameter10 = parse.getQueryParameter("gclid");
                if (queryParameter10 != null) {
                    this.zzEL.put("&gclid", queryParameter10);
                }
                String queryParameter11 = parse.getQueryParameter("aclid");
                if (queryParameter11 != null) {
                    this.zzEL.put("&aclid", queryParameter11);
                }
            }
        }
    }

    public void setClientId(String clientId) {
        set("&cid", clientId);
    }

    public void setEncoding(String encoding) {
        set("&de", encoding);
    }

    public void setHostname(String hostname) {
        set("&dh", hostname);
    }

    public void setLanguage(String language) {
        set("&ul", language);
    }

    public void setLocation(String location) {
        set("&dl", location);
    }

    public void setPage(String page) {
        set("&dp", page);
    }

    public void setReferrer(String referrer) {
        set("&dr", referrer);
    }

    public void setSampleRate(double sampleRate) {
        set("&sf", Double.toString(sampleRate));
    }

    public void setScreenColors(String screenColors) {
        set("&sd", screenColors);
    }

    public void setScreenName(String screenName) {
        set("&cd", screenName);
    }

    public void setScreenResolution(int width, int height) {
        if (width >= 0 || height >= 0) {
            set("&sr", width + "x" + height);
        } else {
            zzaI("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long sessionTimeout) {
        this.zzEN.setSessionTimeout(1000 * sessionTimeout);
    }

    public void setTitle(String title) {
        set("&dt", title);
    }

    public void setUseSecure(boolean useSecure) {
        set("useSecure", zzal.zzH(useSecure));
    }

    public void setViewportSize(String viewportSize) {
        set("&vp", viewportSize);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzak zzak) {
        zzaF("Loading Tracker config values");
        this.zzEP = zzak;
        if (this.zzEP.zzjr()) {
            String zzjs = this.zzEP.zzjs();
            set("&tid", zzjs);
            zza("trackingId loaded", zzjs);
        }
        if (this.zzEP.zzjt()) {
            String d = Double.toString(this.zzEP.zzju());
            set("&sf", d);
            zza("Sample frequency loaded", d);
        }
        if (this.zzEP.zzjv()) {
            int sessionTimeout = this.zzEP.getSessionTimeout();
            setSessionTimeout((long) sessionTimeout);
            zza("Session timeout loaded", Integer.valueOf(sessionTimeout));
        }
        if (this.zzEP.zzjw()) {
            boolean zzjx = this.zzEP.zzjx();
            enableAutoActivityTracking(zzjx);
            zza("Auto activity tracking loaded", Boolean.valueOf(zzjx));
        }
        if (this.zzEP.zzjy()) {
            boolean zzjz = this.zzEP.zzjz();
            if (zzjz) {
                set("&aip", "1");
            }
            zza("Anonymize ip loaded", Boolean.valueOf(zzjz));
        }
        enableExceptionReporting(this.zzEP.zzjA());
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        this.zzEN.zzfV();
        String zziE = zzga().zziE();
        if (zziE != null) {
            set("&an", zziE);
        }
        String zziG = zzga().zziG();
        if (zziG != null) {
            set("&av", zziG);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean zzgd() {
        return this.zzEK;
    }
}
