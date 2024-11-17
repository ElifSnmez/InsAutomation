package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CareersPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    //Locators
    private By seeAllTeamsBtn = By.xpath("//*[@id='career-find-our-calling']/div/div/a");
    private By locationsBlock = By.cssSelector("#career-our-location > div > div > div");
    private By lifeAtInsiderBlock = By.cssSelector("body > div.elementor.elementor-22610 > section.elementor-section.elementor-top-section.elementor-element.elementor-element-a8e7b90.elementor-section-full_width.elementor-section-height-default.elementor-section-height-default > div > div > div");
    private By qaPanel = By.xpath("//h3[text()='Quality Assurance']");


    public CareersPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void clickSeeAllTeamsButtonAndClick() {
        WebElement seeAllTeams = wait.until(ExpectedConditions.visibilityOfElementLocated(seeAllTeamsBtn));
        actions.moveToElement(seeAllTeams).perform();
        seeAllTeams.click();
    }

    public boolean scrollAndCheckLocationsBlock() {
        try {
            WebElement locations = wait.until(ExpectedConditions.visibilityOfElementLocated(locationsBlock));
            actions.moveToElement(locations).perform();
            return locations.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean scrollAndCheckLifeAtInsiderBlock() {
        try {
            WebElement lifeAtInsider = wait.until(ExpectedConditions.visibilityOfElementLocated(lifeAtInsiderBlock));
            actions.moveToElement(lifeAtInsider).perform();
            return lifeAtInsider.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateToQAJobsPage() {
        WebElement qaLink = wait.until(ExpectedConditions.visibilityOfElementLocated(qaPanel));
        actions.moveToElement(qaLink).perform();
        qaLink.click();
    }

}
