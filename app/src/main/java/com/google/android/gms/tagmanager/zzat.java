package com.google.android.gms.tagmanager;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.concurrent.LinkedBlockingQueue;

class zzat extends Thread implements zzas {
    private static zzat zzaDB;
    private volatile boolean mClosed = false;
    /* access modifiers changed from: private */
    public final Context mContext;
    private volatile boolean zzGY = false;
    private final LinkedBlockingQueue<Runnable> zzaDA = new LinkedBlockingQueue<>();
    /* access modifiers changed from: private */
    public volatile zzau zzaDC;

    private zzat(Context context) {
        super("GAThread");
        if (context != null) {
            this.mContext = context.getApplicationContext();
        } else {
            this.mContext = context;
        }
        start();
    }

    static zzat zzaq(Context context) {
        if (zzaDB == null) {
            zzaDB = new zzat(context);
        }
        return zzaDB;
    }

    private String zzd(Throwable th) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        th.printStackTrace(printStream);
        printStream.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public void run() {
        while (!this.mClosed) {
            try {
                Runnable take = this.zzaDA.take();
                if (!this.zzGY) {
                    take.run();
                }
            } catch (InterruptedException e) {
                zzbg.zzal(e.toString());
            } catch (Throwable th) {
                zzbg.zzak("Error on Google TagManager Thread: " + zzd(th));
                zzbg.zzak("Google TagManager is shutting down.");
                this.zzGY = true;
            }
        }
    }

    public void zzdO(String str) {
        zzf(str, System.currentTimeMillis());
    }

    public void zzf(Runnable runnable) {
        this.zzaDA.add(runnable);
    }

    /* access modifiers changed from: package-private */
    public void zzf(String str, long j) {
        final long j2 = j;
        final String str2 = str;
        zzf(new Runnable() {
            public void run() {
                if (zzat.this.zzaDC == null) {
                    zzcu zzxF = zzcu.zzxF();
                    zzxF.zza(zzat.this.mContext, this);
                    zzau unused = zzat.this.zzaDC = zzxF.zzxI();
                }
                zzat.this.zzaDC.zzg(j2, str2);
            }
        });
    }
}
