package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    //Driver declaration
    private static WebDriver driver;


    //class constructor and pagefactory initiation
    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    //Finding relevant elements at container with product choosing DOM
    @FindBy (id = "_carTires_ctTS_ddlDimWidth")
    WebElement tireWidth;

    @FindBy(id = "_carTires_ctTS_ddlDimRatio")
    WebElement tireDimRatio;

    @FindBy(id = "_carTires_ctTS_ddlDimDiameter")
    WebElement tireDimDiameter;

    @FindBy(xpath = "//div[@class = 'optionListNew multiselect producerList carTires b silver']")
    WebElement producerSelectLocator;

    @FindBy(xpath = "//label[@for = 'group_carTires_ctTS_olProducers']")
    WebElement allProducers;

    @FindBy(xpath = "//div[@class = 'optionListNew multiselect b seasonList silver']")
    WebElement seasonSelectLocator;

    @FindBy(xpath = "//label [@for = 'group_carTires_ctTS_olSeasons']")
    WebElement allSeasons;

    @FindBy(id = "_carTires_ctTS_lbSubmit0")
    WebElement seasonsSearchSubmit;


    //Tire width setting. Surronded by try catch in order to handle page reloading
    public void setTireWidth(String tireWidthValue){

        try {
            tireWidth.sendKeys(tireWidthValue);
        }
        catch (StaleElementReferenceException | ElementNotInteractableException e1){
            System.out.println("Exception caught at tire width");
        }
    }

    //Tire Dimension ratio setting. Surronded by try catch in order to handle page DOM reloading
    public void setTireDimRatio(String profileValue){

        try {
            tireDimRatio.sendKeys(profileValue);
        }
        catch (StaleElementReferenceException | ElementNotInteractableException e2) {
            System.out.println("Exception caught at tire Dim");
            tireDimRatio.sendKeys(profileValue);
        }

    }

    //Tire Dimension ratio setting. Surronded by try catch in order to handle page DOM reloading
    public void setTireDimDiameter(String diameterValue){
        try {
            tireDimDiameter.sendKeys(diameterValue);
        }
        catch (StaleElementReferenceException | ElementNotInteractableException e2) {
            System.out.println("Exception caught at tire Dia");
            tireDimDiameter.sendKeys(diameterValue);
        }
    }

    /*
     Producer selection. Usually at this moment element is fiound before page is reloaded.
     Try catch construction is performed
     to handle exception, where DOM is reloaded. After reloading WebElement isn't displayed and has to be found again.
     */
    public void selectProducer(){
        try {
            producerSelectLocator.click();
        } catch (StaleElementReferenceException s){
            producerSelectLocator.click();
        }

        allProducers.click();
    }

    // Tire relevant season selection
    public void selectSeasons(){
        seasonSelectLocator.click();
        allSeasons.click();
    }

    //Tire choice submission
    public void seasonsSearchSubmit(){
        seasonsSearchSubmit.click();
    }



}
