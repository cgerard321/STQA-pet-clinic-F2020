package org.springframework.samples.petclinic.util;

import org.springframework.samples.petclinic.model.Event;

import java.util.Calendar;
import java.util.HashMap;

public class CalendarHelper {
    private int month;
    private int year;
    private int days[][];
    private int numberOfWeeks;
    private HashMap events = new HashMap();
    private static HashMap months = new HashMap();

    private CalendarHelper() {
    }

    private CalendarHelper(int month, int year) {
        days = new int[6][7];
        numberOfWeeks = 0;
        this.month = month;
        this.year = year;
        buildWeeks();
    }

    public static CalendarHelper getCalendar(int month, int year) {
        String key = String.valueOf((new StringBuffer(String.valueOf(month))).append("/").append(year));
        if (months.containsKey(key)) {
            return (CalendarHelper) months.get(key);
        } else {
            CalendarHelper newCalendar = new CalendarHelper(month, year);
            months.put(key, newCalendar);
            return newCalendar;
        }
    }

    private void buildWeeks() {
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(1);
        c.set(year, month, 1);
        for (; c.get(2) == month; c.add(5, 1)) {
            int weekNumber = c.get(4) - 1;
            int dayOfWeek = calculateDay(c.get(7));
            days[weekNumber][dayOfWeek] = c.get(5);
            numberOfWeeks = weekNumber;
        }
    }

    public int calculateDay(int day) {
        if (day == 1)
            return 0;
        if (day == 2)
            return 1;
        if (day == 3)
            return 2;
        if (day == 4)
            return 3;
        if (day == 5)
            return 4;
        if (day == 6)
            return 5;
        return day != 7 ? 7 : 6;
    }

    public int[][] getDays() {
        return days;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks + 1;
    }

    public HashMap getEvents() {

        // Temporary static values
        // Because I was not able to implement the repository with sql
        HashMap<Integer, Event> events = new HashMap<>();
        events.put(02, new Event("Veteran day"));
        events.put(11, new Event("Open house", "10h00am"));
        events.put(27, new Event("Adoption day", "13h00pm"));

        return events;
    }
}
