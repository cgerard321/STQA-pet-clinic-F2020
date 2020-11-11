package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Reminder;
import org.springframework.samples.petclinic.repository.ReminderRepository;

public interface SpringDataReminderRepository extends ReminderRepository, Repository<Reminder, Integer> {
}
