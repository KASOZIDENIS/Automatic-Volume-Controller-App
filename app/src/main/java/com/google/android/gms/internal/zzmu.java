package com.google.android.gms.internal;

import com.google.android.gms.tagmanager.zzbg;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

class zzmu implements zzmw {
    private HttpClient zzaHF;

    zzmu() {
    }

    private InputStream zza(HttpClient httpClient, HttpResponse httpResponse) throws IOException {
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        if (statusCode == 200) {
            zzbg.zzam("Success response");
            return httpResponse.getEntity().getContent();
        }
        String str = "Bad response: " + statusCode;
        if (statusCode == 404) {
            throw new FileNotFoundException(str);
        }
        throw new IOException(str);
    }

    private void zza(HttpClient httpClient) {
        if (httpClient != null && httpClient.getConnectionManager() != null) {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public void close() {
        zza(this.zzaHF);
    }

    public InputStream zzev(String str) throws IOException {
        this.zzaHF = zzyL();
        return zza(this.zzaHF, this.zzaHF.execute(new HttpGet(str)));
    }

    /* access modifiers changed from: package-private */
    public HttpClient zzyL() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
        return new DefaultHttpClient(basicHttpParams);
    }
}
