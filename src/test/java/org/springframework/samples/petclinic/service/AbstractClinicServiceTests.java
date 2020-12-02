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

import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.util.EntityUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * <p> Base class for {@link ClinicService} integration tests. </p> <p> Subclasses should specify Spring context
 * configuration using {@link ContextConfiguration @ContextConfiguration} annotation </p> <p>
 * AbstractclinicServiceTests and its subclasses benefit from the following services provided by the Spring
 * TestContext Framework: </p> <ul> <li><strong>Spring IoC container caching</strong> which spares us unnecessary set up
 * time between test execution.</li> <li><strong>Dependency Injection</strong> of test fixture instances, meaning that
 * we don't need to perform application context lookups. See the use of {@link Autowired @Autowired} on the <code>{@link
 * AbstractClinicServiceTests#clinicService clinicService}</code> instance variable, which uses autowiring <em>by
 * type</em>. <li><strong>Transaction management</strong>, meaning each test method is executed in its own transaction,
 * which is automatically rolled back by default. Thus, even if tests insert or otherwise change database state, there
 * is no need for a teardown or cleanup script. <li> An {@link org.springframework.context.ApplicationContext
 * ApplicationContext} is also inherited and can be used for explicit bean lookup if necessary. </li> </ul>
 *
 * @author Ken Krebs
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 */

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
abstract class AbstractClinicServiceTests {

    @Autowired
    protected ClinicService clinicService;

    @Mock
    PetRepository petRepository;

    @InjectMocks
    ClinicServiceImpl mockService;

    @Test
    @Order(1)
    void shouldFindOwnersByLastName() {
        Collection<Owner> owners = this.clinicService.findOwnerByLastName("Davis");
        assertThat(owners.size()).isEqualTo(2);

        owners = this.clinicService.findOwnerByLastName("Daviss");
        assertThat(owners.isEmpty()).isTrue();
    }

    @Test
    @Order(2)
    void shouldFindSingleOwnerWithPet() {
        Owner owner = this.clinicService.findOwnerById(1);
        assertThat(owner.getLastName()).startsWith("Franklin");
        assertThat(owner.getPets().size()).isEqualTo(1);
        assertThat(owner.getPets().get(0).getType()).isNotNull();
        assertThat(owner.getPets().get(0).getType().getName()).isEqualTo("cat");
    }

    @Test
    @Transactional
    @Order(3)
    public void shouldInsertOwner() {
        Collection<Owner> owners = this.clinicService.findOwnerByLastName("Schultz");
        int found = owners.size();

        Owner owner = new Owner();
        owner.setProfile_picture("images_default");
        owner.setFirstName("Sam");
        owner.setLastName("Schultz");
        owner.setAddress("4 Evans Street");
        owner.setCity("Wollongong");
        owner.setState("WI");
        owner.setTelephone("4444444444");
        owner.setEmail("george.franklin@gamil.com");
        owner.setComment("Please don't crash my pull request or whatever");

        this.clinicService.saveOwner(owner);
        assertThat(owner.getId().longValue()).isNotEqualTo(0);

        owners = this.clinicService.findOwnerByLastName("Schultz");
        assertThat(owners.size()).isEqualTo(found + 1);
    }

    @Test
    @Transactional
    @Order(4)
    void shouldUpdateOwner() {
        Owner owner = this.clinicService.findOwnerById(1);
        String oldLastName = owner.getLastName();
        String newLastName = oldLastName + "X";

        owner.setLastName(newLastName);
        this.clinicService.saveOwner(owner);

        // retrieving new name from database
        owner = this.clinicService.findOwnerById(1);
        assertThat(owner.getLastName()).isEqualTo(newLastName);
    }

    @Test
    @Order(5)
    void shouldFindPetWithCorrectId() {
        Pet pet7 = this.clinicService.findPetById(7);
        assertThat(pet7.getName()).startsWith("Samantha");
        assertThat(pet7.getOwner().getFirstName()).isEqualTo("Jean");

    }

    @Test
    @Order(6)
    void shouldFindAllPetTypes() {
        Collection<PetType> petTypes = this.clinicService.findPetTypes();

        PetType petType1 = EntityUtils.getById(petTypes, PetType.class, 1);
        assertThat(petType1.getName()).isEqualTo("cat");
        PetType petType4 = EntityUtils.getById(petTypes, PetType.class, 4);
        assertThat(petType4.getName()).isEqualTo("snake");
    }

    @Test
    @Transactional
    @Order(9)
    public void shouldInsertPetIntoDatabaseAndGenerateId() {
        Owner owner6 = this.clinicService.findOwnerById(6);
        int found = owner6.getPets().size();

        Pet pet = new Pet();
        pet.setName("bowser");
        Collection<PetType> types = this.clinicService.findPetTypes();
        pet.setType(EntityUtils.getById(types, PetType.class, 2));
        pet.setBirthDate(LocalDate.now());
        owner6.addPet(pet);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);

