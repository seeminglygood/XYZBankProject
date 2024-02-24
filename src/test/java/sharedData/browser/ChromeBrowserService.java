package sharedData.browser;

import loggerUtility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;

public class ChromeBrowserService extends BaseBrowserService implements BrowserService{

    private WebDriver driver;
    @Override
    public void openBrowser(Boolean cicd) {
        ChromeOptions chromeOptions = (ChromeOptions) prepareBrowserOptions(cicd);
        driver = new ChromeDriver(chromeOptions);
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
        ChromeOptions chromeOptions = new ChromeOptions();

        if (cicd){
            chromeOptions.addArguments("--headless");
        }

        if (!browserTestData.get("headless").isEmpty()){
            chromeOptions.addArguments(browserTestData.get("headless"));
        }

        chromeOptions.addArguments(browserTestData.get("gpu"));
        chromeOptions.addArguments(browserTestData.get("infobars"));
        chromeOptions.addArguments(browserTestData.get("sandbox"));
        chromeOptions.addArguments(browserTestData.get("resolution"));
        return chromeOptions;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
