package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;

@ExtendWith(SeleniumExtension.class)
public class AppointmentTest {

    ChromeDriver driver;

    public AppointmentTest(ChromeDriver driver) {
        this.driver = driver;

    }

    @Test
    void checkForCancelButton() throws Exception{

        //assert
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement cancel= driver.findElement(By.xpath("//input[@type='submit']"));
        assertThat(cancel.isDisplayed(), is(true)) ;

    }

    @Test
    void checkNumberOfCancelButtons() throws Exception{

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        int count = driver.findElements(By.xpath("//input[@type='submit']")).size();

        assertThat(count, is(4));

    }

    @Test
    void checkFirstCancelButtonIsID1() throws Exception{

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement cancel = driver.findElement(By.xpath("//input[@id='1']"));
        List cancels = driver.findElements(By.xpath("//input[@type='submit']"));
        assertThat(cancels.get(0), is(cancel));


    }


}
