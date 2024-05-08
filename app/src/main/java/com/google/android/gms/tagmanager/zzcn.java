package com.google.android.gms.tagmanager;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.internal.zzc;
import com.google.android.gms.internal.zzmi;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zznx;
import com.google.android.gms.internal.zzny;
import com.google.android.gms.tagmanager.zzbf;
import com.google.android.gms.tagmanager.zzcb;
import com.google.android.gms.tagmanager.zzp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;

class zzcn implements zzp.zzf {
    private final Context mContext;
    private final String zzaCk;
    private zzbf<zzmi.zza> zzaEG;
    private final ExecutorService zzaEN = Executors.newSingleThreadExecutor();

    zzcn(Context context, String str) {
        this.mContext = context;
        this.zzaCk = str;
    }

    private zzmq.zzc zza(ByteArrayOutputStream byteArrayOutputStream) {
        try {
            return zzaz.zzdQ(byteArrayOutputStream.toString("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            zzbg.zzaj("Failed to convert binary resource to string for JSON parsing; the file format is not UTF-8 format.");
            return null;
        } catch (JSONException e2) {
            zzbg.zzan("Failed to extract the container from the resource file. Resource is a UTF-8 encoded string but doesn't contain a JSON container");
            return null;
        }
    }

    private void zzd(zzmi.zza zza) throws IllegalArgumentException {
        if (zza.zzhh == null && zza.zzaGM == null) {
            throw new IllegalArgumentException("Resource and SupplementedResource are NULL.");
        }
    }

    private zzmq.zzc zzn(byte[] bArr) {
        try {
            zzmq.zzc zzb = zzmq.zzb(zzc.zzf.zza(bArr));
            if (zzb == null) {
                return zzb;
            }
            zzbg.zzam("The container was successfully loaded from the resource (using binary file)");
            return zzb;
        } catch (zznx e) {
            zzbg.zzak("The resource file is corrupted. The container cannot be extracted from the binary file");
            return null;
        } catch (zzmq.zzg e2) {
            zzbg.zzan("The resource file is invalid. The container from the binary file is invalid");
            return null;
        }
    }

    public synchronized void release() {
        this.zzaEN.shutdown();
    }

    public void zza(zzbf<zzmi.zza> zzbf) {
        this.zzaEG = zzbf;
    }

    public void zzb(final zzmi.zza zza) {
        this.zzaEN.execute(new Runnable() {
            public void run() {
                zzcn.this.zzc(zza);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public boolean zzc(zzmi.zza zza) {
        boolean z = false;
        File zzxu = zzxu();
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(zzxu);
            try {
                fileOutputStream.write(zzny.zzf(zza));
                z = true;
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    zzbg.zzan("error closing stream for writing resource to disk");
                }
            } catch (IOException e2) {
                zzbg.zzan("Error writing resource to disk. Removing resource from disk.");
                zzxu.delete();
                try {
                    fileOutputStream.close();
                } catch (IOException e3) {
                    zzbg.zzan("error closing stream for writing resource to disk");
                }
            } catch (Throwable th) {
                try {
                    fileOutputStream.close();
                } catch (IOException e4) {
                    zzbg.zzan("error closing stream for writing resource to disk");
                }
                throw th;
            }
        } catch (FileNotFoundException e5) {
            zzbg.zzak("Error opening resource file for writing");
        }
        return z;
    }

    public zzmq.zzc zzhC(int i) {
        try {
            InputStream openRawResource = this.mContext.getResources().openRawResource(i);
            zzbg.zzam("Attempting to load a container from the resource ID " + i + " (" + this.mContext.getResources().getResourceName(i) + ")");
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzmq.zzc(openRawResource, byteArrayOutputStream);
                zzmq.zzc zza = zza(byteArrayOutputStream);
                if (zza == null) {
                    return zzn(byteArrayOutputStream.toByteArray());
                }
                zzbg.zzam("The container was successfully loaded from the resource (using JSON file format)");
                return zza;
            } catch (IOException e) {
                zzbg.zzan("Error reading the default container with resource ID " + i + " (" + this.mContext.getResources().getResourceName(i) + ")");
                return null;
            }
        } catch (Resources.NotFoundException e2) {
            zzbg.zzan("Failed to load the container. No default container resource found with the resource ID " + i);
            return null;
        }
    }

    public void zzwC() {
        this.zzaEN.execute(new Runnable() {
            public void run() {
                zzcn.this.zzxt();
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void zzxt() {
        if (this.zzaEG == null) {
            throw new IllegalStateException("Callback must be set before execute");
        }
        this.zzaEG.zzwB();
        zzbg.zzam("Attempting to load resource from disk");
        if ((zzcb.zzxl().zzxm() == zzcb.zza.CONTAINER || zzcb.zzxl().zzxm() == zzcb.zza.CONTAINER_DEBUG) && this.zzaCk.equals(zzcb.zzxl().getContainerId())) {
            this.zzaEG.zza(zzbf.zza.NOT_AVAILABLE);
            return;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(zzxu());
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                zzmq.zzc(fileInputStream, byteArrayOutputStream);
                zzmi.zza zzo = zzmi.zza.zzo(byteArrayOutputStream.toByteArray());
                zzd(zzo);
                this.zzaEG.zzv(zzo);
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    zzbg.zzan("Error closing stream for reading resource from disk");
                }
            } catch (IOException e2) {
                this.zzaEG.zza(zzbf.zza.IO_ERROR);
                zzbg.zzan("Failed to read the resource from disk");
                try {
                    fileInputStream.close();
                } catch (IOException e3) {
                    zzbg.zzan("Error closing stream for reading resource from disk");
                }
            } catch (IllegalArgumentException e4) {
                this.zzaEG.zza(zzbf.zza.IO_ERROR);
                zzbg.zzan("Failed to read the resource from disk. The resource is inconsistent");
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    zzbg.zzan("Error closing stream for reading resource from disk");
                }
            } catch (Throwable th) {
                try {
                    fileInputStream.close();
                } catch (IOException e6) {
                    zzbg.zzan("Error closing stream for reading resource from disk");
                }
                throw th;
            }
            zzbg.zzam("The Disk resource was successfully read.");
        } catch (FileNotFoundException e7) {
            zzbg.zzaj("Failed to find the resource in the disk");
            this.zzaEG.zza(zzbf.zza.NOT_AVAILABLE);
        }
    }

    /* access modifiers changed from: package-private */
    public File zzxu() {
        return new File(this.mContext.getDir("google_tagmanager", 0), "resource_" + this.zzaCk);
    }
}
