package org.example;

import AccountHandle.AccountHandle;
import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import org.example.Rest.RestAssure;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class DashBoard extends BaseSteps {
    public static String riskEngineerName;
    public static int surveysAllTime;
    public static int surveyThisMounth;
    public static int ClientLocations;
    public static int riskEngineers;
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    private final String mailUrl = "https://api.testmail.app/api/json?apikey=5fd43ba5-ddf9-45a1-ac5f-9278b01a48d2&namespace=cdknn&pretty=true";
    private String baseXpath = "//*[@class=\"el-card__header\"]//*[contains(text(),'TITLEHERE')]/../../..//*[contains(@class,'el-row')]/div[1]";

    @Step("Dashboard Store All values")
    public void dashboardStoreAllValues() {
        waitSeconds(2);
        WebElement element;

        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Surveys/All Time"));
        Assert.assertNotNull("Surveys/All Time number not found ", element);
        surveysAllTime = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));

        logger.info("surveysAllTime value is :" + surveysAllTime);
        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Surveys This Month"));
        Assert.assertNotNull("Surveys This Month number not found ", element);
        surveyThisMounth = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));
        logger.info("surveyThisMounth value is :" + surveyThisMounth);

        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Client Locations"));
        Assert.assertNotNull("Client Locations number not found ", element);
        ClientLocations = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));
        logger.info("ClientLocations value is :" + ClientLocations);

        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Risk Engineers"));
        Assert.assertNotNull("Risk Engineers Time number not found ", element);
        riskEngineers = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));
        logger.info("riskEngineers value is :" + riskEngineers);
        logger.info("Dashboard values is recorded");

    }

    @Step("DashBoard check RiskEngineer Count plus one")
    public void checkRiskEngineerCount() {
        waitSeconds(1);
        int lastRiskEngineerNum;
        WebElement element = findElementWithXpathWithOutAssert(baseXpath.replaceAll("TITLEHERE", "Risk Engineers"));
        Assert.assertNotNull("Risk Engineers Time number not found ", element);
        lastRiskEngineerNum = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));
        Assert.assertEquals("Dashboarh count does not increase", riskEngineers + 1, lastRiskEngineerNum);
    }

    @Step("DashBoard check Clients Count plus <num>")
    public void checkClientCount(int num) {
        waitSeconds(1);
        int lastClientLocations;
        WebElement element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Client Locations"));
        Assert.assertNotNull("Client Locations number not found ", element);
        lastClientLocations = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));
        Assert.assertEquals("Dashboarh count does not increase",
                ClientLocations + num,
                lastClientLocations);
    }

    @Step("Dashboard CheckAll survey values increase <num>")
    public void surveyValues(int i) {
        waitSeconds(2);
        WebElement element;
        int lastsurveysAllTime;
        int lastsurveyThisMounth;
        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Surveys/All Time"));
        Assert.assertNotNull("Surveys/All Time number not found ", element);
        lastsurveysAllTime = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));

        element = findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TITLEHERE", "Surveys This Month"));
        Assert.assertNotNull("Surveys This Month number not found ", element);
        lastsurveyThisMounth = Integer.parseInt(
                element
                        .getText()
                        .replaceAll(" ", "")
                        .replaceAll("\n", ""));

        Assert.assertEquals("New SurveyAll Value is not correct",
                surveysAllTime + i,
                lastsurveysAllTime);

        Assert.assertEquals("New Survey Month Value is not correct",
                surveyThisMounth + i,
                lastsurveyThisMounth);
    }

    @Step("Sing Up if Text Not Exist Fail <text> <failMessage>")
    public void checkForText(String text, String failMessage) {
        String xpath =
                "//*[contains(text(),'" + text + "')]";

        WebElement element =
                findElementWithXpathWithOutAssert(xpath);

        Assert.assertNotNull(
                "xpath: " + xpath + "\n" +
                        failMessage,
                element);
    }

    @Step("Sing in Try Wrong Email Addesses")
    public void tryWrongMailAddresses() {
        BaseSteps baseSteps = new BaseSteps();
        Methods methods = new Methods();
        String[] WrongAddresses =
                "znkldk znkldk1 asd@asd asd@ @asdv .com@asd .com asd@asd.c Wrong@as@hell @this.is@Wrongtoo @@@@ little@wrong.a hell.no Thisis.fun"
                        .split(" ");

        String[] trueAddresses =
                "znkldk@asd.com asd.ads@mail.aa true@mail.think much@more.True This@islook.wrongBut.youAre.wrong.this.is.legit.address"
                        .split(" ");
        waitSeconds(2);
        baseSteps.clearTextWithKeyUsingClear("SingUp-Email-TextBox");

        baseSteps.clicktWithKey("SingUp-Email-TextBox");

        for (String wrong : WrongAddresses) {
            baseSteps.clearTextWithKeyUsingClear("SingUp-Email-TextBox");
            baseSteps.writeTextWithKey(wrong, "SingUp-Email-TextBox");
            Assert.assertTrue("Wrong mail address is acceptted!!! " + wrong
                    , methods.doesElementExistWithKeyLessTimeOut("Singup-email-Err"));
        }
        baseSteps.clearTextWithKey("SingUp-Email-TextBox");
    }

    @Step("Singup Write text That in Txt to <key> and add <text>")
    public void readAndWriteTexta(String key, String text) {
        BaseSteps baseSteps = new BaseSteps();
        AccountHandle.getKeyOnApi();
        logger.info("Created Mail = "+ AccountHandle.newApiValue + text);
        baseSteps.writeTextWithKey(AccountHandle.newApiValue + text, key);
    }

    @Step("Singup Write text That in Txt to <key> and add <text> not increase number")
    public void readAndWriteTextaa(String key, String text) {
        BaseSteps baseSteps = new BaseSteps();
        AccountHandle.createMailAndCreateKey();
        baseSteps.writeTextWithKey(AccountHandle.newApiValue + text, key);
    }

    @Step("SingUp is Name Correct")
    public void isNameCoreect() {
        waitSeconds(1);
        AccountHandle.createMailAndCreateKey();
        checkForText(AccountHandle.newApiValue, "Name is Not Correct" + AccountHandle.newApiValue);
    }

    @Step("Sing Up Try Wrong Phone number")
    public void tryWrongPhoneNumbers() {
        BaseSteps baseSteps = new BaseSteps();
        Methods methods = new Methods();
        String[] WrongAddresses =
                "123 312 552284474 55555555555"
                        .split(" ");

        String[] trueAddresses =
                "5522844745"
                        .split(" ");

        for (String wrong : WrongAddresses) {
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.writeTextWithKey(wrong, "SetCall-Contact Phone-TextBox");
            baseSteps.clicktWithKey("Login-SingUp-Btn");
            Assert.assertTrue("Wrong phone number is acceptted!!! " + wrong
                    , methods.doesElementExistWithKeyLessTimeOut("SetCall-ContactPhone-Err"));
        }
    }

    @Step("Go to <page>")
    public void menuNav(String page) {
        String btnXpath = "//*[@role=\"menuitem\"]//*[contains(text() ,'" + page + "')]";
        String titleXpath = "//*[@class=\"el-header app-header\"]//*[contains(text() ,'" + page + "')]";
        findElementWithXpathWithOutAssert(btnXpath).click();
        WebElement element = findElementWithXpathWithOutAssert(titleXpath);
        Assert.assertNotNull(titleXpath + " is not exist \n" + page + "page did not open", element);
    }
}