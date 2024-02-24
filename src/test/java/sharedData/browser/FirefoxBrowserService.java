package sharedData.browser;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;

public class FirefoxBrowserService extends BaseBrowserService implements BrowserService {
    private WebDriver driver;

    @Override
    public void openBrowser(Boolean cicd) {
        FirefoxOptions firefoxOptions = (FirefoxOptions) prepareBrowserOptions(cicd);
        driver = new FirefoxDriver(firefoxOptions);
        driver.get(getBrowserOptions().get("url"));
        //driver.manage().window().maximize();

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
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (cicd) {
            firefoxOptions.addArguments("--headless");
        }

        if (!browserTestData.get("headless").isEmpty()) {
            firefoxOptions.addArguments(browserTestData.get("headless"));
        }

        firefoxOptions.addArguments(browserTestData.get("gpu"));
        firefoxOptions.addArguments(browserTestData.get("infobars"));
        firefoxOptions.addArguments(browserTestData.get("sandbox"));
        firefoxOptions.addArguments(browserTestData.get("resolution"));
        return firefoxOptions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

}
