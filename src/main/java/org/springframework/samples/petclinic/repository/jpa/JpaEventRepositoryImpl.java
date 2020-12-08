package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.repository.EventRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;

@Repository
public class JpaEventRepositoryImpl implements EventRepository {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Collection<Event> getEvents() {

        Query query = this.em.createQuery("SELECT DISTINCT event FROM Event event");

        return query.getResultList();
    }
}
