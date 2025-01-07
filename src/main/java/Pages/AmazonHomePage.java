package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class AmazonHomePage {
    WebDriver driver;

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }
    private final By signInNavLink = By.id("nav-link-accountList-nav-line-1");
    private final By searchInputField = By.id("twotabsearchtextbox");
    private final By allItems = By.id("nav-hamburger-menu");
    private final By perfumesSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_perfumes']");
    private final By todayDeals = By.xpath("//*[@data-csa-c-content-id='nav_cs_gb']");
    private final By helpSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_help']");
    private final By electronicsSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_electronics']");
    private final By toysSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_toys']");
    private final By sellSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_sell']");
    private final By fashionSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_fashion']");
    private final By primeSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_primelink_nonmember']");
    private final By mobileSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_mobiles']");
    private final By superMarketSection = By.xpath("//*[@data-csa-c-content-id='nav_cs_supermarket']");


    private final List<By> ELEMENT_LOCATORS = Arrays.asList(
            searchInputField,
            toysSection,
            todayDeals,
            electronicsSection,
            allItems
    );
    public boolean verifyMainSectionsAreDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        for (By locator : ELEMENT_LOCATORS) {
            if (!driver.findElement(locator).isDisplayed()) {
                return false;
            }
        }
        return true;
    }

    public AllProductsPage clickAllItemsTap() {
        driver.findElement(this.allItems).click();
        return new AllProductsPage (driver);
    }

    public LoginPage navigateToLogin() {
        driver.findElement(this.signInNavLink).click();
        return new LoginPage (driver);
    }

    public String checkUserNameDisplayed() {
        return driver.findElement(this.signInNavLink).getText();

    }

    public AccountSettingsPage navigateToProfileSection() {
         driver.findElement(this.signInNavLink).click();

         return new AccountSettingsPage(driver);

    }
}
