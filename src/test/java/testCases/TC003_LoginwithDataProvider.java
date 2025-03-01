package testCases;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginwithDataProvider extends BaseClass {
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = {"master"})
public void login_with_data_providers(String email, String pwd, String res) {
		
		try {
			logger.info("**** Starting the test case ****");
			HomePage home = new HomePage(driver);
			home.clickMyacc();
			home.clickLogin();
			LoginPage login = new LoginPage(driver);
			login.inputEmailId(email);
			login.inputPwd(pwd);
			login.clickLogin();
			//my account
			MyAccountPage acc = new MyAccountPage(driver);
			boolean msg = acc.accCreatedMsg();
			
			if(res.equals("valid")) {
				if(msg == true) {
					Assert.assertTrue(true);
					acc.clickLogout();
				}
			
			else {
				Assert.assertTrue(false);
				
				
			}
			}
			
			if(res.equals("invalid"))
				if(msg == true) {
					Assert.assertTrue(false);
					acc.clickLogout();
				}
			
			else {
				Assert.assertTrue(true);
			}
		}catch(Exception e) {
			Assert.fail();
		}
}
			
			
	
}