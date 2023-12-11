package driver;

import ElementHelper.CsvReader;
import com.thoughtworks.gauge.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.Methods;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static io.restassured.RestAssured.given;

public class Driver {
    public static String browserName = "chrome";
    public static String currentSpec;
    public static String baseUrl = "https://EnvironmentHere.virtualriskspace.com/authorize#/?rdk=70a1306b-2946-4ad3-9289-0ccd98fb50d6";
    public static String currentEnviroment = "demo";
    public static boolean isThisADockerTest = true;
    protected static WebDriver webDriver;
    Logger logger = Logger.getLogger(String.valueOf(Driver.class));
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeSuite
    public void setItems() {
        CsvReader.setAllElements();
    }

    @BeforeScenario
    public void initializeDriver(ExecutionContext context) throws MalformedURLException {
        currentSpec = context.getCurrentSpecification().getName().replaceAll(" ", "").toLowerCase();
        logger.info(currentSpec);
        String mainPath = Methods.createPathProperties("allFiles");
        String catPath = mainPath + Methods.createPathProperties("videoConference");
        String downloadFilepath = mainPath + Methods.createPathProperties("download");
        logger.info("mainPath: " + mainPath);
        logger.info("catPath: " + catPath);
        logger.info("downloadFilepath: " + downloadFilepath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");
        logger.info("Desktop path of this pc: " + mainPath);
        WebDriverManager.chromedriver().setup();
        setTheEnvironment();
        //cam stream options
        options.addArguments("--use-fake-ui-for-media-stream");
        options.addArguments("--use-fake-device-for-media-stream");
        options.addArguments("--use-file-for-fake-video-capture=" + catPath);


        if (isThisADockerTest) {
            logger.info("------------------------- Docker Test -------------------------");
            options.setExperimentalOption("w3c", false);
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--start-fullscreen");
            options.addArguments("--no-sandbox");
            capabilities.setPlatform(Platform.LINUX);
            capabilities.setVersion("");
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

            browserName = System.getenv("browser");
            webDriver.get(baseUrl);
        } else {
            logger.info("------------------------- Local Test -------------------------");
            //Download Path
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.prompt_for_download", false);
            prefs.put("download.default_directory", downloadFilepath);
            options.setExperimentalOption("prefs", prefs);
            LoggingPreferences logPrefs = new LoggingPreferences();
            logPrefs.enable(LogType.BROWSER, Level.ALL);
            capabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
            capabilities.setCapability(ChromeOptions.CAPABILITY, options);

            webDriver = new ChromeDriver(capabilities);
            webDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            webDriver.get(baseUrl);
            webDriver.manage().window().maximize();
        }
    }

    private void setTheEnvironment() {
        logger.info("-------------------------------------------------------");
        logger.info("Enviroment is = " + currentEnviroment);
        logger.info("-------------------------------------------------------");
        baseUrl = baseUrl.replaceAll("EnvironmentHere", currentEnviroment);
        if (currentEnviroment.contains("live")) {
            baseUrl = "https://virtualriskspace.com/authorize#/?rdk=70a1306b-2946-4ad3-9289-0ccd98fb50d6";
            System.out.println(baseUrl);
        }
    }

    @AfterScenario
    public void closeDriver() {
        webDriver.quit();
    }

    @Step("Open login Page")
    public void openLoginPage() {
        webDriver.get(baseUrl);
    }


    String test="[\n" +
            "  {\n" +
            "    \"id\": 0,\n" +
            "    \"username\": \"string\",\n" +
            "    \"firstName\": \"string\",\n" +
            "    \"lastName\": \"string\",\n" +
            "    \"email\": \"string\",\n" +
            "    \"password\": \"string\",\n" +
            "    \"phone\": \"string\",\n" +
            "    \"userStatus\": 0\n" +
            "  }\n" +
            "]";
}