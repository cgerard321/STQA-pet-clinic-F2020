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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    public OwnerController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Qualifier("servletContext")
    @Autowired
    ServletContext context;

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
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Owner> results = this.clinicService.findOwnerByLastName(owner.getLastName());
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owners/ownersList";
        }
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
    public String processCancelOwnerAppointmentForm(@PathVariable int ownerId, @RequestBody MultiValueMap<String, String> formData) {
        Collection<Visit> ownerVisits = clinicService.findVisitsByOwnerId(ownerId);

        List<Integer> visits = new ArrayList<>();
        formData.forEach((k, v) -> {
            int visitId;
            try {
                visitId = Integer.parseInt(k);
            } catch (NumberFormatException e) {
                return;
            }

            // only delete visits which actually belong to that owner
            if (ownerVisits.stream().anyMatch(visit -> visit.getId() == visitId)) {
                v.stream().findAny().ifPresent(answer -> {
                    if (answer.equals("on")) {
                        visits.add(visitId);
                    }
                });
            }
        });

        if (!visits.isEmpty()) {
            clinicService.deleteVisitsById(visits);
        }

        return "redirect:/owners/{ownerId}";
    }

    // Since multipart support is not enabled on the server, this endpoint is not working as it should and must be commented for the tests to pass.
   /* @RequestMapping(value = "/owners/addMultipleOwners", method = RequestMethod.POST, headers = "content-type=multipart/form-data")
    public String addMultipleOwners(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null)
            System.err.println("file is null");
        else
            System.err.println("Output: " + file.getName());


        return "redirect:/owners";
    }*/

    // The JSON parsing logic is contained within that endpoint until I figure out a way to enable multipart support on Spring.
    @PostMapping(value = "/owners/addMultipleOwnersFake")
    public String addMultipleOwnersFake() throws FileNotFoundException {

        // Obviously, the goal is to have the user supply the JSON file and not simply fetching it from our resources folder.
        final String FILE_PATH = ResourceUtils.getFile("classpath:uploads/success.json").getPath();

        Gson gson = new Gson();

        Owner[] owners = gson.fromJson(new FileReader(FILE_PATH), Owner[].class);

        // We add each owner object into the database.
        for (Owner owner : owners)
            clinicService.saveOwner(owner);

        return "redirect:/owners";
    }
}
