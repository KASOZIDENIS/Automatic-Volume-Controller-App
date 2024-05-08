package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public class zza extends zzd {
    public static boolean zzFo;
    private AdvertisingIdClient.Info zzFp;
    private final zzai zzFq;
    private String zzFr;
    private boolean zzFs = false;
    private Object zzFt = new Object();

    zza(zze zze) {
        super(zze);
        this.zzFq = new zzai(zze.zzgG());
    }

    private boolean zza(AdvertisingIdClient.Info info, AdvertisingIdClient.Info info2) {
        String str;
        String str2 = null;
        String id = info2 == null ? null : info2.getId();
        if (TextUtils.isEmpty(id)) {
            return true;
        }
        String zzhI = zzgM().zzhI();
        synchronized (this.zzFt) {
            if (!this.zzFs) {
                this.zzFr = zzgu();
                this.zzFs = true;
            } else if (TextUtils.isEmpty(this.zzFr)) {
                if (info != null) {
                    str2 = info.getId();
                }
                if (str2 == null) {
                    boolean zzaE = zzaE(id + zzhI);
                    return zzaE;
                }
                this.zzFr = zzaD(str2 + zzhI);
            }
            String zzaD = zzaD(id + zzhI);
            if (TextUtils.isEmpty(zzaD)) {
                return false;
            }
            if (zzaD.equals(this.zzFr)) {
                return true;
            }
            if (!TextUtils.isEmpty(this.zzFr)) {
                zzaF("Resetting the client id because Advertising Id changed.");
                str = zzgM().zzhJ();
                zza("New client Id", str);
            } else {
                str = zzhI;
            }
            boolean zzaE2 = zzaE(id + str);
            return zzaE2;
        }
    }

    private static String zzaD(String str) {
        MessageDigest zzaX = zzal.zzaX("MD5");
        if (zzaX == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, zzaX.digest(str.getBytes()))});
    }

    private boolean zzaE(String str) {
        try {
            String zzaD = zzaD(str);
            zzaF("Storing hashed adid.");
            FileOutputStream openFileOutput = getContext().openFileOutput("gaClientIdData", 0);
            openFileOutput.write(zzaD.getBytes());
            openFileOutput.close();
            this.zzFr = zzaD;
            return true;
        } catch (IOException e) {
            zze("Error creating hash file", e);
            return false;
        }
    }

    private synchronized AdvertisingIdClient.Info zzgs() {
        if (this.zzFq.zzt(1000)) {
            this.zzFq.start();
            AdvertisingIdClient.Info zzgt = zzgt();
            if (zza(this.zzFp, zzgt)) {
                this.zzFp = zzgt;
            } else {
                zzaJ("Failed to reset client id on adid change. Not using adid");
                this.zzFp = new AdvertisingIdClient.Info("", false);
            }
        }
        return this.zzFp;
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
    }

    public boolean zzgn() {
        zzgR();
        AdvertisingIdClient.Info zzgs = zzgs();
        return zzgs != null && !zzgs.isLimitAdTrackingEnabled();
    }

    public String zzgr() {
        zzgR();
        AdvertisingIdClient.Info zzgs = zzgs();
        String id = zzgs != null ? zzgs.getId() : null;
        if (TextUtils.isEmpty(id)) {
            return null;
        }
        return id;
    }

    /* access modifiers changed from: protected */
    public AdvertisingIdClient.Info zzgt() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(getContext());
        } catch (IllegalStateException e) {
            zzaI("IllegalStateException getting Ad Id Info. If you would like to see Audience reports, please ensure that you have added '<meta-data android:name=\"com.google.android.gms.version\" android:value=\"@integer/google_play_services_version\" />' to your application manifest file. See http://goo.gl/naFqQk for details.");
            return null;
        } catch (Throwable th) {
            if (zzFo) {
                return null;
            }
            zzFo = true;
            zzd("Error getting advertiser id", th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String zzgu() {
        String str = null;
        try {
            FileInputStream openFileInput = getContext().openFileInput("gaClientIdData");
            byte[] bArr = new byte[128];
            int read = openFileInput.read(bArr, 0, 128);
            if (openFileInput.available() > 0) {
                zzaI("Hash file seems corrupted, deleting it.");
                openFileInput.close();
                getContext().deleteFile("gaClientIdData");
                return null;
            } else if (read <= 0) {
                zzaF("Hash file is empty.");
                openFileInput.close();
                return null;
            } else {
                String str2 = new String(bArr, 0, read);
                try {
                    openFileInput.close();
                    return str2;
                } catch (FileNotFoundException e) {
                    return str2;
                } catch (IOException e2) {
                    IOException iOException = e2;
                    str = str2;
                    e = iOException;
                    zzd("Error reading Hash file, deleting it", e);
                    getContext().deleteFile("gaClientIdData");
                    return str;
                }
            }
        } catch (FileNotFoundException e3) {
            return null;
        } catch (IOException e4) {
            e = e4;
            zzd("Error reading Hash file, deleting it", e);
            getContext().deleteFile("gaClientIdData");
            return str;
        }
    }
}
