package helperMethods;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsMethods {
    private WebDriver driver;

    public AlertsMethods(WebDriver driver) {
        this.driver = driver;
    }

    private void waitForAlert(Integer waitTime) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void acceptAlert() {
        waitForAlert(10);
        driver.switchTo().alert().accept();
    }

    public String getAlertText() {
        waitForAlert(10);
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.dismiss();
        return alertText;
    }
}
