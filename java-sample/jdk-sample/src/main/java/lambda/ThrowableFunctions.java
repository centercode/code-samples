package lambda;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;


public class ThrowableFunctions {

    private static final Logger logger = LoggerFactory.getLogger(ThrowableFunctions.class);

    /**
     * call ThrowableConsumer without throwing, return true if no exception.
     */
    public static <T, E extends Exception> boolean silentConsumer(ThrowableConsumer<T, E> consumer, T t) {
        try {
            consumer.accept(t);
            return true;
        } catch (Throwable e) {
            logger.error("error when execute {}.", ThrowableConsumer.class.getSimpleName(), e);
            return false;
        }
    }

    /**
     * call ThrowableSupplier without throwing, return valid Optional if no exception.
     */
    public static <R, E extends Exception> Optional<R> optionalSupplier(ThrowableSupplier<R, E> supplier) {
        try {
            return Optional.ofNullable(supplier.get());
        } catch (Throwable e) {
            logger.error("error when execute {}.", ThrowableSupplier.class.getSimpleName(), e);
            return Optional.empty();
        }
    }

    /**
     * call ThrowableSupplier with sneaky throwing.
     */
    public static <R, E extends Exception> R sneakyThrowSupplier(ThrowableSupplier<R, E> supplier) {
        try {
            return supplier.get();
        } catch (Throwable e) {
            sneakyThrow(e);
            // will not go here
            return null;
        }
    }

    private static <T extends Throwable> void sneakyThrow(Throwable t) throws T {
        throw (T) t;
    }

    @FunctionalInterface
    public interface ThrowableConsumer<T, E extends Exception> {
        void accept(T t) throws E;
    }

    @FunctionalInterface
    public interface ThrowableSupplier<R, E extends Exception> {
        R get() throws E;
    }
}