package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzal;
import com.google.android.gms.common.internal.zzv;
import java.lang.Thread;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzkk {
    private static volatile zzkk zzawk;
    private final Context mContext;
    private volatile zzkp zzGq;
    /* access modifiers changed from: private */
    public final List<zzkl> zzawl = new CopyOnWriteArrayList();
    private final zzkf zzawm = new zzkf();
    private final zza zzawn = new zza();
    /* access modifiers changed from: private */
    public Thread.UncaughtExceptionHandler zzawo;

    private class zza extends ThreadPoolExecutor {
        public zza() {
            super(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
            setThreadFactory(new zzb());
        }

        /* access modifiers changed from: protected */
        public <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value) {
            return new FutureTask<T>(runnable, value) {
                /* access modifiers changed from: protected */
                public void setException(Throwable error) {
                    Thread.UncaughtExceptionHandler zzb = zzkk.this.zzawo;
                    if (zzb != null) {
                        zzb.uncaughtException(Thread.currentThread(), error);
                    } else if (Log.isLoggable("GAv4", 6)) {
                        Log.e("GAv4", "MeasurementExecutor: job failed with " + error);
                    }
                    super.setException(error);
                }
            };
        }
    }

    private static class zzb implements ThreadFactory {
        private static final AtomicInteger zzaws = new AtomicInteger();

        private zzb() {
        }

        public Thread newThread(Runnable target) {
            return new zzc(target, "measurement-" + zzaws.incrementAndGet());
        }
    }

    private static class zzc extends Thread {
        zzc(Runnable runnable, String str) {
            super(runnable, str);
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    zzkk(Context context) {
        Context applicationContext = context.getApplicationContext();
        zzv.zzr(applicationContext);
        this.mContext = applicationContext;
    }

    public static zzkk zzak(Context context) {
        zzv.zzr(context);
        if (zzawk == null) {
            synchronized (zzkk.class) {
                if (zzawk == null) {
                    zzawk = new zzkk(context);
                }
            }
        }
        return zzawk;
    }

    /* access modifiers changed from: private */
    public void zzb(zzkg zzkg) {
        zzv.zzbJ("deliver should be called from worker thread");
        zzv.zzb(zzkg.zzue(), (Object) "Measurement must be submitted");
        List<zzkm> zzub = zzkg.zzub();
        if (!zzub.isEmpty()) {
            HashSet hashSet = new HashSet();
            for (zzkm next : zzub) {
                Uri zzfR = next.zzfR();
                if (!hashSet.contains(zzfR)) {
                    hashSet.add(zzfR);
                    next.zzb(zzkg);
                }
            }
        }
    }

    public static void zzgF() {
        if (!(Thread.currentThread() instanceof zzc)) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public void zza(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzawo = uncaughtExceptionHandler;
    }

    public <V> Future<V> zzb(Callable<V> callable) {
        zzv.zzr(callable);
        if (!(Thread.currentThread() instanceof zzc)) {
            return this.zzawn.submit(callable);
        }
        FutureTask futureTask = new FutureTask(callable);
        futureTask.run();
        return futureTask;
    }

    /* access modifiers changed from: package-private */
    public void zze(zzkg zzkg) {
        if (zzkg.zzui()) {
            throw new IllegalStateException("Measurement prototype can't be submitted");
        } else if (zzkg.zzue()) {
            throw new IllegalStateException("Measurement can only be submitted once");
        } else {
            final zzkg zztZ = zzkg.zztZ();
            zztZ.zzuf();
            this.zzawn.execute(new Runnable() {
                public void run() {
                    zztZ.zzug().zza(zztZ);
                    for (zzkl zza : zzkk.this.zzawl) {
                        zza.zza(zztZ);
                    }
                    zzkk.this.zzb(zztZ);
                }
            });
        }
    }

    public void zze(Runnable runnable) {
        zzv.zzr(runnable);
        this.zzawn.submit(runnable);
    }

    public zzkp zzum() {
        if (this.zzGq == null) {
            synchronized (this) {
                if (this.zzGq == null) {
                    zzkp zzkp = new zzkp();
                    PackageManager packageManager = this.mContext.getPackageManager();
                    String packageName = this.mContext.getPackageName();
                    zzkp.setAppId(packageName);
                    zzkp.setAppInstallerId(packageManager.getInstallerPackageName(packageName));
                    String str = null;
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(this.mContext.getPackageName(), 0);
                        if (packageInfo != null) {
                            CharSequence applicationLabel = packageManager.getApplicationLabel(packageInfo.applicationInfo);
                            if (!TextUtils.isEmpty(applicationLabel)) {
                                packageName = applicationLabel.toString();
                            }
                            str = packageInfo.versionName;
                        }
                    } catch (PackageManager.NameNotFoundException e) {
                        Log.e("GAv4", "Error retrieving package info: appName set to " + packageName);
                    }
                    zzkp.setAppName(packageName);
                    zzkp.setAppVersion(str);
                    this.zzGq = zzkp;
                }
            }
        }
        return this.zzGq;
    }

    public zzkr zzun() {
        DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
        zzkr zzkr = new zzkr();
        zzkr.setLanguage(zzal.zza(Locale.getDefault()));
        zzkr.zzgE(displayMetrics.widthPixels);
        zzkr.zzgF(displayMetrics.heightPixels);
        return zzkr;
    }
}
