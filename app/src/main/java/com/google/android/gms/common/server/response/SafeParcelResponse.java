package com.google.android.gms.common.server.response;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.internal.zzhq;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class SafeParcelResponse extends FastJsonResponse implements SafeParcelable {
    public static final zze CREATOR = new zze();
    private final String mClassName;
    private final FieldMappingDictionary zzUG;
    private final Parcel zzUN;
    private final int zzUO;
    private int zzUP;
    private int zzUQ;
    private final int zzzH;

    SafeParcelResponse(int versionCode, Parcel parcel, FieldMappingDictionary fieldMappingDictionary) {
        this.zzzH = versionCode;
        this.zzUN = (Parcel) zzv.zzr(parcel);
        this.zzUO = 2;
        this.zzUG = fieldMappingDictionary;
        if (this.zzUG == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzUG.zzmO();
        }
        this.zzUP = 2;
    }

    private SafeParcelResponse(SafeParcelable safeParcelable, FieldMappingDictionary dictionary, String className) {
        this.zzzH = 1;
        this.zzUN = Parcel.obtain();
        safeParcelable.writeToParcel(this.zzUN, 0);
        this.zzUO = 1;
        this.zzUG = (FieldMappingDictionary) zzv.zzr(dictionary);
        this.mClassName = (String) zzv.zzr(className);
        this.zzUP = 2;
    }

    private static HashMap<Integer, Map.Entry<String, FastJsonResponse.Field<?, ?>>> zzB(Map<String, FastJsonResponse.Field<?, ?>> map) {
        HashMap<Integer, Map.Entry<String, FastJsonResponse.Field<?, ?>>> hashMap = new HashMap<>();
        for (Map.Entry next : map.entrySet()) {
            hashMap.put(Integer.valueOf(((FastJsonResponse.Field) next.getValue()).zzmF()), next);
        }
        return hashMap;
    }

    public static <T extends FastJsonResponse & SafeParcelable> SafeParcelResponse zza(T t) {
        String canonicalName = t.getClass().getCanonicalName();
        return new SafeParcelResponse((SafeParcelable) t, zzb(t), canonicalName);
    }

    private static void zza(FieldMappingDictionary fieldMappingDictionary, FastJsonResponse fastJsonResponse) {
        Class<?> cls = fastJsonResponse.getClass();
        if (!fieldMappingDictionary.zzb(cls)) {
            Map<String, FastJsonResponse.Field<?, ?>> zzmy = fastJsonResponse.zzmy();
            fieldMappingDictionary.zza(cls, zzmy);
            for (String str : zzmy.keySet()) {
                FastJsonResponse.Field field = zzmy.get(str);
                Class<? extends FastJsonResponse> zzmG = field.zzmG();
                if (zzmG != null) {
                    try {
                        zza(fieldMappingDictionary, (FastJsonResponse) zzmG.newInstance());
                    } catch (InstantiationException e) {
                        throw new IllegalStateException("Could not instantiate an object of type " + field.zzmG().getCanonicalName(), e);
                    } catch (IllegalAccessException e2) {
                        throw new IllegalStateException("Could not access object of type " + field.zzmG().getCanonicalName(), e2);
                    }
                }
            }
        }
    }

    private void zza(StringBuilder sb, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                sb.append(obj);
                return;
            case 7:
                sb.append("\"").append(zzhz.zzbY(obj.toString())).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzhr.zzg((byte[]) obj)).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzhr.zzh((byte[]) obj));
                sb.append("\"");
                return;
            case 10:
                zzia.zza(sb, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private void zza(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Parcel parcel, int i) {
        switch (field.zzmx()) {
            case 0:
                zzb(sb, field, (Object) zza(field, Integer.valueOf(zza.zzg(parcel, i))));
                return;
            case 1:
                zzb(sb, field, (Object) zza(field, zza.zzk(parcel, i)));
                return;
            case 2:
                zzb(sb, field, (Object) zza(field, Long.valueOf(zza.zzi(parcel, i))));
                return;
            case 3:
                zzb(sb, field, (Object) zza(field, Float.valueOf(zza.zzl(parcel, i))));
                return;
            case 4:
                zzb(sb, field, (Object) zza(field, Double.valueOf(zza.zzm(parcel, i))));
                return;
            case 5:
                zzb(sb, field, (Object) zza(field, zza.zzn(parcel, i)));
                return;
            case 6:
                zzb(sb, field, (Object) zza(field, Boolean.valueOf(zza.zzc(parcel, i))));
                return;
            case 7:
                zzb(sb, field, (Object) zza(field, zza.zzo(parcel, i)));
                return;
            case 8:
            case 9:
                zzb(sb, field, (Object) zza(field, zza.zzr(parcel, i)));
                return;
            case 10:
                zzb(sb, field, (Object) zza(field, zzh(zza.zzq(parcel, i))));
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown field out type = " + field.zzmx());
        }
    }

    private void zza(StringBuilder sb, String str, FastJsonResponse.Field<?, ?> field, Parcel parcel, int i) {
        sb.append("\"").append(str).append("\":");
        if (field.zzmI()) {
            zza(sb, field, parcel, i);
        } else {
            zzb(sb, field, parcel, i);
        }
    }

    private void zza(StringBuilder sb, Map<String, FastJsonResponse.Field<?, ?>> map, Parcel parcel) {
        HashMap<Integer, Map.Entry<String, FastJsonResponse.Field<?, ?>>> zzB = zzB(map);
        sb.append('{');
        int zzL = zza.zzL(parcel);
        boolean z = false;
        while (parcel.dataPosition() < zzL) {
            int zzK = zza.zzK(parcel);
            Map.Entry entry = zzB.get(Integer.valueOf(zza.zzaV(zzK)));
            if (entry != null) {
                if (z) {
                    sb.append(",");
                }
                zza(sb, (String) entry.getKey(), (FastJsonResponse.Field) entry.getValue(), parcel, zzK);
                z = true;
            }
        }
        if (parcel.dataPosition() != zzL) {
            throw new zza.C0004zza("Overread allowed size end=" + zzL, parcel);
        }
        sb.append('}');
    }

    private static FieldMappingDictionary zzb(FastJsonResponse fastJsonResponse) {
        FieldMappingDictionary fieldMappingDictionary = new FieldMappingDictionary(fastJsonResponse.getClass());
        zza(fieldMappingDictionary, fastJsonResponse);
        fieldMappingDictionary.zzmM();
        fieldMappingDictionary.zzmL();
        return fieldMappingDictionary;
    }

    private void zzb(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Parcel parcel, int i) {
        if (field.zzmD()) {
            sb.append("[");
            switch (field.zzmx()) {
                case 0:
                    zzhq.zza(sb, zza.zzu(parcel, i));
                    break;
                case 1:
                    zzhq.zza(sb, (T[]) zza.zzw(parcel, i));
                    break;
                case 2:
                    zzhq.zza(sb, zza.zzv(parcel, i));
                    break;
                case 3:
                    zzhq.zza(sb, zza.zzx(parcel, i));
                    break;
                case 4:
                    zzhq.zza(sb, zza.zzy(parcel, i));
                    break;
                case 5:
                    zzhq.zza(sb, (T[]) zza.zzz(parcel, i));
                    break;
                case 6:
                    zzhq.zza(sb, zza.zzt(parcel, i));
                    break;
                case 7:
                    zzhq.zza(sb, zza.zzA(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] zzE = zza.zzE(parcel, i);
                    int length = zzE.length;
                    for (int i2 = 0; i2 < length; i2++) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        zzE[i2].setDataPosition(0);
                        zza(sb, field.zzmK(), zzE[i2]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            sb.append("]");
            return;
        }
        switch (field.zzmx()) {
            case 0:
                sb.append(zza.zzg(parcel, i));
                return;
            case 1:
                sb.append(zza.zzk(parcel, i));
                return;
            case 2:
                sb.append(zza.zzi(parcel, i));
                return;
            case 3:
                sb.append(zza.zzl(parcel, i));
                return;
            case 4:
                sb.append(zza.zzm(parcel, i));
                return;
            case 5:
                sb.append(zza.zzn(parcel, i));
                return;
            case 6:
                sb.append(zza.zzc(parcel, i));
                return;
            case 7:
                sb.append("\"").append(zzhz.zzbY(zza.zzo(parcel, i))).append("\"");
                return;
            case 8:
                sb.append("\"").append(zzhr.zzg(zza.zzr(parcel, i))).append("\"");
                return;
            case 9:
                sb.append("\"").append(zzhr.zzh(zza.zzr(parcel, i)));
                sb.append("\"");
                return;
            case 10:
                Bundle zzq = zza.zzq(parcel, i);
                Set<String> keySet = zzq.keySet();
                keySet.size();
                sb.append("{");
                boolean z = true;
                for (String str : keySet) {
                    if (!z) {
                        sb.append(",");
                    }
                    sb.append("\"").append(str).append("\"");
                    sb.append(":");
                    sb.append("\"").append(zzhz.zzbY(zzq.getString(str))).append("\"");
                    z = false;
                }
                sb.append("}");
                return;
            case 11:
                Parcel zzD = zza.zzD(parcel, i);
                zzD.setDataPosition(0);
                zza(sb, field.zzmK(), zzD);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private void zzb(StringBuilder sb, FastJsonResponse.Field<?, ?> field, Object obj) {
        if (field.zzmC()) {
            zzb(sb, field, (ArrayList<?>) (ArrayList) obj);
        } else {
            zza(sb, field.zzmw(), obj);
        }
    }

    private void zzb(StringBuilder sb, FastJsonResponse.Field<?, ?> field, ArrayList<?> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append(",");
            }
            zza(sb, field.zzmw(), (Object) arrayList.get(i));
        }
        sb.append("]");
    }

    public static HashMap<String, String> zzh(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap<>();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public int describeContents() {
        zze zze = CREATOR;
        return 0;
    }

    public int getVersionCode() {
        return this.zzzH;
    }

    public String toString() {
        zzv.zzb(this.zzUG, (Object) "Cannot convert to JSON on client side.");
        Parcel zzmQ = zzmQ();
        zzmQ.setDataPosition(0);
        StringBuilder sb = new StringBuilder(100);
        zza(sb, this.zzUG.zzbX(this.mClassName), zzmQ);
        return sb.toString();
    }

    public void writeToParcel(Parcel out, int flags) {
        zze zze = CREATOR;
        zze.zza(this, out, flags);
    }

    /* access modifiers changed from: protected */
    public Object zzbT(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    /* access modifiers changed from: protected */
    public boolean zzbU(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public Parcel zzmQ() {
        switch (this.zzUP) {
            case 0:
                this.zzUQ = zzb.zzM(this.zzUN);
                zzb.zzH(this.zzUN, this.zzUQ);
                this.zzUP = 2;
                break;
            case 1:
                zzb.zzH(this.zzUN, this.zzUQ);
                this.zzUP = 2;
                break;
        }
        return this.zzUN;
    }

    /* access modifiers changed from: package-private */
    public FieldMappingDictionary zzmR() {
        switch (this.zzUO) {
            case 0:
                return null;
            case 1:
                return this.zzUG;
            case 2:
                return this.zzUG;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zzUO);
        }
    }

    public Map<String, FastJsonResponse.Field<?, ?>> zzmy() {
        if (this.zzUG == null) {
            return null;
        }
        return this.zzUG.zzbX(this.mClassName);
    }
}
