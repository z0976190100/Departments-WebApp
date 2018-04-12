package com.controller;

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.EmployeeEntityImpl;
import com.service.utils.ConfigurationManager;
import com.service.utils.MessageManager;
import com.service.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static com.service.utils.MessageManager.errorRedirect;
import static com.service.utils.MessageManager.responseMessages;

public class EmployeeGetPostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        responseMessages = "";

        DAOGenericImpl actor = new DAOGenericImpl();

        Validator validator = new Validator();

        EmployeeEntityImpl emp = new EmployeeEntityImpl(
                req.getParameter("empfname"),
                req.getParameter("emplname"),
                Long.valueOf(req.getParameter("deppid")),
                req.getParameter("emplogin"),
                req.getParameter("emppass")
        );

        if (validator.isExist(emp, "login", emp.getLogin())) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.LOGIN_SAVE_PROBLEM_MESSAGE) + "#";
        }
        if (validator.isExist(emp, "pass", emp.getPass())) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.PASSWORD_SAVE_PROBLEM_MESSAGE) + "#";
        }

        if (responseMessages.equals("")) {

            Set<String> ks = emp.getColoumnValueMap().keySet();

            for (String col : ks) {
                if (!col.contains("_long")) {
                    actor.updateEntryColoumnWhereId(emp,
                            col,
                            Long.valueOf(req.getParameter("empid")),
                            (String)emp.getColoumnValueMap().get(col));
                }
            }
            responseMessages = "Successfully updated";
        }
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPUPD_PAGE_PATH);
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

        responseMessages = "";

        DAOGenericImpl actor = new DAOGenericImpl();

        Validator validator = new Validator();

        EmployeeEntityImpl newE = new EmployeeEntityImpl(
                req.getParameter("empfname"),
                req.getParameter("emplname"),
                Long.valueOf(req.getParameter("deppid")),
                req.getParameter("emplogin"),
                req.getParameter("emppass"));

        if (validator.isExist(newE, "login", newE.getLogin())) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.LOGIN_SAVE_PROBLEM_MESSAGE) + "#";
        }
        if (validator.isExist(newE, "pass", newE.getPass())) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.PASSWORD_SAVE_PROBLEM_MESSAGE) + "#";
        }

        if (!validator.isEmail(newE.getLogin())) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.INVALID_EMAIL_MESSAGE) + "#";
        }

        if (responseMessages.equals("")) {
            actor.saveEntry(newE);
            responseMessages = "New Employee record is saved.";
        }
        req.setAttribute("responseMessages", responseMessages);
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
