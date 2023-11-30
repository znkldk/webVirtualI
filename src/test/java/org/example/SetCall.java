package org.example;

import AccountHandle.AccountHandle;
import com.thoughtworks.gauge.Step;
import org.example.Rest.RestAssure;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SetCall extends BaseSteps {
    public static String Message;
    public static String CallLink;
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    private final String mailUrl = "https://api.testmail.app/api/json?apikey=5fd43ba5-ddf9-45a1-ac5f-9278b01a48d2&namespace=cdknn&pretty=true";

    @Step("Get The Call link And Answer It")
    public void getTheCallLinkAndAnswerIt() {
        Methods methods = new Methods();
        String link;
        link = methods.getTextFromElement("Call-Link-Text")
                .replaceAll("link_outline", "")
                .replaceAll(" ", "")
                .replaceAll("\n", "");
        CallLink = link;
        goToLinkNewTab(link);

    }

    @Step("Get The Call link And Answer It New Window")
    public void getTheCallLinkAndAnswerItNewWindow() {
        Methods methods = new Methods();
        String link;
        link = methods.getTextFromElement("Call-Link-Text")
                .replaceAll("link_outline", "")
                .replaceAll(" ", "")
                .replaceAll("\n", "");
        CallLink = link;
        goToLinkNewWindow(link);

    }

    @Step("Check Email")
    public void checkMail() {
        logger.info("Email is checking...");
        logger.info("20 second waiting for suring mail receive...");
        waitSeconds(20);

        String mailText = RestAssure.sentTheGet(mailUrl).prettyPrint();
        logger.info("Checking message: " + Message);
        logger.info("Checking link: " + CallLink);

        Assert.assertTrue(CallLink + " Cant Find in Email!!!",
                mailText.contains(CallLink));
    }

    @Step("Check Email with text <text>")
    public void checkMail(String text) {
        logger.info("Email is checking...");
        logger.info("20 second waiting for suring mail receive...");
        waitSeconds(20);

        String mailText = RestAssure.sentTheGet(mailUrl).prettyPrint();
        logger.info("Checking message: " + text);
        logger.info("Checking link: " + CallLink);

        Assert.assertTrue(text + " Cant Find in Email!!!", mailText.contains(text));
    }


    @Step("Fill Message Content with unique content")
    public void fillMessageContentWithUniqueContent() {
        BaseSteps baseSteps = new BaseSteps();
        AccountHandle.createMailAndCreateKey();
        Message = "Automation test znkldk " + AccountHandle.newApiValue;

        baseSteps.writeTextWithKey(Message, "SetCall-MessageContent-TxtBox");
    }

    @Step("Set Call Try Wrong Email Addresses")
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
        baseSteps.clicktWithKey("SetCall-Contact Email-TextBox");

        for (String wrong : WrongAddresses) {
            baseSteps.clearTextWithKey("SetCall-Contact Email-TextBox");
            baseSteps.writeTextWithKey(wrong, "SetCall-Contact Email-TextBox");
            Assert.assertTrue("Wrong mail address is accepted!!! " + wrong
                    , methods.doesElementExistWithKeyLessTimeOut("SetCall-Contact Email-Err"));
        }

        waitSeconds(2);
        for (String correct : trueAddresses) {
            baseSteps.clearTextWithKey("SetCall-Contact Email-TextBox");
            baseSteps.writeTextWithKey(correct, "SetCall-Contact Email-TextBox");
            Assert.assertFalse("correct mail address is NOT accepted!!! " + correct
                    , methods.doesElementExistWithKeyLessTimeOut("SetCall-Contact Email-Err"));
        }
        baseSteps.clearTextWithKey("SetCall-Contact Email-TextBox");

    }

    @Step("Set Call Try Wrong Phone number")
    public void tryWrongPhoneNumbers() {
        BaseSteps baseSteps = new BaseSteps();
        Methods methods = new Methods();
        String[] WrongAddresses =
                "123 312 asd 552284474 5555555555"
                        .split(" ");
        for (String wrong : WrongAddresses) {
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.writeTextWithKey(wrong, "SetCall-Contact Phone-TextBox");
            baseSteps.javascriptclicker("SetCall-StartCAll-Btn");
            Assert.assertTrue("Wrong phone number is accepted!!! " + wrong
                    , methods.doesElementExistWithKeyLessTimeOut("SetCall-ContactPhone-Err"));
        }
    }

    @Step("Call Check Video streaming is off")
    public void checkVideoStreamingIsOff() {
        String consoleLog = getConsoleLog();
        Assert.assertTrue("!!! video Stream is not muted consoleLog: " + consoleLog,
                consoleLog.contains("Muted video stream"));
    }

    @Step("Call Check Voice streaming is off")
    public void checkVoiceStreamingIsOff() {
        String consoleLog = getConsoleLog();
        Assert.assertTrue("!!! audio Stream is not muted consoleLog: " + consoleLog,
                consoleLog.contains("Muted audio stream"));
    }


    @Step("Call Check agora log for voice transmission")
    public void checkLogForVoiceTransmission() {
        String expectedText = "" +
                "isErrorState: false \n" +
                "mediaType: \"audio\"\n" +
                "playerId: 0\n" +
                "reason: \"playing\"\n" +
                "status: \"play\"";

        String consoleLog = getConsoleLog();
        Assert.assertTrue("!!! audio Stream is not working consoleLog: " + consoleLog,
                consoleLog.contains(expectedText));
    }

    @Step("CAll Check Agora log for joined successfully")
    public void callCheckAgoraLogForJoinedSuccessfully() {
        String expectedText = "joined successfully";

        String consoleLog = getConsoleLog();
        Assert.assertTrue("expectedText: " + expectedText + "!!! video Stream is not working consoleLog: " + consoleLog,
                consoleLog.contains(expectedText));
    }

    @Step("Call Check agora log for video transmission")
    public void checkLogForVideoTransmission() {
        String expectedText = "" +
                "isErrorState: false \n" +
                "mediaType: \"video\"\n" +
                "playerId: 0\n" +
                "reason: \"playing\"\n" +
                "status: \"play\"";

        String consoleLog = getConsoleLog();
        Assert.assertTrue("!!! video Stream is not working consoleLog: " + consoleLog,
                consoleLog.contains(expectedText));
    }

    @Step("Call check agora log for end stream")
    public void callCheckAgoraLogForEndStream() {
        String expectedText = "Leaving channel";
        String consoleLog = getConsoleLog();
        Assert.assertTrue("!!! video Stream is not end consoleLog: " + consoleLog,
                consoleLog.contains(expectedText));
    }

    private String getConsoleLog() {
        StringBuilder allLog = new StringBuilder();
        System.out.println("log test");
        LogEntries logEntries = webDriver.manage().logs().get(LogType.BROWSER);
        System.out.println("log test");
        for (LogEntry entry : logEntries) {
            allLog.append(entry);
        }
        return allLog.toString();
    }

    @Step("Call Add to Call List Contains <text> else <errorMessage>")
    public void doesAddGuestAddToCallListContainsText(String text, String errorMessage) {
        logger.info("Checking List for Text: " + text);

        String baseXpath = "//*[@class=\"el-table__body\"]//*[contains(text(),'TEXTHERE')]";
        baseXpath = baseXpath.replaceAll("TEXTHERE", text);
        logger.info("Xpath to search:" + baseXpath);
        WebElement element = findElementWithXpathWithOutAssert(baseXpath);
        Assert.assertNotNull(errorMessage + " \n xpath: " + baseXpath,
                element);
        logger.info("text: " + text + " found in list");
    }

    @Step("Call did Message Received <expectedMessage>")
    public void checkMessageAreaForExpectedText(String expectedMessage) {
        String baseXpath = "//*[@class=\"message-list-box\"]//*[contains(text(),'expectedMessageHere')]";
        baseXpath = baseXpath.replaceAll("expectedMessageHere", expectedMessage);

        WebElement element = findElementWithXpathWithOutAssert(baseXpath);
        Assert.assertNotNull("Expected Message cant find expected message is : " +
                expectedMessage, element);
        logger.info("Expected Message found text: " + expectedMessage);

    }

    @Step("Check only one tab exist")
    public void checkOnyOneTabExist() {
        ArrayList<String> wid = new ArrayList<>(webDriver.getWindowHandles());
        Assert.assertEquals("Call tab did not close", 1, wid.size());
    }
}
