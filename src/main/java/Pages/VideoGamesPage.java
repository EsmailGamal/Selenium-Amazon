package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.locators.RelativeLocator;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class VideoGamesPage {
    WebDriver driver;
    public int totalPrice = 0;
    public VideoGamesPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By freeShippingFilter = By.xpath("//label[@for='apb-browse-refinements-checkbox_0']//i[@class='a-icon a-icon-checkbox']");
    private final By newFilterCondition = By.xpath("//span[@class='a-size-base a-color-base'][normalize-space()='New']\n");
    private final By dropdownButton = By.xpath("//span[@class='a-dropdown-label']");
    private final By priceWholeElements = By.xpath("//span[@class='a-price-whole']");
    private final By addToCartDetailsPage = By.id("add-to-cart-button");
    private final By addtoCart = By.xpath("//div[@data-cy='add-to-cart']");
    private final By warrantyPopUp = By.id("attach-warranty-displayTitle");
    private final By closeWarranty = By.xpath("//input[@aria-labelledby='attachSiNoCoverage-announce']");

    private By dropdownOption(int index) {
        return By.id("s-result-sort-select_" + index);
    }

    private final By videoGamesBanner = By.cssSelector("div[class='fst-h1-st pageBanner'] h1 b");

    public boolean verifyVideoBannerDisplayed() {
        return driver.findElement(videoGamesBanner).isDisplayed();
    }

    public VideoGamesPage applyFreeFilter() {
        driver.findElement(this.freeShippingFilter).click();
        return this;
    }

    public VideoGamesPage applyNewFilter() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.newFilterCondition));
        element.click();
        return this;
    }

    public VideoGamesPage selectSortingDropdownOptionByIndex(int index) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(dropdownButton));
        dropdown.click();

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(dropdownOption(index)));
        option.click();
        return this;
    }


    public CartPage clickAddToCartForEligibleItems(int maxPrice) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        List<WebElement> priceElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(priceWholeElements));

        List<WebElement> top8Prices = priceElements.stream().limit(8).toList();

        int totalPrice = 0;

        for (WebElement priceElement : top8Prices) {
            String priceText = priceElement.getText().replace("EGP", "").replace(",", "").trim();

            try {
                int price = (int) Double.parseDouble(priceText);

                if (price < maxPrice) {
                    System.out.println("[INFO] Eligible item found with price: " + price);

                    try {
                        WebElement addToCartButton = driver.findElement(
                                RelativeLocator.with(By.tagName("button")).below(priceElement)
                        );

                        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();
                        System.out.println("[INFO] Added item to cart with price: " + price);

                        // Add the item's price to the total
                        totalPrice += price;

                    } catch (Exception e) {
                        System.err.println("[ERROR] Unable to click Add to Cart button for item with price: " + price + " - " + e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("[ERROR] Failed to parse price text: \"" + priceText + "\" - " + e.getMessage());
            }
        }

        System.out.println("[INFO] Total price of items added to cart: " + totalPrice);
        return new CartPage(driver, totalPrice);
    }

}



