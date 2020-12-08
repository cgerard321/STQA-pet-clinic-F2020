package org.springframework.samples.petclinic.model;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;

@ExtendWith(SeleniumExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringJUnitConfig(locations = {"classpath:spring/business-config.xml"})
@ActiveProfiles("jpa")

public class VetTest {

    private final ClinicService clinicService;

    @Autowired
    public VetTest(ClinicService clinicService) {
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