        this.clinicService.savePet(pet);
        this.clinicService.saveOwner(owner6);

        owner6 = this.clinicService.findOwnerById(6);
        assertThat(owner6.getPets().size()).isEqualTo(found + 1);
        // checks that id has been generated
        assertThat(pet.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Order(10)
    public void shouldUpdatePetName() throws Exception {
        Pet pet7 = this.clinicService.findPetById(7);
        String oldName = pet7.getName();

        String newName = oldName + "X";
        pet7.setName(newName);
        this.clinicService.savePet(pet7);

        pet7 = this.clinicService.findPetById(7);
        assertThat(pet7.getName()).isEqualTo(newName);
    }

    @Test
    @Order(11)
    void shouldFindVets() {
        Collection<Vet> vets = this.clinicService.findVets();

        Vet vet = EntityUtils.getById(vets, Vet.class, 3);
        assertThat(vet.getLastName()).isEqualTo("Douglas");
        assertThat(vet.getNrOfSpecialties()).isEqualTo(2);
        assertThat(vet.getSpecialties().get(0).getName()).isEqualTo("dentistry");
        assertThat(vet.getSpecialties().get(1).getName()).isEqualTo("surgery");
    }

    @Test
    @Transactional
    @Order(13)
    public void shouldAddNewVisitForPet() {
        Pet pet7 = this.clinicService.findPetById(7);
        int found = pet7.getVisits().size();
        Visit visit = new Visit();
        pet7.addVisit(visit);
        visit.setDescription("test");
        this.clinicService.saveVisit(visit);
        this.clinicService.savePet(pet7);

        pet7 = this.clinicService.findPetById(7);
        assertThat(pet7.getVisits().size()).isEqualTo(found + 1);
        assertThat(visit.getId()).isNotNull();
    }

    @Test
    @Order(12)
    void shouldFindVisitsByPetId() throws Exception {
        Collection<Visit> visits = this.clinicService.findVisitsByPetId(7);
        assertThat(visits.size()).isEqualTo(2);
        Visit[] visitArr = visits.toArray(new Visit[visits.size()]);
        assertThat(visitArr[0].getPet()).isNotNull();
        assertThat(visitArr[0].getDate()).isNotNull();
        assertThat(visitArr[0].getPet().getId()).isEqualTo(7);
    }

    @Test
    @Order(14)
    void shouldFindVisitsByOwnerId() throws Exception {
        Collection<Visit> visits = this.clinicService.findVisitsByOwnerId(6);
        assertThat(visits.size()).isEqualTo(4);
        Visit[] visitArr = visits.toArray(new Visit[visits.size()]);
        assertThat(visitArr[0].getPet()).isNotNull();
        assertThat(visitArr[0].getDate()).isNotNull();

        // we need this because retrieving visits doesn't fill the pet's owner property.
        Pet pet = this.clinicService.findPetById(visitArr[0].getPet().getId());
        assertThat(pet.getOwner().getId()).isEqualTo(6);
    }

    @Test
    @Order(8)
    void shouldFindAllPetInClinic() {
        Collection<Pet> pets = this.clinicService.findPets();
        // Make sure that all the pets is there
        assertThat(pets.size()).isEqualTo(13);

        // Get the last pet
        Pet pet = EntityUtils.getById(pets, Pet.class, 13);

        assertThat(pet.getName()).isEqualTo("Sly");
        assertThat(pet.getBirthDate()).isEqualTo("2012-06-08");
        assertThat(pet.getType().toString()).isEqualTo("cat");
        assertThat(pet.getOwner().getId()).isEqualTo(10);
    }

    @Test
    @Order(18)
    void shouldRemovePetFromPetList() {

        Collection<Pet> pets = this.clinicService.findPets();

        Pet pet = EntityUtils.getById(
            pets,
            Pet.class,
            1);

        pets.remove(pet);

        assertThat(pets.size()).isEqualTo(12);

    }

    // Remove Samantha
    @Test
    @Order(22)
    void shouldRemoveSamanthaFromPetList() {
        int id = 7;
        // Arrange
        Collection<Pet> actualPetList;

        // Act
        this.clinicService.removePetById(id);
        actualPetList = this.clinicService.findPets();

        // Assert
        assertThat(actualPetList.size()).isEqualTo(12);
        boolean result = actualPetList.stream().anyMatch(x -> x.getName().equalsIgnoreCase("Samantha"));
        assertFalse(result);
    }

    @Test
    @Order(23)
    void shouldExceptionWithPetNotExist() {
        int id = 69;
        assertThrows(ObjectRetrievalFailureException.class, () -> this.clinicService.removePetById(id));
    }

    @Test
    @Order(15)
    void shouldRetrieveOwnerEmail() throws Exception {
        Owner owner = new Owner();
        owner.setEmail("antoine.heb@outlook.com");

        String expected = "antoine.heb@outlook.com";
        String actualResult = owner.getEmail();

        assertThat(actualResult.equals(expected));
    }

    @Test
    @Transactional
    @Order(16)
    void shouldUpdateOwnerEmail() {
        Owner owner = this.clinicService.findOwnerById(1);
        String oldEmail = owner.getEmail();
        String newEmail = oldEmail + "X";

        owner.setEmail(newEmail);
        this.clinicService.saveOwner(owner);

        // retrieving new name from database
        owner = this.clinicService.findOwnerById(1);
        assertThat(owner.getEmail()).isEqualTo(newEmail);
    }

    @Test
    @Order(17)
    void shouldExceptionFindAllPetInClinic() {
        when(petRepository.findAll()).thenReturn(null);
        assertThrows(NullPointerException.class, () -> mockService.findPets());
    }

    @Test
    @Transactional
    @Order(7)
    void shouldDeleteVisitsByIdIn() {
        this.clinicService.deleteVisitsById(Arrays.asList(1, 2));

        // Note: relying on the fact all visits in the sample database are for owner 6
        Collection<Visit> visits = this.clinicService.findVisitsByOwnerId(6);
        assertThat(visits.size()).isEqualTo(2);
    }


//    @Test
//    void shouldFindAllSchedulesClinic() {
//        Collection<Schedule> schedules = this.clinicService.findSchedules();
//        // Make sure that all the schedules is there
//        assertThat(schedules.size()).isEqualTo(6);
//    }


//    @Test
//    void shouldFindScheduleWithCorrectId() {
//        Schedule sched6 = this.clinicService.findScheduleByVetId(6);
//        assertThat(sched6.getVetId()).isEqualTo(6);
//        assertThat(sched6.getDayAvailable()).isEqualTo(5);
//
//    }

    @Test
    @Order(21)
    void shouldFindAllAppointments(){

        Collection<Visit> visits = this.clinicService.findAllVisits();
        assertThat(visits.size()==4);
    }

    @Test
    @Transactional
    @Order(19)
    void shouldDeleteVisitById() throws Exception{

        int oldRows = this.clinicService.findAllVisits().size();
        MatcherAssert.assertThat(oldRows, is(4));

        this.clinicService.deleteVisitById(4);

        int newRows = this.clinicService.findAllVisits().size();
        MatcherAssert.assertThat(newRows, is(3));
    }

    @Test
    @Transactional
    @Order(24)
    public void shouldInsertRating() {
        Rating rating = new Rating();
        rating.setUsername("Nick");
        Collection<Pet> pets = this.clinicService.findPets();
        rating.setPet(EntityUtils.getById(pets, Pet.class, 2));
        rating.setRating(5);
        this.clinicService.saveRating(rating);
        assertThat(rating.getUsername()).isEqualTo("Nick");
    }

    @Test
    @Order(25)
    void shouldFindRatings() {
        Collection<Rating> ratings = this.clinicService.findRatings();
        Rating rating = EntityUtils.getById(ratings, Rating.class, 1);
        assertThat(rating.getUsername()).isEqualTo("Johny");
        assertThat(rating.getRating()).isEqualTo(5);
        assertThat(ratings.size()).isEqualTo(1);
    }


    @Test
    void shouldRetrieveOwnerState() {
        Owner owner = new Owner();
        owner.setState("NY");

        String expected = "NY";
        String actualResult = owner.getState();

        assertThat(actualResult.equals(expected));
    }

    @Test
    void shouldUpdateOwnerState() {
        Owner owner = this.clinicService.findOwnerById(1);
        String newState = "NY";

        owner.setState(newState);
        this.clinicService.saveOwner(owner);

        // retrieving new name from database
        owner = this.clinicService.findOwnerById(1);
        assertThat(owner.getState()).isEqualTo(newState);
    }

    @Test
    void shouldReturnFutureVisits() {
        //arrange-act
        Collection<Visit> visits = this.clinicService.findAllFutureVisits();
        //assert
        assertTrue(visits.size() > 0);
    }

    @Test
    void shouldReturnVisitsWithFutureDates() {
        //arrange
        Collection<Visit> visits = this.clinicService.findAllFutureVisits();
        LocalDate current_date = LocalDate.now();

        //act
        Visit test = visits.iterator().next();
        for(Visit v : visits)
            assertFalse(v.getDate().compareTo(current_date) < 0);

        //assert
        assertEquals(test.getDate(), LocalDate.of(2021, 01, 01));
        assertTrue(visits.size() > 0);
    }
}
