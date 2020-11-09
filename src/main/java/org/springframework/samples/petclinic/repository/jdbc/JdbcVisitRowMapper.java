/*
 * Copyright 2002-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.repository.jdbc;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * {@link RowMapper} implementation mapping data from a {@link ResultSet} to the corresponding properties
 * of the {@link Visit} class.
 */
class JdbcVisitRowMapper implements RowMapper<Visit> {

    private List<JdbcPet> pets;

    public List<JdbcPet> getPets() {
        return pets;
    }

    public void setPets(List<JdbcPet> pets) {
        this.pets = pets;
    }

    public JdbcVisitRowMapper() {
        this.pets = null;
    }

    public JdbcVisitRowMapper(List<JdbcPet> pets) {
        this.pets = pets;
    }

    @Override
    public Visit mapRow(ResultSet rs, int row) throws SQLException {
        Visit visit = new Visit();
        visit.setId(rs.getInt("visit_id"));
        visit.setDate(rs.getObject("visit_date", LocalDate.class));
        visit.setDescription(rs.getString("description"));

        if (pets != null) {
            int petId = rs.getInt("pet_id");
            pets.stream().filter(p -> p.getId() == petId).findFirst().ifPresent(visit::setPet);
        }

        return visit;
    }
}
