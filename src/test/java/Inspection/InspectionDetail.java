package Inspection;

import AccountHandle.AccountHandle;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.example.*;
import org.example.Rest.RestAssure;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import io.restassured.response.Response;
import java.util.List;
import java.util.logging.Logger;

public class InspectionDetail extends InspectionDetailMethods {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));


    String ErrorMessage="";
    public static String formName;

    ErrorMethod errorMethod=new ErrorMethod();
    RestAssure restAssure = new RestAssure();
    BaseSteps baseSteps=new BaseSteps();

    private static String token;
    private static String urlId;
    private static String visidId;
    private static String formId;
    public static int sectionsSize;
    public static boolean saveMode=false;
    public Response r;

    String Url = AccountHandle.apiBaseUrl + AccountHandle.checkCloud + "/SharedLink/";
    String currentEnviroment = Driver.currentEnviroment.toLowerCase();

    @Step("Get form json <formtype>")
    public void setAutorizationCodeAndUrl(String formtype) {
        JavascriptExecutor executor = (JavascriptExecutor) webDriver;
        String url = executor.executeScript("return document.URL;").toString();
        RestAssure restAssure=new RestAssure();
        logger.info("readed url is: "+url);
        url=url.replaceAll("////","*");
        urlId=url.replaceAll("////","*").split("//*")[4];
        logger.info("urlId is find as: "+urlId);
        token = String.valueOf(executor.executeScript("return window.localStorage.auth_token"));
        logger.info("Bareer token is: "+token);
        visidId= restAssure.sentThePostVisidId(token,urlId);
        formId=restAssure.sentThePostFormId(token,urlId,formtype);

        r= restAssure.getTheJson(token,visidId,formId);
    }

    @Step("Inspection Detail Share form and record link and password")
    public void shareFormAndRecordValues() {
        Methods methods=new Methods();
        String newJsonString;
        String jsonkey;

        String link = methods.getTextFromElement("ID-Link-Text");
        String password = methods.getTextFromElement("ID-Password-Text");

        /*Link Update*/
        jsonkey = currentEnviroment + "link";
        newJsonString = "{\""+jsonkey+"\":"+   "\""+link+"\""  +"}";
        restAssure.updateJsonWithPatch( Url , currentEnviroment , newJsonString);

        /*Pass Update*/
        jsonkey = currentEnviroment + "pass";
        newJsonString = "{\""+jsonkey+"\":"+   "\""+password+"\""  +"}";
        restAssure.updateJsonWithPatch( Url , currentEnviroment , newJsonString);
    }

    @Step("Go to Shared link and enter The Password")
    public void goToSharedLink(){
        String link = restAssure.sentTheGetForSpecsItem(Url , currentEnviroment , currentEnviroment + "link");
        String password = restAssure.sentTheGetForSpecsItem(Url , currentEnviroment , currentEnviroment + "pass");

        logger.info("\n"+"link: "+link+"\n"+"password:"+password);

        baseSteps.goToLink("https://"+link);
        waitBySeconds(3);
        baseSteps.javascriptclickerXpath("CybotCookiebotDialogBodyButtonAccept");
        baseSteps.writeTextWithKey(password,"Share-Code-Txt");
    }

    @Step("Call History Check Mail Shared Email")
    public void checkMailContent(){
        Email email=new Email();
        String pass = restAssure.sentTheGetForSpecsItem(Url , currentEnviroment , currentEnviroment + "pass");
        email.checkEmailContainsText(pass);
    }

    @Step("ID check Header <headerName>")
    public void idCheckHeader(String headerName){
        String baseXpath="//*[@class=\"el-card__header\"]//*[contains(text(),'"+headerName+"')]";
        WebElement element=findElementWithXpathWithOutAssert(baseXpath);
        Assert.assertNotNull(headerName+ " did not open ",element);
        logger.info(headerName+" opened successfully");
    }

    @Step("Inspection Form Check <formName> Smoke")
    public void listFilesForFolder1Smoke(String formName) {
        setAutorizationCodeAndUrl(formName);
        int countOfComponent=0;
        String space="-";
        String path="";
        List<JSONObject> l=r.getBody().jsonPath().get("data.sections");
        sectionsSize=l.size();
        String title;
        String id;
        for (int i=0;i<sectionsSize;i++){
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id")==null){
                if (countOfComponent>1){
                    logger.info("smoke test limit arrived!!");
                    continue;
                }
                id=r.getBody().jsonPath().get("data.sections["+i+"].id").toString();
                title=r.getBody().jsonPath().get("data.sections["+i+"].name").toString();
                findTitleAndClick(title);
                System.out.println(space+title);
                makeTheChecks(title,r, errorMethod,i);
                countOfComponent++;
            }
        }

        if (errorMethod.isThereError()){
            Assert.fail(errorMethod.getErrorMessage());
        }
    }

    @Step("Inspection Form Set Save mod on")
    public void inspectionSaveModOn(){
        saveMode=true;
    }
    @Step("Inspection Form Check <formName>")
    public void listFilesForFolder1(String formName) {
        setAutorizationCodeAndUrl(formName);
        BaseSteps baseSteps=new BaseSteps();
        String space="-";
        String path="";

        List<JSONObject> l=r.getBody().jsonPath().get("data.sections");
        sectionsSize=l.size();
        String title;
        String id;
        for (int i=0;i<sectionsSize;i++){
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id")==null){
                id=r.getBody().jsonPath().get("data.sections["+i+"].id").toString();
                title=r.getBody().jsonPath().get("data.sections["+i+"].name").toString();
                findTitleAndClick(title);
                System.out.println(space+title);
                makeTheChecks(title,r, errorMethod,i);
                listFilesForFolder2(title,id);
            }
        }
        System.out.println(errorMethod.getErrorMessage());

        if (saveMode){
            baseSteps.clicktWithKey("ID-Save-Btn");
            waitBySeconds(5);
            baseSteps.clearTextWithKey("ID-Finalize-Btn");
            waitBySeconds(1);
            baseSteps.clearTextWithKey("ID-PupUpYes-Btn");
            waitBySeconds(1);
        }
        if (errorMethod.isThereError()){
            Assert.fail(errorMethod.getErrorMessage());
        }
    }

    public void listFilesForFolder2(String title,String id) {
        String space="--";
        String title2;
        String id2;
        for (int i=0;i<sectionsSize;i++){
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id")==null ||
                    r.getBody().jsonPath().get("data.sections["+i+"].section_sort_index").toString().equals("0")) {
                continue;
            }
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id").equals(id)){
                id2=r.getBody().jsonPath().get("data.sections["+i+"].id").toString();
                title2=r.getBody().jsonPath().get("data.sections["+i+"].name").toString();
                System.out.println(space+title2);
                findTitleAndClick(title+">"+title2);
                makeTheChecks(title+">"+title2,r, errorMethod,i);
                System.out.println(title+">"+title2+" level 2 and i: "+i);
                listFilesForFolder3(title+">"+title2,id2);
            }
        }
    }

    public void listFilesForFolder3(String title2,String id) {
        String space="---";
        String title3;
        String id3;
        for (int i=0;i<sectionsSize;i++){
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id")==null ||
                    r.getBody().jsonPath().get("data.sections["+i+"].section_sort_index").toString().equals("0")) {
                continue;
            }
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id").equals(id)){
                id3=r.getBody().jsonPath().get("data.sections["+i+"].id").toString();
                title3=r.getBody().jsonPath().get("data.sections["+i+"].name").toString();

                System.out.println(space+title3);

                findTitleAndClick(title2+">"+title3);
                makeTheChecks(title2+">"+title3,r, errorMethod,i);

                System.out.println(title2+">"+title3+" level 3");

               listFilesForFolder4(title2+">"+title3,id3);
            }
        }
    }

    public void listFilesForFolder4(String title3,String id) {
        String space="----";
        String title4;
        String id4;
        for (int i=0;i<sectionsSize;i++){
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id")==null ||
                    r.getBody().jsonPath().get("data.sections["+i+"].section_sort_index").toString().equals("0")) {
                continue;
            }
            if (r.getBody().jsonPath().get("data.sections["+i+"].parent_id").equals(id)){
                id4=r.getBody().jsonPath().get("data.sections["+i+"].id").toString();
                title4=r.getBody().jsonPath().get("data.sections["+i+"].name").toString();
                System.out.println(space+title4);
                findTitleAndClick(title3+">"+title4);
                makeTheChecks(title3+">"+title4,r, errorMethod,i);
                System.out.println(title3+">"+title4);
            }
        }
    }
}