package com.schoolproject.tcrs.models;

import java.util.Date;

public class Driver {
    private String licenseNumber;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private Date dob;
    private String height;
    private String eyeColour;
    private Date licenseExpiryDate;
    private String status;

    // Constructor, getters, and setters
    public Driver(String licenseNumber, String firstName, String lastName, String address,
                  String phoneNumber, Date dob, String height, String eyeColour,
                  Date licenseExpiryDate, String status) {
        this.licenseNumber = String.valueOf(licenseNumber);
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dob = dob;
        this.height = height;
        this.eyeColour = eyeColour;
        this.licenseExpiryDate = licenseExpiryDate;
        this.status = status;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(int licenseNumber) {
        this.licenseNumber = String.valueOf(licenseNumber);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public Date getLicenseExpiryDate() {
        return licenseExpiryDate;
    }

    public void setLicenseExpiryDate(Date licenseExpiryDate) {
        this.licenseExpiryDate = licenseExpiryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
