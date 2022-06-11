package com.sauce.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage{



    @FindBy(id = "user-name")
    WebElement user_name;

    @FindBy(id="password")
    WebElement passwordPath;

    @FindBy(id="login-button")
    WebElement login_button;

    // constructor method
    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public LoginPage waitUntilLoaded() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(user_name));
        return this;
    }

    public ProductsPage login(String userName, String password){
        user_name.clear();
        user_name.sendKeys(userName);
        passwordPath.clear();
        passwordPath.sendKeys(password);
        login_button.click();
        return new ProductsPage(this.driver);
    }

}