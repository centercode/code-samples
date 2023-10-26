package io.github.centercode.algorithm.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 大顶堆, JDK implement reference: {@link java.util.PriorityQueue}
 */
public class MaxHeap {

    List<Integer> array = new ArrayList<>();

    MaxHeap() {
    }

    /**
     * 构造方法，根据输入列表建堆，O(n)
     */
    MaxHeap(List<Integer> nums) {
        // 将列表元素原封不动添加进堆
        array.addAll(nums);
        // 堆化除叶节点以外的其他所有节点
        for (int i = parent(size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * 元素入堆，O(log n)
     */
    void push(int val) {
        array.add(val);
        // 从底至顶堆化
        siftUp(size() - 1);
    }

    /**
     * 元素出堆，O(log n)
     */
    int pop() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        // 交换根节点与最右叶节点（即交换首元素与尾元素）
        swap(0, size() - 1);
        int val = array.remove(size() - 1);
        // 从顶至底堆化
        siftDown(0);
        return val;
    }

    /**
     * 访问堆顶元素
     */
    int peek() {
        return isEmpty() ? -1 : array.get(0);
    }

    int size() {
        return array.size();
    }

    /**
     * 从节点 i 开始，从底至顶堆化
     */
    void siftUp(int i) {
        while (true) {
            int p = parent(i);
            // 当“越过根节点”或“节点无须修复”时，结束堆化
            if (p < 0 || array.get(i) <= array.get(p)) {
                break;
            }
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    /**
     * 从节点 i 开始，从顶至底堆化
     */
    void siftDown(int i) {
        while (true) {
            // 判断节点 i, l, r 中值最大的节点，记为 max
            int l = left(i), r = right(i), max = i;
            if (l < size() && array.get(l) > array.get(max)) {
                max = l;
            }
            if (r < size() && array.get(r) > array.get(max)) {
                max = r;
            }
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
            if (max == i) {
                break;
            }
            swap(i, max);
            // 循环向下堆化
            i = max;
        }
    }

    /**
     * 获取左子节点索引
     */
    private static int left(int i) {
        return 2 * i + 1;
    }

    /**
     * 获取右子节点索引
     */
    private static int right(int i) {
        return 2 * i + 2;
    }

    /**
     * 获取父节点索引
     */
    private static int parent(int i) {
        return (i - 1) / 2; // 向下整除
    }

    private void swap(int a, int b) {
        int tmp = array.get(a);
        array.set(a, array.get(b));
        array.set(b, tmp);
    }

    private boolean isEmpty() {
        return array.isEmpty();
    }
}
