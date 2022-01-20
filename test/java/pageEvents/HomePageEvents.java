package pageEvents;

import Utils.ElementFetch;
import org.testng.Assert;
import pageObjects.HomePageElements;
import pageObjects.LoginPageElements;

public class HomePageEvents {

    public void clickOnSignInButton(){
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getElement("classname" , HomePageElements.searchbutton).sendKeys("Macbook");
    }
}
