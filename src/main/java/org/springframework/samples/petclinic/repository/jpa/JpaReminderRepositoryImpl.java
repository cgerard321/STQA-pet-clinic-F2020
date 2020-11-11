package org.springframework.samples.petclinic.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.samples.petclinic.model.Reminder;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.repository.ReminderRepository;
import org.springframework.samples.petclinic.repository.VisitRepository;
import org.springframework.stereotype.Repository;

/**
 * JPA implementation of the ClinicService interface using EntityManager.
 * <p/>
 * <p>The mappings are defined in "orm.xml" located in the META-INF directory.
 *
 * @author Mike Keith
 * @author Rod Johnson
 * @author Sam Brannen
 * @author Michael Isvy
 * @since 22.4.2006
 */
@Repository
public class JpaReminderRepositoryImpl implements ReminderRepository {

    @PersistenceContext
    private EntityManager em;


//    @Override
//    public void save(Reminder reminder) {
//        if (reminder.getId() == null) {
//            this.em.persist(reminder);
//        } else {
//            this.em.merge(reminder);
//        }
//    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Reminder> findByVetId(Integer vetId) {
        Query query = this.em.createQuery("SELECT r FROM Reminder r where r.vet.id= :id");
        query.setParameter("id", vetId);
        return query.getResultList();
    }

}

