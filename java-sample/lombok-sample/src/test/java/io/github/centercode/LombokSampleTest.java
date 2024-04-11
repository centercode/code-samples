package io.github.centercode;

import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

class LombokSampleTest {

    @Test
    void val0() {
        val b = true;
        val c = 'x';
        val i = 123;
        val l = 123L;
        val f = 3.14F;
        val d = 3.14D;
        val str = "abc";
        val e = DayOfWeek.MONDAY;
        val psf = StandardCharsets.UTF_8;
        val list = new ArrayList<String>();
        val map = new HashMap<Integer, String>();
        System.out.println("b=" + b);
        System.out.println("c=" + c);
        System.out.println("i=" + i);
        System.out.println("l=" + l);
        System.out.println("f=" + f);
        System.out.println("d=" + d);
        System.out.println("str=" + str);
        System.out.println("e=" + e);
        System.out.println("psf=" + psf);
        System.out.println("list=" + list);
        System.out.println("map=" + map);
    }

    @Test
    void var() {
        var b = true;
        var c = 'x';
        var i = 123;
        var l = 123L;
        var f = 3.14F;
        var d = 3.14D;
        var str = "abc";
        var e = DayOfWeek.MONDAY;
        var psf = StandardCharsets.UTF_8;
        var list = new ArrayList<String>();
        var map = new HashMap<Integer, String>();
        b = false;
        c = 'X';
        i = 456;
        l = 456L;
        f = 6.28F;
        d = 6.28D;
        str = "xyz";
        e = DayOfWeek.TUESDAY;
        psf = StandardCharsets.ISO_8859_1;
        list = new ArrayList<String>();
        map = new HashMap<Integer, String>();
    }

}