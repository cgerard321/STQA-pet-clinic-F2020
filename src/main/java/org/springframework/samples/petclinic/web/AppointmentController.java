package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.*;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.*;


import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppointmentController {

    private final ClinicService clinicService;
    private static final String VIEWS_APPOINTMENTS_VIEW_FORM = "appointments/viewAppointments";
    private static final String LAST_MIN_VISIT = "/appointments/lastMinuteAppointments";

    @Autowired
    public AppointmentController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }


    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping(value = "/owners/{ownerId}/appointments/viewForm")
    public String initViewOwnerAppointmentForm(@PathVariable("ownerId") int ownerId, Map<String, Object> model) throws Exception {
        Collection<Visit> visits = this.clinicService.findVisitsByOwnerId(ownerId);
        model.put("visits", visits);
        model.put("showWarning", true);
        return VIEWS_APPOINTMENTS_VIEW_FORM;
    }

    @GetMapping(value = "appointments/viewForm")
    public String initViewOwnerAppointmentForm(Map<String, Object> model) {
        Collection<Visit> visits = this.clinicService.findAllVisits();
        model.put("visits", visits);
        model.put("showWarning", true);

            return VIEWS_APPOINTMENTS_VIEW_FORM;
    }

    @GetMapping(value="/appointments/viewForm/${filter}")
    public String filteredViewForm (@RequestParam String filter, Map<String, Object> model)
    {

        Collection<Visit> visits = this.clinicService.findAllVisits();

        //Set up variables for filtering
        if(filter.equals("upcoming"))
        {
            Collection<Visit> filterUpcoming = this.clinicService.findAllFutureVisits();
            model.put("filterUpcoming", filterUpcoming);
            return "redirect:/appointments/viewForm?=filter=upcoming";
        }
        else if(filter.equals("duplicate"))
        {
            Collection <Visit> filterDuplicate = this.clinicService.findAppointmentDuplicates();
            model.put("filterDuplicate", filterDuplicate);
            return  "redirect:/appointments/viewForm?=filter=duplicate";
        }
        else if(filter.equals("petappts")) {
            Collection <Visit> filterPetAppts = this.clinicService.groupPetsByAppointments();
            model.put("filterPetAppts", filterPetAppts);
            return  "redirect:/appointments/viewForm?=filter=duplicate";
        }
        else {
            model.put("filter", visits);
            return  "redirect:/appointments/viewForm";
        }
    }

    @PostMapping(value = "/appointments/{appointmentId}/cancel")
    public String initViewFormCancel(@PathVariable("appointmentId") int appointmentId, Map<String, Object> model) throws Exception{

        this.clinicService.deleteVisitById(appointmentId);
        return "redirect:/appointments/viewForm";
    }

    /*Cancel an appointment through the vet profile page*/
    @PostMapping(value="/vets/{appointmentId}/cancel")
    public String initVetFormCancel(@PathVariable("appointmentId") int appointmentId, Map<String, Object> model) throws Exception
    {
        this.clinicService.deleteVisitById(appointmentId);
        return "redirect:/vets/vetProfile";
    }

    @GetMapping(value = "/owners/*/pets/{petId}/visits/lastMin")
    public String showLastMinuteVisits(Map<String, Object> vetInfo) {
        Vets vet = new Vets();
        vet.getVetList().addAll(this.clinicService.findVets());
        vetInfo.put("vets", vet);
        return LAST_MIN_VISIT;
    }

    @PostMapping(value = "/owners/{ownerId}/pets/{petId}/visits/lastMin")
    public String processLastMinuteVisits(@RequestParam("result") int id, @PathVariable("petId") int petId)
    {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Vets vet = new Vets();
        vet.getVetList().addAll(this.clinicService.findVets());
        ArrayList<Schedule> holder = new ArrayList<>();

        for(Vet v : vet.getVetList())
        {
            for(Schedule sched: v.getSchedulesLastMinute())
                    holder.add(sched);
        }

        int chosenDay = holder.get(id-1).getId();

        LocalDate visitDate = LocalDate.now();
        LocalDate newDate = LocalDate.now();

        if(chosenDay+1 == calendar.get(Calendar.DAY_OF_WEEK))
        {
            visitDate = LocalDate.now();
        }
        else if((chosenDay+1) - calendar.get(Calendar.DAY_OF_WEEK) == 1)
            visitDate = newDate.plusDays(1);
        else
            visitDate = newDate.plusDays(2);

/*Possible bug - Sunday exception case not implemented yet*/

        Pet p = this.clinicService.findPetById(petId);

        Visit v = new Visit();
        v.setDate(visitDate);
        v.setDescription("Last minute appointment.");
        v.setPet(p);

        this.clinicService.saveVisit(v);
        return "redirect:/owners/{ownerId}";
    }
}


