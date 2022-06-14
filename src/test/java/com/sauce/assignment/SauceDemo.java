package com.sauce.assignment;

import com.aventstack.extentreports.ExtentTest;
import com.sauce.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class SauceDemo extends BaseTestcase {
    @Test
    public void Login() throws InterruptedException {
        report = extent.createTest("LoginTest");
        logger.info("Testcases Started");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.open();
        report.info("URL opened Successfully");
        loginPage.waitUntilLoaded();
        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.waitUntilLoaded();
        report.info("Login Successfully");
        loginPage.logout();
        report.info("Logout Successfully");

    }

    @Test
    public void shopping() throws InterruptedException {
        ExtentTest report = extent.createTest("ShoppingTest");
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.open();
        report.info("URL opened Successfully");
        loginPage.waitUntilLoaded();

        ProductsPage productsPage = loginPage.login("standard_user", "secret_sauce");
        productsPage.waitUntilLoaded();
        report.pass("Login Successfully");
        productsPage.addToCart(2);
        report.info("products added to the cart Successfully");

        YourCartPage yourCartPage = productsPage.goToYourCartPage();
        yourCartPage.waitUntilLoaded();
        report.info("Open cart page Successfully");
        yourCartPage.calculateTotalItemPrice();
        report.info("Before removing: Total item price calculated successfully");

        YourInformationPage yourInformationPage = yourCartPage.checkOut();
        yourInformationPage.waitUntilLoaded();

        Overview overviewPage = yourInformationPage.fillPersonalInformation();
        report.info("Fill the personal details successfully");
        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal(),"Calculated total price is mismatch with subtotal ");
        report.pass("Before removing: Calculated total price is equals subtotal");

        overviewPage.goToYourCartPage();
        yourCartPage.waitUntilLoaded();
        yourCartPage.remove(1);
        report.info("Removed given number of items successfully");
        yourCartPage.calculateTotalItemPrice();
        report.info("After removing: Total item price calculated successfully");
        yourCartPage.checkOut();

        yourInformationPage.fillPersonalInformation();
        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal(),"Calculated total price is mismatch with subtotal ");
        report.pass("After removing: Calculated total price is equals subtotal");

        CompletePage completePage = overviewPage.finish();
        report.info("Finish shopping successfully");
        completePage.waitForSuccessMessage();
        completePage.logout();
        report.pass("Logout successfully");
    }
}