package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    WebElement user_name;
    @FindBy(id = "password")
    WebElement passwordPath;
    @FindBy(id = "login-button")
    WebElement login_button;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * Open the given URL
     * @param testData
     */
    public void open(Properties testData) {
        driver.get(testData.getProperty("url"));
    }

    /**
     * @return LoginPage after page load successfully
     */
    public LoginPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOf(user_name));
        return this;
    }

    /**
     * @param testData
     * @return ProductsPage after login
     */
    public ProductsPage login(Properties testData) {
        user_name.clear();
        user_name.sendKeys(testData.getProperty("userName"));
        passwordPath.clear();
        passwordPath.sendKeys(testData.getProperty("password"));
        login_button.click();
        return new ProductsPage(this.driver);
    }
}