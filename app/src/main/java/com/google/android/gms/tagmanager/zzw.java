package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.internal.zzht;
import com.google.android.gms.internal.zzhv;
import com.google.android.gms.tagmanager.DataLayer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class zzw implements DataLayer.zzc {
    /* access modifiers changed from: private */
    public static final String zzaDb = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' STRING NOT NULL, '%s' BLOB NOT NULL, '%s' INTEGER NOT NULL);", new Object[]{"datalayer", "ID", "key", "value", "expires"});
    /* access modifiers changed from: private */
    public final Context mContext;
    private final Executor zzaDc;
    private zza zzaDd;
    private int zzaDe;
    private zzht zznR;

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0048  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean zza(java.lang.String r11, android.database.sqlite.SQLiteDatabase r12) {
            /*
                r10 = this;
                r8 = 0
                r9 = 0
                java.lang.String r1 = "SQLITE_MASTER"
                r0 = 1
                java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r0 = 0
                java.lang.String r3 = "name"
                r2[r0] = r3     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                java.lang.String r3 = "name=?"
                r0 = 1
                java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r0 = 0
                r4[r0] = r11     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                r5 = 0
                r6 = 0
                r7 = 0
                r0 = r12
                android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0045 }
                boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0053, all -> 0x004c }
                if (r1 == 0) goto L_0x0025
                r1.close()
            L_0x0025:
                return r0
            L_0x0026:
                r0 = move-exception
                r0 = r9
            L_0x0028:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x004f }
                r1.<init>()     // Catch:{ all -> 0x004f }
                java.lang.String r2 = "Error querying for table "
                java.lang.StringBuilder r1 = r1.append(r2)     // Catch:{ all -> 0x004f }
                java.lang.StringBuilder r1 = r1.append(r11)     // Catch:{ all -> 0x004f }
                java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x004f }
                com.google.android.gms.tagmanager.zzbg.zzan(r1)     // Catch:{ all -> 0x004f }
                if (r0 == 0) goto L_0x0043
                r0.close()
            L_0x0043:
                r0 = r8
                goto L_0x0025
            L_0x0045:
                r0 = move-exception
            L_0x0046:
                if (r9 == 0) goto L_0x004b
                r9.close()
            L_0x004b:
                throw r0
            L_0x004c:
                r0 = move-exception
                r9 = r1
                goto L_0x0046
            L_0x004f:
                r1 = move-exception
                r9 = r0
                r0 = r1
                goto L_0x0046
            L_0x0053:
                r0 = move-exception
                r0 = r1
                goto L_0x0028
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zza.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        /* JADX INFO: finally extract failed */
        private void zzc(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM datalayer WHERE 0", (String[]) null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                rawQuery.close();
                if (!hashSet.remove("key") || !hashSet.remove("value") || !hashSet.remove("ID") || !hashSet.remove("expires")) {
                    throw new SQLiteException("Database column missing");
                } else if (!hashSet.isEmpty()) {
                    throw new SQLiteException("Database has extra columns");
                }
            } catch (Throwable th) {
                rawQuery.close();
                throw th;
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzw.this.mContext.getDatabasePath("google_tagmanager.db").delete();
            }
            return sQLiteDatabase == null ? super.getWritableDatabase() : sQLiteDatabase;
        }

        public void onCreate(SQLiteDatabase db) {
            zzal.zzaQ(db.getPath());
        }

        public void onOpen(SQLiteDatabase db) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = db.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (!zza("datalayer", db)) {
                db.execSQL(zzw.zzaDb);
            } else {
                zzc(db);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    private static class zzb {
        final byte[] zzaDk;
        final String zzra;

        zzb(String str, byte[] bArr) {
            this.zzra = str;
            this.zzaDk = bArr;
        }

        public String toString() {
            return "KeyAndSerialized: key = " + this.zzra + " serialized hash = " + Arrays.hashCode(this.zzaDk);
        }
    }

    public zzw(Context context) {
        this(context, zzhv.zznd(), "google_tagmanager.db", 2000, Executors.newSingleThreadExecutor());
    }

    zzw(Context context, zzht zzht, String str, int i, Executor executor) {
        this.mContext = context;
        this.zznR = zzht;
        this.zzaDe = i;
        this.zzaDc = executor;
        this.zzaDd = new zza(this.mContext, str);
    }

    private void zzR(long j) {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for deleteOlderThan.");
        if (zzdJ != null) {
            try {
                zzbg.zzam("Deleted " + zzdJ.delete("datalayer", "expires <= ?", new String[]{Long.toString(j)}) + " expired items");
            } catch (SQLiteException e) {
                zzbg.zzan("Error deleting old entries.");
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void zzb(List<zzb> list, long j) {
        try {
            long currentTimeMillis = this.zznR.currentTimeMillis();
            zzR(currentTimeMillis);
            zzhD(list.size());
            zzc(list, currentTimeMillis + j);
            zzwL();
        } catch (Throwable th) {
            zzwL();
            throw th;
        }
    }

    private void zzc(List<zzb> list, long j) {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for writeEntryToDatabase.");
        if (zzdJ != null) {
            for (zzb next : list) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("expires", Long.valueOf(j));
                contentValues.put("key", next.zzra);
                contentValues.put("value", next.zzaDk);
                zzdJ.insert("datalayer", (String) null, contentValues);
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzdI(String str) {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for clearKeysWithPrefix.");
        if (zzdJ != null) {
            try {
                zzbg.zzam("Cleared " + zzdJ.delete("datalayer", "key = ? OR key LIKE ?", new String[]{str, str + ".%"}) + " items");
            } catch (SQLiteException e) {
                zzbg.zzan("Error deleting entries with key prefix: " + str + " (" + e + ").");
            } finally {
                zzwL();
            }
        }
    }

    private SQLiteDatabase zzdJ(String str) {
        try {
            return this.zzaDd.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbg.zzan(str);
            return null;
        }
    }

    private void zze(String[] strArr) {
        SQLiteDatabase zzdJ;
        if (strArr != null && strArr.length != 0 && (zzdJ = zzdJ("Error opening database for deleteEntries.")) != null) {
            try {
                zzdJ.delete("datalayer", String.format("%s in (%s)", new Object[]{"ID", TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
            } catch (SQLiteException e) {
                zzbg.zzan("Error deleting entries " + Arrays.toString(strArr));
            }
        }
    }

    private void zzhD(int i) {
        int zzwK = (zzwK() - this.zzaDe) + i;
        if (zzwK > 0) {
            List<String> zzhE = zzhE(zzwK);
            zzbg.zzal("DataLayer store full, deleting " + zzhE.size() + " entries to make room.");
            zze((String[]) zzhE.toArray(new String[0]));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.String> zzhE(int r14) {
        /*
            r13 = this;
            r10 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            if (r14 > 0) goto L_0x000f
            java.lang.String r0 = "Invalid maxEntries specified. Skipping."
            com.google.android.gms.tagmanager.zzbg.zzan(r0)
            r0 = r9
        L_0x000e:
            return r0
        L_0x000f:
            java.lang.String r0 = "Error opening database for peekEntryIds."
            android.database.sqlite.SQLiteDatabase r0 = r13.zzdJ(r0)
            if (r0 != 0) goto L_0x0019
            r0 = r9
            goto L_0x000e
        L_0x0019:
            java.lang.String r1 = "datalayer"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            java.lang.String r4 = "ID"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r11 = 0
            java.lang.String r12 = "ID"
            r8[r11] = r12     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            java.lang.String r8 = java.lang.Integer.toString(r14)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0088 }
            if (r0 == 0) goto L_0x0055
        L_0x0043:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch:{ SQLiteException -> 0x0088 }
            java.lang.String r0 = java.lang.String.valueOf(r2)     // Catch:{ SQLiteException -> 0x0088 }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x0088 }
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0088 }
            if (r0 != 0) goto L_0x0043
        L_0x0055:
            if (r1 == 0) goto L_0x005a
            r1.close()
        L_0x005a:
            r0 = r9
            goto L_0x000e
        L_0x005c:
            r0 = move-exception
            r1 = r10
        L_0x005e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0086 }
            r2.<init>()     // Catch:{ all -> 0x0086 }
            java.lang.String r3 = "Error in peekEntries fetching entryIds: "
            java.lang.StringBuilder r2 = r2.append(r3)     // Catch:{ all -> 0x0086 }
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0086 }
            java.lang.StringBuilder r0 = r2.append(r0)     // Catch:{ all -> 0x0086 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0086 }
            com.google.android.gms.tagmanager.zzbg.zzan(r0)     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x005a
            r1.close()
            goto L_0x005a
        L_0x007e:
            r0 = move-exception
            r1 = r10
        L_0x0080:
            if (r1 == 0) goto L_0x0085
            r1.close()
        L_0x0085:
            throw r0
        L_0x0086:
            r0 = move-exception
            goto L_0x0080
        L_0x0088:
            r0 = move-exception
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzhE(int):java.util.List");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0029 A[SYNTHETIC, Splitter:B:20:0x0029] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0038 A[SYNTHETIC, Splitter:B:27:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.Object zzm(byte[] r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r2 = new java.io.ByteArrayInputStream
            r2.<init>(r6)
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch:{ IOException -> 0x0018, ClassNotFoundException -> 0x0025, all -> 0x0032 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0018, ClassNotFoundException -> 0x0025, all -> 0x0032 }
            java.lang.Object r0 = r1.readObject()     // Catch:{ IOException -> 0x0045, ClassNotFoundException -> 0x0043, all -> 0x0041 }
            if (r1 == 0) goto L_0x0014
            r1.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0014:
            r2.close()     // Catch:{ IOException -> 0x0047 }
        L_0x0017:
            return r0
        L_0x0018:
            r1 = move-exception
            r1 = r0
        L_0x001a:
            if (r1 == 0) goto L_0x001f
            r1.close()     // Catch:{ IOException -> 0x0023 }
        L_0x001f:
            r2.close()     // Catch:{ IOException -> 0x0023 }
            goto L_0x0017
        L_0x0023:
            r1 = move-exception
            goto L_0x0017
        L_0x0025:
            r1 = move-exception
            r1 = r0
        L_0x0027:
            if (r1 == 0) goto L_0x002c
            r1.close()     // Catch:{ IOException -> 0x0030 }
        L_0x002c:
            r2.close()     // Catch:{ IOException -> 0x0030 }
            goto L_0x0017
        L_0x0030:
            r1 = move-exception
            goto L_0x0017
        L_0x0032:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x0036:
            if (r1 == 0) goto L_0x003b
            r1.close()     // Catch:{ IOException -> 0x003f }
        L_0x003b:
            r2.close()     // Catch:{ IOException -> 0x003f }
        L_0x003e:
            throw r0
        L_0x003f:
            r1 = move-exception
            goto L_0x003e
        L_0x0041:
            r0 = move-exception
            goto L_0x0036
        L_0x0043:
            r3 = move-exception
            goto L_0x0027
        L_0x0045:
            r3 = move-exception
            goto L_0x001a
        L_0x0047:
            r1 = move-exception
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzm(byte[]):java.lang.Object");
    }

    private List<DataLayer.zza> zzq(List<zzb> list) {
        ArrayList arrayList = new ArrayList();
        for (zzb next : list) {
            arrayList.add(new DataLayer.zza(next.zzra, zzm(next.zzaDk)));
        }
        return arrayList;
    }

    private List<zzb> zzr(List<DataLayer.zza> list) {
        ArrayList arrayList = new ArrayList();
        for (DataLayer.zza next : list) {
            arrayList.add(new zzb(next.zzra, zzw(next.zzCM)));
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x002e A[SYNTHETIC, Splitter:B:20:0x002e] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] zzw(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream
            r2.<init>()
            java.io.ObjectOutputStream r1 = new java.io.ObjectOutputStream     // Catch:{ IOException -> 0x001b, all -> 0x0028 }
            r1.<init>(r2)     // Catch:{ IOException -> 0x001b, all -> 0x0028 }
            r1.writeObject(r6)     // Catch:{ IOException -> 0x0039, all -> 0x0037 }
            byte[] r0 = r2.toByteArray()     // Catch:{ IOException -> 0x0039, all -> 0x0037 }
            if (r1 == 0) goto L_0x0017
            r1.close()     // Catch:{ IOException -> 0x003b }
        L_0x0017:
            r2.close()     // Catch:{ IOException -> 0x003b }
        L_0x001a:
            return r0
        L_0x001b:
            r1 = move-exception
            r1 = r0
        L_0x001d:
            if (r1 == 0) goto L_0x0022
            r1.close()     // Catch:{ IOException -> 0x0026 }
        L_0x0022:
            r2.close()     // Catch:{ IOException -> 0x0026 }
            goto L_0x001a
        L_0x0026:
            r1 = move-exception
            goto L_0x001a
        L_0x0028:
            r1 = move-exception
            r4 = r1
            r1 = r0
            r0 = r4
        L_0x002c:
            if (r1 == 0) goto L_0x0031
            r1.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0031:
            r2.close()     // Catch:{ IOException -> 0x0035 }
        L_0x0034:
            throw r0
        L_0x0035:
            r1 = move-exception
            goto L_0x0034
        L_0x0037:
            r0 = move-exception
            goto L_0x002c
        L_0x0039:
            r3 = move-exception
            goto L_0x001d
        L_0x003b:
            r1 = move-exception
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzw.zzw(java.lang.Object):byte[]");
    }

    /* access modifiers changed from: private */
    public List<DataLayer.zza> zzwI() {
        try {
            zzR(this.zznR.currentTimeMillis());
            return zzq(zzwJ());
        } finally {
            zzwL();
        }
    }

    /* JADX INFO: finally extract failed */
    private List<zzb> zzwJ() {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for loadSerialized.");
        ArrayList arrayList = new ArrayList();
        if (zzdJ == null) {
            return arrayList;
        }
        Cursor query = zzdJ.query("datalayer", new String[]{"key", "value"}, (String) null, (String[]) null, (String) null, (String) null, "ID", (String) null);
        while (query.moveToNext()) {
            try {
                arrayList.add(new zzb(query.getString(0), query.getBlob(1)));
            } catch (Throwable th) {
                query.close();
                throw th;
            }
        }
        query.close();
        return arrayList;
    }

    private int zzwK() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzdJ = zzdJ("Error opening database for getNumStoredEntries.");
        if (zzdJ != null) {
            try {
                Cursor rawQuery = zzdJ.rawQuery("SELECT COUNT(*) from datalayer", (String[]) null);
                if (rawQuery.moveToFirst()) {
                    i = (int) rawQuery.getLong(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e) {
                zzbg.zzan("Error getting numStoredEntries");
                if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return i;
    }

    private void zzwL() {
        try {
            this.zzaDd.close();
        } catch (SQLiteException e) {
        }
    }

    public void zza(final DataLayer.zzc.zza zza2) {
        this.zzaDc.execute(new Runnable() {
            public void run() {
                zza2.zzp(zzw.this.zzwI());
            }
        });
    }

    public void zza(List<DataLayer.zza> list, final long j) {
        final List<zzb> zzr = zzr(list);
        this.zzaDc.execute(new Runnable() {
            public void run() {
                zzw.this.zzb(zzr, j);
            }
        });
    }

    public void zzdH(final String str) {
        this.zzaDc.execute(new Runnable() {
            public void run() {
                zzw.this.zzdI(str);
            }
        });
    }
}
