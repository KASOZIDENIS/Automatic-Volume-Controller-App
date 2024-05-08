package com.google.android.gms.analytics.internal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzv;
import java.io.Closeable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

class zzi extends zzd implements Closeable {
    /* access modifiers changed from: private */
    public static final String zzGk = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[]{"hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"});
    private static final String zzGl = String.format("SELECT MAX(%s) FROM %s WHERE 1;", new Object[]{"hit_time", "hits2"});
    private final zza zzGm;
    private final zzai zzGn = new zzai(zzgG());
    /* access modifiers changed from: private */
    public final zzai zzGo = new zzai(zzgG());

    class zza extends SQLiteOpenHelper {
        zza(Context context, String str) {
            super(context, str, (SQLiteDatabase.CursorFactory) null, 1);
        }

        private void zza(SQLiteDatabase sQLiteDatabase) {
            boolean z = true;
            Set<String> zzb = zzb(sQLiteDatabase, "hits2");
            for (String str : new String[]{"hit_id", "hit_string", "hit_time", "hit_url"}) {
                if (!zzb.remove(str)) {
                    throw new SQLiteException("Database hits2 is missing required column: " + str);
                }
            }
            if (zzb.remove("hit_app_id")) {
                z = false;
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database hits2 has extra columns");
            } else if (z) {
                sQLiteDatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id INTEGER");
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x0039  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean zza(android.database.sqlite.SQLiteDatabase r11, java.lang.String r12) {
            /*
                r10 = this;
                r8 = 0
                r9 = 0
                java.lang.String r1 = "SQLITE_MASTER"
                r0 = 1
                java.lang.String[] r2 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
                r0 = 0
                java.lang.String r3 = "name"
                r2[r0] = r3     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
                java.lang.String r3 = "name=?"
                r0 = 1
                java.lang.String[] r4 = new java.lang.String[r0]     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
                r0 = 0
                r4[r0] = r12     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
                r5 = 0
                r6 = 0
                r7 = 0
                r0 = r11
                android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ SQLiteException -> 0x0026, all -> 0x0036 }
                boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0040 }
                if (r1 == 0) goto L_0x0025
                r1.close()
            L_0x0025:
                return r0
            L_0x0026:
                r0 = move-exception
                r1 = r9
            L_0x0028:
                com.google.android.gms.analytics.internal.zzi r2 = com.google.android.gms.analytics.internal.zzi.this     // Catch:{ all -> 0x003d }
                java.lang.String r3 = "Error querying for table"
                r2.zzc(r3, r12, r0)     // Catch:{ all -> 0x003d }
                if (r1 == 0) goto L_0x0034
                r1.close()
            L_0x0034:
                r0 = r8
                goto L_0x0025
            L_0x0036:
                r0 = move-exception
            L_0x0037:
                if (r9 == 0) goto L_0x003c
                r9.close()
            L_0x003c:
                throw r0
            L_0x003d:
                r0 = move-exception
                r9 = r1
                goto L_0x0037
            L_0x0040:
                r0 = move-exception
                goto L_0x0028
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi.zza.zza(android.database.sqlite.SQLiteDatabase, java.lang.String):boolean");
        }

        private Set<String> zzb(SQLiteDatabase sQLiteDatabase, String str) {
            HashSet hashSet = new HashSet();
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT * FROM " + str + " LIMIT 0", (String[]) null);
            try {
                String[] columnNames = rawQuery.getColumnNames();
                for (String add : columnNames) {
                    hashSet.add(add);
                }
                return hashSet;
            } finally {
                rawQuery.close();
            }
        }

        private void zzb(SQLiteDatabase sQLiteDatabase) {
            Set<String> zzb = zzb(sQLiteDatabase, "properties");
            for (String str : new String[]{"app_uid", "cid", "tid", "params", "adid", "hits_count"}) {
                if (!zzb.remove(str)) {
                    throw new SQLiteException("Database properties is missing required column: " + str);
                }
            }
            if (!zzb.isEmpty()) {
                throw new SQLiteException("Database properties table has extra columns");
            }
        }

        public SQLiteDatabase getWritableDatabase() {
            if (!zzi.this.zzGo.zzt(3600000)) {
                throw new SQLiteException("Database open failed");
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e) {
                zzi.this.zzGo.start();
                zzi.this.zzaJ("Opening the database failed, dropping the table and recreating it");
                zzi.this.getContext().getDatabasePath(zzi.this.zzho()).delete();
                try {
                    SQLiteDatabase writableDatabase = super.getWritableDatabase();
                    zzi.this.zzGo.clear();
                    return writableDatabase;
                } catch (SQLiteException e2) {
                    zzi.this.zze("Failed to open freshly created database", e2);
                    throw e2;
                }
            }
        }

        public void onCreate(SQLiteDatabase database) {
            zzw.zzaQ(database.getPath());
        }

        public void onOpen(SQLiteDatabase database) {
            if (Build.VERSION.SDK_INT < 15) {
                Cursor rawQuery = database.rawQuery("PRAGMA journal_mode=memory", (String[]) null);
                try {
                    rawQuery.moveToFirst();
                } finally {
                    rawQuery.close();
                }
            }
            if (!zza(database, "hits2")) {
                database.execSQL(zzi.zzGk);
            } else {
                zza(database);
            }
            if (!zza(database, "properties")) {
                database.execSQL("CREATE TABLE IF NOT EXISTS properties ( app_uid INTEGER NOT NULL, cid TEXT NOT NULL, tid TEXT NOT NULL, params TEXT NOT NULL, adid INTEGER NOT NULL, hits_count INTEGER NOT NULL, PRIMARY KEY (app_uid, cid, tid)) ;");
            } else {
                zzb(database);
            }
        }

        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        }
    }

    zzi(zze zze) {
        super(zze);
        this.zzGm = new zza(zze.getContext(), zzho());
    }

    private long zza(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = getWritableDatabase().rawQuery(str, strArr);
            if (cursor.moveToFirst()) {
                long j = cursor.getLong(0);
                if (cursor != null) {
                    cursor.close();
                }
                return j;
            }
            throw new SQLiteException("Database returned empty set");
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private long zza(String str, String[] strArr, long j) {
        Cursor cursor = null;
        try {
            Cursor rawQuery = getWritableDatabase().rawQuery(str, strArr);
            if (rawQuery.moveToFirst()) {
                j = rawQuery.getLong(0);
                if (rawQuery != null) {
                    rawQuery.close();
                }
            } else if (rawQuery != null) {
                rawQuery.close();
            }
            return j;
        } catch (SQLiteException e) {
            zzd("Database error", str, e);
            throw e;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private String zzd(zzaa zzaa) {
        return zzaa.zziR() ? zzgI().zzid() : zzgI().zzie();
    }

    private static String zze(zzaa zzaa) {
        zzv.zzr(zzaa);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry next : zzaa.zzhe().entrySet()) {
            String str = (String) next.getKey();
            if (!"ht".equals(str) && !"qt".equals(str) && !"AppUID".equals(str)) {
                builder.appendQueryParameter(str, (String) next.getValue());
            }
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    private void zzhn() {
        int zzin = zzgI().zzin();
        long zzhd = zzhd();
        if (zzhd > ((long) (zzin - 1))) {
            List<Long> zzm = zzm((zzhd - ((long) zzin)) + 1);
            zzd("Store full, deleting hits to make room, count", Integer.valueOf(zzm.size()));
            zze(zzm);
        }
    }

    /* access modifiers changed from: private */
    public String zzho() {
        return !zzgI().zzhP() ? zzgI().zzip() : zzgI().zzhQ() ? zzgI().zzip() : zzgI().zziq();
    }

    private static String zzz(Map<String, String> map) {
        zzv.zzr(map);
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry next : map.entrySet()) {
            builder.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        String encodedQuery = builder.build().getEncodedQuery();
        return encodedQuery == null ? "" : encodedQuery;
    }

    public void beginTransaction() {
        zzgR();
        getWritableDatabase().beginTransaction();
    }

    public void close() {
        try {
            this.zzGm.close();
        } catch (SQLiteException e) {
            zze("Sql error closing database", e);
        } catch (IllegalStateException e2) {
            zze("Error closing database", e2);
        }
    }

    public void endTransaction() {
        zzgR();
        getWritableDatabase().endTransaction();
    }

    /* access modifiers changed from: package-private */
    public SQLiteDatabase getWritableDatabase() {
        try {
            return this.zzGm.getWritableDatabase();
        } catch (SQLiteException e) {
            zzd("Error opening database", e);
            throw e;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isEmpty() {
        return zzhd() == 0;
    }

    public void setTransactionSuccessful() {
        zzgR();
        getWritableDatabase().setTransactionSuccessful();
    }

    public long zza(long j, String str, String str2) {
        zzv.zzbS(str);
        zzv.zzbS(str2);
        zzgR();
        zzgF();
        return zza("SELECT hits_count FROM properties WHERE app_uid=? AND cid=? AND tid=?", new String[]{String.valueOf(j), str, str2}, 0);
    }

    public void zza(long j, String str) {
        zzv.zzbS(str);
        zzgR();
        zzgF();
        int delete = getWritableDatabase().delete("properties", "app_uid=? AND cid<>?", new String[]{String.valueOf(j), str});
        if (delete > 0) {
            zza("Deleted property records", Integer.valueOf(delete));
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzaK(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            if (!str.startsWith("?")) {
                str = "?" + str;
            }
            List<NameValuePair> parse = URLEncodedUtils.parse(new URI(str), "UTF-8");
            HashMap hashMap = new HashMap(parse.size());
            for (NameValuePair nameValuePair : parse) {
                hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
            return hashMap;
        } catch (URISyntaxException e) {
            zze("Error parsing hit parameters", e);
            return new HashMap(0);
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> zzaL(String str) {
        if (TextUtils.isEmpty(str)) {
            return new HashMap(0);
        }
        try {
            List<NameValuePair> parse = URLEncodedUtils.parse(new URI("?" + str), "UTF-8");
            HashMap hashMap = new HashMap(parse.size());
            for (NameValuePair nameValuePair : parse) {
                hashMap.put(nameValuePair.getName(), nameValuePair.getValue());
            }
            return hashMap;
        } catch (URISyntaxException e) {
            zze("Error parsing property parameters", e);
            return new HashMap(0);
        }
    }

    public void zzb(zzg zzg) {
        zzv.zzr(zzg);
        zzgR();
        zzgF();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String zzz = zzz(zzg.zzhe());
        ContentValues contentValues = new ContentValues();
        contentValues.put("app_uid", Long.valueOf(zzg.zzha()));
        contentValues.put("cid", zzg.getClientId());
        contentValues.put("tid", zzg.zzhb());
        contentValues.put("adid", Integer.valueOf(zzg.zzhc() ? 1 : 0));
        contentValues.put("hits_count", Long.valueOf(zzg.zzhd()));
        contentValues.put("params", zzz);
        try {
            if (writableDatabase.insertWithOnConflict("properties", (String) null, contentValues, 5) == -1) {
                zzaJ("Failed to insert/update a property (got -1)");
            }
        } catch (SQLiteException e) {
            zze("Error storing a property", e);
        }
    }

    public void zzc(zzaa zzaa) {
        zzv.zzr(zzaa);
        zzgF();
        zzgR();
        String zze = zze(zzaa);
        if (zze.length() > 8192) {
            zzgH().zza(zzaa, "Hit length exceeds the maximum allowed size");
            return;
        }
        zzhn();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("hit_string", zze);
        contentValues.put("hit_time", Long.valueOf(zzaa.zziP()));
        contentValues.put("hit_app_id", Integer.valueOf(zzaa.zziN()));
        contentValues.put("hit_url", zzd(zzaa));
        try {
            long insert = writableDatabase.insert("hits2", (String) null, contentValues);
            if (insert == -1) {
                zzaJ("Failed to insert a hit (got -1)");
            } else {
                zzb("Hit saved to database. db-id, hit", Long.valueOf(insert), zzaa);
            }
        } catch (SQLiteException e) {
            zze("Error storing a hit", e);
        }
    }

    public void zze(List<Long> list) {
        zzv.zzr(list);
        zzgF();
        zzgR();
        if (!list.isEmpty()) {
            StringBuilder sb = new StringBuilder("hit_id");
            sb.append(" in (");
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < list.size()) {
                    Long l = list.get(i2);
                    if (l != null && l.longValue() != 0) {
                        if (i2 > 0) {
                            sb.append(",");
                        }
                        sb.append(l);
                        i = i2 + 1;
                    }
                } else {
                    sb.append(")");
                    String sb2 = sb.toString();
                    try {
                        SQLiteDatabase writableDatabase = getWritableDatabase();
                        zza("Deleting dispatched hits. count", Integer.valueOf(list.size()));
                        int delete = writableDatabase.delete("hits2", sb2, (String[]) null);
                        if (delete != list.size()) {
                            zzb("Deleted fewer hits then expected", Integer.valueOf(list.size()), Integer.valueOf(delete), sb2);
                            return;
                        }
                        return;
                    } catch (SQLiteException e) {
                        zze("Error deleting hits", e);
                        throw e;
                    }
                }
            }
            throw new SQLiteException("Invalid hit id");
        }
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
    }

    public long zzhd() {
        zzgF();
        zzgR();
        return zza("SELECT COUNT(*) FROM hits2", (String[]) null);
    }

    public void zzhj() {
        zzgF();
        zzgR();
        getWritableDatabase().delete("hits2", (String) null, (String[]) null);
    }

    public void zzhk() {
        zzgF();
        zzgR();
        getWritableDatabase().delete("properties", (String) null, (String[]) null);
    }

    public int zzhl() {
        zzgF();
        zzgR();
        if (!this.zzGn.zzt(86400000)) {
            return 0;
        }
        this.zzGn.start();
        zzaF("Deleting stale hits (if any)");
        int delete = getWritableDatabase().delete("hits2", "hit_time < ?", new String[]{Long.toString(zzgG().currentTimeMillis() - 2592000000L)});
        zza("Deleted stale hits, count", Integer.valueOf(delete));
        return delete;
    }

    public long zzhm() {
        zzgF();
        zzgR();
        return zza(zzGl, (String[]) null, 0);
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> zzm(long r14) {
        /*
            r13 = this;
            r10 = 0
            r13.zzgF()
            r13.zzgR()
            r0 = 0
            int r0 = (r14 > r0 ? 1 : (r14 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0012
            java.util.List r0 = java.util.Collections.emptyList()
        L_0x0011:
            return r0
        L_0x0012:
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.lang.String r1 = "hits2"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            r11 = 0
            java.lang.String r12 = "hit_id"
            r8[r11] = r12     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            android.database.Cursor r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x005e, all -> 0x006b }
            boolean r0 = r1.moveToFirst()     // Catch:{ SQLiteException -> 0x0075 }
            if (r0 == 0) goto L_0x0057
        L_0x0045:
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch:{ SQLiteException -> 0x0075 }
            java.lang.Long r0 = java.lang.Long.valueOf(r2)     // Catch:{ SQLiteException -> 0x0075 }
            r9.add(r0)     // Catch:{ SQLiteException -> 0x0075 }
            boolean r0 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0075 }
            if (r0 != 0) goto L_0x0045
        L_0x0057:
            if (r1 == 0) goto L_0x005c
            r1.close()
        L_0x005c:
            r0 = r9
            goto L_0x0011
        L_0x005e:
            r0 = move-exception
            r1 = r10
        L_0x0060:
            java.lang.String r2 = "Error selecting hit ids"
            r13.zzd(r2, r0)     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x005c
            r1.close()
            goto L_0x005c
        L_0x006b:
            r0 = move-exception
        L_0x006c:
            if (r10 == 0) goto L_0x0071
            r10.close()
        L_0x0071:
            throw r0
        L_0x0072:
            r0 = move-exception
            r10 = r1
            goto L_0x006c
        L_0x0075:
            r0 = move-exception
            goto L_0x0060
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi.zzm(long):java.util.List");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009e, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a2, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a4, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a5, code lost:
        r1 = r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00a2 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x0019] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.analytics.internal.zzaa> zzn(long r14) {
        /*
            r13 = this;
            r0 = 1
            r1 = 0
            r9 = 0
            r2 = 0
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x008f
        L_0x0009:
            com.google.android.gms.common.internal.zzv.zzQ(r0)
            r13.zzgF()
            r13.zzgR()
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            java.lang.String r1 = "hits2"
            r2 = 5
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 0
            java.lang.String r4 = "hit_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 1
            java.lang.String r4 = "hit_time"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 2
            java.lang.String r4 = "hit_string"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 3
            java.lang.String r4 = "hit_url"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 4
            java.lang.String r4 = "hit_app_id"
            r2[r3] = r4     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            java.lang.String r7 = "%s ASC"
            r8 = 1
            java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            r10 = 0
            java.lang.String r11 = "hit_id"
            r8[r10] = r11     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            java.lang.String r7 = java.lang.String.format(r7, r8)     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            java.lang.String r8 = java.lang.Long.toString(r14)     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x0092, all -> 0x00a2 }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r10.<init>()     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            if (r0 == 0) goto L_0x0089
        L_0x0059:
            r0 = 0
            long r6 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r0 = 1
            long r3 = r9.getLong(r0)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r0 = 2
            java.lang.String r0 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r1 = 3
            java.lang.String r1 = r9.getString(r1)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r2 = 4
            int r8 = r9.getInt(r2)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            java.util.Map r2 = r13.zzaK(r0)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            boolean r5 = com.google.android.gms.analytics.internal.zzal.zzaZ(r1)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            com.google.android.gms.analytics.internal.zzaa r0 = new com.google.android.gms.analytics.internal.zzaa     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r1 = r13
            r0.<init>(r1, r2, r3, r5, r6, r8)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            r10.add(r0)     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00a4, all -> 0x00a2 }
            if (r0 != 0) goto L_0x0059
        L_0x0089:
            if (r9 == 0) goto L_0x008e
            r9.close()
        L_0x008e:
            return r10
        L_0x008f:
            r0 = r1
            goto L_0x0009
        L_0x0092:
            r0 = move-exception
            r1 = r9
        L_0x0094:
            java.lang.String r2 = "Error loading hits from the database"
            r13.zze(r2, r0)     // Catch:{ all -> 0x009a }
            throw r0     // Catch:{ all -> 0x009a }
        L_0x009a:
            r0 = move-exception
            r9 = r1
        L_0x009c:
            if (r9 == 0) goto L_0x00a1
            r9.close()
        L_0x00a1:
            throw r0
        L_0x00a2:
            r0 = move-exception
            goto L_0x009c
        L_0x00a4:
            r0 = move-exception
            r1 = r9
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi.zzn(long):java.util.List");
    }

    public void zzo(long j) {
        zzgF();
        zzgR();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Long.valueOf(j));
        zza("Deleting hit, id", Long.valueOf(j));
        zze((List<Long>) arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00b8, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00ba, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00bb, code lost:
        r1 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00b8 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x000c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.google.android.gms.analytics.internal.zzg> zzp(long r14) {
        /*
            r13 = this;
            r13.zzgR()
            r13.zzgF()
            android.database.sqlite.SQLiteDatabase r0 = r13.getWritableDatabase()
            r9 = 0
            r1 = 5
            java.lang.String[] r2 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 0
            java.lang.String r3 = "cid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 1
            java.lang.String r3 = "tid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 2
            java.lang.String r3 = "adid"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 3
            java.lang.String r3 = "hits_count"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 4
            java.lang.String r3 = "params"
            r2[r1] = r3     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            com.google.android.gms.analytics.internal.zzq r1 = r13.zzgI()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            int r10 = r1.zzio()     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            java.lang.String r8 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            java.lang.String r3 = "app_uid=?"
            r1 = 1
            java.lang.String[] r4 = new java.lang.String[r1]     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r1 = 0
            java.lang.String r5 = java.lang.String.valueOf(r14)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            r4[r1] = r5     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            java.lang.String r1 = "properties"
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ SQLiteException -> 0x00ba, all -> 0x00b8 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r11.<init>()     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            boolean r0 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 == 0) goto L_0x008b
        L_0x0053:
            r0 = 0
            java.lang.String r3 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r0 = 1
            java.lang.String r4 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r0 = 2
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 == 0) goto L_0x009c
            r5 = 1
        L_0x0065:
            r0 = 3
            int r0 = r9.getInt(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            long r6 = (long) r0     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r0 = 4
            java.lang.String r0 = r9.getString(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            java.util.Map r8 = r13.zzaL(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            boolean r0 = android.text.TextUtils.isEmpty(r3)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0080
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 == 0) goto L_0x009e
        L_0x0080:
            java.lang.String r0 = "Read property with empty client id or tracker id"
            r13.zzc(r0, r3, r4)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
        L_0x0085:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 != 0) goto L_0x0053
        L_0x008b:
            int r0 = r11.size()     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            if (r0 < r10) goto L_0x0096
            java.lang.String r0 = "Sending hits to too many properties. Campaign report might be incorrect"
            r13.zzaI(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
        L_0x0096:
            if (r9 == 0) goto L_0x009b
            r9.close()
        L_0x009b:
            return r11
        L_0x009c:
            r5 = 0
            goto L_0x0065
        L_0x009e:
            com.google.android.gms.analytics.internal.zzg r0 = new com.google.android.gms.analytics.internal.zzg     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r1 = r14
            r0.<init>(r1, r3, r4, r5, r6, r8)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            r11.add(r0)     // Catch:{ SQLiteException -> 0x00a8, all -> 0x00b8 }
            goto L_0x0085
        L_0x00a8:
            r0 = move-exception
            r1 = r9
        L_0x00aa:
            java.lang.String r2 = "Error loading hits from the database"
            r13.zze(r2, r0)     // Catch:{ all -> 0x00b0 }
            throw r0     // Catch:{ all -> 0x00b0 }
        L_0x00b0:
            r0 = move-exception
            r9 = r1
        L_0x00b2:
            if (r9 == 0) goto L_0x00b7
            r9.close()
        L_0x00b7:
            throw r0
        L_0x00b8:
            r0 = move-exception
            goto L_0x00b2
        L_0x00ba:
            r0 = move-exception
            r1 = r9
            goto L_0x00aa
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzi.zzp(long):java.util.List");
    }
}
