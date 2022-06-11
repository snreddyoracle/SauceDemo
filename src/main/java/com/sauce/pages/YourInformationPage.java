package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class YourInformationPage extends BasePage {

    double totalItemPrice = 0;


    WebElement addtoCartLocator;
    private static final String addtoCart = "//div[@class = 'inventory_item'][%s]//button[text() ='Add to cart']";

    WebElement itemPriceLocator;
    private static final String itemPrice = "//div[@class = 'inventory_item'][%s]//div[@class ='inventory_item_price']";

    WebElement removeItemLocator;
    private static final String removeItem = "//div[@class = 'inventory_item'][%s]//div[@class ='Remove']";

    @FindBy(xpath = "//div[@id = 'shopping_cart_container']")
    WebElement cartLinkLocator;

    @FindBy(id = "first-name")
    WebElement firstNameLocator;


    @FindBy(id = "last-name")
    WebElement lastNameLocator;


    @FindBy(id = "postal-code")
    WebElement zipCodeLocator;


    @FindBy(id = "continue")
    WebElement continueLocator;


    // constructor method
    public YourInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    public YourInformationPage waitUntilLoaded() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(firstNameLocator));
        return this;
    }

    public Overview fillPersonalInformation() {

        firstNameLocator.sendKeys("aaaaa");
        lastNameLocator.sendKeys("bbbbb");
        zipCodeLocator.sendKeys("500072");
        continueLocator.click();

        return new Overview(this.driver);


    }

}