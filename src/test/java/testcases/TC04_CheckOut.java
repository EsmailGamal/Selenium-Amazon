package testcases;

import Pages.AmazonHomePage;
import Pages.CartPage;
import Pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.UserDataReader;

import java.util.Map;

public class TC04_CheckOut extends TestBase {
    private final int MAX_PRICE = 15000;

    @Test(priority = 1, description = "Verify User Can Create Address")
    public void verifyUserCheckoutSuccessfully() throws Exception {
        // Read user data from the JSON file
        UserDataReader userDataReader = new UserDataReader("src/test/java/resources/address.json");
        Map<String, String> user = userDataReader.getUser("user1");

        // Extract user address details directly
        String userName = user.get("userName");
        String mobileNumber = user.get("mobileNumber");
        String streetName = user.get("street_name");
        String buildingName = user.get("building_name");
        String cityArea = user.get("city_area");
        String district = user.get("district");
        String governate = user.get("governate");

        CheckoutPage checkoutPage = new AmazonHomePage(driver)
                .clickAllItemsTap()
                .clickOnSeeAllButton()
                .clickOnVideoGames()
                .clickOnAllVideoGames()
                .applyFreeFilter()
                .applyNewFilter()
                .selectSortingDropdownOptionByIndex(2)
                .clickAddToCartForEligibleItems(MAX_PRICE)
                .navigateToCartPage()
                .navigateToBuyButton()
                .enterValidAddress(userName, mobileNumber, streetName, buildingName, cityArea, district, governate);
        boolean ArePaymentMethodsDisplayed = checkoutPage.paymentMethodsDisplayed();
        Assert.assertTrue(ArePaymentMethodsDisplayed, "[ERROR] Payment Methods Not Displayed");
        System.out.println("Payment Methods Displayed ");


    }
}