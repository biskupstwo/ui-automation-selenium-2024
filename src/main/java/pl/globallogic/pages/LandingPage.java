package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;

public class LandingPage extends BasePage{
    private final String host;
    private WebDriverWait wait;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected int timeout = 10;
    private Predicate<WebDriver> loginCriteria = webDriver -> {
        return webDriver.getCurrentUrl().contains(IS_USER_LOGGED_IN_SIGN);
    };

    public void setLoginCriteria(Predicate<WebDriver> loginCriteria) {
        this.loginCriteria = loginCriteria;
    }

    private final By usernameFieldLocator = By.id("user-name");
    private final By passwordFieldLocator = By.id("password");
    private final By loginButtonLocator = By.id("login-button");

    private static final String IS_USER_LOGGED_IN_SIGN = "inventory.html";

    public LandingPage(WebDriver driver, String host) {
        super(driver);
        this.host = host;
    }


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void visit() {
        super.visit(this.host);
    }

    public void loginWith(String username, String password) {
        logger.info("Logging in.");
        type(usernameFieldLocator, username);
        type(passwordFieldLocator, password);
        click(loginButtonLocator);
    }

    public boolean isUserLoggedInSuccessfully() {
        logger.info("Checking if user is logged in successfully.");
        //return driver.getCurrentUrl().contains(IS_USER_LOGGED_IN_SIGN);
        return loginCriteria.test(driver);
    }

    private WebElement findBy(By locator){
        logger.info("Looking for element with locator {}", locator);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findElementsBy(By locator){
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(locator)
        );
    }
}
