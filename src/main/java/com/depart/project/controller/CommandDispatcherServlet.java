package com.depart.project.controller;

import com.depart.project.service.Command;
import com.depart.project.service.RequestRouter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.depart.project.service.utils.MessageManager.errorRedirect;

// TODO servlet error redirect

public class CommandDispatcherServlet extends HttpServlet {

/*static {createTable("department");}*/

   private RequestRouter requestHelper = RequestRouter.getInstance();

    public CommandDispatcherServlet(){
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
            Command command = requestHelper.getCommand(req);
            pagePath = command.execute(req, resp);
        } catch (ServletException | IOException | NullPointerException e ){
            e.printStackTrace();
            errorRedirect(req, resp);
        }

        try {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        } catch (ServletException | IOException | NullPointerException e) {
            e.printStackTrace();
            errorRedirect(req, resp);
        }
    }
}

