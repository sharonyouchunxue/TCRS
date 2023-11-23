package com.schoolproject.tcrs.models;

public class PoliceOfficer {
    private int id;
    private String name;
    private String badgeNumber;
    private String password;

    public PoliceOfficer(int id, String name, String badgeNumber, String password1) {
        this.id = id;
        this.name = name;
        this.badgeNumber = badgeNumber;
    }

    public PoliceOfficer(int retrievedBadgeNumber, String name, int userID) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
