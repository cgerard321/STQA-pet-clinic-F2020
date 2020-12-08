package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
public class AppointmentControllerTests {

    @Autowired
    private AppointmentController appointmentController;

    @Autowired
    private FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(appointmentController)
            .setConversionService(formattingConversionServiceFactoryBean.getObject())
            .build();

        Collection<Visit> visitList = new ArrayList<>();
        Visit visit = new Visit();
        visit.setDate(LocalDate.now());

        Pet pet = new Pet();
        pet.setName("Test");

        visit.setPet(pet);
        visit.setDescription("test description");
        visitList.add(visit);

        given(this.clinicService.findAllVisits()).willReturn(visitList);
    }

    @Test
    void testViewAllAppointments() throws Exception {
        mockMvc.perform(get("/appointments/viewForm"))
            .andExpect(status().isOk())
            .andExpect(view().name("appointments/viewAppointments"))
            .andExpect(model().attributeExists("visits"));

    }

    @Test
    void testViewOwnerAppointments() throws Exception {
        mockMvc.perform(get("/owners/6/appointments/viewForm"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visits"));

    }

    @Test
    void testOwnerAppointmentsPage() throws Exception{
        mockMvc.perform(get("/owners/6/appointments/viewForm"))
            .andExpect(status().isOk());
    }


    @Test
    void testOwnerAppointmentsPageRedirect() throws Exception{
        mockMvc.perform(get("/owners/6/appointments/viewForm"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visits"));
    }

    @Test // Ryan
    void testGetAllVisits() throws Exception {
        mockMvc.perform(get("/appointments/getAllAppointments"))
            .andExpect(status().isOk())
            .andExpect(content().string(startsWith("[")))
            .andExpect(content().string(containsString("date")))
            .andExpect(content().string(containsString("petName")))
            .andExpect(content().string(containsString("description")))
            .andExpect(content().string(endsWith("]")));
    }
}

