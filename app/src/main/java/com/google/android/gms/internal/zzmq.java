package com.google.android.gms.internal;

import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzd;
import com.google.android.gms.tagmanager.zzbg;
import com.google.android.gms.tagmanager.zzdf;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzmq {

    public static class zza {
        private final zzd.zza zzaFi;
        private final Map<String, zzd.zza> zzaHk;

        private zza(Map<String, zzd.zza> map, zzd.zza zza) {
            this.zzaHk = map;
            this.zzaFi = zza;
        }

        public static zzb zzys() {
            return new zzb();
        }

        public String toString() {
            return "Properties: " + zzyt() + " pushAfterEvaluate: " + this.zzaFi;
        }

        public void zza(String str, zzd.zza zza) {
            this.zzaHk.put(str, zza);
        }

        public zzd.zza zzxy() {
            return this.zzaFi;
        }

        public Map<String, zzd.zza> zzyt() {
            return Collections.unmodifiableMap(this.zzaHk);
        }
    }

    public static class zzb {
        private zzd.zza zzaFi;
        private final Map<String, zzd.zza> zzaHk;

        private zzb() {
            this.zzaHk = new HashMap();
        }

        public zzb zzb(String str, zzd.zza zza) {
            this.zzaHk.put(str, zza);
            return this;
        }

        public zzb zzq(zzd.zza zza) {
            this.zzaFi = zza;
            return this;
        }

        public zza zzyu() {
            return new zza(this.zzaHk, this.zzaFi);
        }
    }

    public static class zzc {
        private final List<zze> zzaHl;
        private final Map<String, List<zza>> zzaHm;
        private final int zzaHn;
        private final String zzacK;

        private zzc(List<zze> list, Map<String, List<zza>> map, String str, int i) {
            this.zzaHl = Collections.unmodifiableList(list);
            this.zzaHm = Collections.unmodifiableMap(map);
            this.zzacK = str;
            this.zzaHn = i;
        }

        public static zzd zzyv() {
            return new zzd();
        }

        public String getVersion() {
            return this.zzacK;
        }

        public String toString() {
            return "Rules: " + zzyw() + "  Macros: " + this.zzaHm;
        }

        public List<zze> zzyw() {
            return this.zzaHl;
        }

        public Map<String, List<zza>> zzyx() {
            return this.zzaHm;
        }
    }

    public static class zzd {
        private final List<zze> zzaHl;
        private final Map<String, List<zza>> zzaHm;
        private int zzaHn;
        private String zzacK;

        private zzd() {
            this.zzaHl = new ArrayList();
            this.zzaHm = new HashMap();
            this.zzacK = "";
            this.zzaHn = 0;
        }

        public zzd zzb(zze zze) {
            this.zzaHl.add(zze);
            return this;
        }

        public zzd zzc(zza zza) {
            String zzg = zzdf.zzg(zza.zzyt().get(zzb.INSTANCE_NAME.toString()));
            List list = this.zzaHm.get(zzg);
            if (list == null) {
                list = new ArrayList();
                this.zzaHm.put(zzg, list);
            }
            list.add(zza);
            return this;
        }

        public zzd zzen(String str) {
            this.zzacK = str;
            return this;
        }

        public zzd zzhM(int i) {
            this.zzaHn = i;
            return this;
        }

        public zzc zzyy() {
            return new zzc(this.zzaHl, this.zzaHm, this.zzacK, this.zzaHn);
        }
    }

    public static class zze {
        private final List<zza> zzaHo;
        private final List<zza> zzaHp;
        private final List<zza> zzaHq;
        private final List<zza> zzaHr;
        private final List<zza> zzaHs;
        private final List<zza> zzaHt;
        private final List<String> zzaHu;
        private final List<String> zzaHv;
        private final List<String> zzaHw;
        private final List<String> zzaHx;

        private zze(List<zza> list, List<zza> list2, List<zza> list3, List<zza> list4, List<zza> list5, List<zza> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.zzaHo = Collections.unmodifiableList(list);
            this.zzaHp = Collections.unmodifiableList(list2);
            this.zzaHq = Collections.unmodifiableList(list3);
            this.zzaHr = Collections.unmodifiableList(list4);
            this.zzaHs = Collections.unmodifiableList(list5);
            this.zzaHt = Collections.unmodifiableList(list6);
            this.zzaHu = Collections.unmodifiableList(list7);
            this.zzaHv = Collections.unmodifiableList(list8);
            this.zzaHw = Collections.unmodifiableList(list9);
            this.zzaHx = Collections.unmodifiableList(list10);
        }

        public static zzf zzyz() {
            return new zzf();
        }

        public String toString() {
            return "Positive predicates: " + zzyA() + "  Negative predicates: " + zzyB() + "  Add tags: " + zzyC() + "  Remove tags: " + zzyD() + "  Add macros: " + zzyE() + "  Remove macros: " + zzyJ();
        }

        public List<zza> zzyA() {
            return this.zzaHo;
        }

        public List<zza> zzyB() {
            return this.zzaHp;
        }

        public List<zza> zzyC() {
            return this.zzaHq;
        }

        public List<zza> zzyD() {
            return this.zzaHr;
        }

        public List<zza> zzyE() {
            return this.zzaHs;
        }

        public List<String> zzyF() {
            return this.zzaHu;
        }

        public List<String> zzyG() {
            return this.zzaHv;
        }

        public List<String> zzyH() {
            return this.zzaHw;
        }

        public List<String> zzyI() {
            return this.zzaHx;
        }

        public List<zza> zzyJ() {
            return this.zzaHt;
        }
    }

    public static class zzf {
        private final List<zza> zzaHo;
        private final List<zza> zzaHp;
        private final List<zza> zzaHq;
        private final List<zza> zzaHr;
        private final List<zza> zzaHs;
        private final List<zza> zzaHt;
        private final List<String> zzaHu;
        private final List<String> zzaHv;
        private final List<String> zzaHw;
        private final List<String> zzaHx;

        private zzf() {
            this.zzaHo = new ArrayList();
            this.zzaHp = new ArrayList();
            this.zzaHq = new ArrayList();
            this.zzaHr = new ArrayList();
            this.zzaHs = new ArrayList();
            this.zzaHt = new ArrayList();
            this.zzaHu = new ArrayList();
            this.zzaHv = new ArrayList();
            this.zzaHw = new ArrayList();
            this.zzaHx = new ArrayList();
        }

        public zzf zzd(zza zza) {
            this.zzaHo.add(zza);
            return this;
        }

        public zzf zze(zza zza) {
            this.zzaHp.add(zza);
            return this;
        }

        public zzf zzeo(String str) {
            this.zzaHw.add(str);
            return this;
        }

        public zzf zzep(String str) {
            this.zzaHx.add(str);
            return this;
        }

        public zzf zzeq(String str) {
            this.zzaHu.add(str);
            return this;
        }

        public zzf zzer(String str) {
            this.zzaHv.add(str);
            return this;
        }

        public zzf zzf(zza zza) {
            this.zzaHq.add(zza);
            return this;
        }

        public zzf zzg(zza zza) {
            this.zzaHr.add(zza);
            return this;
        }

        public zzf zzh(zza zza) {
            this.zzaHs.add(zza);
            return this;
        }

        public zzf zzi(zza zza) {
            this.zzaHt.add(zza);
            return this;
        }

        public zze zzyK() {
            return new zze(this.zzaHo, this.zzaHp, this.zzaHq, this.zzaHr, this.zzaHs, this.zzaHt, this.zzaHu, this.zzaHv, this.zzaHw, this.zzaHx);
        }
    }

    public static class zzg extends Exception {
        public zzg(String str) {
            super(str);
        }
    }

    private static zzd.zza zza(int i, zzc.zzf zzf2, zzd.zza[] zzaArr, Set<Integer> set) throws zzg {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            zzel("Value cycle detected.  Current value reference: " + i + "." + "  Previous value references: " + set + ".");
        }
        zzd.zza zza2 = (zzd.zza) zza(zzf2.zzgv, i, "values");
        if (zzaArr[i] != null) {
            return zzaArr[i];
        }
        zzd.zza zza3 = null;
        set.add(Integer.valueOf(i));
        switch (zza2.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                zza3 = zza2;
                break;
            case 2:
                zzc.zzh zzp = zzp(zza2);
                zza3 = zzo(zza2);
                zza3.zzhl = new zzd.zza[zzp.zzgW.length];
                int[] iArr = zzp.zzgW;
                int length = iArr.length;
                int i3 = 0;
                while (i2 < length) {
                    zza3.zzhl[i3] = zza(iArr[i2], zzf2, zzaArr, set);
                    i2++;
                    i3++;
                }
                break;
            case 3:
                zza3 = zzo(zza2);
                zzc.zzh zzp2 = zzp(zza2);
                if (zzp2.zzgX.length != zzp2.zzgY.length) {
                    zzel("Uneven map keys (" + zzp2.zzgX.length + ") and map values (" + zzp2.zzgY.length + ")");
                }
                zza3.zzhm = new zzd.zza[zzp2.zzgX.length];
                zza3.zzhn = new zzd.zza[zzp2.zzgX.length];
                int[] iArr2 = zzp2.zzgX;
                int length2 = iArr2.length;
                int i4 = 0;
                int i5 = 0;
                while (i4 < length2) {
                    zza3.zzhm[i5] = zza(iArr2[i4], zzf2, zzaArr, set);
                    i4++;
                    i5++;
                }
                int[] iArr3 = zzp2.zzgY;
                int length3 = iArr3.length;
                int i6 = 0;
                while (i2 < length3) {
                    zza3.zzhn[i6] = zza(iArr3[i2], zzf2, zzaArr, set);
                    i2++;
                    i6++;
                }
                break;
            case 4:
                zza3 = zzo(zza2);
                zza3.zzho = zzdf.zzg(zza(zzp(zza2).zzhb, zzf2, zzaArr, set));
                break;
            case 7:
                zza3 = zzo(zza2);
                zzc.zzh zzp3 = zzp(zza2);
                zza3.zzhs = new zzd.zza[zzp3.zzha.length];
                int[] iArr4 = zzp3.zzha;
                int length4 = iArr4.length;
                int i7 = 0;
                while (i2 < length4) {
                    zza3.zzhs[i7] = zza(iArr4[i2], zzf2, zzaArr, set);
                    i2++;
                    i7++;
                }
                break;
        }
        if (zza3 == null) {
            zzel("Invalid value: " + zza2);
        }
        zzaArr[i] = zza3;
        set.remove(Integer.valueOf(i));
        return zza3;
    }

    private static zza zza(zzc.zzb zzb2, zzc.zzf zzf2, zzd.zza[] zzaArr, int i) throws zzg {
        zzb zzys = zza.zzys();
        for (int valueOf : zzb2.zzgf) {
            zzc.zze zze2 = (zzc.zze) zza(zzf2.zzgw, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(zzf2.zzgu, zze2.key, "keys");
            zzd.zza zza2 = (zzd.zza) zza(zzaArr, zze2.value, "values");
            if (zzb.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzys.zzq(zza2);
            } else {
                zzys.zzb(str, zza2);
            }
        }
        return zzys.zzyu();
    }

    private static zze zza(zzc.zzg zzg2, List<zza> list, List<zza> list2, List<zza> list3, zzc.zzf zzf2) {
        zzf zzyz = zze.zzyz();
        for (int valueOf : zzg2.zzgK) {
            zzyz.zzd(list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : zzg2.zzgL) {
            zzyz.zze(list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf3 : zzg2.zzgM) {
            zzyz.zzf(list.get(Integer.valueOf(valueOf3).intValue()));
        }
        for (int valueOf4 : zzg2.zzgO) {
            zzyz.zzeo(zzf2.zzgv[Integer.valueOf(valueOf4).intValue()].zzhk);
        }
        for (int valueOf5 : zzg2.zzgN) {
            zzyz.zzg(list.get(Integer.valueOf(valueOf5).intValue()));
        }
        for (int valueOf6 : zzg2.zzgP) {
            zzyz.zzep(zzf2.zzgv[Integer.valueOf(valueOf6).intValue()].zzhk);
        }
        for (int valueOf7 : zzg2.zzgQ) {
            zzyz.zzh(list2.get(Integer.valueOf(valueOf7).intValue()));
        }
        for (int valueOf8 : zzg2.zzgS) {
            zzyz.zzeq(zzf2.zzgv[Integer.valueOf(valueOf8).intValue()].zzhk);
        }
        for (int valueOf9 : zzg2.zzgR) {
            zzyz.zzi(list2.get(Integer.valueOf(valueOf9).intValue()));
        }
        for (int valueOf10 : zzg2.zzgT) {
            zzyz.zzer(zzf2.zzgv[Integer.valueOf(valueOf10).intValue()].zzhk);
        }
        return zzyz.zzyK();
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzg {
        if (i < 0 || i >= tArr.length) {
            zzel("Index out of bounds detected: " + i + " in " + str);
        }
        return tArr[i];
    }

    public static zzc zzb(zzc.zzf zzf2) throws zzg {
        zzd.zza[] zzaArr = new zzd.zza[zzf2.zzgv.length];
        for (int i = 0; i < zzf2.zzgv.length; i++) {
            zza(i, zzf2, zzaArr, (Set<Integer>) new HashSet(0));
        }
        zzd zzyv = zzc.zzyv();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < zzf2.zzgy.length; i2++) {
            arrayList.add(zza(zzf2.zzgy[i2], zzf2, zzaArr, i2));
        }
        ArrayList arrayList2 = new ArrayList();
        for (int i3 = 0; i3 < zzf2.zzgz.length; i3++) {
            arrayList2.add(zza(zzf2.zzgz[i3], zzf2, zzaArr, i3));
        }
        ArrayList arrayList3 = new ArrayList();
        for (int i4 = 0; i4 < zzf2.zzgx.length; i4++) {
            zza zza2 = zza(zzf2.zzgx[i4], zzf2, zzaArr, i4);
            zzyv.zzc(zza2);
            arrayList3.add(zza2);
        }
        for (zzc.zzg zza3 : zzf2.zzgA) {
            zzyv.zzb(zza(zza3, arrayList, arrayList3, arrayList2, zzf2));
        }
        zzyv.zzen(zzf2.version);
        zzyv.zzhM(zzf2.zzgI);
        return zzyv.zzyy();
    }

    public static void zzc(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    private static void zzel(String str) throws zzg {
        zzbg.zzak(str);
        throw new zzg(str);
    }

    public static zzd.zza zzo(zzd.zza zza2) {
        zzd.zza zza3 = new zzd.zza();
        zza3.type = zza2.type;
        zza3.zzht = (int[]) zza2.zzht.clone();
        if (zza2.zzhu) {
            zza3.zzhu = zza2.zzhu;
        }
        return zza3;
    }

    private static zzc.zzh zzp(zzd.zza zza2) throws zzg {
        if (((zzc.zzh) zza2.zza(zzc.zzh.zzgU)) == null) {
            zzel("Expected a ServingValue and didn't get one. Value is: " + zza2);
        }
        return (zzc.zzh) zza2.zza(zzc.zzh.zzgU);
    }
}
