package tests.bankManager;

import objectData.AddCustomerObject;
import org.testng.annotations.Test;
import pages.bankManagerLogin.BankManagerPage;
import pages.homePage.HomePage;
import sharedData.Hooks;

public class OpenAccountTest extends Hooks {

    @Test
    public void testMethod(){
        AddCustomerObject addCustomerObject = new AddCustomerObject(testData);
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBankManagerLogin();

        BankManagerPage bankManagerPage = new BankManagerPage(getDriver());
        bankManagerPage.openAccount();
    }
}
