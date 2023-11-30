package org.example;

import Inspection.InspectionDetailMethods;
import org.openqa.selenium.WebElement;

import java.util.logging.Logger;

public class ErrorMethod {
    public static String ErrorMessage="";
    public static boolean isThereError=false;
    Logger logger = Logger.getLogger(String.valueOf(InspectionDetailMethods.class));
    public void addErrorMessage(String errorMessage){
        if (errorMessage.isEmpty()){
            return;
        }
        logger.info("--------------\n" +
                "New Error Message Added: \n" +
                ""+errorMessage+"" +
                "\n--------------");

        ErrorMessage=ErrorMessage+" \n\n---------\n\n "+errorMessage;
        isThereError=true;
    }

    public static boolean isThereError(){
        return isThereError;
    }

    public static String getErrorMessage() {
        return ErrorMessage;
    }

    public boolean addErrorMessageIfElementNull(WebElement element,String Message){

        if (element==null){
            addErrorMessage(Message);
            return true;
        }
        return false;
    }
}
