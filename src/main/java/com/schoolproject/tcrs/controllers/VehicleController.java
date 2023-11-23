package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehicleController {
    public Vehicle getVehicleByID(int ID) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Vehicle WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int retrievedID = resultSet.getInt("ID");
                String licensePlateNumber = resultSet.getString("LicensePlateNumber");
                String registrationExpiryDateStr = resultSet.getString("RegistrationExpiryDate");
                String makeModel = resultSet.getString("MakeModel");
                int year = resultSet.getInt("Year");
                String vehicleColour = resultSet.getString("VehicleColour");
                String status = resultSet.getString("Status");

                // Convert the date string from the database to a java.util.Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date registrationExpiryDate = dateFormat.parse(registrationExpiryDateStr);

                Vehicle vehicle = new Vehicle(retrievedID, licensePlateNumber, registrationExpiryDate,
                        makeModel, year, vehicleColour, status);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return vehicle;
            } else {
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return null;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Vehicle";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int ID = resultSet.getInt("ID");
                String licensePlateNumber = resultSet.getString("LicensePlateNumber");
                String registrationExpiryDateStr = resultSet.getString("RegistrationExpiryDate");
                String makeModel = resultSet.getString("MakeModel");
                int year = resultSet.getInt("Year");
                String vehicleColour = resultSet.getString("VehicleColour");
                String status = resultSet.getString("Status");

                // Convert the date string from the database to a java.util.Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date registrationExpiryDate = dateFormat.parse(registrationExpiryDateStr);

                Vehicle vehicle = new Vehicle(ID, licensePlateNumber, registrationExpiryDate,
                        makeModel, year, vehicleColour, status);

                vehicles.add(vehicle);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    // Method to retrieve a Vehicle by LicensePlateNumber
    public Vehicle getVehicleByLicensePlate(String licensePlateNumber) {
        try {
            // Get a database connection from the DatabaseConnector class
            Connection connection = DatabaseConnector.getConnection();

            // Prepare the SQL query
            String sql = "SELECT * FROM Vehicle WHERE LicensePlateNumber = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, licensePlateNumber);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if a vehicle with the given LicensePlateNumber was found
            if (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String registrationExpiryDateStr = resultSet.getString("RegistrationExpiryDate");
                String makeModel = resultSet.getString("MakeModel");
                int year = resultSet.getInt("Year");
                String vehicleColour = resultSet.getString("VehicleColour");
                String status = resultSet.getString("Status");

                // Convert the date string from the database to a java.util.Date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date registrationExpiryDate = dateFormat.parse(registrationExpiryDateStr);

                // Create a Vehicle object with the retrieved information
                Vehicle vehicle = new Vehicle(id, licensePlateNumber, registrationExpiryDate, makeModel, year, vehicleColour, status);

                // Close resources
                resultSet.close();
                preparedStatement.close();
                connection.close();

                return vehicle;
            } else {
                // Vehicle not found
                resultSet.close();
                preparedStatement.close();
                connection.close();
                return null;
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
