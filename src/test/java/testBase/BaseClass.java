package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;

	@BeforeClass(groups = {"sanity","regression","master","DataDriven"})
	@Parameters({"os","browser"})
	public void setUp( String os,String br) throws IOException {
		
		FileReader file = new FileReader("src/test/resources/config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());
		
		if (p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities cap = new DesiredCapabilities();
			  
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN10);
			}
			else if (os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else if(os.equalsIgnoreCase("mac")){
				cap.setPlatform(Platform.MAC);
			}
			else {
				System.out.println("no such os");
				return;
			}
			
			switch (br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default : System.out.println("no such browser"); return;
			}
			
			 driver = new RemoteWebDriver( new URL("http://localhost:4444/wd/hub"),cap);
			 
		}
		
		if (p.getProperty("execution_env").equalsIgnoreCase("local")){
			switch (br.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver = new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("incorrect browser entry"); return;
			}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appUrl"));
		String appUrl = p.getProperty("appUrl");
		System.out.println("App URL from config: " + appUrl);
		driver.manage().window().maximize();
	}

	@AfterClass(groups = {"sanity","regression","master","DataDriven"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generated_string = RandomStringUtils.randomAlphabetic(5);
		return generated_string;
		}

		public String randomNumber() {
		String generated_number = RandomStringUtils.randomNumeric(10);
		return generated_number;
		}	

		public String randomAlphaNum() {
		String generated_string = RandomStringUtils.randomAlphabetic(3);
		String generated_number = RandomStringUtils.randomNumeric(3);
		String pwd = generated_string+generated_number;
		return pwd;
		}
		
		public String captureScreen(String tname) throws IOException{
			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
			File targetFile = new File(targetFilePath);
			
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
