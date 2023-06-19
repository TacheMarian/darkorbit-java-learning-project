package com.company.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static String url = "jdbc:mysql://localhost/darkorbit_accounts";
    private static String user = "darkorbitonly";
    private static String password = "darkbot";

    public DatabaseConfig() {
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        DatabaseConfig.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        DatabaseConfig.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        DatabaseConfig.password = password;
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user, password);
    }
}
