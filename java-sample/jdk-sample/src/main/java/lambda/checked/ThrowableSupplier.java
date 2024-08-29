package lambda.checked;

import java.util.AbstractMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * a throwable type of {@link java.util.function.Supplier}.
 */
@FunctionalInterface
public interface ThrowableSupplier<R, E extends Exception> {

    R get() throws E;

    default Supplier<R> sneaky() {
        return () -> {
            try {
                return get();
            } catch (Throwable e) {
                Sneaky.sneakyThrow(e);
                // will not go here
                return null;
            }
        };
    }

    default Supplier<Map.Entry<Boolean, R>> handle(Consumer<Throwable> handler) {
        return () -> {
            try {
                R r = get();
                return new AbstractMap.SimpleImmutableEntry<>(Boolean.TRUE, r);
            } catch (Throwable e) {
                handler.accept(e);
                return new AbstractMap.SimpleImmutableEntry<>(Boolean.FALSE, null);
            }
        };
    }
}
