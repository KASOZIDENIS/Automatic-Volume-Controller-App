package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzz implements zzar {
    private static final Object zzaBX = new Object();
    private static zzz zzaDm;
    private zzcd zzaCA;
    private String zzaDn;
    private String zzaDo;
    private zzas zzaDp;

    private zzz(Context context) {
        this(zzat.zzaq(context), new zzcs());
    }

    zzz(zzas zzas, zzcd zzcd) {
        this.zzaDp = zzas;
        this.zzaCA = zzcd;
    }

    public static zzar zzao(Context context) {
        zzz zzz;
        synchronized (zzaBX) {
            if (zzaDm == null) {
                zzaDm = new zzz(context);
            }
            zzz = zzaDm;
        }
        return zzz;
    }

    public boolean zzdK(String str) {
        if (!this.zzaCA.zziU()) {
            zzbg.zzan("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        if (!(this.zzaDn == null || this.zzaDo == null)) {
            try {
                str = this.zzaDn + "?" + this.zzaDo + "=" + URLEncoder.encode(str, "UTF-8");
                zzbg.zzam("Sending wrapped url hit: " + str);
            } catch (UnsupportedEncodingException e) {
                zzbg.zzd("Error wrapping URL for testing.", e);
                return false;
            }
        }
        this.zzaDp.zzdO(str);
        return true;
    }
}
