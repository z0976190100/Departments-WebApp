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
    DAOGenericImpl actor = new DAOGenericImpl();
    Validator validator = new Validator();


    // renaming existing Department
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        responseMessages = "";
        responseMessages += MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE) + "#";

        String newDepTitle = req.getParameter("newdepptitle");

        if (!newDepTitle.equals("")) {

            responseMessages = "";

            DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);

            if (validator.isExist(nd, "title", nd.getTitle())) {
                responseMessages += MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE) + "#";
                depTitleInputValue = nd.getTitle();
            }

            if (responseMessages.equals("")) {

                actor.updateEntryColoumnWhereId(nd, "title", Long.valueOf(req.getParameter("deppid")), nd.getTitle());
                }
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


    // saving new Department
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        responseMessages = "";
        responseMessages += MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE) + "#";
        String newDepTitle = req.getParameter("newdepptitle");

        if (!newDepTitle.equals("")) {

            responseMessages = "";
            DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);

            if (validator.isExist(nd, "title", nd.getTitle())) {
                responseMessages += MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE) + "#";
                depTitleInputValue = nd.getTitle();
            }

            if (responseMessages.equals("")) {

                actor.saveEntry(nd);
                responseMessages = "New Department record is saved.";
                depTitleInputValue = "";
            }
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

