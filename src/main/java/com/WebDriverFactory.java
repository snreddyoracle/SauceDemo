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

    /**
     * @param browserName
     * @return WebDriver for given browser
     */
    public static WebDriver getDriver(BrowserName browserName) {
        WebDriver driver = null;
        if (browserName == BrowserName.CHROME) {
            driver = getChromeDriver();
        } else {
            throw new InvalidArgumentException("Invalid browser option");
        }
        driver.manage().window().maximize();
        return driver;
    }

    /**
     * @return Log4j object
     */
    public static Logger getLogger() {

        return logger;
    }

    /**
     * @return  Driver file path
     */
    private static File getDriversPath() {
        String os = System.getProperty("os.name");
        File osDriversDir = null;
        logger.debug("Operating system: " + os);
        logger.debug("Drivers root directory: " + rootDriversDir.toString());
        if (os.toLowerCase().contains("windows")) {
            osDriversDir = new File(rootDriversDir, "windows");
        } else if (os.toLowerCase().contains("mac")) {
            osDriversDir = new File(rootDriversDir, "mac");
        } else {
            throw new InvalidArgumentException("Invalid operating system: " + os);
        }
        logger.debug("OS drivers directory: " + osDriversDir.toString());
        return osDriversDir;
    }

    private static WebDriver getChromeDriver() {
        logger.debug("Get chrome driver..");
        String os = System.getProperty("os.name");
        File driversDir = getDriversPath();
        File chromeFile=null;
        if (os.toLowerCase().contains("windows")) {
            chromeFile = new File(driversDir, "chromedriver.exe");
        } else if (os.toLowerCase().contains("mac")) {
            chromeFile = new File(driversDir, "chromedriver");
        }

        System.setProperty("webdriver.chrome.driver", chromeFile.getPath());
        return new ChromeDriver();
    }
}
