package com.service.helpers;

//Implemented to entities, which require validation of incoming data before processing in persistence operations.
//Preprocessing in servlet filters.

import com.persistense.dao.DAOGenericImpl;
import com.persistense.entity.IEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

}
