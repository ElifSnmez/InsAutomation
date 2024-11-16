package org.example.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OpenPositionsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    private By seeAllQaJobsBtn = By.xpath("//a[contains(text(),'See all QA jobs')]");
    private By dropdownBox = By.xpath("//*[@id='top-filter-form']/div[1]/span");
    private By positionList = By.xpath("//*[@id='career-position-list']/div/div/div[1]");
    private By jobList = By.cssSelector("#jobs-list .position-list-item");
    private By firstJob = By.cssSelector("div.position-list-item-wrapper.bg-light");
    private By jobCardPositionTitleLocator = By.cssSelector(".position-title.font-weight-bold"); // Pozisyon başlığı
    private By jobCardDepartmentLocator = By.cssSelector(".position-department"); // Departman
    private By jobCardLocationLocator = By.cssSelector(".position-location");
    private By viewRoleButton = By.cssSelector("#jobs-list > div > div > a");


    public OpenPositionsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public void clickToSeeAllQaJobsButton() {
        WebElement qaJobsButton = wait.until(ExpectedConditions.elementToBeClickable(seeAllQaJobsBtn));
        qaJobsButton.click();
    }

    public void selectFromDropdown() throws InterruptedException {
        WebElement scrollToBrowse = wait.until(ExpectedConditions.visibilityOfElementLocated(positionList));
        actions.moveToElement(scrollToBrowse).perform();
        Thread.sleep(5000);
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownBox));
        dropdown.click();
        List<WebElement> dropdownOptions = driver.findElements(By.xpath("//ul[contains(@class, 'select2-results__options')]/li"));
        if (dropdownOptions.size() >= 2) {
            WebElement secondOption = dropdownOptions.get(1);
            secondOption.click();
        }
    }

    public boolean isJobListPresent() throws InterruptedException {
        WebElement firstJobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(firstJob));
        actions.moveToElement(firstJobCard).perform();
        List<WebElement> QAJobList = driver.findElements(jobList);
        Thread.sleep(3000);
        if (!QAJobList.isEmpty()) {
            System.out.println("Job list is present with " + QAJobList.size() + " jobs.");
            return true;
        } else {
            System.out.println("Job list is empty.");
            return false;
        }
    }

    public void checkJobCardDetails() throws InterruptedException {
        for (WebElement job : driver.findElements(firstJob)) {
            String title = job.findElement(jobCardPositionTitleLocator).getText();
            String department = job.findElement(jobCardDepartmentLocator).getText();
            String location = job.findElement(jobCardLocationLocator).getText();

            System.out.println("Position: " + title + " | Department: " + department + " | Location: " + location);

            if (!title.toLowerCase().contains("quality assurance") || !department.equals("Quality Assurance") || !location.equals("Istanbul, Turkey")) {
                System.out.println("Error: Job details do not match expected values!");
            }
        }
    }

    public void clickJobCard() throws InterruptedException {
        WebElement jobCard = wait.until(ExpectedConditions.visibilityOfElementLocated(firstJob));
        actions.moveToElement(jobCard).perform();
        WebElement viewRole = wait.until(ExpectedConditions.elementToBeClickable(viewRoleButton));
        viewRole.click();
    }





}


