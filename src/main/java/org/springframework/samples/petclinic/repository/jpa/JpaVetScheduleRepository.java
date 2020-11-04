package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.samples.petclinic.model.VetSchedule;
import org.springframework.samples.petclinic.repository.springdatajpa.VetScheduleRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

public class JpaVetScheduleRepository implements VetScheduleRepository {

    @PersistenceContext
    private EntityManager em;

// Gabriel
//    @Override
//    public Collection<VetSchedule> findAll() {
//        return this.em.createQuery("SELECT day_available FROM vet_schedule LEFT JOIN FETCH Vet.id").getResultList();
//    }
}
