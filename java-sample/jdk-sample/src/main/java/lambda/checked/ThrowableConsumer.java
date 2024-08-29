package lambda.checked;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * a throwable type of {@link java.util.function.Consumer}.
 */
@FunctionalInterface
public interface ThrowableConsumer<T, E extends Exception> {

    void accept(T t) throws E;

    default Consumer<T> sneaky() {
        return t -> {
            try {
                accept(t);
            } catch (Throwable e) {
                Sneaky.sneakyThrow(e);
            }
        };
    }

    default Function<T, Boolean> handle(BiConsumer<T, Throwable> handler) {
        return t -> {
            try {
                accept(t);
                return Boolean.TRUE;
            } catch (Throwable e) {
                handler.accept(t, e);
                return Boolean.FALSE;
            }
        };
    }
}
