package pl.globallogic.saucelabs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class SauceDemoBaseTest {

    protected WebDriver driver;
    protected String host = "https://www.saucedemo.com";

    @BeforeMethod
    public void testSuiteSetUp(){
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void testSuiteCleanUp() {
        driver.quit();
    }
}
