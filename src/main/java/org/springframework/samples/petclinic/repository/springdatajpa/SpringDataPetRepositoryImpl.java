package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Profile("spring-data-jpa")
public class SpringDataPetRepositoryImpl implements PetRepositoryOverride {

    @PersistenceContext
    private EntityManager em;

    @Modifying
    @Transactional
    @Override
    public void removePet(Pet pet) {
        String petId = pet.getId().toString();
        // Cascade delete visits
        this.em.createQuery("DELETE FROM Visit visit WHERE pet_id=" + petId).executeUpdate();
        // Cascade delete rating
        this.em.createQuery("DELETE FROM Rating rating WHERE pet_id=" + petId).executeUpdate();

        this.em.createQuery("DELETE FROM Pet pet WHERE id=" + petId).executeUpdate();
        if (em.contains(pet)) {
            em.remove(pet);
        }
    }
}
