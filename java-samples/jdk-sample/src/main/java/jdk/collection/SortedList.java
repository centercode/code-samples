package jdk.collection;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 插入即排序list,类似TreeMap
 * <a href="https://stackoverflow.com/questions/13236203/sorted-list-in-java-like-treemap-or-sortedset">Sorted List in java like TreeMap or SortedSet</a>
 * @param <E>
 */
public class SortedList<E extends Comparable<E>> extends ArrayList<E> {

    @Override
    public boolean add(E e) {
        int index = Collections.binarySearch(this, e);
        super.add(index < 0 ? ~index : index, e);
        return true;
    }
}