package sharedData.browser;


import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.time.Duration;
import java.util.HashMap;

public class SafariBrowserService extends BaseBrowserService implements BrowserService {

    private WebDriver driver;

    @Override
    public void openBrowser(Boolean cicd) {
        SafariOptions safariOptions = (SafariOptions) prepareBrowserOptions(cicd);
        driver = new SafariDriver(safariOptions);
        driver.get(getBrowserOptions().get("url"));
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        LoggerUtility.info("The browser was opened, XYZ Bank Home page was loaded.");
    }

    @Override
    public void closeBrowser() {
        driver.quit();
        LoggerUtility.info("The browser was closed successfully.");
    }

    @Override
    public Object prepareBrowserOptions(Boolean cicd) {
        HashMap<String, String> browserTestData = getBrowserOptions();
        SafariOptions safariOptions = new SafariOptions();

        if (cicd) {
            safariOptions.setCapability("safari:useSimulator", true);
            safariOptions.setCapability("safari:useAutomationExtension", false);
            safariOptions.setCapability("safari:headless", true);
        }

        if (cicd && browserTestData.get("headless") != null) {
            safariOptions.setCapability("safari:headless", Boolean.parseBoolean(browserTestData.get("headless")));
        }

        safariOptions.setCapability("safari:gpusupport", browserTestData.get("gpu"));
        safariOptions.setCapability("safari:diagnose", browserTestData.get("infobars"));
        safariOptions.setCapability("safari:disabledSandbox", browserTestData.get("sandbox"));
        safariOptions.setCapability("safari:resolution", browserTestData.get("resolution"));

        return safariOptions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
