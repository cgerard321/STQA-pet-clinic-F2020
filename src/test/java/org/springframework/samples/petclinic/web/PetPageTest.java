package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;

@ExtendWith(SeleniumExtension.class)
public class PetPageTest {

    ChromeDriver driver;

    public PetPageTest(ChromeDriver driver) {
        this.driver = driver;
    }

    @Test
    void shouldGoToEdit() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        driver.findElement(By.xpath("//a[contains(@href, '/owners/5/pets/6/edit')]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.findElementByXPath("//input[@value='George']").isDisplayed(), is(true));
    }

    @Test
    void shouldGoToDetailView() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        driver.findElement(By.xpath("//a[contains(text(), 'Iggy')]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.findElement(By.xpath("//strong[contains(text(), 'Iggy')]")).isDisplayed(), is(true));
        assertThat(driver.findElement(By.xpath("//strong[contains(text(), 'Harold Davis')]")).isDisplayed(), is(true));
        assertThat(driver.findElement(By.xpath("//strong[contains(text(), 'lizard')]")).isDisplayed(), is(true));
    }

    @Test
    void shouldDeletePet() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        driver.findElement(By.xpath("//button[@value=5]")).click();
        try {
            Thread.sleep(1000);
            driver.switchTo().alert().accept();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.getCurrentUrl(), endsWith("/pets/find"));
        // Revert
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/owners/4/pets/new.html");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElementByName("name").sendKeys("Iggy");
        driver.findElementByName("birthDate").sendKeys("2010/11/30");
        driver.findElementByName("birthDate").sendKeys(Keys.TAB);
        Select select = new Select(driver.findElementByName("type"));
        select.selectByVisibleText("lizard");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Test
    void shouldShowOwner() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        driver.findElement(By.xpath("//a[contains(text(), 'Jeff Black')]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.findElementByXPath("//strong[contains(text(), 'Jeff Black')]").isDisplayed(), is(true));
        assertThat(driver.findElementByXPath("//td[@headers='email']").getText(), is("jeff.black@gmail.com"));
        assertThat(driver.getCurrentUrl(), endsWith("/owners/7.html"));
    }

    @Test
    void shouldShowToolTip() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        new Actions(driver).moveToElement(driver.findElement(By.xpath("//a[contains(text(), 'Jeff Black')]"))).build().perform();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.findElementByXPath("//div[@role='tooltip']").isDisplayed(), is(true));
    }

    @Test
    void shouldSearchCorrectPet() {
        // Arrange
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/pets/find.html");
        driver.manage().window().maximize();

        // Act & Assert
        driver.findElementById("searchFilter").sendKeys("Mul");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertThat(driver.findElementByXPath("//a[contains(text(), 'Mulligan')]").isDisplayed(), is(true));
    }
}
