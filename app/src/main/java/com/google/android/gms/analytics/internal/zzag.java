package com.google.android.gms.analytics.internal;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import com.google.android.gms.common.internal.zzv;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

class zzag extends zzd {
    /* access modifiers changed from: private */
    public static final byte[] zzIw = "\n".getBytes();
    private final String zzBW = zza("GoogleAnalytics", "4.5.0", Build.VERSION.RELEASE, zzal.zza(Locale.getDefault()), Build.MODEL, Build.ID);
    private final zzai zzIv;

    private class zza {
        private int zzIx;
        private ByteArrayOutputStream zzIy = new ByteArrayOutputStream();

        public zza() {
        }

        public byte[] getPayload() {
            return this.zzIy.toByteArray();
        }

        public boolean zzj(zzaa zzaa) {
            zzv.zzr(zzaa);
            if (this.zzIx + 1 > zzag.this.zzgI().zzib()) {
                return false;
            }
            String zza = zzag.this.zza(zzaa, false);
            if (zza == null) {
                zzag.this.zzgH().zza(zzaa, "Error formatting hit");
                return true;
            }
            byte[] bytes = zza.getBytes();
            int length = bytes.length;
            if (length > zzag.this.zzgI().zzhT()) {
                zzag.this.zzgH().zza(zzaa, "Hit size exceeds the maximum size limit");
                return true;
            }
            if (this.zzIy.size() > 0) {
                length++;
            }
            if (length + this.zzIy.size() > zzag.this.zzgI().zzhV()) {
                return false;
            }
            try {
                if (this.zzIy.size() > 0) {
                    this.zzIy.write(zzag.zzIw);
                }
                this.zzIy.write(bytes);
                this.zzIx++;
                return true;
            } catch (IOException e) {
                zzag.this.zze("Failed to write payload when batching hits", e);
                return true;
            }
        }

        public int zzjc() {
            return this.zzIx;
        }
    }

    zzag(zze zze) {
        super(zze);
        this.zzIv = new zzai(zze.zzgG());
    }

