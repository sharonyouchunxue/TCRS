package com.schoolproject.tcrs.models;

import java.sql.Date;
import java.sql.Time;

public class Citation {
    private int citationNumber;
    private int policeOfficerBadgeNumber;
    private int violationCode;
    private String driverLicenseNumber;
    private String vehicleID;
    private Date date;
    private Time time;
    private String location;
    private String type;

    public Citation(int citationNumber, int policeOfficerBadgeNumber, int violationCode,
                    String driverLicenseNumber, String vehicleID, Date date, Time time, String location, String type) {
        this.citationNumber = citationNumber;
        this.policeOfficerBadgeNumber = policeOfficerBadgeNumber;
        this.violationCode = violationCode;
        this.driverLicenseNumber = driverLicenseNumber;
        this.vehicleID = vehicleID;
        this.date = date;
        this.time = time;
        this.location = location;
        this.type = type;
    }

    // Getters and setters
    public int getCitationNumber() {
        return citationNumber;
    }

    public void setCitationNumber(int citationNumber) {
        this.citationNumber = citationNumber;
    }

    public int getPoliceOfficerBadgeNumber() {
        return policeOfficerBadgeNumber;
    }

    public void setPoliceOfficerBadgeNumber(int policeOfficerBadgeNumber) {
        this.policeOfficerBadgeNumber = policeOfficerBadgeNumber;
    }

    public int getViolationCode() {
        return violationCode;
    }

    public void setViolationCode(int violationCode) {
        this.violationCode = violationCode;
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }

    public void setDriverLicenseNumber(String driverLicenseNumber) {
        this.driverLicenseNumber = driverLicenseNumber;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
