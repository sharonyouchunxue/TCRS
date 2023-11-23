package com.schoolproject.tcrs.models;

import java.sql.Date;

public class Warrants {
    private int warrantID;
    private String licensePlateNumber;
    private Date issueDate;
    private String reason;
    private String driverLicenseNumber;
    private int vehicleID;

    public Warrants(int warrantID, String licensePlateNumber, Date issueDate, String reason,
                   String driverLicenseNumber, int vehicleID) {
        this.warrantID = warrantID;
        this.licensePlateNumber = licensePlateNumber;
        this.issueDate = issueDate;
        this.reason = reason;
        this.driverLicenseNumber = driverLicenseNumber;
        this.vehicleID = vehicleID;
    }

    // Get methods
    public int getWarrantID() {
        return warrantID;
    }

    public void setWarrantID(int warrantID) {
        this.warrantID = warrantID;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }
}
