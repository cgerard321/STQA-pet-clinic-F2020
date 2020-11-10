package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Schedule;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class ScheduleControllerTests {

    private static final int TEST_VET_ID = 1;

    @Autowired
    private ScheduleController scheduleController;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    private Schedule schedule;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(scheduleController).build();

        schedule = new Schedule();
        schedule.setVetId(TEST_VET_ID);
        schedule.setDayAvailable(2);


        given(this.clinicService.findScheduleByVetId(TEST_VET_ID)).willReturn(schedule);

    }

    @Test
    void testShowScheduleList() throws Exception {
        mockMvc.perform(get("/scheduleList"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("schedules"))
            .andExpect(view().name("vets/scheduleList"));
    }

    @Test
    void testDisplayScheduleById() throws Exception {
        given(this.clinicService.findScheduleByVetId(schedule.getVetId())).willReturn(schedule);

        mockMvc.perform(get("/displaySchedule?vetId=2")
            .param("dayAvailable", "2")
        )
            .andExpect(view().name("vets/displaySchedule"));
    }

}
