package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class CartPage {
    WebDriver driver;
    private int totalPrice; // Field to store total price

    public CartPage(WebDriver driver, int totalPrice) {
        this.driver = driver;
        this.totalPrice = totalPrice;
    }

    public int getCalculatedTotalPrice() {
        return totalPrice;
    }

    private final By cartCounter = By.id("nav-cart-count");
    private final By cartIcon = By.id("nav-cart");
    private final By subTotal = By.id("sc-subtotal-label-activecart");
    private final By removeCart = By.id("deselect-all");
    private final By totalAmountCart = By.cssSelector("span[id='sc-subtotal-amount-activecart'] span[class='a-size-medium a-color-base sc-price sc-white-space-nowrap']");
    private final By goToBasketSidePanel = By.xpath("//a[normalize-space()='Go to basket']");
    private final By proceedToBuyButton = By.xpath("//input[@data-feature-id='proceed-to-checkout-action']");
    public final By trashLocator = By.xpath("//span[@class='a-icon a-icon-small-trash']");

    public final By homeIcon = By.id("nav-logo-sprites");

    public CartPage clearCart() {

            driver.findElement(cartIcon).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            try {
                List<WebElement> trashIcons = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(this.trashLocator));

                while (!trashIcons.isEmpty()) {
                    System.out.println("[INFO] Found " + trashIcons.size() + " items in the cart. Clearing...");

                    // Iterate over trash icons and remove items
                    for (WebElement trashIcon : trashIcons) {
                        try {
                            wait.until(ExpectedConditions.elementToBeClickable(trashIcon)).click();
                            System.out.println("[INFO] Removed an item from the cart.");

                            Thread.sleep(500); // Adjust if necessary
                        } catch (Exception e) {
                            System.err.println("[ERROR] Unable to remove an item: " + e.getMessage());
                        }
                    }

                    trashIcons = driver.findElements(this.trashLocator);
                }

                System.out.println("[INFO] Cart is now empty.");

            } catch (Exception e) {
                System.out.println("[INFO] No items found in the cart. Redirecting to Amazon Home page.");
                driver.findElement(homeIcon).click();
            }

            return this;
        }


    public CartPage navigateToCartPage() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.cartCounter));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        return this;
    }

    public CheckoutPage navigateToBuyButton() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.proceedToBuyButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        return new CheckoutPage(driver);
    }


    public Boolean UserLandSuccessfullyInCartPage() {
        return driver.findElement(removeCart).isDisplayed();
    }

    public int getTotalPriceFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        WebElement totalAmount = wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmountCart));
        String CartpriceText = totalAmount.getText().replace("EGP", "").replace(",", "").trim();

        try {
            // Parse the price as a double and convert to int by removing fractions
             int cartprice = (int) Double.parseDouble(CartpriceText);
             return cartprice;

        } catch (NumberFormatException e) {
            throw new RuntimeException("[ERROR] Failed to parse cart total price: " + e.getMessage());
        }

    }
}
