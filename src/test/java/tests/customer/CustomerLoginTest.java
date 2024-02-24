package tests.customer;

import pages.customerLogin.CustomerLoginPage;
import pages.homePage.HomePage;
import sharedData.Hooks;
import org.testng.annotations.Test;

public class CustomerLoginTest extends Hooks {

    @Test
    public void testMethod(){
        HomePage homePage = new HomePage(getDriver());
        homePage.clickCustomerLoginButton();

        CustomerLoginPage customerLoginPage = new CustomerLoginPage(getDriver());
        customerLoginPage.loginAndLogoutCustomer();
    }
}
