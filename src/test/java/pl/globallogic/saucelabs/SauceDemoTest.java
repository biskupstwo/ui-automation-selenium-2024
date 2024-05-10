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
        String errorMessage = test.getText();
        String expectedErrorMessage = "Epic sadface: Username and password do not match any user in this service";
        Assert.assertEquals(errorMessage, expectedErrorMessage);

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
        String errorMessage = test.getText();
        String expectedErrorMessage = "Epic sadface: Sorry, this user has been locked out.";
        Assert.assertEquals(errorMessage, expectedErrorMessage);

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
    @Test
    public void shouldHavePriceInformationForItemInACart(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement backpackAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        backpackAddToCart.click();

        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        String backpackPrice = "$29.99";
        WebElement inventoryItemPrice = driver.findElement(By.className("inventory_item_price"));
        String itemPrice = inventoryItemPrice.getText();
        Assert.assertEquals(backpackPrice, itemPrice);

        driver.quit();
    }
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
    @Test
    public void shouldPersistCheckoutDetails(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com");

        WebElement username = driver.findElement(By.id("user-name"));
        WebElement password = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        username.sendKeys("standard_user");
        password.sendKeys("secret_sauce");
        loginButton.click();

        WebElement backpackAddToCart = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        backpackAddToCart.click();

        WebElement cartLink = driver.findElement(By.className("shopping_cart_link"));
        cartLink.click();

        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);


        WebElement checkoutBtn = driver.findElement(By.id("checkout"));
        checkoutBtn.click();
        //String itemPrice = inventoryItemPrice.getText();

        String expectedCheckoutStepOneUrl = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrlStep2 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlStep2, expectedCheckoutStepOneUrl);


        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement postalCode = driver.findElement(By.id("postal-code"));

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        postalCode.sendKeys("66666");

        WebElement continueBtn = driver.findElement(By.id("continue"));
        continueBtn.click();

        String expectedCheckoutStepTwoUrl = "https://www.saucedemo.com/checkout-step-two.html";
        String actualUrlStep3 = driver.getCurrentUrl();
        Assert.assertEquals(actualUrlStep3, expectedCheckoutStepTwoUrl);

        String backpackPrice = "$29.99";
        WebElement inventoryItemPrice = driver.findElement(By.className("inventory_item_price"));
        String itemPrice = inventoryItemPrice.getText();
        Assert.assertEquals(backpackPrice, itemPrice);

        WebElement subTotalPriceActualElement = driver.findElement(By.className("inventory_item_price"));
        String subTotalPriceActual = subTotalPriceActualElement.getText();
        Assert.assertEquals(subTotalPriceActual, backpackPrice);

        driver.quit();
    }
}

