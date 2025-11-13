package com.app.main;

import java.time.*;

public class WeekendDates {
    public static void main(String[] args) {
        int year = Year.now().getValue();  // current year
        Month month = Month.NOVEMBER;

        // Get number of days in November
        int daysInMonth = month.length(Year.isLeap(year));

        System.out.println("Saturdays and Sundays in " + month + " " + year + ":");

        for (int day = 1; day <= daysInMonth; day++) {
            LocalDate date = LocalDate.of(year, month, day);
            DayOfWeek dayOfWeek = date.getDayOfWeek();

            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                System.out.println(date + " → " + dayOfWeek);
            }
        }
    }
}
