package com.depart.project.persistense.dao;

import com.depart.project.persistense.entity.IEntity;
import com.depart.project.service.utils.ConfigurationManager;

import java.sql.*;
import java.util.Map;

public class DAOGenericImpl<T> implements DAO<T> {

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


    @Override
    public PreparedStatement selectAllWhere(T entity, String colName, String flag) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection()) {

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setString(1, flag);

            ps.executeQuery();
            return ps;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAllEntriesWhere(T entity, String colName, long id) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection()) {

            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setLong(1, id);

            ps.executeUpdate();
            connection.close();
            return true;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteAllEntriesWhere(T entity, String colName, String flag) {
        return false;
    }

    @Override
    public PreparedStatement selectAll(T entity) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection()) {

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + ";");

            ps.executeQuery();
            connection.close();
            return ps;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement selectAllWhere(T entity, String colName, long flag) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection()) {

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setLong(1, flag);

            ps.executeQuery();
            connection.close();
            return ps;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public T selectEntryByProperty(String key) {
        return null;
    }

    @Override
    public PreparedStatement selectEntryById(long id, T entity) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection()) {

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE ID = ? ;");
            ps.setLong(1, id);

            ps.executeQuery();
            connection.close();
            return ps;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveEntry(T entity) {

        IEntity nentity = (IEntity) entity;
        String tn = nentity.getTableName();
        String ut = nentity.getUniqueTitle();
        Map<String, String> cv = nentity.getColoumnValueMap();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO " + tn + " ( " + ut + ", id ) VALUES (? , DEFAULT ) RETURNING * ;")) {

            ps.setString(1, cv.get(ut));
            ResultSet rs = ps.executeQuery();
            rs.next();
            long id = rs.getLong("id");

            for (String colName : cv.keySet()) {

                if (colName.contains("_long")) {
                    long colV = Long.valueOf(cv.get(colName));
                    try (PreparedStatement pps = connection.prepareStatement(
                            "UPDATE " + tn + " SET " + colName + " = " + colV + " WHERE ID = " + id + ";"
                    )) {
                        pps.executeUpdate();
                        continue;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (colName.equals("birth_date")) {
                    updateEntryDate(entity, id, nentity.getBirthDate());
                        continue;
                }

                try( PreparedStatement pss = connection.prepareStatement(
                        "UPDATE " + tn + " SET " + colName + " = '" + cv.get(colName) + "' WHERE ID = " + id + ";")){

                    pss.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return true;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteEntry(T entity, long id) {

        IEntity nentity = (IEntity) entity;

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "DELETE FROM " + nentity.getTableName() + " WHERE ID = ? ;")){

            ps.setLong(1, id);
            ps.executeUpdate();

            return true;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateEntryColoumnWhereId(T entry, String colName, long id, String newValue) {
        IEntity nentity = (IEntity) entry;
        String tn = nentity.getTableName();
        String ut = nentity.getUniqueTitle();

        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE " + tn + " SET " + colName + " = '" + newValue + "' WHERE ID = " + id + ";")){

            ps.executeUpdate();

            return true;

        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEntry(T entity, long id) {
        return false;
    }


    @Override
    public boolean updateEntryDate(T entity, long id, java.sql.Date date) {

        IEntity nentity = (IEntity) entity;
        String tn = nentity.getTableName();
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE " + tn + " SET birth_date = ? WHERE ID = " + id + ";")) {
            ps.setDate(1, date);
            ps.executeUpdate();

            return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }
}