    private int zza(URL url) {
        int i;
        zzv.zzr(url);
        zzb("GET request", url);
        HttpURLConnection httpURLConnection = null;
        try {
            HttpURLConnection zzb = zzb(url);
            zzb.connect();
            zza(zzb);
            i = zzb.getResponseCode();
            if (i == 200) {
                zzfZ().zzgB();
            }
            zzb("GET status", Integer.valueOf(i));
            if (zzb != null) {
                zzb.disconnect();
            }
        } catch (IOException e) {
            zzd("Network GET connection error", e);
            i = 0;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
        return i;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v9, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: type inference failed for: r2v2, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v4, types: [java.net.HttpURLConnection] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r2v8 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x006e A[SYNTHETIC, Splitter:B:26:0x006e] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0082 A[SYNTHETIC, Splitter:B:35:0x0082] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0087  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zza(java.net.URL r6, byte[] r7) {
        /*
            r5 = this;
            r1 = 0
            com.google.android.gms.common.internal.zzv.zzr(r6)
            com.google.android.gms.common.internal.zzv.zzr(r7)
            java.lang.String r0 = "POST bytes, url"
            int r2 = r7.length
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r5.zzb(r0, r2, r6)
            boolean r0 = r5.zzgQ()
            if (r0 == 0) goto L_0x0021
            java.lang.String r0 = "Post payload\n"
            java.lang.String r2 = new java.lang.String
            r2.<init>(r7)
            r5.zza(r0, r2)
        L_0x0021:
            java.net.HttpURLConnection r2 = r5.zzb(r6)     // Catch:{ IOException -> 0x0064, all -> 0x007e }
            r0 = 1
            r2.setDoOutput(r0)     // Catch:{ IOException -> 0x0094 }
            int r0 = r7.length     // Catch:{ IOException -> 0x0094 }
            r2.setFixedLengthStreamingMode(r0)     // Catch:{ IOException -> 0x0094 }
            r2.connect()     // Catch:{ IOException -> 0x0094 }
            java.io.OutputStream r1 = r2.getOutputStream()     // Catch:{ IOException -> 0x0094 }
            r1.write(r7)     // Catch:{ IOException -> 0x0094 }
            r5.zza((java.net.HttpURLConnection) r2)     // Catch:{ IOException -> 0x0094 }
            int r0 = r2.getResponseCode()     // Catch:{ IOException -> 0x0094 }
            r3 = 200(0xc8, float:2.8E-43)
            if (r0 != r3) goto L_0x0049
            com.google.android.gms.analytics.internal.zzb r3 = r5.zzfZ()     // Catch:{ IOException -> 0x0094 }
            r3.zzgB()     // Catch:{ IOException -> 0x0094 }
        L_0x0049:
            java.lang.String r3 = "POST status"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x0094 }
            r5.zzb(r3, r4)     // Catch:{ IOException -> 0x0094 }
            if (r1 == 0) goto L_0x0057
            r1.close()     // Catch:{ IOException -> 0x005d }
        L_0x0057:
            if (r2 == 0) goto L_0x005c
            r2.disconnect()
        L_0x005c:
            return r0
        L_0x005d:
            r1 = move-exception
            java.lang.String r3 = "Error closing http post connection output stream"
            r5.zze(r3, r1)
            goto L_0x0057
        L_0x0064:
            r0 = move-exception
            r2 = r1
        L_0x0066:
            java.lang.String r3 = "Network POST connection error"
            r5.zzd(r3, r0)     // Catch:{ all -> 0x0092 }
            r0 = 0
            if (r1 == 0) goto L_0x0071
            r1.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0071:
            if (r2 == 0) goto L_0x005c
            r2.disconnect()
            goto L_0x005c
        L_0x0077:
            r1 = move-exception
            java.lang.String r3 = "Error closing http post connection output stream"
            r5.zze(r3, r1)
            goto L_0x0071
        L_0x007e:
            r0 = move-exception
            r2 = r1
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()     // Catch:{ IOException -> 0x008b }
        L_0x0085:
            if (r2 == 0) goto L_0x008a
            r2.disconnect()
        L_0x008a:
            throw r0
        L_0x008b:
            r1 = move-exception
            java.lang.String r3 = "Error closing http post connection output stream"
            r5.zze(r3, r1)
            goto L_0x0085
        L_0x0092:
            r0 = move-exception
            goto L_0x0080
        L_0x0094:
            r0 = move-exception
            goto L_0x0066
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzag.zza(java.net.URL, byte[]):int");
    }

    private static String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    private void zza(StringBuilder sb, String str, String str2) throws UnsupportedEncodingException {
        if (sb.length() != 0) {
            sb.append('&');
        }
        sb.append(URLEncoder.encode(str, "UTF-8"));
        sb.append('=');
        sb.append(URLEncoder.encode(str2, "UTF-8"));
    }

    private void zza(HttpURLConnection httpURLConnection) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = httpURLConnection.getInputStream();
            do {
            } while (inputStream.read(new byte[1024]) > 0);
            if (inputStream != null) {
                try {
                } catch (IOException e) {
                    zze("Error closing http connection input stream", e);
                }
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e2) {
                    zze("Error closing http connection input stream", e2);
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00af A[SYNTHETIC, Splitter:B:35:0x00af] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c3 A[SYNTHETIC, Splitter:B:44:0x00c3] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00c8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int zzb(java.net.URL r9, byte[] r10) {
        /*
            r8 = this;
            r1 = 0
            com.google.android.gms.common.internal.zzv.zzr(r9)
            com.google.android.gms.common.internal.zzv.zzr(r10)
            byte[] r0 = zze(r10)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.String r2 = "POST compressed size, ratio %, url"
            int r3 = r0.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r4 = 100
            int r6 = r0.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            long r4 = r4 * r6
            int r6 = r10.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            long r6 = (long) r6     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            long r4 = r4 / r6
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r8.zza(r2, r3, r4, r9)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            int r2 = r0.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            int r3 = r10.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            if (r2 <= r3) goto L_0x0034
            java.lang.String r2 = "Compressed payload is larger then uncompressed. compressed, uncompressed"
            int r3 = r0.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            int r4 = r10.length     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r8.zzc(r2, r3, r4)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
        L_0x0034:
            boolean r2 = r8.zzgQ()     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            if (r2 == 0) goto L_0x0057
            java.lang.String r2 = "Post payload"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r3.<init>()     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.String r4 = "\n"
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r4.<init>(r10)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            java.lang.String r3 = r3.toString()     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r8.zza(r2, r3)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
        L_0x0057:
            java.net.HttpURLConnection r3 = r8.zzb(r9)     // Catch:{ IOException -> 0x00a5, all -> 0x00bf }
            r2 = 1
            r3.setDoOutput(r2)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            java.lang.String r2 = "Content-Encoding"
            java.lang.String r4 = "gzip"
            r3.addRequestProperty(r2, r4)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            int r2 = r0.length     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r3.setFixedLengthStreamingMode(r2)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r3.connect()     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            java.io.OutputStream r2 = r3.getOutputStream()     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r2.write(r0)     // Catch:{ IOException -> 0x00de, all -> 0x00d5 }
            r2.close()     // Catch:{ IOException -> 0x00de, all -> 0x00d5 }
            r2 = 0
            r8.zza((java.net.HttpURLConnection) r3)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            int r0 = r3.getResponseCode()     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r0 != r4) goto L_0x008a
            com.google.android.gms.analytics.internal.zzb r4 = r8.zzfZ()     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r4.zzgB()     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
        L_0x008a:
            java.lang.String r4 = "POST status"
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            r8.zzb(r4, r5)     // Catch:{ IOException -> 0x00db, all -> 0x00d3 }
            if (r1 == 0) goto L_0x0098
            r2.close()     // Catch:{ IOException -> 0x009e }
        L_0x0098:
            if (r3 == 0) goto L_0x009d
            r3.disconnect()
        L_0x009d:
            return r0
        L_0x009e:
            r1 = move-exception
            java.lang.String r2 = "Error closing http compressed post connection output stream"
            r8.zze(r2, r1)
            goto L_0x0098
        L_0x00a5:
            r0 = move-exception
            r2 = r1
        L_0x00a7:
            java.lang.String r3 = "Network compressed POST connection error"
            r8.zzd(r3, r0)     // Catch:{ all -> 0x00d8 }
            r0 = 0
            if (r1 == 0) goto L_0x00b2
            r1.close()     // Catch:{ IOException -> 0x00b8 }
        L_0x00b2:
            if (r2 == 0) goto L_0x009d
            r2.disconnect()
            goto L_0x009d
        L_0x00b8:
            r1 = move-exception
            java.lang.String r3 = "Error closing http compressed post connection output stream"
            r8.zze(r3, r1)
            goto L_0x00b2
        L_0x00bf:
            r0 = move-exception
            r3 = r1
        L_0x00c1:
            if (r1 == 0) goto L_0x00c6
            r1.close()     // Catch:{ IOException -> 0x00cc }
        L_0x00c6:
            if (r3 == 0) goto L_0x00cb
            r3.disconnect()
        L_0x00cb:
            throw r0
        L_0x00cc:
            r1 = move-exception
            java.lang.String r2 = "Error closing http compressed post connection output stream"
            r8.zze(r2, r1)
            goto L_0x00c6
        L_0x00d3:
            r0 = move-exception
            goto L_0x00c1
        L_0x00d5:
            r0 = move-exception
            r1 = r2
            goto L_0x00c1
        L_0x00d8:
            r0 = move-exception
            r3 = r2
            goto L_0x00c1
        L_0x00db:
            r0 = move-exception
            r2 = r3
            goto L_0x00a7
        L_0x00de:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L_0x00a7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzag.zzb(java.net.URL, byte[]):int");
    }

    private URL zzb(zzaa zzaa, String str) {
        try {
            return new URL(zzaa.zziR() ? zzgI().zzid() + zzgI().zzif() + "?" + str : zzgI().zzie() + zzgI().zzif() + "?" + str);
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private static byte[] zze(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private boolean zzg(zzaa zzaa) {
        zzv.zzr(zzaa);
        String zza2 = zza(zzaa, !zzaa.zziR());
        if (zza2 == null) {
            zzgH().zza(zzaa, "Error formatting hit for upload");
            return true;
        } else if (zza2.length() <= zzgI().zzhS()) {
            URL zzb = zzb(zzaa, zza2);
            if (zzb != null) {
                return zza(zzb) == 200;
            }
            zzaJ("Failed to build collect GET endpoint url");
            return false;
        } else {
            String zza3 = zza(zzaa, false);
            if (zza3 == null) {
                zzgH().zza(zzaa, "Error formatting hit for POST upload");
                return true;
            }
            byte[] bytes = zza3.getBytes();
            if (bytes.length > zzgI().zzhU()) {
                zzgH().zza(zzaa, "Hit payload exceeds size limit");
                return true;
            }
            URL zzh = zzh(zzaa);
            if (zzh != null) {
                return zza(zzh, bytes) == 200;
            }
            zzaJ("Failed to build collect POST endpoint url");
            return false;
        }
    }

    private URL zzh(zzaa zzaa) {
        try {
            return new URL(zzaa.zziR() ? zzgI().zzid() + zzgI().zzif() : zzgI().zzie() + zzgI().zzif());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    private String zzi(zzaa zzaa) {
        return String.valueOf(zzaa.zziO());
    }

    private URL zzja() {
        try {
            return new URL(zzgI().zzid() + zzgI().zzig());
        } catch (MalformedURLException e) {
            zze("Error trying to parse the hardcoded host url", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public String zza(zzaa zzaa, boolean z) {
        zzv.zzr(zzaa);
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : zzaa.zzhe().entrySet()) {
                String str = (String) next.getKey();
                if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str) && !"z".equals(str) && !"_gmsv".equals(str)) {
                    zza(sb, str, (String) next.getValue());
                }
            }
            zza(sb, "ht", String.valueOf(zzaa.zziP()));
            zza(sb, "qt", String.valueOf(zzgG().currentTimeMillis() - zzaa.zziP()));
            if (zzgI().zzhP()) {
                zza(sb, "_gmsv", zzfZ().zzgy());
            }
            if (z) {
                long zziS = zzaa.zziS();
                zza(sb, "z", zziS != 0 ? String.valueOf(zziS) : zzi(zzaa));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            zze("Failed to encode name or value", e);
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public List<Long> zza(List<zzaa> list, boolean z) {
        zzv.zzQ(!list.isEmpty());
        zza("Uploading batched hits. compression, count", Boolean.valueOf(z), Integer.valueOf(list.size()));
        zza zza2 = new zza();
        ArrayList arrayList = new ArrayList();
        for (zzaa next : list) {
            if (!zza2.zzj(next)) {
                break;
            }
            arrayList.add(Long.valueOf(next.zziO()));
        }
        if (zza2.zzjc() == 0) {
            return arrayList;
        }
        URL zzja = zzja();
        if (zzja == null) {
            zzaJ("Failed to build batching endpoint url");
            return Collections.emptyList();
        }
        int zzb = z ? zzb(zzja, zza2.getPayload()) : zza(zzja, zza2.getPayload());
        if (200 == zzb) {
            zza("Batched upload completed. Hits batched", Integer.valueOf(zza2.zzjc()));
            return arrayList;
        }
        zza("Network error uploading hits. status code", Integer.valueOf(zzb));
        if (zzgI().zzij().contains(Integer.valueOf(zzb))) {
            zzaI("Server instructed the client to stop batching");
            this.zzIv.start();
        }
        return Collections.emptyList();
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection zzb(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        if (!(openConnection instanceof HttpURLConnection)) {
            throw new IOException("Failed to obtain http connection");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) openConnection;
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setConnectTimeout(zzgI().zzis());
        httpURLConnection.setReadTimeout(zzgI().zzit());
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setRequestProperty("User-Agent", this.zzBW);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    public List<Long> zzg(List<zzaa> list) {
        boolean z;
        boolean z2 = true;
        zzgF();
        zzgR();
        zzv.zzr(list);
        if (zzgI().zzij().isEmpty() || !this.zzIv.zzt(zzgI().zzic() * 1000)) {
            z2 = false;
            z = false;
        } else {
            z = zzgI().zzih() != zzl.NONE;
            if (zzgI().zzii() != zzn.GZIP) {
                z2 = false;
            }
        }
        return z ? zza(list, z2) : zzh(list);
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        zza("Network initialized. User agent", this.zzBW);
    }

    /* access modifiers changed from: package-private */
    public List<Long> zzh(List<zzaa> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (zzaa next : list) {
            if (zzg(next)) {
                arrayList.add(Long.valueOf(next.zziO()));
                if (arrayList.size() >= zzgI().zzia()) {
                    break;
                }
            } else {
                break;
            }
        }
        return arrayList;
    }

    public boolean zziZ() {
        NetworkInfo networkInfo;
        zzgF();
        zzgR();
        try {
            networkInfo = ((ConnectivityManager) getContext().getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (SecurityException e) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        zzaF("No network connectivity");
        return false;
    }
}
