package lang;

import static java.nio.charset.StandardCharsets.UTF_16BE;
import static java.nio.charset.StandardCharsets.UTF_16LE;
import static java.nio.charset.StandardCharsets.UTF_8;
import static lang.UnicodeSample.UTF_32BE;
import static lang.UnicodeSample.UTF_32LE;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UnicodeSampleTest {

    @Test
    public void testUnicodeRange() {
        assertEquals(Character.MIN_CODE_POINT, Character.MIN_VALUE);
        assertTrue(Character.isBmpCodePoint(Character.MAX_VALUE));
        assertFalse(Character.isBmpCodePoint(Character.MAX_VALUE + 1));
        assertFalse(Character.isSupplementaryCodePoint(Character.MAX_VALUE));
        assertTrue(Character.isSupplementaryCodePoint(Character.MAX_VALUE + 1));
        // min BMP code point.
        assertTrue(Character.isValidCodePoint(Character.MIN_VALUE));
        // max BMP code point.
        assertTrue(Character.isValidCodePoint(Character.MAX_VALUE));
        // max unicode code point.
        assertTrue(Character.isValidCodePoint(Character.MAX_CODE_POINT));
        // out of range.
        assertFalse(Character.isValidCodePoint(Character.MAX_CODE_POINT + 1));
        // valid but none sense range.
        assertTrue(Character.isValidCodePoint(Character.MIN_HIGH_SURROGATE) && Character.isValidCodePoint(Character.MAX_LOW_SURROGATE));
    }

    @Test
    public void testUTF32() {
        assertEquals(4, UnicodeSample.toBytes(Character.MIN_CODE_POINT, UTF_32LE).length);
        assertEquals(4, UnicodeSample.toBytes(Character.MAX_CODE_POINT, UTF_32LE).length);
    }

    @Test
    public void testUTF16() {
        // max bmp chars
        assertEquals(1, Character.toChars(Character.MAX_VALUE).length);
        assertEquals(2, UnicodeSample.toBytes(Character.MAX_VALUE, UTF_16LE).length);
        // min supplementary chars
        assertEquals(2, Character.toChars(Character.MIN_SUPPLEMENTARY_CODE_POINT).length);
        assertEquals(4, UnicodeSample.toBytes(Character.MIN_SUPPLEMENTARY_CODE_POINT, UTF_16LE).length);

        assertEquals(Character.MIN_HIGH_SURROGATE, Character.highSurrogate(Character.MIN_SUPPLEMENTARY_CODE_POINT));
        assertEquals(Character.MIN_LOW_SURROGATE, Character.lowSurrogate(Character.MIN_SUPPLEMENTARY_CODE_POINT));

        assertEquals(Character.MAX_HIGH_SURROGATE, Character.highSurrogate(Character.MAX_CODE_POINT));
        assertEquals(Character.MAX_LOW_SURROGATE, Character.lowSurrogate(Character.MAX_CODE_POINT));
    }

    @Test
    public void testUTF8() {
        // 1字节UTF-8范围
        assertEquals(1, "\u0000".getBytes(UTF_8).length);
        assertEquals(1, "\u007F".getBytes(UTF_8).length);

        // 2字节UTF-8范围
        assertEquals(2, "\u0080".getBytes(UTF_8).length);
        assertEquals(2, "\u07FF".getBytes(UTF_8).length);

        // 3字节UTF-8范围
        assertEquals(3, "\u0800".getBytes(UTF_8).length);
        assertEquals(3, "\uD7FF".getBytes(UTF_8).length);
        // 去除代理对范围
        assertNotEquals(3, UnicodeSample.toBytes(Character.MIN_HIGH_SURROGATE, UTF_8).length);
        assertNotEquals(3, UnicodeSample.toBytes(Character.MAX_HIGH_SURROGATE, UTF_8).length);
        assertNotEquals(3, UnicodeSample.toBytes(Character.MIN_LOW_SURROGATE, UTF_8).length);
        assertNotEquals(3, UnicodeSample.toBytes(Character.MAX_LOW_SURROGATE, UTF_8).length);
        assertEquals(3, "\uE000".getBytes(UTF_8).length);
        assertEquals(3, "\uFFFF".getBytes(UTF_8).length);

        // 4字节UTF-8范围
        assertEquals(4, UnicodeSample.toBytes(Character.MAX_VALUE + 1, UTF_8).length);
        assertEquals(4, UnicodeSample.toBytes(Character.MAX_CODE_POINT, UTF_8).length);
    }

    @Test
    public void testBOM() {
        assertArrayEquals(new byte[] {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF}, UnicodeSample.toBytes('\uFEFF', UTF_8));
        assertArrayEquals(new byte[] {(byte) 0xFF, (byte) 0xFE}, UnicodeSample.toBytes('\uFEFF', UTF_16LE));
        assertArrayEquals(new byte[] {(byte) 0xFE, (byte) 0xFF}, UnicodeSample.toBytes('\uFEFF', UTF_16BE));
        assertArrayEquals(new byte[] {(byte) 0xFF, (byte) 0xFE, 0x0, 0x0}, UnicodeSample.toBytes('\uFEFF', UnicodeSample.UTF_32LE));
        assertArrayEquals(new byte[] {0x0, 0x0, (byte) 0xFE, (byte) 0xFF}, UnicodeSample.toBytes('\uFEFF', UTF_32BE));
    }

}