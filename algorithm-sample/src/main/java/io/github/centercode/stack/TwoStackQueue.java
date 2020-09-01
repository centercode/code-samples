package io.github.centercode.stack;

class TwoStackQueue {

    int[] tail = new int[50005];

    int[] head = new int[50005];

    int tailTop = -1;
    int headTop = -1;

    public static void main(String[] args) {
        TwoStackQueue queue = new TwoStackQueue();
        queue.appendTail(1);
        queue.appendTail(2);
        queue.appendTail(3);
        System.out.println(queue.deleteHead());
        queue.appendTail(4);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    public TwoStackQueue() {
    }

    public void appendTail(int value) {
        tail[++tailTop] = value;
    }

    public int deleteHead() {
        if (0 <= headTop) {
            return head[headTop--];
        }
        transfer();
        if (headTop < 0) {
            return -1;
        } else {
            return head[headTop--];
        }
    }

    private void transfer() {
        while (0 <= tailTop) {
            head[++headTop] = tail[tailTop--];
        }
    }
}