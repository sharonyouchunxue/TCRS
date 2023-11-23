package com.schoolproject.tcrs.models;

import java.util.Date;

public class Vehicle {
    private int ID;
    private String licensePlateNumber;
    private Date registrationExpiryDate;
    private String makeModel;
    private int year;
    private String vehicleColour;
    private String status;

    public Vehicle(int ID, String licensePlateNumber, Date registrationExpiryDate, String makeModel, int year, String vehicleColour, String status) {
        this.ID = ID;
        this.licensePlateNumber = licensePlateNumber;
        this.registrationExpiryDate = registrationExpiryDate;
        this.makeModel = makeModel;
        this.year = year;
        this.vehicleColour = vehicleColour;
        this.status = status;
    }

    public int getID() {
        return ID;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public Date getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public int getYear() {
        return year;
    }

    public String getVehicleColour() {
        return vehicleColour;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "ID=" + ID +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                ", registrationExpiryDate=" + registrationExpiryDate +
                ", makeModel='" + makeModel + '\'' +
                ", year=" + year +
                ", vehicleColour='" + vehicleColour + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
