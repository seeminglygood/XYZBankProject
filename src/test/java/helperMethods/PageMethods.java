package helperMethods;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class PageMethods {
    private WebDriver driver;

    public PageMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToSpecificWindow(Integer windowNumber) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles()); // windows list
        driver.switchTo().window(windows.get(windowNumber));
    }

    public void closeCurrentWindow() {
        driver.close();
    }

    public void validateURL(String expectedURL) {
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
    }

    public void validatePageTitle(String expectedPageTitle) {
        Assert.assertEquals(driver.getTitle(), expectedPageTitle);
        LoggerUtility.info("The Page title is visible and correct:" + expectedPageTitle);
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public void navigateForward() {
        driver.navigate().forward();
    }

    public void navigateBack() {
        driver.navigate().back();
    }

    public void getPageSource() {
        String pageSource = driver.getPageSource();
        System.out.println("Page Source: " + pageSource);
    }

    public void navigateToURL(String url) {
        driver.get(url);
    }

    public String getPageURL(){

       String pageURL =  driver.getCurrentUrl();
        return pageURL;
    }

}
