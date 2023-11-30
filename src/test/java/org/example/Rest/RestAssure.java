package org.example.Rest;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.BaseSteps;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import static io.restassured.RestAssured.given;

public class RestAssure {
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));

    public static Response sentTheGet(String link){
        logger.info("******** Get Gonderiliyor ********");
        Response r=given()
                .contentType("application/json").log().all().when().get(link)
                .then().extract().response();

        logger.info(
                "\n+++++++++++++++++ Response +++++++++++++++++\n"+
                        "\n "+r.prettyPrint()+" \n"+
                        "Response Time= "+r.getTime()+"ms"+
                        "\n+++++++++++++++++++++++++++++++++++++++++++++++++\n");
        return r;
    }

    public String sentThePostVisidId(String token,String urlId){
        logger.info("******** visidId Gonderiliyor ********");
        String visidId;
        String Enviroment= Driver.currentEnviroment;
        String Url="https://"+Enviroment+".virtualriskspace.com/mobile/visit";
        if (Enviroment.contains("ive")){
            Url="https://virtualriskspace.com/mobile/visit";
        }
        Response r = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"id\": \""+urlId+"\"}")
                .when()
                .post(Url)
                .then()
                .extract().response();

        logger.info(
                "\n+++++++++++++++++ Response +++++++++++++++++\n"+
                        "\n "+r.prettyPrint()+" \n"+
                        "Response Time= "+r.getTime()+"ms"+
                        "\n+++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Assert.assertEquals("visit api problem ",200,r.getStatusCode());

        visidId=r.getBody().jsonPath().get("data.customer.id").toString();
        logger.info("visid id found as : "+visidId);
        return visidId;
    }

    public String sentThePostFormId(String token,String urlId,String formType){
        logger.info("******** Sending FormID ********");
        String formId;
        String Enviroment= Driver.currentEnviroment;
        String Url="https://"+Enviroment+".virtualriskspace.com/mobile/visit/forms";
        if (Enviroment.contains("ive")){
            Url="https://virtualriskspace.com/mobile/visit/forms";
        }

        Response r = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"id\": \""+urlId+"\"}")
                .when()
                .post(Url)
                .then()
                .extract().response();
        Assert.assertEquals("form api problem ",200,r.getStatusCode());

        List<JSONObject> l=r.getBody().jsonPath().get("data");
        System.out.println(r.getBody().jsonPath().get("data").toString());
        System.out.println("Size: "+l.size());

        String name;
        for (int i=0;i<l.size();i++){
            name=r.getBody().jsonPath().get("data["+i+"].name").toString();
            if (name.contains(formType)){
                System.out.println("Name : "+r.getBody().jsonPath().get("data["+i+"].name"));
                System.out.println("id : "+r.getBody().jsonPath().get("data["+i+"].id"));
                formId=r.getBody().jsonPath().get("data["+i+"].id");
                return formId;
            }
        }
        return null;
    }

    public Response getTheJson(String token, String visidId, String formId){
        logger.info("******** Getting Full Form ********");
        String Enviroment= Driver.currentEnviroment;
        String Url="https://"+Enviroment+".virtualriskspace.com/mobile/visit/form";
        if (Enviroment.contains("ive")){
            Url="https://virtualriskspace.com/mobile/visit/form";
        }

        Response r = given()
                .header("Content-type", "application/json")
                .header("Authorization", "Bearer " + token)
                .body("{\"id\": \""+visidId+"\",\"id\": \""+formId+"\"}")
                .when()
                .post(Url)
                .then()
                .extract().response();
        logger.info(
                "\n+++++++++++++++++ Response +++++++++++++++++\n"+
                        "\n "+r.prettyPrint()+" \n"+
                        "Response Time= "+r.getTime()+"ms"+
                        "\n+++++++++++++++++++++++++++++++++++++++++++++++++\n");
        Assert.assertEquals("full form api problem ",200,r.getStatusCode());

        return r;
    }

    public String sentTheGetForSpecsItem(String link , String specUrl , String key){
        logger.info("+++++++++++++++++ Sending Get Request for Specific Item +++++++++++++++++");
        specUrl = specUrl +".json";
        RestAssured.baseURI = link;
        Response res = given()
                .when()
                .get(specUrl);
        JsonPath j = new JsonPath(res.asString());
        logger.info(key + " = " +j.getString(key));
        return j.getString(key);
    }

    public static void updateJsonWithPatch(String link , String specUrl , String requestBody){
        logger.info("+++++++++++++++++ Updating JSON With Patch Method +++++++++++++++++");
        specUrl = specUrl +".json";
        RestAssured.baseURI = link;
        Response r =
                given().body(requestBody)
                .when().patch(specUrl);
        logger.info("Api value updated with patch method");
    }

    @Step("Send Report to Firebase <tags>")
    public static void reportJsonPost(String tags){
        logger.info("+++++++++++++++++ Report Sending to Firebase For TRS +++++++++++++++++");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateTitle = simpleDateFormat.format(new Date());

        String URLTagRegression ="https://vrs-report-default-rtdb.europe-west1.firebasedatabase.app/regression/"+dateTitle+".json";
        String URLTagSmoke = "https://vrs-report-default-rtdb.europe-west1.firebasedatabase.app/smoke/"+dateTitle+"/"+Driver.currentEnviroment+".json";
        String URLKeyReg ="https://vrs-report-default-rtdb.europe-west1.firebasedatabase.app/key/Regression/"+dateTitle+".json";
        String URLKeySmoke ="https://vrs-report-default-rtdb.europe-west1.firebasedatabase.app/key/Smoke/"+dateTitle+".json";
        File file = new File("reports/json-report/result.json");
        String jsonKey = "{\""+dateTitle+"\":"+   "\"\""  +"}";
        Response res =
                given()
                        .body(jsonKey)
                        .when()
                        .patch(tags.equals("smoke") ? URLKeySmoke : URLKeyReg);

        Response r =
                given()
                        .header("Content-type", "application/json")
                        .body(file)
                        .when()
                        .patch(tags.equals("smoke") ? URLTagSmoke : URLTagRegression);


        logger.info("Api value updated with patch method");
    }

    public static void main(String[] args) {
        File file = new File("reports/json-report/result.json");
        Response r =
                given()

                        .body(file)
                        .when()
                        .patch("https://vrs-report-default-rtdb.europe-west1.firebasedatabase.app/key/.json");
    }
}