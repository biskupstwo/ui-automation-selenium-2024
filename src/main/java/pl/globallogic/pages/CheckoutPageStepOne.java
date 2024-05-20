package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPageStepOne {

    private final WebDriver driver;
    private WebDriverWait wait;

    private final String IS_USER_ON_CHECKOUT_PAGE_STEP_ONE = "checkout-step-one.html";

    private final By firstNameFieldLocator = By.id("first-name");
    private final By lastNameFieldLocator = By.id("last-name");
    private final By postalCodeFieldLocator = By.id("postal-code");
    private final By continueButtonLocator = By.id("continue");




    public CheckoutPageStepOne(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private WebElement findBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public void fillInFieldsAndContinue(String firstName, String lastName, String postalCode) {
        findBy(firstNameFieldLocator).sendKeys(firstName);
        findBy(lastNameFieldLocator).sendKeys(lastName);
        findBy(postalCodeFieldLocator).sendKeys(postalCode);
        findBy(continueButtonLocator).click();
    }
    public boolean isUserOnCheckoutPageStepOne() {
        return driver.getCurrentUrl().contains(IS_USER_ON_CHECKOUT_PAGE_STEP_ONE);
    }
}
