package sharedData.browser;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class BrowserFactory {

    // This method reads some system variable values from the pom.xml file
    // Based on these values it will be decided on which environment the automated tests will be run
    public WebDriver getBrowserDriver() {
        Boolean cicd = Boolean.parseBoolean(System.getProperty("cicd"));
        String browser = null;

        if (cicd) {
            // if running the test/s remotely, then read the browser type value from pom.xml
            browser = System.getProperty("browser");
        } else {
            // if running the test/s locally, then read the browser type value from BrowserData.properties
            HashMap<String, String> browserTestData = new BaseBrowserService().getBrowserOptions();
            browser = browserTestData.get("browser");
        }

        // Creational design pattern > Factory Method applied here
        // - provides an interface for creating objects in a superclass, but allows subclasses to alter the
        // type of objects that will be created.
        // - encapsulates the creation logic of browser service objects based on a given browser type
        switch (browser) {
            case "chrome":
                ChromeBrowserService chromeBrowserService = new ChromeBrowserService();
                chromeBrowserService.openBrowser(cicd);
                return chromeBrowserService.getDriver();
            case "firefox":
                FirefoxBrowserService firefoxBrowserService = new FirefoxBrowserService();
                firefoxBrowserService.openBrowser(cicd);
                return firefoxBrowserService.getDriver();
            case "safari":
                SafariBrowserService safariBrowserService = new SafariBrowserService();
                safariBrowserService.openBrowser(cicd);
                return safariBrowserService.getDriver();
            // Based on the value of 'browser', an instance of a specific browser service is created:
            // (ChromeBrowserService,FirefoxBrowserService, or SafariBrowserService).
            //Each browser service is responsible for tasks related to its respective browser, such
            // as opening the browser and getting its driver.
        }
        return null;
    }
}
