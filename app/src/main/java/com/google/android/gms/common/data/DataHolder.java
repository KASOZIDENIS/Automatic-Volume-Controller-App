package com.google.android.gms.common.data;

import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class DataHolder implements SafeParcelable {
    public static final zze CREATOR = new zze();
    private static final zza zzRI = new zza(new String[0], (String) null) {
    };
    boolean mClosed;
    private final int zzOJ;
    private final String[] zzRA;
    Bundle zzRB;
    private final CursorWindow[] zzRC;
    private final Bundle zzRD;
    int[] zzRE;
    int zzRF;
    private Object zzRG;
    private boolean zzRH;
    private final int zzzH;

    public static class zza {
        /* access modifiers changed from: private */
        public final String[] zzRA;
        /* access modifiers changed from: private */
        public final ArrayList<HashMap<String, Object>> zzRJ;
        private final String zzRK;
        private final HashMap<Object, Integer> zzRL;
        private boolean zzRM;
        private String zzRN;

        private zza(String[] strArr, String str) {
            this.zzRA = (String[]) zzv.zzr(strArr);
            this.zzRJ = new ArrayList<>();
            this.zzRK = str;
            this.zzRL = new HashMap<>();
            this.zzRM = false;
            this.zzRN = null;
        }
    }

    public static class zzb extends RuntimeException {
        public zzb(String str) {
            super(str);
        }
    }

    DataHolder(int versionCode, String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.zzRH = true;
        this.zzzH = versionCode;
        this.zzRA = columns;
        this.zzRC = windows;
        this.zzOJ = statusCode;
        this.zzRD = metadata;
    }

    private DataHolder(zza builder, int statusCode, Bundle metadata) {
        this(builder.zzRA, zza(builder, -1), statusCode, metadata);
    }

    public DataHolder(String[] columns, CursorWindow[] windows, int statusCode, Bundle metadata) {
        this.mClosed = false;
        this.zzRH = true;
        this.zzzH = 1;
        this.zzRA = (String[]) zzv.zzr(columns);
        this.zzRC = (CursorWindow[]) zzv.zzr(windows);
        this.zzOJ = statusCode;
        this.zzRD = metadata;
        zzlq();
    }

    public static DataHolder zza(int i, Bundle bundle) {
        return new DataHolder(zzRI, i, bundle);
    }

    private static CursorWindow[] zza(zza zza2, int i) {
        int i2;
        boolean z;
        CursorWindow cursorWindow;
        if (zza2.zzRA.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList zzb2 = (i < 0 || i >= zza2.zzRJ.size()) ? zza2.zzRJ : zza2.zzRJ.subList(0, i);
        int size = zzb2.size();
        CursorWindow cursorWindow2 = new CursorWindow(false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cursorWindow2);
        cursorWindow2.setNumColumns(zza2.zzRA.length);
        int i3 = 0;
        boolean z2 = false;
        while (i3 < size) {
            try {
                if (!cursorWindow2.allocRow()) {
                    Log.d("DataHolder", "Allocating additional cursor window for large data set (row " + i3 + ")");
                    cursorWindow2 = new CursorWindow(false);
                    cursorWindow2.setStartPosition(i3);
                    cursorWindow2.setNumColumns(zza2.zzRA.length);
                    arrayList.add(cursorWindow2);
                    if (!cursorWindow2.allocRow()) {
                        Log.e("DataHolder", "Unable to allocate row to hold data.");
                        arrayList.remove(cursorWindow2);
                        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
                    }
                }
                Map map = (Map) zzb2.get(i3);
                boolean z3 = true;
                for (int i4 = 0; i4 < zza2.zzRA.length && z3; i4++) {
                    String str = zza2.zzRA[i4];
                    Object obj = map.get(str);
                    if (obj == null) {
                        z3 = cursorWindow2.putNull(i3, i4);
                    } else if (obj instanceof String) {
                        z3 = cursorWindow2.putString((String) obj, i3, i4);
                    } else if (obj instanceof Long) {
                        z3 = cursorWindow2.putLong(((Long) obj).longValue(), i3, i4);
                    } else if (obj instanceof Integer) {
                        z3 = cursorWindow2.putLong((long) ((Integer) obj).intValue(), i3, i4);
                    } else if (obj instanceof Boolean) {
                        z3 = cursorWindow2.putLong(((Boolean) obj).booleanValue() ? 1 : 0, i3, i4);
                    } else if (obj instanceof byte[]) {
                        z3 = cursorWindow2.putBlob((byte[]) obj, i3, i4);
                    } else if (obj instanceof Double) {
                        z3 = cursorWindow2.putDouble(((Double) obj).doubleValue(), i3, i4);
                    } else if (obj instanceof Float) {
                        z3 = cursorWindow2.putDouble((double) ((Float) obj).floatValue(), i3, i4);
                    } else {
                        throw new IllegalArgumentException("Unsupported object for column " + str + ": " + obj);
                    }
                }
                if (z3) {
                    i2 = i3;
                    z = false;
                    cursorWindow = cursorWindow2;
                } else if (z2) {
                    throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                } else {
                    Log.d("DataHolder", "Couldn't populate window data for row " + i3 + " - allocating new window.");
                    cursorWindow2.freeLastRow();
                    CursorWindow cursorWindow3 = new CursorWindow(false);
                    cursorWindow3.setStartPosition(i3);
                    cursorWindow3.setNumColumns(zza2.zzRA.length);
                    arrayList.add(cursorWindow3);
                    i2 = i3 - 1;
                    cursorWindow = cursorWindow3;
                    z = true;
                }
                z2 = z;
                cursorWindow2 = cursorWindow;
                i3 = i2 + 1;
            } catch (RuntimeException e) {
                RuntimeException runtimeException = e;
                int size2 = arrayList.size();
                for (int i5 = 0; i5 < size2; i5++) {
                    ((CursorWindow) arrayList.get(i5)).close();
                }
                throw runtimeException;
            }
        }
        return (CursorWindow[]) arrayList.toArray(new CursorWindow[arrayList.size()]);
    }

    public static DataHolder zzaE(int i) {
        return zza(i, (Bundle) null);
    }

    private void zzf(String str, int i) {
        if (this.zzRB == null || !this.zzRB.containsKey(str)) {
            throw new IllegalArgumentException("No such column: " + str);
        } else if (isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        } else if (i < 0 || i >= this.zzRF) {
            throw new CursorIndexOutOfBoundsException(i, this.zzRF);
        }
    }

    public void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (CursorWindow close : this.zzRC) {
                    close.close();
                }
            }
        }
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            if (this.zzRH && this.zzRC.length > 0 && !isClosed()) {
                Log.e("DataBuffer", "Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (" + (this.zzRG == null ? "internal object: " + toString() : this.zzRG.toString()) + ")");
                close();
            }
        } finally {
            super.finalize();
        }
    }

    public int getCount() {
        return this.zzRF;
    }

    public int getStatusCode() {
        return this.zzOJ;
    }

    /* access modifiers changed from: package-private */
    public int getVersionCode() {
        return this.zzzH;
    }

    public boolean isClosed() {
        boolean z;
        synchronized (this) {
            z = this.mClosed;
        }
        return z;
    }

    public void writeToParcel(Parcel dest, int flags) {
        zze.zza(this, dest, flags);
    }

    public void zza(String str, int i, int i2, CharArrayBuffer charArrayBuffer) {
        zzf(str, i);
        this.zzRC[i2].copyStringToBuffer(i, this.zzRB.getInt(str), charArrayBuffer);
    }

    public int zzaD(int i) {
        int i2 = 0;
        zzv.zzP(i >= 0 && i < this.zzRF);
        while (true) {
            if (i2 >= this.zzRE.length) {
                break;
            } else if (i < this.zzRE[i2]) {
                i2--;
                break;
            } else {
                i2++;
            }
        }
        return i2 == this.zzRE.length ? i2 - 1 : i2;
    }

    public long zzb(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].getLong(i, this.zzRB.getInt(str));
    }

    public boolean zzbF(String str) {
        return this.zzRB.containsKey(str);
    }

    public int zzc(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].getInt(i, this.zzRB.getInt(str));
    }

    public String zzd(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].getString(i, this.zzRB.getInt(str));
    }

    public boolean zze(String str, int i, int i2) {
        zzf(str, i);
        return Long.valueOf(this.zzRC[i2].getLong(i, this.zzRB.getInt(str))).longValue() == 1;
    }

    public float zzf(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].getFloat(i, this.zzRB.getInt(str));
    }

    public byte[] zzg(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].getBlob(i, this.zzRB.getInt(str));
    }

    public Uri zzh(String str, int i, int i2) {
        String zzd = zzd(str, i, i2);
        if (zzd == null) {
            return null;
        }
        return Uri.parse(zzd);
    }

    public boolean zzi(String str, int i, int i2) {
        zzf(str, i);
        return this.zzRC[i2].isNull(i, this.zzRB.getInt(str));
    }

    public Bundle zzlm() {
        return this.zzRD;
    }

    public void zzlq() {
        this.zzRB = new Bundle();
        for (int i = 0; i < this.zzRA.length; i++) {
            this.zzRB.putInt(this.zzRA[i], i);
        }
        this.zzRE = new int[this.zzRC.length];
        int i2 = 0;
        for (int i3 = 0; i3 < this.zzRC.length; i3++) {
            this.zzRE[i3] = i2;
            i2 += this.zzRC[i3].getNumRows() - (i2 - this.zzRC[i3].getStartPosition());
        }
        this.zzRF = i2;
    }

    /* access modifiers changed from: package-private */
    public String[] zzlr() {
        return this.zzRA;
    }

    /* access modifiers changed from: package-private */
    public CursorWindow[] zzls() {
        return this.zzRC;
    }

    public void zzm(Object obj) {
        this.zzRG = obj;
    }
}
