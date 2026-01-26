package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionOr {

    private static String DB_URL = "jdbc:oracle:thin:@localhost:1521:EE";
    private static String USER = "searchbar";
    private static String PASS = "searchbar";

    // Charger le driver une seule fois (optimisation légère)
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found.");
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    public static void main(String args[]) {
        try {
            Connection conn = ConnectionOr.getConnection();
            System.out.println("Connection established: " + conn);
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }

    }
}