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
    private By companyMenu = By.xpath("//a[contains(text(),'Company')]");
    private By companySubMenu = By.cssSelector(".new-menu-dropdown-layout-6-mid-container");
    private By careersLink = By.cssSelector("#navbarNavDropdown > ul:nth-child(1) > li.nav-item.dropdown.show > div > div.new-menu-dropdown-layout-6-mid-container > a:nth-child(2)");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickAcceptCookies() {
        driver.findElement(acceptCookies).click();
    }

    public void checkNavBarExist() {
        if (driver.findElement(navbar).isDisplayed()) {
            System.out.println("NavBar Exist.");
        } else {
            System.out.println("NavBar Not Found.");
        }
    }

    public void clickCompanyMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement companyMenuElement = wait.until(ExpectedConditions.elementToBeClickable(companyMenu));
        companyMenuElement.click();
        WebElement submenu = wait.until(ExpectedConditions.visibilityOfElementLocated(companySubMenu));
        WebElement careers = wait.until(ExpectedConditions.elementToBeClickable(careersLink));
        careers.click();

    }
}
