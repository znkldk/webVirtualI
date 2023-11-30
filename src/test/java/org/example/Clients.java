package org.example;

import AccountHandle.AccountHandle;
import EngineerDetail.EngineerDetail;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class Clients extends BaseSteps{

    @Step("Client delete Account <text> <dropdown>")
    public void dropDownSelecter(String value,String dropdown){
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]/..//*[@class=\"fa fa-trash\"]";
        clicktWithKey(dropdown);
        waitSeconds(1);
        clicktWithKey("Clients-AccountDelete-Btn");
        waitSeconds(1);
        clicktWithKey(dropdown);
        waitSeconds(1);

        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdownunda "+value+" elementi bulunamadı xpath: "+valueBaseXpath,
                element);
        clickByElement(element);
        waitSeconds(1);

        //check the dd

        String valueBaseXpath2="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]/..//*[@class=\"fa fa-trash\"]";

        clicktWithKey(dropdown);
        waitSeconds(1);

        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element2=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNull(dropdown+" dropdownunda "+value+" elementi bulundu silme başarısız!!! xpath: "+valueBaseXpath,
                element2);
        clicktWithKey(dropdown);

        waitSeconds(2);

    }

    @Step("Calient New Account <text> <dropdown>")
    public void dropDownSelecterNew(String value,String dropdown){
        writeTextWithKey(value,dropdown);
        waitSeconds(1);
        String valueBaseXpath="//*[contains(@style,'center') and not(contains(@style,'none'))]//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'VALUEHERE')]";
        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdownunda "+value+" elementi bulunamadı xpath: "+valueBaseXpath,
                element);
        clickByElement(element);
        waitSeconds(1);

    }
    @Step("Client name fill with risk engineer number")
    public void fillWithRiskEngineerNumber(){
        WebElement element=findElementWithKey("Clients-Name-TextBox");
        AccountHandle.createMailAndCreateKey();

        BaseSteps.writeTextWithElement(element,AccountHandle.newApiValue,"client name");
    }

}
