package io.github.centercode;

import com.nlf.calendar.Holiday;
import com.nlf.calendar.Lunar;
import com.nlf.calendar.LunarMonth;
import com.nlf.calendar.LunarYear;
import com.nlf.calendar.Solar;
import com.nlf.calendar.util.HolidayUtil;

import java.util.ArrayList;
import java.util.List;

public class HolidayBirth {
    public static void main(String[] args) {
//        printLeapMonths(1990, 2022);
        Solar solar = Solar.fromYmd(1990, 7, 21);
        //= Solar.fromYmd(2017, 8, 21);
        printSolarRatio(solar);
        printLunarRatio(solar);
    }

    static void printSolarRatio(Solar solar) {
        List<Solar> allSolars = listAllSolars(solar);
        List<String> solarHolidays = listSolarHolidays(allSolars);
        int ratio = solarHolidays.size() * 100 / allSolars.size();
        System.out.println("Solar Ratio=" + ratio + "%, Total=" + allSolars.size() + ", Holidays:");
        solarHolidays.forEach(System.out::println);
    }

    static void printLunarRatio(Solar solar) {
        List<Lunar> allLunars = listAllLunars(solar.getLunar());
        List<String> lunarHolidays = listLunarHolidays(allLunars);
        int ratio = lunarHolidays.size() * 100 / allLunars.size();
        System.out.println("Lunar Ratio=" + ratio + "%, Total=" + allLunars.size() + ", Holidays:");
        lunarHolidays.forEach(System.out::println);
    }

    /**
     * print all leap months between startYear(include) and endYear(include).
     */
    static void printLeapMonths(int startYear, int endYear) {
        ArrayList<LunarMonth> lunarMonths = new ArrayList<>();
        for (int y = startYear; y <= endYear; y++) {
            LunarYear lunarYear = LunarYear.fromYear(y);
            for (LunarMonth month : lunarYear.getMonths()) {
                if (month.isLeap()) {
                    lunarMonths.add(month);
                }
            }
        }
        System.out.println("Leap months between " + startYear + " and " + endYear + ":");
        lunarMonths.forEach(System.out::println);
    }

    /**
     * list all same month and day solar dates until now.
     */
    static List<Solar> listAllSolars(Solar birthDay) {
        Solar now = new Solar();
        int currentYear = now.getYear();
        int currentMonth = now.getMonth();
        int currentDay = now.getDay();
        ArrayList<Solar> list = new ArrayList<>();
        for (int y = birthDay.getYear(); y <= currentYear; y++) {
            if (y == currentYear
                    && (birthDay.getMonth() > currentMonth
                    || birthDay.getMonth() == currentMonth && birthDay.getDay() > currentDay)) {
                break;
            }
            Solar dt = Solar.fromYmd(y, birthDay.getMonth(), birthDay.getDay());
            list.add(dt);
        }
        return list;
    }

    /**
     * list all same month and day lunar dates until now.
     * if the year didn't have corresponding leap month, return the corresponding non-leap month.
     * if the month didn't have corresponding day (usually the 30th), return the last day of month.
     */
    static List<Lunar> listAllLunars(Lunar birthDay) {
        Lunar now = new Lunar();
        int currentYear = now.getYear();
        int currentMonth = now.getMonth();
        int currentDay = now.getDay();
        List<Lunar> list = new ArrayList<>();
        for (int y = birthDay.getYear(); y <= currentYear; y++) {
            LunarYear lunarYear = LunarYear.fromYear(y);
            LunarMonth lunarMonth = lunarYear.getMonth(birthDay.getMonth());
            if (lunarMonth == null) {
                lunarMonth = lunarYear.getMonth(-birthDay.getMonth());
            }
            int lunarDay = Math.min(birthDay.getDay(), lunarMonth.getDayCount());
            if (y == currentYear
                    && (lunarMonth.getMonth() > currentMonth
                    || lunarMonth.getMonth() == currentMonth && lunarDay > currentDay)) {
                break;
            }
            Lunar dt = Lunar.fromYmd(lunarYear.getYear(), lunarMonth.getMonth(), lunarDay);
            list.add(dt);
        }
        return list;
    }

    /**
     * filter the holidays of input solar days.
     */
    static List<String> listSolarHolidays(List<Solar> allSolars) {
        List<String> result = new ArrayList<>();
        for (Solar dt : allSolars) {
            String flag = getHolidayBirthFlag(dt);
            if (flag != null) {
                result.add(dt.toString() + ":" + flag);
            }
        }
        return result;
    }

    /**
     * filter the holidays of input lunar days.
     */
    static List<String> listLunarHolidays(List<Lunar> allLunars) {
        List<String> result = new ArrayList<>();
        for (Lunar dt : allLunars) {
            Solar solar = dt.getSolar();
            String flag = getHolidayBirthFlag(solar);
            if (flag != null) {
                result.add(dt.toString() + "(" + solar.toString() + ") : " + flag);
            }
        }
        return result;
    }

    /**
     * whether a solar day is a holiday(no need to work).
     *
     * @param dt the input solar date
     * @return holiday name if is a holiday, null otherwise.
     */
    static String getHolidayBirthFlag(Solar dt) {
        Holiday holiday = HolidayUtil.getHoliday(dt.getYear(), dt.getMonth(), dt.getDay());
        if (holiday == null) {
            int dayOfWeek = dt.getWeek();
            if (dayOfWeek == 0) {
                return "周日";
            } else if (dayOfWeek == 6) {
                return "周六";
            } else {
                return null;
            }
        }
        if (!holiday.isWork()) {
            return holiday.getName();
        }
        return null;
    }
}
