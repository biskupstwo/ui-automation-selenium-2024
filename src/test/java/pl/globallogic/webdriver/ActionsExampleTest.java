package pl.globallogic.webdriver;

import net.bytebuddy.build.BuildLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import pl.globallogic.waits.ExamplesBaseTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ActionsExampleTest extends ExamplesBaseTest {

    private static Logger log = LoggerFactory.getLogger(ActionsExampleTest.class);
    @Test
    void testScreenshotPng() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshot = ts.getScreenshotAs(OutputType.FILE);
        log.info("Screenshot created on {}", screenshot);
        Path destination = Paths.get("screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
        log.info("Screenshot moved to {}", destination);
    }

    @Test
    void testWebElementScreenshot() throws IOException{
        driver.get(PLAYGROUND_BASE + WEB_FORM);
        WebElement form = driver.findElement(By.tagName("form"));
        File screenshot = form.getScreenshotAs(OutputType.FILE);
        Path destination = Paths.get("element_screenshot.png");
        Files.move(screenshot.toPath(), destination, REPLACE_EXISTING);
    }
}
