package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.instanceOf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
public class AppointmentControllerTest {

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
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/appointments/create.html"))
            .andExpect(status().isOk())
            .andExpect(model().attribute("vetAppoint", instanceOf(Vets.class)))
            .andExpect(view().name("appointments/createAppointments"))
            .andExpect(forwardedUrl("appointments/createAppointments"));
    }

    @Test
    void testViewAllAppointments() throws Exception {
        mockMvc.perform(get("/appointments/viewForm"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visits"))
            .andExpect(view().name("appointments/viewAppointments"));

    }

    @Test
    void testViewOwnerAppointments() throws Exception {
        mockMvc.perform(get("/owners/6/appointments/viewForm"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("visits"));
    }
}
