package pl.globallogic.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class WaitingStrategiesExamplesTest extends ExamplesBaseTest{

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadImplicitly(){
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement landscape = driver.findElement(By.id("landscape"));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilLandscapeImageWillLoadExplicitly(){
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldFluentWaitUntilLandscapeImageWillLoad(){
        driver.get(PLAYGROUND_BASE + "/loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);
        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        Assert.assertTrue(landscape.getAttribute("src").contains("landscape"));
    }

    @Test
    public void shouldWaitUntilCalculationResultWillBeAvailable(){
        // 5 + 5 == 10
        String expectedResult = "10";
        By screenLocator = By.className("screen");
        driver.get(PLAYGROUND_BASE + "/slow-calculator.html");
        driver.findElement(By.xpath("//span[text()='5']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='5']")).click();
        driver.findElement(By.xpath("//span[text()='=']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(screenLocator, expectedResult));
        String actualResult = driver.findElement(screenLocator).getText();
        Assert.assertEquals(expectedResult, actualResult);
    }
}
