package test.java;

import Utils.Utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class BaseTest {

    public static WebDriver driver;
    public ExtentReports extent;
    public ExtentSparkReporter spark;
    public ExtentTest logger;


    @BeforeTest
    public void beforeTestMethod() {
        extent = new ExtentReports();
        spark = new ExtentSparkReporter("myReport.html");
        extent.attachReporter(spark);

    }

    @BeforeMethod
    @Parameters(value = {"browsername"})
    public void beforeMethodMethod(String browserName) {
        logger = extent.createTest("My first test automation framework");
        setupDriver(browserName);
        driver.manage().window().maximize();
        driver.get(Utils.url);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case" + methodName + "passed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.GREEN);
            logger.log(Status.PASS, m);
        } else if (result.getStatus() == ITestResult.FAILURE) {
            String methodName = result.getMethod().getMethodName();
            String logtext = "Test case" + methodName + "failed";
            Markup m = MarkupHelper.createLabel(logtext, ExtentColor.RED);
            logger.log(Status.FAIL, m);
        }
        driver.quit();
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }

    public void setupDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C://Users//DIS//Downloads//chromedriver_win32 (1)//chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C://Users//DIS//Downloads//geckodriver-v0.30.0-win64//chromedriver.exe");
            driver = new FirefoxDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", "C://Users//DIS//Downloads//chromedriver_win32 (1)//chromedriver.exe");
            driver = new ChromeDriver();
        }
    }

}
