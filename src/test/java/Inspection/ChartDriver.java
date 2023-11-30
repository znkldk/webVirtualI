package Inspection;

import com.thoughtworks.gauge.Step;
import io.restassured.response.Response;
import org.example.ErrorMethod;
import org.example.Methods;
import org.example.Rest.RestAssure;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.util.logging.Logger;

public class ChartDriver extends Chart{
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));


    String ErrorMessage="";
    public static String formName;

    ErrorMethod errorMethod=new ErrorMethod();

    private static String token;
    private static String urlId;
    private static String visidId;
    private static String formId;
    public static int sectionsSize;
    public Response r;

    @Step("Get form json <formtype> Chart")
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

    @Step("Chart Form Check <formName>")
    public void listFilesForFolder1Chart(String formName) {
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
                findTitleAndClickChart(title);
                System.out.println(space+title);
                makeTheChecksChart(title,r, errorMethod,i);
                listFilesForFolder2(title,id);

            }
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
                findTitleAndClickChart(title+">"+title2);
                makeTheChecksChart(title+">"+title2,r, errorMethod,i);
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

                findTitleAndClickChart(title2+">"+title3);
                makeTheChecksChart(title2+">"+title3,r, errorMethod,i);

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
                findTitleAndClickChart(title3+">"+title4);
                makeTheChecksChart(title3+">"+title4,r, errorMethod,i);

                System.out.println(title3+">"+title4);
            }



        }
    }

}
