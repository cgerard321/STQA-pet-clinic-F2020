package org.springframework.samples.petclinic.repository;

import org.springframework.samples.petclinic.model.Event;

import java.util.Collection;

public interface EventRepository {

    Collection<Event> getEvents(int year, int month);

}
