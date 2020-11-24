package org.springframework.samples.petclinic.repository;

import org.springframework.samples.petclinic.model.Rating;
import org.springframework.samples.petclinic.model.Vet;

import java.util.Collection;
import java.util.List;

public interface RatingRepository{

    void save(Rating rating);

    Collection<Rating> findAll();
}
