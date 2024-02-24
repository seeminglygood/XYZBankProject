package pages.customerLogin;

import pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WIPCustomerAccountPage extends BasePage {
    public WIPCustomerAccountPage(WebDriver driver) {
        super(driver);
    }

    //Elements on the page:
    @FindBy(xpath = "//strong[1]/span")
    private WebElement accountHolder;
    @FindBy(id = "accountSelect")
    private WebElement accountSelector;
    @FindBy(css = "strong[class='ng-binding']")
    private WebElement accountData;
    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement transactionsButton;
    @FindBy(css = "button[ng-class='btnClass2']")
    private WebElement depositButton;
    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement withdrawalButton;
    @FindBy(xpath = "//label")
    private WebElement actionFieldLabel;
    @FindBy(css = "input[type='number']")
    private WebElement inputField;
    @FindBy(css = "button[type='submit']")
    private WebElement submitTypeButton;
    @FindBy(css = "span[class='error ng-binding']")
    private WebElement actionMessage;

}
