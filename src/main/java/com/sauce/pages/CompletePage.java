package com.sauce.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class CompletePage extends BasePage{


    @FindBy(xpath = "//div/h2[text()='THANK YOU FOR YOUR ORDER']")
    WebElement successMessageLocator;



    // constructor method
    public CompletePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CompletePage waitForSuccessMessage() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(successMessageLocator));
        return this;
    }

}