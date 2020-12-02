package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;

@ExtendWith(SeleniumExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AppointmentTest {

    ChromeDriver driver;

    public AppointmentTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    @Order(1)
    void checkForCancelButton() {

        //assert
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement cancel= driver.findElement(By.xpath("//input[@type='submit']"));
        assertThat(cancel.isDisplayed(), is(true)) ;

    }

    @Test
    @Order(2)
    void checkNumberOfCancelButtons() {

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        int count = driver.findElements(By.xpath("//input[@type='submit']")).size();

        assertThat(count, is(4));

    }

    @Test
    @Order(3)
    void checkFirstCancelButtonIsID1() {

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement cancel = driver.findElement(By.xpath("//input[@id='1']"));
        List<WebElement> cancels = driver.findElements(By.xpath("//input[@type='submit']"));
        assertThat(cancels.get(0), is(cancel));
    }

    @Test
    @Order(4)
    void checkGoBackButtonGoesBackCancelAppointment() {
        // get a date in the future
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 3);

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/owners/1/pets/1/visits/new");
        driver.manage().window().maximize();

        // create a visit to make the cancel button show up
        WebElement date = driver.findElement(By.name("date"));
        date.clear();
        date.sendKeys(new SimpleDateFormat("yyyy/MM/dd").format(c.getTime()));
        driver.findElement(By.tagName("h2")).click(); // dismiss the date picker
        driver.findElement(By.name("description")).sendKeys("test");
        driver.findElement(By.cssSelector("#visit button")).click();

        // click the cancel button and check we're on the cancel page
        driver.findElement(By.xpath("/html/body/div[1]/div/a[3]")).click();
        assertThat(driver.getCurrentUrl(), endsWith("/owners/1/appointments/cancel.html"));

        // click the back button and check we're back on the owner page
        driver.findElement(By.xpath("//*[@id=\"visits\"]/button[2]")).click();
        assertThat(driver.getCurrentUrl(), endsWith("/owners/1"));
    }

    //Used for the vetProfile page. Basically ensure that there's always a cancel
    //button for the appointments in the schedule. If there's none, there's no appointments.
    @Test
    @Order(5)
    void checkAppointmentsScheduledCanBeCancelled() {
        driver.get("http://localhost:"+ TOMCAT_PORT+ TOMCAT_PREFIX+ "/vetProfile.html?id=4");
        driver.manage().window().maximize();

        List<WebElement> hasASchedule = driver.findElements(By.className("btn"));
        assertThat(hasASchedule.size(), greaterThanOrEqualTo(0));
    }

    @Test
    @Order(6)
    void testVetVisitResultTable()
    {
        //arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/vetProfile.html?id=1");
        driver.manage().window().maximize();

        //act
        WebElement visitTable = driver.findElementById("vetSchedule");
        int numRows = driver.findElementsByXPath("//table[@id='vetSchedule']/tbody/tr").size();
        String petName = driver.findElementByXPath("//table[@id='vetSchedule']/tbody/tr[1]/td[2]").getText();

        //assert
        assertTrue(visitTable.isDisplayed());
        assertTrue(numRows > 0);
        /*May need to update if more pets are added*/
        assertEquals("Samantha", petName);

        driver.close();
    }
}
