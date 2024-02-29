package lang;

/**
 * Represent the data size unit.
 */
public enum DataUnit {
    /**
     * BYTES
     */
    B(1L),
    /**
     * KILO-BYTES
     */
    KB(B.size << 10),
    /**
     * MEGA-BYTES
     */
    MB(KB.size << 10),
    /**
     * GIGA-BYTES
     */
    GB(MB.size << 10),
    /**
     * TERA-BYTES
     */
    TB(GB.size << 10),
    /**
     * EXA-BYTES
     */
    EB(TB.size << 10),
    /**
     * ZETTA-BYTES
     */
    ZB(EB.size << 10);

    /**
     * count of bytes.
     */
    final long size;

    DataUnit(long size) {
        this.size = size;
    }

    /**
     * convert the size 's' from the source DataUnit 'u' to this.
     *
     * @param s source size
     * @param u source DataUnit
     * @return the converted size
     */
    public long from(long s, DataUnit u) {
        if (s < 0) {
            throw new IllegalArgumentException("source size '" + s + "' is negative!");
        }
        if (u.size == this.size) {
            return s;
        } else if (u.size > this.size) {
            long m = u.size / size;
            return scale(s, m, Long.MAX_VALUE / m);
        } else {
            return s / (size / u.size);
        }
    }

    /**
     * Scale d by m, checking for overflow.
     */
    static long scale(long d, long m, long over) {
        if (d > over) {
            return Long.MAX_VALUE;
        }
        if (d < -over) {
            return Long.MIN_VALUE;
        }
        return d * m;
    }

    /**
     * To human-readable String.
     *
     * @param abbr whether abbreviate or not, e.g. 'K' or 'KB', 'B' or ''.
     */
    public String toString(boolean abbr) {
        if (abbr) {
            return name().substring(0, 1);
        } else {
            return name();
        }
    }
}
