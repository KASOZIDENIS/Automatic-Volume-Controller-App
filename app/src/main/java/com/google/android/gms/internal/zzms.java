package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.tagmanager.zzbg;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class zzms {
    public static final Integer zzaHy = 0;
    public static final Integer zzaHz = 1;
    private final Context mContext;
    private final ExecutorService zzaEN;

    public zzms(Context context) {
        this(context, Executors.newSingleThreadExecutor());
    }

    zzms(Context context, ExecutorService executorService) {
        this.mContext = context;
        this.zzaEN = executorService;
    }

    private String zzeu(String str) {
        return "resource_" + str;
    }

    private byte[] zzf(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            zzhy.zza(inputStream, (OutputStream) byteArrayOutputStream);
            try {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                zzbg.zzan("Error closing stream for reading resource from disk");
                return null;
            }
        } catch (IOException e2) {
            zzbg.zzan("Failed to read the resource from disk");
            try {
                inputStream.close();
            } catch (IOException e3) {
                zzbg.zzan("Error closing stream for reading resource from disk");
                return null;
            }
        } catch (Throwable th) {
            try {
                inputStream.close();
                throw th;
            } catch (IOException e4) {
                zzbg.zzan("Error closing stream for reading resource from disk");
                return null;
            }
        }
    }

    public void zza(String str, Integer num, zzmm zzmm, zzmr zzmr) {
        final String str2 = str;
        final Integer num2 = num;
        final zzmm zzmm2 = zzmm;
        final zzmr zzmr2 = zzmr;
        this.zzaEN.execute(new Runnable() {
            public void run() {
                zzms.this.zzb(str2, num2, zzmm2, zzmr2);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void zzb(String str, Integer num, zzmm zzmm, zzmr zzmr) {
        Object zzp;
        zzbg.zzam("DiskLoader: Starting to load resource from Disk.");
        try {
            Object zzp2 = zzmm.zzp(zzf(new FileInputStream(zzet(str))));
            if (zzp2 != null) {
                zzbg.zzam("Saved resource loaded: " + zzeu(str));
                zzmr.zza(Status.zzQU, zzp2, zzaHz, zzes(str));
                return;
            }
        } catch (FileNotFoundException e) {
            zzbg.zzak("Saved resource not found: " + zzeu(str));
        } catch (zzmq.zzg e2) {
            zzbg.zzak("Saved resource is corrupted: " + zzeu(str));
        }
        if (num == null) {
            zzmr.zza(Status.zzQW, (Object) null, (Integer) null, 0);
            return;
        }
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(num.intValue());
            if (!(openRawResource == null || (zzp = zzmm.zzp(zzf(openRawResource))) == null)) {
                zzbg.zzam("Default resource loaded: " + this.mContext.getResources().getResourceEntryName(num.intValue()));
                zzmr.zza(Status.zzQU, zzp, zzaHy, 0);
                return;
            }
        } catch (Resources.NotFoundException e3) {
            zzbg.zzak("Default resource not found. ID: " + num);
        } catch (zzmq.zzg e4) {
            zzbg.zzak("Default resource resource is corrupted: " + num);
        }
        zzmr.zza(Status.zzQW, (Object) null, (Integer) null, 0);
    }

    public void zze(final String str, final byte[] bArr) {
        this.zzaEN.execute(new Runnable() {
            public void run() {
                zzms.this.zzf(str, bArr);
            }
        });
    }

    public long zzes(String str) {
        File zzet = zzet(str);
        if (zzet.exists()) {
            return zzet.lastModified();
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public File zzet(String str) {
        return new File(this.mContext.getDir("google_tagmanager", 0), zzeu(str));
    }

    /* access modifiers changed from: package-private */
    public void zzf(String str, byte[] bArr) {
        File zzet = zzet(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzet);
            try {
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.close();
                    zzbg.zzam("Resource " + str + " saved on Disk.");
                } catch (IOException e) {
                    zzbg.zzak("Error closing stream for writing resource to disk");
                }
            } catch (IOException e2) {
                zzbg.zzak("Error writing resource to disk. Removing resource from disk");
                zzet.delete();
                try {
                    fileOutputStream.close();
                    zzbg.zzam("Resource " + str + " saved on Disk.");
                } catch (IOException e3) {
                    zzbg.zzak("Error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                    zzbg.zzam("Resource " + str + " saved on Disk.");
                } catch (IOException e4) {
                    zzbg.zzak("Error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            zzbg.zzak("Error opening resource file for writing");
        }
    }
}
