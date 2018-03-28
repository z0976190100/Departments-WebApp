package com.persistense.dao;

import com.persistense.entity.IEntity;
import com.service.helpers.ConfigurationManager;

import java.sql.*;
import java.util.Map;

public class DAOGenericImpl<T> implements DAO<T> {


    @Override
    public PreparedStatement selectAllWhere(T entity, String colName, String flag) {
        IEntity nentity = (IEntity) entity;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setString(1, flag);

            ps.executeQuery();
            connection.close();
            return ps;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteAllEntriesWhere(T entity, String colName, long id) {

        IEntity nentity = (IEntity) entity;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

           PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setLong(1, id);

            ps.executeQuery();
            connection.close();
            return true;

        } catch (SQLException | ClassNotFoundException e) {
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

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );
            PreparedStatement ps = null;

            ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + ";"
            );

            ps.executeQuery();

            connection.close();

            return ps;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement selectAllWhere(T entity, String colName, long flag) {

        IEntity nentity = (IEntity) entity;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

           PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE " + colName + " = ?");

            ps.setLong(1, flag);

            ps.executeQuery();
            connection.close();
            return ps;

        } catch (SQLException | ClassNotFoundException e) {
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

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );
            PreparedStatement ps = null;

            ps = connection.prepareStatement(
                    "SELECT * FROM " + nentity.getTableName() + " WHERE ID = ? ;");
            ps.setLong(1, id);

            ps.executeQuery();

            connection.close();

            return ps;

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean saveEntry(T entity) {

        IEntity nentity = (IEntity) entity;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

            PreparedStatement ps = null;
            String tn = nentity.getTableName();
            String ut = nentity.getUniqueTitle();
            Map<String, String> cv = nentity.getColoumnValueMap();

            ps = connection.prepareStatement(
                    "INSERT INTO " + tn + " ( " + ut + ", id ) VALUES (? , DEFAULT ) RETURNING * ;");

            ps.setString(1, cv.get(ut));
            ResultSet rs = ps.executeQuery();
            rs.next();
            long id = rs.getLong("id");

            ps.close();
            for (String colName : cv.keySet()) {

                if (colName.contains("_long")) {
                    long colV = Long.valueOf(cv.get(colName));
                    ps = connection.prepareStatement(
                            "UPDATE " + tn + " SET " + colName + " = " + colV + " WHERE ID = " + id + ";"
                    );
                    ps.executeUpdate();
                    continue;
                }

                ps = connection.prepareStatement(
                        "UPDATE " + tn + " SET " + colName + " = '" + cv.get(colName) + "' WHERE ID = " + id + ";"
                );

                ps.executeUpdate();
            }

            ps.close();
            connection.close();

            return true;

        } catch (SQLException | ClassNotFoundException ce) {
            ce.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean deleteEntry(T entity, long id) {

        IEntity nentity = (IEntity) entity;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

            PreparedStatement ps = null;
            ps = connection.prepareStatement(
                    "DELETE FROM " + nentity.getTableName() + " WHERE ID = ? ;");
            ps.setLong(1, id);

            ps.executeUpdate();

            connection.close();
            ps.close();

            return true;

        } catch (SQLException | ClassNotFoundException ce) {
            ce.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateEntryColoumnWhereId(T entry, String colName, long id, String newValue) {
        IEntity nentity = (IEntity) entry;

        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME)
            );

            Connection connection = DriverManager.getConnection(
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_LOGIN),
                    ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_PASS)
            );

            PreparedStatement ps = null;
            String tn = nentity.getTableName();
            String ut = nentity.getUniqueTitle();

            ps = connection.prepareStatement(
                    "UPDATE " + tn + " SET " + colName + " = '" + newValue + "' WHERE ID = " + id + ";");

                ps.executeUpdate();

            return true;

        } catch (SQLException | ClassNotFoundException ce) {
            ce.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateEntry(T entity, long id) {
        return false;
    }
}
