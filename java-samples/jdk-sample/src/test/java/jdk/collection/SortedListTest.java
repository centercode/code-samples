package jdk.collection;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortedListTest {

    @Test
    public void add() {
        SortedList<String> list = new SortedList<>();
        list.add("z");
        list.add("aaaa");
        list.add("mmm");
        list.add("oo");
        list.add("a");
        System.out.println(list);
    }
}