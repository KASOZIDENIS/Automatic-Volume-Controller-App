package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzd;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzdf {
    private static final Object zzaGf = null;
    private static Long zzaGg = new Long(0);
    private static Double zzaGh = new Double(0.0d);
    private static zzde zzaGi = zzde.zzS(0);
    private static String zzaGj = new String("");
    private static Boolean zzaGk = new Boolean(false);
    private static List<Object> zzaGl = new ArrayList(0);
    private static Map<Object, Object> zzaGm = new HashMap();
    private static zzd.zza zzaGn = zzE(zzaGj);

    private static double getDouble(Object o) {
        if (o instanceof Number) {
            return ((Number) o).doubleValue();
        }
        zzbg.zzak("getDouble received non-Number");
        return 0.0d;
    }

    public static zzde zzA(Object obj) {
        return obj instanceof zzde ? (zzde) obj : zzG(obj) ? zzde.zzS(zzH(obj)) : zzF(obj) ? zzde.zza(Double.valueOf(getDouble(obj))) : zzec(zzz(obj));
    }

    public static Long zzB(Object obj) {
        return zzG(obj) ? Long.valueOf(zzH(obj)) : zzed(zzz(obj));
    }

    public static Double zzC(Object obj) {
        return zzF(obj) ? Double.valueOf(getDouble(obj)) : zzee(zzz(obj));
    }

    public static Boolean zzD(Object obj) {
        return obj instanceof Boolean ? (Boolean) obj : zzef(zzz(obj));
    }

    public static zzd.zza zzE(Object obj) {
        boolean z = false;
        zzd.zza zza = new zzd.zza();
        if (obj instanceof zzd.zza) {
            return (zzd.zza) obj;
        }
        if (obj instanceof String) {
            zza.type = 1;
            zza.zzhk = (String) obj;
        } else if (obj instanceof List) {
            zza.type = 2;
            List<Object> list = (List) obj;
            ArrayList arrayList = new ArrayList(list.size());
            boolean z2 = false;
            for (Object zzE : list) {
                zzd.zza zzE2 = zzE(zzE);
                if (zzE2 == zzaGn) {
                    return zzaGn;
                }
                boolean z3 = z2 || zzE2.zzhu;
                arrayList.add(zzE2);
                z2 = z3;
            }
            zza.zzhl = (zzd.zza[]) arrayList.toArray(new zzd.zza[0]);
            z = z2;
        } else if (obj instanceof Map) {
            zza.type = 3;
            Set<Map.Entry> entrySet = ((Map) obj).entrySet();
            ArrayList arrayList2 = new ArrayList(entrySet.size());
            ArrayList arrayList3 = new ArrayList(entrySet.size());
            boolean z4 = false;
            for (Map.Entry entry : entrySet) {
                zzd.zza zzE3 = zzE(entry.getKey());
                zzd.zza zzE4 = zzE(entry.getValue());
                if (zzE3 == zzaGn || zzE4 == zzaGn) {
                    return zzaGn;
                }
                boolean z5 = z4 || zzE3.zzhu || zzE4.zzhu;
                arrayList2.add(zzE3);
                arrayList3.add(zzE4);
                z4 = z5;
            }
            zza.zzhm = (zzd.zza[]) arrayList2.toArray(new zzd.zza[0]);
            zza.zzhn = (zzd.zza[]) arrayList3.toArray(new zzd.zza[0]);
            z = z4;
        } else if (zzF(obj)) {
            zza.type = 1;
            zza.zzhk = obj.toString();
        } else if (zzG(obj)) {
            zza.type = 6;
            zza.zzhq = zzH(obj);
        } else if (obj instanceof Boolean) {
            zza.type = 8;
            zza.zzhr = ((Boolean) obj).booleanValue();
        } else {
            zzbg.zzak("Converting to Value from unknown object type: " + (obj == null ? "null" : obj.getClass().toString()));
            return zzaGn;
        }
        zza.zzhu = z;
        return zza;
    }

    private static boolean zzF(Object obj) {
        return (obj instanceof Double) || (obj instanceof Float) || ((obj instanceof zzde) && ((zzde) obj).zzxL());
    }

    private static boolean zzG(Object obj) {
        return (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Integer) || (obj instanceof Long) || ((obj instanceof zzde) && ((zzde) obj).zzxM());
    }

    private static long zzH(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        zzbg.zzak("getInt64 received non-Number");
        return 0;
    }

    public static zzd.zza zzeb(String str) {
        zzd.zza zza = new zzd.zza();
        zza.type = 5;
        zza.zzhp = str;
        return zza;
    }

    private static zzde zzec(String str) {
        try {
            return zzde.zzea(str);
        } catch (NumberFormatException e) {
            zzbg.zzak("Failed to convert '" + str + "' to a number.");
            return zzaGi;
        }
    }

    private static Long zzed(String str) {
        zzde zzec = zzec(str);
        return zzec == zzaGi ? zzaGg : Long.valueOf(zzec.longValue());
    }

    private static Double zzee(String str) {
        zzde zzec = zzec(str);
        return zzec == zzaGi ? zzaGh : Double.valueOf(zzec.doubleValue());
    }

    private static Boolean zzef(String str) {
        return "true".equalsIgnoreCase(str) ? Boolean.TRUE : "false".equalsIgnoreCase(str) ? Boolean.FALSE : zzaGk;
    }

    public static String zzg(zzd.zza zza) {
        return zzz(zzl(zza));
    }

    public static zzde zzh(zzd.zza zza) {
        return zzA(zzl(zza));
    }

    public static Long zzi(zzd.zza zza) {
        return zzB(zzl(zza));
    }

    public static Double zzj(zzd.zza zza) {
        return zzC(zzl(zza));
    }

    public static Boolean zzk(zzd.zza zza) {
        return zzD(zzl(zza));
    }

    public static Object zzl(zzd.zza zza) {
        int i = 0;
        if (zza == null) {
            return zzaGf;
        }
        switch (zza.type) {
            case 1:
                return zza.zzhk;
            case 2:
                ArrayList arrayList = new ArrayList(zza.zzhl.length);
                zzd.zza[] zzaArr = zza.zzhl;
                int length = zzaArr.length;
                while (i < length) {
                    Object zzl = zzl(zzaArr[i]);
                    if (zzl == zzaGf) {
                        return zzaGf;
                    }
                    arrayList.add(zzl);
                    i++;
                }
                return arrayList;
            case 3:
                if (zza.zzhm.length != zza.zzhn.length) {
                    zzbg.zzak("Converting an invalid value to object: " + zza.toString());
                    return zzaGf;
                }
                HashMap hashMap = new HashMap(zza.zzhn.length);
                while (i < zza.zzhm.length) {
                    Object zzl2 = zzl(zza.zzhm[i]);
                    Object zzl3 = zzl(zza.zzhn[i]);
                    if (zzl2 == zzaGf || zzl3 == zzaGf) {
                        return zzaGf;
                    }
                    hashMap.put(zzl2, zzl3);
                    i++;
                }
                return hashMap;
            case 4:
                zzbg.zzak("Trying to convert a macro reference to object");
                return zzaGf;
            case 5:
                zzbg.zzak("Trying to convert a function id to object");
                return zzaGf;
            case 6:
                return Long.valueOf(zza.zzhq);
            case 7:
                StringBuffer stringBuffer = new StringBuffer();
                zzd.zza[] zzaArr2 = zza.zzhs;
                int length2 = zzaArr2.length;
                while (i < length2) {
                    String zzg = zzg(zzaArr2[i]);
                    if (zzg == zzaGj) {
                        return zzaGf;
                    }
                    stringBuffer.append(zzg);
                    i++;
                }
                return stringBuffer.toString();
            case 8:
                return Boolean.valueOf(zza.zzhr);
            default:
                zzbg.zzak("Failed to convert a value of type: " + zza.type);
                return zzaGf;
        }
    }

    public static Object zzxQ() {
        return zzaGf;
    }

    public static Long zzxR() {
        return zzaGg;
    }

    public static Double zzxS() {
        return zzaGh;
    }

    public static Boolean zzxT() {
        return zzaGk;
    }

    public static zzde zzxU() {
        return zzaGi;
    }

    public static String zzxV() {
        return zzaGj;
    }

    public static zzd.zza zzxW() {
        return zzaGn;
    }

    public static String zzz(Object obj) {
        return obj == null ? zzaGj : obj.toString();
    }
}
