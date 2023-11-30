package CreatAccountMethods;

import com.thoughtworks.gauge.Step;
import driver.Driver;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.BaseSteps;
import org.example.Rest.RestAssure;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class CreateAccountMethods {
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    private static final String apiBaseUrl = "https://vrs-test-1-default-rtdb.europe-west1.firebasedatabase.app/";
    private static final String currentEnviromentName = Driver.currentEnviroment.toLowerCase();
    private static final String environmentFinal = Driver.currentEnviroment.equals("live") ? "" : (Driver.currentEnviroment + ".");
    private static final String accountSurname = "Automation";
    private static final String accountPassword = "123123";
    private static final String bearerAccountMail = "testadmin@test.com";
    private static final String bearerAccountPassword = "Bouncer!1";
    public static String currentMail;
    private static String riskEngineerToken = null;
    private static String surveyTypeToken = null;
    private static String bearerAuth;
    private static String createAccountAppValue;

    @Step("Create New <method> With Post Method")
    public static void mainCreateAccountMethod(String method) {
        getKeyforAccountMethods();
        getBearerToken();
        if (method.contains("risk engineer")){
            createRiskEngineerMethod();
        } else if (method.contains("claim")){
            createClaimsMethod();
        } else {
            Assert.fail("******** Sending method is not correct ********");
        }
    }

    @Step("Create New Inspection Form Name <formName> With Post")
    public static void createAccountMethodInspection(String formName) {
        getSurveyTypeToken(formName);
        getTokenForRiskEngineer();
        createInspectionMethod();
    }

    public static void createRiskEngineerMethod() {
        logger.info("******** Creating Risk Engineer ********");
        currentMail = createAccountAppValue + "@" + (Driver.currentSpec == null ? "test" : Driver.currentSpec) + ".com";
        String accountName = createAccountAppValue + " Post";
        Response r = given()
                .header("Authorization", "Bearer " + bearerAuth)
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("shared", false)
                .formParam("is_owner", true)
                .formParam("is_code_changeable", true)
                .formParam("representative_mobile_id", 0)
                .formParam("tenant_id", 144)
                .formParam("surveys_count", 0)
                .formParam("user[email]", currentMail)
                .formParam("user[email_verified]", false)
                .formParam("user[first_name]", accountName)
                .formParam("user[last_name]", accountSurname)
                .formParam("user[password_available]", true)
                .formParam("user[password]", accountPassword)
                .formParam("user[confirm_password]", accountPassword)
                .formParam("user[record_state]", 1)
                .formParam("user[cell_phone]", "+90 541 444 44 44")
                .formParam("user[country_authority_level]", "all")
                .formParam("user[insured_user_mode]", false)
                .when()
                .post("https://" + environmentFinal + "virtualriskspace.com/representative/save");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        logger.info("New RiskEngineer" +
                "\n Mail = " + currentMail +
                "\n Name Surname = " + accountName + " " + accountSurname +
                "\n Password = " + accountPassword +
                "\n Response = " + r.getStatusCode()
        );
        String newJsonString = "{\"" + Driver.currentSpec + "\":" + "\"" + currentMail + "\"" + "}";
        RestAssure.updateJsonWithPatch(apiBaseUrl, "regresion", newJsonString);
    }

    public static void createClaimsMethod() {
        logger.info("******** Creating Claims ********");
        currentMail = createAccountAppValue + "@" + (Driver.currentSpec == null ? "test" : Driver.currentSpec) + ".com";
        String accountName = createAccountAppValue + "Post";
        Map<Object, Object> jsonObject = new HashMap<>();
        jsonObject.put("email", currentMail);
        jsonObject.put("email_verified", false);
        jsonObject.put("first_name", accountName);
        jsonObject.put("last_name", accountSurname);
        jsonObject.put("password_available", true);
        jsonObject.put("password", accountPassword);
        jsonObject.put("confirm_password", accountPassword);
        jsonObject.put("organization_id", "024f2ef8-e3e7-4711-a85f-401b8e4b1172");
        jsonObject.put("record_state", 1);
        jsonObject.put("access_role_id", "c3386588-521a-41f0-b878-9d318347fde5");
        jsonObject.put("insured_user_mode", true);
        jsonObject.put("country_authority_level", "all");
        Response r = given()
                .header("Authorization", "Bearer " + bearerAuth)
                .header("content-type", "application/json")
                .body(jsonObject)
                .when()
                .post("https://" + environmentFinal + "virtualriskspace.com/user/save");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        logger.info("New RiskEngineer" +
                "\n Mail = " + currentMail +
                "\n Name Surname = " + accountName + " " + accountSurname +
                "\n Password = " + accountPassword +
                "\n Response = " + r.getStatusCode()
        );
        String newJsonString = "{\"" + Driver.currentSpec + "\":" + "\"" + currentMail + "\"" + "}";
        RestAssure.updateJsonWithPatch(apiBaseUrl, "regresion", newJsonString);
    }

    public static void createInspectionMethod() {
        logger.info("******** Creating New Inspection ********");
        String inspectionStartDate = getTodayDateAccount() + "T" + getTimeNowAccount("f");
        String inspectionEndDate = getTodayDateAccount() + "T" + (Integer.parseInt(getTimeNowAccount("h")) + 1) + ":" + getTimeNowAccount("m");
        String inspectionRiskEngineerToken = riskEngineerToken;
        String inspectionSurveyTypeToken = surveyTypeToken;


        String inspectionClientToken = "";
        if (currentEnviromentName.equals("live")) inspectionClientToken = "423b9b50-c1b6-4145-a416-f015c2f8b58b";
        else if (currentEnviromentName.equals("demo")) inspectionClientToken = "859efb8a-f369-4cb7-ad6f-590a41d494e9";
        else if (currentEnviromentName.equals("test")) inspectionClientToken = "a3c3ade7-5417-4e58-a777-c83b3abbb75e";

        String organizationToken= "";
        if (currentEnviromentName.equals("live")) organizationToken = "024f2ef8-e3e7-4711-a85f-401b8e4b1172";
        else if (currentEnviromentName.equals("demo")) organizationToken = "be990d79-e820-4945-91bf-488a0ae894bf";
        else if (currentEnviromentName.equals("test")) organizationToken = "27ef617b-28de-4e37-aa72-112135b4d795";

        String inspectionClientAdressToken= "";
        if (currentEnviromentName.equals("live")) inspectionClientAdressToken = "3174518f-3c61-48f4-895b-52009ebd194c";
        else if (currentEnviromentName.equals("demo")) inspectionClientAdressToken = "98d43121-95ae-42e0-a46e-d234400a30d8";
        else if (currentEnviromentName.equals("test")) inspectionClientAdressToken = "8d886e01-bde3-41d5-940f-de04781fc291";

        Map<Object, Object> jsonObject = new HashMap<>();
        jsonObject.put("start_date", inspectionStartDate);
        jsonObject.put("end_date", inspectionEndDate);
        jsonObject.put("time_zone", "Europe/Istanbul");
        jsonObject.put("representative_id", inspectionRiskEngineerToken);
        jsonObject.put("customer_id", inspectionClientToken);
        jsonObject.put("organization_id", organizationToken);
        jsonObject.put("survey_type_id", inspectionSurveyTypeToken);
        jsonObject.put("address_id", inspectionClientAdressToken);

        Response r = given()
                .header("Authorization", "Bearer " + bearerAuth)
                .header("content-type", "application/json")
                .body(jsonObject)
                .when()
                .post("https://" + environmentFinal + "virtualriskspace.com/visit/save");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        logger.info("******** New Inspection Created Successfully ********");
    }

    public static void getTokenForRiskEngineer() {
        logger.info("******** Getting Risk Engineer Token ********");
        Response r = given()
                .header("Authorization", "Bearer " + bearerAuth)
                .header("content-type", "application/x-www-form-urlencoded")
                .when()
                .get("https://" + environmentFinal + "virtualriskspace.com/context/get");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        List<JSONObject> l = r.getBody().jsonPath().get("data.users");
        logger.info("Find "+l.size()+" Result");

        for (int i = l.size() -1 ; i >= 0 ; i--) {
            if (r.getBody().jsonPath().get("data.users[" + i + "].email").toString().equals(currentMail)) {
                riskEngineerToken = r.getBody().jsonPath().get("data.users[" + i + "].representative_id").toString();
                break;
            }
        }
        logger.info("\n Risk Engineer Token = " + riskEngineerToken);
    }

    public static void getSurveyTypeToken(String formName) {
        logger.info("******** Getting Survey Type Token ********");
        Response r = given()
                .header("Authorization", "Bearer " + bearerAuth)
                .header("content-type", "application/x-www-form-urlencoded")
                .when()
                .get("https://" + environmentFinal + "virtualriskspace.com/forms/types?dc=186625.9199368312");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        List<JSONObject> l = r.getBody().jsonPath().get("data");
        for (int i = 0; i < l.size(); i++) {
            if (r.getBody().jsonPath().get("data[" + i + "].name").toString().equals(formName)) {
                surveyTypeToken = r.getBody().jsonPath().get("data[" + i + "].id").toString();
                break;
            }
        }
        logger.info("\n Survey Type Token = " + surveyTypeToken);
    }

    public static String getTodayDateAccount() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuu-MM-dd");
        LocalDate localDate = LocalDate.now();
        return dtf.format(localDate);
    }

    public static String getTimeNowAccount(String type) {
        String pattern = "";
        if (type.equals("h")) pattern = "HH";
        if (type.equals("f")) pattern = "HH:mm";
        if (type.equals("m")) pattern = "mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());
    }

    public static void getBearerToken() {
        logger.info("******** Getting Bearer Token ********");
        Response r = given()
                .header("content-type", "multipart/form-data")
                .multiPart("email", bearerAccountMail)
                .multiPart("password", bearerAccountPassword)
                .post("https://" + environmentFinal + "virtualriskspace.com/auth/mobilelogin");
        String returnCode = r.jsonPath().getString("meta.return_code");
        if (!Objects.equals(returnCode, "0")) logger.info("Display Message = " + r.prettyPrint());
        Assert.assertEquals("Return Code Fail", "0", returnCode);
        JsonPath j = new JsonPath(r.asString());
        logger.info("\n Bearer Token" + " = " + j.getString("data"));
        bearerAuth = j.getString("data");
    }

    public static void getKeyforAccountMethods() {
        logger.info("******** Getting Key From Api ********");
        String checkCloud = Driver.isThisADockerTest ? "docker" : "local";
        RestAssure restAssure = new RestAssure();

        /*Get Key*/
        String apiValue = restAssure.sentTheGetForSpecsItem(apiBaseUrl, checkCloud, currentEnviromentName);

        /*Creating New Key */
        createAccountAppValue = String.valueOf(Integer.parseInt(apiValue) + 1);
        logger.info("New value for api = " + createAccountAppValue);

        /*Patch New Value */
        String newJsonString = "{\"" + currentEnviromentName + "\": " + createAccountAppValue + "}";
        RestAssure.updateJsonWithPatch(apiBaseUrl, checkCloud, newJsonString);
    }
}