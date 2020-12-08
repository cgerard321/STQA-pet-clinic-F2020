package org.springframework.samples.petclinic.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalendarHelperTests {
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
}
