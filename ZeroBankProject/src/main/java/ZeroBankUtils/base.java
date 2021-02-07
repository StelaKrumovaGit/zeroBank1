package ZeroBankUtils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import Pages.AccountSummaryPage;
import Pages.LoginPage;
import Pages.SignInPage;

public class base {
	public static final String BROWSER = "chrome";
	public static String baseUrl;
	public static WebElement element;
	public static WebDriver driver;
	public static final int TIMEOUT = 10;
	public static final int PAGE_TIMEOUT = 45;
	public static final int SCRIPT_TIMEOUT = 45;
	public static String screenshotName;
	public static Robot robot;
	public static Actions actions;
	public static JavascriptExecutor js;
	static long startTime;
	static long endTime;
	/**Pages */
	protected SignInPage SignInPage;
	protected LoginPage LoginPage;
	protected AccountSummaryPage AccountSummaryPage;

	public static WebDriver initialise(String browser) {

		/** create FF profile and Chrome op[tions objects */
		FirefoxProfile firefoxProfile = new FirefoxProfile();
		ChromeOptions chromeOptions = new ChromeOptions();

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\executables\\chromedriver.exe");

			chromeOptions.addArguments("--disable-infobars");
			chromeOptions.addArguments("--window-size=1920,1080");
			chromeOptions.addArguments("--ignore-certificate-errors");
			chromeOptions.addArguments("--disable-default-apps");
			chromeOptions.addArguments("--disable-popup-blocking");
			chromeOptions.addArguments("--incognito");

			driver = new ChromeDriver(chromeOptions);

		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\executables\\geckodriver.exe");
			firefoxProfile.setPreference("permissions.default.stylesheet", 2);
			firefoxProfile.setPreference("permissions.default.image", 2);
			firefoxProfile.setPreference("dom.ipc.plugins.enabled.libflashplayer.so", false);
			firefoxProfile.setPreference("geo.enabled", false);

			driver = new FirefoxDriver(firefoxProfile);
		}
		/* set amount of total time for search for element */
		driver.manage().timeouts().implicitlyWait(TIMEOUT, TimeUnit.SECONDS);
		/* set amount of time to page load to complete before trow error */
		driver.manage().timeouts().pageLoadTimeout(PAGE_TIMEOUT, TimeUnit.SECONDS);
		/*
		 * set amount of time to wait for a script to finish execution before throwing
		 * error
		 */
		driver.manage().timeouts().setScriptTimeout(SCRIPT_TIMEOUT, TimeUnit.SECONDS);

		driver.manage().window().maximize();

