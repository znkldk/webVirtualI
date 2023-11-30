package AccountHandle;

import Inspection.InspectionDetailMethods;
import driver.Driver;
import org.example.Rest.RestAssure;
import java.util.logging.Logger;

public class AccountHandle {
    static Logger logger = Logger.getLogger(String.valueOf(InspectionDetailMethods.class));
    RestAssure restAssure = new RestAssure();
    public static String mailAddressCurrent;
    public static String newApiValue;
    static String currentEnviromentName= Driver.currentEnviroment.toLowerCase();
    public static String apiBaseUrl = "https://vrs-test-1-default-rtdb.europe-west1.firebasedatabase.app/" ;
    public static String checkCloud = Driver.isThisADockerTest ? "docker" : "local";

    public static void getKeyOnApi() {
        logger.info("Running getKeyOnApi");
        RestAssure restAssure = new RestAssure();

        /* Get Key*/
        String apiValue = restAssure.sentTheGetForSpecsItem(apiBaseUrl , checkCloud , currentEnviromentName);

        /* Creating New Key */
        newApiValue = String.valueOf(Integer.parseInt(apiValue) + 1);
        logger.info("New value for api = "+ newApiValue);

        /* Creating Mail With Referenced to Key */
        mailAddressCurrent = newApiValue + currentEnviromentName + checkCloud + "@test.com";
        logger.info("New mail adress = "+ mailAddressCurrent);

        /* Patch New Value */
        String newJsonString = "{\""+currentEnviromentName+"\": "+newApiValue+"}";
        RestAssure.updateJsonWithPatch(apiBaseUrl, checkCloud , newJsonString);
    }

    public static void createMailAndCreateKey() {
        logger.info("******** Getting Key Without Increase and Creating Mail ********");
        RestAssure restAssure = new RestAssure();

        /*Get Key*/
        newApiValue = restAssure.sentTheGetForSpecsItem(apiBaseUrl , checkCloud , currentEnviromentName);

        /*Creating Mail With Referenced to Key */
        mailAddressCurrent = newApiValue + currentEnviromentName + checkCloud + "@test.com";
        logger.info("Mail address assigned to var = "+ mailAddressCurrent);
    }

    public static void getMailForSpec() {
        logger.info("******** Getting Mail on Database ********");
        RestAssure restAssure = new RestAssure();
        mailAddressCurrent = restAssure.sentTheGetForSpecsItem(apiBaseUrl , "regresion" , Driver.currentSpec);
        logger.info("Mail Address for Current Scenario = " + mailAddressCurrent);
    }
}
