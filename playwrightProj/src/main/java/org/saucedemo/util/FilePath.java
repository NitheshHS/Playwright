package org.saucedemo.util;

import java.util.Date;

public enum FilePath {
    SAUCE_DEMO_PROPERTY("./src/main/resources/saucedemo.properties"),
    EXTENT_REPORT_PATH("./reports/"+JavaUtil.getSystemDate()+".html");
    private String path;
    FilePath(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
