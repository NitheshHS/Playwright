package org.saucedemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SauceDemoProperties {

    public String getProperties(String propName) {
        File file = new File(FilePath.SAUCE_DEMO_PROPERTY.getPath());
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return properties.getProperty(propName);
        } catch (FileNotFoundException e) {
            System.out.println("File not exist: " + file.getName());
        } catch (IOException e) {
            System.out.println("Unable to read file: " + file);
        }
        return null;
    }
}
