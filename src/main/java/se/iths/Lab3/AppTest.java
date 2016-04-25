package se.iths.Lab3;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import se.iths.Lab3.Homepage;

public class AppTest {
	 	private static FirefoxDriver driver;
	 	WebElement element;
	 	static Homepage hp;

	 @Before
     public void openBrowser(){
         driver = new FirefoxDriver();
         hp = new Homepage(driver);
		} 
	 /*
	 Test
	 public void registerUser() {
		 driver.get("http://www.store.demoqa.com");	
	     driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	     driver.findElement(By.id("Register")).click();
	     driver.findElement(By.id("user_login")).sendKeys("testuser_1");
	     driver.findElement(By.id("user_email")).sendKeys("testuser_1@test.com");
	     driver.findElement(By.id("aiowps_captcha_equation")).sendKeys("11");	
	  }
	  */
	// @Test
     public void valid_UserCredential(){
		 
	     driver.get("http://www.store.demoqa.com");	
	     driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	     driver.findElement(By.id("log")).sendKeys("testuser_1");
	     driver.findElement(By.id("pwd")).sendKeys("Test@123");
	     driver.findElement(By.id("login")).click();
	 }
	   
	 @Test
     public void inValid_UserCredential() throws InterruptedException
     {
    	 hp.openStoreDemoqa();
    	 driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	     Thread.sleep(1000);
	     driver.findElement(By.id("log")).sendKeys("testuser_1");
	     Thread.sleep(1000);
	     driver.findElement(By.id("pwd")).sendKeys("Test@123");
	     Thread.sleep(1000);
	     driver.findElement(By.id("login")).click();
	     (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
	            public Boolean apply(WebDriver d) {
	                return d.findElement(By.id("login_wrapper")).getText().contains("ERROR");
	            }
	        });
	     Assert.assertTrue(driver.findElement(By.id("login_wrapper")).getText().contains("ERROR"));
	    
     }
	 
	 @Test
	 public void ProcessOfBuyingMagicMouse() throws InterruptedException {
		 hp.openStoreDemoqa();
		 hp.SearchProduct("Magic mouse");
		 hp.buyProduct();
		 hp.goToCheckout();	     
		 Assert.assertTrue(driver.findElement(By.id("wrapper")).getText().contains("Checkout"));     
		 
	 }
	 @Test
	 public void removeProduct() throws InterruptedException {
	     hp.openStoreDemoqa();	
	     hp.SearchProduct("magic mouse");
	     hp.buyProduct();
	     hp.goToCheckout();
	     Thread.sleep(1000);
	     driver.findElement(By.xpath("//input[@value='Remove'][@type='submit']")).click();	  
	     Thread.sleep(1000);
	     Assert.assertTrue(driver.findElement(By.id("wrap-all")).getText().contains("Oops"));

	 }
	 
	 @Test
	 public void addSameProduct() throws InterruptedException {
		 hp.openStoreDemoqa();	
	     hp.SearchProduct("magic mouse");
	     hp.buyProduct();
	     hp.continueShopping();
	     Thread.sleep(2000);
	     hp.buyProduct();
	     hp.goToCheckout();
	     Assert.assertEquals("2",driver.findElement(By.xpath("//input[@name='quantity']")).getAttribute("value"));
	 }
	 
	 @Test
	 public void addTwodifferentProducts() throws InterruptedException {
		 hp.openStoreDemoqa();	
	     hp.SearchProduct("magic mouse");
	     hp.buyProduct();
	     hp.SearchProduct("apple tv");
	     hp.buyProduct();
	     hp.goToCheckout();
	     Assert.assertTrue(driver.findElement(By.id("checkout_page_container")).getText().toLowerCase().contains("magic mouse"));
	     Assert.assertTrue(driver.findElement(By.id("checkout_page_container")).getText().toLowerCase().contains("apple tv")); 

	 }
	 

	 @After
	 public void closeBrowser(){
		 driver.quit();
	 }
}