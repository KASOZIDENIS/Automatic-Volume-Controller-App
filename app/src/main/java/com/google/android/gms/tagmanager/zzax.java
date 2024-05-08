package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

class zzax {
    private static String zzaDG;
    static Map<String, String> zzaDH = new HashMap();

    zzax() {
    }

    static String zzB(String str, String str2) {
        if (str2 != null) {
            return Uri.parse("http://hostname/?" + str).getQueryParameter(str2);
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    static void zzdP(String str) {
        synchronized (zzax.class) {
            zzaDG = str;
        }
    }

    static String zzf(Context context, String str, String str2) {
        String str3 = zzaDH.get(str);
        if (str3 == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            str3 = sharedPreferences != null ? sharedPreferences.getString(str, "") : "";
            zzaDH.put(str, str3);
        }
        return zzB(str3, str2);
    }

    static String zzi(Context context, String str) {
        if (zzaDG == null) {
            synchronized (zzax.class) {
                if (zzaDG == null) {
                    SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzaDG = sharedPreferences.getString("referrer", "");
                    } else {
                        zzaDG = "";
                    }
                }
            }
        }
        return zzB(zzaDG, str);
    }

    static void zzj(Context context, String str) {
        String zzB = zzB(str, "conv");
        if (zzB != null && zzB.length() > 0) {
            zzaDH.put(zzB, str);
            zzcv.zza(context, "gtm_click_referrers", zzB, str);
        }
    }
}
