package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Citation;
import com.schoolproject.tcrs.models.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class DriverController {

    public Driver getDriverDetails(String vehiclePlate, String fullName) {
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "SELECT d.* FROM Driver d " +
                             "JOIN Vehicle v ON d.LicenseNumber = v.DriverLicenseNumber " +
                             "WHERE v.PlateNumber = ? AND CONCAT(d.FirstName, ' ', d.LastName) = ?")) {

            preparedStatement.setString(1, vehiclePlate);
            preparedStatement.setString(2, fullName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String licenseNumber = resultSet.getString("LicenseNumber");
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String address = resultSet.getString("Street") + ", " + resultSet.getString("City");
                    String phoneNumber = resultSet.getString("PhoneNum");
                    Date dob = resultSet.getDate("DOB");
                    String height = resultSet.getString("Height");
                    String eyeColour = resultSet.getString("EyeColor");
                    Date licenseExpiryDate = resultSet.getDate("LicenseExpiryDate");
                    String status = resultSet.getString("Status");

                    return new Driver(licenseNumber, firstName, lastName, address,
                            phoneNumber, dob, height, eyeColour, licenseExpiryDate, status);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // If driver not found or exception thrown
    }

    public String notify(String vehiclePlate, String fullName) {
        // Perform the database query using the vehicle plate and full name
        Driver driver = getDriverDetails(vehiclePlate, fullName);

        if (driver != null) {
            // Driver found, return driver information as a formatted string
            return "Driver Details:\n" +
                    "License Number: " + driver.getLicenseNumber() + "\n" +
                    "Name: " + driver.getFirstName() + " " + driver.getLastName() + "\n" +
                    "Address: " + driver.getAddress() + "\n" +
                    "Phone Number: " + driver.getPhoneNumber() + "\n" +
                    "Date of Birth: " + driver.getDob() + "\n" +
                    "Height: " + driver.getHeight() + "\n" +
                    "Eye Color: " + driver.getEyeColour() + "\n" +
                    "License Expiry Date: " + driver.getLicenseExpiryDate() + "\n" +
                    "Status: " + driver.getStatus();
        } else {
            // No driver found with the given vehicle plate and full name
            return "Driver not found for the given vehicle plate: " + vehiclePlate + " and full name: " + fullName;
        }
    }

    public Driver getDriverByLicenseNumber(String licenseNumber) {
        try {
            // Get a database connection from the DatabaseConnector class
            Connection connection = DatabaseConnector.getConnection();

            // Prepare the SQL query to search by license number
            String sql = "SELECT * FROM Driver WHERE LicenseNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, licenseNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a driver with the given license number was found
            if (resultSet.next()) {
                String retrievedLicenseNumber = resultSet.getString("LicenseNumber"); // Retrieve as String
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String address = resultSet.getString("Street") + ", " + resultSet.getString("City");
                String phoneNumber = resultSet.getString("PhoneNum");
                Date dob = resultSet.getDate("DOB");
                String height = resultSet.getString("Height");
                String eyeColour = resultSet.getString("EyeColor");
                Date licenseExpiryDate = resultSet.getDate("LicenseExpiryDate");
                String status = resultSet.getString("Status");

                // Create a Driver object with the retrieved information
                Driver driver = new Driver(retrievedLicenseNumber, firstName, lastName,
                        address, phoneNumber, dob, height, eyeColour, licenseExpiryDate, status);

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return driver;
            } else {
                // Driver not found
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ObservableList<Citation> getCitationsByLicenseNumber(String licenseNumber) {
        ObservableList<Citation> citations = FXCollections.observableArrayList();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Citation WHERE DriverLicenseNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, licenseNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming you have a Citation class with a constructor matching these parameters
                Citation citation = new Citation(
                        resultSet.getInt("CitationNumber"),
                        resultSet.getInt("PoliceOfficerBadgeNumber"),
                        resultSet.getInt("ViolationCode"),
                        resultSet.getString("DriverLicenseNumber"),
                        resultSet.getString("VehicleID"),
                        resultSet.getDate("Date"),
                        resultSet.getTime("Time"),
                        resultSet.getString("Location"),
                        resultSet.getString("Type")
                );
                citations.add(citation);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citations;
    }


    // Check if a driver with the given license number exists
    public boolean doesDriverExist(String driverLicenseNumber) {
        try {
            // Get a database connection from the DatabaseConnector class
            Connection connection = DatabaseConnector.getConnection();

            // Prepare the SQL query to search by license number
            String sql = "SELECT COUNT(*) FROM Driver WHERE LicenseNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, driverLicenseNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a driver with the given license number was found
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return count > 0;
            } else {
                // Driver not found
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
