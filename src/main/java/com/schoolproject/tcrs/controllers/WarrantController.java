package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Warrants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarrantController {
    public Warrants getWarrantById(int warrantID) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Warrants WHERE WarrantID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, warrantID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int retrievedWarrantID = resultSet.getInt("WarrantID");
                String licensePlateNumber = resultSet.getString("LicensePlateNumber");
                Date issueDate = resultSet.getDate("IssueDate");
                String reason = resultSet.getString("Reason");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                int vehicleID = resultSet.getInt("VehicleID");

                Warrants warrant = new Warrants(retrievedWarrantID, licensePlateNumber, issueDate,
                        reason, driverLicenseNumber, vehicleID);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return warrant;
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

    public List<Warrants> getAllWarrants() {
        List<Warrants> warrants = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Warrants";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int warrantID = resultSet.getInt("WarrantID");
                String licensePlateNumber = resultSet.getString("LicensePlateNumber");
                Date issueDate = resultSet.getDate("IssueDate");
                String reason = resultSet.getString("Reason");
                String driverLicenseNumber = resultSet.getString("DriverLicenseNumber");
                int vehicleID = resultSet.getInt("VehicleID");

                Warrants warrant = new Warrants(warrantID, licensePlateNumber, issueDate,
                        reason, driverLicenseNumber, vehicleID);

                warrants.add(warrant);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return warrants;
    }

}
