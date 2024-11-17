import org.example.Pages.CareersPage;
import org.example.Pages.OpenPositionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.Pages.HomePage;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Set;


public class TestRunner {
    public WebDriver driver;
    private HomePage homePage;
    private CareersPage careersPage;
    private OpenPositionsPage openPositionsPage;
    public String url = "https://useinsider.com/";

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/escos/OneDrive/Masaüstü/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(url);

        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        openPositionsPage = new OpenPositionsPage(driver);
    }

    @Test(priority = 1)
    public void testHomePage() {
        homePage.clickAcceptCookiesBtn();
        Assert.assertTrue(homePage.checkNavBarExist(), "NavBar is not displayed!");
        homePage.navigateCareersPage();
    }

    @Test(priority = 2, dependsOnMethods = "testHomePage")
    public void testCareersPage() {
        careersPage.clickSeeAllTeamsButtonAndClick();
        Assert.assertTrue(careersPage.scrollAndCheckLocationsBlock(), "Locations block is not visible!");
        Assert.assertTrue(careersPage.scrollAndCheckLifeAtInsiderBlock(), "Life at Insider block is not visible!");
        careersPage.navigateToQAJobsPage();
        String expectedQaJobsUrl = url + "careers/quality-assurance/";
        Assert.assertEquals(driver.getCurrentUrl(), expectedQaJobsUrl, "Not redirected to QA Jobs page!");
    }

    @Test(priority = 3, dependsOnMethods = "testCareersPage")
    public void testOpenPositionsPage() throws InterruptedException {
        openPositionsPage.clickToSeeAllQaJobsButton();
        openPositionsPage.selectFromDropdown();
        Assert.assertTrue(openPositionsPage.isJobListPresent(), "Job list is not present!");
        Assert.assertTrue(openPositionsPage.checkJobCardDetails(), "Job card details are incorrect!");
        openPositionsPage.clickViewJobCard();

        String expectedJobDetailsUrl = "https://jobs.lever.co/useinsider/78ddbec0-16bf-4eab-b5a6-04facb993ddc";
        switchToNewTabAndVerifyUrl(expectedJobDetailsUrl);
    }

    public void switchToNewTabAndVerifyUrl(String expectedUrl) {
        String originalWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, expectedUrl, "Not redirected to expected job details page!");

        driver.quit();
    }
}





