package com.google.android.gms.tagmanager;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityEventCompat;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzd;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzm;
import com.google.android.gms.tagmanager.zzt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class zzcp {
    private static final zzbw<zzd.zza> zzaEQ = new zzbw<>(zzdf.zzxW(), true);
    private final DataLayer zzaCl;
    private final zzmq.zzc zzaER;
    private final zzah zzaES;
    private final Map<String, zzak> zzaET;
    private final Map<String, zzak> zzaEU;
    private final Map<String, zzak> zzaEV;
    private final zzl<zzmq.zza, zzbw<zzd.zza>> zzaEW;
    private final zzl<String, zzb> zzaEX;
    private final Set<zzmq.zze> zzaEY;
    private final Map<String, zzc> zzaEZ;
    private volatile String zzaFa;
    private int zzaFb;

    interface zza {
        void zza(zzmq.zze zze, Set<zzmq.zza> set, Set<zzmq.zza> set2, zzck zzck);
    }

    private static class zzb {
        private zzbw<zzd.zza> zzaFh;
        private zzd.zza zzaFi;

        public zzb(zzbw<zzd.zza> zzbw, zzd.zza zza) {
            this.zzaFh = zzbw;
            this.zzaFi = zza;
        }

        public int getSize() {
            return (this.zzaFi == null ? 0 : this.zzaFi.zzAb()) + this.zzaFh.getObject().zzAb();
        }

        public zzbw<zzd.zza> zzxx() {
            return this.zzaFh;
        }

        public zzd.zza zzxy() {
            return this.zzaFi;
        }
    }

    private static class zzc {
        private final Set<zzmq.zze> zzaEY = new HashSet();
        private final Map<zzmq.zze, List<zzmq.zza>> zzaFj = new HashMap();
        private final Map<zzmq.zze, List<zzmq.zza>> zzaFk = new HashMap();
        private final Map<zzmq.zze, List<String>> zzaFl = new HashMap();
        private final Map<zzmq.zze, List<String>> zzaFm = new HashMap();
        private zzmq.zza zzaFn;

        public void zza(zzmq.zze zze) {
            this.zzaEY.add(zze);
        }

        public void zza(zzmq.zze zze, zzmq.zza zza) {
            List list = this.zzaFj.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzaFj.put(zze, list);
            }
            list.add(zza);
        }

        public void zza(zzmq.zze zze, String str) {
            List list = this.zzaFl.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzaFl.put(zze, list);
            }
            list.add(str);
        }

        public void zzb(zzmq.zza zza) {
            this.zzaFn = zza;
        }

        public void zzb(zzmq.zze zze, zzmq.zza zza) {
            List list = this.zzaFk.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzaFk.put(zze, list);
            }
            list.add(zza);
        }

        public void zzb(zzmq.zze zze, String str) {
            List list = this.zzaFm.get(zze);
            if (list == null) {
                list = new ArrayList();
                this.zzaFm.put(zze, list);
            }
            list.add(str);
        }

        public Map<zzmq.zze, List<zzmq.zza>> zzxA() {
            return this.zzaFj;
        }

        public Map<zzmq.zze, List<String>> zzxB() {
            return this.zzaFl;
        }

        public Map<zzmq.zze, List<String>> zzxC() {
            return this.zzaFm;
        }

        public Map<zzmq.zze, List<zzmq.zza>> zzxD() {
            return this.zzaFk;
        }

        public zzmq.zza zzxE() {
            return this.zzaFn;
        }

        public Set<zzmq.zze> zzxz() {
            return this.zzaEY;
        }
    }

    public zzcp(Context context, zzmq.zzc zzc2, DataLayer dataLayer, zzt.zza zza2, zzt.zza zza3, zzah zzah) {
        if (zzc2 == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.zzaER = zzc2;
        this.zzaEY = new HashSet(zzc2.zzyw());
        this.zzaCl = dataLayer;
        this.zzaES = zzah;
        this.zzaEW = new zzm().zza(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new zzm.zza<zzmq.zza, zzbw<zzd.zza>>() {
            /* renamed from: zza */
            public int sizeOf(zzmq.zza zza, zzbw<zzd.zza> zzbw) {
                return zzbw.getObject().zzAb();
            }
        });
        this.zzaEX = new zzm().zza(AccessibilityEventCompat.TYPE_TOUCH_INTERACTION_START, new zzm.zza<String, zzb>() {
            /* renamed from: zza */
            public int sizeOf(String str, zzb zzb) {
                return str.length() + zzb.getSize();
            }
        });
        this.zzaET = new HashMap();
        zzb(new zzj(context));
        zzb(new zzt(zza3));
        zzb(new zzx(dataLayer));
        zzb(new zzdg(context, dataLayer));
        zzb(new zzdb(context, dataLayer));
        this.zzaEU = new HashMap();
        zzc(new zzr());
        zzc(new zzae());
        zzc(new zzaf());
        zzc(new zzam());
        zzc(new zzan());
        zzc(new zzbc());
        zzc(new zzbd());
        zzc(new zzcf());
        zzc(new zzcy());
        this.zzaEV = new HashMap();
        zza((zzak) new zzb(context));
        zza((zzak) new zzc(context));
        zza((zzak) new zze(context));
        zza((zzak) new zzf(context));
        zza((zzak) new zzg(context));
        zza((zzak) new zzh(context));
        zza((zzak) new zzi(context));
        zza((zzak) new zzn());
        zza((zzak) new zzq(this.zzaER.getVersion()));
        zza((zzak) new zzt(zza2));
        zza((zzak) new zzv(dataLayer));
        zza((zzak) new zzaa(context));
        zza((zzak) new zzab());
        zza((zzak) new zzad());
        zza((zzak) new zzai(this));
        zza((zzak) new zzao());
        zza((zzak) new zzap());
        zza((zzak) new zzaw(context));
        zza((zzak) new zzay());
        zza((zzak) new zzbb());
        zza((zzak) new zzbi());
        zza((zzak) new zzbk(context));
        zza((zzak) new zzbx());
        zza((zzak) new zzbz());
        zza((zzak) new zzcc());
        zza((zzak) new zzce());
        zza((zzak) new zzcg(context));
        zza((zzak) new zzcq());
        zza((zzak) new zzcr());
        zza((zzak) new zzda());
        zza((zzak) new zzdh());
        this.zzaEZ = new HashMap();
        for (zzmq.zze next : this.zzaEY) {
            if (zzah.zzwR()) {
                zza(next.zzyE(), next.zzyF(), "add macro");
                zza(next.zzyJ(), next.zzyG(), "remove macro");
                zza(next.zzyC(), next.zzyH(), "add tag");
                zza(next.zzyD(), next.zzyI(), "remove tag");
            }
            for (int i = 0; i < next.zzyE().size(); i++) {
                zzmq.zza zza4 = next.zzyE().get(i);
                String str = "Unknown";
                if (zzah.zzwR() && i < next.zzyF().size()) {
                    str = next.zzyF().get(i);
                }
                zzc zzf = zzf(this.zzaEZ, zza(zza4));
                zzf.zza(next);
                zzf.zza(next, zza4);
                zzf.zza(next, str);
            }
            for (int i2 = 0; i2 < next.zzyJ().size(); i2++) {
                zzmq.zza zza5 = next.zzyJ().get(i2);
                String str2 = "Unknown";
                if (zzah.zzwR() && i2 < next.zzyG().size()) {
                    str2 = next.zzyG().get(i2);
                }
                zzc zzf2 = zzf(this.zzaEZ, zza(zza5));
                zzf2.zza(next);
                zzf2.zzb(next, zza5);
                zzf2.zzb(next, str2);
            }
        }
        for (Map.Entry next2 : this.zzaER.zzyx().entrySet()) {
            for (zzmq.zza zza6 : (List) next2.getValue()) {
                if (!zzdf.zzk(zza6.zzyt().get(com.google.android.gms.internal.zzb.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzf(this.zzaEZ, (String) next2.getKey()).zzb(zza6);
                }
            }
        }
    }

    private zzbw<zzd.zza> zza(zzd.zza zza2, Set<String> set, zzdi zzdi) {
        if (!zza2.zzhu) {
            return new zzbw<>(zza2, true);
        }
        switch (zza2.type) {
            case 2:
                zzd.zza zzo = zzmq.zzo(zza2);
                zzo.zzhl = new zzd.zza[zza2.zzhl.length];
                for (int i = 0; i < zza2.zzhl.length; i++) {
                    zzbw<zzd.zza> zza3 = zza(zza2.zzhl[i], set, zzdi.zzhF(i));
                    if (zza3 == zzaEQ) {
                        return zzaEQ;
                    }
                    zzo.zzhl[i] = zza3.getObject();
                }
                return new zzbw<>(zzo, false);
            case 3:
                zzd.zza zzo2 = zzmq.zzo(zza2);
                if (zza2.zzhm.length != zza2.zzhn.length) {
                    zzbg.zzak("Invalid serving value: " + zza2.toString());
                    return zzaEQ;
                }
                zzo2.zzhm = new zzd.zza[zza2.zzhm.length];
                zzo2.zzhn = new zzd.zza[zza2.zzhm.length];
                for (int i2 = 0; i2 < zza2.zzhm.length; i2++) {
                    zzbw<zzd.zza> zza4 = zza(zza2.zzhm[i2], set, zzdi.zzhG(i2));
                    zzbw<zzd.zza> zza5 = zza(zza2.zzhn[i2], set, zzdi.zzhH(i2));
                    if (zza4 == zzaEQ || zza5 == zzaEQ) {
                        return zzaEQ;
                    }
                    zzo2.zzhm[i2] = zza4.getObject();
                    zzo2.zzhn[i2] = zza5.getObject();
                }
                return new zzbw<>(zzo2, false);
            case 4:
                if (set.contains(zza2.zzho)) {
                    zzbg.zzak("Macro cycle detected.  Current macro reference: " + zza2.zzho + "." + "  Previous macro references: " + set.toString() + ".");
                    return zzaEQ;
                }
                set.add(zza2.zzho);
                zzbw<zzd.zza> zza6 = zzdj.zza(zza(zza2.zzho, set, zzdi.zzxf()), zza2.zzht);
                set.remove(zza2.zzho);
                return zza6;
            case 7:
                zzd.zza zzo3 = zzmq.zzo(zza2);
                zzo3.zzhs = new zzd.zza[zza2.zzhs.length];
                for (int i3 = 0; i3 < zza2.zzhs.length; i3++) {
                    zzbw<zzd.zza> zza7 = zza(zza2.zzhs[i3], set, zzdi.zzhI(i3));
                    if (zza7 == zzaEQ) {
                        return zzaEQ;
                    }
                    zzo3.zzhs[i3] = zza7.getObject();
                }
                return new zzbw<>(zzo3, false);
            default:
                zzbg.zzak("Unknown type: " + zza2.type);
                return zzaEQ;
        }
    }

    private zzbw<zzd.zza> zza(String str, Set<String> set, zzbj zzbj) {
        zzmq.zza zza2;
        this.zzaFb++;
        zzb zzb2 = this.zzaEX.get(str);
        if (zzb2 == null || this.zzaES.zzwR()) {
            zzc zzc2 = this.zzaEZ.get(str);
            if (zzc2 == null) {
                zzbg.zzak(zzxw() + "Invalid macro: " + str);
                this.zzaFb--;
                return zzaEQ;
            }
            zzbw<Set<zzmq.zza>> zza3 = zza(str, zzc2.zzxz(), zzc2.zzxA(), zzc2.zzxB(), zzc2.zzxD(), zzc2.zzxC(), set, zzbj.zzwH());
            if (zza3.getObject().isEmpty()) {
                zza2 = zzc2.zzxE();
            } else {
                if (zza3.getObject().size() > 1) {
                    zzbg.zzan(zzxw() + "Multiple macros active for macroName " + str);
                }
                zza2 = (zzmq.zza) zza3.getObject().iterator().next();
            }
            if (zza2 == null) {
                this.zzaFb--;
                return zzaEQ;
            }
            zzbw<zzd.zza> zza4 = zza(this.zzaEV, zza2, set, zzbj.zzwX());
            zzbw<zzd.zza> zzbw = zza4 == zzaEQ ? zzaEQ : new zzbw<>(zza4.getObject(), zza3.zzxg() && zza4.zzxg());
            zzd.zza zzxy = zza2.zzxy();
            if (zzbw.zzxg()) {
                this.zzaEX.zze(str, new zzb(zzbw, zzxy));
            }
            zza(zzxy, set);
            this.zzaFb--;
            return zzbw;
        }
        zza(zzb2.zzxy(), set);
        this.zzaFb--;
        return zzb2.zzxx();
    }

    private zzbw<zzd.zza> zza(Map<String, zzak> map, zzmq.zza zza2, Set<String> set, zzch zzch) {
        boolean z;
        boolean z2 = true;
        zzd.zza zza3 = zza2.zzyt().get(com.google.android.gms.internal.zzb.FUNCTION.toString());
        if (zza3 == null) {
            zzbg.zzak("No function id in properties");
            return zzaEQ;
        }
        String str = zza3.zzhp;
        zzak zzak = map.get(str);
        if (zzak == null) {
            zzbg.zzak(str + " has no backing implementation.");
            return zzaEQ;
        }
        zzbw<zzd.zza> zzbw = this.zzaEW.get(zza2);
        if (zzbw != null && !this.zzaES.zzwR()) {
            return zzbw;
        }
        HashMap hashMap = new HashMap();
        boolean z3 = true;
        for (Map.Entry next : zza2.zzyt().entrySet()) {
            zzbw<zzd.zza> zza4 = zza((zzd.zza) next.getValue(), set, zzch.zzdR((String) next.getKey()).zze((zzd.zza) next.getValue()));
            if (zza4 == zzaEQ) {
                return zzaEQ;
            }
            if (zza4.zzxg()) {
                zza2.zza((String) next.getKey(), zza4.getObject());
                z = z3;
            } else {
                z = false;
            }
            hashMap.put(next.getKey(), zza4.getObject());
            z3 = z;
        }
        if (!zzak.zzg(hashMap.keySet())) {
            zzbg.zzak("Incorrect keys for function " + str + " required " + zzak.zzwT() + " had " + hashMap.keySet());
            return zzaEQ;
        }
        if (!z3 || !zzak.zzwn()) {
            z2 = false;
        }
        zzbw<zzd.zza> zzbw2 = new zzbw<>(zzak.zzD(hashMap), z2);
        if (z2) {
            this.zzaEW.zze(zza2, zzbw2);
        }
        zzch.zzd(zzbw2.getObject());
        return zzbw2;
    }

    private zzbw<Set<zzmq.zza>> zza(Set<zzmq.zze> set, Set<String> set2, zza zza2, zzco zzco) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        boolean z = true;
        for (zzmq.zze next : set) {
            zzck zzxe = zzco.zzxe();
            zzbw<Boolean> zza3 = zza(next, set2, zzxe);
            if (zza3.getObject().booleanValue()) {
                zza2.zza(next, hashSet, hashSet2, zzxe);
            }
            z = z && zza3.zzxg();
        }
        hashSet.removeAll(hashSet2);
        zzco.zzh(hashSet);
        return new zzbw<>(hashSet, z);
    }

    private static String zza(zzmq.zza zza2) {
        return zzdf.zzg(zza2.zzyt().get(com.google.android.gms.internal.zzb.INSTANCE_NAME.toString()));
    }

    private void zza(zzd.zza zza2, Set<String> set) {
        zzbw<zzd.zza> zza3;
        if (zza2 != null && (zza3 = zza(zza2, set, (zzdi) new zzbu())) != zzaEQ) {
            Object zzl = zzdf.zzl(zza3.getObject());
            if (zzl instanceof Map) {
                this.zzaCl.push((Map) zzl);
            } else if (zzl instanceof List) {
                for (Object next : (List) zzl) {
                    if (next instanceof Map) {
                        this.zzaCl.push((Map) next);
                    } else {
                        zzbg.zzan("pushAfterEvaluate: value not a Map");
                    }
                }
            } else {
                zzbg.zzan("pushAfterEvaluate: value not a Map or List");
            }
        }
    }

    private static void zza(List<zzmq.zza> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            zzbg.zzal("Invalid resource: imbalance of rule names of functions for " + str + " operation. Using default rule name instead");
        }
    }

    private static void zza(Map<String, zzak> map, zzak zzak) {
        if (map.containsKey(zzak.zzwS())) {
            throw new IllegalArgumentException("Duplicate function type name: " + zzak.zzwS());
        }
        map.put(zzak.zzwS(), zzak);
    }

    private static zzc zzf(Map<String, zzc> map, String str) {
        zzc zzc2 = map.get(str);
        if (zzc2 != null) {
            return zzc2;
        }
        zzc zzc3 = new zzc();
        map.put(str, zzc3);
        return zzc3;
    }

    private String zzxw() {
        if (this.zzaFb <= 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Integer.toString(this.zzaFb));
        for (int i = 2; i < this.zzaFb; i++) {
            sb.append(' ');
        }
        sb.append(": ");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public zzbw<Boolean> zza(zzmq.zza zza2, Set<String> set, zzch zzch) {
        zzbw<zzd.zza> zza3 = zza(this.zzaEU, zza2, set, zzch);
        Boolean zzk = zzdf.zzk(zza3.getObject());
        zzch.zzd(zzdf.zzE(zzk));
        return new zzbw<>(zzk, zza3.zzxg());
    }

    /* access modifiers changed from: package-private */
    public zzbw<Boolean> zza(zzmq.zze zze, Set<String> set, zzck zzck) {
        boolean z;
        boolean z2 = true;
        for (zzmq.zza zza2 : zze.zzyB()) {
            zzbw<Boolean> zza3 = zza(zza2, set, zzck.zzwY());
            if (zza3.getObject().booleanValue()) {
                zzck.zzf(zzdf.zzE(false));
                return new zzbw<>(false, zza3.zzxg());
            }
            z2 = z && zza3.zzxg();
        }
        for (zzmq.zza zza4 : zze.zzyA()) {
            zzbw<Boolean> zza5 = zza(zza4, set, zzck.zzwZ());
            if (!zza5.getObject().booleanValue()) {
                zzck.zzf(zzdf.zzE(false));
                return new zzbw<>(false, zza5.zzxg());
            }
            z = z && zza5.zzxg();
        }
        zzck.zzf(zzdf.zzE(true));
        return new zzbw<>(true, z);
    }

    /* access modifiers changed from: package-private */
    public zzbw<Set<zzmq.zza>> zza(String str, Set<zzmq.zze> set, Map<zzmq.zze, List<zzmq.zza>> map, Map<zzmq.zze, List<String>> map2, Map<zzmq.zze, List<zzmq.zza>> map3, Map<zzmq.zze, List<String>> map4, Set<String> set2, zzco zzco) {
        final Map<zzmq.zze, List<zzmq.zza>> map5 = map;
        final Map<zzmq.zze, List<String>> map6 = map2;
        final Map<zzmq.zze, List<zzmq.zza>> map7 = map3;
        final Map<zzmq.zze, List<String>> map8 = map4;
        return zza(set, set2, (zza) new zza() {
            public void zza(zzmq.zze zze, Set<zzmq.zza> set, Set<zzmq.zza> set2, zzck zzck) {
                List list = (List) map5.get(zze);
                List list2 = (List) map6.get(zze);
                if (list != null) {
                    set.addAll(list);
                    zzck.zzxa().zzc(list, list2);
                }
                List list3 = (List) map7.get(zze);
                List list4 = (List) map8.get(zze);
                if (list3 != null) {
                    set2.addAll(list3);
                    zzck.zzxb().zzc(list3, list4);
                }
            }
        }, zzco);
    }

    /* access modifiers changed from: package-private */
    public zzbw<Set<zzmq.zza>> zza(Set<zzmq.zze> set, zzco zzco) {
        return zza(set, (Set<String>) new HashSet(), (zza) new zza() {
            public void zza(zzmq.zze zze, Set<zzmq.zza> set, Set<zzmq.zza> set2, zzck zzck) {
                set.addAll(zze.zzyC());
                set2.addAll(zze.zzyD());
                zzck.zzxc().zzc(zze.zzyC(), zze.zzyH());
                zzck.zzxd().zzc(zze.zzyD(), zze.zzyI());
            }
        }, zzco);
    }

    /* access modifiers changed from: package-private */
    public void zza(zzak zzak) {
        zza(this.zzaEV, zzak);
    }

    /* access modifiers changed from: package-private */
    public void zzb(zzak zzak) {
        zza(this.zzaET, zzak);
    }

    /* access modifiers changed from: package-private */
    public void zzc(zzak zzak) {
        zza(this.zzaEU, zzak);
    }

    public zzbw<zzd.zza> zzdV(String str) {
        this.zzaFb = 0;
        zzag zzdL = this.zzaES.zzdL(str);
        zzbw<zzd.zza> zza2 = zza(str, (Set<String>) new HashSet(), zzdL.zzwO());
        zzdL.zzwQ();
        return zza2;
    }

    /* access modifiers changed from: package-private */
    public synchronized void zzdW(String str) {
        this.zzaFa = str;
    }

    public synchronized void zzdz(String str) {
        zzdW(str);
        zzag zzdM = this.zzaES.zzdM(str);
        zzu zzwP = zzdM.zzwP();
        for (zzmq.zza zza2 : zza(this.zzaEY, zzwP.zzwH()).getObject()) {
            zza(this.zzaET, zza2, (Set<String>) new HashSet(), zzwP.zzwG());
        }
        zzdM.zzwQ();
        zzdW((String) null);
    }

    public synchronized void zzt(List<zzc.zzi> list) {
        for (zzc.zzi next : list) {
            if (next.name == null || !next.name.startsWith("gaExperiment:")) {
                zzbg.zzam("Ignored supplemental: " + next);
            } else {
                zzaj.zza(this.zzaCl, next);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized String zzxv() {
        return this.zzaFa;
    }
}
