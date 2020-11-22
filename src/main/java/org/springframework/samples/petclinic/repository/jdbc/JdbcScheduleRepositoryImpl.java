package org.springframework.samples.petclinic.repository.jdbc;

import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.ScheduleRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcScheduleRepositoryImpl implements ScheduleRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcScheduleRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Schedule> findAll() {
        List<Schedule> schedules = new ArrayList<>();
        schedules.addAll(this.jdbcTemplate.query(
            "SELECT vet_id, day_available FROM vet_schedule ORDER BY vet_id",
            BeanPropertyRowMapper.newInstance(Schedule.class)));

        return schedules;
    }

    //ERROR IN SQL
    @Override
    public Schedule findScheduleById(int id) {
        Schedule schedule;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("vet_id", id);
            schedule = this.namedParameterJdbcTemplate.queryForObject(
                "SELECT vet_id, day_available FROM vet_schedule WHERE vet_id= :vet_id",
                params,
                BeanPropertyRowMapper.newInstance(Schedule.class)
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Schedule.class, id);
        }

        return schedule;
    }


}
