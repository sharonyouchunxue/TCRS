package com.schoolproject.tcrs.models;

public class User {
    private int userId;
    private String username;
    private String passwordHash;
    private String role;
    private Integer badgeNumber; // Nullable for non-officer users

    // Constructor, getters, and setters
    public User(int userId, String username, String passwordHash, String role, Integer badgeNumber) {
        this.userId = userId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.role = role;
        this.badgeNumber = badgeNumber;
    }

    // Login method
    public boolean login() {
        // Placeholder: Add authentication logic
        return true;
    }

    // Logout method
    public void logout() {
        // Placeholder: Add logout logic
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
