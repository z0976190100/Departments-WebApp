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
import java.util.Set;

import static com.depart.project.service.utils.MessageManager.errorRedirect;

public class EmployeeUpdateServlet extends HttpServlet {

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

        if (validator.employeeFormValidate(responseMessagesMap, newE)) {

            Set<String> ks = newE.getColoumnValueMap().keySet();

            for (String col : ks) {
                if (!col.contains("_long")) {
                    actor.updateEntryColoumnWhereId(newE,
                            col,
                            newE.getId(),
                            (String) newE.getColoumnValueMap().get(col));
                }
            }
            responseMessagesMap.put("EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.EMPLOYEE_RECORD_UPDATE_SUCCESS_MESSAGE));
        }
        req.setAttribute("responseMessages", responseMessagesMap);
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.EMPLOYEE_ADD_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req, resp);

        }

    }


}
