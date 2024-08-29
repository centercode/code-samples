package lambda.checked;

import lambda.Function;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * a throwable type of {@link java.util.function.Function}.
 */
@FunctionalInterface
public interface ThrowableFunction<T, R, E extends Throwable> {

    R apply(T t) throws E;

    default Function<T, R> sneaky() {
        return t -> {
            try {
                return apply(t);
            } catch (Throwable e) {
                Sneaky.sneakyThrow(e);
                // will not go here
                return null;
            }
        };
    }

    default Function<T, Map.Entry<Boolean, R>> handle(BiConsumer<T, Throwable> handler) {
        return t -> {
            try {
                R r = apply(t);
                return new AbstractMap.SimpleImmutableEntry<>(Boolean.TRUE, r);
            } catch (Throwable e) {
                handler.accept(t, e);
                return new AbstractMap.SimpleImmutableEntry<>(Boolean.FALSE, null);
            }
        };
    }
}
