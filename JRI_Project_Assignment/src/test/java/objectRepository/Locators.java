package objectRepository;

import org.openqa.selenium.By;

public class Locators {
//PageName_ElementName_ElementType

	// Here QA will store all the application locators!
		// PageName_WebElementName_elementType

		// HomePage
		public final By HomePage_CreateNewAccount_Link = By.id("signup-link9");

		// CreateNewAccountPage
		public final By CreateNewAccountPage_CreateAccount_Button = By.id("imgbtnSubmit");
		
		public final By CreateNewAccountPage_Name_ErroeMSG = By.id("nameTD");
		public final By CreateNewAccountPage_Name_EditBox = By.name("signup_name");
		
		public final By CreateNewAccountPage_MobileNo_ErroeMSG = By.id("mobilenoTD");
		public final By CreateNewAccountPage_MobileNo_Editbox = By.id("signup_mobileno");
		

		public final By CreateNewAccountPage_Email_ErroeMSG = By.id("emailTD");
		public final By CreateNewAccountPage_Email_Editbox = By.id("signup_email");

		public final By CreateNewAccountPage_Password_ErroeMSG = By.id("passwordTD");
		public final By CreateNewAccountPage_Password_Editbox = By.id("signup_password");
		
		public final By CreateNewAccountPage_Terms_ErroeMSG = By.id("tdcondition");

		
		

}
