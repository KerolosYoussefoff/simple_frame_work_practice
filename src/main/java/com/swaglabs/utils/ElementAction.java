package com.swaglabs.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ElementAction {
    //private constructor to forbid instantiation
    private ElementAction(){
    }

    // send data to an element
    @Step("Sending data to the element : {locator} with data : {data}")
    public static void sendData(WebDriver driver, By locator , String data){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        findElement(driver,locator).sendKeys(data);
        LogsUtils.info("The data sent to the element is : " , data , " and the locator is : " , locator.toString());
    }
    // click on an element
    @Step("Clicking on the element : {locator}")
    public static void clickElement(WebDriver driver , By locator){
        Waits.waitForElementToBeClickable(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        findElement(driver,locator).click();
        LogsUtils.info("The element is clicked and the locator is : " , locator.toString());
    }
    // get text of an element
    @Step("Getting the text of the element : {locator}")
    public static String getElementText(WebDriver driver , By locator){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        LogsUtils.info("Getting element text :", locator.toString() , "Text : " , findElement(driver,locator).getText());
        return findElement(driver,locator).getText();

    }
    // get the value of an element
    @Step("Getting the value of the element : {locator}")
    public static String getElementValue(WebDriver driver , By locator){
        Waits.waitForElementToBeVisible(driver,locator);
        Scrolling.scrollToWebElement(driver,locator);
        LogsUtils.info("Getting element value :", locator.toString() , "Text : " , findElement(driver,locator).getDomProperty("value"));
        return  findElement(driver,locator).getDomProperty("value");
    }
    // find an element
    @Step("Finding the element : {locator}")
    public static WebElement findElement(WebDriver driver ,By locator){
         return driver.findElement(locator);
    }




}
