package com.schoolproject.tcrs.controllers;
import com.schoolproject.tcrs.database.DatabaseConnector;

import com.schoolproject.tcrs.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    // Method to logout a user
    public void logoutUser(User user) {
        user.logout();
    }

    public User authenticateUser(String username, String passwordHash) {
        User user = null;
        String sql = "SELECT * FROM User WHERE username = ? AND passwordHash = ?";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, passwordHash);
            System.out.println("Executing query: " + pstmt);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("ID");
                    String retrievedUsername = rs.getString("Username");
                    String retrievedPasswordHash = rs.getString("PasswordHash");
                    String role = rs.getString("Role");
                    Integer badgeNumber = (Integer) rs.getObject("BadgeNumber"); // Can be null

                    // Create a new User object with the retrieved data
                    user = new User(id, retrievedUsername, retrievedPasswordHash, role, badgeNumber);

                    // Print out the user information
                    System.out.println("User ID: " + user.getUserId());
                    System.out.println("Username: " + user.getUsername());
                    System.out.println("Role: " + user.getRole());
                    System.out.println("Badge Number: " + user.getBadgeNumber());
                    // ... any other user information you wish to display
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        }
        return user;
    }

    public void displayAllUsers() {
        String sql = "SELECT * FROM User";

        try (Connection conn = DatabaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getString("Role"),
                        (Integer) rs.getObject("BadgeNumber") // Can handle nulls
                );

                // Now print the user's information
                System.out.println("ID: " + user.getUserId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("Badge Number: " + user.getBadgeNumber());
                System.out.println("---------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQLException: " + e.getMessage());
        }
    }



}
