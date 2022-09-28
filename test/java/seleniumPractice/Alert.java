package seleniumPractice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Alert {
    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.leafground.com/");
        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement element = driver.findElement(By.xpath("//img[@alt = 'contextClick']"));


        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", element);

        element.click();
        driver.findElement(By.xpath("//input[@type = 'file']")).sendKeys("C://Users//DIS//Documents//Youtube//Thumbnails");

        /*org.openqa.selenium.Alert alert = driver.switchTo().alert();
        System.out.println(alert.getText());
        String string = alert.getText();

        if (string.equals(" I am an alert box! ")){
            System.out.println("Correct alert message");
        }else {
            System.out.println("Incorrect alert message");
        }
        alert.accept();
        driver.close();
        */
    }
}
