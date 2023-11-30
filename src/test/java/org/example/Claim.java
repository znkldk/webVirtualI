package org.example;

import EngineerDetail.EngineerDetail;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class Claim extends BaseSteps{
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    String mondotoryFields="Insured Reference Num#Policy#Incident Date#Loss Type#Incident Description";

    @Step("Fill TextBox with <text> By Text <TextBoxText>")
    public void fillTextBoxWithText(String text, String textBoxText){
        String xpath="//*[contains(text(),'"+textBoxText+"')]/..//*[@type=\"text\" or @class=\"el-textarea__inner\"]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(xpath+" not found",element);
        writeTextWithElement(element,text,"asd");
    }

    @Step("Is area required <text>")
    public void isAreaRequired(String text){
        String xpath="//*[@class=\"el-form-item is-required\"]//*[contains(text(),'"+text+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(text+" Area Not Mondotary !!!!",element);
    }

    @Step("Claim Check mondotory fields Alerts")
    public void checkMondotoryFields(){
        String xpath="//*[contains(text(),'TEXTHERE')]/..//*[contains(text(),'Field is required.')]";
        String[] titles=mondotoryFields.split("#");
        WebElement element;
        for (String title:titles){
            xpath=xpath.replaceAll("TEXTHERE",title);
            element=findElementWithXpathWithOutAssert(xpath);
            Assert.assertNotNull("Area Not Mondotory",element);
        }
    }

    @Step("Dropdown name <name> select <Choice>")
    public void dropdownSelectByText(String name,String Choice){
        SurveyManagement surveyManegment=new SurveyManagement();
        String xpath="//*[contains(text(),'"+name+"')]/..//*[@class=\"el-select__caret el-input__icon el-icon-arrow-up\"]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("name: "+name+" dropdownı bulunamadı",element);
        surveyManegment.dropDownSelecterWithElement(element,Choice,name);
    }

    @Step("Celendar select today <name>")
    public void celendarName(String name){
        String xpath="//*[contains(text(),'"+name+"')]/..//*[contains(@class,\"el-input__icon el-icon\")]";
        String todayXpath="//*[@class=\"el-date-table\"]//*[@class=\"available today\"]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        hoverByElement(element);
        waitSeconds(1);
        clickByElement(element);
        waitSeconds(1);
        WebElement today=findElementWithXpathWithOutAssert(todayXpath);
        clickByElement(today);
        waitSeconds(1);
    }

    @Step("Notes Check Message Sent <message>")
    public void checkSentMessage(String message){
        String xpath="//*[@class=\"note-body\"]//*[@class=\"note-box sent\"]//*[.='"+message+"']";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("message Cant Found ",element);
    }

    @Step("Claim upload All Kind Of files")
    public void uploadAllKindOfFiles(){
        UploadFile uploadFile=new UploadFile();
        GridMethods gridMethods=new GridMethods();
        WebElement element=findElementWithKey("Claim-Upload-Upload");

        String absolutePath=uploadFile.getRandomFileFromDirectory("Img");
        logger.info("image Path="+absolutePath);
        BaseSteps.writeTextWithElement(element,absolutePath,"Images");
        waitSeconds(3);
        clearTextWithKey("Claim-FileName-TextBox");
        writeTextWithKey("image","Claim-FileName-TextBox");
        clicktWithKey("Claim-ChangeFileNameSave-Btn");
        absolutePath=uploadFile.getRandomFileFromDirectory("Doc");

        logger.info("Doc Path="+absolutePath);
        BaseSteps.writeTextWithElement(element,absolutePath,"Doc");
        waitSeconds(3);
        clearTextWithKey("Claim-FileName-TextBox");
        writeTextWithKey("Doc","Claim-FileName-TextBox");
        clicktWithKey("Claim-ChangeFileNameSave-Btn");

        absolutePath=uploadFile.getRandomFileFromDirectory("Sound");
        logger.info("Sound Path="+absolutePath);
        BaseSteps.writeTextWithElement(element,absolutePath,"Sound");
        waitSeconds(3);
        clearTextWithKey("Claim-FileName-TextBox");
        writeTextWithKey("Sound","Claim-FileName-TextBox");
        clicktWithKey("Claim-ChangeFileNameSave-Btn");

        absolutePath=uploadFile.getRandomFileFromDirectory("Video");
        logger.info("Video Path="+absolutePath);
        BaseSteps.writeTextWithElement(element,absolutePath,"Video");
        waitSeconds(3);
        clearTextWithKey("Claim-FileName-TextBox");
        writeTextWithKey("Video","Claim-FileName-TextBox");
        clicktWithKey("Claim-ChangeFileNameSave-Btn");

        gridMethods.doesGridHasTheTextWithTitle("Doc","Documents");
        gridMethods.doesGridHasTheTextWithTitle("image","Documents");
        gridMethods.doesGridHasTheTextWithTitle("Video","Documents");
        gridMethods.doesGridHasTheTextWithTitle("Sound","Documents");
    }

    @Step("Claim list Check headers")
    public void checkHeaders(){
        String titles="System Ref Number#Insured Ref Number#Insurer Ref Number#Date of Submission#Country#Policy#Status";
        String[] gridtabs=titles.split("#");
        String xpath="//thead//*[contains(text(),'TABHERE')]";
        WebElement element;
        for (String s:gridtabs){
            xpath=xpath.replaceAll("TABHERE",s);
            element=findElementWithXpathWithOutAssert(xpath);
            logger.info(s+ " cheking...");
            Assert.assertNotNull(s+ "cant found on call history tab",element);
        }
    }

    @Step("Claim Display Check <Title> <Value>")
    public void checkDisplay(String title,String value){

        if (value.contains("LastRiskEngineer") || value.contains("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            value=engineerDetail.readAndgetTxtValue();
        }
        String xpath="//*[contains(text(),'"+title+"')]/..//*[contains(text(),'"+value+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(title+" "+value+" Cant found in display",element);
        logger.info(title+" "+value+" found");

    }

    @Step("Claim Notification Click Test <text> with <with> And go detail")
    public void claimNotification(String text,String with){

        waitSeconds(2);
        if (text.contains("LastRiskEngineer") || text.contains("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }

        String xpath="//*[contains(@aria-labelledby,\"read\") and not(@style=\"display: none;\")]//*[contains(text(),'"+text+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(text+" cant found in notifications",element);

        String elementText=element.getText();
        Assert.assertTrue("expected text cant found",elementText.contains(with));
        element.click();
        waitSeconds(2);
        clickButtonWithText("Detail");
        waitSeconds(2);
        String claimdetailTitlexpath="//*[.='Claim Display']";
        WebElement element2=findElementWithXpathWithOutAssert(claimdetailTitlexpath);
        Assert.assertNotNull(text+" Claim Detail Did Not Open",element2);
    }

}
