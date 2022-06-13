package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Overview extends BasePage {
    double totalItemPrice = 0;
    @FindBy(xpath = "//div[@id = 'shopping_cart_container']")
    WebElement cartLinkLocator;
    @FindBy(id = "finish")
    WebElement finishButton;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement subTotalLocator;
    @FindBy(id = "cancel")
    WebElement CancelButton;

    // constructor method
    public Overview(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Overview waitUntilLoaded() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(cartLinkLocator));
        return this;
    }

    public double getSubTotal() {
        return Double.parseDouble(subTotalLocator.getText().split("\\$")[1]);
    }

    public CompletePage finish() {
        finishButton.click();
        return new CompletePage(this.driver);
    }
}