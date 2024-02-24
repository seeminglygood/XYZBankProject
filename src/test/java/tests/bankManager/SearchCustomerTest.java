package tests.bankManager;

import org.testng.annotations.Test;
import pages.bankManagerLogin.BankManagerPage;
import pages.homePage.HomePage;
import sharedData.Hooks;

public class SearchCustomerTest extends Hooks {

    @Test
    public void testMethod(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickBankManagerLogin();

        BankManagerPage bankManagerPage = new BankManagerPage(getDriver());
        bankManagerPage.clickCustomers();
        bankManagerPage.searchCustomer();
    }
}
