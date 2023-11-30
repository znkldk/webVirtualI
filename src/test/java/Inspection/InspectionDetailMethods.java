package Inspection;

import com.thoughtworks.gauge.Step;
import org.example.*;
import org.example.Rest.RestAssure;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import javax.xml.stream.FactoryConfigurationError;
import io.restassured.response.Response;
import java.io.File;
import java.util.List;
import java.util.logging.Logger;

public class InspectionDetailMethods extends Methods {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));

    //*[@class="el-card__body"]/div[@role="tablist"]//*[@role="button" and contains(text() ,'LOSS')]/ancestor::div[@role="tablist"]//*[@role="button" and contains(text() ,'Loss')]/ancestor::div[@role="tablist"]
    String ErrorMessage="";
    ErrorMethod errorMethod;

    private static boolean popUpControl=true;
    private static boolean plusBtnControls=true;

    @Step("Make pupup controls disable")
    public void makepopupcontrolsdisable(){
        popUpControl= false;
        logger.info("pop up controls turned off");
    }

    @Step("Make plus Btn  controls disable")
    public void makePlusBtncontrolsdisable(){
        plusBtnControls= false;
        logger.info("plus Btn Controls turned off");
    }

    public void makeTheChecks(String path, Response r, ErrorMethod errMethod,int i){
        errorMethod=errMethod;
        int questionsSize;
        List<JSONObject> l=r.getBody().jsonPath().get("data.sections["+i+"].questions");
        if (l==null){
            logger.info(path+ " does not have any questions");
            return;

        }
        questionsSize=l.size();
        logger.info("questionsSize: " +questionsSize);
        logger.info(path+" için element kontrolleri yapılıyor...");

        String control_type;
        String caption;

        for (int j=0;j<questionsSize;j++){

            control_type=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].control_type").toString();

            if (control_type.contains("TextBox") || control_type.contains("TextArea")){
                caption=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].caption").toString();
                findTextBoxAndCheck(path, caption);
            }

            else if (control_type.contains("Coordinate")){
                findCoordinatsAndCheck(path);
            }

            else if (control_type.contains("ComboBox") || control_type.contains("CheckBoxList")){
                caption=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].caption").toString();

                findCheckBoxListAndCheckIt(path,caption,r,i,j);

            }
            else if (control_type.contains("Number") || control_type.contains("Money")){
                caption=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].caption").toString();
                findNumberBoxAndCheck(path,caption);

            }
            else if (control_type.contains("Date")){
                caption=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].caption").toString();
                findCelendarAndCheck(path,caption);
            }
            else if (control_type.contains("Video") || control_type.contains("Audio") || control_type.contains("Image")){
                fileUpload(path,control_type);
            }


        }

        /*

        if (fileName.contains("TextBox")){
            findTextBoxAndCheck(path, fileName);
        }else if (fileName.contains("Coordinates")){
            findCoordinatsAndCheck(path);
        }else if (fileName.contains("CheckBoxList")){
            findCheckBoxListAndCheckIt(path,fileName);
        }else if (fileName.contains("NumberBox")){
            findNumberBoxAndCheck(path,fileName);
        }else if (fileName.contains("Celandar")){
            findCelendarAndCheck(path,fileName);
        }

         */

        errorMethod.addErrorMessage(ErrorMessage);
        ErrorMessage="";

    }

    @Step("Inspection Open Title <path>")
    public void findTitleAndClick(String path){
        String xpath;
        xpath=getTitleAddress(path);

        WebElement element=findElementWithXpathWithOutAssert(xpath);

        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+path+" title can not found";
            logger.info("\n"+path+" title can not found!!!");
            return;
        }
        BaseSteps.scroolToElement(element);
        waitBySeconds(1);
        element.click();
        waitBySeconds(1);
    }

    public void findTextBoxAndCheck(String path,String textBoxName){
        String titleXpath;
        String textBoxXpath;
        titleXpath=getTitleAddress(path);
        textBoxName=textBoxName.replaceAll("TextBox\\.","");
        textBoxName=textBoxName.replaceAll("\\.txt","");
        logger.info("Checking Textbox path:"+path);
        logger.info("Checking Textbox Name:"+textBoxName);

        textBoxXpath=titleXpath+"//*[@placeholder=\"textBoxNameHere\"]".replaceAll("textBoxNameHere",textBoxName);
        WebElement element=findElementWithXpathWithOutAssert(textBoxXpath);
        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+path+" TextBox can not found TextBoxName: "+textBoxName;
            return;
        }

        BaseSteps.clickByElement(element);
        BaseSteps.writeTextWithElement(element,"T",textBoxName);
        waitBySeconds(1);
        checkPlusExistBtnAndGetPlusBtn(textBoxXpath,path,textBoxName);
        if (plusBtnControls) {
            BaseSteps.clearTextWithElement(element, textBoxName);
            BaseSteps.clickByElement(element);
            checkPlusNotExistBtn(textBoxXpath, path, textBoxName);
            // afterThis PopUp Control
            BaseSteps.clickByElement(element);
            BaseSteps.writeTextWithElement(element, "T", textBoxName);
            waitBySeconds(1);
        }
        WebElement plusBtn=checkPlusExistBtnAndGetPlusBtn(textBoxXpath,path,textBoxName);
        makePopUpChecks(plusBtn,path,textBoxName);
    }

    public void findCelendarAndCheck(String path,String celendarName){

        String titleXpath;
        String toDayXpath;
        String celenarXpath;
        String deleteCelendarXpath="//*[@class=\"el-input__icon el-icon-circle-close\"]";
        titleXpath=getTitleAddress(path);
        celendarName=celendarName.replaceAll("Celandar\\.","");
        celendarName=celendarName.replaceAll("\\.txt","");
        logger.info("Checking Celandar path:"+path);
        logger.info("Checking Celandar Name:"+celendarName);

        toDayXpath="//*[@class=\"el-picker-panel el-date-picker el-popper\" and not(contains(@style,'display: none'))]//table[not(@style=\"display: none;\")]//*[@class=\"available today\"]";
        celenarXpath=titleXpath+"//*[@placeholder=\"Pick a day\"]";
        WebElement celendar=findElementWithXpathWithOutAssert(celenarXpath);
        if (celendar==null){
            ErrorMessage=ErrorMessage+"\n"+path+" Celandar can not found Celandar: date";
            return;
        }
        BaseSteps.clickByElement(celendar);

        WebElement today=findElementWithXpathWithOutAssert(toDayXpath);
        if (today==null){
            ErrorMessage=ErrorMessage+"\n"+path+" today please Check It Celandar: date xpath: "+toDayXpath;
            return;
        }
        BaseSteps.clickByElement(today);
        waitBySeconds(1);
        WebElement plusBtn= checkPlusExistBtnAndGetPlusBtn(celenarXpath,path,celendarName);
        makePopUpChecks(plusBtn,path,celendarName);
        BaseSteps.hoverByElement(celendar);
        WebElement delete=findElementWithXpathWithOutAssert(deleteCelendarXpath);
        if (delete==null) {
            ErrorMessage = ErrorMessage + "\n" + path + " delete celendar is not working please Check It Celandar: date xpath: " + deleteCelendarXpath;
            return;
        }
      waitBySeconds(1);

    }

    public void findNumberBoxAndCheck(String path,String numberBoxName){
        String titleXpath;
        String textBoxXpath;
        titleXpath=getTitleAddress(path);
        numberBoxName=numberBoxName.replaceAll("NumberBox\\.","");
        numberBoxName=numberBoxName.replaceAll("\\.txt","");
        logger.info("Checking Textbox2 path:"+path);
        logger.info("Checking Textbox2 Name:"+numberBoxName);

        textBoxXpath="("+titleXpath+"//span[@class=\"form-label\" and .='textBoxNameHere']/ancestor::div[@style=\"display: block !important;\"])[last()]//*[@type=\"number\" or @type=\"text\"]".replaceAll("textBoxNameHere",numberBoxName);
        WebElement element=findElementWithXpathWithOutAssert(textBoxXpath);
        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+path+" NumberBoxName can not found numberBoxName: "+numberBoxName;
            return;
        }

        BaseSteps.clickByElement(element);
        BaseSteps.writeTextWithElement(element,"1",numberBoxName);
        waitBySeconds(1);
        checkPlusExistBtnAndGetPlusBtn(textBoxXpath,path,numberBoxName);
        if (plusBtnControls) {

            BaseSteps.clearTextWithElement(element, numberBoxName);
            BaseSteps.clickByElement(element);
            checkPlusNotExistBtn(textBoxXpath, path, numberBoxName);

            //plus btn controls
            BaseSteps.writeTextWithElement(element, "1", numberBoxName);
            waitBySeconds(1);
        }
        WebElement plusBtn=checkPlusExistBtnAndGetPlusBtn(textBoxXpath,path,numberBoxName);
        makePopUpChecks(plusBtn,path,numberBoxName);

    }

    public void findCheckBoxListAndCheckIt(String path,String checkBoxNameLink,Response r,int i,int j){
        String titleXpath;
        String checkBoxListXpath;
        String lastXpath;
        logger.info(path+checkBoxNameLink);

        if (checkBoxNameLink.contains("'")){
            checkBoxNameLink=checkBoxNameLink.split("'")[0];
            logger.info("illagal character in "+checkBoxNameLink);
            return;
        }

        titleXpath=getTitleAddress(path);
        checkBoxListXpath="//label[.='CHECKBOXLISTNAMEHERE']/ancestor::div[@style=\"display: block !important;\"])[last()]".replaceAll("CHECKBOXLISTNAMEHERE",checkBoxNameLink);
        checkBoxListXpath="("+titleXpath+checkBoxListXpath;
        WebElement element= findElementWithXpathWithOutAssert(checkBoxListXpath);

        if (element==null){
            logger.info("\n"+path+" CheckBoxList can not found CheckBoxList Name: "+checkBoxNameLink);
            ErrorMessage=ErrorMessage+"\n"+path+" CheckBoxList can not found CheckBoxList Name: "+checkBoxNameLink;
            return;
        }

        //----------
        //checkInside Of Checkbox
        String checkBoxListInside;
        r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].control_type").toString();

        List<JSONObject> checkBoxes=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].control_data");

        if (checkBoxes==null){
            logger.info(path+ " does not have any questions");
            ErrorMessage=ErrorMessage+"\n"+path+" "+checkBoxNameLink+" does not has any checkbox inside please check It "+checkBoxNameLink;

            return;
        }
        int comboboxesSize=checkBoxes.size();
        String stringToCheck;
        boolean anyItemClicked=false;
        for (int k=0;k<comboboxesSize;k++){
            stringToCheck=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].control_data["+k+"].cd_value").toString();
            System.out.println("check value "+stringToCheck);
            if (stringToCheck.contains("'")){

                logger.info("illagal character in "+stringToCheck);
                return;
            }
            checkBoxListInside=checkBoxListXpath+"//label[.='stringToCheck']".replaceAll("stringToCheck",stringToCheck);
        //    checkBoxListInside=checkBoxListXpath+"//label[contains(text(),'stringToCheck')]".replaceAll("stringToCheck",stringToCheck);

            element= findElementWithXpathWithOutAssert(checkBoxListInside);
            if (element==null){
                logger.info("sorun varrrrrr");
                ErrorMessage=ErrorMessage+"\n"+path+" "+ stringToCheck+" can not found CheckBoxList Name: "+checkBoxNameLink;
                return;
            }
            BaseSteps.clickByElement(element);
            anyItemClicked=true;
        }
        if (anyItemClicked){
            WebElement plusBtn=checkPlusExistBtnAndGetPlusBtn(checkBoxListXpath,path,checkBoxNameLink);
            makePopUpChecks(plusBtn,path,checkBoxNameLink);
        }
    }

    public void findCoordinatsAndCheck(String path){
        String titleXpath;
        String coordinatsName="Coordinats";
        String coordinatsFieldXpath;

        titleXpath=getTitleAddress(path);

        coordinatsFieldXpath="//*[@class=\"fa fa-location-arrow\"]/ancestor::div[@style=\"display: block !important;\"])[last()]//*[@type=\"text\"]";
        coordinatsFieldXpath="("+titleXpath+coordinatsFieldXpath;

        WebElement element=findElementWithXpathWithOutAssert(coordinatsFieldXpath);

        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+path+" Coordinates can not found Coordinates Name: "+coordinatsName;
            return;
        }

        BaseSteps.clickByElement(element);
        BaseSteps.writeTextWithElement(element,"40.9171962,29.3166251",coordinatsName);
        waitBySeconds(1);
        checkPlusExistBtnAndGetPlusBtn(coordinatsFieldXpath,path,coordinatsName);
        if (popUpControl) {

            BaseSteps.clearTextWithElement(element, coordinatsName);
            BaseSteps.clickByElement(element);
            checkPlusNotExistBtn(coordinatsFieldXpath, path, coordinatsName);

            //popup control
            BaseSteps.writeTextWithElement(element, "40.9171962,29.3166251", coordinatsName);
            waitBySeconds(1);
        }
        WebElement plusBtn=checkPlusExistBtnAndGetPlusBtn(coordinatsFieldXpath,path,coordinatsName);
        makePopUpChecks(plusBtn,path,"Coordinates");
    }

    public void fileUpload(String path,String kind){

        if (!path.contains("bu metot guncellenecek ilerde !!!!")){
            return;
        }
        String imgXpath="//*[@type=\"file\" and @accept=\"image/*\"])[last()]";
        String audioXpath="//*[@type=\"file\" and @accept=\"audio/*\"])[last()]";
        String videoXpath="//*[@type=\"file\" and @accept=\"video/*\"])[last()]";
        String titleXpath=getTitleAddress(path);
        String lastXpath=null;
        UploadFile uploadFile=new UploadFile();

        String absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Doc");
        if (kind.contains("mage")){//image
            lastXpath="("+titleXpath+imgXpath;
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Img");
        } else if (kind.contains("ideo")){//Video
            lastXpath="("+titleXpath+videoXpath;
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Video");
        }else if (kind.contains("udio")){//audio
            lastXpath="("+titleXpath+audioXpath;
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Sound");
        } else {
            logger.info("guncellemeye ihtiyaç var burayı !!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }

        waitBySeconds(2);

        WebElement element=findElementWithXpathWithOutAssert(lastXpath);

        boolean needReturn=errorMethod.addErrorMessageIfElementNull(
                element,
                "document input element cant found \n"+
                        "path:"+path+"\n"+
                        "kind:"+ kind);
        if (needReturn){
            return;
        }

        BaseSteps.writeTextWithElement(element,absolutePathOfVideo,"uploads");
        waitBySeconds(3);


    }

    public String getTitleAddress(String path){
        String formTitlesBaseXpath="//*[@class=\"el-card__body\"]/div[@role=\"tablist\"]";
        String titlesAdditionalXpath="//*[.='TITLE HERE']/ancestor::div[@role=\"tablist\"])[last()]";

        String[] pathMatris=path.split(">");

        String xpath=formTitlesBaseXpath;
        for (String pathValue:pathMatris){
            xpath="("+xpath+titlesAdditionalXpath.replaceAll("TITLE HERE",pathValue);
        }
        return xpath;
    }

    private void makePopUpChecks(WebElement plusBtn,String path,String type){

        if (plusBtn==null){
            errorMethod.addErrorMessage("path:"+path+
                    "name: "+type+"" +
                    "plus Btn does not exsist");
            logger.info("plus Element is null!!!");
            return;
        }
        if (!popUpControl){
            logger.info("pop up controls skipped");
            return;
        }

        UploadFile uploadFile=new UploadFile();
       // waitBySeconds(1);
        BaseSteps.clickByElement(plusBtn);
       // waitBySeconds(1);
        uploadFile.uploadFilesAllKind(errorMethod,path,type);
        waitBySeconds(1);
    }

    private WebElement checkPlusExistBtnAndGetPlusBtn(String xpath,String path,String type){

        xpath=xpath+"/ancestor::form//*[@class=\"fa fa-plus\"]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+"path:"+path+" Name: "+type+" + cant found";
            return null;
        }
        return element;
    }

    private void checkPlusNotExistBtn(String xpath,String path,String type){
        xpath=xpath+"/ancestor::form//*[@class=\"fa fa-plus\"]";
        WebElement element=findElementWithXpathWithOutAssertLessTimeOut(xpath);
        if (!(element==null)){
            ErrorMessage=ErrorMessage+"\n"+"path:"+path+"\n Name: "+type+"\n plus Btn found!!!\n ";
        }
    }

}


























