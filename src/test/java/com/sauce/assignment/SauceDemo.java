package com.sauce.assignment;

import com.BaseTestcase;
import com.WebDriverFactory;
import com.aventstack.extentreports.ExtentTest;
import com.sauce.pages.*;
import com.sauce.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SauceDemo extends BaseTestcase {
    @Test
    public void Login() throws InterruptedException {
        ExtentTest report = extent.createTest("LoginTest");
        logger.info("Testcases Started");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.open();
        loginPage.waitUntilLoaded();



    }
    @Test//T3
    public void shopping() throws InterruptedException {
        ExtentTest report = extent.createTest("ShoppingTest");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.open();
        loginPage.waitUntilLoaded();
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");

        productsPage.waitUntilLoaded();
        YourCartPage yourCartPage = productsPage.addToCart(2);
        yourCartPage.calculateTotalItemPrice();


        yourCartPage.waitUntilLoaded();
        YourInformationPage yourInformationPage = yourCartPage.checkOut();

        Overview overviewPage = yourInformationPage.fillPersonalInformation();
        //overviewPage.getSubTotal();
        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal());
        overviewPage.goToYourCartPage();
        yourCartPage.waitUntilLoaded();
        yourCartPage.remove(1);
        yourCartPage.calculateTotalItemPrice();
        yourCartPage.checkOut();
        yourInformationPage.fillPersonalInformation();
        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal());
        CompletePage completePage = overviewPage.finish();
        completePage.waitForSuccessMessage();


    }

}