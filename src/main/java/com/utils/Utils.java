package com.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class Utils {
    public static String takeSnapShot(WebDriver driver, String fileName) throws Exception {
        String fileWithPath = "./Scrennshots/" + fileName + ".png";
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);

        return fileWithPath;
    }
    public static Properties getTestData() throws Exception {
        FileReader reader=new FileReader("src/main/resources/db.properties");

        Properties prop=new Properties();
        prop.load(reader);
        return prop;
    }
}
