package io.github.centercode;

import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Reference: https://projectlombok.org/features
 */
public class LombokSample {

    public void val0() {
        val str = "abc";
        val b = true;
        val i = 123;
        val l = 123L;
        val f = 3.14F;
        val d = 3.14D;
        System.out.println("b=" + b);
        System.out.println("i=" + i);
        System.out.println("l=" + l);
        System.out.println("f=" + f);
        System.out.println("d=" + d);
        System.out.println("str=" + str);
    }

    public String val1() {
        val example = new ArrayList<String>();
        example.add("Hello, World!");
        val foo = example.get(0);
        return foo.toLowerCase();
    }

    public void val2() {
        val map = new HashMap<Integer, String>();
        map.put(0, "zero");
        map.put(5, "five");
        for (val entry : map.entrySet()) {
            System.out.printf("%d: %s\n", entry.getKey(), entry.getValue());
        }
    }
}
