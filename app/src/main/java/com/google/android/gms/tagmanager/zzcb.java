package com.google.android.gms.tagmanager;

import android.net.Uri;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

class zzcb {
    private static zzcb zzaEq;
    private volatile String zzaCk;
    private volatile zza zzaEr;
    private volatile String zzaEs;
    private volatile String zzaEt;

    enum zza {
        NONE,
        CONTAINER,
        CONTAINER_DEBUG
    }

    zzcb() {
        clear();
    }

    private String zzdS(String str) {
        return str.split("&")[0].split("=")[1];
    }

    private String zzm(Uri uri) {
        return uri.getQuery().replace("&gtm_debug=x", "");
    }

    static zzcb zzxl() {
        zzcb zzcb;
        synchronized (zzcb.class) {
            if (zzaEq == null) {
                zzaEq = new zzcb();
            }
            zzcb = zzaEq;
        }
        return zzcb;
    }

    /* access modifiers changed from: package-private */
    public void clear() {
        this.zzaEr = zza.NONE;
        this.zzaEs = null;
        this.zzaCk = null;
        this.zzaEt = null;
    }

    /* access modifiers changed from: package-private */
    public String getContainerId() {
        return this.zzaCk;
    }

    /* access modifiers changed from: package-private */
    public synchronized boolean zzl(Uri uri) {
        boolean z = true;
        synchronized (this) {
            try {
                String decode = URLDecoder.decode(uri.toString(), "UTF-8");
                if (decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_auth=\\S+&gtm_preview=\\d+(&gtm_debug=x)?$")) {
                    zzbg.zzam("Container preview url: " + decode);
                    if (decode.matches(".*?&gtm_debug=x$")) {
                        this.zzaEr = zza.CONTAINER_DEBUG;
                    } else {
                        this.zzaEr = zza.CONTAINER;
                    }
                    this.zzaEt = zzm(uri);
                    if (this.zzaEr == zza.CONTAINER || this.zzaEr == zza.CONTAINER_DEBUG) {
                        this.zzaEs = "/r?" + this.zzaEt;
                    }
                    this.zzaCk = zzdS(this.zzaEt);
                } else if (!decode.matches("^tagmanager.c.\\S+:\\/\\/preview\\/p\\?id=\\S+&gtm_preview=$")) {
                    zzbg.zzan("Invalid preview uri: " + decode);
                    z = false;
                } else if (zzdS(uri.getQuery()).equals(this.zzaCk)) {
                    zzbg.zzam("Exit preview mode for container: " + this.zzaCk);
                    this.zzaEr = zza.NONE;
                    this.zzaEs = null;
                } else {
                    z = false;
                }
            } catch (UnsupportedEncodingException e) {
                z = false;
            }
        }
        return z;
    }

    /* access modifiers changed from: package-private */
    public zza zzxm() {
        return this.zzaEr;
    }

    /* access modifiers changed from: package-private */
    public String zzxn() {
        return this.zzaEs;
    }
}
