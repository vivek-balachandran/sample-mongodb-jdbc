package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySqlExample {

    // Connection details - Update these!
    private static final String URL = "jdbc:mysql://localhost:3306/guvi_db";
    private static final String USER = "admin";
    private static final String PASSWORD = "admin";

    public static void main(String[] args) {
        // SQL Query
        String query = "SELECT * FROM employee";

        // Try-with-resources auto-closes connection, statement, and result set
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("Connected to MySQL successfully!\n");

            // Fetching records
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("emp_name");
                String email = rs.getString("email_id");

                System.out.println("ID: " + id + " | Name: " + name + " | Email: " + email);
            }

        } catch (Exception e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
