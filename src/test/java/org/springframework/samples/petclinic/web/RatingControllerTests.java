package org.springframework.samples.petclinic.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.Rating;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class RatingControllerTests {

    private static final int TEST_PET_ID = 1;

    @Autowired
    private RatingController ratingController;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    private Rating rating;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ratingController).build();

        given(this.clinicService.findPetById(TEST_PET_ID)).willReturn(new Pet());

    }

    @Test
    void testShowRatingList() throws Exception {
        mockMvc.perform(get("/ratings"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("ratings"))
            .andExpect(view().name("ratings/ratingList"));
    }

    @Test
    void testInitNewRatingForm() throws Exception {
        mockMvc.perform(get("/ratings/new"))
            .andExpect(status().isOk())
            .andExpect(view().name("ratings/createRatingForm"));
    }

    @Test
    void testProcessNewVisitFormSuccess() throws Exception {
        mockMvc.perform(post("/ratings/new")
            .param("username", "Nick")
            .param("rating", "5")
            .param("pet", "Leo")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"));
    }


}
