package seleniumPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Frames {

    public void frameClick() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.leafground.com/");
        Thread.sleep(5000);
        driver.manage().window().maximize();
        WebElement element = driver.findElement(By.xpath("//img[@alt = 'Frame']"));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        element.click();
        driver.switchTo().frame("//iframe[@src = 'default.html']");
        driver.findElement(By.id("//button[@id = 'Click'] "));
    }
    public static void main(String[] args) throws InterruptedException {

        Frames fr = new Frames();
        fr.frameClick();

    }
}
