package com.google.android.gms.internal;

import com.google.android.gms.internal.zzd;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.tagmanager.zzdf;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

final class zzmk {
    static zzd.zza zza(int i, JSONArray jSONArray, zzd.zza[] zzaArr, Set<Integer> set) throws zzmq.zzg, JSONException {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            zzel("Value cycle detected. Current value reference: " + i + "." + "  Previous value references: " + set + ".");
        }
        Object zza = zza(jSONArray, i, "values");
        if (zzaArr[i] != null) {
            return zzaArr[i];
        }
        set.add(Integer.valueOf(i));
        zzd.zza zza2 = new zzd.zza();
        if (zza instanceof JSONArray) {
            JSONArray jSONArray2 = (JSONArray) zza;
            zza2.type = 2;
            zza2.zzhu = true;
            zza2.zzhl = new zzd.zza[jSONArray2.length()];
            while (i2 < zza2.zzhl.length) {
                zza2.zzhl[i2] = zza(jSONArray2.getInt(i2), jSONArray, zzaArr, set);
                i2++;
            }
        } else if (zza instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) zza;
            JSONArray optJSONArray = jSONObject.optJSONArray("escaping");
            if (optJSONArray != null) {
                zza2.zzht = new int[optJSONArray.length()];
                for (int i3 = 0; i3 < zza2.zzht.length; i3++) {
                    zza2.zzht[i3] = optJSONArray.getInt(i3);
                }
            }
            if (!jSONObject.has("function_id")) {
                if (!jSONObject.has("macro_reference")) {
                    if (!jSONObject.has("template_token")) {
                        zza2.type = 3;
                        zza2.zzhu = true;
                        int length = jSONObject.length();
                        zza2.zzhm = new zzd.zza[length];
                        zza2.zzhn = new zzd.zza[length];
                        Iterator<String> keys = jSONObject.keys();
                        while (true) {
                            int i4 = i2;
                            if (!keys.hasNext()) {
                                break;
                            }
                            String next = keys.next();
                            zza2.zzhm[i4] = zza(new Integer(next).intValue(), jSONArray, zzaArr, set);
                            zza2.zzhn[i4] = zza(jSONObject.getInt(next), jSONArray, zzaArr, set);
                            i2 = i4 + 1;
                        }
                    } else {
                        zza2.type = 7;
                        zza2.zzhu = true;
                        JSONArray jSONArray3 = jSONObject.getJSONArray("template_token");
                        zza2.zzhs = new zzd.zza[jSONArray3.length()];
                        while (i2 < zza2.zzhs.length) {
                            zza2.zzhs[i2] = zza(jSONArray3.getInt(i2), jSONArray, zzaArr, set);
                            i2++;
                        }
                    }
                } else {
                    zza2.type = 4;
                    zza2.zzhu = true;
                    zza2.zzho = zzdf.zzg(zza(jSONObject.getInt("macro_reference"), jSONArray, zzaArr, set));
                }
            } else {
                zza2.type = 5;
                zza2.zzhp = jSONObject.getString("function_id");
            }
        } else if (zza instanceof String) {
            zza2.zzhk = (String) zza;
            zza2.type = 1;
        } else if (zza instanceof Boolean) {
            zza2.zzhr = ((Boolean) zza).booleanValue();
            zza2.type = 8;
        } else if (zza instanceof Integer) {
            zza2.zzhq = (long) ((Integer) zza).intValue();
            zza2.type = 6;
        } else {
            zzel("Invalid value type: " + zza);
        }
        zzaArr[i] = zza2;
        set.remove(Integer.valueOf(i));
        return zza2;
    }

    static zzmq.zza zza(JSONObject jSONObject, JSONArray jSONArray, JSONArray jSONArray2, zzd.zza[] zzaArr, int i) throws zzmq.zzg, JSONException {
        zzmq.zzb zzys = zzmq.zza.zzys();
        JSONArray jSONArray3 = jSONObject.getJSONArray("property");
        int i2 = 0;
        while (true) {
            int i3 = i2;
            if (i3 >= jSONArray3.length()) {
                return zzys.zzyu();
            }
            JSONObject jSONObject2 = (JSONObject) zza(jSONArray, jSONArray3.getInt(i3), "properties");
            String str = (String) zza(jSONArray2, jSONObject2.getInt("key"), "key");
            zzd.zza zza = (zzd.zza) zza((T[]) zzaArr, jSONObject2.getInt("value"), "value");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzys.zzq(zza);
            } else {
                zzys.zzb(str, zza);
            }
            i2 = i3 + 1;
        }
    }

    static zzmq.zze zza(JSONObject jSONObject, List<zzmq.zza> list, List<zzmq.zza> list2, List<zzmq.zza> list3, zzd.zza[] zzaArr) throws JSONException {
        zzmq.zzf zzyz = zzmq.zze.zzyz();
        JSONArray optJSONArray = jSONObject.optJSONArray("positive_predicate");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("negative_predicate");
        JSONArray optJSONArray3 = jSONObject.optJSONArray("add_tag");
        JSONArray optJSONArray4 = jSONObject.optJSONArray("remove_tag");
        JSONArray optJSONArray5 = jSONObject.optJSONArray("add_tag_rule_name");
        JSONArray optJSONArray6 = jSONObject.optJSONArray("remove_tag_rule_name");
        JSONArray optJSONArray7 = jSONObject.optJSONArray("add_macro");
        JSONArray optJSONArray8 = jSONObject.optJSONArray("remove_macro");
        JSONArray optJSONArray9 = jSONObject.optJSONArray("add_macro_rule_name");
        JSONArray optJSONArray10 = jSONObject.optJSONArray("remove_macro_rule_name");
        if (optJSONArray != null) {
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 >= optJSONArray.length()) {
                    break;
                }
                zzyz.zzd(list3.get(optJSONArray.getInt(i2)));
                i = i2 + 1;
            }
        }
        if (optJSONArray2 != null) {
            int i3 = 0;
            while (true) {
                int i4 = i3;
                if (i4 >= optJSONArray2.length()) {
                    break;
                }
                zzyz.zze(list3.get(optJSONArray2.getInt(i4)));
                i3 = i4 + 1;
            }
        }
        if (optJSONArray3 != null) {
            int i5 = 0;
            while (true) {
                int i6 = i5;
                if (i6 >= optJSONArray3.length()) {
                    break;
                }
                zzyz.zzf(list.get(optJSONArray3.getInt(i6)));
                i5 = i6 + 1;
            }
        }
        if (optJSONArray4 != null) {
            int i7 = 0;
            while (true) {
                int i8 = i7;
                if (i8 >= optJSONArray4.length()) {
                    break;
                }
                zzyz.zzg(list.get(optJSONArray4.getInt(i8)));
                i7 = i8 + 1;
            }
        }
        if (optJSONArray5 != null) {
            for (int i9 = 0; i9 < optJSONArray5.length(); i9++) {
                zzyz.zzeo(zzaArr[optJSONArray5.getInt(i9)].zzhk);
            }
        }
        if (optJSONArray6 != null) {
            for (int i10 = 0; i10 < optJSONArray6.length(); i10++) {
                zzyz.zzep(zzaArr[optJSONArray6.getInt(i10)].zzhk);
            }
        }
        if (optJSONArray7 != null) {
            int i11 = 0;
            while (true) {
                int i12 = i11;
                if (i12 >= optJSONArray7.length()) {
                    break;
                }
                zzyz.zzh(list2.get(optJSONArray7.getInt(i12)));
                i11 = i12 + 1;
            }
        }
        if (optJSONArray8 != null) {
            int i13 = 0;
            while (true) {
                int i14 = i13;
                if (i14 >= optJSONArray8.length()) {
                    break;
                }
                zzyz.zzi(list2.get(optJSONArray8.getInt(i14)));
                i13 = i14 + 1;
            }
        }
        if (optJSONArray9 != null) {
            for (int i15 = 0; i15 < optJSONArray9.length(); i15++) {
                zzyz.zzeq(zzaArr[optJSONArray9.getInt(i15)].zzhk);
            }
        }
        if (optJSONArray10 != null) {
            for (int i16 = 0; i16 < optJSONArray10.length(); i16++) {
                zzyz.zzer(zzaArr[optJSONArray10.getInt(i16)].zzhk);
            }
        }
        return zzyz.zzyK();
    }

    private static <T> T zza(JSONArray jSONArray, int i, String str) throws zzmq.zzg {
        if (i >= 0 && i < jSONArray.length()) {
            try {
                return jSONArray.get(i);
            } catch (JSONException e) {
            }
        }
        zzel("Index out of bounds detected: " + i + " in " + str);
        return null;
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzmq.zzg {
        if (i < 0 || i >= tArr.length) {
            zzel("Index out of bounds detected: " + i + " in " + str);
        }
        return tArr[i];
    }

    static List<zzmq.zza> zza(JSONArray jSONArray, JSONArray jSONArray2, JSONArray jSONArray3, zzd.zza[] zzaArr) throws JSONException, zzmq.zzg {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(zza(jSONArray.getJSONObject(i), jSONArray2, jSONArray3, zzaArr, i));
        }
        return arrayList;
    }

    static zzmq.zzc zzdQ(String str) throws JSONException, zzmq.zzg {
        JSONObject jSONObject = new JSONObject(str);
        Object obj = jSONObject.get("resource");
        if (obj instanceof JSONObject) {
            JSONObject jSONObject2 = (JSONObject) obj;
            zzmq.zzd zzyv = zzmq.zzc.zzyv();
            zzd.zza[] zzh = zzh(jSONObject2);
            JSONArray jSONArray = jSONObject2.getJSONArray("properties");
            JSONArray jSONArray2 = jSONObject2.getJSONArray("key");
            List<zzmq.zza> zza = zza(jSONObject2.getJSONArray("tags"), jSONArray, jSONArray2, zzh);
            List<zzmq.zza> zza2 = zza(jSONObject2.getJSONArray("predicates"), jSONArray, jSONArray2, zzh);
            List<zzmq.zza> zza3 = zza(jSONObject2.getJSONArray("macros"), jSONArray, jSONArray2, zzh);
            for (zzmq.zza zzc : zza3) {
                zzyv.zzc(zzc);
            }
            JSONArray jSONArray3 = jSONObject2.getJSONArray("rules");
            for (int i = 0; i < jSONArray3.length(); i++) {
                zzyv.zzb(zza(jSONArray3.getJSONObject(i), zza, zza3, zza2, zzh));
            }
            zzyv.zzen("1");
            zzyv.zzhM(1);
            if (jSONObject.optJSONArray("runtime") != null) {
            }
            return zzyv.zzyy();
        }
        throw new zzmq.zzg("Resource map not found");
    }

    private static void zzel(String str) throws zzmq.zzg {
        zzbg.zzak(str);
        throw new zzmq.zzg(str);
    }

    static zzd.zza[] zzh(JSONObject jSONObject) throws JSONException, zzmq.zzg {
        Object opt = jSONObject.opt("values");
        if (opt instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) opt;
            zzd.zza[] zzaArr = new zzd.zza[jSONArray.length()];
            for (int i = 0; i < zzaArr.length; i++) {
                zza(i, jSONArray, zzaArr, (Set<Integer>) new HashSet(0));
            }
            return zzaArr;
        }
        throw new zzmq.zzg("Missing Values list");
    }
}
