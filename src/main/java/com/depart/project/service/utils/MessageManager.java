package com.depart.project.service.utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

   public static void errorRedirect(HttpServletRequest request, HttpServletResponse resp) {

        request.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        try {
            request.getRequestDispatcher(pagePath).forward(request, resp);
        }catch (ServletException | IOException e){
            e.printStackTrace();
        }
    }


    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }


}
