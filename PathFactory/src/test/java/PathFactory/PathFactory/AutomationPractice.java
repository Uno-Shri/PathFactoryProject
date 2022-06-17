package PathFactory.PathFactory;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;



public class AutomationPractice extends Base{
	
	String expectedTitle = "My Store";
	String validEmail = "test@pathfactory.com";
	String invalidEmail = "test123@pathfactory";
	String validPassword = "Password123";
	String invalidPassword = "Password321";
	String actualError = "";
	String expectedLoginTitle = "Login - My Store";
	String expectedError_noEmail = "There is 1 error\nAn email address required.";
	String expectedError_wrongPassword = "There is 1 error\nAuthentication failed.";
	String expectedError_wrongEmail = "There is 1 error\nInvalid email address.";
	String expectedWelcomeText = "Welcome to your account. Here you can manage all of your personal information and orders.";
	String expectedForgotPassworrdText = "Please enter the email address you used to register. We will then send you a new password.";
	
    //Locators
	String signInLocator = "//*[contains(text(),'Sign in')]";
	String forgotPasswordLocator = "//*[contains(text(),'Forgot your password')]";
	String emailFieldLocator = "//*[@id='email_create']";
	String submitLogInBtnLocator = "//*[@id='SubmitLogin']";
	String registeredEmailLocator = "//*[@id='email']";
	String passwordLocator = "//*[@id='passwd']";
	
//***************************************************************************************************************************************

	
   @BeforeClass
  public void launchUrl() {
	   
	   setupBrowser("Chrome" , "http://automationpractice.com/index.php");
	   
   }

  @Test (priority=1)
  public void verifyLandingPageTest() {
		
	  String actualTitle = driver.getTitle();
	  Assert.assertEquals(actualTitle, expectedTitle );
	  
  }
  
  @Test (priority=2)
  public void verifyEmptyStateErrorTest()  {
	  driver.findElement(By.xpath(signInLocator)).click();

	  driver.findElement(By.xpath(submitLogInBtnLocator)).click();
	  actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();  
	  
	  Assert.assertEquals(actualError, expectedError_noEmail);
	 
  }
  
  @Test (priority=3)
  public void verifyIncorrectPasswordErrorTest() {
	  
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(validEmail);
		driver.findElement(By.xpath(passwordLocator)).sendKeys(invalidPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();
		
		Assert.assertEquals(actualError, expectedError_wrongPassword);
  }
  
  @Test (priority=4)
  public void verifyIncorrectEmailErrorTest() {
	    driver.findElement(By.xpath(registeredEmailLocator)).clear();
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(invalidEmail);
		driver.findElement(By.xpath(passwordLocator)).clear();
		driver.findElement(By.xpath(passwordLocator)).sendKeys(validPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		actualError = driver.findElement(By.xpath("//*[@id=\'center_column\']/div[1]")).getText();
		
		Assert.assertEquals(actualError, expectedError_wrongEmail);
  }
  
  @Test (priority=5)
  public void verifyForgotPasswordLinkTest() {
	  
	  driver.findElement(By.xpath(forgotPasswordLocator)).click();
	    String forgotPasswordLandingPage = driver.findElement(By.xpath("//*[contains(text(),'Please enter the email')]")).getText();
				
	    try {
	        Thread.sleep(5000);
	    } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				driver.findElement(By.xpath(signInLocator)).click();
				
				if(forgotPasswordLandingPage.equals(expectedForgotPassworrdText));
				
				Assert.assertEquals(forgotPasswordLandingPage, expectedForgotPassworrdText);
  }
  
  @Test (priority=6)
  public void verifyLoginSuccess() {
	  
		driver.findElement(By.xpath(registeredEmailLocator)).clear();
		driver.findElement(By.xpath(registeredEmailLocator)).sendKeys(validEmail);
		driver.findElement(By.xpath(passwordLocator)).clear();
		driver.findElement(By.xpath(passwordLocator)).sendKeys(validPassword);
		
		driver.findElement(By.xpath(submitLogInBtnLocator)).click();
		String welcomeText = driver.findElement(By.xpath("//*[@class=\'info-account\']")).getText();
		//Assertion
		Assert.assertEquals(welcomeText, expectedWelcomeText);
  }
  
  @AfterClass 
  
  public void tearDown() {
	  
	  driver.close();
	  
  }
  

}
