package lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataUnitTest {

    @Test
    public void testSize() {
        assertEquals(1L, DataUnit.B.size);
        assertEquals(1L << 10, DataUnit.KB.size);
        assertEquals(1L << 20, DataUnit.MB.size);
        assertEquals(1L << 30, DataUnit.GB.size);
        assertEquals(1L << 40, DataUnit.TB.size);
        assertEquals(1L << 50, DataUnit.EB.size);
        assertEquals(1L << 60, DataUnit.ZB.size);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFromNegative() {
        assertEquals(-1L, DataUnit.KB.from(-1L, DataUnit.KB));
    }

    @Test
    public void testFromExactDivision() {
        for (DataUnit unit : DataUnit.values()) {
            assertEquals(0L, unit.from(0L, unit));
            assertEquals(1L, unit.from(1L, unit));
            assertEquals(Long.MAX_VALUE, unit.from(Long.MAX_VALUE, unit));
        }

        assertEquals(0, DataUnit.KB.from(1L, DataUnit.B));
        assertEquals(1L, DataUnit.KB.from(1024L, DataUnit.B));
        assertEquals(1024L, DataUnit.B.from(1L, DataUnit.KB));

        assertEquals(1L, DataUnit.MB.from(1024L, DataUnit.KB));
        assertEquals(1024L, DataUnit.KB.from(1L, DataUnit.MB));

        assertEquals(1L << 60, DataUnit.B.from(1L, DataUnit.ZB));

        // overflow
        assertEquals(Long.MAX_VALUE, DataUnit.B.from(1L << 20, DataUnit.ZB));
    }

    @Test
    public void testToString() {
        assertEquals("B", DataUnit.B.toString(true));
        assertEquals("B", DataUnit.B.toString(false));
        assertEquals("K", DataUnit.KB.toString(true));
        assertEquals("KB", DataUnit.KB.toString(false));
    }

}