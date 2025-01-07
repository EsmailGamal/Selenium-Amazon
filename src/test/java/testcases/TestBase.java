package testcases;

import Pages.AccountSettingsPage;
import Pages.AmazonHomePage;
import Pages.CartPage;
import Pages.LoginPage;
import drivers.DriverFactory;
import drivers.DriverHolder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utilities.UserDataReader;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class TestBase {

    protected static WebDriver driver;
    protected static final String Base_URL = "https://www.amazon.eg/-/en/ref=nav_logo";

    @Parameters("browser")
    @BeforeTest
    public void setupDriver(@Optional("chrome") String browser) throws Exception {
        try {

            // Initialize the WebDriver using DriverFactory
            driver = DriverFactory.getNewInstance(browser);
            DriverHolder.setDriver(driver);
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.navigate().refresh();
            driver.get(Base_URL);

            System.out.println("[INFO] Browser setup complete with URL: https://www.amazon.com");

            new LoginPage(driver).performLogin();
            new CartPage(driver, 0).clearCart();
            new AmazonHomePage(driver).navigateToProfileSection();
            new AccountSettingsPage(driver).navigateToAddressSection()
                                           .clickRemoveAddress();

        } catch (Exception e) {
            System.err.println("[ERROR] Failed to initialize the browser: " + e.getMessage());
            throw e;
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        try {
            if (driver != null) {
                driver.quit();
                System.out.println("[INFO] Browser closed successfully.");
            }
        } catch (WebDriverException e) {
            System.err.println("[ERROR] WebDriver exception during teardown: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("[ERROR] Unexpected error during teardown: " + e.getMessage());
            throw e;
        } finally {
            try {
                DriverHolder.quitDriver();
                System.out.println("[INFO] DriverHolder cleanup complete.");
            } catch (Exception e) {
                System.err.println("[ERROR] Failed to clean up DriverHolder: " + e.getMessage());
            }
        }
    }

}




