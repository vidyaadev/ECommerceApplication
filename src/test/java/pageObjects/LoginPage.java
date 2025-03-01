package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
 public LoginPage(WebDriver driver){
	 super(driver);
 }
 
 @FindBy(xpath = "//input[@id='input-email']")
 WebElement emailId;
 
 @FindBy(xpath = "//input[@id='input-password']")
WebElement password;
 
 @FindBy(xpath = "//input[@value='Login']")
 WebElement loginBtn;
 

 
 public void inputEmailId(String email) {
	 emailId.sendKeys(email);
 }
 
 public void inputPwd(String pwd) {
	 password.sendKeys(pwd);
 }
 
 public void clickLogin() {
	 loginBtn.click();
 }
 

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
