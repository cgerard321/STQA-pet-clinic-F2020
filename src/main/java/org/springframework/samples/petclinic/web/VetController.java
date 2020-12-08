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
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.beans.PropertyEditorSupport;
import java.util.*;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

    private final ClinicService clinicService;
    private EntityManager em;

    @Autowired
    public VetController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = {"/vets.html"})
    public String showVetList(Map<String, Object> model) {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for Object-Xml mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/vetList";
    }

    @GetMapping(value = {"/vets.json", "/vets.xml"})
    public
    @ResponseBody
    Vets showResourcesVetList() {
        // Here we are returning an object of type 'Vets' rather than a collection of Vet objects
        // so it is simpler for JSon/Object mapping
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        return vets;
    }


    @GetMapping(value = {"/scheduleList"})
    public String showVetScheduleList(Map<String, Object> model) {
        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());
        model.put("vets", vets);
        return "vets/scheduleList";
    }

    /*Added by the APPT team, returns the schedule of a vet based on their specialty*/
    @GetMapping(value = "/vetProfile.html")
    public String showVetProfile(Map<String, Object> model, @RequestParam int id) {
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
        for (int i = 0; i < allvets.size(); i++) {
            if (allvets.get(i).getId() == id) {
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
                if (selectedVet.getSpecialties().size() == 0) {
                    generalVisits.addAll(miscVisits);

                    model.put("schedule", generalVisits);
                } else if (selectedVet.getSpecialties().toString().contains("surgery")) {
                    model.put("schedule", surgeryVisits);
                } else if (selectedVet.getSpecialties().toString().contains("dentistry")) {
                    model.put("schedule", dentistryVisits);
                } else if (selectedVet.getSpecialties().toString().contains("radiology")) {
                    model.put("schedule", radiologyVisits);
                }
            }
        }

        return "vets/vetProfile";
    }


    @GetMapping("/modifySchedule/{vetId}")
    public String editVetScheduleInit(@PathVariable(name = "vetId") int vetId, Model m) {
        Vet vet = clinicService.findVetById(vetId);

        List<Schedule> selectableDays = Arrays.asList(
            new Schedule(1, "Sunday"),
            new Schedule(2, "Monday"),
            new Schedule(3, "Tuesday"),
            new Schedule(4, "Wednesday"),
            new Schedule(5, "Thursday"),
            new Schedule(6, "Friday"),
            new Schedule(7, "Saturday")
        );

        m.addAttribute("vet", vet);
        m.addAttribute("selectableDays", selectableDays);
        return "vets/modifySchedule";
    }


    @PostMapping(value = "/modifySchedule/{vetId}")
    public String saveSchedule(@RequestParam List<String> schedules, @PathVariable(name = "vetId") int vetId, Map<String, Object> m) {

        List<Schedule> scheduleList = new ArrayList<>();

        Vet vet = clinicService.findVetById(vetId);
        String[] weekDays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        for(int i=0; i<schedules.size(); i++){
            int id = Integer.parseInt(schedules.get(i));
            scheduleList.add(new Schedule(id, weekDays[id - 1]));
        }

        vet.setScheduleInternal(scheduleList);

        this.clinicService.saveVet(vet);

        Vets vets = new Vets();
        vets.getVetList().addAll(this.clinicService.findVets());

        for(int i =0; i<vets.getVetList().size(); i++){
            for(int t=0; t<vets.getVetList().get(i).getSchedules().size();t++)
                System.out.println(vets.getVetList().get(i).getSchedules().get(t).toString());
        }

        m.put("vets",vets);


        return "vets/scheduleList";
    }
    @GetMapping("/vets/available")
    @ResponseBody
    Collection<Vet> getAvailableVets(@RequestParam("dayId") int dayId) {
        return clinicService.findVetsAvailableForDay(dayId);
    }
}
