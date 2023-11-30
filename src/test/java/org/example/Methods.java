package org.example;

import ElementHelper.InfoParam;
import PropertiesHelper.PropertiesHelper;
import driver.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

public class Methods extends Driver {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));
    InfoParam infoParam=new InfoParam();
    static String currentSystem = System.getProperty("os.name");
    WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    WebElement webElement;

    private void waitForPageLoad() {
        int tryCount = 35;
        int waitPeriod = 1;

        for (int i = 0; i < tryCount; i++) {
            if ((((JavascriptExecutor) webDriver).executeScript("return document.readyState").toString()).equals("complete")) break;
            else waitBySeconds(waitPeriod);
        }
        Assert.fail("Page Not Loaded !!!");
    }

    public WebElement findElementMethod(String Data, String Type, Boolean Assertion, int TimeOut) {
        Type = Type.toLowerCase();
        waitForPageLoad();
        By by = Type.equals("xpath") ? By.xpath(Data) : Type.equals("text") ? By.xpath("//*[contains(text(),'" + Data + "')]") : infoParam.getBy(Data);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, TimeOut);
        WebElement webElement = null;
        try {
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }
        catch (Exception e) {
            logger.info(Data + "\n" + Type + " not found!");
            if (Assertion) Assert.fail(Data + "\n" + Type + " not found!");
        }
        return webElement;
    }

    public WebElement findElementWithKey(String key){
        By by = infoParam.getBy(key);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement findElementWithText(String text){
        String xpath="//*[contains(text(),'"+text+"')]";
        By by = By.xpath(xpath);
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement findElementWithTextWithOutAssert(String text){
        String xpath="//*[contains(text(),'"+text+"')]";
        By by = By.xpath(xpath);
        try {
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }
        catch (Exception e){
            logger.info("This text = "+text+ " not found!");
            return null;
        }
        return webElement;
    }

    public WebElement findElementWithXpathWithOutAssert(String xpath){
        By by = By.xpath(xpath);
        try {
            webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        }catch (Exception e){
            logger.info("This xpath = "+xpath+ " not found!");
            return null;
        }
        return webElement;
    }

    public WebElement findElementWithXpathWithOutAssertLocated(String xpath){
        By by = By.xpath(xpath);
        try {
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
            logger.info("xpath found = "+xpath);
        }catch (Exception e){
            logger.info("This xpath = "+xpath+ " not found!");
            return null;
        }
        return webElement;
    }

    public WebElement findElementWithXpathWithOutAssertLessTimeOut(String xpath){
        By by =By.xpath(xpath);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        try {
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            logger.info("This xpath = "+xpath+ " not found!");
            return null;
        }
        return webElement;
    }

    public boolean doesElementExistWithKeyLessTimeOut(String key){
        By by = infoParam.getBy(key);
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(2));
        try {
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            logger.info("This key = "+key+ " not found!");
            return false;
        }
        return true;
    }

    public WebElement findElementWithKeyWithOutAssert(String key){
        By by = infoParam.getBy(key);
        try {
            webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch (Exception e){
            logger.info("This key = "+key+ " not found!");
            return null;
        }
        return webElement;
    }

    public WebElement findElementWithKeyForClick(String key){
        By by = infoParam.getBy(key);
        webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(by));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        return webElement;
    }

    public void waitBySeconds(int seconds) {
        seconds=seconds+4;
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSystemMac(){
        return currentSystem.contains("Mac") || currentSystem.contains("Linux");
    }

    public static String createPathProperties(String locations){
        PropertiesHelper propertiesHelper=new PropertiesHelper();
        String result;
        String lastReturnValue;

        if(Driver.isThisADockerTest) result = "Linux";
        else if (isSystemMac()) result = "MacOS";
        else result = "Windows";

        try {
            lastReturnValue = propertiesHelper.readProperties("filePath",locations +"."+ result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lastReturnValue;
    }

    public String getTextFromElement(String key){
        WebElement element = findElementWithKey(key);
        logger.info("Element key: "+key+" text value: "+ element.getText());
        return element.getText();
    }
    public static String getTodayDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }
    public static String getTimeNow(){
        String pattern = " HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }
}