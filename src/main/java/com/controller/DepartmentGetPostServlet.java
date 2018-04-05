package com.controller;

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.DepartmentEntityImpl;
import com.service.utils.ConfigurationManager;
import com.service.utils.MessageManager;
import com.service.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.service.utils.MessageManager.errorRedirect;
import static com.service.utils.MessageManager.responseMessages;

public class DepartmentGetPostServlet extends HttpServlet {

    public static String depTitleInputValue = "";

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
        Validator validator = new Validator();
        responseMessages = "";

        String newDepTitle = req.getParameter("newdepptitle");
        DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);

        if (validator.isExist(nd, "title", nd.title)) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE) + "#";
            depTitleInputValue = nd.title;
            String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
                errorRedirect(req);
            }
        }

        if (responseMessages.equals("")) {

                actor.saveEntry(nd);
                responseMessages = "New Department record is saved.";
            }

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

