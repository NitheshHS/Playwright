package org.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PageAssertions;
import org.saucedemo.util.BaseTest;
import org.testng.Assert;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SwagLabScreen extends Screen {
    private Page page;
    private ExtentTest test;
    private String sauceLabsLogo ="div.app_logo";
    private String sortProductSelectDropDown = "select.product_sort_container";
    private String listOfInventoryItems = "div.inventory_item_name";
    private String listOfItemPrice = "div.inventory_item_price";
    private String addToCartButton = "text='ADD TO CART'";
    private String cartIcon = "//*[name()='svg' and @data-icon='shopping-cart']";

    public SwagLabScreen(Page page,ExtentTest test) {
        super(page, test);
        this.page = page;
        this.test=test;
    }

    public boolean verifySauceLabLogoIsDisplayed(){
        test.info("check the sauce swag demo logo");
        return isDisplayed(sauceLabsLogo);
    }

    public SwagLabScreen verifyItemsItemAreSorted(String option){
        List<String> allInventoryTexts = getAllTextContent(listOfInventoryItems).stream().sorted().toList();
        test.info("Get list of Inventory Item title: "+allInventoryTexts);
        List<Double> listOfPrice = javaUtil.convertStringToDouble(getAllTextContent(listOfItemPrice)).stream().sorted().toList();
        test.info("Sorted price list: "+listOfPrice);
        List<String> selectedOption=selectDropdown(sortProductSelectDropDown, option);
        System.out.println(selectedOption);
        if(selectedOption.get(0).equalsIgnoreCase("az")){
            List<String> naturalSorted = getAllTextContent(listOfInventoryItems);
            Assert.assertEquals(allInventoryTexts, naturalSorted, "Strings are not sorted in natural order");
            test.info("title sorted in ascending order");
        }else if(selectedOption.get(0).equalsIgnoreCase("za")){
            List<String> reverseOrder = getAllTextContent(listOfInventoryItems);
            allInventoryTexts = allInventoryTexts.stream().sorted(Comparator.reverseOrder()).toList();
            Assert.assertEquals(allInventoryTexts, reverseOrder,"Strings are not sorted in reverse order");
            test.info("title sorted in descending order");
        } else if (selectedOption.get(0).equalsIgnoreCase("lohi")) {
            List<Double> priceInAscendingOrder = javaUtil.convertStringToDouble(getAllTextContent(listOfItemPrice));
            Assert.assertEquals(listOfPrice, priceInAscendingOrder, "price not sorted in ascending order");
            test.info("Price sorted in ascending order: "+priceInAscendingOrder);
        } else if (selectedOption.get(0).equalsIgnoreCase("hilo")) {
            List<Double> priceInDescendingOrder = javaUtil.convertStringToDouble(getAllTextContent(listOfItemPrice));
            Assert.assertEquals(listOfPrice.stream().sorted(Comparator.reverseOrder()).toList(), priceInDescendingOrder, "price not sorted in ascending order");
            test.info("Price sorted in descending order: "+priceInDescendingOrder);
        }else{
            System.out.println("Option doesn't exist in dropdown: "+option);
        }
        System.out.println(allInventoryTexts);
        return this;
    }

    public SwagLabScreen clickOnAddToCart(){
        test.info("click on add to cart button");
        click(addToCartButton);
        return this;
    }

    public CartScreen clickOnCartIcon(){
        test.info("click on cart Icon");
        click(cartIcon);
        return new CartScreen(page, test);
    }

}
