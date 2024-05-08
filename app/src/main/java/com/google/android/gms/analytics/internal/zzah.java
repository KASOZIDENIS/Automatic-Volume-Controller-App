package com.google.android.gms.analytics.internal;

import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.internal.zzv;
import java.util.UUID;

public class zzah extends zzd {
    /* access modifiers changed from: private */
    public SharedPreferences zzIA;
    private long zzIB;
    private long zzIC = -1;
    private final zza zzID = new zza("monitoring", zzgI().zziv());

    public final class zza {
        private final String mName;
        private final long zzIE;

        private zza(String str, long j) {
            zzv.zzbS(str);
            zzv.zzQ(j > 0);
            this.mName = str;
            this.zzIE = j;
        }

        private void zzjj() {
            long currentTimeMillis = zzah.this.zzgG().currentTimeMillis();
            SharedPreferences.Editor edit = zzah.this.zzIA.edit();
            edit.remove(zzjo());
            edit.remove(zzjp());
            edit.putLong(zzjn(), currentTimeMillis);
            edit.commit();
        }

        private long zzjk() {
            long zzjm = zzjm();
            if (zzjm == 0) {
                return 0;
            }
            return Math.abs(zzjm - zzah.this.zzgG().currentTimeMillis());
        }

        private long zzjm() {
            return zzah.this.zzIA.getLong(zzjn(), 0);
        }

        private String zzjn() {
            return this.mName + ":start";
        }

        private String zzjo() {
            return this.mName + ":count";
        }

        public void zzaS(String str) {
            if (zzjm() == 0) {
                zzjj();
            }
            if (str == null) {
                str = "";
            }
            synchronized (this) {
                long j = zzah.this.zzIA.getLong(zzjo(), 0);
                if (j <= 0) {
                    SharedPreferences.Editor edit = zzah.this.zzIA.edit();
                    edit.putString(zzjp(), str);
                    edit.putLong(zzjo(), 1);
                    edit.apply();
                    return;
                }
                boolean z = (UUID.randomUUID().getLeastSignificantBits() & Long.MAX_VALUE) < Long.MAX_VALUE / (j + 1);
                SharedPreferences.Editor edit2 = zzah.this.zzIA.edit();
                if (z) {
                    edit2.putString(zzjp(), str);
                }
                edit2.putLong(zzjo(), j + 1);
                edit2.apply();
            }
        }

        public Pair<String, Long> zzjl() {
            long zzjk = zzjk();
            if (zzjk < this.zzIE) {
                return null;
            }
            if (zzjk > this.zzIE * 2) {
                zzjj();
                return null;
            }
            String string = zzah.this.zzIA.getString(zzjp(), (String) null);
            long j = zzah.this.zzIA.getLong(zzjo(), 0);
            zzjj();
            if (string == null || j <= 0) {
                return null;
            }
            return new Pair<>(string, Long.valueOf(j));
        }

        /* access modifiers changed from: protected */
        public String zzjp() {
            return this.mName + ":value";
        }
    }

    protected zzah(zze zze) {
        super(zze);
    }

    public void zzaR(String str) {
        zzgF();
        zzgR();
        SharedPreferences.Editor edit = this.zzIA.edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("installation_campaign");
        } else {
            edit.putString("installation_campaign", str);
        }
        if (!edit.commit()) {
            zzaI("Failed to commit campaign data");
        }
    }

    /* access modifiers changed from: protected */
    public void zzgb() {
        this.zzIA = getContext().getSharedPreferences("com.google.android.gms.analytics.prefs", 0);
    }

    public long zzjd() {
        zzgF();
        zzgR();
        if (this.zzIB == 0) {
            long j = this.zzIA.getLong("first_run", 0);
            if (j != 0) {
                this.zzIB = j;
            } else {
                long currentTimeMillis = zzgG().currentTimeMillis();
                SharedPreferences.Editor edit = this.zzIA.edit();
                edit.putLong("first_run", currentTimeMillis);
                if (!edit.commit()) {
                    zzaI("Failed to commit first run time");
                }
                this.zzIB = currentTimeMillis;
            }
        }
        return this.zzIB;
    }

    public zzai zzje() {
        return new zzai(zzgG(), zzjd());
    }

    public long zzjf() {
        zzgF();
        zzgR();
        if (this.zzIC == -1) {
            this.zzIC = this.zzIA.getLong("last_dispatch", 0);
        }
        return this.zzIC;
    }

    public void zzjg() {
        zzgF();
        zzgR();
        long currentTimeMillis = zzgG().currentTimeMillis();
        SharedPreferences.Editor edit = this.zzIA.edit();
        edit.putLong("last_dispatch", currentTimeMillis);
        edit.apply();
        this.zzIC = currentTimeMillis;
    }

    public String zzjh() {
        zzgF();
        zzgR();
        String string = this.zzIA.getString("installation_campaign", (String) null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    public zza zzji() {
        return this.zzID;
    }
}
