package Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.BaseTest;
import java.util.List;

public class ElementFetch {
    public WebElement getElement(String identifiertype, String identifiervalue) {
        switch (identifiertype) {
            case "id":
                return BaseTest.driver.findElement(By.id(identifiervalue));
            case "classname":
                return BaseTest.driver.findElement(By.className(identifiervalue));
            case "xpath":
                return BaseTest.driver.findElement(By.xpath(identifiervalue));
            case "CSS":
                return BaseTest.driver.findElement(By.cssSelector(identifiervalue));
            case "tagname":
                return BaseTest.driver.findElement(By.tagName(identifiervalue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebElements(String identifiertype, String identifiervalue) {
        switch (identifiertype) {
            case "id":
                return BaseTest.driver.findElements(By.id(identifiervalue));
            case "classname":
                return BaseTest.driver.findElements(By.className(identifiervalue));
            case "xpath":
                return BaseTest.driver.findElements(By.xpath(identifiervalue));
            case "CSS":
                return BaseTest.driver.findElements(By.cssSelector(identifiervalue));
            case "tagname":
                return BaseTest.driver.findElements(By.tagName(identifiervalue));
            default:
                return null;
        }
    }
}
