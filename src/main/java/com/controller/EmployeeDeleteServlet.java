package com.controller;

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.EmployeeEntityImpl;
import com.service.utils.ConfigurationManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.service.utils.MessageManager.errorRedirect;

public class EmployeeDeleteServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DAOGenericImpl actor = new DAOGenericImpl();

        String str = req.getParameter("empid");
        long idd = (long) Long.valueOf(str);
        actor.deleteEntry(new EmployeeEntityImpl(), idd);
        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEP_PAGE_PATH);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagePath);
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
            errorRedirect(req);
        }
    }
}
