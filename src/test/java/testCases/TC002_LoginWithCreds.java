package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginWithCreds extends BaseClass {
	
	@Test(groups = {"sanity","master"})
	public void login_with_valid_creds() {
		
		try {
			logger.info("**** Starting the test case ****");
			HomePage home = new HomePage(driver);
			home.clickMyacc();
			home.clickLogin();
			LoginPage login = new LoginPage(driver);
			login.inputEmailId(p.getProperty("email"));
			login.inputPwd(p.getProperty("password"));
			login.clickLogin();
			//my account
			MyAccountPage acc = new MyAccountPage(driver);
			boolean msg = acc.accCreatedMsg();
			Assert.assertEquals(msg, true,"login failed");
			//Assert.assertTrue(msg);
					
		}catch(Exception e) {
			logger.error("Test failed");
			logger.debug("Debug logs");
			Assert.fail();
		}
	
		}
}
