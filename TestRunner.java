import org.example.Pages.CareersPage;
import org.example.Pages.OpenPositionsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.Pages.HomePage;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestRunner {
    public WebDriver driver;
    public String url = "https://useinsider.com/";

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/escos/OneDrive/Masaüstü/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://useinsider.com/");
    }

    public static void main(String[] args) throws InterruptedException {
        TestRunner testRunner = new TestRunner();
        testRunner.setUp();

        HomePage homePage = new HomePage(testRunner.driver);
        homePage.clickAcceptCookies();
        homePage.checkNavBarExist();
        homePage.clickCompanyMenu();

        CareersPage careersPage = new CareersPage(testRunner.driver);
        careersPage.scrollToSeeAllTeamsButtonAndClick();
        careersPage.scrollAndCheckLocationsBlock();
        careersPage.scrollAndCheckLifeAtInsiderBlock();
        careersPage.navigateToQAJobsPage();

        OpenPositionsPage openPositionsPage = new OpenPositionsPage(testRunner.driver);
        openPositionsPage.clickToSeeAllQaJobsButton();
        openPositionsPage.selectFromDropdown();
        openPositionsPage.isJobListPresent();
        openPositionsPage.checkJobCardDetails();
        openPositionsPage.clickJobCard();

        //testRunner.driver.quit();
        //System.out.println("Test tamamlandı.");
    }
}
