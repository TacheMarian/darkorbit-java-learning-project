package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static String url = "jdbc:mysql://localhost/darkorbit_accounts";
    private static String user = "darkorbitonly";
    private static String password = "darkbot";

    public Database() {
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        Database.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        Database.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Database.password = password;
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url,user, password);
    }
}
