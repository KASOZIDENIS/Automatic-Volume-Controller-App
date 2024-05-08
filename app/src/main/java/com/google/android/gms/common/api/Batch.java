package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.AbstractPendingResult;
import com.google.android.gms.common.api.PendingResult;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends AbstractPendingResult<BatchResult> {
    /* access modifiers changed from: private */
    public int zzPr;
    /* access modifiers changed from: private */
    public boolean zzPs;
    /* access modifiers changed from: private */
    public boolean zzPt;
    /* access modifiers changed from: private */
    public final PendingResult<?>[] zzPu;
    /* access modifiers changed from: private */
    public final Object zzoe;

    public static final class Builder {
        private List<PendingResult<?>> zzPw = new ArrayList();
        private Looper zzPx;

        public Builder(GoogleApiClient googleApiClient) {
            this.zzPx = googleApiClient.getLooper();
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken<>(this.zzPw.size());
            this.zzPw.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.zzPw, this.zzPx);
        }
    }

    private Batch(List<PendingResult<?>> pendingResultList, Looper looper) {
        super(new AbstractPendingResult.CallbackHandler(looper));
        this.zzoe = new Object();
        this.zzPr = pendingResultList.size();
        this.zzPu = new PendingResult[this.zzPr];
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 < pendingResultList.size()) {
                PendingResult<?> pendingResult = pendingResultList.get(i2);
                this.zzPu[i2] = pendingResult;
                pendingResult.addBatchCallback(new PendingResult.BatchCallback() {
                    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
                        return;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void zzl(com.google.android.gms.common.api.Status r6) {
                        /*
                            r5 = this;
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this
                            java.lang.Object r1 = r0.zzoe
                            monitor-enter(r1)
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0011
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                        L_0x0010:
                            return
                        L_0x0011:
                            boolean r0 = r6.isCanceled()     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x003c
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            boolean unused = r0.zzPt = r2     // Catch:{ all -> 0x0039 }
                        L_0x001d:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch.zzb(r0)     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            int r0 = r0.zzPr     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x0037
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.zzPt     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0049
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch.super.cancel()     // Catch:{ all -> 0x0039 }
                        L_0x0037:
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            goto L_0x0010
                        L_0x0039:
                            r0 = move-exception
                            monitor-exit(r1)     // Catch:{ all -> 0x0039 }
                            throw r0
                        L_0x003c:
                            boolean r0 = r6.isSuccess()     // Catch:{ all -> 0x0039 }
                            if (r0 != 0) goto L_0x001d
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            r2 = 1
                            boolean unused = r0.zzPs = r2     // Catch:{ all -> 0x0039 }
                            goto L_0x001d
                        L_0x0049:
                            com.google.android.gms.common.api.Batch r0 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            boolean r0 = r0.zzPs     // Catch:{ all -> 0x0039 }
                            if (r0 == 0) goto L_0x0069
                            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0039 }
                            r2 = 13
                            r0.<init>(r2)     // Catch:{ all -> 0x0039 }
                        L_0x0058:
                            com.google.android.gms.common.api.Batch r2 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.BatchResult r3 = new com.google.android.gms.common.api.BatchResult     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.Batch r4 = com.google.android.gms.common.api.Batch.this     // Catch:{ all -> 0x0039 }
                            com.google.android.gms.common.api.PendingResult[] r4 = r4.zzPu     // Catch:{ all -> 0x0039 }
                            r3.<init>(r0, r4)     // Catch:{ all -> 0x0039 }
                            r2.setResult(r3)     // Catch:{ all -> 0x0039 }
                            goto L_0x0037
                        L_0x0069:
                            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zzQU     // Catch:{ all -> 0x0039 }
                            goto L_0x0058
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.Batch.AnonymousClass1.zzl(com.google.android.gms.common.api.Status):void");
                    }
                });
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    static /* synthetic */ int zzb(Batch batch) {
        int i = batch.zzPr;
        batch.zzPr = i - 1;
        return i;
    }

    public void cancel() {
        super.cancel();
        for (PendingResult<?> cancel : this.zzPu) {
            cancel.cancel();
        }
    }

    public BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zzPu);
    }
}
