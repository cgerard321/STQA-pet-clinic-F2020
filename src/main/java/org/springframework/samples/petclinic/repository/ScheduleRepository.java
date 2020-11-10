package org.springframework.samples.petclinic.repository;

import org.springframework.samples.petclinic.model.Schedule;


import java.util.List;


public interface ScheduleRepository {

    List<Schedule> findAllSchedules();

    Schedule findScheduleById(int id);

    // void saveOrUpdate(Schedule schedule);

    // void delete(int vetId);


}
