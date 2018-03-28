package com.controller;

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.DepartmentEntityImpl;
import com.service.helpers.ConfigurationManager;
import com.service.helpers.MessageManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.service.helpers.MessageManager.errorRedirect;
import static com.service.helpers.MessageManager.responseMessages;

public class DepartmentGetPostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOGenericImpl actor = new DAOGenericImpl();
        responseMessages = "";

        DepartmentEntityImpl nd = new DepartmentEntityImpl();
        if (actor.updateEntryColoumnWhereId(nd,
                "title",
                Long.valueOf(req.getParameter("deppid")),
                req.getParameter("newdeptitle"))) {
            String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
                errorRedirect(req);
            }
        }
        responseMessages += MessageManager.getInstance().getProperty(MessageManager.DEPUPD_PROBLEM_MESSAGE) + "#";
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPUPD_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOGenericImpl actor = new DAOGenericImpl();
        responseMessages = "";

        String newDepTitle = req.getParameter("newdepptitle");
        DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);
        actor.saveEntry(nd);
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req);
        }


    }


}
