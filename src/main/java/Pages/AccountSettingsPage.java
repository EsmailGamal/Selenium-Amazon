package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountSettingsPage {
    private WebDriver driver;
    public AccountSettingsPage (WebDriver driver) {
        this.driver = driver;
    }
    private final By addressSection = By.xpath("//span[normalize-space()='Edit, remove or set default address']");
    private final By removeAddressButton = By.id("ya-myab-address-delete-btn-0");
    private final By confirmAddressDelete = By.id("deleteAddressModal-0-submit-btn-announce");
    private final By homeIcon = By.id("nav-logo-sprites");



    public AccountSettingsPage navigateToAddressSection() {
    driver.findElement(addressSection).click();
    return this;
}

    public AccountSettingsPage clickRemoveAddress() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement removeButton = wait.until(ExpectedConditions.presenceOfElementLocated(removeAddressButton));
            removeButton.click();

            WebElement yesButton = wait.until(ExpectedConditions.elementToBeClickable(confirmAddressDelete ));

            Actions actions = new Actions(driver);
            try {
                actions.moveToElement(yesButton).click().perform();
                actions.moveToElement(yesButton).click().perform();
                actions.moveToElement(yesButton).click().perform();
                System.out.println("[INFO] Successfully clicked 'Yes' using Actions.");
            } catch (Exception e) {
                System.err.println("[WARNING] Actions click failed. Attempting force click...");
                // Fallback to JavascriptExecutor for force click
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yesButton);
                System.out.println("[INFO] Successfully clicked 'Yes' using force click.");
            }

            Thread.sleep(500); // Adjust if necessary

            // Attempt to re-locate the remove button to ensure it doesn't exist
            if (driver.findElements(removeAddressButton).isEmpty()) {
                System.out.println("[INFO] Remove address button no longer exists.");
                driver.findElement(homeIcon).click(); // Assuming homePageIcon is the locator for the home button/icon
            } else {
                System.out.println("[INFO] Remove address button still exists. Retrying...");
                WebElement retryRemoveButton = wait.until(ExpectedConditions.presenceOfElementLocated(removeAddressButton));
                retryRemoveButton.click();
                System.out.println("[INFO] Retried and clicked remove address button.");
            }
        } catch (Exception e) {
            System.out.println("[INFO] Remove address button not found. Redirecting to Amazon Home page.");
            driver.findElement(homeIcon).click(); // Assuming homePageIcon is the locator for the home button/icon
        }

        return this;
    }


}