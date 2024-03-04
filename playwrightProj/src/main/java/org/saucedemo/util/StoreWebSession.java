package org.saucedemo.util;

import com.microsoft.playwright.BrowserContext;

import java.nio.file.Paths;

public class StoreWebSession {

    public String storeState(BrowserContext context, String filePath){
        return context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(filePath)));
    }
}
