package com.google.android.gms.analytics.internal;

import android.content.Context;
import com.google.android.gms.common.internal.zzv;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class zzm extends zzd {
    private volatile String zzFg;
    private Future<String> zzGK;

    protected zzm(zze zze) {
        super(zze);
    }

    private boolean zzg(Context context, String str) {
        boolean z = false;
        zzv.zzbS(str);
        zzv.zzbJ("ClientId should be saved from worker thread");
        FileOutputStream fileOutputStream = null;
        try {
            zza("Storing clientId", str);
            fileOutputStream = context.openFileOutput("gaClientId", 0);
            fileOutputStream.write(str.getBytes());
            z = true;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zze("Failed to close clientId writing stream", e);
                }
            }
        } catch (FileNotFoundException e2) {
            zze("Error creating clientId file", e2);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zze("Failed to close clientId writing stream", e3);
                }
            }
        } catch (IOException e4) {
            zze("Error writing to clientId file", e4);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e5) {
                    zze("Failed to close clientId writing stream", e5);
                }
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e6) {
                    zze("Failed to close clientId writing stream", e6);
                }
            }
            throw th;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public String zzhL() {
        String zzhM = zzhM();
        try {
            return !zzg(zzgJ().getContext(), zzhM) ? "0" : zzhM;
        } catch (Exception e) {
            zze("Error saving clientId file", e);
            return "0";
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[SYNTHETIC, Splitter:B:34:0x0074] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x008d A[SYNTHETIC, Splitter:B:44:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x009d A[SYNTHETIC, Splitter:B:51:0x009d] */
    /* JADX WARNING: Removed duplicated region for block: B:66:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String zzL(android.content.Context r7) {
        /*
            r6 = this;
            r0 = 0
            java.lang.String r1 = "ClientId should be loaded from worker thread"
            com.google.android.gms.common.internal.zzv.zzbJ(r1)
            java.lang.String r1 = "gaClientId"
            java.io.FileInputStream r2 = r7.openFileInput(r1)     // Catch:{ FileNotFoundException -> 0x0070, IOException -> 0x007f, all -> 0x0098 }
            r1 = 36
            byte[] r3 = new byte[r1]     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            r1 = 0
            int r4 = r3.length     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            int r4 = r2.read(r3, r1, r4)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            int r1 = r2.available()     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            if (r1 <= 0) goto L_0x0036
            java.lang.String r1 = "clientId file seems corrupted, deleting it."
            r6.zzaI(r1)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x002f }
        L_0x002e:
            return r0
        L_0x002f:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002e
        L_0x0036:
            r1 = 14
            if (r4 >= r1) goto L_0x0054
            java.lang.String r1 = "clientId file is empty, deleting it."
            r6.zzaI(r1)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x004d }
            goto L_0x002e
        L_0x004d:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002e
        L_0x0054:
            r2.close()     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            java.lang.String r1 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            r5 = 0
            r1.<init>(r3, r5, r4)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            java.lang.String r3 = "Read client id from disk"
            r6.zza(r3, r1)     // Catch:{ FileNotFoundException -> 0x00ac, IOException -> 0x00aa }
            if (r2 == 0) goto L_0x0067
            r2.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0067:
            r0 = r1
            goto L_0x002e
        L_0x0069:
            r0 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r0)
            goto L_0x0067
        L_0x0070:
            r1 = move-exception
            r1 = r0
        L_0x0072:
            if (r1 == 0) goto L_0x002e
            r1.close()     // Catch:{ IOException -> 0x0078 }
            goto L_0x002e
        L_0x0078:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002e
        L_0x007f:
            r1 = move-exception
            r2 = r0
        L_0x0081:
            java.lang.String r3 = "Error reading client id file, deleting it"
            r6.zze(r3, r1)     // Catch:{ all -> 0x00a8 }
            java.lang.String r1 = "gaClientId"
            r7.deleteFile(r1)     // Catch:{ all -> 0x00a8 }
            if (r2 == 0) goto L_0x002e
            r2.close()     // Catch:{ IOException -> 0x0091 }
            goto L_0x002e
        L_0x0091:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x002e
        L_0x0098:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x009b:
            if (r2 == 0) goto L_0x00a0
            r2.close()     // Catch:{ IOException -> 0x00a1 }
        L_0x00a0:
            throw r0
        L_0x00a1:
            r1 = move-exception
            java.lang.String r2 = "Failed to close client id reading stream"
            r6.zze(r2, r1)
            goto L_0x00a0
        L_0x00a8:
            r0 = move-exception
            goto L_0x009b
        L_0x00aa:
            r1 = move-exception
            goto L_0x0081
        L_0x00ac:
            r1 = move-exception
            r1 = r2
            goto L_0x0072
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.internal.zzm.zzL(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
    }

    public String zzhI() {
        String str;
        zzgR();
        synchronized (this) {
            if (this.zzFg == null) {
                this.zzGK = zzgJ().zzb(new Callable<String>() {
                    /* renamed from: zzhN */
                    public String call() throws Exception {
                        return zzm.this.zzhK();
                    }
                });
            }
            if (this.zzGK != null) {
                try {
                    this.zzFg = this.zzGK.get();
                } catch (InterruptedException e) {
                    zzd("ClientId loading or generation was interrupted", e);
                    this.zzFg = "0";
                } catch (ExecutionException e2) {
                    zze("Failed to load or generate client id", e2);
                    this.zzFg = "0";
                }
                if (this.zzFg == null) {
                    this.zzFg = "0";
                }
                zza("Loaded clientId", this.zzFg);
                this.zzGK = null;
            }
            str = this.zzFg;
        }
        return str;
    }

    /* access modifiers changed from: package-private */
    public String zzhJ() {
        synchronized (this) {
            this.zzFg = null;
            this.zzGK = zzgJ().zzb(new Callable<String>() {
                /* renamed from: zzhN */
                public String call() throws Exception {
                    return zzm.this.zzhL();
                }
            });
        }
        return zzhI();
    }

    /* access modifiers changed from: package-private */
    public String zzhK() {
        String zzL = zzL(zzgJ().getContext());
        return zzL == null ? zzhL() : zzL;
    }

    /* access modifiers changed from: protected */
    public String zzhM() {
        return UUID.randomUUID().toString().toLowerCase();
    }
}
