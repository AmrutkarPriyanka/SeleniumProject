package verveTech;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ScreenCode {

	WebDriver driver;
	WebDriverWait wait ;

	//pre condition annotation
	@BeforeMethod
	public void login ()
	{
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get("https://www.myntra.com/");
	}

	@Test
	public void sendkeys() throws InterruptedException 
	{
		//Click On Men field
		WebElement MenField = driver.findElement(By.xpath("//a[@data-group='men']"));

		// mouse over using action class
		Actions act = new Actions(driver);
		act.moveToElement(MenField).build().perform();

		// click on PhoneCase
		WebElement phonecase = driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and @data-reactid='168'] "));
		phonecase.click();

		// Click on 3rd row 3rd element 
		WebElement ThirdRowThirdCase = driver.findElement(By.xpath("(//ul[@class='results-base']/li)[13]"));
		ThirdRowThirdCase.click();

		// handling the switching of window tab using Set<String>
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		String parentWindowId = it.next();
		String childWindowId = it.next();
		driver.switchTo().window(childWindowId);

		//price of selected phone case
		WebElement priceText = driver.findElement(By.xpath("//span[@class='pdp-price']/strong"));

		//Validation of price is displayed
		Assert.assertTrue(priceText.isDisplayed());

		String str = priceText.getText();
		int price = Integer.parseInt(str.substring(4));

		//Validation of price is not less than 10
		if(price>=10)
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.fail();
		}	

		//Validation of AddToCartButton is displayed

		WebElement AddToBagButton = driver.findElement(By.xpath("//div[contains(text(),'ADD TO BAG')]"));
		Assert.assertTrue(AddToBagButton.isDisplayed());
	}

	//post condition annotation
	@AfterMethod
	public void logout()
	{
		driver.quit();
	}
}
