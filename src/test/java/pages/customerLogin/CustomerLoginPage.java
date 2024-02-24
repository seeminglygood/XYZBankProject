package pages.customerLogin;

import loggerUtility.LoggerUtility;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import sharedData.TestConstants;

import java.util.List;

public class CustomerLoginPage extends BasePage {
    public CustomerLoginPage(WebDriver driver) {
        super(driver);
    }

    //Elements on the page:
    @FindBy(id = "userSelect")
    private WebElement yourNameSelector;

    @FindBy(css = "button[type='submit']")
    private WebElement loginButton;

    @FindBy(css = "button[class='btn logout']")
    private WebElement logoutButton;

    // Methods relating to the page elements:

    public void loginAndLogoutCustomer() {
        selectCustomer(1);

        clickLogin();
        elementsMethods.waitVisibleElement(logoutButton, 10);
        LoggerUtility.info("The customer login was successful, the Customer Account page is displayed.");
        String accountPageURL = pageMethods.getPageURL();
        Assert.assertEquals(accountPageURL, TestConstants.loggedInCustomerPageURL);
        LoggerUtility.info("The Customer Account page URL is correct: " + accountPageURL);

        clickLogout();
        elementsMethods.waitVisibleElement(yourNameSelector, 10);
        String logoutURL = pageMethods.getPageURL();
        Assert.assertEquals(logoutURL, TestConstants.customerLoginPageURL);
        LoggerUtility.info("The customer logout was successful, the Customer Login page is once more displayed.");
    }

    private void selectCustomer(Integer customerNo){
        Select select = new Select(yourNameSelector);
        List<WebElement> selectOptions = select.getOptions();
        elementsMethods.clickElement(selectOptions.get(customerNo));
        LoggerUtility.info("The user selects a customer from the dropdown selector; customer position in list is: " + customerNo);
    }
    private void clickLogin(){
        elementsMethods.clickJSElement(loginButton);
        LoggerUtility.info("The user clicks on the Login button.");
    }
    private void clickLogout(){
        elementsMethods.clickJSElement(logoutButton);
        LoggerUtility.info("The user clicks on the Logout button.");
    }
}
