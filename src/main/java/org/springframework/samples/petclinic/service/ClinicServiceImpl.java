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


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Mostly used as a facade for all Petclinic controllers
 * Also a placeholder for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class ClinicServiceImpl implements ClinicService {

    private PetRepository petRepository;
    private VetRepository vetRepository;
    private OwnerRepository ownerRepository;
    private VisitRepository visitRepository;
    private RatingRepository ratingRepository;

    @Autowired
    public ClinicServiceImpl(PetRepository petRepository, VetRepository vetRepository, OwnerRepository ownerRepository, VisitRepository visitRepository, RatingRepository ratingRepository) {
        this.petRepository = petRepository;
        this.vetRepository = vetRepository;
        this.ownerRepository = ownerRepository;
        this.visitRepository = visitRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<PetType> findPetTypes() {
        return petRepository.findPetTypes();
    }

    @Override
    @Transactional(readOnly = true)
    public Owner findOwnerById(int id) {
        return ownerRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Owner> findOwnerByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public Collection<Owner> findAllOwner() {
        return ownerRepository.findAllOwner();
    }

    @Override
    @Transactional
    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }


    @Override
    @Transactional
    public void saveVisit(Visit visit) {
        visitRepository.save(visit);
    }

    @Override
    @Transactional(readOnly = true)
    public Pet findPetById(int id) {
        return petRepository.findById(id);
    }

    @Override
    public Collection<Pet> findPets() {
        Collection<Pet> ret = petRepository.findAll();

        // Check if there is pets in the clinic
        if (ret == null || ret.isEmpty()) {
            throw new NullPointerException();
        }

        return ret;
    }

    @Override
    @Transactional
    public void savePet(Pet pet) {
        petRepository.save(pet);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "vets")
    public Collection<Vet> findVets() {
        return vetRepository.findAll();
    }

    @Override
    public Collection<Visit> findVisitsByPetId(int petId) {
        return visitRepository.findByPetId(petId);
    }

    @Override
    public Collection<Visit> findVisitsByOwnerId(int ownerId) {
        return visitRepository.findByOwnerId(ownerId);
    }

    @Override
    public Collection<Visit> findAllVisits(){
        return visitRepository.findAll();
    }

    @Override
    public Collection<Visit> findAllFutureVisits()
    {
        LocalDate current_date = LocalDate.now();
        return visitRepository.findAllFutureVisits(current_date);
    }

    @Transactional
    public void deleteVisitsById(List<Integer> visitIds) {
        visitRepository.deleteByIdIn(visitIds);
    }
    @Transactional
    public void deleteVisitById(int visitId) {
        visitRepository.deleteById(visitId);
    }

    public void removeOwnerById(int ownerId) {
        Owner owner = ownerRepository.findById(ownerId);
        if (owner == null) {
            throw new ObjectRetrievalFailureException("Owner not found", ObjectRetrievalFailureException.class);
        }
        ownerRepository.removeOwner(owner);
    }

    public void removePetById(int petId) {
        Pet pet = petRepository.findById(petId);
        // Check if the petId is associated to a valid pet
        if (pet == null) {
            throw new ObjectRetrievalFailureException("Pet not found", ObjectRetrievalFailureException.class);
        }

        petRepository.removePet(pet);
    }

    @Override
    @Transactional
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Rating> findRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public Collection<Rating> findRatingsByPetId(int petId){
        return ratingRepository.findByPetId(petId);
    }


}
