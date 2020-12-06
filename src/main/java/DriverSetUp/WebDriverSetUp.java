package DriverSetUp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utilities.Constant;

import java.util.concurrent.TimeUnit;

public class WebDriverSetUp {



    //Driver setting.
    public static WebDriver WebDriverSetUpMethod(WebDriver driver) {

        System.setProperty(Constant.webDriverKey, Constant.webDriverPath);
        driver = new ChromeDriver();

        //Wait definition
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //Maximizing window
        driver.manage().window().maximize();

        //Go to demanded URL
        driver.get(Constant.URL);
        return driver;
    }

}
