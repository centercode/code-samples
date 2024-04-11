package io.github.centercode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LombokSampleTest {
    LombokSample sample = new LombokSample();

    @Test
    void val0() {
        sample.val0();
    }

    @Test
    void val1() {
        Assertions.assertEquals("hello, world!", sample.val1());
    }

    @Test
    void val2() {
        sample.val2();
    }
}