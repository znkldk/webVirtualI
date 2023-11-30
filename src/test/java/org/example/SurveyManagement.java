package org.example;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

public class SurveyManagement extends BaseSteps {
    private final String searchXpath = "//*[@type=\"button\"]//*[contains(@class,'search')]";
    private final String editXpath = "//*[@class=\"fa fa-edit\"]";
    private final String eyeXpath = "//*[@class=\"fa fa-eye\"]";
    private final String arrowXpath = "//*[@class=\"fa fa-location-arrow\"]";
    private final String chartXpath = "//*[@class=\"fa fa-bar-chart\"]";
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));

    @Step("Survey Click <iconName> icon By Text <text>")
    public void surveyManagementClickIcon(String iconName, String text) {
        String baseXpath = "//*[contains(@class,\"position-relative route\")]//*[contains(text(),'TEXTHERE')]/ancestor::div[contains(@class,\"position-relative route\")]";
        baseXpath = baseXpath.replaceAll(
                "TEXTHERE",
                text);
        String finalXpath = baseXpath;
        if (iconName.contains("search")) {
            finalXpath = finalXpath + searchXpath;
        } else if (iconName.contains("edit")) {
            finalXpath = finalXpath + editXpath;
        } else if (iconName.contains("eye")) {
            finalXpath = finalXpath + eyeXpath;
        } else if (iconName.contains("arrow")) {
            finalXpath = finalXpath + arrowXpath;
        } else if (iconName.contains("chart")) {
            finalXpath = finalXpath + chartXpath;
        } else {
            Assert.fail("icon name is not proper please Check it icon name:" + iconName);
        }
        WebElement element = findElementWithXpathWithOutAssert(finalXpath);
        Assert.assertNotNull("iconName: " + iconName + " did not find", element);
        clickByElement(element);
        waitSeconds(1);
    }

    @Step("Survey Check icons before finalize By Text <text>")
    public void surveyManagementCheckIconBeforeFinalize(String text) {
        String baseXpath = "//*[contains(@class,\"position-relative route\")]//*[contains(text(),'TEXTHERE')]/ancestor::div[contains(@class,\"position-relative route\")]";
        baseXpath = baseXpath.replaceAll(
                "TEXTHERE",
                text);

        Assert.assertNotNull("missing search icon in inspection (not finalized)",
                findElementWithXpathWithOutAssert(baseXpath + searchXpath));

        Assert.assertNotNull("missing edit icon in inspection (not finalized)",
                findElementWithXpathWithOutAssert(baseXpath + editXpath));

        Assert.assertNotNull("missing eye icon in inspection (not finalized)",
                findElementWithXpathWithOutAssert(baseXpath + eyeXpath));

        Assert.assertNotNull("missing arrow icon in inspection (not finalized)",
                findElementWithXpathWithOutAssert(baseXpath + arrowXpath));

        Assert.assertNull("chart icon in inspection (not finalized)",
                findElementWithXpathWithOutAssert(baseXpath + chartXpath));

    }

    @Step("Survey Choose <value> from <dropDown> dropdown")
    public void dropDownSelecter(String value, String dropdown) {
        String valueBaseXpath = "//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\" or contains(@ class,\"dropdown\")]/..//*[contains(text(),'VALUEHERE')]";
        clicktWithKey(dropdown);
        waitSeconds(1);

        valueBaseXpath = valueBaseXpath.replaceAll("VALUEHERE", value);
        WebElement element = findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown + " dropdownunda " + value + " elementi bulunamadı xpath: " + valueBaseXpath, element);
        clickByElement(element);
    }

    public void dropDownSelecterWithElement(WebElement elementDropdown, String value, String dropdown) {
        String valueBaseXpath = "//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\" or contains(@ class,\"dropdown\")]/..//*[contains(text(),'VALUEHERE')]";

        clickByElement(elementDropdown);
        waitSeconds(1);

        valueBaseXpath = valueBaseXpath.
                replaceAll("VALUEHERE", value);
        WebElement element = findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown + " dropdownunda " + value + " elementi bulunamadı xpath: " + valueBaseXpath,
                element);
        clickByElement(element);
    }

    @Step("Call open the form tab")
    public void formtab() {
        waitSeconds(2);
        ArrayList<String> wid = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(wid.get(2));
        waitSeconds(2);
    }

    @Step("Survey new Inspection page Check Date Is today")
    public void surveyCheckDate() {
        String baseXpath = "//*[@class=\"available today current\"]//*[contains(text(),'TODAYHERE')]";

        baseXpath = baseXpath.replaceAll("TODAYHERE", getdate());
        WebElement element = findElementWithXpathWithOutAssert(baseXpath);
        Assert.assertNotNull(" Today is not selected today is :" + getdate(), element);
    }

    @Step("Survey Check icons after finalize By Text <text>")
    public void surveyManagementCheckIconAfterFinalize(String text) {
        String baseXpath = "//*[contains(@class,\"position-relative route\")]//*[contains(text(),'TEXTHERE')]/ancestor::div[contains(@class,\"position-relative route\")]";
        baseXpath = baseXpath.replaceAll("TEXTHERE", text);

        Assert.assertNotNull(" search icon in inspection (finalized)",
                findElementWithXpathWithOutAssert(baseXpath + searchXpath));

        Assert.assertNull(" edit icon in inspection (finalized)",
                findElementWithXpathWithOutAssert(baseXpath + editXpath));

        Assert.assertNull(" eye icon in inspection (finalized)",
                findElementWithXpathWithOutAssert(baseXpath + eyeXpath));

        Assert.assertNull(" arrow icon in inspection (finalized)",
                findElementWithXpathWithOutAssert(baseXpath + arrowXpath));

        Assert.assertNotNull("missing chart icon in inspection (finalized)",
                findElementWithXpathWithOutAssert(baseXpath + chartXpath));
    }

    private String getdate() {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);

        dt = c.getTime();
        String[] time = String.valueOf(dt).split(" ");

        logger.info("Today is: " + Integer.valueOf(time[2]));
        return String.valueOf(Integer.valueOf(time[2]));
    }
}