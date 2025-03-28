package com.swaglabs.tests;

import com.swaglabs.driver.DriverManager;
import com.swaglabs.listeners.TestNGListeners;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.InventoryPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.jsonUtils;
import com.swaglabs.utils.propertiesUtils;
import org.testng.annotations.*;

import java.io.File;

//the test case will fail if @listeners(TestNGListeners.class) is not added to the code
@Listeners(TestNGListeners.class)
public class E2e {
    // Variables
    File allureResultFile = new File("test-outputs/allure-results");
    jsonUtils testData ;
    // Tests will be defined here (e.g., login functionality tests)
    @Test
    public void successfullLogin(){
        new LoginPage(DriverManager.getDriver())
                .enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLogin().assertSuccessfulLoginSoft();
    }
    @Test(dependsOnMethods = "successfullLogin")
    public void addToCart(){
        new InventoryPage(DriverManager.getDriver())
                .addProductToCart(testData.getJsonData("product-names.item-1.name"))
                .addProductToCart(testData.getJsonData("product-names.item-2.name"))
                .assertProductsAdded(testData.getJsonData("product-names.item-2.name"));
    }
    @Test(dependsOnMethods = "addToCart")
    public void checkProductsAddedToTheCart(){
        new InventoryPage(DriverManager.getDriver())
                .openCartPage()
                .assertProductDetailsFromCart(testData.getJsonData("product-names.item-1.name"),testData.getJsonData("product-names.item-1.price"))
                .assertProductDetailsFromCart(testData.getJsonData("product-names.item-2.name"),testData.getJsonData("product-names.item-2.price"));
    }
    @Test(dependsOnMethods = "checkProductsAddedToTheCart")
    public void checkoutProcess(){
        new CartPage(DriverManager.getDriver())
                .openCheckoutPage()
                .sendCustomerDetails(
                         testData.getJsonData("checkout-data.first-name")
                        ,testData.getJsonData("checkout-data.last-name")
                        ,testData.getJsonData("checkout-data.zip-code"))
                .validateCheckoutInformation(
                         testData.getJsonData("checkout-data.first-name")
                        ,testData.getJsonData("checkout-data.last-name")
                        ,testData.getJsonData("checkout-data.zip-code")
                ).clickContinue();
                ;
    }
    // Configuration method to set up the test environment before each test method
    @BeforeClass
    public void beforeClass(){
        testData =new jsonUtils("test-data");
        String browserName =propertiesUtils.getPropertiesValue("browserType");
        DriverManager.createInstance(browserName);
        new LoginPage(DriverManager.getDriver()).navigateToLoginPage();
    }

    // Method to clean up after tests, executed after each test method
    @AfterClass
    public void tearDown() {
        // Close the browser and end the WebDriver session to free up resources
//        BrowserActions.quitBrowser(DriverManager.getDriver());
    }

}