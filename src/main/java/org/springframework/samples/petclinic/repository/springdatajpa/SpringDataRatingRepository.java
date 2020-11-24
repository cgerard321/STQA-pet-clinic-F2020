package org.springframework.samples.petclinic.repository.springdatajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.samples.petclinic.model.Rating;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.repository.RatingRepository;

public interface SpringDataRatingRepository extends RatingRepository, Repository<Rating, Integer> {

}
