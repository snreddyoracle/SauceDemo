package com.sauce.assignment;

import com.WebDriverFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.utils.Utilty;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.sauce.pages.*;

public class BaseTestcase {
    protected LoginPage loginPage;
    protected ProductsPage productsPage;
    protected YourCartPage yourCartPage;
    protected YourInformationPage yourInformationPage;
    protected Overview overviewPage;
    protected CompletePage completePage;
    protected WebDriver driver;
    public final static Logger logger = WebDriverFactory.getLogger();
    protected ExtentReports extent;
    protected ExtentTest report;

    @BeforeSuite
    public void results() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("SauceDemoTestResult.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void setup(ITestResult result) {
        this.driver = WebDriverFactory.getDriver(WebDriverFactory.BrowserName.CHROME);
        this.loginPage = new LoginPage(this.driver);
    }

    @AfterMethod
    public void teardown(ITestResult result) throws Exception {
        if (ITestResult.FAILURE == result.getStatus()) {
            String temp = Utilty.takeSnapShot(driver, result.getName());
            report.fail(result.getThrowable().getMessage(), MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
        }

        driver.quit();
    }

    @AfterSuite
    public void tearDownAfterSuite() {
        extent.flush();
    }
}