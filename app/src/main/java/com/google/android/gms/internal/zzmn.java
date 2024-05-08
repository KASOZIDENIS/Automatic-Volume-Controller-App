package com.google.android.gms.internal;

import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzbg;
import org.json.JSONException;

public final class zzmn {
    public static zzmm zzaGY = new zzmm() {
        public Object zzp(byte[] bArr) throws zzmq.zzg {
            if (bArr == null) {
                throw new zzmq.zzg("Cannot parse a null byte[]");
            } else if (bArr.length == 0) {
                throw new zzmq.zzg("Cannot parse a 0 length byte[]");
            } else {
                try {
                    zzmq.zzc zzdQ = zzmk.zzdQ(new String(bArr));
                    if (zzdQ != null) {
                        zzbg.zzam("The container was successfully parsed from the resource");
                    }
                    return zzdQ;
                } catch (JSONException e) {
                    throw new zzmq.zzg("The resource data is corrupted. The container cannot be extracted from the binary data");
                } catch (zzmq.zzg e2) {
                    throw new zzmq.zzg("The resource data is invalid. The container cannot be extracted from the binary data");
                }
            }
        }
    };
}
