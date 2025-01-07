package testcases;

import Pages.AmazonHomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC0_HomePage extends TestBase {

    @Test(priority = 1, description = "Ensure Main Sections are Displayed")
    public void verifyMainSectionsAreDisplayed() {
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver);

        boolean areSectionsDisplayed = amazonHomePage.verifyMainSectionsAreDisplayed();

        Assert.assertTrue(areSectionsDisplayed, "[ERROR] Not all main sections are displayed.");

        System.out.println("[INFO] Verified: All main sections are displayed.");
    }

}