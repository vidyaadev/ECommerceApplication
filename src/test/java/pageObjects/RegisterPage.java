package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {
	
	
 public RegisterPage(WebDriver driver) {
	 super(driver);
 }
 

@FindBy(id = "input-firstname")
WebElement firstName;
 
@FindBy(id = "input-lastname")
WebElement lastName;

@FindBy(id = "input-email")
WebElement emailid;

@FindBy(id = "input-telephone")
WebElement telephone;

@FindBy(id = "input-password")
WebElement pwd;

@FindBy(id = "input-confirm")
WebElement confPwd;

@FindBy(xpath = "//input[@name='agree']")
WebElement chkbox;

@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement accCreatedMsg;

@FindBy(xpath = "//input[@value='Continue']")
WebElement regLogin;

public void inputFirstName(String fname) {
	firstName.sendKeys(fname);
}

public void inputLastname(String lname) {
	lastName.sendKeys(lname);
}

public void inputEmail(String email) {
	emailid.sendKeys(email);
}

public void inputNo(String no) {
	telephone.sendKeys(no);
}

public void inputPwd(String password) {
	pwd.sendKeys(password);
}

public void inputConfPwd(String pwd) {
	confPwd.sendKeys(pwd);
}

public void clickCheckBox() {
	chkbox.click();
}

public void clickOnContinue() {
	regLogin.click();
}
 
 
public String getAccCreatedMsg() {
	try {
	return (accCreatedMsg.getText());
	}catch(Exception e){
		return (e.getMessage());
	}
	
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
