package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringJUnitWebConfig(locations = {"classpath:spring/mvc-core-config.xml", "classpath:spring/mvc-test-config.xml"})
@ExtendWith(SeleniumExtension.class)
public class AppointmentControllerTest {

    EdgeDriver driver;

    public AppointmentControllerTest(EdgeDriver driver) {
        this.driver = driver;

    }

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
    void testNavigateToAppointments() throws Exception{
        mockMvc.perform(get("/appointments/create"))
            .andExpect(status().isOk())
            .andExpect(view().name("appointments/createAppointments"))
            .andExpect(forwardedUrl("appointments/createAppointments"));
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
    void checkForCancelButton() throws Exception{

        //assert
        driver.get("http://localhost:8080/spring_framework_petclinic_war/appointments/viewForm");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement cancel= driver.findElement(By.xpath("//input[@type='submit']"));
        assertThat(cancel.isDisplayed(), is(true)) ;

    }

    @Test
    void checkNumberOfCancelButtons() throws Exception{

        driver.get("http://localhost:8080/spring_framework_petclinic_war/appointments/viewForm");
        driver.manage().window().maximize();

        int count = driver.findElements(By.xpath("//input[@type='submit']")).size();

        assertThat(count, is(4));

    }

    @Test
    void checkFirstCancelButtonIsID1() throws Exception{

        driver.get("http://localhost:8080/spring_framework_petclinic_war/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement cancel = driver.findElement(By.xpath("//input[@id='1']"));
        List cancels = driver.findElements(By.xpath("//input[@type='submit']"));
        assertThat(cancels.get(0), is(cancel));


    }


}
