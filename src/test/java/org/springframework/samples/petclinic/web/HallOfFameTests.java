package org.springframework.samples.petclinic.web;

import io.github.bonigarcia.seljup.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ExtendWith(SeleniumExtension.class)
public class HallOfFameTests {

    ChromeDriver driver;

    // Test Class Created by Simon St-Andre
    // This class is to test the hall of fame and some of its functionalities

    public HallOfFameTests(ChromeDriver driver){
        this.driver = driver;
    }

    // method to check that the card displays information
    // Name of the displayed information should be the same as the one displayed in the title
    @Test
    void test_view_pet_info_in_ranking() throws Exception{
        // Arrange
        driver.get("http://localhost:8080/spring_framework_petclinic_war/");
        driver.manage().window().maximize();

        // Act
//        driver.findElementById("hallOfFame").click();

        WebElement elemetn = driver.findElement(By.id("HOF1Name"));
        String firstPetName = driver.findElement(By.id("HOF1Name")).getAttribute("innerHTML");
        firstPetName = firstPetName.split(" ")[0];
//        WebElement hoverElement = driver.findElement(By.className("card"));

        String firstPetNameInfo = driver.findElement(By.id("Name1")).getAttribute("innerHTML");

        // Assert
        assertThat(firstPetName, is(firstPetNameInfo));
    }
}
