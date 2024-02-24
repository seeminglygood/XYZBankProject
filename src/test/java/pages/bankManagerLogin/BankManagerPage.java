package pages.bankManagerLogin;

import loggerUtility.LoggerUtility;
import objectData.AddCustomerObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import pages.BasePage;

import java.util.ArrayList;
import java.util.List;

public class BankManagerPage extends BasePage {
    public BankManagerPage(WebDriver driver) {
        super(driver);
    }

    //Elements on the page:
    @FindBy(css = "button[ng-class='btnClass1']")
    private WebElement addCustomerButton;
    @FindBy(css = "input[ng-model='fName']")
    private WebElement firstNameField;
    @FindBy(css = "input[ng-model='lName']")
    private WebElement lastNameField;
    @FindBy(css = "input[ng-model='postCd']")
    private WebElement postCodeField;
    @FindBy(css = "button[ng-class='btnClass2']")
    private WebElement openAccountButton;
    @FindBy(css = "button[ng-class='btnClass3']")
    private WebElement customersButton;
    @FindBy(css = "form[ng-submit='addCustomer()']")
    private WebElement addCustomerForm;
    @FindBy(name = "myForm")
    private WebElement openAccountForm;
    @FindBy(css = "[ng-model='searchCustomer']")
    private WebElement searchCustomerBar;
    @FindBy(css = ".table")
    private WebElement customersTable;
    @FindBy(xpath = "//thead/tr/td")
    private List<WebElement> customersTableHeaderEntries;
    @FindBy(css = "tr.ng-scope")
    private List<WebElement> customersTableEntries;
    @FindBy(css = "button[type='submit']")
    private WebElement submitTypeCTAButton;
    @FindBy(id = "userSelect")
    private WebElement selectCustomer;
    @FindBy(id = "currency")
    private WebElement selectCurrency;
    @FindBy(css = "button[ng-click='deleteCust(cust)']")
    private List<WebElement> deleteButtons;

    // Methods relating to the page elements:
    /////////////////////////////////// Methods for ADD CUSTOMER sub-page ///////////////////////////////////

    public void addCustomer(AddCustomerObject addCustomerObject) {
        clickCustomers();
        Integer initialTableSize = getActualCustomersTableSize();
        LoggerUtility.info("The user checks the current number of rows in the Customers table, which is: " + initialTableSize);
        clickAddCustomer();
        String firstName = addCustomerObject.getFirstNameValue() + System.currentTimeMillis();
        addCustomerFillFirstName(firstName);
        addCustomerFillLastName(addCustomerObject.getLastNameValue());
        addCustomerFillPostCode(addCustomerObject.getPostCodeValue());
        elementsMethods.clickElement(submitTypeCTAButton);
        LoggerUtility.info("The user clicks the Add Customer CTA button under the Add Customer form.");
        alertsMethods.acceptAlert();
        LoggerUtility.info("A confirmation alert is displayed and the user closes it successfully.");
        clickCustomers();
        validateAddCustomer(initialTableSize, addCustomerObject);
    }

    public void validateAddCustomer(Integer initialTableSize, AddCustomerObject addCustomerObject) {
        Integer expectedTableSize = getActualCustomersTableSize();
        Assert.assertTrue(initialTableSize + 1 == expectedTableSize);
        LoggerUtility.info("A new entry has been added to the Customers table and the new number of rows is: " + expectedTableSize);

        List<String> lastCustomersTableEntry = getCustomersTableEntries().get(expectedTableSize - 1);
        Assert.assertTrue(lastCustomersTableEntry.get(0).contains(addCustomerObject.getFirstNameValue()));
        LoggerUtility.info("The last entry in the Customers table contains the first name of the newly added user: " + addCustomerObject.getFirstNameValue());
        Assert.assertTrue(lastCustomersTableEntry.get(1).equals(addCustomerObject.getLastNameValue()));
        LoggerUtility.info("The last entry in the Customers table contains the last name of the newly added user: " + addCustomerObject.getLastNameValue());
        Assert.assertTrue(lastCustomersTableEntry.get(2).equals(addCustomerObject.getPostCodeValue()));
        LoggerUtility.info("The last entry in the Customers table contains the post code of the newly added user: " + addCustomerObject.getPostCodeValue());
        Assert.assertTrue(lastCustomersTableEntry.get(3).isEmpty());
        LoggerUtility.info("There is no Account Number associated with the newly created Customer, as none was yet created.");
    }

    public void clickAddCustomer() {
        elementsMethods.clickElement(addCustomerButton);
        elementsMethods.validateElementVisible(addCustomerForm);
        LoggerUtility.info("The user clicks on the Add Customer button from the Bank Manager page and the Add Customer form appears.");
    }

    private void addCustomerFillFirstName(String firstNameValue) {
        elementsMethods.fillElement(firstNameField, firstNameValue);
        LoggerUtility.info("The user fills in the First Name field with the value: " + firstNameValue);
    }

    private void addCustomerFillLastName(String lastNamevalue) {
        elementsMethods.fillElement(lastNameField, lastNamevalue);
        LoggerUtility.info("The user fills in the Last Name field with the value: " + lastNamevalue);
    }

