package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Reminder;

public interface ReminderRepository {


//    void save(Reminder reminder);

    List<Reminder> findByVetId(Integer vetId);
}
