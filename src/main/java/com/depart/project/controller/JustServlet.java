package com.depart.project.controller;

import com.depart.project.persistense.dao.DAOGenericImpl;
import com.depart.project.persistense.entity.EmployeeEntityImpl;
import com.depart.project.persistense.entity.IEntity;
import com.depart.project.service.utils.ConfigurationManager;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class JustServlet extends HttpServlet {

    Object respond;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private Connection getConnection() {

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            return DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean saveEntry(java.sql.Date date) {


        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO department1.datetest ( date ) VALUES (?) RETURNING * ;")) {

ps.setDate(1, date);
            ResultSet rs = ps.executeQuery();
            rs.next();
            respond = rs.getObject("date");
            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bdate = req.getParameter("date");

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date daDate = formatter.parse(bdate);
            System.out.println(daDate);
            System.out.println(formatter.format(daDate));

            java.sql.Date sql = new java.sql.Date(daDate.getTime());

            saveEntry(sql);

            req.setAttribute("date", respond);
            req.getRequestDispatcher("/index.jsp").forward(req,resp);

        } catch (ParseException e) {
            e.printStackTrace();
        }






        /*resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
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
        writer.flush();*/
    }
}
