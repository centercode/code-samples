package lang;

import org.junit.Assert;
import org.junit.Test;

public class LangSampleTest {

    @Test
    public void testNumericEqual() {
        Integer a = 12;
        Integer b = 12;
        int c = 12;
        Assert.assertTrue(a == b); // true
        Assert.assertTrue(b == c); // true

        Double d1 = 1.0d;
        Double d2 = 1.0d;
        double d3 = 1.0d;
        double d4 = 1.0d;
        Assert.assertTrue(d1 != d2); // false
        Assert.assertTrue(d3 == d4); // true
    }

    @Test
    public void testMinusOperator() {
        assertAllEquals(-0xFFFFFFFF, 1);
        assertAllEquals(-0x80000000, 0x80000000);
    }

    @Test
    public void testShiftOperator() {
        // 左移
        assertAllEquals(0 << 1, 0);
        assertAllEquals(1 << 1, 2);
        assertAllEquals(0B1101 << 1, 0B11010);
        // 1073741824 << 1 => -2147483648
        assertAllEquals(
                0B0100_0000_0000_0000_0000_0000_0000_0000 << 1,
                0B1000_0000_0000_0000_0000_0000_0000_0000,
                -2147483648,
                Integer.MIN_VALUE
        );
        assertAllEquals(0B0100_0000_0000_0000_0000_0000_0000_0000 << 2, 0);
        // 2147483647 << 1 => -4294967294 => -2
        assertAllEquals(Integer.MAX_VALUE << 1, 0xFFFFFFFE, -2);
        assertAllEquals(Integer.MAX_VALUE << 2, 0xFFFFFFFC, -4);
        assertAllEquals(Integer.MAX_VALUE << 3, 0xFFFFFFF8, -8);

        // 带符号右移
        assertAllEquals(0 >> 1, 0);
        assertAllEquals(1 >> 1, 0);
        assertAllEquals(0B1101 >> 1, 0B110);
        assertAllEquals(Integer.MIN_VALUE >> 1, -0x40000000);
        assertAllEquals(0xFFFFFFFF >> 1, 0xFFFFFFFF, -1);
        assertAllEquals(0xFFFFFFFF >> 2, 0xFFFFFFFF, -1);
        assertAllEquals(0xFFFFFFFF >> 3, 0xFFFFFFFF, -1);

        // 无符号右移
        assertAllEquals(0 >>> 1, 0);
        assertAllEquals(1 >>> 1, 0);
        assertAllEquals(0B1101 >>> 1, 0B110);
        assertAllEquals(Integer.MIN_VALUE >>> 1, 0x40000000);
        assertAllEquals(0xFFFFFFFF >>> 1, 0x7FFFFFFF, 2147483647, Integer.MAX_VALUE);
        assertAllEquals(0xFFFFFFFF >>> 2, 0x3FFFFFFF, 1073741823);
        assertAllEquals(0xFFFFFFFF >>> 3, 0x1FFFFFFF, 536870911);
    }

    boolean assertAllEquals(int... nums) {
        if (nums.length == 0) {
            return true;
        }
        for (int i = 1; i < nums.length; i++) {
            Assert.assertTrue(nums[0] + " != " + nums[i], nums[0] == nums[i]);
        }
        return true;
    }

    @Test
    public void testHexIntRange() {
        //// negative
        assertAllEquals(-0xFFFF_FFFF, 0x0000_0001, 1);
        assertAllEquals(-0x8000_0001, 0x7FFF_FFFF, Integer.MAX_VALUE);
        // 符号突变
        assertAllEquals(-0x8000_0000, 0x8000_0000, Integer.MIN_VALUE);
        assertAllEquals(-0x7FFF_FFFF, 0x8000_0001, Integer.MIN_VALUE + 1);
        assertAllEquals(-0x0000_0001, 0xFFFF_FFFF);
        assertAllEquals(-0x0000_0000, 0);
        //// positive
        assertAllEquals(0x0000_0000, 0);
        assertAllEquals(0x0000_0001, 1);
        assertAllEquals(0x7FFF_FFFF, 2147483647, Integer.MAX_VALUE);
        // 符号突变
        assertAllEquals(0x8000_0000, -2147483648, Integer.MIN_VALUE);
        assertAllEquals(0x8000_0001, -2147483647, -Integer.MAX_VALUE);
        assertAllEquals(0xFFFF_FFFF, -1);
    }

    @Test
    public void testParseHexInt() {
        // Integer.parseInt("-80000001", 16)： NumberFormatException
        assertAllEquals(Integer.parseInt("-80000000", 16), 0x8000_0000, Integer.MIN_VALUE);
        assertAllEquals(Integer.parseInt("-7FFFFFFF", 16), -0x7FFF_FFFF, 0x8000_0001);
        assertAllEquals(Integer.parseInt("-00000001", 16), -0x0000_0001, 0xFFFF_FFFF);
        assertAllEquals(Integer.parseInt("-00000000", 16), 0x0000_0000);
        assertAllEquals(Integer.parseInt("00000000", 16), 0x0000_0000);
        assertAllEquals(Integer.parseInt("00000001", 16), 0x0000_0001);
        assertAllEquals(Integer.parseInt("0000000F", 16), 0x0000_000F);
        assertAllEquals(Integer.parseInt("00000010", 16), 0x0000_0010);
        assertAllEquals(Integer.parseInt("7FFFFFFF", 16), 0x7FFF_FFFF, Integer.MAX_VALUE);
        // Integer.parseInt("80000000", 16)： NumberFormatException
    }

    @Test
    public void testToString() {
        Assert.assertEquals(Integer.toString(0x0000_0000, 10), "0");
        Assert.assertEquals(Integer.toString(0x7FFF_FFFF, 10), "2147483647");
        Assert.assertEquals(Integer.toString(0x8000_0000, 10), "-2147483648");
        Assert.assertEquals(Integer.toString(0xFFFF_FFFF, 10), "-1");
    }

    @Test
    public void testToHexString() {
        Assert.assertEquals(Integer.toHexString(0x0000_0000), "0");
        Assert.assertEquals(Integer.toHexString(0x7FFF_FFFF), "7fffffff");
        Assert.assertEquals(Integer.toHexString(0x8000_0000), "80000000");
        Assert.assertEquals(Integer.toHexString(0xFFFF_FFFF), "ffffffff");
    }

    @Test
    public void testToUnsignedString() {
        Assert.assertEquals(Integer.toUnsignedString(0x0000_0000), "0");
        Assert.assertEquals(Integer.toUnsignedString(0x7FFF_FFFF), "2147483647");
        Assert.assertEquals(Integer.toUnsignedString(0x8000_0000), "2147483648");
        Assert.assertEquals(Integer.toUnsignedString(0xFFFF_FFFF), "4294967295");
    }
}