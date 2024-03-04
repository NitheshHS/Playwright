package org.saucedemo.util;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.saucedemo.pages.CartScreen;
import org.saucedemo.pages.Screen;
import org.saucedemo.reportutil.ExtentReporter;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.nio.file.Paths;
import java.util.Arrays;

public class BaseTest {
    public static ThreadLocal<ExtentReporter> extentReporter = new ThreadLocal<>();
    public static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    public Playwright playwright;
    public Browser browser;
    public Page page;
    public BrowserContext context;
    public StoreWebSession storeWebSession = new StoreWebSession();
    public SauceDemoProperties sauceDemoProperties = new SauceDemoProperties();
    public APIRequestContext apiRequestContext;

    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    @BeforeSuite
    public void setUpReport() {
        extentReporter.set(new ExtentReporter());
        extentReporter.get().setUpReport(FilePath.EXTENT_REPORT_PATH.getPath());
    }

    @BeforeMethod
    public void initBrowser(ITestResult result) {
        extentTestThreadLocal.set(extentReporter.get().createTest(result.getMethod().getMethodName()));
        playwright = Playwright.create();
        String browserName = sauceDemoProperties.getProperties("BROWSER").toUpperCase();
        switch (browserName) {
            case "CHROMIUM":
                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "SAFARI":
                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            case "FIREFOX":
                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                break;
            default:
                System.out.println("Invalid browser name: " + browserName);
        }
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        page.navigate(sauceDemoProperties.getProperties("URL"));

    }

    @AfterMethod
    public void quitBrowser(ITestResult result, ITestNGMethod testNGMethod) {
        if (Arrays.stream(testNGMethod.getGroups()).anyMatch(group -> group.equalsIgnoreCase("API"))) {

        } else {
            extentReporter.get().testResult(result, getScreenshot(result));
            page.close();
            browser.close();
        }
        playwright.close();
    }

    @AfterSuite
    public void flushReport() {
        extentReporter.get().flushReport();
    }

    private String getScreenshot(ITestResult result) {
        if (!result.isSuccess()) {
            return page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get("./screenshots/" + result.getMethod().getMethodName() + ".png"))).toString();
        }
        return null;
    }
}
