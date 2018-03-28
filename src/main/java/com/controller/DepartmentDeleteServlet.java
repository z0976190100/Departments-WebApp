package com.controller;

import com.daart.controller.DAO.DAOGenericImpl;
import com.daart.controller.DAO.DepartmentEntityImpl;
import com.daart.controller.DAO.EmployeeEntityImpl;
import com.daart.controller.services.helpers.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.daart.controller.services.helpers.MessageManager.errorRedirect;

public class DepartmentDeleteServlet extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DAOGenericImpl actor = new DAOGenericImpl();

        String str  = req.getParameter("deppid");
        long idd = (long) Long.valueOf(str);
        actor.deleteEntry(new DepartmentEntityImpl(), idd);
        actor.deleteAllEntriesWhere(new EmployeeEntityImpl(), "department_id_long", idd);
        String pagePath =  ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req);
        }
    }

}
