package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {
     WebDriver driver;
    public CheckoutPage (WebDriver driver) {this.driver=driver;}

    private final By secureCheckOut = By.id("nav-checkout-title-header-text");
    private final By addNewAddressButton = By.xpath("//a[@class='a-button-text']");
    private final By userNameInAddress = By.id("address-ui-widgets-enterAddressFullName");
    private final By mobileNumberInAddress = By.id("address-ui-widgets-enterAddressPhoneNumber");
    private final By streetName= By.id("address-ui-widgets-enterAddressLine1");
    private final By buildingName = By.id("address-ui-widgets-enter-building-name-or-number");
    private final By cityArea = By.id("address-ui-widgets-enterAddressCity");
    private  By cityOptions(int index) {
        return By.id("address-ui-widgets-autoCompleteResult-" + index);
    };
    private final By district = By.name ("address-ui-widgets-enterAddressDistrictOrCounty");
    private final By governate = By.name ("address-ui-widgets-enterAddressStateOrRegion");

    private  By districtOptions(int index) {return By.id("address-ui-widgets-autoCompleteResult-" + index); };
    private final By saveAddressButton = By.xpath("//input[@data-testid='bottom-continue-button']");
    private final By paymentMethodButton = By.xpath("//span[@id='checkout-primary-continue-button-id']//input[@type='submit']");

    public CheckoutPage enterValidAddress(String name, String mobile, String streetName, String buildingName, String cityArea, String district , String governate) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(this.addNewAddressButton)).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.userNameInAddress)).sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(this.mobileNumberInAddress)).sendKeys(mobile);

        wait.until(ExpectedConditions.elementToBeClickable(this.streetName)).sendKeys(streetName);

        wait.until(ExpectedConditions.elementToBeClickable(this.buildingName)).sendKeys(buildingName);

        wait.until(ExpectedConditions.elementToBeClickable(this.cityArea)).sendKeys(cityArea);

        //
        //wait.until(ExpectedConditions.elementToBeClickable(this.cityOptions(0))).click();

        wait.until(ExpectedConditions.elementToBeClickable(this.district)).sendKeys(district);

        wait.until(ExpectedConditions.elementToBeClickable(this.governate)).sendKeys(governate);


       // wait.until(ExpectedConditions.elementToBeClickable(this.districtOptions(0))).click();

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.saveAddressButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

        return this;
    }

    public Boolean paymentMethodsDisplayed() {
        return driver.findElement(paymentMethodButton).isDisplayed();
    }
}
