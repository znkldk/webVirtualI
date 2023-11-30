package PropertiesHelper;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    public String readProperties(String fileName, String value) throws IOException {
        FileReader reader = new FileReader("./src/test/resources/Properties/"+fileName+".properties");
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(value);
    }
}