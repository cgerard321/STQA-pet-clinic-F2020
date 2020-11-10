package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import org.springframework.web.bind.annotation.GetMapping;
import java.util.Map;

@Controller
public class AppointmentController {


    private static final String VIEWS_APPOINTMENTS_VIEW_FORM = "appointments/viewAppointments";
    private final ClinicService clinicService;



    private static final String APPOINT_FORM = "appointments/createAppointments";
    private final ClinicService clinicService;


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
}

    /* Controller for the booking appointment page */
    @GetMapping(value = "/appointments/create")
    public String initCreationForm(Map<String, Object> vetInfo) {
        Vets vetList = new Vets();
        vetList.getVetList().addAll(this.clinicService.findVets());
        vetInfo.put("vetAppoint", vetList);
        return APPOINT_FORM;
    }
}


