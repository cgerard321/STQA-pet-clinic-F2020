package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.repository.ScheduleRepository;

import java.util.List;

public interface SpringDataScheduleRepository extends ScheduleRepository, Repository<Schedule, Integer> {

    @Override
    @Query("SELECT vetId FROM vet_schedule ORDER BY vetId")
    List<Schedule> findAll();

    @Override
    @Query("SELECT vetId, dayAvailable FROM vet_schedule WHERE vetId = :id")
    Schedule findScheduleById(int id);
}
