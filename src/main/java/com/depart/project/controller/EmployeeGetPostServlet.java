package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.EmployeeBuilder;
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

import static com.depart.project.service.utils.MessageManager.errorRedirect;


public class EmployeeGetPostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    // saving new employee to DB
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> responseMessagesMap = new HashMap<>();
        DAOGenericImpl actor = new DAOGenericImpl();
        Validator validator = new Validator();
        EmployeeEntityImpl newE = new EmployeeBuilder().build(req);

        if (validator.employeeFormValidate(responseMessagesMap, newE)) {
            actor.saveEntry(newE);
            responseMessagesMap.put("NEW_EMPLOYEE_SAVE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_EMPLOYEE_SAVE_SUCCESS_MESSAGE));
        }
        req.setAttribute("responseMessages", responseMessagesMap);
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPLOYEE_ADD_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            dispatcher = getServletContext().getRequestDispatcher(errorRedirect(req));
            dispatcher.forward(req, resp);

        }
    }
}