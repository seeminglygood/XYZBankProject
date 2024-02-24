package helperMethods;

import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;

public class UtilMethods {

    private WebDriver driver;

    public UtilMethods(WebDriver driver) {
        this.driver = driver;
    }

    public String extractStringAfterSpecificSymbol(String inputString, String specificSymbol) {
        String[] parts = inputString.split(specificSymbol, 2);
        // Check if there's at least one part after the split
        if (parts.length > 1) {
            // Trim the second part and return
            return parts[1].trim();
        }
        // Return null if the specific symbol is not found or if the second part is empty
        return null;
        // add LOGGER MESSAGE HERE
    }
}
