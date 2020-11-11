package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.samples.petclinic.model.Reminder;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcVetReminderExtractor extends
    OneToManyResultSetExtractor<Vet, Reminder, Integer>{
    public JdbcVetReminderExtractor() {
        super(new JdbcVetRowMapper(), new JdbcReminderRowMapper());
    }

    @Override
    protected Integer mapPrimaryKey(ResultSet rs) throws SQLException {
        return rs.getInt("vets.id");
    }

    @Override
    protected Integer mapForeignKey(ResultSet rs) throws SQLException {
        if (rs.getObject("reminders.vet_id") == null) {
            return null;
        } else {
            return rs.getInt("reminders.vet_id");
        }
    }

    @Override
    protected void addChild(Vet root, Reminder child) {
        root.addReminder(child);
    }
}
