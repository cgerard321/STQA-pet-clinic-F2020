package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;

import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AppointmentController {

    private static final String appointForm = "appointments/appointments";
    private final ClinicService clinicService;

    @Autowired
    public AppointmentController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/appointments")
    public String initFindForm(Map<String, Object> vetInfo) {
        Vets vetList = new Vets();
        vetList.getVetList().addAll(this.clinicService.findVets());
        vetInfo.put("vetAppoint", vetList);
        return appointForm;
    }
}

