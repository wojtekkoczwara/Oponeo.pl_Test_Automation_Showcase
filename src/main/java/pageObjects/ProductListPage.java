package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class ProductListPage {

    //Driver declaration
    private static WebDriver driver;

    //class constructor and pagefactory initiation
    public ProductListPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Finding relevant elements at product list container DOM
    @FindBy(name = "_ctTL_r0_ddlQuantity")
    List<WebElement> tireAmountSelectorList;

    @FindBy(name = "_ctTL_r0_ddlQuantity")
    WebElement dropDownAmount;

    @FindBy(id = "_ctTL_r0_bttnATC")
    WebElement goToCartFromProductListButton;

    @FindBy (xpath = "//div[@class = 'column sum']/div[@class = 'summary']/span[@class = 'price']")
    WebElement totalPositionAmountElement;

    @FindBy (xpath = "//div[@class = 'cartSummary container']/div[@class = 'summary']/p/strong")
    WebElement totalAmountElement;

    @FindBy(id = "cm_c_lbGTO")
    WebElement confirmOrder;

    /*
    It's necesssary to list all selectors at relevant tire containers. By get() construction driver chooses first
    element and clicks it
     */
    public void chooseProduct(){
        tireAmountSelectorList.get(0).click();
    }



    /*
    At list selection driver chooses value, that is demanded and delivered from outside class
     */
    public void setDropDownAmount(String amount){
        Select dropDOwnAmountSelect = new Select(dropDownAmount);
        dropDOwnAmountSelect.selectByValue(amount);
    }

    /*
    Submit choice of a tire and amount of relevant tires
     */
    public void goToCartFromProductList(){
        goToCartFromProductListButton.click();
    }

    /*
    Checking price calculator - one position amount
     */
    public int getPositionAmountValue (){
        String totalPositionAmountText = totalPositionAmountElement.getText();
        int totalPositionAmount = priceStringToIntConverter(totalPositionAmountText);
        return totalPositionAmount;
    }

    /*
    Checking price calculator - total amount
     */
    public int getTotalAmountValue(){
        String totalAmountText = totalAmountElement.getText();
        int totalAmount = priceStringToIntConverter(totalAmountText);
        return totalAmount;
    }

    /*
    Order confirmation
     */
    public void confirmOrder(){
        confirmOrder.click();
    }

    /*
    Convert price from String to Int at price calculator. This method is checking length of a price. If 6 - price is
    XXX PLN (i.e. 100), if 7 - XXXX (i.e. 10000). Then, after substring, clear price without value is converted to int
     */
    public int priceStringToIntConverter (String amountText){
        int totalPositionAmountTextLength = amountText.length();
        if (totalPositionAmountTextLength == 6){
            amountText = amountText.substring(0,3);
        }
        else if (totalPositionAmountTextLength == 7) {
            amountText = amountText.substring(0,4);
        }
        else {
            System.out.println("Amount of tires chosen is too big");
        }
        int totalAmount = Integer.parseInt(amountText);
        return totalAmount;
    }
}
