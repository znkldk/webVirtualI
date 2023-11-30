package org.example;

import Inspection.InspectionDetailMethods;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.Random;
import java.util.logging.Logger;

public class UploadFile extends Methods {
    private final String baseXpath = "//*[@class=\"el-dialog__wrapper document-upload-dialog\" and not(contains(@style,'none'))]/div[@aria-label=\"TitleHere\" and not(contains(@style,'none'))]";
    private final String popUpTitleXpath = baseXpath + "//*[@class=\"el-dialog__title\"]";
    private final String popUpImageFieldXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@class=\"file-upload-image\"]";
    private final String popUpVideoFieldXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@class=\"file-upload-video\"]";
    private final String popUpAudioFieldXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@class=\"file-upload-audio\"]";
    private final String popUpDocsFieldXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@class=\"file-upload-docs\"]";
    private final String popUpImageTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[.='Image']";
    private final String popUpDocumentTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[.='Document']";
    private final String popUpAudioTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[.='Audio']";
    private final String popUpVideoTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[.='Video']";
    private final String popUpVisitLibraryBtnXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@type=\"button\" and .='Select from Visit Library']";
    private final String popUpVisitLibraryTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]/*[.='Visit Library']";
    private final String popUpNoteXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[@class=\"el-textarea__inner\"]";
    private final String popUpNoteTitleXpath = baseXpath + "/div[@class=\"el-dialog__body\"]//*[.=\"Notes\"]";
    private final String popUpOkBtn = "//*[@class=\"el-message-box__btns\"]//*[@type=\"button\"]//*[normalize-space(text())='OK']";
    private final String popUpCancelBtn = "//*[@class=\"el-message-box__btns\"]//*[@type=\"button\"]//*[normalize-space(text())='Cancel']";
    public boolean deleteFunctionIs = false;
    Logger logger = Logger.getLogger(String.valueOf(InspectionDetailMethods.class));
    private String videoInput = baseXpath + "//*[@class=\"el-upload__input\" and @accept=\"video/*\"]";
    private String audioInput = baseXpath + "//*[@class=\"el-upload__input\" and @accept=\"audio/*\"]";
    private String documentInput = baseXpath + "//*[@class=\"el-upload__input\" and contains(@accept,\"document\")]";
    private String imageInput = baseXpath + "//*[@class=\"el-upload__input\" and @accept=\"image/*\"]";
    private String popUpCloseBtnXpath = baseXpath + "//*[@class=\"el-dialog__close el-icon el-icon-close\"]";

    private static String getAbsolutePath(String notAbsolutePath) {
        File file = new File(notAbsolutePath);
        return isSystemMac() ? file.getAbsolutePath() : notAbsolutePath;
    }

    private int getPathWayCount(String path) {
        /*
        bu metod o pathte gelene kadar kaç başlık gelindigini ögrenmek için
        bu şekilde yüklenen elementlerin sayıları sabit bi şekilde birbirinden farklı olur
         */
        String[] s = path.split(">");
        logger.info("Path: " + path + "  getPathWayCount count is:" + s.length + 1);
        return s.length + 1;
    }

    public void uploadFilesAllKind(ErrorMethod errorMethod, String Path, String Name) {
        checkPopUpComponents(errorMethod, Path, Name);
        uploadImage(errorMethod, Path, Name);
        uploadVideo(errorMethod, Path, Name);
        uploadAudio(errorMethod, Path, Name);
        uploadDocument(errorMethod, Path, Name);
        WriteNameToNotes(errorMethod, Path, Name);
        waitBySeconds(1);

        popUpCloseBtnXpath = popUpCloseBtnXpath.replaceAll("TitleHere", Name);
        logger.info("close btn xpath: " + popUpCloseBtnXpath);
        WebElement closeBtn = findElementWithXpathWithOutAssert(popUpCloseBtnXpath);
        waitBySeconds(1);
        BaseSteps.clickByElement(closeBtn);
    }

    private void WriteNameToNotes(ErrorMethod errorMethod, String Path, String Name) {
        WebElement popUpNote = findElementWithXpathWithOutAssertLocated(popUpNoteXpath.replaceAll("TitleHere", Name));
        if (popUpNote == null) {
            logger.info("" +
                    "popUpNote cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("popUpNote cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteXpath.replaceAll("TitleHere", Name));
            return;
        }
        BaseSteps.writeTextWithElement(popUpNote, Name, Name);
    }

    private void uploadVideo(ErrorMethod errorMethod, String Path, String Name) {
        boolean needReturn;
        videoInput = videoInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(videoInput);
        logger.info("video xpath: " + videoInput);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                element,
                "video input element cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name);
        if (needReturn) {
            return;
        }
        String absolutePathOfVideo = "file cant found";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Video");
            logger.info("video Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "video");

        } catch (Exception e) {
            errorMethod.addErrorMessage(
                    "some problems appears when video upload \n" +
                            "path:" + Path + "\n" +
                            "xpath:" + videoInput + "\n" +
                            "file path: " + absolutePathOfVideo + "\n" +
                            "name:" + Name);
            return;
        }
        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//video");
        needReturn = errorMethod.addErrorMessageIfElementNull(
                videoControl,
                "some problems appears when video upload \n" +
                        "path:" + Path + "\n" +
                        "file path: " + absolutePathOfVideo + "\n" +
                        "name:" + Name + "\n" +
                        "xpath:" + videoInput);
        if (needReturn) {
            return;
        }

        deleteFileAndCheckIt("video", errorMethod, Path, Name);

        return;
    }

    private void uploadImage(ErrorMethod errorMethod, String Path, String Name) {
        boolean needReturn;
        imageInput = imageInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(imageInput);
        logger.info("image xpath: " + imageInput);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                element,
                "image input element cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name
        );

        if (needReturn) {
            return;
        }

        String absolutePathOfVideo = "file cant found!!";

        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Img");
            logger.info("image Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Images");

        } catch (Exception e) {
            errorMethod.addErrorMessage(
                    "some problems appears when image upload \n" +
                            "path:" + Path + "\n" +
                            "file path:" + absolutePathOfVideo + "\n" +
                            "name:" + Name);
            return;
        }
        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//img");

        needReturn = errorMethod.addErrorMessageIfElementNull(
                videoControl,
                "some problems appears when image upload \n" +
                        "path:" + Path + "\n" +
                        "file path:" + absolutePathOfVideo + "\n" +
                        "name:" + Name);

        if (needReturn) {
            return;
        }

        deleteFileAndCheckIt("img", errorMethod, Path, Name);

        return;
    }

    private void uploadAudio(ErrorMethod errorMethod, String Path, String Name) {
        boolean needReturn;
        audioInput = audioInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(audioInput);
        logger.info("audio xpath: " + audioInput);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                element,
                "audio input element cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name);
        if (needReturn) {
            return;
        }


        String absolutePathOfVideo = "file cant found!!";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Sound");
            logger.info("audio Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Sounds");

        } catch (Exception e) {
            errorMethod.addErrorMessage(
                    "some problems appears when audio upload \n" +
                            "path:" + Path + "\n" +
                            "file path:" + absolutePathOfVideo + "\n" +
                            "name:" + Name);
            return;
        }
        waitBySeconds(2);
        WebElement audioControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//audio");
        needReturn = errorMethod.addErrorMessageIfElementNull(
                audioControl,
                "some problems appears when audio upload \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name + "\n" +
                        "file path:" + absolutePathOfVideo + "\n" +
                        "xpath:" + audioInput
        );
        if (needReturn) {
            return;
        }
        deleteFileAndCheckIt("audio", errorMethod, Path, Name);

        return;
    }

    private void uploadDocument(ErrorMethod errorMethod, String Path, String Name) {
        boolean needReturn;
        documentInput = documentInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(documentInput);
        logger.info("document xpath: " + documentInput);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                element,
                "document input element cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name);
        if (needReturn) {
            return;
        }
        String absolutePathOfVideo = "file cant found";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Doc");
            logger.info("document Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Documents");

        } catch (Exception e) {
            errorMethod.addErrorMessage(
                    "some problems appears when document upload \n" +
                            "path:" + Path + "\n" +
                            "name:" + Name + "\n" +
                            "filePath:" + absolutePathOfVideo
            );
            return;
        }
        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//a");
        needReturn = errorMethod.addErrorMessageIfElementNull(
                videoControl,
                "some problems appears when document upload \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name + "\n" +
                        "xpath:" + documentInput + "\n" +
                        "filePath:" + absolutePathOfVideo
        );
        if (needReturn) {
            return;
        }

        deleteFileAndCheckIt("a", errorMethod, Path, Name);
        return;
    }

    public String getRandomFileFromDirectory(String kind) {
        logger.info("choosing video");
        String folder = Methods.createPathProperties("allFiles") + "/" + kind + "/";
        String file = "";

        switch (kind) {
            case "Doc":
                file = "sample.odt";
                break;
            case "Img":
                file = "1.png";
                break;
            case "Sound":
                file = "soundSample.mp3";
                break;
            case "Video":
                file = "video.mp4";
                break;
        }
        return folder + file;
    }

    private String getRandomFileFromDirectoryOld(String kind) {
        logger.info("choosing video");
        File folder;
        if (isThisADockerTest) {
            folder = new File(getAbsolutePath("/Users/testinium/Desktop/VIAllUpdateAbleFiles/" + kind + "/"));
            System.out.println("path:" + " testinium ");
        } else {
            folder = new File(getAbsolutePath("/Users/znkldk/Desktop/VIAllUpdateAbleFiles/" + kind + "/"));
            System.out.println("path:" + " znkldk ");
        }

        File[] listOfFiles = folder.listFiles();
        System.out.println(listOfFiles.length);
        Random rand = new Random();
        int randomNumber = rand.nextInt(listOfFiles.length);
        logger.info("Choosing file for: " + kind + " : " + listOfFiles[randomNumber].getName());
        return listOfFiles[randomNumber].getAbsolutePath();
    }

    public void checkPopUpComponents(ErrorMethod errorMethod, String Path, String Name) {

        WebElement title = findElementWithXpathWithOutAssertLocated(popUpTitleXpath.replaceAll("TitleHere", Name));
        if (title == null) {
            logger.info("Pop up title cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("Pop up title cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpTitleXpath.replaceAll("TitleHere", Name));
        }

        WebElement ImageField = findElementWithXpathWithOutAssertLocated(popUpImageFieldXpath.replaceAll("TitleHere", Name));
        if (ImageField == null) {
            logger.info("ImageField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpImageFieldXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("ImageField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpImageFieldXpath.replaceAll("TitleHere", Name));
        }

        WebElement VideoField = findElementWithXpathWithOutAssertLocated(popUpVideoFieldXpath.replaceAll("TitleHere", Name));
        if (VideoField == null) {
            logger.info("VideoField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVideoFieldXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("VideoField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVideoFieldXpath.replaceAll("TitleHere", Name));
        }

        WebElement AudioField = findElementWithXpathWithOutAssertLocated(popUpAudioFieldXpath.replaceAll("TitleHere", Name));
        if (AudioField == null) {
            logger.info("AudioField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpAudioFieldXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("AudioField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpAudioFieldXpath.replaceAll("TitleHere", Name));
        }

        WebElement DocsField = findElementWithXpathWithOutAssertLocated(popUpDocsFieldXpath.replaceAll("TitleHere", Name));
        if (DocsField == null) {
            logger.info("DocsField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpDocsFieldXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("DocsField cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpDocsFieldXpath.replaceAll("TitleHere", Name));
        }

        WebElement ImageTitle = findElementWithXpathWithOutAssertLocated(popUpImageTitleXpath.replaceAll("TitleHere", Name));
        if (ImageTitle == null) {
            logger.info("ImageTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpImageTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("ImageTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpImageTitleXpath.replaceAll("TitleHere", Name));
        }

        WebElement DocumentTitle = findElementWithXpathWithOutAssertLocated(popUpDocumentTitleXpath.replaceAll("TitleHere", Name));
        if (DocumentTitle == null) {
            logger.info("DocumentTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpDocumentTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("DocumentTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpDocumentTitleXpath.replaceAll("TitleHere", Name));
        }

        WebElement AudioTitle = findElementWithXpathWithOutAssertLocated(popUpAudioTitleXpath.replaceAll("TitleHere", Name));
        if (AudioTitle == null) {
            logger.info("AudioTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpAudioTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("AudioTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpAudioTitleXpath.replaceAll("TitleHere", Name));
        }

        WebElement VideoTitle = findElementWithXpathWithOutAssertLocated(popUpVideoTitleXpath.replaceAll("TitleHere", Name));
        if (VideoTitle == null) {
            logger.info("VideoTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVideoTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("VideoTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVideoTitleXpath.replaceAll("TitleHere", Name));
        }

        WebElement VisitLibraryBtn = findElementWithXpathWithOutAssertLocated(popUpVisitLibraryBtnXpath.replaceAll("TitleHere", Name));
        if (VisitLibraryBtn == null) {
            logger.info("VisitLibraryBtn cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVisitLibraryBtnXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("VisitLibraryBtn cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVisitLibraryBtnXpath.replaceAll("TitleHere", Name));
        }

        WebElement VisitLibraryTitle = findElementWithXpathWithOutAssertLocated(popUpVisitLibraryTitleXpath.replaceAll("TitleHere", Name));
        if (VisitLibraryTitle == null) {
            logger.info("VisitLibraryTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVisitLibraryBtnXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("VisitLibraryTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpVisitLibraryBtnXpath.replaceAll("TitleHere", Name));
        }

        WebElement popUpNote = findElementWithXpathWithOutAssertLocated(popUpNoteXpath.replaceAll("TitleHere", Name));
        if (popUpNote == null) {
            logger.info("popUpNote cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("popUpNote cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteXpath.replaceAll("TitleHere", Name));
        }

        WebElement popUpNoteTitle = findElementWithXpathWithOutAssertLocated(popUpNoteTitleXpath.replaceAll("TitleHere", Name));
        if (popUpNoteTitle == null) {
            logger.info("popUpNoteTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteTitleXpath.replaceAll("TitleHere", Name));

            errorMethod.addErrorMessage("popUpNoteTitle cant found \n" +
                    "Path:" + Path + "\n" +
                    "Name:" + Name + "\n" +
                    "xpath:" + popUpNoteTitleXpath.replaceAll("TitleHere", Name));
        }
    }

    public void deleteFileAndCheckIt(String type, ErrorMethod errorMethod, String Path, String Name) {
        boolean needReturn;

        if (!deleteFunctionIs) {
            return;
        }
        logger.info("Deleting checks in progress...");
        String deleteBtnXpath =
                baseXpath.replaceAll("TitleHere", Name) +
                        "//div[" + type + "]/div//*[@class=\"fa fa-trash text-danger\"]";
        WebElement deleteBtn =
                findElementWithXpathWithOutAssert(deleteBtnXpath);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                deleteBtn,
                "delete Btn cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name +
                        "type:" + type);
        if (needReturn) {
            return;
        }

        BaseSteps.clickByElement(deleteBtn);
        WebElement cancelBtn = findElementWithXpathWithOutAssert(popUpCancelBtn);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                cancelBtn,
                "cancelBtn cant found \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name +
                        "type:" + type);
        if (needReturn) {
            return;
        }

        BaseSteps.clickByElement(cancelBtn);
        waitBySeconds(1);
        deleteBtn =
                findElementWithXpathWithOutAssert(deleteBtnXpath);


        needReturn = errorMethod.addErrorMessageIfElementNull(
                deleteBtn,
                "delete Btn disappear !! \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name + "\n" +
                        "type:" + type);
        if (needReturn) {
            return;
        }


        BaseSteps.clickByElement(deleteBtn);
        waitBySeconds(1);
        WebElement okBtn = findElementWithXpathWithOutAssert(popUpOkBtn);
        BaseSteps.clickByElement(okBtn);

        waitBySeconds(1);

        needReturn = errorMethod.addErrorMessageIfElementNull(
                deleteBtn,
                "delete Btn does not work !! \n" +
                        "path:" + Path + "\n" +
                        "name:" + Name +
                        "type:" + type);
        if (needReturn) {
            return;
        }
    }

    @Step("Inspection Upload image <name>")
    public void uploadImageStep(String Name) {
        boolean needReturn;
        imageInput = imageInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(imageInput);
        logger.info("image xpath: " + imageInput);

        String absolutePathOfVideo = "file cant found!!";

        absolutePathOfVideo = getRandomFileFromDirectory("Img");
        logger.info("image Path=" + absolutePathOfVideo);
        BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Images");

        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//img");
    }

    @Step("upload Image name: <Name>")
    public void uploadImageForGauge(String Name) {
        waitBySeconds(2);
        imageInput = imageInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(imageInput);
        logger.info("image xpath: " + imageInput);

        String absolutePathOfVideo = "file cant found!!";

        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Img");
            logger.info("image Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Images");

        } catch (Exception e) {

            return;
        }
        waitBySeconds(5);
    }

    @Step("upload Video name: <Name>")
    public void uploadVideo(String Name) {
        boolean needReturn;
        videoInput = videoInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(videoInput);
        logger.info("video xpath: " + videoInput);

        String absolutePathOfVideo = "file cant found";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Video");
            logger.info("video Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "video");

        } catch (Exception e) {
            Assert.fail("something is wrong");
        }
        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//video");
    }

    @Step("upload Audio name: <Name>")
    public void uploadAudio(String Name) {
        boolean needReturn;
        audioInput = audioInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(audioInput);
        logger.info("audio xpath: " + audioInput);

        String absolutePathOfVideo = "file cant found!!";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Sound");
            logger.info("audio Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Sounds");

        } catch (Exception e) {
            Assert.fail("uploadAudio Error");
        }
        waitBySeconds(2);
        WebElement audioControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//audio");
        return;
    }

    @Step("upload Doc name: <Name>")
    public void uploadDocument(String Name) {
        boolean needReturn;
        documentInput = documentInput.replaceAll("TitleHere", Name);
        WebElement element = findElementWithXpathWithOutAssertLocated(documentInput);
        logger.info("document xpath: " + documentInput);

        String absolutePathOfVideo = "file cant found";
        try {
            absolutePathOfVideo = getRandomFileFromDirectory("Doc");
            logger.info("document Path=" + absolutePathOfVideo);
            BaseSteps.writeTextWithElement(element, absolutePathOfVideo, "Documents");

        } catch (Exception e) {
            Assert.fail("asd");
        }
        waitBySeconds(2);
        WebElement videoControl = findElementWithXpathWithOutAssertLocated(baseXpath.replaceAll("TitleHere", Name) + "//a");

    }

    @Step("Inspection Close File Upload Window <Name>")
    public void fileUpload(String Name) {
        WebElement closeBtn = findElementWithXpathWithOutAssert(popUpCloseBtnXpath.replaceAll("TitleHere", Name));
        waitBySeconds(1);
        BaseSteps.clickByElement(closeBtn);
        waitBySeconds(1);
    }
}
