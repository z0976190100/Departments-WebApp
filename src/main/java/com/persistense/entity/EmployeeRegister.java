package com.persistense.entity;

import com.persistense.dao.DAOGenericImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRegister {


    public static List<EmployeeEntityImpl> emppList = new ArrayList<>();

    public List<EmployeeEntityImpl> getEmppList() {
        return emppList;
    }



    public void listUpdate() {

    }


    public static void listUpdate(long id) {
        DAOGenericImpl actor = new DAOGenericImpl();

        PreparedStatement ps = actor.selectAllWhere(new EmployeeEntityImpl(), "department_id_long", id);

        emppList.clear();

        try {
            ResultSet rs  = ps.getResultSet();
            while (rs.next()) {

                EmployeeEntityImpl employee = new EmployeeEntityImpl(
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getLong("department_id_long"),
                        rs.getString("login"),
                        rs.getString("pass"));
                employee.setId(rs.getLong("id"));
                emppList.add(employee);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                if (ps != null) ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }



    }
}
