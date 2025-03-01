package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {


	
@Test(groups = {"regression","master"})
public void new_registration() {
	
	try {
	
	logger.info("**** Starting the test case ****");
	HomePage home = new HomePage(driver);
	RegisterPage reg = new RegisterPage(driver);
	home.clickMyacc();
	logger.info("*** clicked on my account ***");
	home.clickRegister();
	logger.info("*** Providing customer info ***");
	reg.inputFirstName(randomString().toUpperCase());
	reg.inputLastname(randomString());
	reg.inputEmail(randomString()+"@gmail.com");
	reg.inputNo(randomNumber());
	String password = randomAlphaNum();
	reg.inputPwd(password);
	reg.inputConfPwd(password);
	reg.clickCheckBox();
	reg.clickOnContinue();
	logger.info("*** validating customer info ***");
	String confMsg = reg.getAccCreatedMsg();
	Assert.assertEquals(confMsg, "Your Account Has Been Created!");
	}
	catch(Exception e) {
		logger.error("Test failed");
		logger.debug("Debug logs");
		Assert.fail();
	}
	logger.info("Test case passed");
	
	/*
	reg.inputFirstName("Vidya");
	reg.inputLastname("D");
	reg.inputEmail("vidyad12345@gmail.com");
	reg.inputNo("1234567890");
	reg.inputPwd("1234");
	reg.inputConfPwd("1234");
	reg.clickCheckBox();
	reg.clickOnContinue();
	String confMsg = reg.getAccCreatedMsg();
	Assert.assertEquals(confMsg, "Your Account Has Been Created!");
	*/
}	



	
	
	
	
	
	
	
	
	
}
