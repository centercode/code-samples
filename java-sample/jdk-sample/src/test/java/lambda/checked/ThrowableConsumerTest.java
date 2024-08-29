package lambda.checked;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.function.Function;

public class ThrowableConsumerTest {

    ThrowableConsumer<Integer, OperationNotSupportedException> printer = (Integer t) -> {
        if (t == 0) {
            throw new OperationNotSupportedException();
        }
        System.out.println(t);
    };

    @Test(expected = OperationNotSupportedException.class)
    public void acceptWithThrow() throws OperationNotSupportedException {
        printer.accept(0);
    }

    @Test
    public void acceptWithoutThrow() throws OperationNotSupportedException {
        printer.accept(1);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void sneakyWithThrow() {
        printer.sneaky().accept(0);
    }

    @Test
    public void sneakyWithoutThrow() {
        printer.sneaky().accept(1);
    }

    @Test
    public void handle() {
        Function<Integer, Boolean> handleable = printer.handle((t, e) -> {
            System.err.println("Encounter exception for " + t);
        });
        Assert.assertTrue(handleable.apply(1));
        Assert.assertFalse(handleable.apply(0));
    }
}