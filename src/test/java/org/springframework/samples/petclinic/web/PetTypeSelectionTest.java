package org.springframework.samples.petclinic.web;
import org.springframework.samples.petclinic.service.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Array;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;


@ExtendWith(SeleniumExtension.class)

public class PetTypeSelectionTest {
    ChromeDriver driver;
    public org.springframework.samples.petclinic.service.ClinicServiceImpl ClinicServiceImpl;

    public PetTypeSelectionTest(ChromeDriver driver) {
        this.driver = driver;
    }
    @Test
    public void ConfirmPets() {
        //assert
        List<String> myOption = new ArrayList<>();
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/");
        driver.manage().window().maximize();

        //gets the select tag
        WebElement petSelect = driver.findElement(By.id("petType"));
        //get the size of the element inside the select tag
        int optionsCount = petSelect.findElements(By.tagName("option")).size();
        //create a list of web elements with the tage name option
        List<WebElement> petOptions = petSelect.findElements(By.tagName("option"));
        //Loop through the list of options
        for(int i=0;i<optionsCount;i++){
            String options = petOptions.get(i).getAttribute("value");
            //get that value of each option tag and adds it to an array
            myOption.add(options);
        }
        //Check to see if selection has more than 1 option
        assertThat(optionsCount, greaterThan(1));
        //Check to see if all option are present
        assertThat(myOption.get(0),is("all") );
        assertThat(myOption.get(1),is("bird") );
        assertThat(myOption.get(2),is("cat") );
        assertThat(myOption.get(3),is("dog") );
        assertThat(myOption.get(4),is("hamster") );
        assertThat(myOption.get(5),is("lizard") );
        assertThat(myOption.get(6),is("snake") );

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
