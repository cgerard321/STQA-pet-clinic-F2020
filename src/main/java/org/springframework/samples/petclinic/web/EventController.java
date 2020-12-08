package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Event;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.ArrayList;

@Controller
public class EventController {

    private final ClinicService clinicService;

    @Autowired
    public EventController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "event/getAllEvents")
    @ResponseBody
    public String[] getAllEvents() {
        LocalDate localDate = LocalDate.now();

        ArrayList<Event> events = new ArrayList<>(this.clinicService.getEvents(localDate.getYear(), localDate.getMonthValue()));

        String[] stringEvents = new String[events.size()];

        for(int i = 0; i < events.size(); i++) {
            stringEvents[i] = events.get(i).toJsonString();
        }
        return stringEvents;
    }
}
