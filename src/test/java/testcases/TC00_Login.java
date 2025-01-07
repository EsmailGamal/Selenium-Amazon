package testcases;

import Pages.AmazonHomePage;
import Pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.UserDataReader;

import java.util.Map;


public class TC00_Login extends TestBase {

    @Test(priority = 1, description = "Ensure user Login Successfully")
    public void ensureSuccessfullyLogin(){
        String Account = new AmazonHomePage (driver)
                .checkUserNameDisplayed();
        Assert.assertTrue(Account.contains("Esmail"),
                "The profile does not contain the expected text: Esmail");


    }
}