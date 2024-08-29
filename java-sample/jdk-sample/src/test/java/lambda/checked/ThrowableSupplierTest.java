package lambda.checked;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;
import java.util.function.Supplier;

public class ThrowableSupplierTest {

    @Test
    public void getWithoutThrow() throws OperationNotSupportedException {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        generator.get();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void getWithThrow() throws OperationNotSupportedException {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        Assert.assertEquals(Integer.valueOf(1), generator.get());
        generator.get();
    }

    @Test
    public void sneakyWithoutThrow() {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        generator.sneaky().get();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void sneakyWithThrow() {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        Supplier<Integer> sneakyGenerator = generator.sneaky();
        Assert.assertEquals(Integer.valueOf(1), sneakyGenerator.get());
        sneakyGenerator.get();
    }

    @Test
    public void handleWithoutThrow() {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        Supplier<Map.Entry<Boolean, Integer>> handleable = generator.handle(e -> {
            System.err.println("Encounter exception!");
        });
        Map.Entry<Boolean, Integer> res = handleable.get();
        Assert.assertTrue(res.getKey());
        Assert.assertEquals(Integer.valueOf(1), res.getValue());
    }

    @Test
    public void handleWithThrow() {
        ThrowableSupplier<Integer, OperationNotSupportedException> generator = new ThrowableSupplier<Integer, OperationNotSupportedException>() {

            int counter = 0;

            @Override
            public Integer get() throws OperationNotSupportedException {
                int r = ++counter;
                if (r % 2 == 0) {
                    throw new OperationNotSupportedException();
                }
                return r;
            }
        };
        Supplier<Map.Entry<Boolean, Integer>> handleable = generator.handle(e -> {
            System.err.println("Encounter exception!");
        });
        handleable.get();
        Map.Entry<Boolean, Integer> res = handleable.get();
        Assert.assertFalse(res.getKey());
        Assert.assertNull(res.getValue());
    }
}