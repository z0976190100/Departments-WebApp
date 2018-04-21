package com.depart.project.service;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;
import com.depart.project.service.utils.MessageManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CommandLogin_ implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASS = "pass";

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pagePath = null;

        Map<String, String> respMess = new HashMap<>();

        String login = req.getParameter(PARAM_NAME_LOGIN);
        String pass = req.getParameter(PARAM_NAME_PASS);


        if (checkLogin(login, pass, req)) {
            req.setAttribute("login", "true");
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEPARTMENT_LISTBUILDER_SERVLET_PATH);
        } else {
            respMess.put("LOGIN_PASSWORD_PROBLEM_MESSAGE", MessageManager.getInstance().getProperty(MessageManager.LOGIN_PASSWORD_PROBLEM_MESSAGE));
            req.setAttribute("responseMessages", respMess);
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }

        return pagePath;
    }

    private static boolean checkLogin(String login, String pass, HttpServletRequest req) {


        DAOGenericImpl actor = new DAOGenericImpl();

        try (PreparedStatement ps = actor.selectAllWhere(new EmployeeEntityImpl(), "login", login);
                ResultSet rs = ps.getResultSet()) {
            if (rs.next()) {
                if (rs.getString("pass").equals(pass)) {
                    req.setAttribute("user", rs.getString("first_name"));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
