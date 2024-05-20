package pl.globallogic.saucelabs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.globallogic.pages.*;

import java.time.Duration;

public class SauceDemoSmokeTest extends SauceDemoBaseTest {


    //shouldShowErrorForInvalidPassword
    //shouldShowErrorForInvalidUsername
    //shouldShowErrorForEmptyCredentials
    @Test
    public void shouldSuccessfullyLoginStandardUser(){
        LandingPage page = new LandingPage(driver, host);
        page.visit();
        page.loginWith("standard_user","secret_sauce");
        page.isUserLoggedInSuccessfully();
    }

    @Test
    public void shouldShowAnErrorForInvalidPassword(){
        LandingPage page = new LandingPage(driver, host);
        page.visit();
        page.loginWith("standard_user","gold_saucer");

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
    public void shouldAddSauceLabsBackpackIntoCartFromCatalogView() throws InterruptedException {
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;
        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);
    }

    @Test
    public void shouldAddSauceLabsBackpackIntoCartFromItemDetailsView(){
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;
        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.goToDescription(itemName);

        ItemDetailsView itemDetailsView = new ItemDetailsView(driver);
        itemDetailsView.isUserOnItemDescriptionPage();
        itemDetailsView.addToCart(itemName);

        Assert.assertEquals(itemDetailsView.cartItemsCount(), expectedCartItemsCount);
    }
    //shouldHavePriceInformationForItemInACart (verify: compare prices catalog/cart)
    @Test
    public void shouldHavePriceInformationForItemInACart(){
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        //String itemNameButton = "sauce-labs-backpack";
        int expectedCartItemsCount = 1;

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);

        catalogPage.goToCartPage();
        CartPage cartPage = new CartPage(driver);
        cartPage.isUserOnCartPage();

        String backpackPrice = "$29.99";
        cartPage.inventoryItemPriceCompare(backpackPrice);
    }
    //shouldRemoveSauceLabsBackpackFromCart (verify: CartIcon or ButtonChange)

    //shouldRemoveSauceLabsBackpackFromCartOnCatalogPage
    @Test
    public void shouldRemoveSauceLabsBackpackFromCartOnCatalogPage(){
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        String itemNameButton = "sauce-labs-backpack";
        int expectedCartItemsCount = 1;

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);

        catalogPage.removeFromCart(itemNameButton);
        catalogPage.isThereItemsInCart();
    }
    @Test
    public void shouldAddListOfItemsToTheCart(){
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName1 = "Sauce Labs Backpack";
        String itemName2 = "Sauce Labs Fleece Jacket";
        String itemName3 = "Sauce Labs Onesie";
        int expectedCartItemsCount = 3;
        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName1);
        catalogPage.addToCart(itemName2);
        catalogPage.addToCart(itemName3);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);
    }
    @Test
    public void shouldPersistCheckoutDetails(){
        String username = "standard_user";
        String password = "secret_sauce";
        String itemName = "Sauce Labs Backpack";
        int expectedCartItemsCount = 1;

        LandingPage landingPage = new LandingPage(driver, host);
        landingPage.visit();
        landingPage.loginWith(username, password);
        CatalogPage catalogPage = new CatalogPage(driver);
        catalogPage.addToCart(itemName);
        Assert.assertEquals(catalogPage.cartItemsCount(), expectedCartItemsCount);

        catalogPage.goToCartPage();
        CartPage cartPage = new CartPage(driver);
        cartPage.isUserOnCartPage();
        cartPage.goToCheckoutPage();

        CheckoutPageStepOne checkoutPageStepOne = new CheckoutPageStepOne(driver);
        checkoutPageStepOne.isUserOnCheckoutPageStepOne();
        checkoutPageStepOne.fillInFieldsAndContinue("John","Doe","66666");

        CheckoutPageStepTwo checkoutPageStepTwo = new CheckoutPageStepTwo(driver);
        checkoutPageStepTwo.isUserOnCheckoutPageStepTwo();

        String backpackPrice = "$29.99";
        checkoutPageStepTwo.inventoryItemPriceCompare(backpackPrice);
    }
}

