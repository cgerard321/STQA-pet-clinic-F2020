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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
// NEW UI TO BE IMPLEMENTED -> JAV
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM = "owners/createOrUpdateOwnerForm";
    private final ClinicService clinicService;
    private String optionSelected;

    @Autowired
    public OwnerController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/owners/new")
    public String initCreationForm(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/owners/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping(value = "/owners/find")
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", new Owner());
        return "owners/findOwners";
    }

    @GetMapping(value = "/owners")
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model,
                                  @RequestParam(value="dropdownOptions", required = false, defaultValue = "") String dropdownOptions) {
        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        //by lucas-cimino
        //get the dropdown value option for options that can have multiple results
        if (dropdownOptions.equals("OptionFirstName") || dropdownOptions.equals("OptionLastName") || dropdownOptions.equals("OptionCity"))
        {
            //initialize a Collection of owner and a String
            Collection<Owner> results = null;
            String value = "";

            //Check the value
            if (dropdownOptions.equals("OptionFirstName"))
            {
                results = this.clinicService.findOwnerByFirstName(owner.getFirstName());
                value = "firstName";
            }

            else if (dropdownOptions.equals("OptionLastName"))
            {
                results = this.clinicService.findOwnerByLastName(owner.getLastName());
                value = "lastName";
            }

            else if (dropdownOptions.equals("OptionCity"))
            {
                results = this.clinicService.findOwnerByCity(owner.getCity());
                value = "city";
            }

            //no owner found
            if (results.isEmpty())
            {
                result.rejectValue(value, "notFound", "not found");
                return "owners/findOwners";
            }

            //one owner found
            else if (results.size() == 1)
            {
                owner = results.iterator().next();
                return "redirect:/owners/" + owner.getId();
            }

            //multiple owners found
            else
            {
                model.put("selections", results);
                return "owners/ownersList";
            }
        }

        else if (dropdownOptions.equals("OptionId") || dropdownOptions.equals("OptionAddress") ||
            dropdownOptions.equals("OptionTelephone") || dropdownOptions.equals("OptionEmail"))
        {
            Owner currOwner = null;
            String value = "";
            //Check the value
            if (dropdownOptions.equals("OptionId"))
            {
                currOwner = this.clinicService.findOwnerById(owner.getId());
                value = "Id";
            }

            else if (dropdownOptions.equals("OptionAddress"))
            {
                currOwner = this.clinicService.findOwnerByAddress(owner.getAddress());
                value = "Address";
            }

            else if (dropdownOptions.equals("OptionTelephone"))
            {
                currOwner = this.clinicService.findOwnerByTelephone(owner.getTelephone());
                value = "Telephone";
            }

            else if (dropdownOptions.equals("OptionEmail"))
            {
                currOwner = this.clinicService.findOwnerByEmail(owner.getEmail());
                value = "Email";
            }

            if (currOwner.equals(owner))
            {
                result.rejectValue(value, "notFound", "not found");
                return "owners/findOwners";
            }

            //one owner found
            else
            {
                return "redirect:/owners/" + currOwner.getId();
            }
        }

        else
        {
            return "owners/findOwners";
        }



        // find owners by last name
//        Collection<Owner> results = this.clinicService.findOwnerByLastName(owner.getLastName());
//        if (results.isEmpty()) {
//            // no owners found
//            result.rejectValue("lastName", "notFound", "not found");
//            return "owners/findOwners";
//        } else if (results.size() == 1) {
//            // 1 owner found
//            owner = results.iterator().next();
//            return "redirect:/owners/" + owner.getId();
//        } else {
//            // multiple owners found
//            model.put("selections", results);
//            return "owners/ownersList";
//        }
    }

    @GetMapping(value = "/owners/{ownerId}/edit")
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        model.addAttribute(owner);
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping(value = "/owners/{ownerId}/edit")
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        if (result.hasErrors()) {
            return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        } else {
            owner.setId(ownerId);
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/{ownerId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @GetMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.clinicService.findOwnerById(ownerId));
        mav.addObject("hasFutureVisits", this.clinicService.findVisitsByOwnerId(ownerId).stream()
            .anyMatch(v -> v.getDate().isAfter(LocalDate.now())));
        return mav;
    }

    @GetMapping(value = "/owners/{ownerId}/appointments/cancel")
    public String initCancelOwnerAppointmentForm(@PathVariable("ownerId") int ownerId, Map<String, Object> model) {
        Collection<Visit> visits = this.clinicService.findVisitsByOwnerId(ownerId).stream()
            .filter(v -> v.getDate().isAfter(LocalDate.now()))
            .collect(Collectors.toList());

        model.put("visits", visits);
        model.put("showWarning", visits.stream().anyMatch(v -> v.getDate().isBefore(LocalDate.now().plusDays(3))));
        return "appointments/cancelAppointment";
    }

    @PostMapping(value = "/owners/{ownerId}/appointments/cancel", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String processCancelOwnerAppointmentForm(@RequestBody MultiValueMap<String, String> formData) {
        List<Integer> visits = new ArrayList<>();
        formData.forEach((k, v) -> {
            int visit;
            try {
                visit = Integer.parseInt(k);
            } catch (NumberFormatException e) {
                return;
            }

            v.stream().findAny().ifPresent(answer -> {
                if (answer.equals("on")) {
                    visits.add(visit);
                }
            });
        });

        if (!visits.isEmpty()) {
            clinicService.deleteVisitsById(visits);
        }

        return "redirect:/owners/{ownerId}";
    }
}
