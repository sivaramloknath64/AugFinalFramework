package Aug.Utils;

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

import Aug.Tests.BaseTest;

public class Testlisteners implements ITestListener {

	private static ExtentReports extentReport;
	private static ThreadLocal<ExtentTest> extenttest = new ThreadLocal<ExtentTest>();

	@Override
	public void onStart(ITestContext context) {

		String timeStamp = new SimpleDateFormat("YYYY_MM_DD_HHMMSS").format(new Date());
		String path = System.getProperty("user.dir") + "\\Reports\\TestReport" + timeStamp + ".html";

		ExtentSparkReporter spark = new ExtentSparkReporter(path);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("TestReportUI");
		spark.config().setReportName("volexTestReport");
		extentReport = new ExtentReports();
		extentReport.attachReporter(spark);
		extentReport.setSystemInfo("Hose", "Local");
		extentReport.setSystemInfo("Browser", "chrome");
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extentReport.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		extenttest.set(test);
	}

	@Override	
	public void onTestSuccess(ITestResult result) {
		extenttest.get().log(Status.PASS, "Passed" + result.getName());
	}
	
	@Override	
	public void onTestFailure(ITestResult result) {
		extenttest.get().log(Status.FAIL, "failed"+result.getName());
		extenttest.get().log(Status.FAIL, result.getThrowable());
		
		BaseTest test=(BaseTest)result.getInstance();
	String path=test.addscreenshot(result.getName());
	extenttest.get().addScreenCaptureFromPath(path);
		
	}
	
	@Override	
	public void onTestSkipped(ITestResult result) {
		extenttest.get().log(Status.SKIP, "Test skipped"+result.getName());
	}
	
	@Override	
	public void onFinish(ITestContext context) {
		extentReport.flush();
		extenttest.remove();
		
	}

}
