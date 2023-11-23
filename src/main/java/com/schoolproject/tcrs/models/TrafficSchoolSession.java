package com.schoolproject.tcrs.models;

import java.util.Date;

public class TrafficSchoolSession {
    private int registrationID;
    private String driverLicenseNumber;
    private int citationCitationNumber;
    private int totalHours;
    private Date schedule;
    private String location;
    private String completionStatus;

    public TrafficSchoolSession(int registrationID, String driverLicenseNumber, int citationCitationNumber,
                                int totalHours, Date schedule, String location, String completionStatus) {
        this.registrationID = registrationID;
        this.driverLicenseNumber = driverLicenseNumber;
        this.citationCitationNumber = citationCitationNumber;
        this.totalHours = totalHours;
        this.schedule = schedule;
        this.location = location;
        this.completionStatus = completionStatus;
    }

    // Getter and setter methods here

    @Override
    public String toString() {
        return "TrafficSchoolSession{" +
                "registrationID=" + registrationID +
                ", driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ", citationCitationNumber=" + citationCitationNumber +
                ", totalHours=" + totalHours +
                ", schedule=" + schedule +
                ", location='" + location + '\'' +
                ", completionStatus='" + completionStatus + '\'' +
                '}';
    }

    public String getDriverLicenseNumber() {
        return driverLicenseNumber;
    }
}
