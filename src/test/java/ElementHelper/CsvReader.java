package ElementHelper;

import com.opencsv.CSVReader;
import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class CsvReader extends ElementKeeper{


    public static void setAllElements() {
        //all csv files must in resources/ElementValues

        File dir = new File("./src/test/resources/ElementValues");
        File[] files = dir.listFiles((dir1, name) -> name.endsWith(".csv"));
        for (File file :files){
            readDataLineByLine(String.valueOf(file));
        }
    }

    public static void readDataLineByLine(String file)
    {
        try {

            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;
            String bait;
            while ((nextRecord = csvReader.readNext()) != null) {

                if (nextRecord[0].equals("")){
                    continue;
                }
                try{
                    bait=allElements.get(nextRecord[0]).get(0);
                    if (bait!=null){
                        Assert.fail(nextRecord[0]+" is duplicated please check it");
                    }
                }catch (Exception e){

                }
                //------------------------------------------
                //after checking values here we set in allElements
                allElements.put(nextRecord[0], new ArrayList<>());
                allElements.get(nextRecord[0]).add(nextRecord[1]);
                allElements.get(nextRecord[0]).add(nextRecord[2]);

                if (allElements.get(nextRecord[0]).get(0).equals("")){
                    Assert.fail(nextRecord[0]+ " value or type is empty please check it");
                }
                if (allElements.get(nextRecord[0]).get(1).equals("")){
                    Assert.fail(nextRecord[0]+ " value or type is empty please check it");
                }
            }
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
