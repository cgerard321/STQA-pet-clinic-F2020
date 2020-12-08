package org.springframework.samples.petclinic.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(locations = {"classpath:spring/business-config.xml"})
@ActiveProfiles("jpa")
public class VetTests {

    private final ClinicService clinicService;

    @Autowired
    public VetTests(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @Test
    void checkGetLastMinuteSchedules(){

        Vets vet = new Vets();
        vet.getVetList().addAll(this.clinicService.findVets());
        ArrayList<Schedule> holder = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        for(Vet v : vet.getVetList())
        {
            for(Schedule sched: v.getSchedulesLastMinute())
                holder.add(sched);
        }

        switch(c.get(Calendar.DAY_OF_WEEK)){

            case 1:
                assertEquals(holder.size(), 6);
                break;
            case 2:
                assertEquals(holder.size(), 5);
                break;
            case 3:
                assertEquals(holder.size(), 6);
                break;
            case 4:
                assertEquals(holder.size(), 6);
                break;
            case 5:
                assertEquals(holder.size(), 5);
                break;
            case 6:
                assertEquals(holder.size(), 6);
                break;
            case 7:
                assertEquals(holder.size(), 5);
                break;
        }


    }


}
