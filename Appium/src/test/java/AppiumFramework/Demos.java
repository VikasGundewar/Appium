package AppiumFramework;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

import java.io.IOException;


public class Demos extends Capability {

static AndroidDriver<AndroidElement> driver;
	
	@BeforeTest
	public void bt() throws IOException, InterruptedException
	{
		//driver = capabilities(appPackage,appActivity,deviceName,platformName,chromeExecutable);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Runtime.getRuntime().exec("taskkill /F /IM node.exe");
		Thread.sleep(5000);
	}
	
	@Test
	public void testcase1() throws InterruptedException 
	{
		//i want to select the radio button
		driver.findElement(By.xpath("//*[@text='Male']")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vikas");
		//Selected dropdown
		driver.findElement(By.className("android.widget.Spinner")).click();
		Thread.sleep(3000);
		//selected India from dropdown
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"))").click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		//String ermsg = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
		//System.out.println(ermsg);
		
		//int links = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		//System.out.println(links);
		//want to scroll to an element and click on add to cart for that element
		//want to scroll till air jordan 9 retro and click on add to cart
		//driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Air Jordan 9 Retro\"))").click();
		//driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
		driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\"com.androidsample.generalstore:id/rvProductList\").instance(0)).scrollIntoView(new UiSelector().textMatches(\"Air Jordan 9 Retro\").instance(0))");
		int links = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
		for(int i=0;i<links;i++) {
			String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
			if(productName.equalsIgnoreCase("Air Jordan 9 Retro"))
			{
				driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
				break;
			}
		}
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		String cartProductName= driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
		String productName="Air Jordan 9 Retro";
		Assert.assertEquals(productName, cartProductName);
	}
	@Test(enabled=false)
	public void testcase2() throws InterruptedException, IOException
	{
		service=startServer();
		driver = capabilities(appPackage,appActivity,deviceName,platformName,chromeExecutable);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	//i want to select the radio button
			driver.findElement(By.xpath("//*[@text='Male']")).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Vikas");
			//Selected dropdown
			driver.findElement(By.className("android.widget.Spinner")).click();
			Thread.sleep(3000);
			//selected India from dropdown
			driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"))").click();
			driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
			driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
			driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
			Thread.sleep(4000);
			String amount1 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(0).getText();
			//removing dollar sign
			amount1 = amount1.substring(1);
			double amount1Value = Double.parseDouble(amount1);
			
			
			
			String amount2 = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice")).get(1).getText();
			amount2 = amount2.substring(1);
			double amount2Value = Double.parseDouble(amount2);
			double TotalValue= amount1Value + amount2Value;
			System.out.println(TotalValue);
			
			//String FinalAmount = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLb1")).getText();
			//FinalAmount=FinalAmount.substring(1);
			//Double FinalAmountValue=Double.parseDouble(FinalAmount);
			//Assert.assertEquals(FinalAmountValue,TotalValue);
			//Tap on the checkbox
			WebElement tap = driver.findElementByClassName("android.widget.CheckBox");
			TouchAction T = new TouchAction(driver);
			T.tap(tapOptions().withElement(element(tap))).perform();
			//longpress on terms and conditions
			WebElement LP= driver.findElement(By.id("com.androidsample.generalstore:id/termsButton"));
			T.longPress(longPressOptions().withElement(element(LP)).withDuration(ofSeconds(3))).release().perform();
			driver.findElement(By.id("android:id/button1")).click();
			driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
			//context is nothing but to know which context i am in at present
			Set<String> contextNames = driver.getContextHandles();
			for (String contextName : contextNames) {
			    System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
			}
			//This is to switch between native and web app in hybrid app
			driver.context("WEBVIEW_com.androidsample.generalstore");
			driver.findElement(By.name("q")).sendKeys("IBM");
			driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
			Thread.sleep(3000);
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			driver.context("NATIVE_APP");
	}
}

