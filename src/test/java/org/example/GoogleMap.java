package org.example;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.Set;
import java.util.logging.Logger;

public class GoogleMap extends Methods{
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));
    BaseSteps baseSteps=new BaseSteps();

    @Step("Google Map Select From Dropdawn <text>")
    public void selectFromDropDownMap(String text){
        String xpath="//*[@class=\"pac-item\"]//*[.='"+text+"']";
        baseSteps.writeTextWithKey(text,"Clients-location-TextBox");
        waitBySeconds(1);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("xpath: "+xpath+" cant found ",element);
        element.click();

    }

}
