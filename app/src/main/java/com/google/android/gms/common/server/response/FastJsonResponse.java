package com.google.android.gms.common.server.response;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.server.converter.ConverterWrapper;
import com.google.android.gms.internal.zzhr;
import com.google.android.gms.internal.zzhz;
import com.google.android.gms.internal.zzia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class FastJsonResponse {

    public static class Field<I, O> implements SafeParcelable {
        public static final zza CREATOR = new zza();
        protected final int zzUA;
        protected final boolean zzUB;
        protected final String zzUC;
        protected final int zzUD;
        protected final Class<? extends FastJsonResponse> zzUE;
        protected final String zzUF;
        private FieldMappingDictionary zzUG;
        /* access modifiers changed from: private */
        public zza<I, O> zzUH;
        protected final int zzUy;
        protected final boolean zzUz;
        private final int zzzH;

        Field(int versionCode, int typeIn, boolean typeInArray, int typeOut, boolean typeOutArray, String outputFieldName, int safeParcelableFieldId, String concreteTypeName, ConverterWrapper wrappedConverter) {
            this.zzzH = versionCode;
            this.zzUy = typeIn;
            this.zzUz = typeInArray;
            this.zzUA = typeOut;
            this.zzUB = typeOutArray;
            this.zzUC = outputFieldName;
            this.zzUD = safeParcelableFieldId;
            if (concreteTypeName == null) {
                this.zzUE = null;
                this.zzUF = null;
            } else {
                this.zzUE = SafeParcelResponse.class;
                this.zzUF = concreteTypeName;
            }
            if (wrappedConverter == null) {
                this.zzUH = null;
            } else {
                this.zzUH = wrappedConverter.zzmu();
            }
        }

        protected Field(int typeIn, boolean typeInArray, int typeOut, boolean typeOutArray, String outputFieldName, int safeParcelableFieldId, Class<? extends FastJsonResponse> concreteType, zza<I, O> converter) {
            this.zzzH = 1;
            this.zzUy = typeIn;
            this.zzUz = typeInArray;
            this.zzUA = typeOut;
            this.zzUB = typeOutArray;
            this.zzUC = outputFieldName;
            this.zzUD = safeParcelableFieldId;
            this.zzUE = concreteType;
            if (concreteType == null) {
                this.zzUF = null;
            } else {
                this.zzUF = concreteType.getCanonicalName();
            }
            this.zzUH = converter;
        }

        public static Field zza(String str, int i, zza<?, ?> zza, boolean z) {
            return new Field(zza.zzmw(), z, zza.zzmx(), false, str, i, (Class<? extends FastJsonResponse>) null, zza);
        }

        public static <T extends FastJsonResponse> Field<T, T> zza(String str, int i, Class<T> cls) {
            return new Field<>(11, false, 11, false, str, i, cls, (zza) null);
        }

        public static <T extends FastJsonResponse> Field<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
            return new Field<>(11, true, 11, true, str, i, cls, (zza) null);
        }

        public static Field<Integer, Integer> zzh(String str, int i) {
            return new Field<>(0, false, 0, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<Double, Double> zzi(String str, int i) {
            return new Field<>(4, false, 4, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<Boolean, Boolean> zzj(String str, int i) {
            return new Field<>(6, false, 6, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<String, String> zzk(String str, int i) {
            return new Field<>(7, false, 7, false, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public static Field<ArrayList<String>, ArrayList<String>> zzl(String str, int i) {
            return new Field<>(7, true, 7, true, str, i, (Class<? extends FastJsonResponse>) null, (zza) null);
        }

        public I convertBack(O output) {
            return this.zzUH.convertBack(output);
        }

        public int describeContents() {
            zza zza = CREATOR;
            return 0;
        }

        public int getVersionCode() {
            return this.zzzH;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Field\n");
            sb.append("            versionCode=").append(this.zzzH).append(10);
            sb.append("                 typeIn=").append(this.zzUy).append(10);
            sb.append("            typeInArray=").append(this.zzUz).append(10);
            sb.append("                typeOut=").append(this.zzUA).append(10);
            sb.append("           typeOutArray=").append(this.zzUB).append(10);
            sb.append("        outputFieldName=").append(this.zzUC).append(10);
            sb.append("      safeParcelFieldId=").append(this.zzUD).append(10);
            sb.append("       concreteTypeName=").append(zzmH()).append(10);
            if (zzmG() != null) {
                sb.append("     concreteType.class=").append(zzmG().getCanonicalName()).append(10);
            }
            sb.append("          converterName=").append(this.zzUH == null ? "null" : this.zzUH.getClass().getCanonicalName()).append(10);
            return sb.toString();
        }

        public void writeToParcel(Parcel out, int flags) {
            zza zza = CREATOR;
            zza.zza(this, out, flags);
        }

        public void zza(FieldMappingDictionary fieldMappingDictionary) {
            this.zzUG = fieldMappingDictionary;
        }

        public Field<I, O> zzmB() {
            return new Field<>(this.zzzH, this.zzUy, this.zzUz, this.zzUA, this.zzUB, this.zzUC, this.zzUD, this.zzUF, zzmJ());
        }

        public boolean zzmC() {
            return this.zzUz;
        }

        public boolean zzmD() {
            return this.zzUB;
        }

        public String zzmE() {
            return this.zzUC;
        }

        public int zzmF() {
            return this.zzUD;
        }

        public Class<? extends FastJsonResponse> zzmG() {
            return this.zzUE;
        }

        /* access modifiers changed from: package-private */
        public String zzmH() {
            if (this.zzUF == null) {
                return null;
            }
            return this.zzUF;
        }

        public boolean zzmI() {
            return this.zzUH != null;
        }

        /* access modifiers changed from: package-private */
        public ConverterWrapper zzmJ() {
            if (this.zzUH == null) {
                return null;
            }
            return ConverterWrapper.zza(this.zzUH);
        }

        public Map<String, Field<?, ?>> zzmK() {
            zzv.zzr(this.zzUF);
            zzv.zzr(this.zzUG);
            return this.zzUG.zzbX(this.zzUF);
        }

        public int zzmw() {
            return this.zzUy;
        }

        public int zzmx() {
            return this.zzUA;
        }
    }

    public interface zza<I, O> {
        I convertBack(O o);

        int zzmw();

        int zzmx();
    }

    private void zza(StringBuilder sb, Field field, Object obj) {
        if (field.zzmw() == 11) {
            sb.append(((FastJsonResponse) field.zzmG().cast(obj)).toString());
        } else if (field.zzmw() == 7) {
            sb.append("\"");
            sb.append(zzhz.zzbY((String) obj));
            sb.append("\"");
        } else {
            sb.append(obj);
        }
    }

    private void zza(StringBuilder sb, Field field, ArrayList<Object> arrayList) {
        sb.append("[");
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(",");
            }
            Object obj = arrayList.get(i);
            if (obj != null) {
                zza(sb, field, obj);
            }
        }
        sb.append("]");
    }

    public String toString() {
        Map<String, Field<?, ?>> zzmy = zzmy();
        StringBuilder sb = new StringBuilder(100);
        for (String next : zzmy.keySet()) {
            Field field = zzmy.get(next);
            if (zza(field)) {
                Object zza2 = zza(field, zzb(field));
                if (sb.length() == 0) {
                    sb.append("{");
                } else {
                    sb.append(",");
                }
                sb.append("\"").append(next).append("\":");
                if (zza2 != null) {
                    switch (field.zzmx()) {
                        case 8:
                            sb.append("\"").append(zzhr.zzg((byte[]) zza2)).append("\"");
                            break;
                        case 9:
                            sb.append("\"").append(zzhr.zzh((byte[]) zza2)).append("\"");
                            break;
                        case 10:
                            zzia.zza(sb, (HashMap) zza2);
                            break;
                        default:
                            if (!field.zzmC()) {
                                zza(sb, field, zza2);
                                break;
                            } else {
                                zza(sb, field, (ArrayList<Object>) (ArrayList) zza2);
                                break;
                            }
                    }
                } else {
                    sb.append("null");
                }
            }
        }
        if (sb.length() > 0) {
            sb.append("}");
        } else {
            sb.append("{}");
        }
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public <O, I> I zza(Field<I, O> field, Object obj) {
        return field.zzUH != null ? field.convertBack(obj) : obj;
    }

    /* access modifiers changed from: protected */
    public boolean zza(Field field) {
        return field.zzmx() == 11 ? field.zzmD() ? zzbW(field.zzmE()) : zzbV(field.zzmE()) : zzbU(field.zzmE());
    }

    /* access modifiers changed from: protected */
    public Object zzb(Field field) {
        String zzmE = field.zzmE();
        if (field.zzmG() == null) {
            return zzbT(field.zzmE());
        }
        zzv.zza(zzbT(field.zzmE()) == null, "Concrete field shouldn't be value object: %s", field.zzmE());
        HashMap<String, Object> zzmA = field.zzmD() ? zzmA() : zzmz();
        if (zzmA != null) {
            return zzmA.get(zzmE);
        }
        try {
            return getClass().getMethod("get" + Character.toUpperCase(zzmE.charAt(0)) + zzmE.substring(1), new Class[0]).invoke(this, new Object[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public abstract Object zzbT(String str);

    /* access modifiers changed from: protected */
    public abstract boolean zzbU(String str);

    /* access modifiers changed from: protected */
    public boolean zzbV(String str) {
        throw new UnsupportedOperationException("Concrete types not supported");
    }

    /* access modifiers changed from: protected */
    public boolean zzbW(String str) {
        throw new UnsupportedOperationException("Concrete type arrays not supported");
    }

    public HashMap<String, Object> zzmA() {
        return null;
    }

    public abstract Map<String, Field<?, ?>> zzmy();

    public HashMap<String, Object> zzmz() {
        return null;
    }
}
