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
package org.springframework.samples.petclinic.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.json.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.repository.PetRepository;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 *
 */
@Controller
//@RequestMapping("/owners/{ownerId}")
public class PetController {
// Yu Qiao was here
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private static final String VIEWS_PETS_VIEW_DETAILS = "pets/petDetails";
    private final ClinicService clinicService;

    @Autowired
    public PetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return this.clinicService.findPetTypes();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable(name = "ownerId", required = false) Integer ownerId) {
        return ownerId != null ? this.clinicService.findOwnerById(ownerId) : null;
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @InitBinder("pet")
    public void initPetBinder(WebDataBinder dataBinder) {
        dataBinder.setValidator(new PetValidator());
    }

    @GetMapping(value = "/owners/{ownerId}/pets/new")
    public String initCreationForm(Owner owner, ModelMap model) {
        Pet pet = new Pet();
        owner.addPet(pet);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/owners/{ownerId}/pets/new")
    public String processCreationForm(Owner owner, @Valid Pet pet, BindingResult result, ModelMap model) {
        if (StringUtils.hasLength(pet.getName()) && pet.isNew() && owner.getPet(pet.getName(), true) != null){
            result.rejectValue("name", "duplicate", "already exists");
        }
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        } else {
            owner.addPet(pet);
            this.clinicService.savePet(pet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/edit")
    public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model) {
        Pet pet = this.clinicService.findPetById(petId);
        model.put("pet", pet);
        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/edit")
    public String processUpdateForm(@Valid Pet pet, BindingResult result, Owner owner, ModelMap model) {
        if (result.hasErrors()) {
            model.put("pet", pet);
            return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
        }
        else {
            owner.addPet(pet);
            this.clinicService.savePet(pet);
            return "redirect:/owners/{ownerId}";
        }
    }

    @GetMapping(value = "/owners/{ownerId}/pets/{petId}/view")
    public String initViewPet(@PathVariable("petId") int petId, ModelMap model) {
        Pet pet = this.clinicService.findPetById(petId);
        model.put("pet", pet);
        return VIEWS_PETS_VIEW_DETAILS;
    }
    @PostMapping(value = "/pets/{petId}/view")
    public String initViewPet2(@PathVariable("petId") int petId, ModelMap model) {
        Pet pet = this.clinicService.findPetById(petId);
        model.put("pet", pet);
        return VIEWS_PETS_VIEW_DETAILS;
    }

    // GET /pets/petList
    @GetMapping(value = "/pets/petList")
    public String processAllPets(Map<String, Object> model) {
        Collection<Pet> results = clinicService.findPets();

        // Put the list of all the pets into the model
        // and give it the key "selections"
        model.put("selections", results);
        return "pets/petList";
    }

    @GetMapping(value = "/pets/find")
    public String initFindForm(Map<String, Object> model) {
        Collection<Pet> results;
        try {
            results = clinicService.findPets();
        } catch (Exception ex) { // When there's no pet in the clinic, it will go here
            results = null;
        }
        model.put("selections", results);
     model.put("pet", results);
        return "pets/findPets";
    }

    // POST /pets/2/remove
    @PostMapping(value = "/pets/{petId}/remove")
    public String removePetFromList(@PathVariable("petId") int petId, Map<String, Object> model) {
        clinicService.removePetById(petId);
        return "redirect:/pets/find";
    }

    @GetMapping(value="/pets/getPetCount")
    @ResponseBody
    public int getPetCount() throws JsonProcessingException {
        return this.clinicService.findPets().size();
    }

    @GetMapping(value="/pets/getAllPetsInJson")
    @ResponseBody
    public String[] getPetListInJsonString() throws JsonProcessingException {
        // Get all pets
        ArrayList<Pet> pets;
        try {
            pets = new ArrayList<>(this.clinicService.findPets());
        }
        catch (NullPointerException e) {
            return null;
        }

        // Set array size accordingly
        String[] stringPets = new String[pets.size()];

        // Sort the Pet list in a descending order according to the Average Rating
        double highestRating;
        int highestIndex;
        for (int i = 0; i < pets.size(); i++){
            highestIndex = i;
            highestRating = pets.get(i).getAverageRating();
            for (int j = i + 1; j < pets.size(); j++){
                double averageRating = pets.get(j).getAverageRating();
                if (averageRating > highestRating){
                    highestRating = averageRating;
                    highestIndex = j;
                }
                else if (averageRating == highestRating){
                    if (pets.get(j).getTimesRated() > pets.get(highestIndex).getTimesRated()){
                        highestRating = averageRating;
                        highestIndex = j;
                    }
                }
            }
            Pet tempPet = pets.get(i);
            pets.set(i, pets.get(highestIndex));
            pets.set(highestIndex, tempPet);

            stringPets[i] = pets.get(i).toJsonString();
        }

        return stringPets;
    }

    //Returns a collection of all pet types. Mapped to url petTypes
    @GetMapping(value = "/pets/petTypes")
    @ResponseBody
    public  Collection<PetType> getPetTypes() throws JsonProcessingException{
        return this.clinicService.findPetTypes();
    }



}
