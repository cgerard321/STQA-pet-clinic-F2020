package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Controller
public class RatingController {

    private final ClinicService clinicService;

    @Autowired
    public RatingController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("pets")
    public Collection<Pet> populatePets() {
        return this.clinicService.findPets();
    }

    @GetMapping(value = "/ratings/new")
    public String initNewRatingForm(Map<String, Object> model) {
        Rating rating = new Rating();
        model.put("rating", rating);
        return "ratings/createRatingForm";
    }

    @PostMapping(value = "/ratings/new")
    public String processNewRatingForm(@RequestParam("pet") String petName, @Valid Rating rating, BindingResult result) {
        ArrayList<Pet> petList = new ArrayList<>(this.clinicService.findPets());
        for(int i=0;i<petList.size();i++){
            if(petName.equals(petList.get(i).getName())){
                System.out.println(petName+" "+petList.get(i).getName());
                Pet pet = this.clinicService.findPetById(i+1);
                rating.setPet(pet);
                break;
            }
        }
        this.clinicService.saveRating(rating);
        return "redirect:/";
    }

    @GetMapping(value = "/ratings")
    public String showRatingList(Map<String, Object> model) {
        Ratings ratings = new Ratings();
        ratings.getRatingList().addAll(this.clinicService.findRatings());
        model.put("ratings", ratings);
        return "ratings/ratingList";
    }
}
