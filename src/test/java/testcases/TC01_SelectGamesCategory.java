package testcases;

import Pages.AllProductsPage;
import Pages.AmazonHomePage;
import Pages.VideoGamesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01_SelectGamesCategory extends TestBase {
    @Test(priority = 1, description = "Ensure User Able to Land on All Video Games Successfully")
    public void verifyUserAbleToLandOnVideoGamesAndBannerIsDisplayed() {
        VideoGamesPage videoGamesPage = new AmazonHomePage(driver)
                .clickAllItemsTap()
                .clickOnSeeAllButton()
                .clickOnVideoGames()
                .clickOnAllVideoGames();
        boolean isVideoBannerDisplayed = videoGamesPage.verifyVideoBannerDisplayed();
        Assert.assertTrue(isVideoBannerDisplayed, "[ERROR] Video Banner Not Displayed.");
        System.out.println("[INFO] Verified: Video Banner is displayed.");

    }
}