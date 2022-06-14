package com.sauce.assignment;

import com.aventstack.extentreports.ExtentTest;
import org.testng.Assert;
import org.testng.annotations.*;

public class SauceDemo extends BaseTestcase {
    @Test(alwaysRun = true)
    public void Login() {

        report = extent.createTest("LoginTest");
        logger.info("Testcases Started");
        loginPage.open(testData);
        report.info("URL opened Successfully");

        loginPage.waitUntilLoaded();
        this.productsPage = loginPage.login(testData);
        productsPage.waitUntilLoaded();
        report.info("Login Successfully");

        loginPage.logout();
        report.pass("Logout Successfully");
    }

    @Test(dependsOnMethods = {"Login"}, alwaysRun = true)
    public void shopping() {
        
        ExtentTest report = extent.createTest("ShoppingTest");
        loginPage.open(testData);
        report.info("URL opened Successfully");

        loginPage.waitUntilLoaded();
        this.productsPage = loginPage.login(testData);
        productsPage.waitUntilLoaded();
        report.pass("Login Successfully");

        productsPage.addToCart(testData);
        report.info("products added to the cart Successfully");

        this.yourCartPage = productsPage.goToYourCartPage();
        yourCartPage.waitUntilLoaded();
        report.info("Open cart page Successfully");

        yourCartPage.calculateTotalItemPrice();
        report.info("Before removing: Total item price calculated successfully");
        this.yourInformationPage = yourCartPage.checkOut();
        yourInformationPage.waitUntilLoaded();
        this.overviewPage = yourInformationPage.fillPersonalInformation(testData);
        report.info("Fill the personal details successfully");

        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal(), "Calculated total price is mismatch with subtotal ");
        report.pass("Before removing: Calculated total price is equals subtotal");
        overviewPage.goToYourCartPage();
        yourCartPage.waitUntilLoaded();
        yourCartPage.remove(testData);
        report.info("Removed given number of items successfully");

        yourCartPage.calculateTotalItemPrice();
        report.info("After removing: Total item price calculated successfully");
        yourCartPage.checkOut();
        yourInformationPage.fillPersonalInformation(testData);
        Assert.assertEquals(yourCartPage.getTotalItemPrice(), overviewPage.getSubTotal(), "Calculated total price is mismatch with subtotal ");
        report.pass("After removing: Calculated total price is equals subtotal");

        this.completePage = overviewPage.finish();
        report.info("Finish shopping successfully");
        completePage.waitForSuccessMessage();
        completePage.logout();
        report.pass("Logout successfully");
    }
}