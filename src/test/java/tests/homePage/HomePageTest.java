package tests.homePage;

import pages.homePage.HomePage;
import sharedData.Hooks;
import org.testng.annotations.Test;

public class HomePageTest extends Hooks {
    @Test
    public void testMethod(){

        HomePage homePage = new HomePage(getDriver());
        homePage.validateVisibilityOfHomePageElements();
        homePage.checkHomePageButtonsFunctionality();
    }
}
