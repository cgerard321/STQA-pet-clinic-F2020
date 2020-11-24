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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Collection;

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

        // Stub new pet for a pet list
        Pet pet1 = new Pet();
        pet1.setId(1);
        pet1.setName("Test1");

        Pet pet2 = new Pet();
        pet2.setId(2);
        pet2.setName("Test2");

        // Add stubbed pets to list
        Collection<Pet> petList = new ArrayList<>();
        petList.add(pet1);
        petList.add(pet2);

        given(this.clinicService.findPetTypes()).willReturn(Lists.newArrayList(cat));
        given(this.clinicService.findOwnerById(TEST_OWNER_ID)).willReturn(new Owner());
        given(this.clinicService.findPetById(TEST_PET_ID)).willReturn(new Pet());
        // Return stubbed petList
        given(this.clinicService.findPets()).willReturn(petList);
    }

//    @Test
//    void testNavigateToFindPets() throws Exception{
//        mockMvc.perform(get("/pets/find.html"))
////            .andExpect(status().isOk())
//            .andExpect(view().name("pets/findPets"))
//            .andExpect(forwardedUrl("pets/findPets"));
//    }

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
    void testPetRemovedFromListRedirectSuccess() throws Exception {
       mockMvc.perform(post("/pets/1/remove")) //This is the page that calls my remove method "1" stands for the pet id
//            .andExpect(status().isOk())
           .andExpect(MockMvcResultMatchers.redirectedUrl("/pets/find"));
    }

    @Test
    void testListAllPetsDisplaySuccess() throws Exception {
        mockMvc.perform(get("/pets/petList")) // Navigate to the page
            .andExpect(status().isOk()) // Make sure the status is ok
            .andExpect(view().name("pets/petList")); // Check if controller handle correctly
    }
    @Test
    void testInitViewPetSuccess() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/view", TEST_OWNER_ID, TEST_PET_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/petDetails"));
    }
    @Test
    void testInitViewPet2Success() throws Exception {
        mockMvc.perform(post("/pets/{petId}/view", TEST_PET_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("pets/petDetails"));
    }

    @Test
    void testListAllPetsCorrectInfo() throws Exception {
        mockMvc.perform(get("/pets/petList")) // Navigate to the page
            .andExpect(status().isOk()) // Make sure the status is ok
            .andExpect(model().attributeExists("selections")) // Check if the model have the pet list called "selections"
            .andExpect(model().attribute("selections", hasSize(2))) // Check if pet list is size 2
            .andExpect(model().attribute("selections", contains( // Check if the pet list contains correct information
                hasProperty("id", equalTo(1)),
                any(Pet.class)
            )));
    }
}
