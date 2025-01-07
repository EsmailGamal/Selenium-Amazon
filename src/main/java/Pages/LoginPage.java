package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.UserDataReader;

import java.util.Map;

public class LoginPage {
    WebDriver driver;

    public LoginPage (WebDriver driver) {this.driver=driver;}

    private final By emailField = By.id("ap_email");
    private final By continueButton = By.id("continue");
    private final By passwordField= By.xpath("//input[@type = 'password']");
    private final By signInButton = By.id("signInSubmit");


    public LoginPage enterValidEmail(String email) {
        driver.findElement(this.emailField).sendKeys(email);
        return this;
    }

    public LoginPage clickContinueButton() {
        driver.findElement(this.continueButton).click();
        return this;
    }

    public LoginPage enterValidPassword(String password) {
        driver.findElement(this.passwordField).sendKeys(password);
        return this;
    }

    public LoginPage clickSignInButton() {
        driver.findElement(this.signInButton).click();
        return this;
    }

    public LoginPage performLogin() throws Exception {
        UserDataReader userDataReader = new UserDataReader("src/test/java/resources/users.json");
        Map<String, String> user = userDataReader.getUser("user1");

        new AmazonHomePage(driver)
                .navigateToLogin()
                .enterValidEmail(user.get("email"))
                .clickContinueButton()
                .enterValidPassword(user.get("password"))
                .clickSignInButton();
        return this;
    }


}


