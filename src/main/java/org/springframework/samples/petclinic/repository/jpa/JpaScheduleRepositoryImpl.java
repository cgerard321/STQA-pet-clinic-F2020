package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.repository.ScheduleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JpaScheduleRepositoryImpl implements ScheduleRepository {

    @PersistenceContext
    private EntityManager em;




    @Override
    public List<Schedule> findAll() {
        return this.em.createQuery("SELECT new vet_schedule (vetId, dayAvailable) FROM vet_schedule").getResultList();
    }

//    @Override
//    public List<Schedule> findAll() {
//        return this.em.createQuery("SELECT distinct sched FROM vet_schedule sched ORDER BY sched.vetId").getResultList();
//    }

    @Override
    public Schedule findScheduleById(int id) {
        return this.em.find(Schedule.class, id);
    }


}
