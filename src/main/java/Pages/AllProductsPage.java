package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllProductsPage {
    private WebDriver driver;
    public AllProductsPage (WebDriver driver) {
        this.driver = driver;
    }
    private final By videoGames = By.xpath("//a[@class='hmenu-item']//div[contains(text(),'Video Games')]");
    private final By allVideoGames = By.cssSelector("ul[class='hmenu hmenu-visible hmenu-translateX'] li:nth-child(3) a:nth-child(1)");
    private final By seeAllButton = By.cssSelector("a[aria-label='See All Categories'] div");


    public AllProductsPage clickOnSeeAllButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.seeAllButton));
        element.click();
        return this;
    }
    public AllProductsPage clickOnVideoGames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.videoGames));
        element.click();
        return this;
    }
    public VideoGamesPage clickOnAllVideoGames() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(this.allVideoGames));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
         return new VideoGamesPage(driver);
    }

    }

