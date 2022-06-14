package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Properties;

public class YourInformationPage extends BasePage {
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

    public YourInformationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    /**
     * @return YourInformationPage after page load successfully.
     */
    public YourInformationPage waitUntilLoaded() {
        wait.until(ExpectedConditions.visibilityOf(firstNameLocator));
        return this;
    }

    /**
     * @return Overview page after fill after personal details.
     * @param testData
     */
    public Overview fillPersonalInformation(Properties testData) {
        firstNameLocator.sendKeys(testData.getProperty("firstName"));
        lastNameLocator.sendKeys(testData.getProperty("lastName"));
        zipCodeLocator.sendKeys(testData.getProperty("zipCode"));
        continueLocator.click();
        return new Overview(this.driver);
    }
}