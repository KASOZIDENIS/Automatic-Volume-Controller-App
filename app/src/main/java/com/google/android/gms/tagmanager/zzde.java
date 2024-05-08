package com.google.android.gms.tagmanager;

class zzde extends Number implements Comparable<zzde> {
    private double zzaGc;
    private long zzaGd;
    private boolean zzaGe = false;

    private zzde(double d) {
        this.zzaGc = d;
    }

    private zzde(long j) {
        this.zzaGd = j;
    }

    public static zzde zzS(long j) {
        return new zzde(j);
    }

    public static zzde zza(Double d) {
        return new zzde(d.doubleValue());
    }

    public static zzde zzea(String str) throws NumberFormatException {
        try {
            return new zzde(Long.parseLong(str));
        } catch (NumberFormatException e) {
            try {
                return new zzde(Double.parseDouble(str));
            } catch (NumberFormatException e2) {
                throw new NumberFormatException(str + " is not a valid TypedNumber");
            }
        }
    }

    public byte byteValue() {
        return (byte) ((int) longValue());
    }

    public double doubleValue() {
        return zzxM() ? (double) this.zzaGd : this.zzaGc;
    }

    public boolean equals(Object other) {
        return (other instanceof zzde) && compareTo((zzde) other) == 0;
    }

    public float floatValue() {
        return (float) doubleValue();
    }

    public int hashCode() {
        return new Long(longValue()).hashCode();
    }

    public int intValue() {
        return zzxO();
    }

    public long longValue() {
        return zzxN();
    }

    public short shortValue() {
        return zzxP();
    }

    public String toString() {
        return zzxM() ? Long.toString(this.zzaGd) : Double.toString(this.zzaGc);
    }

    /* renamed from: zza */
    public int compareTo(zzde zzde) {
        return (!zzxM() || !zzde.zzxM()) ? Double.compare(doubleValue(), zzde.doubleValue()) : new Long(this.zzaGd).compareTo(Long.valueOf(zzde.zzaGd));
    }

    public boolean zzxL() {
        return !zzxM();
    }

    public boolean zzxM() {
        return this.zzaGe;
    }

    public long zzxN() {
        return zzxM() ? this.zzaGd : (long) this.zzaGc;
    }

    public int zzxO() {
        return (int) longValue();
    }

    public short zzxP() {
        return (short) ((int) longValue());
    }
}
