package com.service.helpers;

import java.util.ResourceBundle;

public class ConfigurationManager {
    private static ConfigurationManager instance = null;
    private ResourceBundle resource;
    static final String BUNDLE_NAME = "config";
    public static final String DB_DRIVER_NAME = "DB_DRIVER_NAME";
    public static final String DB_URL = "DB_URL";
    public static final String DB_LOGIN = "DB_LOGIN";
    public static final String DB_PASS = "DB_PASS";
    public static final String ERROR_PAGE_PATH = "ERROR_PAGE_PATH";
    public static final String LOGIN_PAGE_PATH = "LOGIN_PAGE_PATH";
    public static final String MAIN_PAGE_PATH = "MAIN_PAGE_PATH";
    public static final String DEP_PAGE_PATH = "DEP_PAGE_PATH";
    public static final String DEPUPD_PAGE_PATH = "DEPUPD_PAGE_PATH";
    public static final String EMPUPD_PAGE_PATH = "EMPUPD_PAGE_PATH";


    private ConfigurationManager(){

    }

    public static ConfigurationManager getInstance(){
        if(instance == null){
            instance = new ConfigurationManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key){
        return (String) resource.getObject(key);
    }


}
