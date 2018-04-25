package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.service.utils.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeesListBuilderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("employeesList", listUpdate(Long.valueOf(req.getParameter("deppid"))));

        String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DEP_PAGE_PATH);
        req.getRequestDispatcher(pagePath).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private List<EmployeeEntityImpl> listUpdate(long id) {

        List<EmployeeEntityImpl> emppList = new ArrayList<>();

        DAOGenericImpl actor = new DAOGenericImpl();


        try (PreparedStatement ps = actor.selectAllWhere(new EmployeeEntityImpl(), "department_id_long", id);
             ResultSet rs = ps.getResultSet()) {

            while (rs.next()) {

                EmployeeEntityImpl employee = new EmployeeEntityImpl(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDate("birth_date"),
                        rs.getLong("department_id_long"),
                        rs.getString("login"),
                        rs.getString("pass"));
                employee.setId(rs.getLong("id"));

                emppList.add(employee);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return emppList;
    }
}
