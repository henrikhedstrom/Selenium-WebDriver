package se.iths.Lab3;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import se.iths.Lab3.*;

public class Homepage {
 	WebElement element;
 	FirefoxDriver driver;
 	
 	public Homepage(FirefoxDriver driver){
 		this.driver = driver;
 	}
 	
 	public void openStoreDemoqa() {
		driver.get("http://www.store.demoqa.com");	
	}	 
	
 	
	public void SearchProduct(String Product) {
		
	     element = driver.findElement(By.name("s"));
	     element.sendKeys(Product);
	     element.submit();
	}
	public void buyProduct() throws InterruptedException {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.findElement(By.xpath("//input[@name='Buy'][@class='wpsc_buy_button']")).isDisplayed();

            }
        });
		WebElement element = driver.findElement(By.xpath("//input[@name='Buy'][@class='wpsc_buy_button']"));
		System.out.println(element.isEnabled());
	    element.click();
	   
	}
	public void continueShopping() {
		(new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
               return d.findElement(By.xpath("//a[@class='continue_shopping']")).isDisplayed();

            }
        });
	driver.findElement(By.xpath("//a[@class='continue_shopping']")).click();
	}
	
	public void goToCheckout() {
	
		 (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	               return d.findElement(By.linkText("Go to Checkout")).isDisplayed();

	            }
	        });
		driver.findElement(By.linkText("Go to Checkout")).click();	
	}
}
