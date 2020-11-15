/*
 * Copyright 2002-2013 the original author or authors.
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.OwnerRepository;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Sam Brannen
 * @author Thomas Risberg
 * @author Mark Fisher
 */
@Repository
public class JdbcPetRepositoryImpl implements PetRepository {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertPet;

    private OwnerRepository ownerRepository;

    @Autowired
    public JdbcPetRepositoryImpl(DataSource dataSource, OwnerRepository ownerRepository) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        this.insertPet = new SimpleJdbcInsert(dataSource)
            .withTableName("pets")
            .usingGeneratedKeyColumns("id");

        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<PetType> findPetTypes() {
        Map<String, Object> params = new HashMap<>();
        return this.namedParameterJdbcTemplate.query(
            "SELECT id, name FROM types ORDER BY name",
            params,
            BeanPropertyRowMapper.newInstance(PetType.class));
    }

    @Override
    public Pet findById(int id) {
        Integer ownerId;
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("id", id);
            ownerId = this.namedParameterJdbcTemplate.queryForObject("SELECT owner_id FROM pets WHERE id=:id", params, Integer.class);
        } catch (EmptyResultDataAccessException ex) {
            throw new ObjectRetrievalFailureException(Pet.class, id);
        }
        Owner owner = this.ownerRepository.findById(ownerId);
        return EntityUtils.getById(owner.getPets(), Pet.class, id);
    }

    @Override
    public void save(Pet pet) {
        if (pet.isNew()) {
            Number newKey = this.insertPet.executeAndReturnKey(
                createPetParameterSource(pet));
            pet.setId(newKey.intValue());
        } else {
            this.namedParameterJdbcTemplate.update(
                "UPDATE pets SET name=:name, birth_date=:birth_date, type_id=:type_id, " +
                    "owner_id=:owner_id WHERE id=:id",
                createPetParameterSource(pet));
        }
    }

    @Override
    public Collection<Pet> findAll() {
        JdbcPetRowMapper mapper = new JdbcPetRowMapper();

        // Retrieve the list of all the pet types
        final List<PetType> petTypes = findPetTypes();

        // Retrieve the list of all pets
        List<JdbcPet> jdbcPets = this.namedParameterJdbcTemplate.query(
            "SELECT * FROM pets ORDER BY name",
            new BeanPropertyRowMapper<JdbcPet>() {
                int ownerId;

                @Override
                public JdbcPet mapRow(ResultSet rs, int rowNumber) throws SQLException { // This method is called for every row that's been returned
                    JdbcPet jdbcPet = mapper.mapRow(rs, rowNumber);
                    // Get the id of the owners
                    ownerId = rs.getInt("owner_id");
                    // Set pet type
                    jdbcPet.setType(EntityUtils.getById(petTypes, PetType.class, jdbcPet.getTypeId()));

                    return jdbcPet;
                }
            });

        // Add the owners to the pets
        for (JdbcPet jdbcPet : jdbcPets) {
            jdbcPet.setOwner(this.ownerRepository.findById(jdbcPet.getOwnerId()));
        }

        return new ArrayList<>(jdbcPets);
    }

    /**
     * This needs to be implemented in the next Sprint
     */
    @Override
    public void removePet(int id) {

        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
//        Pet pet = this.namedParameterJdbcTemplate.queryForObject("SELECT * FROM Pet WHERE id=:id", params ,Pet.class);

        String DELETE_PET = "delete from pets p WHERE p.id =:id";
        this.namedParameterJdbcTemplate.update(DELETE_PET, params);
    }

    /**
     * Creates a {@link MapSqlParameterSource} based on data values from the supplied {@link Pet} instance.
     */
    private MapSqlParameterSource createPetParameterSource(Pet pet) {
        return new MapSqlParameterSource()
            .addValue("id", pet.getId())
            .addValue("name", pet.getName())
            .addValue("birth_date", pet.getBirthDate())
            .addValue("type_id", pet.getType().getId())
            .addValue("owner_id", pet.getOwner().getId());
    }

}
