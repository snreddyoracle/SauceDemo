package com;

import org.apache.log4j.Logger;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;


public class WebDriverFactory {

    private final static Logger logger = Logger.getLogger(WebDriverFactory.class);

    private final static File rootDriversDir = new File("src/main/resources/drivers");

    public enum BrowserName {
        CHROME
    }

    public static WebDriver getDriver(BrowserName name) {
        WebDriver driver = null;
        if (name == BrowserName.CHROME) {
            driver = getChromeDriver();
        }else {
            throw new InvalidArgumentException("Invalid browser option");
        }
        driver.manage().window().maximize();
        return driver;
    }
    public static Logger getLogger() {
        return logger;
    }

    private static File getDriversPath() {
        String os = System.getProperty("os.name");
        File osDriversDir = null;
        logger.debug("Operating system: " + os);
        logger.debug("Drivers root directory: " + rootDriversDir.toString());
        if (os.toLowerCase().contains("windows")) {
            osDriversDir = new File(rootDriversDir, "windows");
        }
        else{
            throw new InvalidArgumentException("Invalid operating system: " + os);
        }
        logger.debug("OS drivers directory: " + osDriversDir.toString());
        return osDriversDir;
    }


    private static WebDriver getChromeDriver() {
        logger.debug("Get chrome driver..");
        File driversDir = getDriversPath();
        File chromeFile = new File(driversDir, "chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeFile.getPath());
        return new ChromeDriver();
    }
}
