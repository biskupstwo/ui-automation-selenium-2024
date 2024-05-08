package pl.globallogic.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SauceDemoTest {

    @Test
    public void shouldSuccessfullyLoginStandardUser(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }

    @Test
    public void shouldShowAnErrorForInvalidPassword(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("gold_saucer");
        loginButton.click();

        WebElement test = driver.findElement(By.className("error-message-container"));

        String expectedUrl = "https://www.saucedemo.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }

    @Test
    public void shouldShowAnErrorForLockedOutUser(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("locked_out_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement test = driver.findElement(By.className("error-message-container"));

        String expectedUrl = "https://www.saucedemo.com/";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        driver.quit();
    }


    //shouldAddSauceLabsBackpackIntoCartFromCatalogView

    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromCatalogView(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement test = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        test.click();

        driver.quit();
    }
    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromItemDetailsView(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement test = driver.findElement(By.id("item_4_img_link"));
        test.click();

        String expectedUrl = "https://www.saucedemo.com/inventory-item.html?id=4";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        WebElement addToCart = driver.findElement(By.id("add-to-cart"));
        addToCart.click();

        WebElement removeBtn = driver.findElement(By.id("remove"));
        driver.quit();
    }
    //shouldHavePriceInformationForItemInACart (verify: compare prices catalog/cart)
    //shouldRemoveSauceLabsBackpackFromCart (verify: CartIcon or ButtonChange)
    //shouldRemoveSauceLabsBackpackFromCartOnCatalogPage
    @Test
    public void shouldRemoveSauceLabsBackpackFromCartOnCatalogPage(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement test = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        test.click();

        WebElement removeBtn = driver.findElement(By.id("remove-sauce-labs-backpack"));
        removeBtn.click();
        driver.quit();
    }
    //shouldAddListOfItemsToTheCart
    //shouldPersistCheckoutDetails
}
