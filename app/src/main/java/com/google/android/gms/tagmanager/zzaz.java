package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import com.google.android.gms.internal.zzmq;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class zzaz {
    public static zzmq.zzc zzdQ(String str) throws JSONException {
        zzd.zza zzx = zzx(new JSONObject(str));
        zzmq.zzd zzyv = zzmq.zzc.zzyv();
        for (int i = 0; i < zzx.zzhm.length; i++) {
            zzyv.zzc(zzmq.zza.zzys().zzb(zzb.INSTANCE_NAME.toString(), zzx.zzhm[i]).zzb(zzb.FUNCTION.toString(), zzdf.zzeb(zzn.zzwq())).zzb(zzn.zzwr(), zzx.zzhn[i]).zzyu());
        }
        return zzyv.zzyy();
    }

    private static zzd.zza zzx(Object obj) throws JSONException {
        return zzdf.zzE(zzy(obj));
    }

    static Object zzy(Object obj) throws JSONException {
        if (obj instanceof JSONArray) {
            throw new RuntimeException("JSONArrays are not supported");
        } else if (JSONObject.NULL.equals(obj)) {
            throw new RuntimeException("JSON nulls are not supported");
        } else if (!(obj instanceof JSONObject)) {
            return obj;
        } else {
            JSONObject jSONObject = (JSONObject) obj;
            HashMap hashMap = new HashMap();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, zzy(jSONObject.get(next)));
            }
            return hashMap;
        }
    }
}
