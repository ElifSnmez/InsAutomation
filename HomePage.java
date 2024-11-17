package org.example.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;


    // Locators
    private By acceptCookies = By.id("wt-cli-accept-all-btn");
    private By navbar = By.id("navbarNavDropdown");
    private By companyBtn = By.xpath("//a[contains(text(),'Company')]");
    private By careersOption = By.cssSelector("#navbarNavDropdown > ul:nth-child(1) > li.nav-item.dropdown.show > div > div.new-menu-dropdown-layout-6-mid-container > a:nth-child(2)");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAcceptCookiesBtn() {
        driver.findElement(acceptCookies).click();
    }

    public boolean checkNavBarExist() {
        try {
            WebElement navbarElement = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(navbar));
            return navbarElement.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void navigateCareersPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement clickCompanyMenuElement = wait.until(ExpectedConditions.elementToBeClickable(companyBtn));
        clickCompanyMenuElement.click();
        WebElement careers = wait.until(ExpectedConditions.elementToBeClickable(careersOption));
        careers.click();
    }

}
