package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.samples.petclinic.model.Reminder;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * {@link org.springframework.jdbc.core.RowMapper} implementation mapping data from a {@link ResultSet} to the corresponding properties
 * of the {@link Reminder} class.
 */

class JdbcReminderRowMapper implements RowMapper<Reminder> {

    @Override
    public Reminder mapRow(ResultSet rs, int row) throws SQLException {
        Reminder reminder = new Reminder();
        reminder.setId(rs.getInt("event_id"));
        reminder.setEventDate(rs.getObject("event_date", LocalDate.class));
        reminder.setEventDescription(rs.getString("event_description"));
        return reminder;
    }
}
