package org.saucedemo;

import org.saucedemo.pages.LoginScreen;
import org.saucedemo.util.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginScreenTest extends BaseTest {

    @Test
    public void validateSauceDemoLogin(){
        boolean isLogoDisplayed = new LoginScreen(page, getExtentTest())
                .login("standard_user", "secret_sauce")
                .verifySauceLabLogoIsDisplayed();
        //String cookieInfo = storeWebSession.storeState(context, "./src/main/resources/login.json");
        //System.out.println(cookieInfo);
        Assert.assertTrue(isLogoDisplayed, "Sauce Demo Logo not displayed");

    }

    @Test
    public void validateAllItemSorted(){
        new LoginScreen(page, getExtentTest())
                .login("standard_user", "secret_sauce")
                .verifyItemsItemAreSorted("hilo")
                .verifyItemsItemAreSorted("lohi")
                .verifyItemsItemAreSorted("az")
                .verifyItemsItemAreSorted("za");
    }

    @Test
    public void validateCartCheckout(){
        new LoginScreen(page, getExtentTest())
                .login("standard_user", "secret_sauce")
                .clickOnAddToCart()
                .clickOnCartIcon()
                .verifyCheckoutScreen();
    }

}
