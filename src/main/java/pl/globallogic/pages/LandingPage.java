package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LandingPage {
    private final WebDriver driver;
    private final String host;
    private WebDriverWait wait;

    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");

    private final String IS_USER_LOGGED_IN_SIGN = "inventory.html";

    public LandingPage(WebDriver driver, String host) {
        this.driver = driver;
        this.host = host;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void visit() {
        driver.get(host);
    }

    public void loginWith(String username, String password) {
        findBy(usernameFieldLocator).sendKeys(username);
        findBy(passwordFieldLocator).sendKeys(password);
        findBy(loginButtonLocator).click();
    }

    public boolean isUserLoggedInSuccessfully() {
        return driver.getCurrentUrl().contains(IS_USER_LOGGED_IN_SIGN);
    }

    private WebElement findBy(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
