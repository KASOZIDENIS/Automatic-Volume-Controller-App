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
import com.google.android.gms.tagmanager.zzcx;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.apache.http.impl.client.DefaultHttpClient;

class zzby implements zzau {
    /* access modifiers changed from: private */
    public static final String zzGk = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    /* access modifiers changed from: private */
    public final Context mContext;
    private final zzb zzaEe;
    private volatile zzac zzaEf;
    private final zzav zzaEg;
    /* access modifiers changed from: private */
    public final String zzaEh;
    private long zzaEi;
    private final int zzaEj;
    /* access modifiers changed from: private */
    public zzht zznR;

    class zza implements zzcx.zza {
        zza() {
        }

        public void zza(zzaq zzaq) {
            zzby.this.zzo(zzaq.zzwU());
        }

        public void zzb(zzaq zzaq) {
            zzby.this.zzo(zzaq.zzwU());
            zzbg.zzam("Permanent failure dispatching hitId: " + zzaq.zzwU());
        }

        public void zzc(zzaq zzaq) {
            long zzwV = zzaq.zzwV();
            if (zzwV == 0) {
                zzby.this.zzd(zzaq.zzwU(), zzby.this.zznR.currentTimeMillis());
            } else if (zzwV + 14400000 < zzby.this.zznR.currentTimeMillis()) {
                zzby.this.zzo(zzaq.zzwU());
                zzbg.zzam("Giving up on failed hitId: " + zzaq.zzwU());
            }
        }
    }

    class zzb extends SQLiteOpenHelper {
        private boolean zzaEl;
        private long zzaEm = 0;

        zzb(Context context, String str) {
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzby.zzb.zza(java.lang.String, android.database.sqlite.SQLiteDatabase):boolean");
        }

