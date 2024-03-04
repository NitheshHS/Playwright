package org.saucedemo.pages;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.ScreenshotType;
import com.microsoft.playwright.options.SelectOption;
import org.saucedemo.util.BaseTest;
import org.saucedemo.util.JavaUtil;

import java.nio.file.Paths;
import java.util.List;

public class Screen {
    private Page page;
    private ExtentTest extentTest;
    public JavaUtil javaUtil=new JavaUtil();
    public Screen(Page page, ExtentTest extentTest){
        this.page = page;
        this.extentTest = extentTest;
    }
    public Screen locator(String selector){
        page.locator(selector);
        return this;
    }
    public Screen click(String selector){
        page.click(selector);
        return this;
    }
    public Screen enterText(String selector, String text){
        page.fill(selector, text);
        return this;
    }
    public boolean isDisplayed(String selector){
        return page.isVisible(selector);
    }

    /**
     * Returns list of selected options
     * @param selector
     * @param optionName
     * @return listOfSelectedOptions
     */
    public List<String> selectDropdown(String selector, String optionName){
        return page.selectOption(selector, optionName);
    }

    public List<String> getAllTextContent(String locator){
        return page.locator(locator).allTextContents();
    }

    public List<Locator> getAllElements(String selector){
        return page.locator(selector).all();
    }

    public Locator getNthElement(String selector, int index){
        return page.locator(selector).nth(index);
    }

    public Video setVideo(String path){
        return page.video();
    }

    public void dragAndDrop(String source, String target){
        page.dragAndDrop(source, target);
    }

    public void mouseHover(String selector){
        page.hover(selector);
    }

}
