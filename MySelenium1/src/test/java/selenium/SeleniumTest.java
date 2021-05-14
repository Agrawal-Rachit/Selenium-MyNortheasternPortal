package selenium;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class SeleniumTest {

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		File scrFile = ((TakesScreenshot) webdriver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(fileWithPath));
	}
	public static WebDriver driver;


	@Test
	public void Scenario3Test() throws Exception {
		// -------------------Scenario 3 : Browse class--------------------------
		String filePath2 = "C:\\Users\\rachi\\OneDrive\\Desktop\\Software Quality Control"
				+ "\\selenium_project\\MySelenium1\\screenshots\\scenario3\\screenshot";
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://my.northeastern.edu");
		driver.manage().window().maximize();
		// take screen shots
		takeSnapShot(driver, filePath2 + "-Welcome.png");

		// Login
		driver.findElement(By.linkText("Go To Login")).click();
		takeSnapShot(driver, filePath2 + "-Login.png");
		Thread.sleep(1000);

		// User name & password
		driver.findElement(By.id("username")).sendKeys("agrawal.rac");
		driver.findElement(By.id("password")).sendKeys("Welcome@123");
		takeSnapShot(driver, filePath2 + "-Username&Password.png");
		Thread.sleep(1000);
		driver.findElement(By.name("_eventId_proceed")).click();

		// Duo Authentication
		takeSnapShot(driver, filePath2 + "-Duo.png");
		Thread.sleep(5000);

		// Open course registration page
		//driver.findElement(By.xpath("//*[@id=\"layout_2\"]/a")).click();
		driver.findElement(By.cssSelector("#layout_2 > a")).click();
		takeSnapShot(driver, filePath2 + "-Service&Link.png");

		// go to course registration
		driver.findElement(By.xpath(
				"//*[@id=\"portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_VGn3ZknJvwnV\"]/div/div/div/div[5]/div[2]/div/div[1]/div[2]/a"))
				.click();
		takeSnapShot(driver, filePath2 + "-courseRegistration.png");
		// control the new window tab
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		takeSnapShot(driver, filePath2 + "-courseReg.png");
		// Click on browse class
		driver.findElement(By.xpath("//a[contains(@href, 'mode=search')]")).click();
		takeSnapShot(driver, filePath2 + "-browse.png");
		Thread.sleep(1000);

		// select term
		driver.findElement(By.id("select2-chosen-1")).click();
		takeSnapShot(driver, filePath2 + "-before selectTerm.png");
		Thread.sleep(1000);
		driver.findElement(By.id("s2id_autogen1_search")).sendKeys("Spring 2021 Semester");
		Thread.sleep(1000);
		driver.findElement(By.id("202130")).click();
		takeSnapShot(driver, filePath2 + "-after selectTerm.png");
		driver.findElement(By.id("term-go")).click();
		takeSnapShot(driver, filePath2 + "-searchPage.png");

		// add IS and CSYE
		driver.findElement(By.xpath("//*[@id=\"s2id_txt_subject\"]/ul")).click();
		driver.findElement(By.id("s2id_autogen1")).sendKeys("Information Systems Program");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("#INFO")).click();
		// driver.findElement(By.id("INFO")).click();
		takeSnapShot(driver, filePath2 + "-addSubject1.png");
		driver.findElement(By.id("s2id_autogen1")).sendKeys("Computer Systems Engineering");
		Thread.sleep(1000);
		driver.findElement(By.id("CSYE")).click();
		takeSnapShot(driver, filePath2 + "-addSubject2.png");
		driver.findElement(By.id("search-go")).click();
		Thread.sleep(1000);
		takeSnapShot(driver, filePath2 + "-browseClass.png");

		driver.close();

	}

	@Test
	public void Scenario4Test() throws Exception {
		// -------------------Scenario 4 : Bookstore--------------------------
		String filePath = "C:\\Users\\rachi\\OneDrive\\Desktop\\Software Quality Control\\selenium_project\\MySelenium1\\screenshots\\scenario4\\screenshot";

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// open website
		// back to parent window
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		Thread.sleep(1000);
		driver.get("https://northeastern.bncollege.com/shop/northeastern/home");

		Thread.sleep(1000);
		takeSnapShot(driver, filePath + "-Welcome.png");

		driver.findElement(By.cssSelector("#login_user > div > span.guestUser > a")).click();
		Thread.sleep(3000);
		// login process
		driver.findElement(By.cssSelector("#logonId")).sendKeys("agrawal.rac@northeastern.edu");
		Thread.sleep(3000);
		js.executeScript("window.scrollBy(0,1000)");
		driver.findElement(By.cssSelector("#logonPassword")).sendKeys("Welcome@123");
		Thread.sleep(3000);
		takeSnapShot(driver, filePath + "-login.png");
		driver.findElement(By.name("login")).click();
		Thread.sleep(3000);
		// searching an item
		driver.findElement(By.cssSelector("#topNavSearch")).sendKeys("accessories");
		Thread.sleep(3000);
		takeSnapShot(driver, filePath + "-search.png");
		driver.findElement(By.cssSelector("#searchbutton")).click();

		Thread.sleep(3000);
		// item selection
		js.executeScript("window.scrollBy(0,1000)");
		takeSnapShot(driver, filePath + "-item_selection.png");
		driver.findElement(By.cssSelector("#foo1 > li:nth-child(5) > h3 > a")).click();
		Thread.sleep(3000);
		// adding it to cart
		js.executeScript("window.scrollBy(0,200)");
		takeSnapShot(driver, filePath + "-AddToCart.png");
		driver.findElement(By.cssSelector("#addToCartId")).click();
		Thread.sleep(3000);
		// view cart
		takeSnapShot(driver, filePath + "-ViewCart.png");
		driver.findElement(By.cssSelector(
				"body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.ui-front.ajaxDialogProductAdd.ui-dialog-buttons.ui-draggable.modal-border > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button.view-cart-class.ui-button.ui-widget.ui-state-default.ui-corner-all.ui-button-text-only"))
				.click();
		js.executeScript("window.scrollBy(0,500)");
		Thread.sleep(2000);
		takeSnapShot(driver, filePath + "-ViewCartItem.png");
		driver.close();
	}

	@Test
	public void Scenario5Test() throws Exception {
		String filePath1 = "C:\\Users\\rachi\\OneDrive\\Desktop\\Software Quality Control\\selenium_project\\MySelenium1\\screenshots\\scenario5\\screenshot";
		// Open website
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://my.northeastern.edu");
		// take screen shots
		takeSnapShot(driver, filePath1 + "-Welcome.png");

		// Login
		driver.findElement(By.linkText("Go To Login")).click();
		takeSnapShot(driver, filePath1 + "-Login.png");
		Thread.sleep(1000);

		// User name & password
		driver.findElement(By.id("username")).sendKeys("agrawal.rac");
		driver.findElement(By.id("password")).sendKeys("Welcome@123");
		takeSnapShot(driver, filePath1 + "-Username&Password.png");
		Thread.sleep(1000);
		driver.findElement(By.name("_eventId_proceed")).click();

		// Duo Authentication
		takeSnapShot(driver, filePath1 + "-Duo.png");
		Thread.sleep(5000);

		// Open course registration page
		driver.findElement(By.xpath("//*[@id=\"layout_2\"]/a")).click();
		takeSnapShot(driver, filePath1 + "-Service&Link.png");
		driver.findElement(By.xpath(
				"//*[@id=\"portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_VGn3ZknJvwnV\"]/div/div/div/div[5]/div[2]/div/div[1]/div[2]/a"))
				.click();
		takeSnapShot(driver, filePath1 + "-courseRegistration.png");
		Thread.sleep(10000);

		// Switch Windowhandle
		for (String windowHandle : driver.getWindowHandles()) {
			driver.switchTo().window(windowHandle);
		}
		takeSnapShot(driver, filePath1 + "-Registration.png");
		Thread.sleep(2000);

		// Plan Ahead
		driver.findElement(By.xpath("//*[@id=\"worksheetPlanner\"]")).click();
		takeSnapShot(driver, filePath1 + "-PlanAhead.png");
		Thread.sleep(2000);

		// Select Term
		driver.findElement(By.xpath("//*[@id=\"s2id_txt_term\"]")).click();
		takeSnapShot(driver, filePath1 + "-SelectTerm.png");
		Thread.sleep(1000);

		driver.findElement(By.id("s2id_autogen1_search")).sendKeys("Spring 2021 Semester");
		Thread.sleep(1000);
		driver.findElement(By.id("202130")).click();
		takeSnapShot(driver, filePath1 + "-Spring2021Semester.png");
		Thread.sleep(1000);
		driver.findElement(By.id("term-go")).click();
		Thread.sleep(1000);

		// Create Plan
		driver.findElement(By.id("createPlan")).click();
		takeSnapShot(driver, filePath1 + "-CreatePlan.png");
		Thread.sleep(2000);

		// Add courses
		driver.findElement(By.xpath("//*[@id=\"txt_courseNumber\"]")).sendKeys("6255");
		takeSnapShot(driver, filePath1 + "-CourseId.png");
		driver.findElement(By.id("search-go")).click();
		takeSnapShot(driver, filePath1 + "-SearchClass.png");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[1]/td[6]/div/button[2]")).click();
		takeSnapShot(driver, filePath1 + "-AddClass.png");
		Thread.sleep(2000);

		// Save plan
		driver.findElement(By.id("saveButton")).click();
		driver.findElement(By.id("txt_planDesc")).sendKeys("Secnario 5 Plan");
		takeSnapShot(driver, filePath1 + "-SavePlan.png");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector(
				"body > div.ui-dialog.ui-widget.ui-widget-content.ui-corner-all.course-details-dialog.ui-draggable > div.ui-dialog-buttonpane.ui-widget-content.ui-helper-clearfix > div > button:nth-child(2)"))
				.click();
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#breadcrumbHeader > a:nth-child(7)")).click();
		takeSnapShot(driver, filePath1 + "-ViewSavedPlan.png");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("#user")).click();
		driver.findElement(By.cssSelector("#signOut > span")).click();
		driver.close();


	}

}