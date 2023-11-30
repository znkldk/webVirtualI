package org.example;

import EngineerDetail.EngineerDetail;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.logging.Logger;

public class BaseSteps extends Methods{
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    public static boolean isThisWindowFirst=true;
    //------------------------------------------
    @Step("Open The Current Tab")
    public void openTheCurrentTab(){
        ArrayList<String> wid = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(wid.get(1));
    }

    @Step("log The Text Of Element <key>")
    public void logTheTextOfElement(String Key){
        getTextFromElement(Key);
    }

    @Step("Open New Window or previous one")
    public void openNewWindow(){
        String winHandleBefore = webDriver.getWindowHandle();

        for(String winHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(winHandle);
            if (webDriver.getCurrentUrl().contains("https://virtualriskspace.com/#/inspection")) break;
        }

        if (!isThisWindowFirst){
            webDriver.switchTo().window(winHandleBefore);
            isThisWindowFirst=false;
        }
        waitSeconds(5);
    }
    @Step("Open next window")
    public void nextWindow(){
        webDriver.switchTo().window(webDriver.getWindowHandle());
        waitSeconds(5);
    }

    @Step("Open  Window  previous one")
    public void openNewWindowPre(){
        for(String winHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(winHandle);
            break;
        }
        waitSeconds(5);
    }
    //-------------------------------------------------------------------------------------

    @Step("Go to link <link>")
    public void goToLink(String link){
        webDriver.get(link);
        logger.info("Address Opened successfully link: "+ link);
    }

    @Step("Go to link new tab <link>")
    public void goToLinkNewTab(String link){
        ((JavascriptExecutor) webDriver).executeScript("window.open()");

        waitSeconds(1);

        ArrayList<String> wid = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(wid.get(2));
        waitSeconds(1);
        webDriver.get(link);
        logger.info("Address Opened successfully link: "+ link);
    }

    @Step("Go to link new Window <link>")
    public void goToLinkNewWindow(String link){
        waitSeconds(3);
        WebDriver newWindow=webDriver.switchTo().newWindow(WindowType.WINDOW);
        openNewWindow();

        newWindow.get(link);
        waitSeconds(3);
        logger.info("Address Opened successfully link: "+ link);
    }


