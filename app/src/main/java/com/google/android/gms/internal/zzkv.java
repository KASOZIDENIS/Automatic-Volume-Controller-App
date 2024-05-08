package com.google.android.gms.internal;

import android.support.v7.internal.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzv;
import java.util.HashMap;
import java.util.UUID;

public final class zzkv extends zzki<zzkv> {
    private String zzawM;
    private int zzawN;
    private int zzawO;
    private String zzawP;
    private String zzawQ;
    private boolean zzawR;
    private boolean zzawS;
    private boolean zzawT;

    public zzkv() {
        this(false);
    }

    public zzkv(boolean z) {
        this(z, zzuK());
    }

    public zzkv(boolean z, int i) {
        zzv.zzaR(i);
        this.zzawN = i;
        this.zzawS = z;
    }

    static int zzuK() {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits != 0) {
            return leastSignificantBits;
        }
        int mostSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
        if (mostSignificantBits != 0) {
            return mostSignificantBits;
        }
        Log.e("GAv4", "UUID.randomUUID() returned 0.");
        return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }

    private void zzuR() {
        if (this.zzawT) {
            throw new IllegalStateException("ScreenViewInfo is immutable");
        }
    }

    public boolean isMutable() {
        return !this.zzawT;
    }

    public void setScreenName(String screenName) {
        zzuR();
        this.zzawM = screenName;
    }

    public String toString() {
        HashMap hashMap = new HashMap();
        hashMap.put("screenName", this.zzawM);
        hashMap.put("interstitial", Boolean.valueOf(this.zzawR));
        hashMap.put("automatic", Boolean.valueOf(this.zzawS));
        hashMap.put("screenId", Integer.valueOf(this.zzawN));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzawO));
        hashMap.put("referrerScreenName", this.zzawP);
        hashMap.put("referrerUri", this.zzawQ);
        return zzu(hashMap);
    }

    public int zzaJ() {
        return this.zzawN;
    }

    public void zzac(boolean z) {
        zzuR();
        this.zzawS = z;
    }

    public void zzad(boolean z) {
        zzuR();
        this.zzawR = z;
    }

    /* renamed from: zzc */
    public void zza(zzkv zzkv) {
        if (!TextUtils.isEmpty(this.zzawM)) {
            zzkv.setScreenName(this.zzawM);
        }
        if (this.zzawN != 0) {
            zzkv.zzgI(this.zzawN);
        }
        if (this.zzawO != 0) {
            zzkv.zzgJ(this.zzawO);
        }
        if (!TextUtils.isEmpty(this.zzawP)) {
            zzkv.zzdd(this.zzawP);
        }
        if (!TextUtils.isEmpty(this.zzawQ)) {
            zzkv.zzde(this.zzawQ);
        }
        if (this.zzawR) {
            zzkv.zzad(this.zzawR);
        }
        if (this.zzawS) {
            zzkv.zzac(this.zzawS);
        }
    }

    public void zzdd(String str) {
        zzuR();
        this.zzawP = str;
    }

    public void zzde(String str) {
        zzuR();
        if (TextUtils.isEmpty(str)) {
            this.zzawQ = null;
        } else {
            this.zzawQ = str;
        }
    }

    public void zzgI(int i) {
        zzuR();
        this.zzawN = i;
    }

    public void zzgJ(int i) {
        zzuR();
        this.zzawO = i;
    }

    public String zzuL() {
        return this.zzawM;
    }

    public int zzuM() {
        return this.zzawO;
    }

    public String zzuN() {
        return this.zzawP;
    }

    public String zzuO() {
        return this.zzawQ;
    }

    public void zzuP() {
        this.zzawT = true;
    }

    public boolean zzuQ() {
        return this.zzawR;
    }
}
