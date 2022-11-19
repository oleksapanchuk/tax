package com.panchuk.tax.dao.mysql.util;

import com.panchuk.tax.DAOException;
import com.panchuk.tax.constant.ProjectConstant;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

    private static final Properties properties = new Properties();
    private static final String DATABASE_URL;

    static {
        try {
            properties.load(new FileReader(ProjectConstant.SETTINGS_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        DATABASE_URL = (String) properties.get(ProjectConstant.CONNECTION_URL);
        System.out.println(DATABASE_URL);
    }

    private ConnectionManager() { }

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL);
    }

    public static void rollback(Connection con) throws DAOException {
        try {
            con.rollback();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }

    public static void close(Connection con) throws DAOException {
        try {
            con.close();
        } catch (SQLException ex) {
            throw new DAOException(ex);
        }
    }
}
