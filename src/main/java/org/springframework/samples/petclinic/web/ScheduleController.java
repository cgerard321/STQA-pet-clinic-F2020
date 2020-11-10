package org.springframework.samples.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.model.Schedules;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Controller
public class ScheduleController {

    private static final String SEE_ALL_SCHEDULES = "vets/scheduleList";
    private final ClinicService clinicService;

    @Autowired
    public ScheduleController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping(value = "/scheduleList")
    public String displayAllSchedules(Map<String, Object> model) {
        Schedules schedules = new Schedules();
        schedules.getScheduleList().addAll(this.clinicService.findSchedules());
        model.put("schedules", schedules);
        return SEE_ALL_SCHEDULES;
    }


    @GetMapping(value = "/displaySchedule")
    public ModelAndView getScheduleById(@ModelAttribute("schedule") Schedule schedule, BindingResult result, int vetId) throws IOException {
        schedule = this.clinicService.findScheduleByVetId(vetId);
        ModelAndView model = new ModelAndView("vets/displaySchedule");
        model.addObject("schedule", schedule);

        return model;
    }


}
