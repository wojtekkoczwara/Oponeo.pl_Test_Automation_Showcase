import DriverSetUp.WebDriverSetUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import pageObjects.NavigationPanelPage;
import utilities.Constant;

import java.awt.peer.CanvasPeer;
import java.util.concurrent.TimeUnit;

public class LoginTest {

    // driver declaration
    private static WebDriver webDriver;
    private static WebDriver driver;

    //setup method
    @BeforeTest
    public void setUp() {

        //send driver to the class, where driver is being set
        driver = WebDriverSetUp.WebDriverSetUpMethod(webDriver);

        //declaration of variable to the explicit wait (in case)
        WebDriverWait wait = new WebDriverWait(driver, 3);

    }


    @Test
    public void loginTest() throws InterruptedException{

        //Declaration of object of landing page class - navigation panel
        NavigationPanelPage navigationPanelPage = new NavigationPanelPage(driver);

        // go through navigation panel to login page
        navigationPanelPage.goToLogin();

        //Login page object declaration
        LoginPage loginPage = new LoginPage(driver);

        //Send credentials to login and submit
        loginPage.fulfillCredentials(Constant.email, Constant.password);
        loginPage.loginSubmit();

        //Validate login
        Assert.assertTrue(driver.findElement(By.cssSelector("div[class = 'account logged']")).isDisplayed(), "Not logged to oponeo.pl");

    }

    //Quit driver
    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
