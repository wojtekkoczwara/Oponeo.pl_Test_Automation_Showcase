Classes description:

DriverSetup folder:
WebDriverSetUp: 
This class is responsible for set up driver. In this class it's defined for this moment only chromeDriver, but it will be expanded for other browsers. 
There's defined waiter set for 5 secs.

Driver is derived from other classes, that perform testing.

pageObjects:
LoginPage
This class is for page object pattern of login site

NavigationPanelPage
This page object class is for navigation top panel

OrderPage
This class is for page object, where user places an order (paersonal data, payment etc.)

ProductListPage
This page object class gives a web element locators for listed products of chosen tires.

SearchPage 
This page object class gives a locator for product chosing

Utilities:
Constant
This class have a user defined variables. Here user may give any demanded values to be looked for

Test:
LoginTest, that perform login testing

TireFindAndBuyFlowTest
Here are the methods, that perform purchasing process from chosing a product, through adding the product to cart and validation of the price up to ordering a product. Test is finished after providing agreement to personal data handlig for purchasing process. 
