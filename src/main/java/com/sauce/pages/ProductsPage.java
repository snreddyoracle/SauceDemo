package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends BasePage {

    double totalItemPrice = 0;
    WebElement addtoCartLocator;
    private static final String addtoCart = "//div[@class = 'inventory_item'][%s]//button[text() ='Add to cart']";
    WebElement itemPriceLocator;
    private static final String itemPrice = "//div[@class = 'inventory_item'][%s]//div[@class ='inventory_item_price']";

    public ProductsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductsPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOf(openMenu));
        return this;
    }

    /**
     * @param NumberOfItemsToAdd
     * @return ProductsPage after adding products
     */
    public ProductsPage addToCart(int NumberOfItemsToAdd) {
        for (int i = 1; i <= NumberOfItemsToAdd; i++) {
            addtoCartLocator = driver.findElement(By.xpath(String.format(addtoCart, i)));
            addtoCartLocator.click();
            itemPriceLocator = driver.findElement(By.xpath(String.format(itemPrice, i)));
            this.totalItemPrice = totalItemPrice + Double.parseDouble(itemPriceLocator.getText().substring(1));
        }
        return this;
    }
}