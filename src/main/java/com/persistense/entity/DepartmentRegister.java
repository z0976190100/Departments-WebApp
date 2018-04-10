package com.persistense.entity;

import com.persistense.dao.DAOGenericImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.service.utils.MessageManager.responseMessages;

public class DepartmentRegister {

    public static List<DepartmentEntityImpl> deppList = new ArrayList<>();
    public static String test = "test";



   /* static {
        deppList.add(new DepartmentEntityImpl(1, "first", 100));
        deppList.add(new DepartmentEntityImpl(2, "second", 100));
        deppList.add(new DepartmentEntityImpl(3, "thoird", 100));
        deppList.add(new DepartmentEntityImpl(4, "fourth", 100));
    }*/

    public static void listUpdate() {


        DAOGenericImpl actor = new DAOGenericImpl();

        PreparedStatement ps = actor.selectAll(new DepartmentEntityImpl());

        deppList.clear();

        try {
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                DepartmentEntityImpl dp = new DepartmentEntityImpl(rs.getString("title"));
                dp.setId(rs.getLong("id"));

                ps = actor.selectAllWhere(new EmployeeEntityImpl(), "department_id_long", dp.getId());

                ResultSet rrs = ps.getResultSet();
                while (rrs.next()) dp.setEmpQuant(dp.getEmpQuant() + 1);

                deppList.add(dp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