    private void addCustomerFillPostCode(String postCodeValue) {
        elementsMethods.fillElement(postCodeField, postCodeValue);
        LoggerUtility.info("The user fills in the Post Code field with the value: " + postCodeValue);
    }

    /////////////////////////////////// Methods for OPEN ACCOUNT sub-page ///////////////////////////////////
    public void clickOpenAccount() {
        elementsMethods.clickElement(openAccountButton);
        LoggerUtility.info("The user clicks on the Open Account button from the Bank Manager page.");
        elementsMethods.validateElementVisible(openAccountForm);
        LoggerUtility.info("The Open Account form appears.");
    }

    private void selectCustomerName() {
        String firstVisibleEntry = getCellDataCustomersTable(0, 0) + " " + getCellDataCustomersTable(0, 1);
        clickOpenAccount();
        elementsMethods.fillElement(selectCustomer, firstVisibleEntry);
        LoggerUtility.info("The user fills in the Customer field with the name and surname of an existing customer: " + firstVisibleEntry);

    }

    private void selectCurrency() {
        Select select = new Select(selectCurrency);
        List<WebElement> options = select.getOptions();
        elementsMethods.fillElement(selectCurrency, options.get(1).getText());
        LoggerUtility.info("The user selects the first Currency type in the Currency selector.");
    }

    public void openAccount() {
        clickCustomers();
        String initialAccountNumbers = getCellDataCustomersTable(0, 3);
        selectCustomerName();
        selectCurrency();
        elementsMethods.clickElement(submitTypeCTAButton);
        LoggerUtility.info("The user clicks the Process button at the bottom of the Open Account form.");
        String alertText = alertsMethods.getAlertText();
        LoggerUtility.info("An alert confirmation message is displayed: " + alertText);
        String addedAccountNumber = utilMethods.extractStringAfterSpecificSymbol(alertText, ":");
        clickCustomers();
        String updatedAccountNumbers = getCellDataCustomersTable(0, 3);
        Assert.assertEquals(updatedAccountNumbers, initialAccountNumbers + " " + addedAccountNumber);
        LoggerUtility.info("The Account Number from the confirmation text- " + addedAccountNumber + " -is successfully displayed in the Account Number cell on the Customers Table for the respective customer.");
    }

    /////////////////////////////////// Methods for CUSTOMERS sub-page ///////////////////////////////////
    public void clickCustomers() {
        elementsMethods.clickElement(customersButton);
        LoggerUtility.info("The user clicks on the Customers button from the Bank Manager page.");
        elementsMethods.validateElementVisible(searchCustomerBar);
        elementsMethods.validateElementVisible(customersTable);
        LoggerUtility.info("The Search Customer bar and the Customers Table are both visible.");
    }

    private Integer getActualCustomersTableSize() {
        Integer actualCustomersTableSize = customersTableEntries.size();
        return actualCustomersTableSize;
    }

    private List<List<String>> getCustomersTableEntries() {
        List<List<String>> tableEntries = new ArrayList<>();
        for (WebElement row : customersTableEntries) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            List<String> rowData = new ArrayList<>();
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            tableEntries.add(rowData);
        }
        return tableEntries;
    }

    private String getCellDataCustomersTable(Integer row, Integer column) {
        List<String> rowData = getCustomersTableEntries().get(row);
        String cellData = rowData.get(column);
        return cellData;
    }

    public void searchCustomer() {
        Integer initialCustomersTableEntries = getActualCustomersTableSize();
        LoggerUtility.info("The user checks the number of entries in the Customers table, which is: " + initialCustomersTableEntries);
        String nameOfFirstCustomerInTable = getCellDataCustomersTable(0, 0);
        elementsMethods.fillElement(searchCustomerBar, nameOfFirstCustomerInTable);
        LoggerUtility.info("The user enters in the Search Customer bar the first name of the first customer in the table.");
        Assert.assertNotEquals(getActualCustomersTableSize(), initialCustomersTableEntries);
        Assert.assertEquals(getActualCustomersTableSize(), 1);
        LoggerUtility.info("The user notices that the Customers Table size changed to display only the Customer for which the search was performed.");
        /*works with this initial set of data
        TO DO for later: first search how many customers with this first name exist and adapt the assert accordingly,
        so it can be used when there are more customers with the same first name as well*/
    }

    public void deleteCustomer() {
        Integer initialCustomersTableEntries = getActualCustomersTableSize();
        LoggerUtility.info("The user checks the size of the Customers table, which is: " + initialCustomersTableEntries);
        elementsMethods.clickElement(deleteButtons.get(0));
        LoggerUtility.info("The user successfully deletes the first entry in the Customers table. ");
        Integer updatedCustomertableEntries = getActualCustomersTableSize();
        Assert.assertTrue(updatedCustomertableEntries == (initialCustomersTableEntries - 1));
        LoggerUtility.info("The user re-checks the Customers table size, which reflects that 1 customer was deleted and now is: " + updatedCustomertableEntries);
    }
}
