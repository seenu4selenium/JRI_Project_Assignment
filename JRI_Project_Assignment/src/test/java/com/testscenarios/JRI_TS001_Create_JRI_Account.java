package com.testscenarios;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.utilities.CommonFunctions;

import io.github.bonigarcia.wdm.WebDriverManager;
import objectRepository.Locators;

public class JRI_TS001_Create_JRI_Account extends CommonFunctions {

	// classname refName = new classname();
	Locators loc = new Locators();

	//Invoke the JRI Home page
	@Test(description = "Invoke the JRI Home page")
	public void TC_001() {
		openChromeBrowser();
		driver.get("https://www.justrechargeit.com/");
	}

	@Test
	public void TC_002() {
		TC_001();
		clickByAnyLocator(loc.HomePage_CreateNewAccount_Link);
		implicitWait(10);
		// validate whether the SignUp page has displayed or not?
		// get the URL of page and validate it
		String expectedURL = "https://www.justrechargeit.com/SignUp.aspx";
		validateWebElement(getCurrentpageURL(), expectedURL);
	}

	@Test
	public void TC_003() {
		TC_002();
		clickByAnyLocator(loc.CreateNewAccountPage_CreateAccount_Button);
		implicitWait(50);
		validateWebElement(getTextByAnyLocator(loc.CreateNewAccountPage_Name_ErroeMSG), "Enter your name");
	}

	@Test
	public void TC_004() {
		TC_003();
		sendKeysByAnyLocator(loc.CreateNewAccountPage_Name_EditBox, "test");
		clickByAnyLocator(loc.CreateNewAccountPage_CreateAccount_Button);
		implicitWait(50);
		validateWebElement(getTextByAnyLocator(loc.CreateNewAccountPage_MobileNo_ErroeMSG), "Enter mobile no.");
	}

	@Test
	public void TC_005() {
		TC_004();
		sendKeysByAnyLocator(loc.CreateNewAccountPage_Name_EditBox, "test");
		sendKeysByAnyLocator(loc.CreateNewAccountPage_MobileNo_Editbox, "9685741230");
		clickByAnyLocator(loc.CreateNewAccountPage_CreateAccount_Button);
		implicitWait(50);
		validateWebElement(getTextByAnyLocator(loc.CreateNewAccountPage_Email_ErroeMSG), "Enter your email id");
	}

	@Test
	public void TC_006() {
		TC_005();
		sendKeysByAnyLocator(loc.CreateNewAccountPage_Email_Editbox, "test123@gmaiol.cmm");
		clickByAnyLocator(loc.CreateNewAccountPage_CreateAccount_Button);
		implicitWait(50);
		validateWebElement(getTextByAnyLocator(loc.CreateNewAccountPage_Password_ErroeMSG), "Enter your password");

	}

	@Test
	public void TC_007() {
		TC_006();
		sendKeysByAnyLocator(loc.CreateNewAccountPage_Password_Editbox, "123456");
		clickByAnyLocator(loc.CreateNewAccountPage_CreateAccount_Button);
		implicitWait(50);
		validateWebElement(getTextByAnyLocator(loc.CreateNewAccountPage_Terms_ErroeMSG),
				"Please agree to the terms & conditions");

	}

}
