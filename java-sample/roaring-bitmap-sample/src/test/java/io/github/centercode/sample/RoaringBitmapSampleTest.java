package io.github.centercode.sample;

import org.junit.Assert;
import org.junit.Test;
import org.roaringbitmap.RoaringBitmap;

import java.util.Random;

public class RoaringBitmapSampleTest {

    @Test
    public void basic() {
        RoaringBitmap bitmap1 = new RoaringBitmap();
        RoaringBitmap bitmap2 = new RoaringBitmap();
        RoaringBitmap bitmap3 = new RoaringBitmap();
        bitmap1.add(1);
        bitmap2.add(2);
        bitmap3.add(3);

        Assert.assertEquals(1, bitmap1.getCardinality());
        bitmap1.and(bitmap2);
        Assert.assertEquals(0, bitmap1.getCardinality());
        bitmap2.or(bitmap3);
        Assert.assertEquals(2, bitmap2.getCardinality());
    }

    @Test
    public void runOptimize() {
    }

    @Test
    public void serializedSizeInBytes() {
    }

}