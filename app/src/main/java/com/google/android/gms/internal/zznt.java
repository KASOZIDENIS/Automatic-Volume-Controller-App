package com.google.android.gms.internal;

import com.google.android.gms.internal.zzns;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zznt<M extends zzns<M>, T> {
    public final int tag;
    protected final int type;
    protected final Class<T> zzaNJ;
    protected final boolean zzaNK;

    private zznt(int i, Class<T> cls, int i2, boolean z) {
        this.type = i;
        this.zzaNJ = cls;
        this.tag = i2;
        this.zzaNK = z;
    }

    private T zzA(List<zzoa> list) {
        if (list.isEmpty()) {
            return null;
        }
        return this.zzaNJ.cast(zzA(zznq.zzv(list.get(list.size() - 1).zzaNU)));
    }

    @Deprecated
    public static <M extends zzns<M>, T extends zzny> zznt<M, T> zza(int i, Class<T> cls, int i2) {
        return new zznt<>(i, cls, i2, false);
    }

    private T zzz(List<zzoa> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            zzoa zzoa = list.get(i);
            if (zzoa.zzaNU.length != 0) {
                zza(zzoa, (List<Object>) arrayList);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            return null;
        }
        T cast = this.zzaNJ.cast(Array.newInstance(this.zzaNJ.getComponentType(), size));
        for (int i2 = 0; i2 < size; i2++) {
            Array.set(cast, i2, arrayList.get(i2));
        }
        return cast;
    }

    /* access modifiers changed from: protected */
    public Object zzA(zznq zznq) {
        Class<?> componentType = this.zzaNK ? this.zzaNJ.getComponentType() : this.zzaNJ;
        try {
            switch (this.type) {
                case 10:
                    zzny zzny = (zzny) componentType.newInstance();
                    zznq.zza(zzny, zzob.zzjG(this.tag));
                    return zzny;
                case 11:
                    zzny zzny2 = (zzny) componentType.newInstance();
                    zznq.zza(zzny2);
                    return zzny2;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (InstantiationException e) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e);
        } catch (IllegalAccessException e2) {
            throw new IllegalArgumentException("Error creating instance of class " + componentType, e2);
        } catch (IOException e3) {
            throw new IllegalArgumentException("Error reading extension field", e3);
        }
    }

    /* access modifiers changed from: package-private */
    public int zzM(Object obj) {
        return this.zzaNK ? zzN(obj) : zzO(obj);
    }

    /* access modifiers changed from: protected */
    public int zzN(Object obj) {
        int i = 0;
        int length = Array.getLength(obj);
        for (int i2 = 0; i2 < length; i2++) {
            if (Array.get(obj, i2) != null) {
                i += zzO(Array.get(obj, i2));
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int zzO(Object obj) {
        int zzjG = zzob.zzjG(this.tag);
        switch (this.type) {
            case 10:
                return zznr.zzb(zzjG, (zzny) obj);
            case 11:
                return zznr.zzc(zzjG, (zzny) obj);
            default:
                throw new IllegalArgumentException("Unknown type " + this.type);
        }
    }

    /* access modifiers changed from: protected */
    public void zza(zzoa zzoa, List<Object> list) {
        list.add(zzA(zznq.zzv(zzoa.zzaNU)));
    }

    /* access modifiers changed from: package-private */
    public void zza(Object obj, zznr zznr) throws IOException {
        if (this.zzaNK) {
            zzc(obj, zznr);
        } else {
            zzb(obj, zznr);
        }
    }

    /* access modifiers changed from: protected */
    public void zzb(Object obj, zznr zznr) {
        try {
            zznr.zzjy(this.tag);
            switch (this.type) {
                case 10:
                    int zzjG = zzob.zzjG(this.tag);
                    zznr.zzb((zzny) obj);
                    zznr.zzB(zzjG, 4);
                    return;
                case 11:
                    zznr.zzc((zzny) obj);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown type " + this.type);
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        throw new IllegalStateException(e);
    }

    /* access modifiers changed from: protected */
    public void zzc(Object obj, zznr zznr) {
        int length = Array.getLength(obj);
        for (int i = 0; i < length; i++) {
            Object obj2 = Array.get(obj, i);
            if (obj2 != null) {
                zzb(obj2, zznr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final T zzy(List<zzoa> list) {
        if (list == null) {
            return null;
        }
        return this.zzaNK ? zzz(list) : zzA(list);
    }
}
