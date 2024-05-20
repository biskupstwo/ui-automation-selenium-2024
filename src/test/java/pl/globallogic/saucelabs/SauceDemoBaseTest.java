package pl.globallogic.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.globallogic.drivermanager.DriverManager;
import pl.globallogic.pages.LandingPage;

public class SauceDemoBaseTest {

    protected WebDriver driver;
    protected String host = "https://www.saucedemo.com";

    @BeforeMethod
    public void testSuiteSetUp(){
        //driver = new ChromeDriver();
        driver = DriverManager.getDriver(System.getProperty("browser"));
        ChromeOptions opt = new ChromeOptions();
        opt.addArguments("--headless");
        driver = new ChromeDriver(opt);
        driver.manage().window().maximize();
    }



    @AfterMethod
    public void testSuiteCleanUp() {
        driver.quit();
    }
}
