package ElementHelper;

import org.junit.Assert;
import org.openqa.selenium.By;

public class InfoParam extends ElementKeeper {

    public By getBy(String key) {
        String type = allElements.get(key).get(0);
        String value = allElements.get(key).get(1);
        By by = null;
        if (type.equals("xpath")) {
            by = By.xpath(value);
        } else if (type.equals("id")) {
            by = By.id(value);
        } else if (type.equals("css")) {
            by = By.cssSelector(value);
        } else {
            Assert.fail(type + " is not a correct type!! Key is= " + key);
        }
        return by;
    }
}
