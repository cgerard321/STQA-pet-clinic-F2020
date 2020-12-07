package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.*;
import javax.validation.Valid;
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
    public String processNewRatingForm(@RequestParam("pet") String petName, @RequestParam("rating") int ratingValue, @Valid Rating rating, BindingResult result) {
        if(ratingValue <= 10 && ratingValue >= 1) {
            System.out.println("This is my rating "+ratingValue);
            ArrayList<Pet> petList = new ArrayList<>(this.clinicService.findPets());
            for (int i = 0; i < petList.size(); i++) {
                if (petName.equals(petList.get(i).getName())) {
                    System.out.println(petName + " " + petList.get(i).getName());
                    Pet pet = this.clinicService.findPetById(i + 1);
                    rating.setPet(pet);
                    break;
                }
            }
            this.clinicService.saveRating(rating);
            return "redirect:/";
        }
        else{
            JOptionPane.showMessageDialog(null, "Please do not cheat. Enter the rating in range of 1 to 10.\nYou will be redirected to the homepage as a punishment.");
            return "redirect:/";
        }
    }

    @GetMapping(value = "/ratings")
    public String showRatingList(Map<String, Object> model) {
        Ratings ratings = new Ratings();
        ratings.getRatingList().addAll(this.clinicService.findRatings());
        model.put("ratings", ratings);
        return "ratings/ratingList";
    }

    @GetMapping(value = "ratings/findPetRatings/{petId}/petRatings")
    public ModelAndView showPetRatings(@PathVariable("petId") int petId) {
        List<Rating> ratings = this.clinicService.findPetById(petId).getRatings();
        ModelAndView model = new ModelAndView("ratings/petRatingList");
        model.addObject("petRatings", ratings);
        return model;
    }

    @GetMapping(value = "ratings/findPetRatings")
    public String getToFindPetRatingsForm(Map<String, Object> model) {
        Collection<Pet> results = clinicService.findPets();
        model.put("petsList", results);
        return "ratings/findPetRatings";
    }

}
