package com.depart.project.service.utils;

import java.util.ResourceBundle;

public class ConfigurationManager implements ConfigurationManagerConstants {
    private static ConfigurationManager instance = null;
    private ResourceBundle resource;
    static final String BUNDLE_NAME = "config";


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
