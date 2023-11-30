package org.example;

import AccountHandle.AccountHandle;
import EngineerDetail.EngineerDetail;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CallHistory extends BaseSteps{
    private static String dayOfWeek;
    private static String dayNumber;
    private static String month;
    private static String yearNumber;
    private static String lastMondayDayNumber;
    private static String lastMondaymonth;
    private static String expectedSundayDayNumber;
    private static String expectedSundaymonth;
    private static List<String> riskEngineers =new ArrayList<String>();  ;

    @Step("Call Agenda open today")
    public void callAgendaClickToday(){
        WebElement isTodayBtnDisable=findElementWithKeyWithOutAssert("CallAgenda-DisableToday-Btn");
        if (isTodayBtnDisable==null){
            clicktWithKey("CallAgenda-Today-Btn");
        }else {
            logger.info("today button is disable!! click is skipped");
        }
    }

    @Step("Call Agenda Click oclock <time> in day mode")
    public void clickByClockInDayMode(String time){
        String baseXpath="//*[contains (@class , \"fc-timegrid-slot-lane\") and @data-time=\""+time+"\"]";
        WebElement element=findElementWithXpathWithOutAssert(baseXpath);
        Assert.assertNotNull("Cant find time in Calendar="+ time,
                element);
        BaseSteps.clickByElement(element);
    }

    @Step("Call Agenda Click day <dayName> oclock <time> in week mode")
    public void clickTheTimeInDayMode(String dayName,String clock){
        String baseXpathOfDay=
                "//*[contains(@class,'fc-timegrid-col fc-day fc-day-dayNameHERE')]"
                        .replaceAll("dayNameHERE",dayName);
        String baseXpathOfClock=
                "//*[@data-time='clockHERE' and @class=\"fc-timegrid-slot fc-timegrid-slot-lane fc-timegrid-slot-minor\"]"
                        .replaceAll("clockHERE",clock);
        WebElement ElementOfDay=findElementWithXpathWithOutAssertLocated(baseXpathOfDay);
        WebElement ElementOfClock=findElementWithXpathWithOutAssertLocated(baseXpathOfClock);
        Assert.assertNotNull("SomeProblems appear Please Check Code",ElementOfDay);
        Assert.assertNotNull("SomeProblems appear Please Check Code",ElementOfClock);

        scrollToElement(ElementOfClock);
        hoverByElement(ElementOfDay);
        waitSeconds(1);
        clickByElement(ElementOfClock);
    }

    @Step("Call Agenda Click day <mounthDay> in mounth mode")
    public void callAgendaDaySelenctMonthgg(String mounthDay){
        String baseXpathTomorrow="(//*[contains(@class,\"day-future\")])[1]";
        String baseXpathYesterday="(//*[contains(@class,\"day-past\")])[last()]";
        String baseXpathToday="(//*[contains(@class,\"day-today\")])[last()]";

        WebElement element;
        if (mounthDay.contains("oday")){
            element=findElementWithXpathWithOutAssert(baseXpathToday);
        }else if(mounthDay.contains("row")){
            element=findElementWithXpathWithOutAssert(baseXpathTomorrow);
        }else{
            element=findElementWithXpathWithOutAssert(baseXpathYesterday);
        }
        Assert.assertNotNull("element not found please check it",element);
        clickByElement(element);
        waitSeconds(1);

    }

    @Step("Call Agenda Check Date for Day mode add <intForAddDay>")
    public void checkDateForDay(int intForAddDay){
        setDate(intForAddDay,"day");
        String baseXpath="//*[@class=\"fc-header-toolbar fc-toolbar fc-toolbar-ltr\"]//*[contains(text(),\"TextHere\")]";
        WebElement day=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",dayNumber)
        );

        WebElement mounth=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",month)
        );

        WebElement year=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",yearNumber)
        );

        Assert.assertNotNull("Date is not correct please Check it",day);
        Assert.assertNotNull("Date is not correct please Check it",mounth);
        Assert.assertNotNull("Date is not correct please Check it",year);

    }

    @Step("Call Agenda check Are week date numbers proper")
    public void checkWeekModePropper(){
        setMondayAndSundayLastMonday();
        String baseXpath="//*[@class=\"fc-header-toolbar fc-toolbar fc-toolbar-ltr\"]//*[contains(text(),\"TextHere\")]";

        WebElement lastMondaymonthElement=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",lastMondaymonth)
        );
        WebElement expectedSundayDayNumberElement=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",expectedSundayDayNumber)
        );
        WebElement expectedSundaymonthElement=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",expectedSundaymonth)
        );
        WebElement lastMondayDayNumberElement=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",lastMondayDayNumber)
        );

        Assert.assertNotNull("Date is not correct please Check it",lastMondaymonthElement);
        Assert.assertNotNull("Date is not correct please Check it",expectedSundayDayNumberElement);
        Assert.assertNotNull("Date is not correct please Check it",expectedSundaymonthElement);
        Assert.assertNotNull("Date is not correct please Check it",lastMondayDayNumberElement);

    }

    @Step("Call Agenda Check Date for Month mode add <intForAddMonth>")
    public void checkDateForMonth(int intForAddMonth){
        setDate(intForAddMonth,"MOUNHT");
        String baseXpath="//*[@class=\"fc-header-toolbar fc-toolbar fc-toolbar-ltr\"]//*[contains(text(),\"TextHere\")]";

        WebElement mounth=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",month)
        );

        WebElement year=findElementWithXpathWithOutAssert(
                baseXpath.replaceAll("TextHere",yearNumber)
        );

        Assert.assertNotNull("Date is not correct please Check it",mounth);
        Assert.assertNotNull("Date is not correct please Check it",year);

    }

    @Step("Call Agenda Click Month <mounthDay> in mounth mode")
    public void callAgendaDaySelectMonths(String mounthDay){
        String baseXpathTomorrow="(//*[contains(@class,\"day-future\")])[1]//a[@class =\"fc-daygrid-event fc-daygrid-dot-event fc-event fc-event-start fc-event-end fc-event-future \"]";
        String baseXpathYesterday="(//*[contains(@class,\"day-past\")])[last()]";
        String baseXpathToday="(//*[contains(@class,\"day-today\")])[last()]//a[@class =\"fc-daygrid-event fc-daygrid-dot-event fc-event fc-event-start fc-event-end fc-event-today \"]";

        WebElement element;
        if (mounthDay.contains("oday")){
            element=findElementWithXpathWithOutAssert(baseXpathToday);
        }else if(mounthDay.contains("row")){
            element=findElementWithXpathWithOutAssert(baseXpathTomorrow);
        }else{
            element=findElementWithXpathWithOutAssert(baseXpathYesterday);
        }

        Assert.assertNotNull("element not found please check it",element);
        clickByElement(element);
        waitSeconds(1);

    }

    @Step("Call Agenda if event in <mounthDay> exist fail in mounth mode")
    public void callAgendaDaySelenactMonthss(String mounthDay){
        String baseXpathTomorrow="(//*[contains(@class,\"day-future\")])[1]//*[contains(text(),'Test')]";
        String baseXpathYesterday="(//*[contains(@class,\"day-past\")])[last()]//*[contains(text(),'Test')]";
        String baseXpathToday="(//*[contains(@class,\"day-today\")])[last()]//*[contains(text(),'Test')]";

        WebElement element;
        if (mounthDay.contains("oday")){
            element=findElementWithXpathWithOutAssert(baseXpathToday);
        }else if(mounthDay.contains("row")){
            element=findElementWithXpathWithOutAssert(baseXpathTomorrow);
        }else{
            element=findElementWithXpathWithOutAssert(baseXpathYesterday);
            Assert.assertNull("Created an Appointment in yesterday or previus hour",element);
            return;
        }
        Assert.assertNull("event cant found",element);

    }

    @Step("Call Agenda click event by hours <time>")
    public void clickeventByHout(String time){
        String baseXpathEvent="//*[contains(@class,'fc-event-time')][contains(text(),'"+time+"')]//..//..//..";
        WebElement element=findElementWithXpathWithOutAssert(baseXpathEvent);
        Assert.assertNotNull("hours is not correct please Check it",element);

        clickByElement(element);
        waitSeconds(1);
    }

    @Step("Call Agenda if hours <time> is not exist fail")
    public void doesEventExst(String time){
        String baseXpathEvent="//*[@class=\"fc-event-main-frame\"]//*[.='hourshere']"
                .replaceAll("hourshere",time);

        WebElement element=findElementWithXpathWithOutAssert(baseXpathEvent);
        Assert.assertNotNull("hours is not correct please Check it",element);
        waitSeconds(1);
    }

    @Step("Call Agenda if hours <time> is exist fail")
    public void clickeventByHoutt(String time){
        String baseXpathEvent="//*[@class=\"fc-event-main-frame\"]//*[.='hourshere']"
                .replaceAll("hourshere",time);

        WebElement element=findElementWithXpathWithOutAssert(baseXpathEvent);
        Assert.assertNull("Unsuccesfull delete event hours: "+time,element);

    }

    @Step("Call Agenda if hours <time> is exist fail past")
    public void checkPastAppointment(String time){
        String baseXpathEvent="//*[@class=\"fc-event-main-frame\"]//*[.='hourshere']"
                .replaceAll("hourshere",time);

        WebElement element=findElementWithXpathWithOutAssert(baseXpathEvent);
        Assert.assertNull("Created an Appointment in past hours "+time,element);

    }

    @Step("Call Agenda Choose <value> from <dropDown> dropdown")
    public void dropDownSelecter(String value,String dropdown){
        String valueBaseXpath="//*[(@class=\"el-picker-panel time-select el-popper\" or @x-placement=\"bottom-end\") and not(contains(@style,'none'))]//*[contains(text(),'VALUEHERE')]";
        // This is working xpath beside Inspection detail page
        //String valueBaseXpath="//*[@class=\"el-picker-panel time-select el-popper\" and not(contains(@style,'none'))]//*[contains(text(),'VALUEHERE')]";

        clicktWithKey(dropdown);
        waitSeconds(1);

        if (value.equals("rank")){
            EngineerDetail engineerDetail=new EngineerDetail();
            value=engineerDetail.readAndgetTxtValue();
        }
        valueBaseXpath=valueBaseXpath.
                replaceAll("VALUEHERE",value);
        WebElement element=findElementWithXpathWithOutAssert(valueBaseXpath);
        Assert.assertNotNull(dropdown+" dropdownunda "+value+" elementi bulunamadı xpath: "+valueBaseXpath,
                element);
        clickByElement(element);
    }

    @Step("CallAgenda Check participants list <name> <content>")
    public void participandsContentCheck(String name,String content1){
        String xpathContent=
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[contains(text(),'"+content1+"')]";
        String xpathEditBtn =
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[@class=\"fa fa-edit\"]";
        String xpathDeleteBtn =
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[@class=\"fa fa-trash\"]";

        WebElement contend=findElementWithXpathWithOutAssert(xpathContent);
        WebElement edit=findElementWithXpathWithOutAssert(xpathEditBtn);
        WebElement delete=findElementWithXpathWithOutAssert(xpathDeleteBtn);
        Assert.assertNotNull(
                "name: "+name+"\n" +
                        "content1: "+content1+"\n"+
                        "content is not on participands list"
                ,contend);
        Assert.assertNotNull(
                "name: "+name+"\n" +
                        "content1: "+content1+"\n"+
                        "Edit btn did not found"
                ,edit);
        Assert.assertNotNull(
                "name: "+name+"\n" +
                        "content1: "+content1+"\n"+
                        "delete btn did not found"
                ,delete);
    }

    @Step("CallAgenda Click Edit participants list <name> <content>")
    public void participandsContentClickEdit(String name,String content1){
String xpathEditBtn =
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[@class=\"fa fa-edit\"]";

        WebElement edit=findElementWithXpathWithOutAssert(xpathEditBtn);
        Assert.assertNotNull(
                "name: "+name+"\n" +
                        "content1: "+content1+"\n"+
                        "Edit btn did not found"
                ,edit);
        clickByElement(edit);
        waitSeconds(1);
    }

    @Step("CallAgenda delete participants list <name> <content>")
    public void participandsContentDelete(String name,String content1){
        String xpathContent=
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[contains(text(),'"+content1+"')]";
    String xpathDeleteBtn =
                "//*[@class=\"el-table__row\"]//*[contains(text(),'"+name+"')]/ancestor::tr//*[@class=\"fa fa-trash\"]";

        WebElement delete=findElementWithXpathWithOutAssert(xpathDeleteBtn);
        BaseSteps.clickByElement(delete);
        clicktWithKey("CallAgenda-Yes-Btn");
        waitSeconds(2);

        WebElement contend=findElementWithXpathWithOutAssert(xpathContent);

        Assert.assertNull(
                "name: "+name+"\n" +
                        "content1: "+content1+"\n"+
                        "content is on participands list unsuccesful delete"
                ,contend);
    }

    @Step("Call Agenda Try Wrong Email Addesses")
    public void tryWrongMailAddresses(){
        BaseSteps baseSteps=new BaseSteps();
        Methods methods=new Methods();
        String[] WrongAddresses=
                "znkldk znkldk1 asd@asd asd@ @asdv .com@asd .com asd@asd.c Wrong@as@hell @this.is@Wrongtoo @@@@ little@wrong.a hell.no Thisis.fun"
                        .split(" ");

        String[] trueAddresses=
                "znkldk@asd.com asd.ads@mail.aa true@mail.think much@more.True This@islook.wrongBut.youAre.wrong.this.is.legit.address"
                        .split(" ");
        waitSeconds(2);
        baseSteps.clicktWithKey("SetCall-Contact Email-TextBox");

        for (String wrong :WrongAddresses){
            baseSteps.clearTextWithKey("SetCall-Contact Email-TextBox");
            baseSteps.writeTextWithKey(wrong,"SetCall-Contact Email-TextBox");
            baseSteps.clicktWithKey("CallAgenda-save-btn");
            waitSeconds(1);
            Assert.assertTrue("Wrong mail address is accepted!!! "+wrong
                    ,methods.doesElementExistWithKeyLessTimeOut("SetCall-Contact Email-Err"));
        }
    }

    @Step("Call Agenda Try Wrong Phone number")
    public void tryWrongPhoneNumbers(){
        BaseSteps baseSteps=new BaseSteps();
        Methods methods=new Methods();
        String[] WrongAddresses= "123 312 55228447 55555555555".split(" ");
        String[] trueAddresses= "5522844745".split(" ");

        for (String wrong :WrongAddresses){
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.clearTextWithKey("SetCall-Contact Phone-TextBox");
            waitSeconds(1);
            baseSteps.writeTextWithKey(wrong,"SetCall-Contact Phone-TextBox");
            baseSteps.clicktWithKey("CallAgenda-save-btn");
            Assert.assertTrue("Wrong phone number is accepted!!! "+wrong
                    ,methods.doesElementExistWithKeyLessTimeOut("SetCall-ContactPhone-Err"));
        }
    }
    @Step("Call Agenda Check Appointment Sucject <expectedSucject>")
    public void checkDAteSubjet(String expectedSucject){
        String subjectXpath="//*[@class=\"fc-event-title fc-sticky\" and contains(text(),'"+expectedSucject+"')]";
        WebElement element=findElementWithXpathWithOutAssert(subjectXpath);
        Assert.assertNotNull("expectedSucject name: "+expectedSucject+" cant found!!",element);
    }
    private void setDate(int intForAddDay,String mode) {
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        if (mode.contains("day")){
            c.add(Calendar.DATE, intForAddDay);
        } else {
            c.add(Calendar.MONTH, intForAddDay);
        }
        dt = c.getTime();
        String[] time =String.valueOf(dt).split(" ");
        dayOfWeek=time[0];
        month=time[1].replaceAll("0","");
        dayNumber=String.valueOf(Integer.valueOf(time[2]));
        yearNumber=time[5];
        logger.info("Time is setted dayOfWeek:"+dayOfWeek+"  month: "+month+" day: "+dayNumber+" year:"+yearNumber);
    }

    private void setMondayAndSundayLastMonday(){
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        dt = c.getTime();

        String[] time =String.valueOf(dt).split(" ");
        lastMondaymonth=time[1].replaceAll("0","");
        lastMondayDayNumber=String.valueOf(Integer.valueOf(time[2]));
        logger.info("Last monday is:  month: "+lastMondaymonth+" day: "+lastMondayDayNumber);

        Date dtSunday = new Date();
        Calendar cSunday = Calendar.getInstance();
        cSunday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cSunday.add(Calendar.DATE, 6);
        dtSunday = cSunday.getTime();

        String[] timeSunday =String.valueOf(dtSunday).split(" ");
        expectedSundayDayNumber=timeSunday[1].replaceAll("0","");
        expectedSundaymonth=String.valueOf(Integer.valueOf(timeSunday[2]));
        logger.info("Sunday month: "+expectedSundayDayNumber+" day: "+expectedSundaymonth);

    }

    @Step("Call Agenda Admin Disable All RiskEngineers")
    public void checkboxdisable(){
        clicktWithKey("CallAgenda-CheclAll-Btn");
        WebElement element=findElementWithKeyWithOutAssert("CallAgenda-ControlAllDisable-Control");
        if (element==null){
            clicktWithKey("CallAgenda-CheclAll-Btn");
            waitSeconds(1);
            element=findElementWithKeyWithOutAssert("CallAgenda-ControlAllDisable-Control");
            Assert.assertNotNull("a problem accours when clicing the check box (check box not be disable)"
                    ,element);
        }
    }

    @Step("Click last Risk Engineer On Checkbox list")
    public void clickLAstCreatedRiskEngineerCheckBox(){
        AccountHandle.getMailForSpec();
        String[] mail = AccountHandle.mailAddressCurrent.split("@");

        String xpath="//*[@class=\"el-checkbox\"]//*[contains(text(),'"+mail[0]+"')]";
        WebElement element=findElementWithXpathWithOutAssert(xpath);
        Assert.assertNotNull(xpath + " cant found ",element);
        clickByElement(element);
    }

    @Step("Store all Risk Engineers Names")
    public void storeAllRiskEngineers(){
        boolean doesWeHAveNextRiskEngineer=true;
        String xpath;
        int i=1;

        while (doesWeHAveNextRiskEngineer){
            xpath="(//*[@class=\"el-table__row\"]//*[@class=\"el-table_1_column_1   el-table__cell\"])["+i+"]//*[@class=\"cell\"]";
            WebElement element=findElementWithXpathWithOutAssert(xpath);
            if (element==null){
                doesWeHAveNextRiskEngineer=false;
                logger.info("Risk Engineer count is= "+i);
                return;
            }
            System.out.println(element.getText().trim());
            riskEngineers.add(element.getText().trim());
            i++;
        }
    }

    @Step("Store all Clients Names")
    public void storeAllClients(){
        boolean doesWeHAveNextRiskEngineer=true;
        String xpath;
        int i=1;

        while (doesWeHAveNextRiskEngineer){
            xpath="(//*[@class=\"el-table__row\"]//*[@class=\"el-table_1_column_1   el-table__cell\"])["+i+"]//*[@class=\"cell\"]";
            WebElement element=findElementWithXpathWithOutAssert(xpath);
            if (element==null){
                doesWeHAveNextRiskEngineer=false;
                logger.info("Risk Engineer count is= "+i);
                return;
            }
            System.out.println(element.getText().trim());
            if (element.getText().trim().contains("\\")){
                logger.info("illagal karakter!!! ");
                i++;
                continue;
            }
            riskEngineers.add(element.getText().trim());
            i++;
        }
    }

    @Step("Call Agenda Check All risk Engineers names On Left Checkbox List")
    public void checkAllNames(){
        String xpath="//*[@class=\"el-checkbox\"]//*[contains(text(),'NAMEHERE')]";
        for (String s:riskEngineers){
            WebElement element=findElementWithXpathWithOutAssert(
                    xpath.replaceAll("NAMEHERE",s));
            Assert.assertNotNull("risk engineer cant found on call agenda "+s,
                    element);
            logger.info("risk engineer found "+s);

        }
    }

    @Step("Call Agenda Check All risk Engineers names On CallerDropdown")
    public void checkAllNamesInDropDown(){
       // clicktWithKey("CallAppointment-CallerName-TextBox");
        waitSeconds(1);
        String xpath="//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]/li//*[contains(text(),'NAMEHERE')]";
        for (String s:riskEngineers){
            WebElement element=findElementWithXpathWithOutAssert(
                    xpath.replaceAll("NAMEHERE",s));
            Assert.assertNotNull("risk engineer cant found on dropdown "+s,
                    element);
            logger.info("risk engineer found "+s);

        }
    }

    @Step("Risk Engneer Check All risk Engineers names On CallerDropdown")
    public void checkAllNamesInDropDownRiskEng(){
        waitSeconds(1);
        BaseSteps baseSteps=new BaseSteps();
        WebElement dd=findElementWithKey("Survey-Engineer-Dd");
        String xpath="//*[@class=\"el-scrollbar__view el-select-dropdown__list\"]/li//*[contains(text(),'NAMEHERE')]";
        for (String s:riskEngineers){
            baseSteps.writeTextWithKey(s,"Survey-Engineer-Dd");
            WebElement element=findElementWithXpathWithOutAssert(
                    xpath.replaceAll("NAMEHERE",s));
            Assert.assertNotNull("risk engineer cant found on dropdown "+s,
                    element);
            logger.info("risk engineer found "+s);
            baseSteps.clearTextWithKeyUsingClear("Survey-Engineer-Dd");

        }
    }

    @Step("Inspection Check All Clients names On CallerDropdown")
    public void checkAllNamesInDropDownClients(){
        waitSeconds(1);
        BaseSteps baseSteps=new BaseSteps();
        String xpath="//*[@class=\"el-select-dropdown el-popper\" and not(contains(@style,'none'))]//li//*[contains(text(),'NAMEHERE')]";
        for (String s:riskEngineers){
            if (s.contains("Test")){
                continue;
            }
            baseSteps.writeTextWithKey(s,"Survey-Client-Dd");
            WebElement element=findElementWithXpathWithOutAssert(
                    xpath.replaceAll("NAMEHERE",s));
            Assert.assertNotNull("client cant found on dropdown "+s,
                    element);

            logger.info("client found "+s);
            baseSteps.clearTextWithKeyUsingClear("Survey-Client-Dd");

        }
    }

}