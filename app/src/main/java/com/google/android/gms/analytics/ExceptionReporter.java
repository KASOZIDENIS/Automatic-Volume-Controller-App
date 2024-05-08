package com.google.android.gms.analytics;

import android.content.Context;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.internal.zzad;
import java.lang.Thread;
import java.util.ArrayList;

public class ExceptionReporter implements Thread.UncaughtExceptionHandler {
    private final Context mContext;
    private final Thread.UncaughtExceptionHandler zzEs;
    private final Tracker zzEt;
    private ExceptionParser zzEu;
    private GoogleAnalytics zzEv;

    public ExceptionReporter(Tracker tracker, Thread.UncaughtExceptionHandler originalHandler, Context context) {
        if (tracker == null) {
            throw new NullPointerException("tracker cannot be null");
        } else if (context == null) {
            throw new NullPointerException("context cannot be null");
        } else {
            this.zzEs = originalHandler;
            this.zzEt = tracker;
            this.zzEu = new StandardExceptionParser(context, new ArrayList());
            this.mContext = context.getApplicationContext();
            zzad.zzam("ExceptionReporter created, original handler is " + (originalHandler == null ? "null" : originalHandler.getClass().getName()));
        }
    }

    public ExceptionParser getExceptionParser() {
        return this.zzEu;
    }

    public void setExceptionParser(ExceptionParser exceptionParser) {
        this.zzEu = exceptionParser;
    }

    public void uncaughtException(Thread t, Throwable e) {
        String str = "UncaughtException";
        if (this.zzEu != null) {
            str = this.zzEu.getDescription(t != null ? t.getName() : null, e);
        }
        zzad.zzam("Reporting uncaught exception: " + str);
        this.zzEt.send(new HitBuilders.ExceptionBuilder().setDescription(str).setFatal(true).build());
        GoogleAnalytics zzfT = zzfT();
        zzfT.dispatchLocalHits();
        zzfT.zzfY();
        if (this.zzEs != null) {
            zzad.zzam("Passing exception to the original handler");
            this.zzEs.uncaughtException(t, e);
        }
    }

    /* access modifiers changed from: package-private */
    public GoogleAnalytics zzfT() {
        if (this.zzEv == null) {
            this.zzEv = GoogleAnalytics.getInstance(this.mContext);
        }
        return this.zzEv;
    }

    /* access modifiers changed from: package-private */
    public Thread.UncaughtExceptionHandler zzfU() {
        return this.zzEs;
    }
}
