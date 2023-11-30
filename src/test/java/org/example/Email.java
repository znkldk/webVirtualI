package org.example;

import AccountHandle.AccountHandle;
import com.thoughtworks.gauge.Step;
import org.example.Rest.RestAssure;
import org.junit.Assert;

public class Email extends BaseSteps {
    public static String newPass;
    @Step("Check e mail <text>")
    public void checkEmailContainsText(String text) {
        logger.info("Email is checking...");
        logger.info("20 second waiting for mail receive...");
        waitSeconds(20);
        RestAssure restAssure = new RestAssure();
        waitSeconds(2);
        String mailUrl = "https://api.testmail.app/api/json?apikey=5fd43ba5-ddf9-45a1-ac5f-9278b01a48d2&namespace=cdknn&pretty=true";
        String mailText = restAssure.sentTheGet(mailUrl).prettyPrint();
        logger.info("Checking message: " + text);
        Assert.assertTrue(text + " Cant Find in Email!!!", mailText.contains(text));
    }

    @Step("Create and Write Email for Forget Password")
    public void forgetPassCreateMail() {
        logger.info("Email is checking...");
        AccountHandle.createMailAndCreateKey();
        String newMail = "vrsautomationtest+" + AccountHandle.newApiValue + "@gmail.com";
        findElementWithKey("ForgetPassword-email-TextBox").sendKeys(newMail);
    }

    @Step("Forgot passwords get reset password link and open it")
    public void forgetPassGetLink() {
        String mailApi = "https://vrs-test-1-default-rtdb.europe-west1.firebasedatabase.app/";
        RestAssure restAssure = new RestAssure();
        String newJsonString = "{\"link\": "+false+"}";
        restAssure.updateJsonWithPatch(mailApi , "SignUp" , newJsonString);
        logger.info("Email is checking...");
        waitSeconds(10);

        String resetPasswordLink = null;

        /*Get Key*/
        for (int i = 0; i < 10; i++) {
            resetPasswordLink = restAssure.sentTheGetForSpecsItem(mailApi , "SignUp" , "link");
            if(!resetPasswordLink.equals("false")) break;
            waitSeconds(5);
            if(i > 8) Assert.fail("Mail Not Received");
        }
        goToLink(resetPasswordLink);
        waitSeconds(3);
    }
    @Step("Create and Set New Password <key>")
    public void readAndWriteText(String key){
        AccountHandle.createMailAndCreateKey();
        newPass = "Bouncer!"+AccountHandle.newApiValue ;
        logger.info("New Password = "+ newPass);
        writeTextWithKey(newPass,key);
    }

    @Step("Login With New Password <key>")
    public void loginNewPass(String key){
        writeTextWithKey(newPass,key);
    }

}
