package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPageStepTwo {

    private final WebDriver driver;
    private WebDriverWait wait;

    private final String IS_USER_ON_CHECKOUT_PAGE_STEP_TWO = "checkout-step-two.html";

    public CheckoutPageStepTwo(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isUserOnCheckoutPageStepTwo() {
        return driver.getCurrentUrl().contains(IS_USER_ON_CHECKOUT_PAGE_STEP_TWO);
    }

    private WebElement findBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public boolean inventoryItemPriceCompare(String itemPrice){
        return findBy(By.className("inventory_item_price")).getText().equals(itemPrice);
    }
}
