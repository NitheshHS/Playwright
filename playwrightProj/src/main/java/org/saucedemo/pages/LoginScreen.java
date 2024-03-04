package org.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import org.saucedemo.util.BaseTest;

public class LoginScreen extends Screen{
    public ExtentTest test;
    private Page page;
    private String username = "input#user-name";
    private String password = "input#password";
    private String loginBtn = "input#login-button";
    public LoginScreen(Page page, ExtentTest test) {
        super(page, test);
        this.page = page;
        this.test=test;
    }

    public SwagLabScreen login(String user_name, String password1){
        test.info(String.format("log in: username: %s password: %s", user_name, password1));
        enterText(username, user_name)
                .enterText(password, password1)
                .click(loginBtn);
        return new SwagLabScreen(page, test);
    }


}
