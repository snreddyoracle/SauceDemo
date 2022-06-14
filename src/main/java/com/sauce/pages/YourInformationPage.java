package com.sauce.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
     */
    public Overview fillPersonalInformation() {
        firstNameLocator.sendKeys("First Name");
        lastNameLocator.sendKeys("Last Name");
        zipCodeLocator.sendKeys("500072");
        continueLocator.click();
        return new Overview(this.driver);
    }
}