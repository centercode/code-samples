package io.github.centercode.starrocks.udf;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * UDAF sample: my_sum(INT).
 */
public class SumUDAF {

    /**
     * 用于保存中间结果的状态类。类名固定为 'State'，需要实现Serializable接口
     */
    public static class State implements Serializable {

        protected int counter = 0;

        /**
         * 中间结果序列化后的长度，单位为 Byte。
         */
        public int serializeLength() {
            return 4;
        }

        public State setCounter(int counter) {
            this.counter = counter;
            return this;
        }

        public int getCounter() {
            return counter;
        }
    }

    public State create() {
        return new State();
    }

    public void destroy(State state) {
    }

    public void update(State state, Integer val) {
        if (val != null) {
            state.counter += val;
        }
    }

    /**
     * @param state 中间结果状态
     * @param buffer 缓冲区对象，用于保存中间结果
     */
    public void serialize(State state, ByteBuffer buffer) {
        buffer.putInt(state.counter);
    }

    public void merge(State state, ByteBuffer buffer) {
        int val = buffer.getInt();
        state.counter += val;
    }

    public Integer finalize(State state) {
        return state.counter;
    }
}
