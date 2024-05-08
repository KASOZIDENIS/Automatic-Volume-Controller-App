package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzv;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T> implements Iterator<T> {
    protected final DataBuffer<T> zzRt;
    protected int zzRu = -1;

    public zzb(DataBuffer<T> dataBuffer) {
        this.zzRt = (DataBuffer) zzv.zzr(dataBuffer);
    }

    public boolean hasNext() {
        return this.zzRu < this.zzRt.getCount() + -1;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzRu);
        }
        DataBuffer<T> dataBuffer = this.zzRt;
        int i = this.zzRu + 1;
        this.zzRu = i;
        return dataBuffer.get(i);
    }

    public void remove() {
        throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
    }
}
