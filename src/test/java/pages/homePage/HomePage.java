package pages.homePage;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.By;
import org.testng.Assert;
import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sharedData.TestConstants;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Elements on the page:
    @FindBy(xpath = "//strong[contains(text(),'XYZ Bank')]")
    private WebElement header;
    @FindBy(css = "button[class='btn home']")
    private WebElement headerHomeButton;
    @FindBy(xpath = "//button[contains(text(),'Customer')]")
    private WebElement customerLoginButton;
    @FindBy(xpath = "//button[contains(text(),'Bank Manager')]")
    private WebElement bankManagerLoginButton;

    // Methods relating to the page elements:
    public void checkHomePageButtonsFunctionality(){
        checkHomeButtonFunctionality();
        checkCustomerLoginButtonFunctionality();
        clickHomeButton();
        checkBankManagerButtonFunctionality();
        clickHomeButton();
    }
    public void validateVisibilityOfHomePageElements() {
        pageMethods.validatePageTitle("XYZ Bank");

        elementsMethods.validateElementVisible(header);
        elementsMethods.validateElementMessage(header, "XYZ Bank");
        LoggerUtility.info("The header is visible on the Home Page.");

        elementsMethods.validateElementVisible(headerHomeButton);
        elementsMethods.validateElementMessage(headerHomeButton, "Home");
        LoggerUtility.info("The header Home button is visible on the Home Page.");

        elementsMethods.validateElementVisible(customerLoginButton);
        elementsMethods.validateElementMessage(customerLoginButton, "Customer Login");
        LoggerUtility.info("The Customer Login button is visible on the Home Page.");

        elementsMethods.validateElementVisible(bankManagerLoginButton);
        elementsMethods.validateElementMessage(bankManagerLoginButton, "Bank Manager Login");
        LoggerUtility.info("The Bank Manager Login button is visible on the Home Page.");
    }
    public void clickHomeButton() {
        elementsMethods.clickElement(headerHomeButton);
        LoggerUtility.info("The user clicks the Home button from the header to navigate to the Home Page.");
    }

    public void clickCustomerLoginButton() {
        elementsMethods.clickElement(customerLoginButton);
        LoggerUtility.info("The user clicks the Customer Login button from the Home Page.");
    }

    public void clickBankManagerLogin() {
        elementsMethods.clickJSElement(bankManagerLoginButton);
        LoggerUtility.info("The user clicks the Bank Manager Login button from the Home Page.");
    }

    private void checkHomeButtonFunctionality(){
       clickHomeButton();
       Assert.assertEquals(pageMethods.getPageURL(), TestConstants.homePageURL);
       LoggerUtility.info("The page URL doesn't change, the user is still on the Home Page.");
    }

    private void checkCustomerLoginButtonFunctionality(){
        clickCustomerLoginButton();
        WebElement yourNameFieldOnCostomerLoginPage = driver.findElement(By.id("userSelect"));
        elementsMethods.waitVisibleElement(yourNameFieldOnCostomerLoginPage, 10);
        LoggerUtility.info("The user successfully navigates to the Customer Login page; the Your Name field is visible.");
        String currentURL = pageMethods.getPageURL();
        Assert.assertEquals(currentURL, TestConstants.customerLoginPageURL);
        LoggerUtility.info("The Customer Login page URL is correct: " + currentURL);

    }

    private void checkBankManagerButtonFunctionality(){
        clickBankManagerLogin();
        WebElement addCustomerButtonOnBankManagerPage = driver.findElement(By.cssSelector("button[ng-class='btnClass1']"));
        elementsMethods.waitVisibleElement(addCustomerButtonOnBankManagerPage, 10);
        LoggerUtility.info("The user successfully navigates to the Bank Manager Login page; the Add Customer button is visible.");
        String currentURL = pageMethods.getPageURL();
        Assert.assertEquals(currentURL, TestConstants.bankManagerPageURL);
        LoggerUtility.info("The Bank Manager Login page URL is correct: " + currentURL);
    }
}
