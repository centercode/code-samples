package lang;

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
        isInstance(getNumber(1));
        isInstance(getNumber(2));
        isInstance(getNumber(3));
        System.out.println("null instanceof Object: " + (null instanceof Object));
    }

    private static void isInstance(Number val) {
        // Integer.class.isInstance(val)
        if (val instanceof Integer) {
            System.out.println(val + " is instanceof Integer");
        } else {
            System.out.println(val + " is NOT instanceof Integer");
        }
    }

    private static Number getNumber(int type) {
        switch (type) {
            case 1:
                return Integer.MAX_VALUE;
            case 2:
                return Long.MAX_VALUE;
            default:
                return null;
        }
    }

}
