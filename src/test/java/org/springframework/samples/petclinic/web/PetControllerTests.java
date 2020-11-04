package org.springframework.samples.petclinic.web;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link PetController}
 *
 * @author Colin But
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
class PetControllerTests {

    private static final int TEST_OWNER_ID = 1;
    private static final int TEST_PET_ID = 1;

    @Autowired
    private PetController petController;

    @Autowired
    private FormattingConversionServiceFactoryBean formattingConversionServiceFactoryBean;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(petController)
            .setConversionService(formattingConversionServiceFactoryBean.getObject())
            .build();

        PetType cat = new PetType();
        cat.setId(3);
        cat.setName("hamster");

        Pet pet1 = new Pet();
        pet1.setId(1);
        pet1.setName("Test1");

        Pet pet2 = new Pet();
        pet2.setId(2);
        pet2.setName("Test2");

        Collection<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);

        given(this.clinicService.findPetTypes()).willReturn(Lists.newArrayList(cat));
        given(this.clinicService.findOwnerById(TEST_OWNER_ID)).willReturn(new Owner());
        given(this.clinicService.findPetById(TEST_PET_ID)).willReturn(new Pet());
        given(this.clinicService.findPetById()).willReturn(petList);
    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/new", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdatePetForm"))
            .andExpect(model().attributeExists("pet"));
    }

    @Test
    void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/new", TEST_OWNER_ID)
            .param("name", "Betty")
            .param("type", "hamster")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
            .param("name", "Betty")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(model().attributeHasNoErrors("owner"))
            .andExpect(model().attributeHasErrors("pet"))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void testInitUpdateForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("pet"))
            .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void testProcessUpdateFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
            .param("name", "Betty")
            .param("type", "hamster")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void testProcessUpdateFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
            .param("name", "Betty")
            .param("birthDate", "2015/02/12")
        )
            .andExpect(model().attributeHasNoErrors("owner"))
            .andExpect(model().attributeHasErrors("pet"))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/createOrUpdatePetForm"));
    }

    @Test
    void testListAllPetsDisplaySuccess() throws Exception {
        mockMvc.perform(get("/pets"))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/petList"));
    }

    @Test
    void testListAllPetsCorrectInfo() throws Exception {
        Pet pet1Expected = new Pet();
        pet1Expected.setId(1);
        pet1Expected.setName("Test1");

        Pet pet2Expected = new Pet();
        pet2Expected.setId(2);
        pet2Expected.setName("Test2");

        List<Pet> expectedList = new ArrayList<>();
        expectedList.add(pet1Expected);
        expectedList.add(pet2Expected);

        mockMvc.perform(get("/pets"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("selections"))
            .andExpect(model().attribute("selections", hasSize(2)))
            .andExpect(model().attribute("selections", contains(
                hasProperty("id", equalTo(1)),
                any(Pet.class)
            )));
    }

}
