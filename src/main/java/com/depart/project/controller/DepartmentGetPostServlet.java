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
import java.util.HashMap;
import java.util.Map;

// TODO: responsemessages -> responseMessagesMap

import static com.depart.project.service.utils.MessageManager.errorRedirect;
import static com.depart.project.service.utils.MessageManager.responseMessages;

public class DepartmentGetPostServlet extends HttpServlet {

    public static String depTitleInputValue = "";


    // renaming existing Department
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> responseMessages = new HashMap<>();
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPUPD_PAGE_PATH);

        responseMessages.put("EMPTY_FIELD_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));

        String newDepTitle = req.getParameter("newdepptitle");

        if (!newDepTitle.equals("")) {

            responseMessages.clear();

            DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);
            Validator validator = new Validator();

            if (validator.isExist(nd, "title", nd.getTitle())) {
                responseMessages.put("DEPTITLE_SAVE_PROBLEM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE));
                req.setAttribute("depTitleInputValue", nd.getTitle());
            }

            if (responseMessages.isEmpty()) {

                DAOGenericImpl actor = new DAOGenericImpl();

                actor.updateEntryColoumnWhereId(nd, "title", Long.valueOf(req.getParameter("deppid")), nd.getTitle());
                pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPARTMENT_LISTBUILDER_SERVLET_PATH);
                responseMessages.put("DEP_RECORD_UPDATE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DEP_RECORD_UPDATE_SUCCESS_MESSAGE));
            }
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        req.setAttribute("responseMessages", responseMessages);
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
        Map<String, String> responseMessages = new HashMap<>();

        responseMessages.put("EMPTY_FIELD_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));
        String newDepTitle = req.getParameter("newdepptitle");

        if (!newDepTitle.equals("")) {

            responseMessages.clear();
            DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);
            Validator validator = new Validator();

            if (validator.isExist(nd, "title", nd.getTitle())) {
                responseMessages.put("DEPTITLE_SAVE_PROBLEM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE));
                req.setAttribute("depTitleInputValue", nd.getTitle());
            }

            if (responseMessages.isEmpty()) {
                DAOGenericImpl actor = new DAOGenericImpl();
                actor.saveEntry(nd);
                responseMessages.put("NEW_DEP_SAVE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_DEP_SAVE_SUCCESS_MESSAGE));
                req.setAttribute("depTitleInputValue", "");
            }
        }
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPARTMENT_LISTBUILDER_SERVLET_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        req.setAttribute("responseMessages", responseMessages);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req, resp);
        }
    }
}

