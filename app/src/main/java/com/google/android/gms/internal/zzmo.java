package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzv;
import java.util.ArrayList;
import java.util.List;

public class zzmo {
    private final List<zzmj> zzaGZ = new ArrayList();

    public String getId() {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        for (zzmj next : this.zzaGZ) {
            if (z) {
                z = false;
            } else {
                sb.append("#");
            }
            sb.append(next.getContainerId());
        }
        return sb.toString();
    }

    public zzmo zzb(zzmj zzmj) throws IllegalArgumentException {
        zzv.zzr(zzmj);
        for (zzmj containerId : this.zzaGZ) {
            if (containerId.getContainerId().equals(zzmj.getContainerId())) {
                throw new IllegalArgumentException("The container is already being requested. " + zzmj.getContainerId());
            }
        }
        this.zzaGZ.add(zzmj);
        return this;
    }

    public List<zzmj> zzyl() {
        return this.zzaGZ;
    }
}
