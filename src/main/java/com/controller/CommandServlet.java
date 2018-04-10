package com.controller;

import com.service.Command;
import com.service.utils.ConfigurationManager;
import com.service.utils.MessageManager;
import com.service.utils.RequestRouter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandServlet extends HttpServlet {


/*static {createTable("department");}*/

    RequestRouter requestHelper = RequestRouter.getInstance();

    public CommandServlet(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp)throws SecurityException, IOException{

        String pagePath = null;

        try{
            Command command = requestHelper.getCommand(req, resp );
            pagePath = command.execute(req, resp);
        } catch (ServletException e){
            e.printStackTrace();
            req.setAttribute("errorMessage", MessageManager.getInstance().getProperty(
                    MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        catch (IOException e){
            e.printStackTrace();
            req.setAttribute("errorMessage",  MessageManager.getInstance().getProperty(
                    MessageManager.IO_EXCEPTION_ERROR_MESSAGE));
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        try {

            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", MessageManager.getInstance().getProperty(
                    MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
    }
}

