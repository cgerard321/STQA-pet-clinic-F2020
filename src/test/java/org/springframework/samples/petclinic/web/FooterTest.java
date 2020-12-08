package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PORT;
import static org.springframework.samples.petclinic.web.WebTestsCommon.TOMCAT_PREFIX;

// Test may break depending on TomcatPort.
@ExtendWith(SeleniumExtension.class)
public class FooterTest {
    ChromeDriver driver;

    public FooterTest(ChromeDriver driver) {
        this.driver = driver;

    }

    // SELENIUM testing for STQA 196 - Improve alert window UI
    // Initialize chrome driver

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

    // Test Cases for FIND PETS AND APPOINTMENTS TO BE ADDED once pages are implemented.
    @Test
    public void TestGoTo_Home() {
        //assert
        String linkName = "Home";
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/owners/find.html");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer']"));
        int links = footer.findElements(By.tagName("a")).size();

        //store all links of footer wihin a list
        for (int i = 0; i < links; i++) {
            List<WebElement> footlinks = footer.findElements(By.tagName("a"));
            WebElement test = footlinks.get(i);
            String testText = test.getText();

            if (testText.equals(linkName)) {
                test.click();
                break;
            }
        }

        WebElement Welcome = driver.findElement(By.xpath("//*[contains(text(),'Welcome')]"));
        assertThat(Welcome.isDisplayed(), is(true));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoTo_OwnersFooter() {
        //assert

        String linkName = "Find owners";
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer']"));
        int links = footer.findElements(By.tagName("a")).size();

        //store all links of footer wihin a list
        for (int i = 0; i < links; i++) {
            List<WebElement> footlinks = footer.findElements(By.tagName("a"));
            WebElement test = footlinks.get(i);
            String testText = test.getText();

            if (testText.equals(linkName)) {
                test.click();
                break;
            }
        }

        WebElement lastName = driver.findElement(By.id("lastName"));
        assertThat(lastName.isDisplayed(), is(true));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoTo_Veterinarians() {
        //assert
        String linkName = "Veterinarians";
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer']"));
        int links = footer.findElements(By.tagName("a")).size();

        //store all links of footer wihin a list
        for (int i = 0; i < links; i++) {
            List<WebElement> footlinks = footer.findElements(By.tagName("a"));
            WebElement test = footlinks.get(i);
            String testText = test.getText();

            if (testText.equals(linkName)) {
                test.click();
                break;
            }
        }

        WebElement veterinarians = driver.findElement(By.id("veterinarians"));
        assertThat(veterinarians.isDisplayed(), is(true));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestGoTo_Pets() {
        //assert

        String linkName = "Find Pets";
        driver.get("http://localhost:" + TOMCAT_PORT + TOMCAT_PREFIX + "/");
        driver.manage().window().maximize();

        //get list all elements within footer tag.
        WebElement footer = driver.findElement(By.xpath("//div[@class='footer']"));
        int links = footer.findElements(By.tagName("a")).size();

        //store all links of footer wihin a list
        for (int i = 0; i < links; i++) {
            List<WebElement> footlinks = footer.findElements(By.tagName("a"));
            WebElement test = footlinks.get(i);
            String testText = test.getText();

            if (testText.equals(linkName)) {
                test.click();
                break;
            }
        }

        WebElement pets = driver.findElement(By.id("pets"));
        assertThat(pets.isDisplayed(), is(true));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
