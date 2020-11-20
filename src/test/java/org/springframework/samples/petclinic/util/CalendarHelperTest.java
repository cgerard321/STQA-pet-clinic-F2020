package org.springframework.samples.petclinic.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.samples.petclinic.repository.jdbc.JdbcEventRepository;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalendarHelperTest {
    // Louis Choiniere

    @Mock
    JdbcEventRepository jdbcEventRepository;

    @InjectMocks
    CalendarHelper calendarHelper;

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
    void getEvents_NotNull() {
        // Arrange
        CalendarHelper ch = CalendarHelper.getCalendar(10, 2020);

        // Act
        HashMap events = ch.getEvents();

        // Assert
        assertNotNull(events);
    }

    @Test
    void getEvents_mock() {
        // Arrange
        HashMap<Integer, String> mockData = new HashMap<>();
        mockData.put(10, "Event on the 10th");
        mockData.put(15, "Event on the 15");
        when(jdbcEventRepository.getEvents(10)).thenReturn(mockData);

        // Act
        HashMap events = calendarHelper.getEvents();

        // Assert
        assertEquals("Event on the 10th", events.get(10));
        assertEquals("Event on the 15", events.get(15));
    }
}
