package lambda.checked;

public class Sneaky {

    private Sneaky() {
        throw new InstantiationError();
    }

    public static <E extends Throwable> void sneakyThrow(Throwable t) throws E {
        throw (E) t;
    }
}
