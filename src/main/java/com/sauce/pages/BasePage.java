package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriverWait wait;
    public WebDriver driver;

    @FindBy(xpath = "//div[@id = 'shopping_cart_container']")
    WebElement cartLinkLocator;

    @FindBy(xpath = "//button[text()='Open Menu']")
    WebElement openMenu;

    @FindBy(xpath = "//a[text()='Logout']")
    WebElement logOut;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public YourCartPage goToYourCartPage() {
        cartLinkLocator.click();
        return new YourCartPage(this.driver);
    }

    public LoginPage logout() throws InterruptedException {
        openMenu.click();
        wait.until(ExpectedConditions.visibilityOf(logOut));
        logOut.click();
        return new LoginPage(this.driver);
    }
}