        /* JADX INFO: finally extract failed */
        private void zzc(SQLiteDatabase sQLiteDatabase) {
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM gtm_hits WHERE 0", (String[]) null);
            HashSet hashSet = new HashSet();
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                rawQuery.close();
                if (!hashSet.remove("hit_id") || !hashSet.remove("hit_url") || !hashSet.remove("hit_time") || !hashSet.remove("hit_first_send_time")) {
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
            if (!this.zzaEl || this.zzaEm + 3600000 <= zzby.this.zznR.currentTimeMillis()) {
                SQLiteDatabase sQLiteDatabase = null;
                this.zzaEl = true;
                this.zzaEm = zzby.this.zznR.currentTimeMillis();
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (SQLiteException e) {
                    zzby.this.mContext.getDatabasePath(zzby.this.zzaEh).delete();
                }
                if (sQLiteDatabase == null) {
                    sQLiteDatabase = super.getWritableDatabase();
                }
                this.zzaEl = false;
                return sQLiteDatabase;
            }
            throw new SQLiteException("Database creation failed");
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
            if (!zza("gtm_hits", db)) {
                db.execSQL(zzby.zzGk);
            } else {
                zzc(db);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    zzby(zzav zzav, Context context) {
        this(zzav, context, "gtm_urls.db", 2000);
    }

    zzby(zzav zzav, Context context, String str, int i) {
        this.mContext = context.getApplicationContext();
        this.zzaEh = str;
        this.zzaEg = zzav;
        this.zznR = zzhv.zznd();
        this.zzaEe = new zzb(this.mContext, this.zzaEh);
        this.zzaEf = new zzcx(new DefaultHttpClient(), this.mContext, new zza());
        this.zzaEi = 0;
        this.zzaEj = i;
    }

    /* access modifiers changed from: private */
    public void zzd(long j, long j2) {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for getNumStoredHits.");
        if (zzdJ != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzdJ.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException e) {
                zzbg.zzan("Error setting HIT_FIRST_DISPATCH_TIME for hitId: " + j);
                zzo(j);
            }
        }
    }

    private SQLiteDatabase zzdJ(String str) {
        try {
            return this.zzaEe.getWritableDatabase();
        } catch (SQLiteException e) {
            zzbg.zzan(str);
            return null;
        }
    }

    private void zzh(long j, String str) {
        SQLiteDatabase zzdJ = zzdJ("Error opening database for putHit");
        if (zzdJ != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", 0);
            try {
                zzdJ.insert("gtm_hits", (String) null, contentValues);
                this.zzaEg.zzai(false);
            } catch (SQLiteException e) {
                zzbg.zzan("Error storing hit");
            }
        }
    }

    /* access modifiers changed from: private */
    public void zzo(long j) {
        zzf(new String[]{String.valueOf(j)});
    }

    private void zzxh() {
        int zzxi = (zzxi() - this.zzaEj) + 1;
        if (zzxi > 0) {
            List<String> zzhJ = zzhJ(zzxi);
            zzbg.zzam("Store full, deleting " + zzhJ.size() + " hits to make room.");
            zzf((String[]) zzhJ.toArray(new String[0]));
        }
    }

    public void dispatch() {
        zzbg.zzam("GTM Dispatch running...");
        if (this.zzaEf.zzwN()) {
            List<zzaq> zzhK = zzhK(40);
            if (zzhK.isEmpty()) {
                zzbg.zzam("...nothing to dispatch");
                this.zzaEg.zzai(true);
                return;
            }
            this.zzaEf.zzs(zzhK);
            if (zzxj() > 0) {
                zzcu.zzxF().dispatch();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void zzf(String[] strArr) {
        SQLiteDatabase zzdJ;
        boolean z = true;
        if (strArr != null && strArr.length != 0 && (zzdJ = zzdJ("Error opening database for deleteHits.")) != null) {
            try {
                zzdJ.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                zzav zzav = this.zzaEg;
                if (zzxi() != 0) {
                    z = false;
                }
                zzav.zzai(z);
            } catch (SQLiteException e) {
                zzbg.zzan("Error deleting hits");
            }
        }
    }

    public void zzg(long j, String str) {
        zzhl();
        zzxh();
        zzh(j, str);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.String> zzhJ(int r14) {
        /*
            r13 = this;
            r10 = 0
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            if (r14 > 0) goto L_0x000f
            java.lang.String r0 = "Invalid maxHits specified. Skipping"
            com.google.android.gms.tagmanager.zzbg.zzan(r0)
            r0 = r9
        L_0x000e:
            return r0
        L_0x000f:
            java.lang.String r0 = "Error opening database for peekHitIds."
            android.database.sqlite.SQLiteDatabase r0 = r13.zzdJ(r0)
            if (r0 != 0) goto L_0x0019
            r0 = r9
            goto L_0x000e
        L_0x0019:
            java.lang.String r1 = "gtm_hits"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x005c, all -> 0x007e }
            r11 = 0
            java.lang.String r12 = "hit_id"
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
            java.lang.String r3 = "Error in peekHits fetching hitIds: "
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
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzby.zzhJ(int):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00ea, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00f2, code lost:
        r12.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x016b, code lost:
        r2 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x016c, code lost:
        r12 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0171, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x0172, code lost:
        r3 = r2;
        r4 = r13;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:?, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        return r2;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ea  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f2  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x016b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:80:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.tagmanager.zzaq> zzhK(int r17) {
        /*
            r16 = this;
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.lang.String r2 = "Error opening database for peekHits"
            r0 = r16
            android.database.sqlite.SQLiteDatabase r2 = r0.zzdJ(r2)
            if (r2 != 0) goto L_0x0011
            r2 = r11
        L_0x0010:
            return r2
        L_0x0011:
            r12 = 0
            java.lang.String r3 = "gtm_hits"
            r4 = 3
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 1
            java.lang.String r6 = "hit_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 2
            java.lang.String r6 = "hit_first_send_time"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            r13 = 0
            java.lang.String r14 = "hit_id"
            r10[r13] = r14     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            android.database.Cursor r13 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00ca, all -> 0x00ef }
            java.util.ArrayList r12 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0171, all -> 0x016b }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0171, all -> 0x016b }
            boolean r3 = r13.moveToFirst()     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            if (r3 == 0) goto L_0x0068
        L_0x004b:
            com.google.android.gms.tagmanager.zzaq r3 = new com.google.android.gms.tagmanager.zzaq     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r4 = 0
            long r4 = r13.getLong(r4)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r6 = 1
            long r6 = r13.getLong(r6)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r8 = 2
            long r8 = r13.getLong(r8)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r3.<init>(r4, r6, r8)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            r12.add(r3)     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            boolean r3 = r13.moveToNext()     // Catch:{ SQLiteException -> 0x0177, all -> 0x016b }
            if (r3 != 0) goto L_0x004b
        L_0x0068:
            if (r13 == 0) goto L_0x006d
            r13.close()
        L_0x006d:
            r11 = 0
            java.lang.String r3 = "gtm_hits"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 0
            java.lang.String r6 = "hit_id"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 1
            java.lang.String r6 = "hit_url"
            r4[r5] = r6     // Catch:{ SQLiteException -> 0x0169 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "%s ASC"
            r10 = 1
            java.lang.Object[] r10 = new java.lang.Object[r10]     // Catch:{ SQLiteException -> 0x0169 }
            r14 = 0
            java.lang.String r15 = "hit_id"
            r10[r14] = r15     // Catch:{ SQLiteException -> 0x0169 }
            java.lang.String r9 = java.lang.String.format(r9, r10)     // Catch:{ SQLiteException -> 0x0169 }
            java.lang.String r10 = java.lang.Integer.toString(r17)     // Catch:{ SQLiteException -> 0x0169 }
            android.database.Cursor r3 = r2.query(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x0169 }
            boolean r2 = r3.moveToFirst()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r2 == 0) goto L_0x00c2
            r4 = r11
        L_0x009e:
            r0 = r3
            android.database.sqlite.SQLiteCursor r0 = (android.database.sqlite.SQLiteCursor) r0     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r2 = r0
            android.database.CursorWindow r2 = r2.getWindow()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            int r2 = r2.getNumRows()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r2 <= 0) goto L_0x00f6
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.zzaq r2 = (com.google.android.gms.tagmanager.zzaq) r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r2.zzdN(r5)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
        L_0x00ba:
            int r2 = r4 + 1
            boolean r4 = r3.moveToNext()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            if (r4 != 0) goto L_0x017d
        L_0x00c2:
            if (r3 == 0) goto L_0x00c7
            r3.close()
        L_0x00c7:
            r2 = r12
            goto L_0x0010
        L_0x00ca:
            r2 = move-exception
            r3 = r2
            r4 = r12
            r2 = r11
        L_0x00ce:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x016e }
            r5.<init>()     // Catch:{ all -> 0x016e }
            java.lang.String r6 = "Error in peekHits fetching hitIds: "
            java.lang.StringBuilder r5 = r5.append(r6)     // Catch:{ all -> 0x016e }
            java.lang.String r3 = r3.getMessage()     // Catch:{ all -> 0x016e }
            java.lang.StringBuilder r3 = r5.append(r3)     // Catch:{ all -> 0x016e }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x016e }
            com.google.android.gms.tagmanager.zzbg.zzan(r3)     // Catch:{ all -> 0x016e }
            if (r4 == 0) goto L_0x0010
            r4.close()
            goto L_0x0010
        L_0x00ef:
            r2 = move-exception
        L_0x00f0:
            if (r12 == 0) goto L_0x00f5
            r12.close()
        L_0x00f5:
            throw r2
        L_0x00f6:
            java.lang.String r5 = "HitString for hitId %d too large.  Hit will be deleted."
            r2 = 1
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r7 = 0
            java.lang.Object r2 = r12.get(r4)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.zzaq r2 = (com.google.android.gms.tagmanager.zzaq) r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            long r8 = r2.zzwU()     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            java.lang.Long r2 = java.lang.Long.valueOf(r8)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            r6[r7] = r2     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            java.lang.String r2 = java.lang.String.format(r5, r6)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            com.google.android.gms.tagmanager.zzbg.zzan(r2)     // Catch:{ SQLiteException -> 0x0114, all -> 0x0166 }
            goto L_0x00ba
        L_0x0114:
            r2 = move-exception
            r13 = r3
        L_0x0116:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x015f }
            r3.<init>()     // Catch:{ all -> 0x015f }
            java.lang.String r4 = "Error in peekHits fetching hit url: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch:{ all -> 0x015f }
            java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x015f }
            java.lang.StringBuilder r2 = r3.append(r2)     // Catch:{ all -> 0x015f }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x015f }
            com.google.android.gms.tagmanager.zzbg.zzan(r2)     // Catch:{ all -> 0x015f }
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ all -> 0x015f }
            r3.<init>()     // Catch:{ all -> 0x015f }
            r4 = 0
            java.util.Iterator r5 = r12.iterator()     // Catch:{ all -> 0x015f }
        L_0x013a:
            boolean r2 = r5.hasNext()     // Catch:{ all -> 0x015f }
            if (r2 == 0) goto L_0x0152
            java.lang.Object r2 = r5.next()     // Catch:{ all -> 0x015f }
            com.google.android.gms.tagmanager.zzaq r2 = (com.google.android.gms.tagmanager.zzaq) r2     // Catch:{ all -> 0x015f }
            java.lang.String r6 = r2.zzwW()     // Catch:{ all -> 0x015f }
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ all -> 0x015f }
            if (r6 == 0) goto L_0x015b
            if (r4 == 0) goto L_0x015a
        L_0x0152:
            if (r13 == 0) goto L_0x0157
            r13.close()
        L_0x0157:
            r2 = r3
            goto L_0x0010
        L_0x015a:
            r4 = 1
        L_0x015b:
            r3.add(r2)     // Catch:{ all -> 0x015f }
            goto L_0x013a
        L_0x015f:
            r2 = move-exception
        L_0x0160:
            if (r13 == 0) goto L_0x0165
            r13.close()
        L_0x0165:
            throw r2
        L_0x0166:
            r2 = move-exception
            r13 = r3
            goto L_0x0160
        L_0x0169:
            r2 = move-exception
            goto L_0x0116
        L_0x016b:
            r2 = move-exception
            r12 = r13
            goto L_0x00f0
        L_0x016e:
            r2 = move-exception
            r12 = r4
            goto L_0x00f0
        L_0x0171:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r11
            goto L_0x00ce
        L_0x0177:
            r2 = move-exception
            r3 = r2
            r4 = r13
            r2 = r12
            goto L_0x00ce
        L_0x017d:
            r4 = r2
            goto L_0x009e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzby.zzhK(int):java.util.List");
    }

    /* access modifiers changed from: package-private */
    public int zzhl() {
        boolean z = true;
        long currentTimeMillis = this.zznR.currentTimeMillis();
        if (currentTimeMillis <= this.zzaEi + 86400000) {
            return 0;
        }
        this.zzaEi = currentTimeMillis;
        SQLiteDatabase zzdJ = zzdJ("Error opening database for deleteStaleHits.");
        if (zzdJ == null) {
            return 0;
        }
        int delete = zzdJ.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zznR.currentTimeMillis() - 2592000000L)});
        zzav zzav = this.zzaEg;
        if (zzxi() != 0) {
            z = false;
        }
        zzav.zzai(z);
        return delete;
    }

    /* access modifiers changed from: package-private */
    public int zzxi() {
        Cursor cursor = null;
        int i = 0;
        SQLiteDatabase zzdJ = zzdJ("Error opening database for getNumStoredHits.");
        if (zzdJ != null) {
            try {
                Cursor rawQuery = zzdJ.rawQuery("SELECT COUNT(*) from gtm_hits", (String[]) null);
                if (rawQuery.moveToFirst()) {
                    i = (int) rawQuery.getLong(0);
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } catch (SQLiteException e) {
                zzbg.zzan("Error getting numStoredHits");
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

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int zzxj() {
        /*
            r10 = this;
            r8 = 0
            r9 = 0
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r10.zzdJ(r0)
            if (r0 != 0) goto L_0x000b
        L_0x000a:
            return r8
        L_0x000b:
            java.lang.String r1 = "gtm_hits"
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            r3 = 1
            java.lang.String r4 = "hit_first_send_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            java.lang.String r3 = "hit_first_send_time=0"
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x002f, all -> 0x003d }
            int r0 = r1.getCount()     // Catch:{ SQLiteException -> 0x004b, all -> 0x0044 }
            if (r1 == 0) goto L_0x002d
            r1.close()
        L_0x002d:
            r8 = r0
            goto L_0x000a
        L_0x002f:
            r0 = move-exception
            r0 = r9
        L_0x0031:
            java.lang.String r1 = "Error getting num untried hits"
            com.google.android.gms.tagmanager.zzbg.zzan(r1)     // Catch:{ all -> 0x0047 }
            if (r0 == 0) goto L_0x004e
            r0.close()
            r0 = r8
            goto L_0x002d
        L_0x003d:
            r0 = move-exception
        L_0x003e:
            if (r9 == 0) goto L_0x0043
            r9.close()
        L_0x0043:
            throw r0
        L_0x0044:
            r0 = move-exception
            r9 = r1
            goto L_0x003e
        L_0x0047:
            r1 = move-exception
            r9 = r0
            r0 = r1
            goto L_0x003e
        L_0x004b:
            r0 = move-exception
            r0 = r1
            goto L_0x0031
        L_0x004e:
            r0 = r8
            goto L_0x002d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzby.zzxj():int");
    }
}
