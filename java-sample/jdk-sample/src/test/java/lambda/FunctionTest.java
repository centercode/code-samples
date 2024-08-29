package lambda;

import org.junit.Test;

public class FunctionTest {

    Function<String, Integer> transformer = s -> {
        System.out.println("produce " + s);
        return 1;
    };

    @Test
    public void thenConsume() {
        transformer.andThenConsume(i -> System.out.println("consumed " + i))
                .andThenConsume(i -> System.out.println("consumed " + i))
                .andThenConsume(i -> System.out.println("consumed " + i))
                .apply("a");
    }

    @Test
    public void thenConsumes() {
        transformer.andThenConsumes(
                i -> System.out.println("fork1 consumed " + i),
                i -> System.out.println("fork2 consumed " + i),
                i -> System.out.println("fork3 consumed " + i)
        ).apply("a");
    }
}