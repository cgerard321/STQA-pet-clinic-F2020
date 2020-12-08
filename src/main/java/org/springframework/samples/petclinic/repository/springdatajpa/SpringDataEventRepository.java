package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.repository.EventRepository;

import java.util.Collection;

public interface SpringDataEventRepository extends EventRepository, Repository<Event, Integer> {

    @Override
    @Query("SELECT DISTINCT event FROM Event event")
    public Collection<Event> getEvents();
}
