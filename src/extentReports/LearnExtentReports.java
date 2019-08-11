package extentReports;

import java.io.File;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LearnExtentReports 

{
	ExtentReports extent;
	ExtentTest test;
	
	
	@BeforeTest
	public void startReport()
	{
		extent =new ExtentReports(System.getProperty("user.dir")+"/test-output/STMTreport.html",true);
		extent.addSystemInfo("Hostname", "testing");
		extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));		
	}
	@Test
	public void demoreportpass()
	{
		test=extent.startTest("demoreportpass");
		Assert.assertTrue(true);
		test.log(LogStatus.PASS, "asser pass condition is true");
	}
	@Test
	public void demoreportfail()
	{
		test=extent.startTest("demoreportfail");
		Assert.assertTrue(false);
		test.log(LogStatus.PASS, "asser pass condition is false");
	}
		
	@AfterMethod
	public void getResult(ITestResult result)
	{
		if(result.getStatus()==ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, result.getThrowable());
			
		}
		else if(result.getStatus()==ITestResult.SKIP)
		{
			test.log(LogStatus.SKIP, "test case skipped" + result.getName());
		}
		
	   extent.close();
	}
	
	
	@AfterTest 
	public void endreport()
	{
		extent.flush();
		extent.close();
	}
}
