package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
<<<<<<< HEAD

=======
>>>>>>> 8dc0a75d5025a4da00872d94b1d8f6b02b66bb7c
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.samples.petclinic.model.Owner;


import org.springframework.samples.petclinic.model.Visit;
<<<<<<< HEAD


import org.springframework.samples.petclinic.model.Visit;

import org.springframework.samples.petclinic.model.Pet;


=======
import org.springframework.samples.petclinic.model.Pet;
>>>>>>> 8dc0a75d5025a4da00872d94b1d8f6b02b66bb7c
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.ResourceUtils;
import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.util.HashMap;

<<<<<<< HEAD

=======
>>>>>>> 8dc0a75d5025a4da00872d94b1d8f6b02b66bb7c
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for {@link OwnerController}
 *
 *
 *
 * @author Colin But
 */

@ExtendWith(SeleniumExtension.class)
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-test-config.xml", "classpath:spring/mvc-core-config.xml"})
class OwnerControllerTests {

    private static final int TEST_OWNER_ID = 1;
    private static final int TEST_OWNER_ID2 = 2;

    // SELENIUM testing for STQA 196 - Improve alert window UI
    // Initialize chrome driver
    ChromeDriver driver;
    public OwnerControllerTests (ChromeDriver driver){
        this.driver = driver;
    }

    // SELENIUM STQA-196
    // Improve Alert Window UI testing
    @Test
    public void TestRemoveOwnerSelenium() {
        //arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/owners/1.html");
        driver.manage().window().maximize();

        // act
        WebElement test = driver.findElementById("removeOwnerID");
        System.out.println(test.getText());
        driver.findElementById("removeOwnerID").click();

        // add 2 seconds for the browser to catch up, otherwise the test will fail
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElementById("delete").click();

        // assert
        String URL = driver.getCurrentUrl();
        assertThat(URL, is("http://localhost:8080/spring_framework_petclinic_war/owners/1/remove.html" ));
    }


    @Autowired
    private OwnerController ownerController;

    @Autowired
    private ClinicService clinicService;

    @Qualifier("servletContext")
    @Autowired
    ServletContext context;

    private MockMvc mockMvc;

    private Owner george;
    private Owner jav;


    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();

        george = new Owner();
        // STQA 74 - REMOVE OWNER
        // add dependency to the owner
        // initialize the pet
        Pet pet = new Pet();
        // add pet to the owner
        george.addPet(pet);

        george.setId(TEST_OWNER_ID);
        george.setProfile_picture("images (1)");
        george.setFirstName("George");
        george.setLastName("Franklin");
        george.setAddress("110 W. Liberty St.");
        george.setCity("Madison");
        george.setState("NY");
        george.setTelephone("6085551023");
        george.setEmail("george.franklin@gmail.com");
        george.setComment("This owner is hard of hearing");
        given(this.clinicService.findOwnerById(TEST_OWNER_ID)).willReturn(george);


        // STQA 74 - REMOVE OWNER
        // Owner without dependency
        jav = new Owner();

