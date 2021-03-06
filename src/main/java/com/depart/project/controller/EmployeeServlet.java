package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.EmployeeBuilder;
import com.depart.project.service.utils.MessageManager;
import com.depart.project.service.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.depart.project.service.utils.MessageManager.errorRedirect;

public class EmployeeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Map<String, String> responseMessagesMap = new HashMap<>();
        DAOGenericImpl actor = new DAOGenericImpl();
        Validator validator = new Validator();
        EmployeeEntityImpl newE = new EmployeeBuilder().build(req);
        String pagePath = null;

        switch (req.getParameter("command")){

            case("employeeAdd"):
                if (validator.employeeFormValidate(responseMessagesMap, newE)) {
                    actor.saveEntry(newE);
                    responseMessagesMap.put("NEW_EMPLOYEE_SAVE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.NEW_EMPLOYEE_SAVE_SUCCESS_MESSAGE));
                }
                pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPLOYEE_ADD_PAGE_PATH);
                break;

            case("employeeUpdate"):
                if (validator.employeeUpdFormValidate(responseMessagesMap, newE)) {

                    Set<String> ks = newE.getColoumnValueMap().keySet();

                    for (String col : ks) {
                        if (!col.contains("_long") && !col.equals("birth_date") && !col.equals("login")) {
                            actor.updateEntryColoumnWhereId(newE,
                                    col,
                                    newE.getId(),
                                    (String) newE.getColoumnValueMap().get(col));
                        }
                    }
                    actor.updateEntryDate(newE, newE.getId(), (Date) req.getAttribute("birthDate"));
                    responseMessagesMap.put("EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE));
                }
                pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPLOYEE_UPD_PAGE_PATH);
                break;

                default:
                    pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPLOYEES_LISTBUILDER_SERVLET_PATH);
        }

        req.setAttribute("responseMessages", responseMessagesMap);
        try {
            req.getRequestDispatcher(pagePath).forward(req, resp);
        } catch (ServletException | NullPointerException e) {
            e.printStackTrace();
            errorRedirect(req, resp);
        }

    }
}
