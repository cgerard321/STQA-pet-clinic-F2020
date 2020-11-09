package org.springframework.samples.petclinic.web;

import org.springframework.samples.petclinic.model.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AppointmentController {

    @GetMapping(value = "/appointments")
    public String initFindForm(Map<String, Object> model) {
        model.put("pet", new Pet());
        return "appointments/appointments";
    }
}
