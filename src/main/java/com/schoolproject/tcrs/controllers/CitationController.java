package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Citation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CitationController {
    public Citation getCitationByNumber(int citationNumber) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Citation WHERE CitationNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, citationNumber);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int retrievedCitationNumber = resultSet.getInt("CitationNumber");
                int policeOfficerBadgeNumber = resultSet.getInt("PoliceOfficerBadgeNumber");
                int violationCode = resultSet.getInt("ViolationCode");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                String vehicleID = resultSet.getString("VehicleID"); // Changed data type to String
                Date date = resultSet.getDate("Date");
                Time time = resultSet.getTime("Time");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");

                Citation citation = new Citation(retrievedCitationNumber, policeOfficerBadgeNumber,
                        violationCode, driverLicenseNumber, vehicleID, date, time, location, type);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return citation;
            } else {
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

    public List<Citation> getAllCitations() {
        List<Citation> citations = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Citation";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int citationNumber = resultSet.getInt("CitationNumber");
                int policeOfficerBadgeNumber = resultSet.getInt("PoliceOfficerBadgeNumber");
                int violationCode = resultSet.getInt("ViolationCode");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                String vehicleID = resultSet.getString("VehicleID"); // Changed data type to String
                Date date = resultSet.getDate("Date");
                Time time = resultSet.getTime("Time");
                String location = resultSet.getString("Location");
                String type = resultSet.getString("Type");

                Citation citation = new Citation(citationNumber, policeOfficerBadgeNumber,
                        violationCode, driverLicenseNumber, vehicleID, date, time, location, type);

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

    public ObservableList<Citation> getCitationsByLicenseNumber(String licenseNumber) {
        ObservableList<Citation> citations = FXCollections.observableArrayList();
        try (Connection connection = DatabaseConnector.getConnection()) {
            String sql = "SELECT * FROM Citation WHERE DriverLicenseNumber = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, licenseNumber);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return citations;
    }

    // Add the saveCitation method here
    public void saveCitation(Citation citation) {
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            connection.setAutoCommit(false); // Disable auto-commit mode

            String insertQuery = "INSERT INTO Citation (CitationNumber, PoliceOfficerBadgeNumber, ViolationCode, DriverLicenseNumber, VehicleID, Date, Time, Location, Type) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setInt(1, citation.getCitationNumber());
                preparedStatement.setInt(2, citation.getPoliceOfficerBadgeNumber());
                preparedStatement.setInt(3, citation.getViolationCode());
                preparedStatement.setString(4, citation.getDriverLicenseNumber());
                preparedStatement.setString(5, citation.getVehicleID());
                preparedStatement.setDate(6, citation.getDate());
                preparedStatement.setTime(7, citation.getTime());
                preparedStatement.setString(8, citation.getLocation());
                preparedStatement.setString(9, citation.getType());

                preparedStatement.executeUpdate();

                connection.commit(); // Commit the transaction
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Unable to save the citation. A citation with the same number already exists.");
            if (connection != null) {
                try {
                    connection.rollback(); // Roll back the transaction
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Roll back the transaction in case of an error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // Re-enable auto-commit mode
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
