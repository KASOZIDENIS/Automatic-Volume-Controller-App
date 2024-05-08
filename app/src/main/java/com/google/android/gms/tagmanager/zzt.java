package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzb;
import com.google.android.gms.internal.zzd;
import java.util.HashMap;
import java.util.Map;

class zzt extends zzak {
    private static final String ID = com.google.android.gms.internal.zza.FUNCTION_CALL.toString();
    private static final String zzaCP = zzb.FUNCTION_CALL_NAME.toString();
    private static final String zzaCd = zzb.ADDITIONAL_PARAMS.toString();
    private final zza zzaCQ;

    public interface zza {
        Object zzc(String str, Map<String, Object> map);
    }

    public zzt(zza zza2) {
        super(ID, zzaCP);
        this.zzaCQ = zza2;
    }

    public zzd.zza zzD(Map<String, zzd.zza> map) {
        String zzg = zzdf.zzg(map.get(zzaCP));
        HashMap hashMap = new HashMap();
        zzd.zza zza2 = map.get(zzaCd);
        if (zza2 != null) {
            Object zzl = zzdf.zzl(zza2);
            if (!(zzl instanceof Map)) {
                zzbg.zzan("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return zzdf.zzxW();
            }
            for (Map.Entry entry : ((Map) zzl).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return zzdf.zzE(this.zzaCQ.zzc(zzg, hashMap));
        } catch (Exception e) {
            zzbg.zzan("Custom macro/tag " + zzg + " threw exception " + e.getMessage());
            return zzdf.zzxW();
        }
    }

    public boolean zzwn() {
        return false;
    }
}
