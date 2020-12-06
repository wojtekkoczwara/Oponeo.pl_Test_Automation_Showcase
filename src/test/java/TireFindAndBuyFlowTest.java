import DriverSetUp.WebDriverSetUp;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.OrderPage;
import pageObjects.ProductListPage;
import pageObjects.SearchPage;
import utilities.Constant;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TireFindAndBuyFlowTest extends WebDriverSetUp {

    // driver declaration
    private static WebDriver webDriver;
    private static WebDriver driver;

    //setup method
    @BeforeTest
    public void setUp() {

        driver = WebDriverSetUp.WebDriverSetUpMethod(webDriver);
        WebDriverWait wait = new WebDriverWait(driver, 3);

    }


    @Test (priority = 0)
    public void findAndBuyTest() throws InterruptedException, IOException {

        //Declaration of searchproduct page object class.
        SearchPage searchPage = new SearchPage(driver);

        //setting tire parameters. Width, Ratio, Dia, Producers, Seasons. Thread.sleeps necessary
        // to allow page to reload. Explicit waits didn't handled this.
        searchPage.setTireWidth(Constant.tireWidthValue);

        Thread.sleep(1000);

        searchPage.setTireDimRatio(Constant.profileValue);

        Thread.sleep(1000);

        searchPage.setTireDimDiameter(Constant.diameterValue);

        searchPage.selectProducer();

        Thread.sleep(1000);

        searchPage.selectSeasons();

        searchPage.seasonsSearchSubmit();

        //Checking, whether any tires are found with given search parameters
        Assert.assertTrue(driver.findElement(By.id("_upTireLstSrt")).
                isDisplayed(), "No tires in such configuration");

        // Declaration of productList page object class.
        ProductListPage productListPage = new ProductListPage(driver);

        //Product choice. One product with given amount.
        productListPage.chooseProduct();

        productListPage.setDropDownAmount(Constant.amountOfTires);

        //Going to cart. At this moment page automatically redirects user to cart (when he decides to add to cart a tire
        productListPage.goToCartFromProductList();

        //Werification of price calculator
        int totalPositionAmount = productListPage.getPositionAmountValue();
        int totalAmount = productListPage.getTotalAmountValue();
        Assert.assertEquals(totalPositionAmount, totalAmount);

        //Choice final confirmation and going to ordering page
        productListPage.confirmOrder();

        // Declaration of ordering page object class.
        OrderPage orderPage = new OrderPage(driver);


        //Setting personal data given at Constant.class
        orderPage.setPersonalData(Constant.firstName, Constant.lastName, Constant.address, Constant.postalCode,
                Constant.city, Constant.phone, Constant.email);


        //Chosing payment method
        orderPage.choosePayment(Constant.paymentMethodChosen);

        Thread.sleep(1000);

        //Handling permissions. Assertion ensures, that user didn't granted marketing permission
        orderPage.bgFormRegulationsCheck();

        Assert.assertTrue(driver.findElement(By.xpath
                ("//label[@class = 'firstTime']")).isDisplayed(), "Marketing agreement enabled");


        DateFormat df = new SimpleDateFormat("dd-MM-yy HH.mm.ss");
        Date dateobj = new Date();

        //Screenshot of an order page (just for showcase).
        TakesScreenshot scrShot = ((TakesScreenshot)driver);
        File ScrFile = scrShot.getScreenshotAs(OutputType.FILE);

        //Currently screenshot is saved locally at my directory hardcoded. It will be upgraded in the future
        File DestFile = new File("./ScreenShots/ScreenShot - " + df.format(dateobj)+".jpg");


        FileUtils.copyFile(ScrFile, DestFile);

    }

    //Quit driver
    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
