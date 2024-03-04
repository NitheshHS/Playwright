package playwritetest;

import com.microsoft.playwright.*;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import screens.LoginScreen;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PlaywrightBasics {
    private Browser browser;

    @BeforeMethod
    public void launchBrowser(){
        Playwright playwright=Playwright.create();
         browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

    }
    @Test
    public void fileUploadTest(){
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/upload");

//        page.setInputFiles("input#file-upload", Paths.get("./src/test/resources/random.text"));

        FileChooser fileChooser = page.waitForFileChooser(() -> page.click("#file-upload"));
        /*Check the choose file support multiple file upload*/
        System.out.println("multiple file upload: "+fileChooser.isMultiple());
        fileChooser.setFiles(Paths.get("./src/test/resources/random.text"));
        page.click("input#file-submit");
        System.out.println(page.textContent("#uploaded-files"));
    }

    @Test
    public void downloadFileTest() {
        Page page = browser.newPage();
        page.navigate("https://the-internet.herokuapp.com/download");
        /*Download file*/
        Download download = page.waitForDownload(() -> page.click("a:text('preprod.json')"));
        /*save file*/
        download.saveAs(Paths.get(download.suggestedFilename()));
        /*check download failure*/
        System.out.println("Any file download error: "+download.failure());
        /*Download page url*/
        System.out.println(download.url());
    }

    @Test
    public void saveAuthenticationSession(){
        BrowserContext context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("auth.json")));
        Page page = context.newPage();
        page.navigate("http://www.automationpractice.pl/index.php");
        /*page.click("a.login");
        page.fill("input#email", "nithesh@gmail.com");
        page.fill("input#passwd","random");
        page.click("button#SubmitLogin");*/

        String username = page.locator("a.account").textContent().trim();
        Assert.assertEquals(username, "Nithesh Gowda");

        /*Save storage state into the file*/
        //context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("auth.json")));

    }

    @Test
    public void maximizeScreenTest() throws InterruptedException {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        BrowserContext context=browser.newContext(new Browser.NewContextOptions().setViewportSize(dimension.width, dimension.height));
        Page page= context.newPage();
        page.navigate("http://www.automationpractice.pl/index.php");
        Thread.sleep(5000);
    }

    @Test
    public void videoRecordTest(){
        BrowserContext context= browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./video/")));
        context.tracing().start(new Tracing.StartOptions().setSources(true).setSnapshots(true).setScreenshots(true));
        Page page= context.newPage();
        page.navigate("http://www.automationpractice.pl/index.php");
        page.click("a.login");
        page.fill("input#email", "nithesh@gmail.com");
        page.fill("input#passwd","random");
        page.click("button#SubmitLogin");
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("tracing.zip")));
        page.close();
        //page.video().saveAs(Paths.get("screenRecord.webm"));
        System.out.println(page.video().path());
    }

    @Test
    public void pageObjectsTest(){
        BrowserContext context = browser.newContext();
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));
        Page page = context.newPage();
        page.navigate("http://www.automationpractice.pl/index.php");
        LoginScreen loginScreen=new LoginScreen(page);
        loginScreen.login("nithesh@gmail.com","random");
        context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("pageObjectTracing.zip")));
    }

    @Test
    public void handleMultipleWindowPopup(){
        BrowserContext context=browser.newContext();
        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/windows");
        Page newPage = page.waitForPopup(() -> {
            page.getByText("Click Here").click();
        });
        newPage.waitForLoadState();
        System.out.println(newPage.title());
        newPage.close();
        System.out.println(page.title());
        page.close();
    }

    @Test
    public void keyBoardActionTest(){
        BrowserContext context=browser.newContext();
        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/horizontal_slider");
        page.click("input[type='range']");
        for(int i=0;i<2;i++) {
            page.keyboard().press("ArrowRight");
        }
        page.waitForLoadState();
        System.out.println("value: "+page.locator("span#range").textContent());
        page.close();
    }

    @Test
    public void mouseHoverTest(){
        BrowserContext context=browser.newContext();
        Page page = context.newPage();
        page.navigate("https://the-internet.herokuapp.com/hovers");
        page.hover("img[alt='User Avatar']");
        page.hover("//a[text()='View profile']");
        page.click("//a[text()='View profile']");
        System.out.println("page url "+page.url());
        page.pause();
        page.close();
    }

    @AfterMethod
    public void closeBrowser(){
        browser.close();
    }

}
