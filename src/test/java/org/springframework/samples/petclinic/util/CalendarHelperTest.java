package org.springframework.samples.petclinic.util;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class CalendarHelperTest {
    // Louis Choiniere


    @Test
    void getCalendar() {
        // Arrange
        CalendarHelper ch = CalendarHelper.getCalendar(10, 2020);

        // Act
        int month = ch.getMonth();
        int year = ch.getYear();
        int numberOfWeeks = ch.getNumberOfWeeks();

        // Assert
        assertEquals(month, 10);
        assertEquals(year, 2020);
        assertEquals(numberOfWeeks, 5);
    }

    @Test
    void getEvents() {
        // Arrange
        CalendarHelper ch = CalendarHelper.getCalendar(10, 2020);

        // Act
        HashMap events = ch.getEvents();

        // Assert
        assertNotNull(events);
    }
}
