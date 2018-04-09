package com.service.utils;


import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.IEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isExist(IEntity entity, String colName, String flag) {

        DAOGenericImpl actor = new DAOGenericImpl();

        try (PreparedStatement ps = actor.selectAllWhere(entity, colName, flag);
ResultSet rs = ps.getResultSet()){
           return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   public boolean isEmail(String toValidate){

       Pattern emailRegexp = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
       Matcher matcher = emailRegexp.matcher(toValidate);
       return matcher.find();

   }

}
