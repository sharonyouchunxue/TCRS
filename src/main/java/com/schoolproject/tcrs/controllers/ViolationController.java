package com.schoolproject.tcrs.controllers;

import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Violation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViolationController {
    public Violation getViolationByCode(int violationCode) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Violation WHERE ViolationCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, violationCode);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int retrievedViolationCode = resultSet.getInt("ViolationCode");
                String description = resultSet.getString("Description");
                double fineAmount = resultSet.getDouble("FineAmount");

                Violation violation = new Violation(retrievedViolationCode, description, fineAmount);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return violation;
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

    public List<Violation> getAllViolations() {
        List<Violation> violations = new ArrayList<>();
        try {
            Connection connection = DatabaseConnector.getConnection();
            String sql = "SELECT * FROM Violation";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int violationCode = resultSet.getInt("ViolationCode");
                String description = resultSet.getString("Description");
                double fineAmount = resultSet.getDouble("FineAmount");

                Violation violation = new Violation(violationCode, description, fineAmount);

                violations.add(violation);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return violations;
    }

}
