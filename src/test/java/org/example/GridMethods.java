package org.example;

import EngineerDetail.EngineerDetail;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;


public class GridMethods extends BaseSteps{
    public static String cellValue;
    static Logger logger = Logger.getLogger(String.valueOf(BaseSteps.class));

    @Step("Does All Grid Lines Has The <text> if not fail <failmessage>")
    public void checlAllGridHasTheText(String text,String failMessage){
        WebElement element;
        WebElement elementNextLine;

        String xpath;
        String xpathNextLine;

        if (text.contains("LastRiskEngineer") || text.contains("rank") || text.contains("Otomasyon")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }
        boolean doesWeHAveNestCloump=true;
        int i=1;
        while (doesWeHAveNestCloump){
            xpath="(//*[@class=\"el-table__body\"]//tr)["+i+"]//*[contains(text(),'"+text+"')]";
            element=findElementWithXpathWithOutAssert(xpath);
            Assert.assertNotNull(failMessage+" \n"+i+". raw does not have the text: "+text ,element);
            i++;
            logger.info(text+ " found in "+i+". line");
            xpathNextLine="(//*[@class=\"el-table__body\"]//tr)["+i+"]";
            elementNextLine=findElementWithXpathWithOutAssert(xpathNextLine);
            if (elementNextLine==null){
                doesWeHAveNestCloump=false;
                logger.info("All lines checked");
                return;
            }
        }
    }

    @Step("Does Grid Has the text <text>")
    public void doesGridHasTheText(String text){
        waitSeconds(1);
        if (text.contains("LastRiskEngineer") || text.contains("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }
        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'"+text+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(text+"  cant found on grid",element);
        logger.info(text+" found on grid");
    }

    @Step("Check Grid Has Text <text> in <title>")
    public void doesGridHasTheTextWithTitle(String text,String title){
        waitSeconds(1);
        if (text.contains("LastRiskEngineer") || text.contains("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }
        String xpath="//*[@class=\"section-header\" and .='"+title+"']/..//*[@class=\"el-table__body\"]//tr//*[contains(text(),'"+text+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(text+"  cant found on grid",element);
        logger.info(text+" found on grid");
    }

    @Step("Does Grid Has the text <text> if there fail")
    public void doesGridHasTheTextOpesite(String text){
        waitSeconds(1);
        if (text.contains("LastRiskEngineer") || text.contains("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            text=engineerDetail.readAndgetTxtValue();
        }
        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'"+text+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNull(text+" found on grid",element);
        logger.info(text+" found on grid");
    }

    @Step("Click Btn with text on grid <btnText> <lineText>")
    public void clickBtnOnGrid(String btnText, String lineText){

        if (lineText.contains("LastRiskEngineer")){
            EngineerDetail engineerDetail=new EngineerDetail();
            lineText=engineerDetail.readAndgetTxtValue();
        } else if (lineText.equals("cellValue")){
            lineText=cellValue;
        }
        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'"+lineText+"')]/ancestor::tr[@class=\"el-table__row\"]//*[contains(text(),('"+btnText+"'))]";
        logger.info("created xpath "+xpath);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("lineText: "+lineText+" btn Text "+btnText+" cant found on grid!!!",element);
        clickByElement(element);
    }

    @Step("Check Btn with tag on grid <btnText> <lineText>")
    public void checkBtnOnGridWithClass(String btnText, String lineText){

        if (lineText.contains("LastRiskEngineer")){
            EngineerDetail engineerDetail=new EngineerDetail();
            lineText=engineerDetail.readAndgetTxtValue();
        } else if (lineText.equals("cellValue")){
            lineText=cellValue;
        }
        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'"+lineText+"')]/ancestor::tr[@class=\"el-table__row\"]//*[contains(@class,('"+btnText+"'))]";
        logger.info("created xpath "+xpath);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("lineText: "+lineText+" btn Text "+btnText+" cant found on grid!!!",element);
    }

    @Step("Click Btn with tag on grid <btnText> <lineText>")
    public void clickBtnOnGridWithClass(String btnText, String lineText){

        if (lineText.contains("LastRiskEngineer")){
            EngineerDetail engineerDetail=new EngineerDetail();
            String[] s = null;
            String s1  = "";
            if(lineText.contains("#")){
                s=lineText.split("#");
                s1=s[1];
            }
            lineText=engineerDetail.readAndgetTxtValue()+s1;
        } else if (lineText.equals("cellValue")){lineText=cellValue;}

        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'"+lineText+"')]/ancestor::tr[@class=\"el-table__row\"]//*[contains(@class,('"+btnText+"'))]";
        logger.info("created xpath "+xpath);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("lineText: "+lineText+" btn Text "+btnText+" cant found on grid!!!",element);
        clickByElement(element);
        waitSeconds(1);
    }

    @Step("Check Grid headers <titles>")
    public void checkHeaders(String titles){
        String[] gridtabs=titles.split(" ");
        String xpath="//thead//*[contains(text(),'TABHERE')]";
        WebElement element;
        for (String s:gridtabs){
            xpath=xpath.replaceAll("TABHERE",s);
            element=findElementWithXpathWithOutAssert(xpath);
            logger.info(s+ " cheking...");
            Assert.assertNotNull(s+ "cant found on call history tab",element);
        }
    }

    @Step("Check Grid headers <titles> grid Title <title>")
    public void checkHeaders(String titles,String title){
        String[] gridtabs=titles.split(" ");
        String xpath="//*[@class=\"section-header\" and .='"+title+"']/..//thead//*[contains(text(),'TABHERE')]";
        WebElement element;
        for (String s:gridtabs){
            xpath=xpath.replaceAll("TABHERE",s);
            element=findElementWithXpathWithOutAssert(xpath);
            logger.info(s+ " cheking...");
            Assert.assertNotNull(s+ "cant found on call history tab",element);
        }
    }

    @Step("Click Btn with text on grid fist row <btnText> and store cell text <cellNum>")
    public void clickBtnOnGrid(String btnText,int cellNum){
        String celValueXpath="((//*[@class=\"el-table__body\"]//tr)[1]//*[@class=\"cell\"])["+cellNum+"]";
        String xpath="(//*[@class=\"el-table__body\"]//tr)//*[contains(text(),'')]/ancestor::tr[@class=\"el-table__row\"]//*[contains(text(),('"+btnText+"'))]";
        cellValue=findElementWithXpathWithOutAssert(celValueXpath).getText();
        logger.info("readed cell value is: "+cellValue);
        logger.info("created xpath "+xpath);
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull("cellValue: "+cellValue+" btn Text "+btnText+" cant found on grid!!!",element);
        clickByElement(element);
    }
}
