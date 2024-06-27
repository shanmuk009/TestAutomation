package com.test.testUtilities;

//istener class used to genarate extent reports
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Reporter extends TestListenerAdapter {
	public ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;
	
	
	public void onStart(ITestContext testcontext) {
		
		String timeStamp=new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(new Date());
		String repName="Test-Report"+timeStamp+".html";
		spark=new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/"+repName);
		/*
		 * try {
		 * spark.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml"); }
		 * catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		 extent= new ExtentReports();
		 
		 extent.attachReporter(spark);
		 extent.setSystemInfo("hostname","localhost");
		 extent.setSystemInfo("Environment","QA");
		 extent.setSystemInfo("user","Shanmukh");
		 
		 
		 spark.config().setTheme(Theme.DARK);
		 spark.config().setDocumentTitle("Test Report");
		 spark.config().setEncoding("utf-8");
		 spark.config().setReportName("Functional auotomation Test Report");

		 
		
			/*
			 * test=extent.createTest("My Test"); test.log(Status.INFO,
			 * "launching browser"); test.fail("Test Case failed");
			 * 
			 * extent.flush();
			 */
		 }
	
	public void onTestSuccess(ITestResult tr) {
		test=extent.createTest(tr.getName());//create entry in the report
		test.log(Status.PASS,MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));//send the passed info
	}

	public void onTestFailure(ITestResult tr) {
		test=extent.createTest(tr.getName());//create entry in the report
		test.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));//send the passed info
		
		String screenShotPath=System.getProperty("user.dir")+"\\screenShots"+tr.getName()+".png";
		File f=new File(screenShotPath);
		
		if(f.exists()) {
			test.fail("screenshot is below:"+test.addScreenCaptureFromPath(screenShotPath));
		}
	}
	
	public void onTestSkipped(ITestResult tr) {
		test=extent.createTest(tr.getName());//create entry in the report
		test.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));//send the passed info
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
