package testcases;

import Pages.AllProductsPage;
import Pages.AmazonHomePage;
import Pages.CartPage;
import Pages.VideoGamesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_AddToCartPostFilters extends TestBase {
    @Test(priority = 1, description = "Verify Video Games added to cart post-filters")
    public void verifyUserAbleToLAddVideoGamesToCart(){
        CartPage cartPage = new AmazonHomePage(driver)
                .clickAllItemsTap()
                .clickOnSeeAllButton()
                .clickOnVideoGames()
                .clickOnAllVideoGames()
                .applyFreeFilter()
                .applyNewFilter()
                .selectSortingDropdownOptionByIndex(2)
                .clickAddToCartForEligibleItems(15000)
                .navigateToCartPage();
        boolean isUserLandSuccessfullyInCart = cartPage.UserLandSuccessfullyInCartPage();
        Assert.assertTrue(isUserLandSuccessfullyInCart, "[ERROR] User Not Land Successfully");
        System.out.println("User Land Successfully In Cart Page ");

    }



    }
