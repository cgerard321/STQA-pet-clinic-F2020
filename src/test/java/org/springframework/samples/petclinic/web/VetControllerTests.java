package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.samples.petclinic.model.Specialty;
import org.springframework.samples.petclinic.model.Vet;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.xml.HasXPath.hasXPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the {@link VetController}
 */
@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
@ExtendWith(SeleniumExtension.class)
class VetControllerTests {

    public static final int TOMCAT_PORT = 8080;
    public static final String TOMCAT_PREFIX = "/spring_framework_petclinic_war";
    FirefoxDriver driver;

    public VetControllerTests(FirefoxDriver driver) {
        this.driver = driver;
    }

    @Autowired
    private VetController vetController;

    @Autowired
    private ClinicService clinicService;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(vetController).build();

        Vet james = new Vet();
        james.setFirstName("James");
        james.setLastName("Carter");
        james.setId(1);
        Vet helen = new Vet();
        helen.setFirstName("Helen");
        helen.setLastName("Leary");
        helen.setId(2);
        Specialty radiology = new Specialty();
        radiology.setId(1);
        radiology.setName("radiology");
        helen.addSpecialty(radiology);
        given(this.clinicService.findVets()).willReturn(Lists.newArrayList(james, helen));
    }

    @Test
    void testShowVetListHtml() throws Exception {
        mockMvc.perform(get("/vets.html"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("vets"))
            .andExpect(view().name("vets/vetList"));
    }

    @Test
    void testShowResourcesVetList() throws Exception {
        ResultActions actions = mockMvc.perform(get("/vets.json").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
        actions.andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.vetList[0].id").value(1));
    }
	// yeet
    @Test
    void testShowVetListXml() throws Exception {
        mockMvc.perform(get("/vets.xml").accept(MediaType.APPLICATION_XML))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_XML_VALUE))
            .andExpect(content().node(hasXPath("/vets/vet[id=1]/id")));
    }

    @Test
    void testNavigateToVets() throws Exception {
        mockMvc.perform(get("/vets.html"))
            .andExpect(status().isOk())
            .andExpect(view().name("vets/vetList"))
            .andExpect(forwardedUrl("vets/vetList"));
    }

    @Test
    void testNavigateToVetSchedules() throws Exception {
        mockMvc.perform(get("/scheduleList"))
            .andExpect(status().isOk())
            .andExpect(view().name("vets/scheduleList"))
            .andExpect(forwardedUrl("vets/scheduleList"));
    }

    @Test
    void testShowVetScheduleList() throws Exception {
        mockMvc.perform(get("/scheduleList"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("vets"))
            .andExpect(view().name("vets/scheduleList"));
    }

    //tests for the vet profile -- appointment deletion not included
    @Test
    void testShowsVetProfile() throws Exception
    {
        mockMvc.perform(get("/vetProfile.html?id=2"))
        .andExpect(status().isOk())
        .andExpect(model().attributeExists("vet"))
        .andExpect(view().name("vets/vetProfile"));
    }

    @Test
    void testVetVisitResultTable()
    {
        //arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/vetProfile.html?id=2");
        driver.manage().window().maximize();

        //act
        WebElement visitTable = driver.findElementById("vetSchedule");
        int numRows = driver.findElementsByXPath("/table[@id='vetSchedule']/tbody/tr").size();
        String petName = driver.findElementByXPath("/table[@id='vetSchedule']/tbody/tr[0]/td[1]").getText();

        //assert
        assertTrue(visitTable.isDisplayed());
        assertTrue(numRows > 0);
        /*May need to update if more pets are added*/
        assertEquals(petName, "Samantha");
    }
}

