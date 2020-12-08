/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.service;


import org.checkerframework.checker.units.qual.C;
import org.springframework.samples.petclinic.model.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Mostly used as a facade so all controllers have a single point of entry
 *
 * @author Michael Isvy
 */
public interface ClinicService {

    Collection<PetType> findPetTypes();

    Owner findOwnerById(int id);

    Pet findPetById(int id);

    /**
     * Find all the <code>Pet</code> in the clinic
     *
     * @return a list of all the pets
     */
    Collection<Pet> findPets();

    void savePet(Pet pet);

    void saveVisit(Visit visit);

    Collection<Vet> findVets();

    void saveOwner(Owner owner);

    Collection<Owner> findOwnerByLastName(String lastName);

    Collection<Owner> findAllOwner();

    Collection<Visit> findVisitsByPetId(int petId);

//
//    Collection<Schedule> findSchedules();
//
//    Schedule findScheduleByVetId(int id);

    Collection<Visit> findVisitsByOwnerId(int ownerId);

    Collection<Visit> findAllVisits();

    Collection<Visit> findAllFutureVisits();

    void deleteVisitsById(List<Integer> visitIds);

    void deleteVisitById(int visitId);

    void removePetById(int petId);

    void saveRating(Rating rating);

    Collection<Rating> findRatings();

    Collection<Event> getEvents();

    Collection<Rating> findRatingsByPetId(int petId);

    Collection<Vet> findVetsAvailableForDay(int dayId);
}
