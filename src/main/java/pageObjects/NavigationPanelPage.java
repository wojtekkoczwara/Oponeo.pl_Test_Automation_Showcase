package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NavigationPanelPage {

    //Driver declaration
    private static WebDriver driver;

    //Class constructor and pageFactory initiation
    public NavigationPanelPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Relevant elements at Navigation panel location at DOM
    @FindBy(css = "a[class = 'placeholder jqCollapseLink']")
    WebElement loginThrowDownMenu;

    @FindBy(id = "_clntPrflMgrCT_ctClntPrfl_ctLgOutClnt_btnLogin")
    WebElement loginButton;

    //Method handling navigation from landing page to login page
    public void goToLogin(){
        loginThrowDownMenu.click();
        loginButton.click();
    }

}
