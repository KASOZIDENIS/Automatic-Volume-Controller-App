package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;

class zzcx implements zzac {
    private final String zzaFH = zza("GoogleTagManager", "4.00", Build.VERSION.RELEASE, zzc(Locale.getDefault()), Build.MODEL, Build.ID);
    private final HttpClient zzaFI;
    private zza zzaFJ;
    private final Context zzaFq;

    public interface zza {
        void zza(zzaq zzaq);

        void zzb(zzaq zzaq);

        void zzc(zzaq zzaq);
    }

    zzcx(HttpClient httpClient, Context context, zza zza2) {
        this.zzaFq = context.getApplicationContext();
        this.zzaFI = httpClient;
        this.zzaFJ = zza2;
    }

    private void zza(HttpEntityEnclosingRequest httpEntityEnclosingRequest) {
        int available;
        StringBuffer stringBuffer = new StringBuffer();
        for (Header obj : httpEntityEnclosingRequest.getAllHeaders()) {
            stringBuffer.append(obj.toString()).append("\n");
        }
        stringBuffer.append(httpEntityEnclosingRequest.getRequestLine().toString()).append("\n");
        if (httpEntityEnclosingRequest.getEntity() != null) {
            try {
                InputStream content = httpEntityEnclosingRequest.getEntity().getContent();
                if (content != null && (available = content.available()) > 0) {
                    byte[] bArr = new byte[available];
                    content.read(bArr);
                    stringBuffer.append("POST:\n");
                    stringBuffer.append(new String(bArr)).append("\n");
                }
            } catch (IOException e) {
                zzbg.zzam("Error Writing hit to log...");
            }
        }
        zzbg.zzam(stringBuffer.toString());
    }

    static String zzc(Locale locale) {
        if (locale == null || locale.getLanguage() == null || locale.getLanguage().length() == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(locale.getLanguage().toLowerCase());
        if (!(locale.getCountry() == null || locale.getCountry().length() == 0)) {
            sb.append("-").append(locale.getCountry().toLowerCase());
        }
        return sb.toString();
    }

    private HttpEntityEnclosingRequest zzc(URL url) {
        URISyntaxException e;
        BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest;
        try {
            basicHttpEntityEnclosingRequest = new BasicHttpEntityEnclosingRequest("GET", url.toURI().toString());
            try {
                basicHttpEntityEnclosingRequest.addHeader("User-Agent", this.zzaFH);
            } catch (URISyntaxException e2) {
                e = e2;
                zzbg.zzan("Exception sending hit: " + e.getClass().getSimpleName());
                zzbg.zzan(e.getMessage());
                return basicHttpEntityEnclosingRequest;
            }
        } catch (URISyntaxException e3) {
            URISyntaxException uRISyntaxException = e3;
            basicHttpEntityEnclosingRequest = null;
            e = uRISyntaxException;
            zzbg.zzan("Exception sending hit: " + e.getClass().getSimpleName());
            zzbg.zzan(e.getMessage());
            return basicHttpEntityEnclosingRequest;
        }
        return basicHttpEntityEnclosingRequest;
    }

    /* access modifiers changed from: package-private */
    public String zza(String str, String str2, String str3, String str4, String str5, String str6) {
        return String.format("%s/%s (Linux; U; Android %s; %s; %s Build/%s)", new Object[]{str, str2, str3, str4, str5, str6});
    }

    /* access modifiers changed from: package-private */
    public URL zzd(zzaq zzaq) {
        try {
            return new URL(zzaq.zzwW());
        } catch (MalformedURLException e) {
            zzbg.zzak("Error trying to parse the GTM url.");
            return null;
        }
    }

    public void zzs(List<zzaq> list) {
        boolean z;
        int min = Math.min(list.size(), 40);
        boolean z2 = true;
        int i = 0;
        while (i < min) {
            zzaq zzaq = list.get(i);
            URL zzd = zzd(zzaq);
            if (zzd == null) {
                zzbg.zzan("No destination: discarding hit.");
                this.zzaFJ.zzb(zzaq);
                z = z2;
            } else {
                HttpEntityEnclosingRequest zzc = zzc(zzd);
                if (zzc == null) {
                    this.zzaFJ.zzb(zzaq);
                    z = z2;
                } else {
                    HttpHost httpHost = new HttpHost(zzd.getHost(), zzd.getPort(), zzd.getProtocol());
                    zzc.addHeader("Host", httpHost.toHostString());
                    zza(zzc);
                    if (z2) {
                        try {
                            zzbl.zzas(this.zzaFq);
                            z2 = false;
                        } catch (ClientProtocolException e) {
                            zzbg.zzan("ClientProtocolException sending hit; discarding hit...");
                            this.zzaFJ.zzb(zzaq);
                            z = z2;
                        } catch (IOException e2) {
                            zzbg.zzan("Exception sending hit: " + e2.getClass().getSimpleName());
                            zzbg.zzan(e2.getMessage());
                            this.zzaFJ.zzc(zzaq);
                            z = z2;
                        }
                    }
                    HttpResponse execute = this.zzaFI.execute(httpHost, zzc);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    HttpEntity entity = execute.getEntity();
                    if (entity != null) {
                        entity.consumeContent();
                    }
                    if (statusCode != 200) {
                        zzbg.zzan("Bad response: " + execute.getStatusLine().getStatusCode());
                        this.zzaFJ.zzc(zzaq);
                    } else {
                        this.zzaFJ.zza(zzaq);
                    }
                    z = z2;
                }
            }
            i++;
            z2 = z;
        }
    }

    public boolean zzwN() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.zzaFq.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        zzbg.zzam("...no network connectivity");
        return false;
    }
}
