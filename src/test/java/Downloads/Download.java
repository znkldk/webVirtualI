package Downloads;

import com.thoughtworks.gauge.Step;
import org.example.BaseSteps;
import org.junit.Assert;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Download extends BaseSteps {

    @Step("Downloads Check File <Name> and DeleteIt")
    public void doesFileExistAndDeleteIt(String fileName)
    {
        waitSeconds(2);
        boolean contains=false;
        String containsFileName="a";
        if (fileName.contains("contains")){
            contains=true;
            containsFileName=fileName.split(",")[1];
            fileName=getFilesName();
        }

        File file
                = new File("/Users/znkldk/Desktop/Automation_projects/Downloads/"+fileName);

        if (file.delete()) {
            System.out.println("File deleted successfully");
        }
        else {
            Assert.fail(fileName+" File Cant Found");
        }
        if (contains){
            Assert.assertTrue("file dont found:"+containsFileName,
                    fileName.contains(containsFileName));
        }
    }

    public String getFilesName() {
        File folder = new File("/Users/znkldk/Desktop/Automation_projects/Downloads/");
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            return listOfFiles[i].getName();
        }
        Assert.fail();
        return null;
    }


}
