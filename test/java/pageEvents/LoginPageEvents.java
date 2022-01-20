package pageEvents;

import Utils.ElementFetch;
import org.testng.Assert;
import pageObjects.LoginPageElements;

public class LoginPageEvents {
    public void loginPageOpenorNot() {
        ElementFetch elementFetch = new ElementFetch();
        Assert.assertTrue(elementFetch.getListWebElements("xpath", LoginPageElements.loginbutton).size() > 0, "Login page didn't open");

    }

    public void enterEmailId() {
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getElement("id" , LoginPageElements.emailaddress).sendKeys("abc@gmail.com");

    }

}
