package com.service.helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateDBService {

   // static {CreateDBService.createTable("department");}

    public static boolean createTable(String tableTitle) {

        try {
            String driver = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_DRIVER_NAME);
            DriverManager.registerDriver(new org.postgresql.Driver());
            Class.forName(driver);
            Connection connection = null;

            try {
                String url = ConfigurationManager.getInstance().getProperty(ConfigurationManager.DB_URL);
                connection = DriverManager.getConnection(url, "postgres", "rut");
                PreparedStatement ps = null;

                try {
                    ps = connection.prepareStatement(
                            "CREATE TABLE department1.department\n" +
                                    "(\n" +
                                    "    title text COLLATE pg_catalog.\"default\",\n" +
                                    "    employees bigint ,\n" +
                                    "    id SERIAL NOT NULL,\n" +
                                    "    CONSTRAINT \"department_pkey\" PRIMARY KEY (id)\n" +
                                    ")\n" +
                                    "WITH (\n" +
                                    "    OIDS = FALSE\n" +
                                    ")\n" +
                                    "TABLESPACE pg_default;\n" +
                                    "\n" +
                                    "ALTER TABLE department1.department\n" +
                                    "    OWNER to postgres;");


                    try {
                        int in = ps.executeUpdate();
                        return true;
                    } finally {
                       int in = 0;
                    }
                } finally {
                    if (ps != null) ps.close();
                }
            } finally {
                if (connection != null) connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            return false;

        }
    }
}
