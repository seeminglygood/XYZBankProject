package sharedData;

import org.openqa.selenium.WebDriver;

public class TestConstants {
    private WebDriver driver;

    public TestConstants(WebDriver driver) {
        this.driver = driver;
    }

    // Page URLs:
    public static final String homePageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    public static final String customerLoginPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/customer";
    public static final String loggedInCustomerPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/account";
    public static final String loggedInCustomerTransactionsPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/listTx";
    public static final String bankManagerPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager";
    public static final String bankManagerAddCustomerPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust";
    public static final String bankManagerOpenAccountPageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/openAccount";
    public static final String bankManagerCustomerspageURL = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/list";
}
