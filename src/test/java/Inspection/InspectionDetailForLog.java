package Inspection;

import org.example.Methods;

import java.io.File;
import java.util.logging.Logger;

public class InspectionDetailForLog extends Methods {
    Logger logger = Logger.getLogger(String.valueOf(Methods.class));

    public void inspectionDetailClickTitle(String Text){

    }

    public static void main(String[] args) {
        final File folder = new File("./src/test/resources/FormTypes/MaxFactory");
        listFilesForFolder1(folder);
    }
    public static void listFilesForFolder1(final File folder) {
        String space="-";
        String path="";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                    listFilesForFolder2(fileEntry,fileEntry.getName());
                }
            } else {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                }
            }
        }
    }

    public static void listFilesForFolder2(final File folder,String path) {
        String space="--";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                    listFilesForFolder3(fileEntry,path+">"+fileEntry.getName());

                }
            } else {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                }            }
        }
    }

    public static void listFilesForFolder3(final File folder,String path) {
        String space="---";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                    listFilesForFolder4(fileEntry,path+">"+fileEntry.getName());

                }
            } else {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                }            }
        }
    }

    public static void listFilesForFolder4(final File folder,String path) {
        String space="----";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                }
            } else {
                if (!fileEntry.getName().equals(".DS_Store")){
                    System.out.println(space+fileEntry.getName());
                }
            }
        }
    }

}