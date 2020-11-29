package org.springframework.samples.petclinic.repository.jpa;

import org.springframework.samples.petclinic.model.Rating;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.repository.RatingRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

@Repository
public class JpaRatingRepositoryImpl implements RatingRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Rating rating) {
        if (rating.getId() == null) {
            this.em.persist(rating);
        } else {
            this.em.merge(rating);
        }
    }

    @Override
    public Collection<Rating> findAll() {
        return this.em.createQuery("SELECT distinct rating FROM Rating rating ORDER BY rating.id").getResultList();
    }

}
