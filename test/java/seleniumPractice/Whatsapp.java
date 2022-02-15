package seleniumPractice;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;





public class Whatsapp {
    public void changeBio() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://web.whatsapp.com/");

        //Wait for all the elements to be loaded
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(30000);

        //Change your DP & types of dynamic xpath
        driver.findElement(By.xpath("//span[@data-icon = 'menu']")).click();
        //driver.findElement(By.xpath("//li [contains(@id , 'abc')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Settings')]")).click();
        driver.findElement(By.xpath("//span[contains(text(),'I believe me')]")).click();
        driver.findElement(By.xpath("//button[@title = 'Click to edit About']")).click();
        driver.findElement(By.xpath("//div[text()='I believe me']")).clear();
        driver.findElement(By.xpath("//div[@data-tab = '10']")).sendKeys("I always believe myself");
        driver.findElement(By.xpath("//span[@data-icon = 'checkmark']")).click();
        //driver.findElement(By.xpath("//li [starts-with(@id , 'test_')]")).click();
        //driver.findElement(By.xpath("li [ends-with(@id , 'jut')]")).click();
    }
    public static void main(String[] args) throws InterruptedException {
        Whatsapp wp = new Whatsapp();
        wp.changeBio();
    }
}
