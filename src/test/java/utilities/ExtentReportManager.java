package utilities;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener
{
	public ExtentSparkReporter extentsparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
		
	public void onStart(ITestContext Context)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		
		extentsparkreporter=new ExtentSparkReporter(".\\reports\\"+repName);//specify location of the report
		extentsparkreporter.config().setDocumentTitle("Automation Testing");
		extentsparkreporter.config().setReportName("Functional Testing");
		extentsparkreporter.config().setTheme(Theme.STANDARD);
				

		extent=new ExtentReports();
		extent.attachReporter(extentsparkreporter);
		
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user","Vidya D");
		extent.setSystemInfo("os","Windows");
		extent.setSystemInfo("Browser","Chrome");
		
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.PASS,"Test case PASSED is "+ result.getName()); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test case FAILED is "+result.getName());
		test.log(Status.FAIL, "Test case FAILED is "+result.getThrowable());
		
		try {
			String impPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(impPath);
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		
		
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test=extent.createTest(result.getName()); // create new entry in the report
		test.log(Status.SKIP,"Test case SKIPPED is "+result.getName());
	}
	
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
