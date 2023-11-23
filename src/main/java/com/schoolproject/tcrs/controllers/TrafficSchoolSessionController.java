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

}
