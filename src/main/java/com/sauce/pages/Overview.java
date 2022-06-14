package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Overview extends BasePage {

    @FindBy(xpath = "//div[@id = 'shopping_cart_container']")
    WebElement cartLinkLocator;
    @FindBy(id = "finish")
    WebElement finishButton;
    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    WebElement subTotalLocator;

    public Overview(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @return Overview page
     */
    public Overview waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOf(subTotalLocator));
        return this;
    }

    /**
     * @return subtotal in the review page
     */
    public double getSubTotal() {
        return Double.parseDouble(subTotalLocator.getText().split("\\$")[1]);
    }

    /**
     * @return CompletePage after finish the shopping
     */
    public CompletePage finish() {
        finishButton.click();
        return new CompletePage(this.driver);
    }
}