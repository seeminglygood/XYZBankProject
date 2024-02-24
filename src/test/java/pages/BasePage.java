package pages;
import helperMethods.AlertsMethods;
import helperMethods.ElementsMethods;
import helperMethods.PageMethods;
import helperMethods.UtilMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import sharedData.TestConstants;

public class BasePage {
    public WebDriver driver;
    public ElementsMethods elementsMethods;
    public PageMethods pageMethods;
    public AlertsMethods alertsMethods;
    public UtilMethods utilMethods;
    public TestConstants testConstants;


    public BasePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);

        testConstants =  new TestConstants(driver);

        elementsMethods = new ElementsMethods(driver);
        pageMethods = new PageMethods(driver);
        alertsMethods = new AlertsMethods(driver);
        utilMethods = new UtilMethods(driver);


    }
}
