package org.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import org.saucedemo.util.BaseTest;
import org.testng.Assert;

import java.util.List;

public class CartScreen extends Screen {
    public ExtentTest test;
    private Page page;
    private String cartItemList = "div.inventory_item_name";
    private String cartItemPrices = "div.inventory_item_price";
    private String checkoutBtn = "a.btn_action.checkout_button";
    public CartScreen(Page page, ExtentTest test) {
        super(page, test);
        this.page = page;
        this.test=test;
    }
    public CartScreen checkoutCartItems(){
        click(checkoutBtn);
        return this;
    }
    public List<String> getCartItemTitles(){
        return getAllTextContent(cartItemList);
    }
    public List<Double> getCartItemPrices(){
        return getAllTextContent(cartItemPrices).stream().map(txt->Double.parseDouble(txt)).toList();
    }
    public CartScreen verifyCheckoutScreen(){
        getAllElements(cartItemList)
                .stream()
                .forEach(locator -> {
                    Assert.assertTrue(locator.isVisible(), "Cart Item Title not displayed");
                });
        test.info("Verified checkout screen");
        getAllElements(cartItemPrices)
                .stream()
                .forEach(locator -> {
                    Assert.assertTrue(locator.isVisible(), "Cart Item prices not displayed");
                });
        test.info("Verified Item price in checkout page");
        click(checkoutBtn);
        test.info("clicked on checkout Button");
        return this;
    }
}
