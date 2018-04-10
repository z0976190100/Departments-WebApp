package com.service.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class MessageManager {

    public static String responseMessages = "";

    private static MessageManager instance = null;
    private ResourceBundle resource;
   public static final String BUNDLE_NAME = "messages";
   public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
   public static final String SERVLET_EXCEPTION_ERROR_MESSAGE = "SERVLET_EXCEPTION_ERROR_MESSAGE";
   public static final String IO_EXCEPTION_ERROR_MESSAGE = "IO_EXCEPTION_ERROR_MESSAGE";
   public static final String LOGIN_SAVE_PROBLEM_MESSAGE = "LOGIN_SAVE_PROBLEM_MESSAGE";
   public static final String PASSWORD_SAVE_PROBLEM_MESSAGE = "PASSWORD_SAVE_PROBLEM_MESSAGE";
   public static final String LOGIN_PASSWORD_PROBLEM_MESSAGE = "LOGIN_PASSWORD_PROBLEM_MESSAGE";
   public static final String DEPUPD_PROBLEM_MESSAGE = "DEPUPD_PROBLEM_MESSAGE";
   public static final String DEPTITLE_SAVE_PROBLEM_MESSAGE = "DEPTITLE_SAVE_PROBLEM_MESSAGE";
   public static final String EMPTY_FIELD_MESSAGE = "EMPTY_FIELD_MESSAGE";

    public static String errorRedirect(HttpServletRequest request){

        request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(
                MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

    }

    private MessageManager(){}

    public static MessageManager getInstance(){
        if(instance == null) {
            instance = new MessageManager();
            instance.resource  = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key){
        return (String) resource.getObject(key);
    }


}
