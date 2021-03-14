package lang;

import java.util.Random;

/**
 * "At run time, the result of the instanceof operator is true
 * if the value of the RelationalExpression is not null
 * and the reference could be cast to the ReferenceType
 * without raising a ClassCastException.
 * Otherwise the result is false."
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jls/se11/html/jls-15.html#jls-15.20.2">
 * Type comparison operator instanceof</a>
 */
public class InstanceOfSample {

    public static void main(final String[] args) {
        for (int i = 0; i < 10; i++) {
            isInstance();
        }
    }

    private static void isInstance() {
        Number val = getNumber();
        // Integer.class.isInstance(val)
        if (val instanceof Integer) {
            System.out.println(val + " is instanceof Integer.");
        } else {
            System.out.println(val + " is NOT instanceof Integer!");
        }
    }

    private static Number getNumber() {
        Random random = new Random();
        if (random.nextBoolean()) {
            return Integer.MAX_VALUE;
        } else if (random.nextBoolean()) {
            return Long.MAX_VALUE;
        } else {
            return null;
        }
    }

}
