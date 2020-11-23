package org.springframework.samples.petclinic.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.RatingRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcRatingRepositoryImpl implements RatingRepository {

    private NamedParameterJdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertRating;

    @Autowired
    public JdbcRatingRepositoryImpl(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertRating = new SimpleJdbcInsert(dataSource)
            .withTableName("ratings")
            .usingGeneratedKeyColumns("id");
    }

    @Override
    public void save(Rating rating) {
        Number newKey = this.insertRating.executeAndReturnKey(
                createRatingParameterSource(rating));
            rating.setId(newKey.intValue());
    }

    private MapSqlParameterSource createRatingParameterSource(Rating rating) {
        return new MapSqlParameterSource()
            .addValue("id", rating.getId())
            .addValue("username", rating.getUsername())
            .addValue("rating", rating.getRating())
            .addValue("pet_id", rating.getPet().getId());
    }

    @Override
    public Collection<Rating> findAll() {
        List<Rating> ratings = new ArrayList<>();
        // Retrieve the list of all ratings.
        ratings.addAll(this.jdbcTemplate.query(
            "SELECT id, pet_id, username, rating FROM ratings ORDER BY id",
            BeanPropertyRowMapper.newInstance(Rating.class)));
        return ratings;
    }
}
