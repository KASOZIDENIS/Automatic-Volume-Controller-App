package com.google.android.gms.tagmanager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataLayer {
    public static final String EVENT_KEY = "event";
    public static final Object OBJECT_NOT_PRESENT = new Object();
    static final String[] zzaCR = "gtm.lifetime".toString().split("\\.");
    private static final Pattern zzaCS = Pattern.compile("(\\d+)\\s*([smhd]?)");
    private final ConcurrentHashMap<zzb, Integer> zzaCT;
    private final Map<String, Object> zzaCU;
    private final ReentrantLock zzaCV;
    private final LinkedList<Map<String, Object>> zzaCW;
    private final zzc zzaCX;
    /* access modifiers changed from: private */
    public final CountDownLatch zzaCY;

    static final class zza {
        public final Object zzCM;
        public final String zzra;

        zza(String str, Object obj) {
            this.zzra = str;
            this.zzCM = obj;
        }

        public boolean equals(Object o) {
            if (!(o instanceof zza)) {
                return false;
            }
            zza zza = (zza) o;
            return this.zzra.equals(zza.zzra) && this.zzCM.equals(zza.zzCM);
        }

        public int hashCode() {
            return Arrays.hashCode(new Integer[]{Integer.valueOf(this.zzra.hashCode()), Integer.valueOf(this.zzCM.hashCode())});
        }

        public String toString() {
            return "Key: " + this.zzra + " value: " + this.zzCM.toString();
        }
    }

    interface zzb {
        void zzE(Map<String, Object> map);
    }

    interface zzc {

        public interface zza {
            void zzp(List<zza> list);
        }

        void zza(zza zza2);

        void zza(List<zza> list, long j);

        void zzdH(String str);
    }

    DataLayer() {
        this(new zzc() {
            public void zza(zzc.zza zza) {
                zza.zzp(new ArrayList());
            }

            public void zza(List<zza> list, long j) {
            }

            public void zzdH(String str) {
            }
        });
    }

    DataLayer(zzc persistentStore) {
        this.zzaCX = persistentStore;
        this.zzaCT = new ConcurrentHashMap<>();
        this.zzaCU = new HashMap();
        this.zzaCV = new ReentrantLock();
        this.zzaCW = new LinkedList<>();
        this.zzaCY = new CountDownLatch(1);
        zzwE();
    }

    public static List<Object> listOf(Object... objects) {
        ArrayList arrayList = new ArrayList();
        for (Object add : objects) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static Map<String, Object> mapOf(Object... objects) {
        if (objects.length % 2 != 0) {
            throw new IllegalArgumentException("expected even number of key-value pairs");
        }
        HashMap hashMap = new HashMap();
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= objects.length) {
                return hashMap;
            }
            if (!(objects[i2] instanceof String)) {
                throw new IllegalArgumentException("key is not a string: " + objects[i2]);
            }
            hashMap.put(objects[i2], objects[i2 + 1]);
            i = i2 + 2;
        }
    }

    /* access modifiers changed from: private */
    public void zzG(Map<String, Object> map) {
        this.zzaCV.lock();
        try {
            this.zzaCW.offer(map);
            if (this.zzaCV.getHoldCount() == 1) {
                zzwF();
            }
            zzH(map);
        } finally {
            this.zzaCV.unlock();
        }
    }

    private void zzH(Map<String, Object> map) {
        Long zzI = zzI(map);
        if (zzI != null) {
            List<zza> zzK = zzK(map);
            zzK.remove("gtm.lifetime");
            this.zzaCX.zza(zzK, zzI.longValue());
        }
    }

    private Long zzI(Map<String, Object> map) {
        Object zzJ = zzJ(map);
        if (zzJ == null) {
            return null;
        }
        return zzdG(zzJ.toString());
    }

    private Object zzJ(Map<String, Object> map) {
        String[] strArr = zzaCR;
        int length = strArr.length;
        int i = 0;
        Object obj = map;
        while (i < length) {
            String str = strArr[i];
            if (!(obj instanceof Map)) {
                return null;
            }
            i++;
            obj = ((Map) obj).get(str);
        }
        return obj;
    }

    private List<zza> zzK(Map<String, Object> map) {
        ArrayList arrayList = new ArrayList();
        zza(map, "", arrayList);
        return arrayList;
    }

    private void zzL(Map<String, Object> map) {
        synchronized (this.zzaCU) {
            for (String next : map.keySet()) {
                zzc(zzi(next, map.get(next)), this.zzaCU);
            }
        }
        zzM(map);
    }

    private void zzM(Map<String, Object> map) {
        for (zzb zzE : this.zzaCT.keySet()) {
            zzE.zzE(map);
        }
    }

    private void zza(Map<String, Object> map, String str, Collection<zza> collection) {
        for (Map.Entry next : map.entrySet()) {
            String str2 = str + (str.length() == 0 ? "" : ".") + ((String) next.getKey());
            if (next.getValue() instanceof Map) {
                zza((Map) next.getValue(), str2, collection);
            } else if (!str2.equals("gtm.lifetime")) {
                collection.add(new zza(str2, next.getValue()));
            }
        }
    }

    static Long zzdG(String str) {
        long j;
        Matcher matcher = zzaCS.matcher(str);
        if (!matcher.matches()) {
            zzbg.zzal("unknown _lifetime: " + str);
            return null;
        }
        try {
            j = Long.parseLong(matcher.group(1));
        } catch (NumberFormatException e) {
            zzbg.zzan("illegal number in _lifetime value: " + str);
            j = 0;
        }
        if (j <= 0) {
            zzbg.zzal("non-positive _lifetime: " + str);
            return null;
        }
        String group = matcher.group(2);
        if (group.length() == 0) {
            return Long.valueOf(j);
        }
        switch (group.charAt(0)) {
            case 'd':
                return Long.valueOf(j * 1000 * 60 * 60 * 24);
            case 'h':
                return Long.valueOf(j * 1000 * 60 * 60);
            case 'm':
                return Long.valueOf(j * 1000 * 60);
            case 's':
                return Long.valueOf(j * 1000);
            default:
                zzbg.zzan("unknown units in _lifetime: " + str);
                return null;
        }
    }

    private void zzwE() {
        this.zzaCX.zza(new zzc.zza() {
            public void zzp(List<zza> list) {
                for (zza next : list) {
                    DataLayer.this.zzG(DataLayer.this.zzi(next.zzra, next.zzCM));
                }
                DataLayer.this.zzaCY.countDown();
            }
        });
    }

    private void zzwF() {
        int i = 0;
        while (true) {
            int i2 = i;
            Map poll = this.zzaCW.poll();
            if (poll != null) {
                zzL(poll);
                i = i2 + 1;
                if (i > 500) {
                    this.zzaCW.clear();
                    throw new RuntimeException("Seems like an infinite loop of pushing to the data layer");
                }
            } else {
                return;
            }
        }
    }

    public Object get(String key) {
        synchronized (this.zzaCU) {
            Object obj = this.zzaCU;
            String[] split = key.split("\\.");
            int length = split.length;
            Object obj2 = obj;
            int i = 0;
            while (i < length) {
                String str = split[i];
                if (!(obj2 instanceof Map)) {
                    return null;
                }
                Object obj3 = ((Map) obj2).get(str);
                if (obj3 == null) {
                    return null;
                }
                i++;
                obj2 = obj3;
            }
            return obj2;
        }
    }

    public void push(String key, Object value) {
        push(zzi(key, value));
    }

    public void push(Map<String, Object> update) {
        try {
            this.zzaCY.await();
        } catch (InterruptedException e) {
            zzbg.zzan("DataLayer.push: unexpected InterruptedException");
        }
        zzG(update);
    }

    public void pushEvent(String eventName, Map<String, Object> update) {
        HashMap hashMap = new HashMap(update);
        hashMap.put("event", eventName);
        push(hashMap);
    }

    public String toString() {
        String sb;
        synchronized (this.zzaCU) {
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry next : this.zzaCU.entrySet()) {
                sb2.append(String.format("{\n\tKey: %s\n\tValue: %s\n}\n", new Object[]{next.getKey(), next.getValue()}));
            }
            sb = sb2.toString();
        }
        return sb;
    }

    /* access modifiers changed from: package-private */
    public void zza(zzb zzb2) {
        this.zzaCT.put(zzb2, 0);
    }

    /* access modifiers changed from: package-private */
    public void zzb(List<Object> list, List<Object> list2) {
        while (list2.size() < list.size()) {
            list2.add((Object) null);
        }
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < list.size()) {
                Object obj = list.get(i2);
                if (obj instanceof List) {
                    if (!(list2.get(i2) instanceof List)) {
                        list2.set(i2, new ArrayList());
                    }
                    zzb((List) obj, (List) list2.get(i2));
                } else if (obj instanceof Map) {
                    if (!(list2.get(i2) instanceof Map)) {
                        list2.set(i2, new HashMap());
                    }
                    zzc((Map) obj, (Map) list2.get(i2));
                } else if (obj != OBJECT_NOT_PRESENT) {
                    list2.set(i2, obj);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzc(Map<String, Object> map, Map<String, Object> map2) {
        for (String next : map.keySet()) {
            Object obj = map.get(next);
            if (obj instanceof List) {
                if (!(map2.get(next) instanceof List)) {
                    map2.put(next, new ArrayList());
                }
                zzb((List) obj, (List) map2.get(next));
            } else if (obj instanceof Map) {
                if (!(map2.get(next) instanceof Map)) {
                    map2.put(next, new HashMap());
                }
                zzc((Map) obj, (Map) map2.get(next));
            } else {
                map2.put(next, obj);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzdF(String str) {
        push(str, (Object) null);
        this.zzaCX.zzdH(str);
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> zzi(String str, Object obj) {
        HashMap hashMap = new HashMap();
        String[] split = str.toString().split("\\.");
        int i = 0;
        HashMap hashMap2 = hashMap;
        while (i < split.length - 1) {
            HashMap hashMap3 = new HashMap();
            hashMap2.put(split[i], hashMap3);
            i++;
            hashMap2 = hashMap3;
        }
        hashMap2.put(split[split.length - 1], obj);
        return hashMap;
    }
}
