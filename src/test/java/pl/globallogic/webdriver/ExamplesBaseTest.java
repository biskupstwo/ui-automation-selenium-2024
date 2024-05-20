package pl.globallogic.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class ExamplesBaseTest {
    protected WebDriver driver;
    protected final String PLAYGROUND_BASE = "https://bonigarcia.dev/selenium-webdriver-java/";
    protected final String WEB_FORM = "web-form.html";
    @BeforeClass
    public void setUp(){
        driver = new ChromeDriver();

    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

}
