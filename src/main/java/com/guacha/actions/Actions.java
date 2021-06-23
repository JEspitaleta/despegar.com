package com.guacha.actions;

import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Actions {
	
	public static void typeIntoField(WebDriver driver, By field, String text) {
		clickElement(driver, field);
		driver.findElement(field).sendKeys(text);

	}
	
	public static void clickElement(WebDriver driver, By element) {
		driver.findElement(element).click();
	}

	public static void waitForElement(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	public static List<WebElement> getElements(WebDriver driver, By xpath) {
		return driver.findElements(xpath);
	}

	public static void clickElement(WebDriver driver, WebElement e) {
		e.click();
	}

	public static String getAttributeOfElement(WebDriver driver, By element, String attr) {
		return driver.findElement(element).getAttribute(attr);
	}
	
}
