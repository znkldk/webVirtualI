package Inspection;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import org.example.*;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Logger;

public class Chart extends Methods {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));

    String ErrorMessage="";
    ErrorMethod errorMethod;
    String chardFilesGridTitles="Preview Type Size Created By TagsDate";

    public void makeTheChecksChart(String path, Response r, ErrorMethod errMethod, int i) {
        errorMethod = errMethod;

        int questionsSize;
        List<JSONObject> l = r.getBody().jsonPath().get("data.sections[" + i + "].questions");
        if (l == null) {
            logger.info(path + " does not have any questions");
            return;

        }

        questionsSize = l.size();
        logger.info("questionsSize: " + questionsSize);
        logger.info(path + " için element kontrolleri yapılıyor...");

        String control_type;
        String caption;

        for (int j = 0; j < questionsSize; j++) {

            control_type = r.getBody().jsonPath().get("data.sections[" + i + "].questions[" + j + "].control_type").toString();

            if (control_type.contains("Video") || control_type.contains("Audio") || control_type.contains("Image")) {
                return;
            }

            if (control_type.contains("Coordinate")) {
                caption = "Coordinates";
            } else {
                caption=r.getBody().jsonPath().get("data.sections["+i+"].questions["+j+"].caption").toString();
                checkQuestionAndAnswer(path, caption);
            }
        }
    }

    public void checkQuestionAndAnswer(String path,String questionText){
        String titleXpath;
        String textBoxXpath;
        titleXpath=getTitleAddressChart(path);
        questionText=questionText.replaceAll("TextBox\\.","");
        questionText=questionText.replaceAll("\\.txt","");
        logger.info("Checking Questions path:"+path);
        logger.info("Checking Questions Name:"+questionText);

        textBoxXpath=titleXpath+"//*[.=\"textBoxNameHere\"]/../..".replaceAll("textBoxNameHere",questionText);
        WebElement element=findElementWithXpathWithOutAssert(textBoxXpath);

        if (element==null){
            ErrorMessage=ErrorMessage+"\n"+path+" TextBox can not found TextBoxName: "+questionText;
            return;
        }

        WebElement elementText=findElementWithXpathWithOutAssert(textBoxXpath+"/..//*[@class=\"break-word\"]");
        WebElement elementNotes=findElementWithXpathWithOutAssert(textBoxXpath+"/..//*[contains(text(),'Notes:')]");
        WebElement elementVideo=findElementWithXpathWithOutAssert("("+textBoxXpath+"/..//*[contains(@class,'play')])[1]");
        WebElement elementAudio=findElementWithXpathWithOutAssert("("+textBoxXpath+"/..//*[contains(@class,'play')])[2]");
     //   WebElement elementImg=findElementWithXpathWithOutAssert(textBoxXpath);
        WebElement elementDoc=findElementWithXpathWithOutAssert(textBoxXpath+"/..//*[contains(@class,'file')]");

        errorMethod.addErrorMessageIfElementNull(elementText,"path:"+path+" \n"+
                "questionText: "+questionText+" \n"+
                "kind:"+" elementText \n"+
                "cant found");

        errorMethod.addErrorMessageIfElementNull(elementNotes,"path:"+path+" \n"+
                "questionText: "+questionText+" \n"+
                "kind:"+"elementNotes  \n"+
                "cant found");

        errorMethod.addErrorMessageIfElementNull(elementVideo,"path:"+path+" \n"+
                "questionText: "+questionText+" \n"+
                "kind:"+"elementVideo  \n"+
                "cant found");

        errorMethod.addErrorMessageIfElementNull(elementAudio,"path:"+path+" \n"+
                "questionText: "+questionText+" \n"+
                "kind:"+"elementAudio  \n"+
                "cant found");

        errorMethod.addErrorMessageIfElementNull(elementDoc,"path:"+path+" \n"+
                "questionText: "+questionText+" \n"+
                "kind:"+" elementDoc \n"+
                "cant found");
    }

    public String getTitleAddressChart(String path){
        String formTitlesBaseXpath="";
        String titlesAdditionalXpath="//*[@role=\"button\" and .='TITLE HERE']/ancestor::div[@role=\"tablist\"])[last()]";

        String[] pathMatris=path.split(">");

        String xpath=formTitlesBaseXpath;
        for (String pathValue:pathMatris){
            xpath="("+xpath+titlesAdditionalXpath.replaceAll("TITLE HERE",pathValue);
        }
        return xpath;
    }

    @Step("Char Tab Select <tabText>")
    public void clickTabText(String tabText){
        waitBySeconds(2);
        String xpath="//*[@role=\"tablist\" and @class=\"el-tabs__nav is-top\"]//*[contains(text(),'"+tabText+"')]";

        WebElement element=findElementWithXpathWithOutAssert(xpath);
        element.click();
        waitBySeconds(2);
    }

    @Step("Chart Check Files Headers")
    public void checkHeadersChart(){
        GridMethods gridMethods=new GridMethods();
        gridMethods.checkHeaders(chardFilesGridTitles);
    }

    public void findTitleAndClickChart(String path){
        String formTitlesBaseXpath="";
        String titlesAdditionalXpath="//*[@role=\"button\" and .='TITLE HERE']/ancestor::div[@role=\"tablist\"])[last()]";

        String[] pathMatris=path.split(">");

        String xpath=formTitlesBaseXpath;
        for (String pathValue:pathMatris){
            xpath="("+xpath+titlesAdditionalXpath.replaceAll("TITLE HERE",pathValue);
        }
        String lastName=pathMatris[pathMatris.length-1];
        xpath=xpath+"//*[.='TITLE HERE' and @role=\"button\"]".replaceAll("TITLE HERE",lastName);

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

    @Step("Check Location Score Not Zero")
    public void checkLocationNotZero(){
        waitBySeconds(1);
        String locationValueXpath="//*[@class=\"el-table__body-wrapper is-scrolling-none\"]//tr[1]/td[3]//*[@class=\"cell\"]";
        WebElement element=findElementWithXpathWithOutAssert(locationValueXpath);
        Assert.assertNotNull("location value cant found: ",element);
        String locationValue=element.getText();
        Assert.assertNotEquals("location value cant be 0","0",locationValue);

    }

    @Step("Does overall difrent than zero")
    public void overallCheck(){
        String xpath="(//*[@class=\"cell\" and .='OVERALL']/../..//div[@class=\"cell risk-header\"])[1]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("something is wrong",element);
        Assert.assertNotEquals("overall cant be zero risk calculating issue","0.0",element.getText());
    }

    @Step("Chart Files Upload All kind of files <kind>")
    public void fileUploadChard(String kind){

        String lastXpath="//input[@multiple=\"multiple\"]";
        UploadFile uploadFile=new UploadFile();

        String absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Doc");
        if (kind.contains("mage")){//image
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Img");
        } else if (kind.contains("ideo")){//Video
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Video");
        }else if (kind.contains("udio")){//audio
            absolutePathOfVideo=uploadFile.getRandomFileFromDirectory("Sound");
        } else {
            logger.info("guncellemeye ihtiyaç var burayı !!!!!!!!!!!!!!!!!!!!!!!!");
            return;
        }

        waitBySeconds(2);

        WebElement element=findElementWithXpathWithOutAssert(lastXpath);


        BaseSteps.writeTextWithElement(element,absolutePathOfVideo,"uploads");
        waitBySeconds(3);


    }
}
