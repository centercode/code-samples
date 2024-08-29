package lambda;

import java.util.function.Consumer;

/**
 * extend {@link java.util.function.Function} to support chained wrapper.
 */
@FunctionalInterface
public interface Function<T, R> extends java.util.function.Function<T, R> {

    default Function<T, R> andThenConsume(Consumer<R> after) {
        return t -> {
            R res = apply(t);
            after.accept(res);
            return res;
        };
    }

    default Function<T, R> andThenConsumes(Consumer<R>... afters) {
        return t -> {
            R res = apply(t);
            for (Consumer<R> after : afters) {
                after.accept(res);
            }
            return res;
        };
    }

}
