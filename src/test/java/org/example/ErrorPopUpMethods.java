package org.example;

import Inspection.InspectionDetailMethods;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class ErrorPopUpMethods extends Methods{
    Logger logger = Logger.getLogger(String.valueOf(InspectionDetailMethods.class));
    Methods methods=new Methods();

    private String errorPopUpXpath="//*[@class=\"el-notification__content\"]";
    private String successMethod="//*[@class=\"el-notification__icon el-icon-success\"]";
    private String successPopUpCloseBtnXpath="//*[@class=\"el-notification__closeBtn el-icon-close\"]";
    private String errorPupUpMessage="//*[@class=\"el-notification__content\"]//*[contains(text(),'TEXTHERE')]";

    @Step("Does Error Message Appear id not Fail message= <errorMEssage>")
    public void doesErrorPopUpAppearIfNotFail(String errorMEssage){
        logger.info("KVKK tarafindan devre disi bırakilmistir");
        /*WebElement errorPopUp;
        errorPopUp=methods.findElementWithXpathWithOutAssert(errorPopUpXpath);
        Assert.assertNotNull(errorMEssage,errorPopUp);*/
    }
    @Step("Does Error Pup up contains <expectedErrorMessage>")
    public void checkErrorPopUpInside(String expectedErrorMessage){
        logger.info("KVKK tarafindan devre disi bırakilmistir");
        /*doesErrorPopUpAppearIfNotFail("There is no error pop up!!");
        WebElement element;
        element=findElementWithXpathWithOutAssert(
                errorPupUpMessage.replaceAll(
            "TEXTHERE",expectedErrorMessage
        ));
        Assert.assertNotNull("text cant find in pop up text: "+
                expectedErrorMessage,element);
*/
    }

    @Step("Does Success Message Appear if not Fail message <errorMessage>")
    public void doesSuccessPopUpAppearIfNotFail(String errorMessage){
        logger.info("KVKK tarafindan devre disi bırakilmistir");
        /*WebElement errorPopUp;
        errorPopUp=methods.findElementWithXpathWithOutAssert(successMethod);
        Assert.assertNotNull("Success Pop Up did not observe : "+errorMessage,errorPopUp);
        waitBySeconds(1);
        WebElement popupCloseBtn;
        popupCloseBtn=methods.findElementWithXpathWithOutAssert(successPopUpCloseBtnXpath);
        popupCloseBtn.click();
        waitBySeconds(1);*/
    }

    @Step("Does Success Message Appear if yes error message <errorMessage>")
    public void doesSuccessPopUpAppearIfYesFail(String errorMessage){
        logger.info("KVKK tarafindan devre disi bırakilmistir");
       /* WebElement element;
        element=findElementWithXpathWithOutAssert(successMethod);
        Assert.assertNull(
                "A success popup observed This is not expected please check it! \n"+
                errorMessage,element);
        logger.info("Success popup did not observe <- This is expected");*/
    }


}
