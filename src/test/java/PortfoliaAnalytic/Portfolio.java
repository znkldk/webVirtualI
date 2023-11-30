package PortfoliaAnalytic;

import com.thoughtworks.gauge.Step;
import org.example.BaseSteps;
import org.example.Methods;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class Portfolio extends BaseSteps {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));
    public String reports=
            "Average P/E Ratio by Occupancy Type\n" +
            "P/E Ratio Distr. by Occupancy Type\n" +
            "Exposure & Protection Scores by Occ. Type\n" +
            "Exposure vs Protection\n" +
            "P/E Ratio vs MFL Estimate\n" +
            "P/E Ratio vs MPL Estimate\n" +
            "Recom. Priority by Occupancy Type\n" +
            "Sum Insured\n" +
            "Channel Info\n" +
            "Claims by Occupancy Type\n" +
            "Claims by Client Type\n" +
            "Root Cause\n" +
            "Natcat Geophysical Perils\n" +
            "Natcat Meteorological Perils\n" +
            "Natcat Climatological Perils\n" +
            "Total Surveys";
    BaseSteps baseSteps=new BaseSteps();

    @Step("Portfolio Analis Check All Reports")
    public void CheckAllReports(){
        String baseXpath="//*[@class=\"el-card__body\"]//*[@role=\"menubar\"]//*[.='TextHere']";
        String[] report=reports.split("\n");
        WebElement reportManuBtn;

        for (String s:report){
            reportManuBtn=findElementWithXpathWithOutAssert(baseXpath.replaceAll("TextHere",s));
            clickByElement(reportManuBtn);
            failIfElementNotExist("Portfolio-Query-Btn","report Failed "+s);
            failIfElementNotExist("Portfolio-Hide Filter-Btn","report Failed "+s);
            failIfElementNotExist("Portfolio-Filter-Btn","report Failed "+s);
            failIfElementNotExist("Portfolio-Celendar-Btn","report Failed "+s);
            waitSeconds(1);
        }
    }

    @Step("Portfolio Analis Open Reports <text>")
    public void openReports(String text){
        String baseXpath="//*[@class=\"el-card__body\"]//*[@role=\"menubar\"]//*[.='TextHere']";

        WebElement reportManuBtn=findElementWithXpathWithOutAssert(baseXpath.replaceAll("TextHere",text));
        clickByElement(reportManuBtn);
        waitSeconds(1);
    }

    @Step("Portfolio filter Choose <text>")
    public void dropdownChoose(String text){
        String baseXpath="//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]//*[contains(text(),'TextHere')]";
        String baseCpathTag="//*[@class=\"el-select__tags-text\" and (contains(text(),'TextHere'))]";
        String tagCloseBtn="//*[@class=\"el-tag__close el-icon-close\"]";
        javascriptclicker("Portfolio-Filter-Btn");
        waitSeconds(2);
        WebElement dropdown=findElementWithXpathWithOutAssert(baseXpath.replaceAll("TextHere",text));
        clickByElement(dropdown);
        waitSeconds(2);
        WebElement tag=
                findElementWithXpathWithOutAssert(baseCpathTag.replaceAll("TextHere",text));
        Assert.assertNotNull(text+" tag cant found on dd",tag);
        javascriptclickerByElement(findElementWithXpathWithOutAssert(tagCloseBtn));
        waitSeconds(1);

        tag= findElementWithXpathWithOutAssert(baseCpathTag.replaceAll("TextHere",text));
        Assert.assertNull(text+" tag found on dd",tag);
    }
}
