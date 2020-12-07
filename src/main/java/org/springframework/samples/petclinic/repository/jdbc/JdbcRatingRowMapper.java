package org.springframework.samples.petclinic.repository.jdbc;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Rating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * {@link RowMapper} implementation mapping data from a {@link ResultSet} to the corresponding properties
 * of the {@link Rating} class.
 */
class JdbcRatingRowMapper implements RowMapper<Rating> {

    private List<JdbcPet> pets;

    public List<JdbcPet> getPets() {
        return pets;
    }

    public void setPets(List<JdbcPet> pets) {
        this.pets = pets;
    }

    public JdbcRatingRowMapper() {
        this.pets = null;
    }

    public JdbcRatingRowMapper(List<JdbcPet> pets) {
        this.pets = pets;
    }

    @Override
    public Rating mapRow(ResultSet rs, int row) throws SQLException {
        Rating rating = new Rating();
        rating.setId(rs.getInt("rating_id"));
        rating.setUsername(rs.getString("username"));
        rating.setRating(rs.getInt("rating"));

        if (pets != null) {
            int petId = rs.getInt("pet_id");
            pets.stream().filter(p -> p.getId() == petId).findFirst().ifPresent(rating::setPet);
        }

        return rating;
    }
}
