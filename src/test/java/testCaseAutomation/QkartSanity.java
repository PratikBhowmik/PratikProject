package testCaseAutomation;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class QkartSanity {

    public static String lastGeneratedUserName;

    public static WebDriver createDriver() throws MalformedURLException {
        // Launch Browser using Zalenium
        //final DesiredCapabilities capabilities = new DesiredCapabilities();
        //capabilities.setBrowserName(BrowserType.CHROME);
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static void logStatus(String type, String message, String status) {
        System.out.println(String.format("%s |  %s  |  %s | %s", String.valueOf(java.time.LocalDateTime.now()), type,
                message, status));
    }

    public static void takeScreenshot(WebDriver driver, String screenshotType, String description) {
        // TODO: CRIO_TASK_MODULE_SYNCHRONISATION - Implement method using below steps
        /*
         * 1. Check if the folder "/screenshots" exists, create if it doesn't
         * 2. Generate a unique string using the timestamp
         * 3. Capture screenshot
         * 4. Save the screenshot inside the "/screenshots" folder using the following
         * naming convention: screenshot_<Timestamp>_<ScreenshotType>_<Description>.png
         * eg: screenshot_2022-03-05T06:59:46.015489_StartTestcase_Testcase01.png
         */
    }

    /*
     * Testcase01: Verify the functionality of Login button on the Home page
     */
    public static Boolean TestCase01(WebDriver driver) throws InterruptedException {
        Boolean status;
        logStatus("Start TestCase", "Test Case 1: Verify User Registration", "DONE");
        // Visit the Registration page and register a new user
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("End TestCase", "Test Case 1: Verify user Registration : ", status ? "PASS" : "FAIL");
            // Return False as the test case Fails
            return false;
        } else {
            logStatus("TestCase 1", "Test Case Pass. User Registration Pass", "PASS");
        }
        // Save the last generated username
        lastGeneratedUserName = registration.lastGeneratedUsername;
        // Visit the login page and login with the previuosly registered user
        Login login = new Login(driver);
        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        logStatus("Test Step", "User Perform Login: ", status ? "PASS" : "FAIL");
        if (!status) {
            logStatus("End TestCase", "Test Case 1: Verify user Registration : ", status ? "PASS" : "FAIL");
            return false;
        }
        // Visit the home page and log out the logged in user
        Home home = new Home(driver);
        status = home.PerformLogout();
        logStatus("End TestCase", "Test Case 1: Verify user Registration : ", status ? "PASS" : "FAIL");
        return status;
    }

    /*
     * Verify that an existing user is not allowed to re-register on QKart
     */
    public static Boolean TestCase02(WebDriver driver) throws InterruptedException {
        Boolean status;
        logStatus("Start Testcase", "Test Case 2: Verify User Registration with an existing username ", "DONE");
        // Visit the Registration page and register a new user
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        logStatus("Test Step", "User Registration : ", status ? "PASS" : "FAIL");
        if (!status) {
            logStatus("End TestCase", "Test Case 2: Verify user Registration : ", status ? "PASS" : "FAIL");
            return false;
        }
        // Save the last generated username
        lastGeneratedUserName = registration.lastGeneratedUsername;
        // Visit the Registration page and try to register using the previously
        // registered user's credentials
        registration.navigateToRegisterPage();
        status = registration.registerUser(lastGeneratedUserName, "abc@123", false);
        // If status is true, then registration succeeded, else registration has
        // failed. In this case registration failure means Success
        logStatus("End TestCase", "Test Case 2: Verify user Registration : ", status ? "FAIL" : "PASS");
        return !status;
    }

    /*
     * Verify the functinality of the search text box
     */
    public static Boolean TestCase03(WebDriver driver) throws InterruptedException {
        logStatus("TestCase 3", "Start test case : Verify functionality of search box ", "DONE");
        boolean status;
        // Visit the home page
        Home homePage = new Home(driver);
        homePage.navigateToHome();

        // SLEEP_STMT_01 : Wait for Page to Load
        // Search for the "yonex" product
        status = homePage.searchForProduct("yonex");
        if (!status) {
            logStatus("TestCase 3", "Test Case Failure. Unable to search for given product", "FAIL");
            return false;
        }

        // Fetch the search results
        List<WebElement> searchResults = homePage.getSearchResults();

        // Verify the search results are available
        if (searchResults.size() == 0) {
            logStatus("TestCase 3", "Test Case Failure. There were no results for the given search string", "FAIL");
            return false;
        }

        for (WebElement webElement : searchResults) {
            // Create a SearchResult object from the parent element
            SearchResult resultelement = new SearchResult(webElement);

            // Verify that all results contain the searched text
            String elementText = resultelement.getTitleofResult();
            if (!elementText.toUpperCase().contains("YONEX")) {
                logStatus("TestCase 3", "Test Case Failure. Test Results contains un-expected values: " + elementText,
                        "FAIL");
                return false;
            }
        }

        logStatus("Step Success", "Successfully validated the search results ", "PASS");
        // SLEEP_STMT_02
        //Thread.sleep(2000);
        // Search for product
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        status = homePage.searchForProduct("Gesundheit");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("h4")));

        if (!status) {
            logStatus("TestCase 3", "Test Case Failure. Unable to search for given product", "FAIL");
            return false;
        }

        // Verify no search results are found
        searchResults = homePage.getSearchResults();
        if (searchResults.size() == 0) {
            if (homePage.isNoResultFound()) {
                logStatus("Step Success", "Successfully validated that no products found message is displayed", "PASS");
            }
            logStatus("TestCase 3", "Test Case PASS. Verified that no search results were found for the given text",
                    "PASS");
        } else {
            logStatus("TestCase 3", "Test Case Fail. Expected: no results , actual: Results were available", "FAIL");
            return false;
        }

        return true;
    }

    /*
     * Verify the presence of size chart and check if the size chart content is as
     * expected
     */
    public static Boolean TestCase04(WebDriver driver) throws InterruptedException {
        logStatus("TestCase 4", "Start test case : Verify the presence of size Chart", "DONE");
        boolean status = false;

        // Visit home page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        // SLEEP_STMT_03 : Wait for page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
        //Thread.sleep(5000);

        // Search for product and get card content element of search results
        status = homePage.searchForProduct("Running Shoes");
        List<WebElement> searchResults = homePage.getSearchResults();
        // Create expected values
        List<String> expectedTableHeaders = Arrays.asList("Size", "UK/INDIA", "EU", "HEEL TO TOE");
        List<List<String>> expectedTableBody = Arrays.asList(Arrays.asList("6", "6", "40", "9.8"),
                Arrays.asList("7", "7", "41", "10.2"), Arrays.asList("8", "8", "42", "10.6"),
                Arrays.asList("9", "9", "43", "11"), Arrays.asList("10", "10", "44", "11.5"),
                Arrays.asList("11", "11", "45", "12.2"), Arrays.asList("12", "12", "46", "12.6"));
        // Verify size chart presence and content matching for each search result
        for (WebElement webElement : searchResults) {
            SearchResult result = new SearchResult(webElement);

            // Verify if the size chart exists for the search result
            if (result.verifySizeChartExists()) {
                logStatus("Step Success", "Successfully validated presence of Size Chart Link", "PASS");

                // Verify if size dropdown exists
                status = result.verifyExistenceofSizeDropdown(driver);
                logStatus("Step Success", "Validated presence of drop down", status ? "PASS" : "FAIL");

                // Open the size chart
                if (result.openSizechart()) {
                    // Verify if the size chart contents matches the expected values
                    if (result.validateSizeChartContents(expectedTableHeaders, expectedTableBody, driver)) {
                        logStatus("Step Success", "Successfully validated contents of Size Chart Link", "PASS");
                    } else {
                        logStatus("Step Failure", "Failure while validating contents of Size Chart Link", "FAIL");
                    }

                    // Close the size chart modal
                    status = result.closeSizeChart(driver);

                } else {
                    logStatus("TestCase 4", "Test Case Fail. Failure to open Size Chart", "FAIL");
                    return false;
                }
            } else {
                logStatus("TestCase 4", "Test Case Fail. Size Chart Link does not exist", "FAIL");
                return false;
            }
        }
        logStatus("TestCase 4", "Test Case PASS. Validated Size Chart Details", "PASS");
        return status;
    }

    /*
     * Verify the complete flow of checking out and placing order for products is
     * working correctly
     */
    public static Boolean TestCase05(WebDriver driver) throws InterruptedException {
        Boolean status;
        logStatus("Start TestCase", "Test Case 5: Verify Happy Flow of buying products", "DONE");

        // Go to the Register page
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();

        // Register a new user
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("TestCase 5", "Test Case Failure. Happy Flow Test Failed", "FAIL");
        }
        // Save the username of the newly registered user
        lastGeneratedUserName = registration.lastGeneratedUsername;
        // Go to the login page
        Login login = new Login(driver);
        login.navigateToLoginPage();
        // Login with the newly registered user's credentials
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        if (!status) {
            logStatus("Step Failure", "User Perform Login Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase", "Test Case 5: Happy Flow Test Failed : ", status ? "PASS" : "FAIL");
        }
        // Go to the home page
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        // Find required products by searching and add them to the user's cart
        status = homePage.searchForProduct("Yonex");
        homePage.addProductToCart("YONEX Smash Badminton Racquet");
        status = homePage.searchForProduct("Tan");
        homePage.addProductToCart("Tan Leatherette Weekender Duffle");
        // Click on the checkout button
        homePage.clickCheckout();
        // Add a new address on the Checkout page and select it
        Checkout checkoutPage = new Checkout(driver);
        checkoutPage.addNewAddress("Addr line 1 addr Line 2 addr line 3");
        checkoutPage.selectAddress("Addr line 1 addr Line 2 addr line 3");
        // Place the order
        checkoutPage.placeOrder();
        // SLEEP_STMT_04: Wait for place order to succeed and navigate to Thanks page
        //Thread.sleep(3000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.urlContains("/thanks"));
        // Check if placing order redirected to the Thansk page
        status = driver.getCurrentUrl().endsWith("/thanks");
        // Go to the home page
        homePage.navigateToHome();
        //Thread.sleep(3000);
        // Log out the user
        homePage.PerformLogout();
        logStatus("End TestCase", "Test Case 5: Happy Flow Test Completed : ", status ? "PASS" : "FAIL");
        return status;
    }
    /*
     * Verify the quantity of items in cart can be updated
     */
    public static Boolean TestCase06(WebDriver driver) throws InterruptedException {
        Boolean status;
        WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(30));
        logStatus("Start TestCase", "Test Case 6: Verify that cart can be edited", "DONE");
        Home homePage = new Home(driver);
        Register registration = new Register(driver);
        Login login = new Login(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("TestCase 6", "Test case fail happy flow test failed ", "FAIL");
        }
        lastGeneratedUserName = registration.lastGeneratedUsername;
        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        if (!status) {
            logStatus("Step Failure", "User Perform Login Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase", "Test Case 6: Happy Flow Test Failed : ", status ? "PASS" : "FAIL");
        }
        homePage.navigateToHome();
        homePage.addProductToCart("Xtend Smart Watch");
        Thread.sleep(3000);
        homePage.addProductToCart("Yarine Floor Lamp");
        // update watch quantity to 2
        homePage.changeProductQuantityinCart("Xtend Smart Watch", 2);
        // update table lamp quantity to 0
        homePage.changeProductQuantityinCart("Yarine Floor Lamp", 0);
        // update watch quantity again to 1
        homePage.changeProductQuantityinCart("Xtend Smart Watch", 1);
        homePage.clickCheckout();
        Checkout checkoutPage = new Checkout(driver);
        checkoutPage.addNewAddress("Addr line 1 addr Line 2 addr line 3");
        checkoutPage.selectAddress("Addr line 1 addr Line 2 addr line 3");
        checkoutPage.placeOrder();
        Thread.sleep(3000);
        status = driver.getCurrentUrl().endsWith("/thanks");
        homePage.navigateToHome();
        Thread.sleep(3000);
        homePage.PerformLogout();
        logStatus("End TestCase", "Test Case 6: Verify that cart can be edited: ", status ? "PASS" : "FAIL");
        return status;
    }
    /*
     * Verify that the cart contents are persisted after logout
     */
    public static Boolean TestCase07(WebDriver driver) throws InterruptedException {
        Boolean status = false;
        List<String> expectedResult = Arrays.asList("Stylecon 9 Seater RHS Sofa Set",
                "Xtend Smart Watch");

        logStatus("Start TestCase", "Test Case 7: Verify that cart contents are persisted after logout", "DONE");

        Register registration = new Register(driver);
        Login login = new Login(driver);
        Home homePage = new Home(driver);

        // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6
        // TODO: Register a new user

        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("TestCase 7", "Test case fail happy flow test failed ", "FAIL");
        }

        lastGeneratedUserName = registration.lastGeneratedUsername;

        // TODO: Login using the newly created user

        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        if (!status) {
            logStatus("Step Failure", "User Perform Login Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase", "Test Case 7: Happy Flow Test Failed : ",
                    status ? "PASS" : "FAIL");
        }


        homePage.navigateToHome();
        status = homePage.searchForProduct("Stylecon");
        homePage.addProductToCart("Stylecon 9 Seater RHS Sofa Set");

        status = homePage.searchForProduct("Xtend");
        homePage.addProductToCart("Xtend Smart Watch");

        homePage.PerformLogout();

        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        Thread.sleep(3000);

        status = homePage.verifyCartContents(expectedResult);

        logStatus("End TestCase", "Test Case 7: Verify that cart contents are persisted after logout: ",
                status ? "PASS" : "FAIL");

        homePage.PerformLogout();
        return status;
    }

    public static Boolean TestCase08(WebDriver driver) throws InterruptedException {
        Boolean status;
        logStatus("Start TestCase",
                "Test Case 8: Verify that insufficient balance error is thrown when the wallet balance is not enough",
                "DONE");

        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("Step Failure", "User Perform Registration Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase",
                    "Test Case 8: Verify that insufficient balance error is thrown when the wallet balance is not enough: ",
                    status ? "PASS" : "FAIL");
            return false;
        }
        lastGeneratedUserName = registration.lastGeneratedUsername;

        Login login = new Login(driver);
        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        if (!status) {
            logStatus("Step Failure", "User Perform Login Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase",
                    "Test Case 8: Verify that insufficient balance error is thrown when the wallet balance is not enough: ",
                    status ? "PASS" : "FAIL");
            return false;
        }

        Home homePage = new Home(driver);
        homePage.navigateToHome();
        status = homePage.searchForProduct("Stylecon");
        homePage.addProductToCart("Stylecon 9 Seater RHS Sofa Set");
        Thread.sleep(3000);

        homePage.changeProductQuantityinCart("Stylecon 9 Seater RHS Sofa Set", 10);

        homePage.clickCheckout();

        Checkout checkoutPage = new Checkout(driver);
        checkoutPage.addNewAddress("Addr line 1 addr Line 2 addr line 3");
        checkoutPage.selectAddress("Addr line 1 addr Line 2 addr line 3");

        checkoutPage.placeOrder();
        Thread.sleep(3000);

        status = checkoutPage.verifyInsufficientBalanceMessage();

        logStatus("End TestCase",
                "Test Case 8: Verify that insufficient balance error is thrown when the wallet balance is not enough: ",
                status ? "PASS" : "FAIL");

        return status;
    }

    public static Boolean TestCase09(WebDriver driver) throws InterruptedException {
        Boolean status = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logStatus("Start TestCase", "Test Case 9: Test case for multitab scenario ", "DONE");
        List<String> expectedResult = Arrays.asList("YONEX Smash Badminton Raquet");
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        status = registration.registerUser("testUser", "abc@123", true);
        if (!status) {
            logStatus("Step Failure", "User Perform Registration Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase",
                    "Test Case 9: Verify that insufficient balance error is thrown when the wallet balance is not enough: ",
                    status ? "PASS" : "FAIL");
            return false;
        }
        lastGeneratedUserName = registration.lastGeneratedUsername;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        status = login.PerformLogin(lastGeneratedUserName, "abc@123");
        if (!status) {
            logStatus("Step Failure", "User Perform Login Failed", status ? "PASS" : "FAIL");
            logStatus("End TestCase",
                    "Test Case 9: Verify that insufficient balance error is thrown when the wallet balance is not enough: ",
                    status ? "PASS" : "FAIL");
            return false;
        }
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        String parent_window = driver.getWindowHandle();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("button")));
        homePage.searchForProduct("Yonex");
        Thread.sleep(3000);
        homePage.addProductToCart("YONEX Smash Badminton Racquet");
        driver.switchTo().newWindow(WindowType.TAB);
        homePage.navigateToHome();
        status = homePage.verifyCartContents(expectedResult);
        driver.close();
        if (!status) {
            logStatus("Step Failure", "In new tab product in cart is not found",
                    status ? "PASS" : "FAIL");
            logStatus("End TestCase", "Test Case 9:Test case for multitab scenario: ",
                    status ? "PASS" : "FAIL");
            return false;
        }
        driver.switchTo().window(parent_window);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("button")));
        logStatus("End test case", "Test case 9 : Test case for multitab scenario",
                status ? "PASS" : "FAIL");
        return status;
    }

    public static Boolean TestCase10(WebDriver driver) throws InterruptedException {
        Boolean status = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        logStatus("Start TestCase", "Test Case 10: Verify privacy policy and terms and conditions ",
                "DONE");
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        js.executeScript("scrollTo(0,document.body.scrollHeight)");
        WebElement privacyPolicy = driver.findElement(By.linkText("Privacy policy"));
        privacyPolicy.click();
        if (homePage.url.equals(driver.getCurrentUrl())) {
            System.out.println("url validated");
        }
        String parentWindow = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        for (String handle : s) {
            if (!handle.equalsIgnoreCase(parentWindow)) {
                driver.switchTo().window(handle);
                String expectedcontentOfPrivacyPolicy = driver.findElement(By.tagName("h2")).getText();
                String actualContentOfPrivacyPolicy = driver.findElement(By.tagName("h2")).getText();
                if (expectedcontentOfPrivacyPolicy.equals(actualContentOfPrivacyPolicy)) {
                    status = true;
                }
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
        WebElement termsOfService = driver.findElement(By.linkText("Terms of Service"));
        termsOfService.click();
        if (homePage.url.equals(driver.getCurrentUrl())) {
            System.out.println("Url validated");
        }
        String parent_window = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handleTwo : handles) {
            if (!handleTwo.equalsIgnoreCase(parent_window)) {
                driver.switchTo().window(handleTwo);
                String expectedTerms = driver.findElement(By.tagName("h2")).getText();
                String actualTerms = driver.findElement(By.tagName("h2")).getText();
                if (actualTerms.equals(expectedTerms)) {
                    status = true;
                }
                driver.close();
            }
        }
        driver.switchTo().window(parent_window);
        logStatus("End test case", "Verify privacy policy and terms of condition",
                status ? "PASS" : "FAIL");
        return status;
    }

    public static Boolean TestCase11(WebDriver driver) throws InterruptedException {
        Boolean status = false;
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        logStatus("Start TestCase", "Test Case 11: Verify contact us link is working fine", "DONE");
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        registration.registerUser("testUser", "abc@123", true);
        lastGeneratedUserName = registration.lastGeneratedUsername;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        login.PerformLogin(lastGeneratedUserName, "abc@123");
        Home homePage = new Home(driver);
        homePage.navigateToHome();
        js.executeScript("scrollTo(0,document.body.scrollHeight)");
        WebElement contactUs = driver.findElement(By.xpath("//p[text() = 'Contact us']"));
        contactUs.click();
        WebElement nameElement = driver.findElement(By.xpath("//input[@placeholder = 'Name']"));
        nameElement.sendKeys("crio user");
        WebElement emailElement = driver.findElement(By.xpath("//input[@placeholder = 'Email']"));
        emailElement.sendKeys("criouser@gmail.com");
        WebElement messageElement =
                driver.findElement(By.xpath("//input[@placeholder = 'Message']"));
        messageElement.sendKeys("testing the contact us page");
        WebElement contactNowBtn =
                driver.findElement(By.xpath("//div[@class = 'card-block']//following::button"));
        contactNowBtn.click();
        if (wait.until(ExpectedConditions.invisibilityOf(contactNowBtn))) {
            status = true;
        }
        logStatus("End test case 11", "Verify contact us pop up disappears", status ? "PASS" : "FAIL");
        return status;
    }

    public static Boolean TestCase12(WebDriver driver) throws InterruptedException {
        Boolean status = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        logStatus("Start TestCase", "Test Case 12: Verify ads are displayed correctly", "DONE");
        Register registration = new Register(driver);
        registration.navigateToRegisterPage();
        registration.registerUser("testUser", "abc@123", true);
        lastGeneratedUserName = registration.lastGeneratedUsername;
        Login login = new Login(driver);
        login.navigateToLoginPage();
        login.PerformLogin(lastGeneratedUserName, "abc@123");
        Home homePage = new Home(driver);
        Checkout checkout = new Checkout(driver);
        homePage.navigateToHome();
        homePage.searchForProduct("YONEX Smash Badminton Racquet");
        js.executeScript("scrollTo(0,document.body.scrollHeight)");
        homePage.addProductToCart("YONEX Smash Badminton Racquet");
        homePage.clickCheckout();
        checkout.addNewAddress(
                "East chandmari near agartala international school, Agartala, Tripura(W)");
        checkout.selectAddress(
                "East chandmari near agartala international school, Agartala, Tripura(W)");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("button")));
        status = checkout.placeOrder();
        if (!status) {
            System.out.println("Not clicked on place order button");
        }
        List<WebElement> ads = driver.findElements(By.tagName("iframe"));
        if (ads.size() == 3) {
            status = true;
        }
        WebElement adframeOne = driver.findElement(By.xpath("(//iframe[@class = 'iframe'])[1]"));
        WebElement adframeTwo = driver.findElement(By.xpath("(//iframe[@class = 'iframe'])[2]"));
        if (adframeOne.isDisplayed()) {
            driver.switchTo().frame(adframeOne);
            WebElement buttonFrameOne = driver.findElement(By.tagName("button"));
            status = buttonFrameOne.isEnabled();
            driver.switchTo().parentFrame();
        } else {
            System.out.println("button is not validated");
        }
        if (adframeTwo.isDisplayed()) {
            driver.switchTo().frame(adframeTwo);
            WebElement buttonFrameTwo = driver.findElement(By.tagName("button"));
            status = buttonFrameTwo.isEnabled();
            driver.switchTo().parentFrame();
        } else {
            System.out.println("button is not clickable");
        }
        logStatus("End test case", "Test case 12 : Verify ads are working ",
                status ? "PASS" : "FAIL");
        return status;
    }
    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        int totalTests = 0;
        int passedTests = 0;
        Boolean status;
        WebDriver driver = createDriver();
        // Maximize and Implicit Wait for things to initailize
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        try {
            // Execute Test Case 1
            totalTests += 1;
            status = TestCase01(driver);
            if (status) {
                passedTests += 1;
            }
//            //System.out.println("");
//
//            // Execute Test Case 2
            totalTests += 1;
            status = TestCase02(driver);
            if (status) {
                passedTests += 1;
            }

            //System.out.println("");
             //Execute Test Case 3
            totalTests += 1;
            status = TestCase03(driver);
            if (status) {
                passedTests += 1;
            }

             System.out.println("");
             //Execute Test Case 4
            totalTests += 1;
            status = TestCase04(driver);
            if (status) {
                passedTests += 1;
            }

            //System.out.println("");

            //Execute Test Case 5
            totalTests += 1;
            status = TestCase05(driver);
            if (status) {
                passedTests += 1;
            }
////
//            //System.out.println("");
//
            //Execute Test Case 6
            totalTests += 1;
            status = TestCase06(driver);
            if (status) {
                passedTests += 1;
            }

            //System.out.println("");

            //Execute Test Case 7
            totalTests += 1;
            status = TestCase07(driver);
            if (status) {
                passedTests += 1;
            }

            //System.out.println("");

            //Execute Test Case 8
            totalTests += 1;
            status = TestCase08(driver);
            if (status) {
                passedTests += 1;
            }

             System.out.println("");

             //Execute Test Case 9
            totalTests += 1;
            status = TestCase09(driver);
            if (status) {
                passedTests += 1;
            }

            // System.out.println("");

            // Execute Test Case 10
            totalTests += 1;
            status = TestCase10(driver);
            if (status) {
                passedTests += 1;
            }
            // System.out.println("");

            // Execute Test Case 11
             totalTests += 1;
             status = TestCase11(driver);
             if (status) {
             passedTests += 1;
             }
            // System.out.println("");
            // Execute Test Case 12
            totalTests += 1;
            status = TestCase12(driver);
            if (status) {
                passedTests += 1;
            }
            // System.out.println("");
        } catch (Exception e) {
            throw e;
        } finally {
            // quit Chrome Driver
            driver.quit();

            System.out.println(String.format("%s out of %s test cases Passed ", Integer.toString(passedTests),
                    Integer.toString(totalTests)));
        }

    }
}
