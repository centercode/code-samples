package time;

/**
 * https://www.zhihu.com/question/270085921/answer/352173443
 * jodd.datetime.JDateTime.getJulianDayNumber();
 */
public class JulianDaySample {
    public static void main(String[] args) {
        JulianDaySample sample = new JulianDaySample();
        int d1 = sample.getJulianDayNumber(2000, 1, 1);
        int d2 = sample.getJulianDayNumber(2018, 3, 27);
        System.out.println("days=" + (d2 - d1));
    }

    int getJulianDayNumber(int year, int month, int day) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        return day + (153 * m + 2) / 5 + y * 365 + y / 4 - y / 100 + y / 400 - 32045;
    }

}
