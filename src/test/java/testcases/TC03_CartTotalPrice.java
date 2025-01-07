package testcases;

import Pages.AmazonHomePage;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.VideoGamesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_CartTotalPrice extends TestBase {


    private final int MAX_PRICE = 15000;

    @Test(priority = 1, description = "Verify Correct Total Price Display in Cart")
    public void verifyCorrectTotalPriceDisplaysInCart() {
        CartPage cartPage = new AmazonHomePage(driver)
                .clickAllItemsTap()
                .clickOnSeeAllButton()
                .clickOnVideoGames()
                .clickOnAllVideoGames()
                .applyFreeFilter()
                .applyNewFilter()
                .selectSortingDropdownOptionByIndex(2)
                .clickAddToCartForEligibleItems(MAX_PRICE)
                .navigateToCartPage();

        int calculatedTotalPrice = cartPage.getCalculatedTotalPrice();
        int cartDisplayedTotalPrice = cartPage.getTotalPriceFromCart();

        Assert.assertEquals(cartDisplayedTotalPrice, calculatedTotalPrice, "[ERROR] Total price mismatch!");

        System.out.println("[INFO] Total price assertion passed. Displayed price Without Fraction: " + cartDisplayedTotalPrice + ", Calculated price: " + calculatedTotalPrice);
    }
}

