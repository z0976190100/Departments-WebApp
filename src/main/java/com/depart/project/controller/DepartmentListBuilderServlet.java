package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.DepartmentEntityImpl;
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

public class DepartmentListBuilderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("departmentList", listUpdate());

       String pagePath = ConfigurationManager.getInstance().getProperty(ConfigurationManager.MAIN_PAGE_PATH);
       req.getRequestDispatcher(pagePath).forward(req, resp);

    }

    private List <DepartmentEntityImpl> listUpdate() {

        List<DepartmentEntityImpl> deppList = new ArrayList<>();
        DAOGenericImpl actor = new DAOGenericImpl();

        // selecting all existing departments:
        try (PreparedStatement ps = actor.selectAll(new DepartmentEntityImpl());
             ResultSet rs = ps.getResultSet()){

            while (rs.next()) {
                DepartmentEntityImpl dp = new DepartmentEntityImpl(rs.getString("title"));
                dp.setId(rs.getLong("id"));

                // counting employees for each department:
                try(PreparedStatement pps = actor.selectAllWhere(new EmployeeEntityImpl(), "department_id_long", dp.getId());
                ResultSet rrs = pps.getResultSet()){

                    while (rrs.next()) dp.setEmpQuant(dp.getEmpQuant() + 1);

                    deppList.add(dp);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
             }
        return deppList;
    }
}
