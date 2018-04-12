package com.service;

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.EmployeeEntityImpl;
import com.service.utils.ConfigurationManager;
import com.service.utils.MessageManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.service.utils.MessageManager.responseMessages;

public class CommandLogin implements Command {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASS = "pass";

    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pagePath = null;
        responseMessages = "";

        String login = req.getParameter(PARAM_NAME_LOGIN);
        String pass = req.getParameter(PARAM_NAME_PASS);

        if (checkLogin(login, pass, req, resp)) {
            req.setAttribute("login", "true");
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
        } else {
            responseMessages += MessageManager.getInstance().getProperty(MessageManager.LOGIN_PASSWORD_PROBLEM_MESSAGE) + "#";
            req.setAttribute("errorMessage", MessageManager.getInstance().getProperty(MessageManager.LOGIN_ERROR_MESSAGE));
            pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }

        return pagePath;
    }

    private static boolean checkLogin(String login, String pass, HttpServletRequest req, HttpServletResponse resp) {


        DAOGenericImpl actor = new DAOGenericImpl();

        try (
                PreparedStatement ps = actor.selectAllWhere(new EmployeeEntityImpl(), "login", login);
                ResultSet rs = ps.getResultSet()) {
            if (rs.next()) {
                if (rs.getString("pass").equals(pass)) {
                    Cookie cookie = new Cookie("user", rs.getString("first_name"));
                   req.setAttribute("user", rs.getString("first_name"));
                    resp.addCookie(cookie);
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
