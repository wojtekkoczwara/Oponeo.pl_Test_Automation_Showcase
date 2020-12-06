package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    //Driver declaration
    private static WebDriver driver;

    //class constructor and pagefactory initiation
    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Finding relevant elements at login page DOM
    @FindBy(css = "input[class='email required']")
    WebElement emailInput;

    @FindBy(name = "Password")
    WebElement passwordInput;

    @FindBy(xpath = "//a[@ajaxsubmit = 'Login']")
    WebElement loginSubmitButton;

    //Sending credentials to the relevant input fields
    public void fulfillCredentials(String email, String password){
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /*Submission of login Captcha handling necessary. Captcha is displayed
    after more than 10 logins, but it is necessary to handle it via try catch.
    Unfortunately, try catch is an alorithm, that only human can go trough. So that it was necessary to detect it
    and set waiter to allow user to go through it manually.
     */
    public void loginSubmit(){
        try {
            WebElement captchaVerification = driver.findElement(By.id("recaptcha"));
            if (captchaVerification.isDisplayed()) {
                System.out.println("Captcha verification necessary...");
                Thread.sleep(80000);
            }

        }
        catch (Exception e1){
            System.out.println("Captcha didn't detected, moving on...");
        }


        try {
            driver.findElement(By.xpath("//a[@ajaxsubmit = 'Login']")).click();
        }
        catch (Exception e2){
            System.out.println("Probably captcha didn't handled in time, try to redo a test");
        }
    }

}
