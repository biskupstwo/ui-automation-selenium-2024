package pl.globallogic.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CatalogPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By itemsCountBadge = By.className("shopping_cart_badge");

    private final String ITEM_SELECTOR = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//button";
    private final String ITEM_SELECTOR_DESCRIPTION = "//*[text()='%s']//ancestor::div[@class='inventory_item_description']//a";
    private final String ITEM_SELECTOR_REMOVE = "//button[@id='remove-%s']";

    public CatalogPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    private WebElement findBy(By locator) {
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public void addToCart(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR.formatted(itemName))).click();
    }
    public void removeFromCart(String itemName){
        findBy(By.xpath(ITEM_SELECTOR_REMOVE.formatted(itemName))).click();
    }
    public void goToDescription(String itemName) {
        findBy(By.xpath(ITEM_SELECTOR_DESCRIPTION.formatted(itemName))).click();
    }
    public int cartItemsCount() {
        return Integer.parseInt(findBy(itemsCountBadge).getText());
    }

    public boolean isThereItemsInCart(){
        return driver.findElements(By.className("shopping_cart_badge")).isEmpty();
    }

    public void goToCartPage(){
        findBy(By.className("shopping_cart_link")).click();
    }
}
