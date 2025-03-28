package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class InventoryPage {
    private final WebDriver driver;

    private final By cartIcon = By.className("shopping_cart_container");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Open the inventory page.")
    public InventoryPage navigateToInventoryPage() {
        BrowserActions.openWebsite(driver, propertiesUtils.getPropertiesValue("homeUrl"));
        return this;
    }

    @Step("Add the product '{productName}' to the cart")
    public InventoryPage addProductToCart(String productName) {
        By addToCartButton = RelativeLocator.with(By.tagName("button")).below(By.linkText(productName));
        ElementAction.clickElement(driver, addToCartButton);
        LogsUtils.info("The product:"+ productName+"is added to cart.");

        return this;
    }
    @Step("Open cart page.")
    public CartPage openCartPage(){
        ElementAction.clickElement(driver,cartIcon);
        return new CartPage(driver);
    }
    // validations
    @Step("Verify the product added to the cart")
    public InventoryPage assertProductsAdded(String productName) {
        By removeButton = RelativeLocator.with(By.tagName("button")).below(By.linkText(productName));
        String actualValue = ElementAction.getElementText(driver,removeButton);
        LogsUtils.info("Actual :" + actualValue);
        CustomSoftAssertion.getInstance().assertEquals(actualValue,"Remove","product is added to the cart ");
        LogsUtils.info(productName +" product is added to the cart.");
        return this;
    }

}