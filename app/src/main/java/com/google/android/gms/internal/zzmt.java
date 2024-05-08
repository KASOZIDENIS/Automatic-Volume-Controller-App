package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.tagmanager.zzbg;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class zzmt {
    private String zzaCO = "https://www.google-analytics.com";

    private String zzei(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            zzbg.zzak("Cannot encode the string: " + str);
            return "";
        }
    }

    public void zzem(String str) {
        this.zzaCO = str;
        zzbg.zzal("The Ctfe server endpoint was changed to: " + str);
    }

    public String zzu(List<zzmj> list) {
        return this.zzaCO + "/gtm/android?" + zzv(list);
    }

    /* access modifiers changed from: package-private */
    public String zzv(List<zzmj> list) {
        boolean z = true;
        if (list.size() > 1) {
            z = false;
        }
        zzv.zzQ(z);
        if (list.isEmpty()) {
            return "";
        }
        zzmj zzmj = list.get(0);
        String trim = !zzmj.zzyj().trim().equals("") ? zzmj.zzyj().trim() : "-1";
        StringBuilder sb = new StringBuilder();
        if (zzmj.zzyg() != null) {
            sb.append(zzmj.zzyg());
        } else {
            sb.append("id");
        }
        sb.append("=").append(zzei(zzmj.getContainerId())).append("&").append("pv").append("=").append(zzei(trim));
        if (zzmj.zzyi()) {
            sb.append("&gtm_debug=x");
        }
        return sb.toString();
    }
}
