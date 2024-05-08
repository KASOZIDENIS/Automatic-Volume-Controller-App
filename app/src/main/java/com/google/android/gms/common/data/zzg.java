package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T> extends zzb<T> {
    private T zzRQ;

    public zzg(DataBuffer<T> dataBuffer) {
        super(dataBuffer);
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzRu);
        }
        this.zzRu++;
        if (this.zzRu == 0) {
            this.zzRQ = this.zzRt.get(0);
            if (!(this.zzRQ instanceof zzc)) {
                throw new IllegalStateException("DataBuffer reference of type " + this.zzRQ.getClass() + " is not movable");
            }
        } else {
            ((zzc) this.zzRQ).zzaB(this.zzRu);
        }
        return this.zzRQ;
    }
}
