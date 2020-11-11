package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.samples.petclinic.model.Reminder;
import org.springframework.samples.petclinic.model.Reminder;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.ReminderRepository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcReminderRepositoryImpl implements ReminderRepository{
    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertReminder;

    @Autowired
    public JdbcReminderRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertReminder = new SimpleJdbcInsert(dataSource)
            .withTableName("reminders")
            .usingGeneratedKeyColumns("id");
    }


//    @Override
//    public void save(Reminder reminder) {
//        if (reminder.isNew()) {
//            Number newKey = this.insertReminder.executeAndReturnKey(
//                createReminderParameterSource(reminder));
//            reminder.setId(newKey.intValue());
//        } else {
//            throw new UnsupportedOperationException("Reminder update not supported");
//        }
//    }


//    /**
//     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Reminder} instance.
//     * @param reminder
//     */
//    private MapSqlParameterSource createReminderParameterSource(Reminder reminder) {
//        return new MapSqlParameterSource()
//            .addValue("id", reminder.getId())
//            .addValue("event_date", reminder.getEventDate())
//            .addValue("event_description", reminder.getEventDescription())
//            .addValue("vet_id", reminder.getVet().getId());
//    }

    @Override
    public List<Reminder> findByVetId(Integer vetId) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", vetId);
        Vet vet = this.jdbcTemplate.queryForObject(
            "SELECT id, first_name, last_name FROM vets WHERE id=:id",
            params,
            new JdbcVetRowMapper());

        List<Reminder> reminders = this.jdbcTemplate.query(
            "SELECT id as event_id, event_date, event_description FROM reminders WHERE vet_id=:id",
            params, new JdbcReminderRowMapper());

        for (Reminder reminder: reminders) {
            reminder.setVet(vet);
        }

        System.out.println(vet);
        return reminders;
    }
}
