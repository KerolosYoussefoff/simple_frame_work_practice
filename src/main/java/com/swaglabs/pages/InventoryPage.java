package com.swaglabs.pages;

import com.swaglabs.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.locators.RelativeLocator;

public class InventoryPage {
    private final WebDriver driver;
    private int productsAddedCounter = 0;

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
        productsAddedCounter++;
        return this;
    }

    public int getProductsAddedCounter() {
        return productsAddedCounter;
    }

    @Step("Verify the number of products added to the cart")
    public InventoryPage assertProductsAdded() {
        By cartBadge =By.xpath("//span[@data-test=\"shopping-cart-badge\"");
        String actualCartCount = ElementAction.getElementText(driver, cartBadge);
        customSoftAssertion.softAssertion.assertEquals(
                String.valueOf(getProductsAddedCounter()),
                actualCartCount,
                "Verify the number of products added to cart"
        );
        return this;
    }
}