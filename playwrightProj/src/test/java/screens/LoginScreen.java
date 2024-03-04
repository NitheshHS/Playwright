package screens;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginScreen {
    private final Page page;
    private final Locator signIn;
    private final Locator enterEmail;
    private final Locator enterPassword;
    private final Locator loginButton;


    public LoginScreen(Page page){
        this.page = page;
        this.signIn = page.locator("a.login");
        this.enterEmail = page.locator("input#email");
        this.enterPassword = page.locator("input#passwd");
        this.loginButton = page.locator("button#SubmitLogin");
    }

    public void login(String email, String password){
        signIn.click();
        enterEmail.fill(email);
        enterPassword.fill(password);
        loginButton.click();
    }



}
