package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.DepartmentEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.MessageManager;
import com.depart.project.service.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static com.depart.project.service.utils.MessageManager.errorRedirect;

public class DepartmentServlet extends HttpServlet {



    // renaming existing Department
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> responseMessagesMap = new HashMap<>();
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPUPD_PAGE_PATH);

        responseMessagesMap.put("EMPTY_FIELD_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EMPTY_FIELD_MESSAGE));

        String newDepTitle = req.getParameter("newdepptitle");

        if (!newDepTitle.equals("")) {

            responseMessagesMap.clear();

            DepartmentEntityImpl nd = new DepartmentEntityImpl(newDepTitle);
            Validator validator = new Validator();

            if (validator.isExist(nd, "title", nd.getTitle())) {
                responseMessagesMap.put("DEPTITLE_SAVE_PROBLEM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DEPTITLE_SAVE_PROBLEM_MESSAGE));
                req.setAttribute("depTitleInputValue", nd.getTitle());
            }

            if (responseMessagesMap.isEmpty()) {

                DAOGenericImpl actor = new DAOGenericImpl();

                actor.updateEntryColoumnWhereId(nd, "title", Long.valueOf(req.getParameter("deppid")), nd.getTitle());
                pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPARTMENT_LISTBUILDER_SERVLET_PATH);
                responseMessagesMap.put("DEP_RECORD_UPDATE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.DEP_RECORD_UPDATE_SUCCESS_MESSAGE));
            }
        }


        req.setAttribute("responseMessagesMap", responseMessagesMap);
        try {
            req.getRequestDispatcher(pagePath).forward(req, resp);
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

        req.setAttribute("responseMessages", responseMessages);
        try {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req, resp);
        }
    }
}

