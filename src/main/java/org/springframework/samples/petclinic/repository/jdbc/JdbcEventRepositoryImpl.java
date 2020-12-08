package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.repository.EventRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class JdbcEventRepositoryImpl implements EventRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcEventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ArrayList<Event> getEvents(int year, int month) {
        return null;
    }
}
