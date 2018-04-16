package com.depart.project.controller;

import com.depart.project.service.Command;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.MessageManager;
import com.depart.project.service.RequestRouter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO servlet error redirect

public class CommandServlet_ extends HttpServlet {

/*static {createTable("department");}*/

    RequestRouter requestHelper = RequestRouter.getInstance();

    public CommandServlet_(){
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

    private void processRequest(HttpServletRequest req, HttpServletResponse resp){

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
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            req.setAttribute("errorMessage", MessageManager.getInstance().getProperty(
                    MessageManager.SERVLET_EXCEPTION_ERROR_MESSAGE));
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.ERROR_PAGE_PATH);
        }
    }
}

