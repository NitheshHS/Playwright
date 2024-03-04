package org.saucedemo.reportutil;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestResult;
import org.testng.reporters.EmailableReporter;

import java.util.Optional;

public class ExtentReporter {
    private ExtentReports reports;
    private ExtentTest extentTest;

    public ExtentReports setUpReport(String filePath){
        ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("SauceDemo Automation");
        reports=new ExtentReports();
        reports.attachReporter(spark);
        //reports.setSystemInfo("","");
        return reports;
    }

    public ExtentTest createTest(String testName){
        return extentTest = reports.createTest(testName);
    }

    public void testResult(ITestResult result, String bytes){
        if(result.getStatus() == ITestResult.SUCCESS){
            extentTest.log(Status.PASS, result.getMethod().getMethodName());
        }else if(result.getStatus() == ITestResult.FAILURE){
            extentTest.log(Status.FAIL, result.getMethod().getMethodName());
            extentTest.addScreenCaptureFromBase64String(bytes);
            extentTest.fail(result.getThrowable().getCause().fillInStackTrace());
        }else if(result.getStatus() == ITestResult.SKIP){
            extentTest.log(Status.SKIP, result.getMethod().getMethodName());
            extentTest.skip(result.getThrowable().getCause().fillInStackTrace());
        }
    }

    public void flushReport(){
        reports.flush();
    }
    public void logPass(String log){
        extentTest.pass(log);
    }
    public void logFail(String log){
        extentTest.fail(log);
    }
    public void log(String log){
        extentTest.log(Status.INFO,log);
    }

    public ExtentTest getExtentTest(){
        return Optional.ofNullable(extentTest)
                .orElse(null);
    }
}
