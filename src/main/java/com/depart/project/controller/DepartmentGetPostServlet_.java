package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.DepartmentEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.MessageManager;
import com.depart.project.service.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// TODO: responsemessages -> responseMessagesMap

import static com.depart.project.service.utils.MessageManager.errorRedirect;
import static com.depart.project.service.utils.MessageManager.responseMessages;

public class DepartmentGetPostServlet_ extends HttpServlet {

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
           errorRedirect(req, resp);
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
           errorRedirect(req, resp);
        }
    }
}

