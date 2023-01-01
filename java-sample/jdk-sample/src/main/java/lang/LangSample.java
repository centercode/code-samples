package lang;

public class LangSample {
    public static void main(String[] args) {
        LangSample sample = new LangSample();
        sample.equal();
    }

    void equal() {
        Integer a = 12;
        Integer b = 12;
        int c = 12;
        System.out.println(a == b); // true
        System.out.println(b == c); // true

        Double d1 = 1.0d;
        Double d2 = 1.0d;
        double d3 = 1.0d;
        double d4 = 1.0d;
        System.out.println(d1 == d2); // false
        System.out.println(d3 == d4); // true
    }
}
