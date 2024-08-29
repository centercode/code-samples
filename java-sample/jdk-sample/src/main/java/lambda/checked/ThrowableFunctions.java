package lambda.checked;

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

}