package crio;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class WhatsappAutomation {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://web.whatsapp.com/");
        driver.manage().window().maximize();
        Thread.sleep(20000);
        driver.findElement(By.xpath("//div[text()='Search or start new chat']")).sendKeys("Dad");
        driver.findElement(By.xpath("//span[text()='\"Hi i am starting to automate a use case on WebWhatsapp Pratik Bhowmik\"']")).click();
        driver.findElement(By.xpath("//div[text()='Type a message']")).sendKeys("Hi this is an automated message");
        driver.findElement(By.xpath("//span[@data-icon = 'send']")).click();
        driver.close();

    }
}
