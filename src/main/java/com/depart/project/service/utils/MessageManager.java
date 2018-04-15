package com.depart.project.service.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class MessageManager implements MessageManagerConstants{

    public static String responseMessages = "";

    private static MessageManager instance = null;
    private ResourceBundle resource;

    private MessageManager() {
    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public static String errorRedirect(HttpServletRequest request) {

        request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(
                MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
        return ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);

    }


    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }


}
