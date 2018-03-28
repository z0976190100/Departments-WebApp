package com.controller;

import com.daart.controller.DAO.DAOGenericImpl;
import com.daart.controller.DAO.EmployeeEntityImpl;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JustServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setDateHeader("Expires", 0);

        JSONObject jResp = new JSONObject();
        JSONObject jRow;
        JSONObject jRows = new JSONObject();
        JSONObject jHeader = new JSONObject();


        DAOGenericImpl actor = new DAOGenericImpl();

        EmployeeEntityImpl employeeEntity = new EmployeeEntityImpl();

        List<String> colNames = new ArrayList<>();

        colNames.add("first_name");
        colNames.add("last_name");
        colNames.add("department_id_long");
        colNames.add("login");
        colNames.add("pass");
        colNames.add("id");

        for(int i = 0; i<colNames.size()-1; i++){
            jRow = new JSONObject();
            jRow.put("name", colNames.get(i));
            jRow.put("title", colNames.get(i));
            jHeader.put(i+1, jRow);
        }

        jResp.put("header", jHeader);

        try (PreparedStatement ps = actor.selectAll(employeeEntity);
             ResultSet rs = ps.getResultSet()) {
            int rowcount = 1;
            while (rs.next()) {
                jRow = new JSONObject();
                for (String column : colNames) {

                    jRow.put(column, rs.getString(column));
                }

                jRows.put(rowcount, jRow);
                rowcount++;
            }
            jResp.put("rows", jRows);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PrintWriter writer = resp.getWriter();
        writer.print(jResp);
        writer.flush();
    }
}
