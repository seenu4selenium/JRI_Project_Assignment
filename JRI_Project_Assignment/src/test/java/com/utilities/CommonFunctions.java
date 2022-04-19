package com.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions {

	public static WebDriver driver;
	public static Properties prop = new Properties();

	public void openChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	public void openFirefoxBrowser() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
	}

	public void openEdgeBrowser() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
	}

	public void openOperaBrowser() {
		WebDriverManager.operadriver().setup();
		driver = new OperaDriver();
		driver.manage().window().maximize();
	}

	public String getTextByAnyLocator(By Locator) {
		String elementText = driver.findElement(Locator).getText();
		System.out.println("elementText Text is: " + elementText);
		return elementText;
	}

	/***
	 * String actualValue String expectedValue
	 ***/
	public void validateWebElement(String actualValue, String expectedValue) {
		if (actualValue.equals(expectedValue)) {
			System.out.println("Webpage element has displayed as expected");
		} else {
			System.out.println("Webpage element is NOT displayed as expected");
		}
	}

	// implicit wait: wait for until webpage loading
	public void implicitWait(int TimeInMillySeconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeInMillySeconds));
	}

	public String getCurrentpageURL() {
		String url = driver.getCurrentUrl();
		return url;
	}

	/* Send key by using any locator */
	public void sendKeysByAnyLocator(By locator, String inputData) {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.clear();
				element.sendKeys(inputData);
			} else {
				System.out.println("Webelement is not enabled state on Screen,plz check");
			}
		} else {
			System.out.println("Webelement is not displayed on Screen,plz check");
		}
	}

	/* Send key by using any locator */
	public void clickByAnyLocator(By locator) {
		WebElement element = driver.findElement(locator);
		if (element.isDisplayed()) {
			if (element.isEnabled()) {
				element.click();
			} else {
				System.out.println("Webelement is not enabled state on Screen,plz check");
			}
		} else {
			System.out.println("Webelement is not displayed on Screen,plz check");
		}
	}

	/** time stamp ****/
	public String currentTimeStamp() {
		Date abc = new Date();
		DateFormat df = new SimpleDateFormat("yyyyMMMdd_HH_mm_ss");
		String timeStamp = df.format(abc);
		return timeStamp;
	}

	/**
	 * takescreenshot
	 * 
	 * @throws Exception
	 ****/

	public void takescreenshotWithTimeStamp(String nameOfTheFile) throws Exception {
		// take a screeshot: stored into RAM location
		File myscreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// move the screenshot to specific folder above path
		FileHandler.copy(myscreenshot,
				new File("C:\\Users\\nalla\\git\\5AMbatchFW\\HybridFramework_5amIST\\screeenshots\\" + nameOfTheFile
						+ currentTimeStamp() + ".PNG"));
	}

	/********* Read Data from Properties file *********************/
	// to get the data from Property file
	public void loaddata(String path) {
		File file = new File(path);
		FileInputStream fileInput = null;

		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getdata(String key) {
		String keyvlaue = null;
		try {
			keyvlaue = prop.getProperty(key);

		} catch (Exception e) {
			System.out.println("Error description: " + e.getStackTrace());
		}
		return keyvlaue;
	}

	/******************** Frames Handling *******************/

	public int iframeCount() {
		driver.switchTo().defaultContent();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int numberOfFrames = 0;
		numberOfFrames = Integer.parseInt(js.executeScript("return window.length").toString());// 3
		System.out.println("Number of iframes on the page are: " + numberOfFrames);
		return numberOfFrames;
	}

	public void switchToFrameByInt(int i) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(i);
	}

	public int loopAllFramesForElement(By locator) {

		int elementpresenceCount = 0;
		int loop = 0;
		int maxFramaecount = iframeCount();// 10
		// if given locater has present on webpage, then the element size would be '1'
		// else '0'
		elementpresenceCount = driver.findElements(locator).size();// 0
		while (elementpresenceCount == 0 && loop < maxFramaecount) {
			try {
				switchToFrameByInt(loop);
				elementpresenceCount = driver.findElements(locator).size();// 3
				System.out.println("Try LoopAllframesAndReturnWebEL : " + loop + "; ElementpresenceCount: "
						+ elementpresenceCount);
				if (elementpresenceCount > 0 || loop > maxFramaecount) {
					break;
				}
			} catch (Exception ex) {
				System.out.println("Catch LoopAllframesAndReturnWebEL Old: " + loop + "; " + ex);
			}
			loop++;// 1
		}
		return elementpresenceCount;
	}

}
