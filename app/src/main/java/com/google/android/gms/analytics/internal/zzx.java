package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.internal.zzhc;

public final class zzx {
    public static zza<Integer> zzHA = zza.zzd("analytics.max_hits_per_request.k", 20);
    public static zza<Integer> zzHB = zza.zzd("analytics.max_hit_length.k", 8192);
    public static zza<Integer> zzHC = zza.zzd("analytics.max_post_length.k", 8192);
    public static zza<Integer> zzHD = zza.zzd("analytics.max_batch_post_length", 8192);
    public static zza<String> zzHE = zza.zzl("analytics.fallback_responses.k", "404,502");
    public static zza<Integer> zzHF = zza.zzd("analytics.batch_retry_interval.seconds.k", 3600);
    public static zza<Long> zzHG = zza.zzb("analytics.service_monitor_interval", 86400000);
    public static zza<Integer> zzHH = zza.zzd("analytics.http_connection.connect_timeout_millis", 60000);
    public static zza<Integer> zzHI = zza.zzd("analytics.http_connection.read_timeout_millis", 61000);
    public static zza<Long> zzHJ = zza.zzb("analytics.campaigns.time_limit", 86400000);
    public static zza<String> zzHK = zza.zzl("analytics.first_party_experiment_id", "");
    public static zza<Integer> zzHL = zza.zzd("analytics.first_party_experiment_variant", 0);
    public static zza<Boolean> zzHM = zza.zzd("analytics.test.disable_receiver", false);
    public static zza<Long> zzHN = zza.zza("analytics.service_client.idle_disconnect_millis", 10000, 10000);
    public static zza<Long> zzHO = zza.zzb("analytics.service_client.connect_timeout_millis", 5000);
    public static zza<Long> zzHP = zza.zzb("analytics.service_client.second_connect_delay_millis", 5000);
    public static zza<Long> zzHQ = zza.zzb("analytics.service_client.unexpected_reconnect_millis", 60000);
    public static zza<Long> zzHR = zza.zzb("analytics.service_client.reconnect_throttle_millis", 1800000);
    public static zza<Long> zzHS = zza.zzb("analytics.monitoring.sample_period_millis", 86400000);
    public static zza<Long> zzHT = zza.zzb("analytics.initialization_warning_threshold", 5000);
    public static zza<Boolean> zzHd = zza.zzd("analytics.service_enabled", false);
    public static zza<Boolean> zzHe = zza.zzd("analytics.service_client_enabled", true);
    public static zza<String> zzHf = zza.zzd("analytics.log_tag", "GAv4", "GAv4-SVC");
    public static zza<Long> zzHg = zza.zzb("analytics.max_tokens", 60);
    public static zza<Float> zzHh = zza.zza("analytics.tokens_per_sec", 0.5f);
    public static zza<Integer> zzHi = zza.zza("analytics.max_stored_hits", 2000, 20000);
    public static zza<Integer> zzHj = zza.zzd("analytics.max_stored_hits_per_app", 2000);
    public static zza<Integer> zzHk = zza.zzd("analytics.max_stored_properties_per_app", 100);
    public static zza<Long> zzHl = zza.zza("analytics.local_dispatch_millis", 1800000, 120000);
    public static zza<Long> zzHm = zza.zza("analytics.initial_local_dispatch_millis", 5000, 5000);
    public static zza<Long> zzHn = zza.zzb("analytics.min_local_dispatch_millis", 120000);
    public static zza<Long> zzHo = zza.zzb("analytics.max_local_dispatch_millis", 7200000);
    public static zza<Long> zzHp = zza.zzb("analytics.dispatch_alarm_millis", 7200000);
    public static zza<Long> zzHq = zza.zzb("analytics.max_dispatch_alarm_millis", 32400000);
    public static zza<Integer> zzHr = zza.zzd("analytics.max_hits_per_dispatch", 20);
    public static zza<Integer> zzHs = zza.zzd("analytics.max_hits_per_batch", 20);
    public static zza<String> zzHt = zza.zzl("analytics.insecure_host", "http://www.google-analytics.com");
    public static zza<String> zzHu = zza.zzl("analytics.secure_host", "https://ssl.google-analytics.com");
    public static zza<String> zzHv = zza.zzl("analytics.simple_endpoint", "/collect");
    public static zza<String> zzHw = zza.zzl("analytics.batching_endpoint", "/batch");
    public static zza<Integer> zzHx = zza.zzd("analytics.max_get_length", 2036);
    public static zza<String> zzHy = zza.zzd("analytics.batching_strategy.k", zzl.BATCH_BY_COUNT.name(), zzl.BATCH_BY_COUNT.name());
    public static zza<String> zzHz = zza.zzl("analytics.compression_strategy.k", zzn.GZIP.name());

    public static final class zza<V> {
        private final V zzHU;
        private final zzhc<V> zzHV;
        private V zzHW;

        private zza(zzhc<V> zzhc, V v) {
            zzv.zzr(zzhc);
            this.zzHV = zzhc;
            this.zzHU = v;
        }

        static zza<Float> zza(String str, float f) {
            return zza(str, f, f);
        }

        static zza<Float> zza(String str, float f, float f2) {
            return new zza<>(zzhc.zza(str, Float.valueOf(f2)), Float.valueOf(f));
        }

        static zza<Integer> zza(String str, int i, int i2) {
            return new zza<>(zzhc.zza(str, Integer.valueOf(i2)), Integer.valueOf(i));
        }

        static zza<Long> zza(String str, long j, long j2) {
            return new zza<>(zzhc.zza(str, Long.valueOf(j2)), Long.valueOf(j));
        }

        static zza<Boolean> zza(String str, boolean z, boolean z2) {
            return new zza<>(zzhc.zzg(str, z2), Boolean.valueOf(z));
        }

        static zza<Long> zzb(String str, long j) {
            return zza(str, j, j);
        }

        static zza<Integer> zzd(String str, int i) {
            return zza(str, i, i);
        }

        static zza<String> zzd(String str, String str2, String str3) {
            return new zza<>(zzhc.zzr(str, str3), str2);
        }

        static zza<Boolean> zzd(String str, boolean z) {
            return zza(str, z, z);
        }

        static zza<String> zzl(String str, String str2) {
            return zzd(str, str2, str2);
        }

        public V get() {
            return this.zzHW != null ? this.zzHW : (!zzd.zzSV || !zzhc.isInitialized()) ? this.zzHU : this.zzHV.zzlk();
        }
    }
}
