package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.repository.ScheduleRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaScheduleRepositoryImpl implements ScheduleRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public List<Schedule> findAllSchedules() {
        return this.em.createQuery("SELECT new vet_schedule (vetId, dayAvailable) FROM vet_schedule").getResultList();
    }

    @Override
    public Schedule findScheduleById(int id) {
        Query query = this.em.createQuery("SELECT new vet_schedule (vetId, dayAvailable) FROM vet_schedule WHERE vetId =: id");
        query.setParameter("id", id);
        return (Schedule) query.getSingleResult();
    }


}
