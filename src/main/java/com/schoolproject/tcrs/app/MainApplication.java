package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.*;
import com.schoolproject.tcrs.models.*;


import java.util.Date;

public class MainApplication {
    public static void main(String[] args) {
        // Test User authentication
        testUserAuthentication();

        // Test PoliceOfficer data retrieval
        testPoliceOfficerData();

        // Test Driver data retrieval
        testDriverData();

        // Test Violation data retrieval
        testViolationData();

        // Test Vehicle data retrieval
        testVehicleData();

        // Test Warrant data retrieval
        testWarrantData();

        // Test TrafficSchoolSession data retrieval
        testTrafficSchoolSessionData();

        // Test Citation data retrieval
        testCitationData();
    }

    private static void testUserAuthentication() {
        UserController userController = new UserController();

        // Use actual username and password hash values for testing
        String username = "john_doe";
        String passwordHash = "hashedpassword1";

        User user = userController.authenticateUser(username, passwordHash);
        if (user != null) {
            System.out.println("Authentication successful for user: " + user.getUsername());
            // Further logic for an authenticated user
        } else {
            System.out.println("Authentication failed for username: " + username);
        }
    }

    private static void testPoliceOfficerData() {
        PoliceOfficerController policeOfficerController = new PoliceOfficerController();

        // Test getting a PoliceOfficer by badge number
        int badgeNumber = 123;
        PoliceOfficer policeOfficer = policeOfficerController.getPoliceOfficerByBadgeNumber(badgeNumber);

        if (policeOfficer != null) {
            System.out.println("Police Officer found: " + policeOfficer.getName());
        } else {
            System.out.println("Police Officer not found for badge number: " + badgeNumber);
        }
    }

    private static void testDriverData() {
        DriverController driverController = new DriverController();

        // Test getting a Driver by license number
        String licenseNumber = "D1029384";
        Driver driver = driverController.getDriverByLicenseNumber(licenseNumber);

        if (driver != null) {
            System.out.println("Driver found: " + driver.getFirstName() + " " + driver.getLastName());
        } else {
            System.out.println("Driver not found for license number: " + licenseNumber);
        }
    }

    private static void testViolationData() {
        ViolationController violationController = new ViolationController();

        // Test getting a Violation by violation code
        int violationCode = 10;
        Violation violation = violationController.getViolationByCode(violationCode);

        if (violation != null) {
            System.out.println("Violation found: " + violation.getDescription());
        } else {
            System.out.println("Violation not found for violation code: " + violationCode);
        }
    }

    private static void testVehicleData() {
        VehicleController vehicleController = new VehicleController();

        // Test getting a Vehicle by license plate number
        String licensePlateNumber = "GTA2048";
        Vehicle vehicle = vehicleController.getVehicleByLicensePlate(licensePlateNumber);

        if (vehicle != null) {
            System.out.println("Vehicle found: " + vehicle.getMakeModel());
        } else {
            System.out.println("Vehicle not found for license plate number: " + licensePlateNumber);
        }
    }

    private static void testWarrantData() {
        WarrantController warrantController = new WarrantController();

        // Test getting a Warrant by warrant ID
        int warrantID = 1;
        Warrants warrant = warrantController.getWarrantById(warrantID);

        if (warrant != null) {
            System.out.println("Warrant found for license plate: " + warrant.getClass());
        } else {
            System.out.println("Warrant not found for warrant ID: " + warrantID);
        }
    }

    private static void testTrafficSchoolSessionData() {
        TrafficSchoolSessionController trafficSchoolSessionController = new TrafficSchoolSessionController();

        // Test getting a TrafficSchoolSession by registration ID
        int registrationID = 3;
        TrafficSchoolSession trafficSchoolSession = trafficSchoolSessionController.getTrafficSchoolSessionById(registrationID);

        if (trafficSchoolSession != null) {
            System.out.println("Traffic School Session found for driver: " + trafficSchoolSession.getDriverLicenseNumber());
        } else {
            System.out.println("Traffic School Session not found for registration ID: " + registrationID);
        }
    }

    private static void testCitationData() {
        CitationController citationController = new CitationController();

        // Test getting a Citation by citation number
        int citationNumber = 1;
        Citation citation = citationController.getCitationByNumber(citationNumber);

        if (citation != null) {
            System.out.println("Citation found for driver: " + citation.getDriverLicenseNumber());
        } else {
            System.out.println("Citation not found for citation number: " + citationNumber);
        }
    }
}
