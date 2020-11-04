package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.VetSchedule;

import java.util.Collection;

public interface VetScheduleRepository {

    Collection<VetSchedule> findAll();

}
