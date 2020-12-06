package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    void checkForCancelButton() {

        //assert
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement cancel= driver.findElement(By.xpath("//input[@type='submit']"));
        assertThat(cancel.isDisplayed(), is(true)) ;

    }

    @Test
    void checkNumberOfCancelButtons() {

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        int count = driver.findElements(By.xpath("//input[@type='submit']")).size();

        assertThat(count, is(6));

    }

    @Test
    void checkFirstCancelButtonIsID1() {

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement cancel = driver.findElement(By.xpath("//input[@id='1']"));
        List<WebElement> cancels = driver.findElements(By.xpath("//input[@type='submit']"));
        assertThat(cancels.get(0), is(cancel));
    }

    @Test
    void checkGoBackButtonGoesBackCancelAppointment() {
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/owners/6");
        driver.manage().window().maximize();

        // click the cancel button and check we're on the cancel page
        driver.findElement(By.xpath("/html/body/div[1]/div/a[3]")).click();
        assertThat(driver.getCurrentUrl(), endsWith("/owners/6/appointments/cancel.html"));

        // click the back button and check we're back on the owner page
        driver.findElement(By.xpath("//*[@id=\"visits\"]/button[2]")).click();
        assertThat(driver.getCurrentUrl(), endsWith("/owners/6"));
    }

    //Used for the vetProfile page. Basically ensure that there's always a cancel
    //button for the appointments in the schedule. If there's none, there's no appointments.
    @Test
    void checkAppointmentsScheduledCanBeCancelled() {
        driver.get("http://localhost:"+ TOMCAT_PORT+ TOMCAT_PREFIX+ "/vetProfile.html?id=4");
        driver.manage().window().maximize();

        List<WebElement> hasASchedule = driver.findElements(By.className("btn"));
        assertThat(hasASchedule.size(), greaterThanOrEqualTo(0));
    }

    @Test
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

    @Test
    void checkOwnerAppointmentsTableExists() {
        driver.get("http://localhost:"+TOMCAT_PORT + TOMCAT_PREFIX+"owners/6/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement table = driver.findElement(By.xpath("//table"));
        assertThat(table.isDisplayed(), is(true));
    }

    @Test
    void checkOwnerAppointmentsRowsCount() {
        driver.get("http://localhost:"+TOMCAT_PORT + TOMCAT_PREFIX+"/owners/10/appointments/viewForm");
        driver.manage().window().maximize();

        int count = driver.findElements(By.xpath("//tr")).size();

        assertThat(count, is(3));

    }

    @Test
    void checkAppointmentCancelConfirmation(){

        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/appointments/viewForm");
        driver.manage().window().maximize();

        WebElement cancel = driver.findElement(By.xpath("//input[@id='1']"));
        cancel.click();

        boolean foundAlert = false;
        WebDriverWait wait = new WebDriverWait(driver, 0 /*timeout in seconds*/);
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            foundAlert = true;
        } catch (TimeoutException eTO) {
            foundAlert = false;
        }

        assertThat(foundAlert, is(true));



    }
}
