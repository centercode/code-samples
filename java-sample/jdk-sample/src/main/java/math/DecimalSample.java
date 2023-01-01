package math;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DecimalSample {

    public static void main(String[] args) {
        DecimalSample sample = new DecimalSample();
        sample.round();
    }

    void round() {
        MathContext mc = new MathContext(19, RoundingMode.HALF_UP);
        BigDecimal decimal = new BigDecimal("12345678901234567890.123456", mc);
        BigDecimal bigDecimal = decimal.setScale(3, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal);
    }
}
