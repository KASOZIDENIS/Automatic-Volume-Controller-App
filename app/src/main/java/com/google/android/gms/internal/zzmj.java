package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;

public class zzmj {
    private final String zzaCk;
    private final String zzaEI;
    private final Integer zzaGN;
    private final String zzaGO;
    private final boolean zzaGP;

    public zzmj(String str, Integer num, String str2, boolean z) {
        this(str, num, str2, z, "");
    }

    public zzmj(String str, Integer num, String str2, boolean z, String str3) {
        zzv.zzr(str);
        zzv.zzr(str3);
        this.zzaCk = str;
        this.zzaGN = num;
        this.zzaGO = str2;
        this.zzaGP = z;
        this.zzaEI = str3;
    }

    public String getContainerId() {
        return this.zzaCk;
    }

    public Integer zzyf() {
        return this.zzaGN;
    }

    public String zzyg() {
        return this.zzaGO;
    }

    public String zzyh() {
        return this.zzaGO != null ? this.zzaGO + "_" + this.zzaCk : this.zzaCk;
    }

    public boolean zzyi() {
        return this.zzaGP;
    }

    public String zzyj() {
        return this.zzaEI;
    }
}