        jav.setId(TEST_OWNER_ID2);
        jav.setFirstName("George");
        jav.setLastName("Franklin");
        jav.setAddress("110 W. Liberty St.");
        jav.setCity("Madison");
        jav.setState("NY");
        jav.setTelephone("6085551023");
        jav.setEmail("george.franklin@gmail.com");
        jav.setComment("This owner is hard of hearing");
        given(this.clinicService.findOwnerById(TEST_OWNER_ID2)).willReturn(jav);


    }

    @Test
    void testInitCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("owner"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testProcessCreationFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/new")
            .param("profile_picture", "images (1)")
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("address", "123 Caramel Street")
            .param("city", "London")
            .param("state", "NY")
            .param("telephone", "0131676163")
            .param("email", "george.franklin@gmail.com")
            .param("comment", "This owner is hard of hearing")
        )
            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testProcessCreationFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/new")
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("city", "London")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("owner"))
            .andExpect(model().attributeHasFieldErrors("owner", "address"))
            .andExpect(model().attributeHasFieldErrors("owner", "state"))
            .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(model().attributeHasFieldErrors("owner", "email"))
            .andExpect(model().attributeHasFieldErrors("owner", "profile_picture"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testInitFindForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("owner"))
            .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testProcessFindFormSuccess() throws Exception {
        given(this.clinicService.findOwnerByLastName("")).willReturn(Lists.newArrayList(george, new Owner()));

        mockMvc.perform(get("/owners"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testProcessFindFormByLastName() throws Exception {
        given(this.clinicService.findOwnerByLastName(george.getLastName())).willReturn(Lists.newArrayList(george));

        mockMvc.perform(get("/owners")
            .param("lastName", "Franklin")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/" + TEST_OWNER_ID));
    }

    @Test
    void testProcessFindFormNoOwnersFound() throws Exception {
        mockMvc.perform(get("/owners")
            .param("lastName", "Unknown Surname")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasFieldErrors("owner", "lastName"))
            .andExpect(model().attributeHasFieldErrorCode("owner", "lastName", "notFound"))
            .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void testInitUpdateOwnerForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/edit", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("owner"))
            .andExpect(model().attribute("owner", hasProperty("profile_picture", is("images (1)"))))
            .andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
            .andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
            .andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
            .andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
            .andExpect(model().attribute("owner", hasProperty("state", is("NY"))))
            .andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
            .andExpect(model().attribute("owner", hasProperty("email", is("george.franklin@gmail.com"))))
            .andExpect(model().attribute("owner", hasProperty("comment", is("This owner is hard of hearing"))))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testProcessUpdateOwnerFormSuccess() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID)
            .param("profile_picture", "images (1)")
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("address", "123 Caramel Street")
            .param("city", "London")
            .param("state", "NY")
            .param("telephone", "0161629158")
            .param("email", "joe.bloggs@gmail.com")
            .param("comment", "This owner is hard of hearing")
        )
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/owners/{ownerId}"));
    }

    @Test
    void testProcessUpdateOwnerFormHasErrors() throws Exception {
        mockMvc.perform(post("/owners/{ownerId}/edit", TEST_OWNER_ID)
            .param("firstName", "Joe")
            .param("lastName", "Bloggs")
            .param("city", "London")
        )
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("owner"))
            .andExpect(model().attributeHasFieldErrors("owner", "profile_picture"))
            .andExpect(model().attributeHasFieldErrors("owner", "address"))
            .andExpect(model().attributeHasFieldErrors("owner", "state"))
            .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(model().attributeHasFieldErrors("owner", "email"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

    @Test
    void testShowOwner() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(model().attribute("owner", hasProperty("profile_picture", is("images (1)"))))
            .andExpect(model().attribute("owner", hasProperty("lastName", is("Franklin"))))
            .andExpect(model().attribute("owner", hasProperty("firstName", is("George"))))
            .andExpect(model().attribute("owner", hasProperty("address", is("110 W. Liberty St."))))
            .andExpect(model().attribute("owner", hasProperty("city", is("Madison"))))
            .andExpect(model().attribute("owner", hasProperty("state", is("NY"))))
            .andExpect(model().attribute("owner", hasProperty("telephone", is("6085551023"))))
            .andExpect(model().attribute("owner", hasProperty("email", is("george.franklin@gmail.com"))))
            .andExpect(model().attribute("owner", hasProperty("comment", is("This owner is hard of hearing"))))
            .andExpect(model().attribute("hasFutureVisits", is(false)))
            .andExpect(view().name("owners/ownerDetails"));
    }

    @Test
    void testNavigateToFindOwners() throws Exception {
        mockMvc.perform(get("/owners/find.html"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/findOwners"))
            .andExpect(forwardedUrl("owners/findOwners"));
    }

    @Test
    void testInitCancelOwnerAppointmentForm() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/appointments/cancel", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(model().attribute("visits", empty()))
            .andExpect(model().attribute("showWarning", instanceOf(boolean.class)))
            .andExpect(view().name("appointments/cancelAppointment"));
    }

    @Test
    void testProcessCancelOwnerAppointmentForm() throws Exception {
        Visit visit1 = new Visit();
        visit1.setId(1);
        Visit visit3 = new Visit();
        visit3.setId(3);

        given(this.clinicService.findVisitsByOwnerId(TEST_OWNER_ID)).willReturn(Lists.newArrayList(visit1, visit3));

        mockMvc.perform(post("/owners/{ownerId}/appointments/cancel", TEST_OWNER_ID)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
            .param("1", "on")
            .param("2", "on")
            .param("3", "off")
        )
            .andExpect(status().is3xxRedirection());

        then(clinicService).should().deleteVisitsById(Lists.newArrayList(1));
    }
    // We must comment this test out until I have figured out a way to enable multipart support.
    //@Test
    void testAddMultipleOwners_SendFileSuccessful() throws Exception {

        final String GOOD_FILE_PATH = System.getProperty("user.dir") + "/src/main/resources/uploads/success.json";

        FileInputStream fs = new FileInputStream(GOOD_FILE_PATH);
        MockMultipartFile file = new MockMultipartFile("file", fs);

        HashMap<String, String> mediaTypeParams = new HashMap<>();
        mediaTypeParams.put("boundary", "265001916915724");

        MediaType mediaType = new MediaType("multipart", "form-data", mediaTypeParams);

        mockMvc.perform(post("/owners/addMultipleOwners")
                            .content(file.getBytes())
                            .contentType(mediaType))
                            .andExpect(status().is3xxRedirection());
    }

    @Test
    void testAddMultipleOwnersFake_UseExistingFileSuccessful() throws Exception {
        final String GOOD_FILE_PATH = ResourceUtils.getFile("classpath:uploads/success.json").getPath();

        mockMvc.perform(post("/owners/addMultipleOwnersFake")).andExpect(status().is3xxRedirection());
    }

    @Test
    void testProcessNewOwnerFormHasErrorMessage() throws Exception {
        mockMvc.perform(post("/owners/new")
            .param("firstName", "12124")
            .param("lastName", "anr@")
            .param("address", "48 Theberge")
            .param("city", "1442.")
            .param("state", "1234")
            .param("telephone", "514229178a")
            .param("email", "antoine.heboutlook")
        )
            //This test is supposed to pass if error messages are been displayed on screen when the owner name does not contain only letter
            .andExpect(status().isOk())
            .andExpect(model().attributeHasErrors("owner"))
            .andExpect(model().attributeHasFieldErrors("owner", "firstName"))
            .andExpect(model().attributeHasFieldErrors("owner", "lastName"))
            .andExpect(model().attributeHasFieldErrors("owner", "address"))
            .andExpect(model().attributeHasFieldErrors("owner", "city"))
            .andExpect(model().attributeHasFieldErrors("owner", "state"))
            .andExpect(model().attributeHasFieldErrors("owner", "telephone"))
            .andExpect(model().attributeHasFieldErrors("owner", "email"))
            .andExpect(view().name("owners/createOrUpdateOwnerForm"));
    }

<<<<<<< HEAD
=======
    // STQA 74 - REMOVE OWNER
    // try to remove owner with dependency
    // since there's a dependency, it should go to removeOwner.jsp
    @Test
    public void testOwnerHasDependency() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/remove", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/removeOwner"))
            .andExpect(forwardedUrl("owners/removeOwner"));
    }

    // STQA 74 - REMOVE OWNER
    // remove owner without dependency

    @Test
    public void testRemoveOwnerNoDependency() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/remove", TEST_OWNER_ID2))
            .andExpect(view().name("redirect:/owners.html?lastName="));
    }
>>>>>>> 8dc0a75d5025a4da00872d94b1d8f6b02b66bb7c

    //test added by Antoine
    @Test
    void testProcessFindAllOwnerForm() throws Exception {
        //this get method should retrieve all owners and display the list
        mockMvc.perform(get("/findAll"))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/ownersList"));
    }

    // STQA 74 - REMOVE OWNER
    // try to remove owner with dependency
    // since there's a dependency, it should go to removeOwner.jsp
    @Test
    public void testOwnerHasDependency() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/remove", TEST_OWNER_ID))
            .andExpect(status().isOk())
            .andExpect(view().name("owners/removeOwner"))
            .andExpect(forwardedUrl("owners/removeOwner"));
    }


    // STQA 74 - REMOVE OWNER
    // remove owner without dependency

    @Test
    public void testRemoveOwnerNoDependency() throws Exception {
        mockMvc.perform(get("/owners/{ownerId}/remove", TEST_OWNER_ID2))
            .andExpect(view().name("redirect:/owners.html?lastName="));
    }

}
