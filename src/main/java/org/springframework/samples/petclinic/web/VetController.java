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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

    private final ClinicService clinicService;

    @Autowired
    public VetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = { "/vets.html"})
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/vetList";
    }

    @GetMapping(value = { "/vets.json", "/vets.xml"})
    public
    @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }


    @GetMapping(value = { "/scheduleList"})
    public String showVetScheduleList(Map<String, Object> model) {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/scheduleList";
    }

    /*Added by the APPT team, returns the schedule of a vet based on their specialty*/
    @GetMapping(value ="/vetProfile.html")
    public String showVetProfile(Map<String, Object> model, @RequestParam int id)
    {
        Vet selectedVet;

         List<Vet> allvets = this.showResourcesVetList().getVetList();
            //model.put("vet", selectedVet);

        //modified to use findAllFutureVisits() method to get future appointments
        Collection<Visit> allVisits = this.clinicService.findAllFutureVisits();
        ArrayList<Visit> generalVisits = new ArrayList<>();
        ArrayList<Visit> surgeryVisits = new ArrayList<>();
        ArrayList<Visit> dentistryVisits = new ArrayList<>();
        ArrayList<Visit> radiologyVisits = new ArrayList<>();
        ArrayList<Visit> miscVisits = new ArrayList<>();

        //Loop through all the vets in the db, associate the id that's passed through with the corresponding vet
        for(int i = 0; i < allvets.size(); i++)
        {
            if(allvets.get(i).getId() == id)
            {
                selectedVet = allvets.get(i);
                model.put("vet", selectedVet);
                //Loop through all the visits, sort them through the categories in arraylists listed above.
                for (Visit visit : allVisits) {
                    if (visit.getDescription().contains("neutered") || visit.getDescription().contains("spayed") ||
                        visit.getDescription().contains("surgery")) {
                        surgeryVisits.add(visit);
                    } else if (visit.getDescription().contains("grooming") || visit.getDescription().contains("trimming") ||
                        visit.getDescription().contains("shot")) {
                        generalVisits.add(visit);
                    } else if (visit.getDescription().contains("receding gums") ||
                        visit.getDescription().contains("tartar control") || visit.getDescription().contains("gingivitis")
                        || visit.getDescription().contains("teeth")) {
                        dentistryVisits.add(visit);
                    } else if (visit.getDescription().contains("X Ray") || visit.getDescription().contains("broken") ||
                        visit.getDescription().contains("bone")) {
                        radiologyVisits.add(visit);
                    }
                    //Other reasons get also assigned to the general veterinarians for an initial evaluation.
                    else {
                        miscVisits.add(visit);
                    }
                }

                //Look through the specializations of the veterinarians and add the corresponding appointments
                //into their schedules
                if(selectedVet.getSpecialties().size() == 0 )
                {
                    generalVisits.addAll(miscVisits);

                    model.put("schedule", generalVisits);
                }

                else if(selectedVet.getSpecialties().toString().contains("surgery"))
                {
                    model.put("schedule", surgeryVisits);
                }

                else if(selectedVet.getSpecialties().toString().contains("dentistry"))
                {
                    model.put("schedule", dentistryVisits);
                }

                else if(selectedVet.getSpecialties().toString().contains("radiology"))
                {
                    model.put("schedule",radiologyVisits);
                }
            }
        }

        return "vets/vetProfile";
    }


}
