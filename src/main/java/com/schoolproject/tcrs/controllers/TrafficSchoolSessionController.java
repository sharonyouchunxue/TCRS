package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.TrafficSchoolSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrafficSchoolSessionController {
    public TrafficSchoolSession getTrafficSchoolSessionById(int registrationID) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM TrafficSchoolSession WHERE RegistrationID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, registrationID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int retrievedRegistrationID = resultSet.getInt("RegistrationID");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                int citationCitationNumber = resultSet.getInt("CitationCitationNumber");
                int totalHours = resultSet.getInt("TotalHours");
                Date schedule = resultSet.getDate("Schedule");
                String location = resultSet.getString("Location");
                String completionStatus = resultSet.getString("CompletionStatus");

                TrafficSchoolSession trafficSchoolSession = new TrafficSchoolSession(
                        retrievedRegistrationID,
                        driverLicenseNumber,
                        citationCitationNumber,
                        totalHours,
                        schedule,
                        location,
                        completionStatus
                );

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return trafficSchoolSession;
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

    public List<TrafficSchoolSession> getAllTrafficSchoolSessions() {
        List<TrafficSchoolSession> trafficSchoolSessions = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM TrafficSchoolSession";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int registrationID = resultSet.getInt("RegistrationID");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                int citationCitationNumber = resultSet.getInt("CitationCitationNumber");
                int totalHours = resultSet.getInt("TotalHours");
                Date schedule = resultSet.getDate("Schedule");
                String location = resultSet.getString("Location");
                String completionStatus = resultSet.getString("CompletionStatus");

                TrafficSchoolSession trafficSchoolSession = new TrafficSchoolSession(
                        registrationID,
                        driverLicenseNumber,
                        citationCitationNumber,
                        totalHours,
                        schedule,
                        location,
                        completionStatus
                );

                trafficSchoolSessions.add(trafficSchoolSession);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trafficSchoolSessions;
    }

    public List<TrafficSchoolSession> getAllAvailableTrafficSchoolSessions() {
        List<TrafficSchoolSession> availableSessions = new ArrayList<>();
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TrafficSchoolSession WHERE /* your condition for available sessions */")) {
            // You should add a WHERE clause in your SQL to filter only available sessions
            // For example, WHERE `Schedule` > CURRENT_DATE and `SeatsAvailable` > 0
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    //... extract data from resultSet and add to availableSessions
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return availableSessions;
    }

    public boolean registerDriverForSession(TrafficSchoolSession session, String driverLicenseNumber) {
        // This method should insert a record into the database to register a driver for the given session
        boolean registrationSuccess = false;
        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO DriverTrafficSchoolSession (DriverLicenseNumber, SessionID) VALUES (?, ?)")) {
            preparedStatement.setString(1, driverLicenseNumber);
            preparedStatement.setInt(2, session.getRegistrationID());
            // Execute update and check if the update count is > 0
            registrationSuccess = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationSuccess;
    }

    public List<String> getAvailableSchedulesForSession(int registrationID) {
        List<String> availableSchedules = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Initialize the database connection
            connection = DatabaseConnector.getConnection();

            // Prepare the SQL query to fetch the available schedules
            String sql = "SELECT Schedule FROM TrafficSchoolSession WHERE RegistrationID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, registrationID);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // Process the result set
            while (resultSet.next()) {
                // Assuming the schedule is stored as a String in the database
                String schedule = resultSet.getString("Schedule");
                availableSchedules.add(schedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close all database resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return availableSchedules;
    }
}
