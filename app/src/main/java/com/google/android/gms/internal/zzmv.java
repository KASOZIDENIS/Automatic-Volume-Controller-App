package com.google.android.gms.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class zzmv implements zzmw {
    private HttpURLConnection zzaHG;

    zzmv() {
    }

    private InputStream zzb(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            return httpURLConnection.getInputStream();
        }
        String str = "Bad response: " + responseCode;
        if (responseCode == 404) {
            throw new FileNotFoundException(str);
        }
        throw new IOException(str);
    }

    private void zzc(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    public void close() {
        zzc(this.zzaHG);
    }

    public InputStream zzev(String str) throws IOException {
        this.zzaHG = zzew(str);
        return zzb(this.zzaHG);
    }

    /* access modifiers changed from: package-private */
    public HttpURLConnection zzew(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setConnectTimeout(20000);
        return httpURLConnection;
    }
}
