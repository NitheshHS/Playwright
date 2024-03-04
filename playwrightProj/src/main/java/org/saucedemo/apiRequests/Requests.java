package org.saucedemo.apiRequests;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.impl.RequestOptionsImpl;
import com.microsoft.playwright.options.RequestOptions;

public class Requests {
    private APIRequestContext apiRequestContext;
    public Requests(APIRequestContext apiRequestContext){
        this.apiRequestContext = apiRequestContext;
    }

    public APIResponse getRequest(APIRequestContext apiRequestContext, String url){
        return apiRequestContext.get(url);
    }

    public APIResponse postRequest(APIRequestContext apiRequestContext, String url, String data){
        return apiRequestContext.post(url, RequestOptions.create().setData(data));
    }
}
