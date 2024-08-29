package lambda.checked;

import lambda.Function;
import org.junit.Assert;
import org.junit.Test;

import javax.naming.OperationNotSupportedException;
import java.util.Map;

public class ThrowableFunctionTest {

    ThrowableFunction<String, Integer, OperationNotSupportedException> fun = in -> {
        switch (in) {
            case "a":
                return 1;
            case "b":
                return 2;
            default:
                throw new OperationNotSupportedException();
        }
    };

    @Test
    public void applyWithoutThrow() throws OperationNotSupportedException {
        Assert.assertEquals(Integer.valueOf(1), fun.apply("a"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void applyWithThrow() throws OperationNotSupportedException {
        Assert.assertEquals(Integer.valueOf(1), fun.apply("c"));
    }

    @Test
    public void sneakyWithoutThrow() {
        Assert.assertEquals(Integer.valueOf(1), fun.sneaky().apply("a"));
    }

    @Test(expected = OperationNotSupportedException.class)
    public void sneakyWithThrow() {
        Assert.assertEquals(Integer.valueOf(1), fun.sneaky().apply("c"));
    }

    @Test
    public void handle() {
        Function<String, Map.Entry<Boolean, Integer>> handleable = fun.handle((t, e) -> {
            System.err.println("Encounter exception for " + t);
        });
        Map.Entry<Boolean, Integer> res = handleable.apply("a");
        Assert.assertTrue(res.getKey());
        Assert.assertEquals(Integer.valueOf(1), res.getValue());
        res = handleable.apply("c");
        Assert.assertFalse(res.getKey());
        Assert.assertNull(res.getValue());
    }
}