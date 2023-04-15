package lambda;

import org.junit.Test;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertFalse;

public class ThrowableFunctionsTest {

    @Test
    public void testSilentConsumer() {
        ThrowableFunctions.silentConsumer(integer -> {
            throw new IOException();
        }, 1);
    }

    @Test
    public void testOptionalSupplier() {
        Optional<String> foo = ThrowableFunctions.optionalSupplier(() -> {
            throw new IOException();
        });
        assertFalse(foo.isPresent());
    }

    @Test(expected = IOException.class)
    public void testSneakyThrowSupplier() {
        ThrowableFunctions.sneakyThrowSupplier(() -> {
            throw new IOException();
        });
    }
}