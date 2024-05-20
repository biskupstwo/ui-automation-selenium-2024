package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import org.slf4j.Logger;

public class CartPage {

    private final WebDriver driver;
    private WebDriverWait wait;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected int timeout = 10;

    private final String IS_USER_ON_CART_PAGE = "cart.html";


    public CartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void setTimeout(int timeout){
        this.timeout = timeout;
    }


    private WebElement findBy(By locator) {
        logger.info("Looking for element with locator {}", locator);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public boolean isUserOnCartPage() {
        logger.info("Checking if user is on the cart page.");
        return driver.getCurrentUrl().contains(IS_USER_ON_CART_PAGE);
    }

    public boolean inventoryItemPriceCompare(String itemPrice){
       logger.info("Comparing Item Prices.");
       return findBy(By.className("inventory_item_price")).getText().equals(itemPrice);
    }

    public void goToCheckoutPage(){
        logger.info("Going to the checkout page.");
        findBy(By.id("checkout")).click();
    }
}
