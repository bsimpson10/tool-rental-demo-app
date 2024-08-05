package org.example.toolrental.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;

public class LocalDateUtil {

    private static Calendar calendar = Calendar.getInstance();

    public static boolean isOnWeekend(LocalDate date) {
        return Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY).contains(date.getDayOfWeek());
    }

    public static boolean isWeekday(LocalDate date) {
        return !isOnWeekend(date);
    }

    public static boolean isLaborDay(LocalDate date) {
        return date.equals(getLaborDayByYear(date.getYear()));
    }

    public static boolean isIndependenceDay(LocalDate date) {
        return date.equals(getIndependenceDayByYear(date.getYear()));
    }

    private static LocalDate getIndependenceDayByYear(int year) {
        var date = LocalDate.of(year, 7, 4);
        var dayOfWeek = date.getDayOfWeek();

        if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
            return date.minusDays(1);
        }

        if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            return date.plusDays(1);
        }

        return date;
    }

    private synchronized static LocalDate getLaborDayByYear(int year) {
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        calendar.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendar.set(Calendar.YEAR, year);

        return LocalDate.of(year, 9, calendar.get(Calendar.DAY_OF_MONTH));
    }
}
