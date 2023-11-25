package com.schoolproject.tcrs.models;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role;
    private Integer badgeNumber; // Nullable for non-officer users

    // Flag to represent if the user is logged in
    private boolean isLoggedIn;

    // Constructor, getters, and setters
    public User(int userId, String username, String passwordHash, String role, Integer badgeNumber) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.badgeNumber = badgeNumber;
        this.isLoggedIn = false; // User is not logged in by default
    }

    // Login method
    public boolean login() {
        // Placeholder: Add authentication logic
        this.isLoggedIn = true; // Set the flag to true when logged in
        return true;
    }

    // Logout method
    public void logout() {
        // Reset the flag to false to represent that the user is logged out
        this.isLoggedIn = false;
    }

    // Method to check if the user is logged in
    public boolean isLoggedIn() {
        return this.isLoggedIn;
    }

    public String getUsername() {
        return this.username;
    }

    public int getUserId() {
        return this.userId;
    }

    public String getRole() {
        return this.role;
    }

    public Integer getBadgeNumber() {
        return this.badgeNumber;
    }
}