		return driver;

	}

	public static void openUrl(String url) {
		driver.get(url);
		Reporter.log("Successfully opened url '" + url + "'");
	}

	public static void assertElementIsDisplayed(WebElement element) {
		Assert.assertTrue(element.isDisplayed());

	}

	/* Refresh Page */
	public static void refresh() {
		driver.navigate().refresh();

	}

	public static void jSClick(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);
		js = (JavascriptExecutor) driver;
		try {
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
			js.executeScript("arguments[0].click();", element);
			// element.click();
			// Reporter.log("Successfully click on element " + element.getText());
		} catch (StaleElementReferenceException e) {

			e.printStackTrace();

		}
	}

	/*
	 * WebElement returns element based on different locator
	 */
	public static WebElement returnElement(String locatorType, String locatorPath) {

		WebDriverWait wait = new WebDriverWait(driver, TIMEOUT);

		switch (locatorType.toLowerCase()) {
		case "id":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(locatorPath))));
		// driver.findElement(By.id(locatorPath));
			
		case "xpath":
			return driver.findElement(By.xpath(locatorPath));

		case "name":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(locatorPath))));
		// driver.findElement(By.name(locatorPath));

		case "classname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className(locatorPath))));
		// driver.findElement(By.className(locatorPath));

		case "cssselector":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(locatorPath))));
		// driver.findElement(By.cssSelector(locatorPath));

		case "linktext":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(locatorPath))));
		// driver.findElement(By.linkText(locatorPath));

		case "tagname":
			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.tagName(locatorPath))));
		// driver.findElement(By.tagName(locatorPath));

		default:
			throw new RuntimeException("Unknown locator " + locatorType + " : " + locatorPath);
		}

	}

	/* findElement using 'returnElement' and verify is Displayed */
	public static WebElement findElement(String locatorType, String locatorPath) {

		element = returnElement(locatorType, locatorPath);
		element.isDisplayed();

		return element;
	}

	/*  Method for finding Fields and Highlight them before enter a value  */
	public static WebElement findFieldAndSentKeys(WebElement element, String value) {
		element.isDisplayed();
		HighLightElement(element);
		element.sendKeys(value);
		Reporter.log(" Enter value : " + value);
		return element;
	}
	/*Simulate Key-Tab prese/release event - using Robot class*/
	public static void keyPressAndReleaseTab() {
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.keyPress(KeyEvent.VK_TAB); // here we simulate PRESS a TAB
		robot.keyRelease(KeyEvent.VK_TAB); // here we simulate RELEASE a TAB
	}

	/*
	 * Highlights the fileds in yellow color apply for the filed that is under
	 * typing
	 */
	public static void HighLightElement(WebElement element) {

		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;')", element);
		try {
			Thread.sleep(10);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px white')", element);
	}
/* method for scroll down*/
	public static void scrollDown(WebElement Element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);

	}
	/* method for click on WebElement using JavascriptExecutor*/
	public static void JSclickOnWebElement(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}

	public static String ReportNGscreenCapture() throws IOException {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		zoomOutPage(50);
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".png";
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String Path = System.getProperty("user.dir") + "\\screenshots\\" + screenshotName;

		File screenshotName = new File(Path);

		FileUtils.copyFile(scrFile, screenshotName);
		Reporter.log("<a href=" + screenshotName + "></a>");
		Reporter.log("<br>  <img src='" + screenshotName + "' height='200' width='200' /><br>");

		return Path;

	}

	/* Work on Dropdowns **/
	public static void select(WebElement select1, String value) {

		Select select = new Select(select1);
		select.selectByVisibleText(value);
		Reporter.log("Selecting from dropdown value as : " + value);

	}

	/* Assert URL */
	public static void URLverify(String expected) {

		String actual = driver.getCurrentUrl();
		Assert.assertTrue(expected.equalsIgnoreCase(actual));

		Reporter.log("URL is Verified");
	}

	/* Page title assert */
	public static void pageTitleVerify(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		Reporter.log("Page title  is Verified");

	}
	/*method performing Page zoom out*/
	public static void zoomOutPage(int percent) {
		JavascriptExecutor js = (JavascriptExecutor) base.driver;
		js.executeScript("document.body.style.zoom='" + percent + "%'");
	}

	public static void verifyEqualTexts(WebElement element, String expected) {

		Assert.assertEquals(element.getText(), expected);
		Reporter.log("Text : " + element.getText() + " - is Verified");
		System.out.println("Text : " + element.getText() + " -  is Verified");

	}
	/* method for getting a Text from an WebElement*/
	public static String getElementText(WebElement element) {

		return element.getText();
	}
	/*FileWriter method - can write down in a .txt particular data getted from an WebElement*/
	public static String FileWriter(WebElement ele,String filePath) throws IOException {
		
		File f1 = new File(filePath);
		java.io.FileWriter fw = new java.io.FileWriter(f1);
		String text = ele.getText();
		Reporter.log(text);
//		System.out.println(text);
		fw.write(text);
		fw.close();
		return text;

	}

	public static String FileWriter(String filePath, String chosenString) throws IOException {
		File f1 = new File(filePath);
		java.io.FileWriter fw = new java.io.FileWriter(f1);

		Reporter.log("Credit card No :" + chosenString);
		System.out.println("Credit card No : "+chosenString);
		fw.write(chosenString);
		fw.close();
		return chosenString;

	}

	/* check all checkBoxSelection method - capturing them by common tagName */
	public void TermsAndConditionsclickAllCheckBoxes(String tagname, String attribute, String text) {
		List<WebElement> list = driver.findElements(By.tagName(tagname));
		int size = list.size();
		for (int i = 0; i < size; i++) {
			list.get(i).getText();
		}
		for (WebElement webElement : list) {
			if (webElement.getAttribute(attribute).equals(text)) {
				base.jSClick(webElement);
			}
		}
	}
	/* File Reader Method - read data from .txt*/
	public static String getStoredString(String filePath, int chars) throws IOException {

		File f1 = new File(filePath);
		FileReader fr = new FileReader(f1);
		char[] chras = new char[chars];
		fr.read(chras);
		String s = new String(chras);
		System.out.println("The stored String is  : " + s);
		fr.close();
		return s;

	}
	
	

	@BeforeClass  
	public void beforeClass() {
		initialise(BROWSER);
		Reporter.log(BROWSER + " is Initialised");
	}

	@AfterClass 
	public void afterTest() {

		if (driver != null) {
			driver.quit();
		}
	}

	@BeforeSuite
	public void beforeSuite() {
		Reporter.log("");
		startTime = System.nanoTime();

	}

	@AfterSuite
	public void afterSuite() {
		if (driver != null) {
			driver.quit();
		}
		Reporter.log("Driver quit");
		endTime = System.nanoTime();
		long dur = endTime - startTime;
		long convert = TimeUnit.SECONDS.convert(dur, TimeUnit.NANOSECONDS);
		Reporter.log("Duration of Suite is : " + convert + " sec.");
		System.out.println("Duration of Suite is : " + convert + " sec.");

	}

}
