package io.github.centercode.algorithm.cache;

import java.util.Arrays;

public class ArrayLRUCache implements LRUCache {

    private Entry[] queue;

    private int tail;

    public ArrayLRUCache(int capacity) {
        this.queue = new Entry[capacity];
        this.tail = -1;
    }

    public int get(int key) {
        int idx = seek(key);
        if (idx == -1) {
            return -1;
        } else {
            ahead(idx);
            return queue[0].value;
        }
    }

    public void put(int key, int value) {
        int idx = seek(key);
        if (-1 < idx) {
            ahead(idx);
            queue[0].value = value;
        } else {
            if (tail < queue.length - 1) {
                System.arraycopy(queue, 0, queue, 1, ++tail);
            } else {
                System.arraycopy(queue, 0, queue, 1, queue.length - 1);
            }
            queue[0] = new Entry(key, value);
        }
    }

    private void ahead(int idx) {
        Entry tmp = queue[idx];
        System.arraycopy(queue, 0, queue, 1, idx);
        queue[0] = tmp;
    }

    private int seek(int key) {
        for (int i = 0; i <= tail; i++) {
            if (queue[i].key == key) {
                return i;
            }
        }
        return -1;
    }

    static class Entry {
        private int key;
        private int value;

        private Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(key);
        }
    }

    @Override
    public String toString() {
        return "{" + Arrays.toString(queue) + '}';
    }
}