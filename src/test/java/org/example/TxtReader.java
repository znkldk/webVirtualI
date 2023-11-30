package org.example;

import driver.Driver;
import java.io.*;
import java.util.logging.Logger;

public class TxtReader {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));

    public String getText(String path, String checkBoxNameLink) {
        String pathOfResources="./src/test/resources/FormTypes/MaxFactory/";
        path=path.replaceAll(">","/");
        path=pathOfResources+path+"/"+checkBoxNameLink;
        String text="";
        try {
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;
            while ((line = br.readLine()) != null) {
                text=text+"\n"+line;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(text);
        return text;
    }

    public void writeText(String fileName,String text) throws IOException {
        String pathOfDoc;
        if (Driver.isThisADockerTest){
            pathOfDoc="../BarisZinkildakVirtualIDosyalari/"+fileName;
        } else {
            pathOfDoc="./Documents/"+fileName;
        }        clearTheFile(pathOfDoc);
        try {
            FileWriter writer = new FileWriter(pathOfDoc, true);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Text wrote successfully "+"\n"+
                        "Filename: "+fileName+" \n"+
                        "Text:"+text);

    }

    public String readText(String fileName) {
        String pathOfDoc;
        if (Driver.isThisADockerTest){
            pathOfDoc="../BarisZinkildakVirtualIDosyalari/"+fileName;
        } else {
            pathOfDoc="./Documents/"+fileName;
        }
        String text="";
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathOfDoc));
            String line;
            while ((line = br.readLine()) != null) {
                text=text+"\n"+line;

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.info("Text read successfully "+"\n"+
                "Filename: "+fileName+" \n"+
                "Text:"+text);
        return text;
    }

    public void clearTheFile(String path) throws IOException {
        FileWriter fwOb = new FileWriter(path, false);
        PrintWriter pwOb = new PrintWriter(fwOb, false);
        pwOb.flush();
        pwOb.close();
        fwOb.close();
    }
}
