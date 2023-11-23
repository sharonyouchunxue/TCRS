package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.PoliceOfficer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PoliceOfficerController {

    // Method to retrieve a PoliceOfficer by badge number
    public PoliceOfficer getPoliceOfficerByBadgeNumber(int badgeNumber) {
        try {
            // Get a database connection from the DatabaseConnector class
            Connection connection = DatabaseConnector.getConnection();

            // Prepare the SQL query with the correct column name
            String sql = "SELECT * FROM PoliceOfficer WHERE badgeNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, badgeNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a police officer with the given badge number was found
            if (resultSet.next()) {
                int retrievedBadgeNumber = resultSet.getInt("badgeNumber");
                String name = resultSet.getString("Name");
                int userID = resultSet.getInt("UserID");

                // Create a PoliceOfficer object with the retrieved information
                PoliceOfficer policeOfficer = new PoliceOfficer(retrievedBadgeNumber, name, userID);

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return policeOfficer;
            } else {
                // Police officer not found
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

}
