package com.testscenarios;

import java.io.FileInputStream;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.Locators;

public class ClassTemplate extends CommonFunctions {

	Locators loc = new Locators();

	@Parameters("browserName")
	@BeforeClass
	public void beforeClass(@Optional("chrome") String browserName) {
		if (browserName.equalsIgnoreCase("Chrome")) {
			openChromeBrowser();
		} else if (browserName.equalsIgnoreCase("Firefox")) {
			openFirefoxBrowser();
		} else if (browserName.equalsIgnoreCase("edge")) {
			openEdgeBrowser();
		} else {
			System.out.println("Please give valid browser name with in Edge/FireFox/Chrome");
		}
	}

	@Test
	public void f() throws Exception {
		FileInputStream propertyFilePath = new FileInputStream("./src/test/resources/testdata/inputs.properties");
		// load the proprty file data to reference
		prop.load(propertyFilePath);

	}

	// This method will execute each and every @test method execution done
	@AfterMethod
	public void takescreenshotOfEachMethod() throws Exception {
		// Here type class name as screenshot file name
		takescreenshotWithTimeStamp("ClassName");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
