package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class YourCartPage<itemCount> extends BasePage {
    private double totalItemPrice;
    private static final String catrtItemCount = "//div[@class='cart_item']";
    WebElement removeFromCartLocator;
    private static final String removeFromCart = "//div[@class='cart_item'][%s]//button[text()='Remove']";
    WebElement itemPriceLocator;
    private static final String itemPrice = "//div[@class='cart_item'][%s]//div[@class='inventory_item_price']";
    WebElement removeItemLocator;
    private static final String removeItem = "//div[@class = 'inventory_item'][%s]//div[@class ='Remove']";
    @FindBy(id = "checkout")
    WebElement checkOutLocator;

    // constructor method
    public YourCartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public YourCartPage waitUntilLoaded() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(checkOutLocator));
        return this;
    }

    public YourInformationPage checkOut() {
        checkOutLocator.click();
        return new YourInformationPage(this.driver);
    }

    public void remove(int NumberOfItemsToRemove) {
        for (int i = 1; i <= NumberOfItemsToRemove; i++) {
            itemPriceLocator = driver.findElement(By.xpath(String.format(itemPrice, i)));
            this.totalItemPrice = totalItemPrice + Double.parseDouble(itemPriceLocator.getText().substring(1));
            removeFromCartLocator = driver.findElement(By.xpath(String.format(removeFromCart, i)));
            removeFromCartLocator.click();
        }
    }

    public void calculateTotalItemPrice() {
        this.totalItemPrice = 0;
        List<WebElement> cartItemCount = driver.findElements(By.xpath(catrtItemCount));
        for (int i = 1; i <= cartItemCount.size(); i++) {
            itemPriceLocator = driver.findElement(By.xpath(String.format(itemPrice, i)));
            this.totalItemPrice = totalItemPrice + Double.parseDouble(itemPriceLocator.getText().substring(1));
        }
    }

    public double getTotalItemPrice() {
        return this.totalItemPrice;
    }
}

