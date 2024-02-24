package sharedData;
import loggerUtility.LoggerUtility;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import sharedData.browser.BrowserFactory;

import java.time.Duration;

// in aceasta clasa salvezi informatii reusable legate de browser logic
public class SharedData {
    private WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }

    public void setup(){

        driver= new BrowserFactory().getBrowserDriver();

        JavascriptExecutor JS = (JavascriptExecutor) driver;
        JS.executeScript("window.scrollBy(0,450)", "");
    }
    public void clear(){
        driver.quit();
        LoggerUtility.info("The browser was closed.");
    }
}
