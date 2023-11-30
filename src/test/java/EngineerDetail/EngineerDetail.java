package EngineerDetail;

import AccountHandle.AccountHandle;
import Inspection.InspectionDetailMethods;
import com.thoughtworks.gauge.Step;
import org.example.BaseSteps;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class EngineerDetail extends BaseSteps {
    Logger logger = Logger.getLogger(String.valueOf(InspectionDetailMethods.class));
    BaseSteps baseSteps = new BaseSteps();

    @Step("Create an email and write then record this mail")
    public void createRandomMail() {
        AccountHandle.getKeyOnApi();
        writeTextWithKey(AccountHandle.mailAddressCurrent, "RiskEng-Email-TxtBox");
        logger.info("New mail adress created and recorded");
    }

    @Step("Get key on api and create mail")
    public void onlyCreateMail() {
        AccountHandle.createMailAndCreateKey();
    }

    @Step("Increase risk engineer number")
    public void increase() {
        AccountHandle.getKeyOnApi();
    }

    @Step("Write email to <key>")
    public void readAndWriteEmail(String key) {
        AccountHandle.getMailForSpec();
        baseSteps.writeTextWithKey(AccountHandle.mailAddressCurrent, key);
    }

    @Step("Write email for Risk Engineer <key>")
    public void readAndWriteForRiskEngineerEmail(String key) {
        AccountHandle.createMailAndCreateKey();
        baseSteps.writeTextWithKey(AccountHandle.mailAddressCurrent, key);
    }

    @Step("Write text That in Txt to <key >")
    public void readAndWriteText(String key) {
        AccountHandle.createMailAndCreateKey();
        baseSteps.writeTextWithKey(AccountHandle.newApiValue, key);
    }

    public String readAndgetTxtValue() {
        AccountHandle.createMailAndCreateKey();
        return AccountHandle.newApiValue;
    }

    @Step("Is Mail On The List Check It")
    public void IsMailOnTheListCheckIt() {
        waitBySeconds(2);

        clicktWithKey("RiskEng-Refresh-Btn");
        waitBySeconds(1);

        WebElement element = findElementWithXpathWithOutAssert("//*[.='" + AccountHandle.mailAddressCurrent + "']");
        Assert.assertNotNull(AccountHandle.mailAddressCurrent + " cant found on the list Please Check it", element);
    }
}