    @Step("Write <text> to <key>")
    public void writeTextWithKey(String text,String key) {
        if (text.equals("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }
        else if (text.equals("Otomasyon")) {
            EngineerDetail engineerDetail=new EngineerDetail();
            String value = engineerDetail.readAndgetTxtValue();
            text = value + " Post" ;
        }
        findElementWithKey(key).sendKeys(text);
        logger.info(text+" written to element "+key);
    }

    public static void writeTextWithElement(WebElement element,String text,String name){
        element.sendKeys(text);
        logger.info(text+" written to element. Element:"+name);
    }

    public static void clearTextWithElement(WebElement element,String name){
        element.sendKeys(isSystemMac() ? Keys.COMMAND + "a" : Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);

        logger.info("Element cleared Element: "+name);
    }

    @Step("Cleat Text <key>")
    public void clearTextWithKeyUsingClear(String key) {
        WebElement element = findElementWithKey(key);
        element.clear();
    }

    public void clearTextWithKey(String key){
        WebElement element=findElementWithKey(key);
        element.sendKeys(isSystemMac() ? Keys.COMMAND + "a" : Keys.CONTROL + "a");
        element.sendKeys(Keys.BACK_SPACE);
        logger.info("Element cleared Element: "+key);
    }
    //-------------------------------------------------------------------------------------

    @Step("Click to <key>")
    public void clicktWithKey(String key) {
        findElementWithKeyForClick(key).click();
        logger.info(key + " is clicked");
    }

    @Step("Click Button With Text <text>")
    public void clickButtonWithText(String text){
        String xpath="//*[@type=\"button\"]//*[contains(text(),'TEXTHERE')]"
                .replaceAll("TEXTHERE",text);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("cant find button by text xpath: "+xpath,element);
        clickByElement(element);
    }

    @Step("Go to Login without Robot Check")
    public void withoutRobotCheck(){
        String editURL = Driver.baseUrl;
        logger.info("New url : "+ editURL);
        webDriver.get(editURL);
    }

    @Step("Check Button With Text <text>")
    public void checkButtonWithText(String text){
        String xpath="//*[@type=\"button\"]//*[contains(text(),'TEXTHERE')]"
                .replaceAll("TEXTHERE",text);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("cant find button by text xpath: "+xpath,element);
    }

    @Step("If key exist Click to <key>")
    public void ifClicktWithKey(String key) {
        WebElement element=findElementWithKeyWithOutAssert(key);
        if (element==null){
            logger.info(key+" cant found step is skipped");
            return;
        }
        findElementWithKeyForClick(key).click();
        logger.info(key+" is clicked");
    }

    @Step("Click with js <key>")
    public void javascriptclicker(String key) {

        WebElement element=findElementWithKey(key);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
        logger.info(key+" is clicked");

    }

    @Step("Click with js id <id>")
    public void javascriptclickerXpath(String id) {
        waitSeconds(2);
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("document.getElementById('"+id+"').click()");
        logger.info(id+" is clicked");
    }

    @Step("Click with js id <id> if exist")
    public void javascriptclickerXpathIfExist(String id) {
        waitSeconds(2);
        try {
            JavascriptExecutor executor = (JavascriptExecutor) webDriver;
            executor.executeScript("document.getElementById('"+id+"').click()");
            logger.info(id+" is clicked");
        }catch (Exception e){
            logger.info("There is no accept btn");
        }

    }

    public void javascriptclickerByElement(WebElement element) {
        logger.info("javascriptclickerByElement...");
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        executor.executeScript("arguments[0].click();", element);
        logger.info("Successful click by js");
    }

    public void scrollToElement(WebElement element){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitSeconds(1);
    }
    public static void clickByElement(WebElement element){
        element.click();
    }

    //-------------------------------------------------------------------------------------

    @Step("Fail if <key> is NOT exist fail message <text>")
    public void failIfElementNotExist(String key,String text){
        WebElement element=findElementWithKeyWithOutAssert(key);
        Assert.assertNotNull(key+" is not exist \n"+ text,element);
        logger.info(key+ " found on page");
    }

    @Step("Fail if <key> is exist fail message <text>")
    public void failIfElementExist(String key,String text){
        WebElement element=findElementWithKeyWithOutAssert(key);
        Assert.assertNull(key+" Shouldn't be on the page but ıt is ",element);
        logger.info(key+ "cant found on page as expected ");
    }
    @Step("Fail if <text> text is NOT exist fail message <text>")
    public void failIfTextNotExist(String text,String failMessage){
        WebElement element = findElementMethod(text ,"text" , false , 10);
        Assert.assertNotNull(text+" is not exist",element);
        logger.info(text+ "text found on page");
    }

    @Step("Fail if <key> does not contains text <text>")
    public void checkKeyContainsText(String key,String expectedText){
        String actualTExt;
        WebElement element=findElementWithKey(key);
        actualTExt=element.getText();
        logger.info("expectedText: "+expectedText+"\n"+
                "actualTExt: "+actualTExt);
        Assert.assertEquals(key+ "does not contains expected text", actualTExt, expectedText);
    }

    //-------------------------------------------------------------------------------------
    //wait tools
    @Step("Wait <value> seconds")
    public void waitSeconds(int seconds) {
        if (seconds > 4) logger.info("Waiting " + seconds + " Seconds");
        waitBySeconds(seconds);
    }
    //-------------------------------------------------------------------------------------
    @Step("Hover element <key>")
    public void hoverByKey(String key){
        hoverByElement(
                findElementWithKey(key)
        );
        logger.info("Hovered element "+key);
    }

    public static void hoverByElement(WebElement element){
        Actions action = new Actions(webDriver);
        action.moveToElement(element);
        action.perform();
    }

    public static void scroolToElement(WebElement element){
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    //-------------------------------------------------------------------------------------

    @Step("Choose parametric from <dropDown> dropdown")
    public void dropDownSelecterFromTestinium(String dropdown){
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]";

        clicktWithKey(dropdown);
        waitSeconds(1);
        String value=System.getenv("FormName")
                .replaceAll("\n","");

        if (value.equals("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            value=engineerDetail.readAndgetTxtValue();
        }
        writeTextWithKey(value,dropdown);
        waitSeconds(1);
        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdownunda "+value+" elementi bulunamadı xpath: "+valueBaseXpath,
                element);
        javascriptclickerByElement(element);
        // clickByElement(element);
    }

    @Step("Choose <value> from <dropdown> dropdown")
    public void dropDownSelecter(String value,String dropdown){
        clicktWithKey(dropdown);
        waitSeconds(1);
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]";

        if (value.equals("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            value=engineerDetail.readAndgetTxtValue();
        }
        valueBaseXpath=valueBaseXpath.replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdown da "+value+" elementi bulunamadı xpath: "+valueBaseXpath, element);
        javascriptclickerByElement(element);
       // clickByElement(element);
    }

    @Step("Check <value> not exist from <dropDown> dropdown")
    public void dropDownSelecterIfNot(String value,String dropdown){
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//span[contains(text(),'VALUEHERE')]";
        javascriptclickerByElement(findElementWithKey(dropdown));
        waitSeconds(1);

        valueBaseXpath=valueBaseXpath.replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNull(dropdown+" dropdownunda "+value+" elementi bulunmaması gerekiyordu xpath: "+valueBaseXpath, element);
        javascriptclickerByElement(element);
        // clickByElement(element);
    }

    @Step("Count chooses from <dropDown> dropdown must be <count>")
    public void dropDownCounter(String dropdown,String count){
        String valueBaseXpath="(//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]/li)[VALUEHERE]";
        javascriptclickerByElement(findElementWithKey(dropdown));
        waitSeconds(1);

        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath.replaceAll("VALUEHERE",count));
        Assert.assertNotNull(dropdown+" dropdownunda "+count+" sayısı kadar seçenek bulunamadı xpath: "+valueBaseXpath,
                element);

        element=findElementWithXpathWithOutAssert(valueBaseXpath.replaceAll("VALUEHERE",String.valueOf(Integer.parseInt(count)+1)));
        Assert.assertNull(dropdown+" dropdownunda "+count+" sayısı kadar seçenek bulunamadı xpath: "+ valueBaseXpath, element);
        logger.info("OK!");
    }

    @Step("Dropdown search test from <dropDown> <value>")
    public void dropDownSearchText(String dropdown,String value){
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]";

        clicktWithKey(dropdown);
        waitSeconds(1);
        writeTextWithKey(value,dropdown);
        waitSeconds(1);
        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdownunda "+value+" elementi bulunamadı xpath: "+valueBaseXpath,
                element);
        javascriptclickerByElement(element);
        // clickByElement(element);
    }

    //-------------------------------------------------------------------------------------
    @Step("Make it fail <message>")
    public void makeItFail(String message){
        Assert.fail("This is a planned fail please check the message: "+message);
    }

    @Step("Chrome pop up accept")
    public void pressEnter() {
        webDriver.switchTo().alert().accept();
    }

    @Step("Write The Phone Number With Proper Formad")
    public void writePhoneNumberWithProperFormad(){
        String trFlagXpath="//*[@class=\"vti__selection\"]//*[@class=\"vti__flag tr\"]";
        String dropdownXpath="//*[@class=\"vti__selection\"]";
        String trDDxpath="//li//div[@class=\"vti__flag tr\"]";
        String trNumber="5555555555";

        WebElement dd=findElementWithXpathWithOutAssert(dropdownXpath);
        Assert.assertNotNull("fail phone dropdown cant found",dd);
        clickByElement(dd);
        waitSeconds(1);
        WebElement ddtr=findElementWithXpathWithOutAssert(trDDxpath);
        Assert.assertNotNull("fail phone dropdown cant found",ddtr);
        clickByElement(ddtr);
        waitSeconds(1);
        boolean isFormadTr=findElementWithXpathWithOutAssert(trFlagXpath)!=null;
        Assert.assertTrue("tr seçilmedi!!!",isFormadTr);
        clearTextWithKeyUsingClear("SetCall-Contact Phone-TextBox");
        writeTextWithKey(trNumber,"SetCall-Contact Phone-TextBox");
    }

    @Step("Optional Write The Phone Number With Proper Formad <text>")
    public void writePhoneNumberWithProperFormadClaim(String text){
        waitSeconds(1);
        String trFlagXpath="//*[@class=\"vti__selection\"]//*[@class=\"vti__flag tr\"]";
        String dropdownXpath="//*[.='"+text+"']/..//*[@class=\"vti__selection\"]";
        String trDDxpath="//*[.='"+text+"']/..//li//div[@class=\"vti__flag tr\"]";
        String trNumber="5555555555";
        String phonexpath="//*[.='"+text+"']/..//*[@name=\"telephone\"]";
        WebElement dd=findElementWithXpathWithOutAssert(dropdownXpath);
        Assert.assertNotNull("fail phone dropdown cant found",dd);
        hoverByElement(dd);
        javascriptclickerByElement(dd);
        waitSeconds(1);
        WebElement ddtr=findElementWithXpathWithOutAssert(trDDxpath);
        Assert.assertNotNull("fail phone dropdown cant found",ddtr);
        javascriptclickerByElement(ddtr);
        waitSeconds(1);
        boolean isFormadTr=findElementWithXpathWithOutAssert(trFlagXpath)!=null;
        Assert.assertTrue("tr seçilmedi!!!",isFormadTr);
        WebElement element=findElementWithXpathWithOutAssert(phonexpath);
        clearTextWithElement(element,"asd");
        writeTextWithElement(element,trNumber,"asd");
    }
    @Step("Check Mandotory fileds <mandatoryPlacesNames>")
    public void checkAlerts(String mandatoryPlacesNames){
        String[] names=mandatoryPlacesNames.split("\\.");
        String xpath="//*[contains(text(),'Please enter NAMEHERE')]";
        for (String name:names){
            Assert.assertNotNull(
                    "'"+name+" cannot be blank' Warning cannot found",
                    findElementWithXpathWithOutAssert(
                            xpath.replaceAll("NAMEHERE",name)));
            logger.info("'"+name+" cannot be blank' warning found");
        }
    }
}