package com.controller;

import com.daart.controller.DAO.DAOGenericImpl;
import com.daart.controller.DAO.EmployeeEntityImpl;
import com.daart.controller.services.helpers.ConfigurationManager;
import com.daart.controller.services.helpers.MessageManager;
import com.daart.controller.services.helpers.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.daart.controller.services.helpers.MessageManager.errorRedirect;
import static com.daart.controller.services.helpers.MessageManager.responseMessages;

public class EmployeeGetPostServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOGenericImpl actor = new DAOGenericImpl();
        responseMessages = "";

        Validator validator = new Validator();

        EmployeeEntityImpl emp = new EmployeeEntityImpl(
                req.getParameter("empfname"),
                req.getParameter("emplname"),
                Long.valueOf(req.getParameter("deppid")),
                req.getParameter("emplogin"),
                req.getParameter("emppass")
        );

        if (validator.isExist(emp, "login", emp.login)) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.LOGIN_SAVE_PROBLEM_MESSAGE) + "#";
        }
        if (validator.isExist(emp, "pass", emp.pass)) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.PASSWORD_SAVE_PROBLEM_MESSAGE) + "#";
        }

        if (responseMessages == "") {


            for (String col : emp.coloumnValueMap.keySet()) {
                if (!col.contains("_long")) {
                    actor.updateEntryColoumnWhereId(emp,
                            col,
                            Long.valueOf(req.getParameter("empid")),
                            emp.coloumnValueMap.get(col));
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
                Long.valueOf(req.getParameter("empdep")),
                req.getParameter("emplogin"),
                req.getParameter("emppass"));

        if (validator.isExist(newE, "login", newE.login)) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.LOGIN_SAVE_PROBLEM_MESSAGE) + "#";
        }
        if (validator.isExist(newE, "pass", newE.pass)) {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.PASSWORD_SAVE_PROBLEM_MESSAGE) + "#";
        }

        if (responseMessages == "") {
            actor.saveEntry(newE);
            responseMessages = "New Employee record is saved.";
        }
        req.setAttribute("responseMessages", responseMessages);
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEP_PAGE_PATH);
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
