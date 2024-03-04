package io.github.centercode.doris.udf;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * UDAF sample: my_sum(INT).
 */
public class SumUDAF {

    /**
     * 用于保存中间结果的状态类。类名固定为 'State'
     */
    public static class State {
        protected int sum = 0;
    }

    /**
     * Required
     */
    public State create() {
        return new State();
    }

    /**
     * Required
     */
    public void destroy(State state) {
    }

    /**
     * Required
     * if you want this udaf function can work with window function.
     * Must impl this, it will be reset to init state after calculate every window frame
     */
    public void reset(State state) {
        state.sum = 0;
    }

    /**
     * Required
     */
    public void add(State state, Integer val) {
        if (val != null) {
            state.sum += val;
        }
    }

    /**
     * Required
     *
     * @param state 中间结果状态
     * @param out
     */
    public void serialize(State state, DataOutputStream out) {
        try {
            out.writeInt(state.sum);
        } catch (Exception e) {
            /* Do not throw exceptions */
            System.out.println(e.getMessage());
        }
    }

    /**
     * Required
     * deserialize get data from buffer before you put
     */
    public void deserialize(State state, DataInputStream in) {
        try {
            state.sum = in.readInt();
        } catch (Exception e) {
            /* Do not throw exceptions */
            System.out.println(e.getMessage());
        }
    }

    /**
     * Required
     */
    public void merge(State state, State rhs) {
        state.sum += rhs.sum;
    }

    /**
     * Required
     */
    public Integer getValue(State state) {
        return state.sum;
    }
}
