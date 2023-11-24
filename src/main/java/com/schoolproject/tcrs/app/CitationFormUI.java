package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.CitationController;
import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.database.DatabaseConnector;
import com.schoolproject.tcrs.models.Citation;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class CitationFormUI extends Application {
    private Stage primaryStage;
    private TextField citationNumberField;
    private TextField officerBadgeNumberField;
    private TextField violationCodeField;
    private TextField driverLicenseNumberField;
    private TextField vehicleIDField;
    private DatePicker dateDatePicker;
    private TextField timeTextField;
    private TextField locationField;
    private ComboBox<String> citationTypeComboBox;

    public CitationFormUI() {
    }

    public CitationFormUI(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("Citation Form");

        GridPane grid = createCitationForm();
        Scene scene = new Scene(grid, 800, 600);
        // Add the style.css file
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private GridPane createCitationForm() {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("Citation Form");
        titleLabel.setStyle("-fx-font-size: 20px;");

        citationNumberField = new TextField();
        officerBadgeNumberField = new TextField();
        violationCodeField = new TextField();
        driverLicenseNumberField = new TextField();
        vehicleIDField = new TextField();
        dateDatePicker = new DatePicker();
        timeTextField = new TextField();
        locationField = new TextField();
        citationTypeComboBox = new ComboBox<>();

        citationTypeComboBox.getItems().addAll("Moving Violation", "Parking Ticket", "Fix-it Ticket");

        Button submitButton = new Button("Submit");
        Button printButton = new Button("Print Citation");

        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(new Label("Citation Number:"), 0, 1);
        grid.add(citationNumberField, 1, 1);
        grid.add(new Label("Officer Badge Number:"), 0, 2);
        grid.add(officerBadgeNumberField, 1, 2);
        grid.add(new Label("Violation Code:"), 0, 3);
        grid.add(violationCodeField, 1, 3);
        grid.add(new Label("Driver License Number:"), 0, 4);
        grid.add(driverLicenseNumberField, 1, 4);
        grid.add(new Label("Vehicle ID:"), 0, 5);
        grid.add(vehicleIDField, 1, 5);
        grid.add(new Label("Date:"), 0, 6);
        grid.add(dateDatePicker, 1, 6);
        grid.add(new Label("Time:"), 0, 7);
        grid.add(timeTextField, 1, 7);
        grid.add(new Label("Location:"), 0, 8);
        grid.add(locationField, 1, 8);
        grid.add(new Label("Citation Type:"), 0, 9);
        grid.add(citationTypeComboBox, 1, 9);

        grid.add(submitButton, 0, 10);
        grid.add(printButton, 1, 10);

        // Handle submission when the Submit button is clicked
        submitButton.setOnAction(e -> {
            saveCitation();
        });

        // Handle printing when the Print button is clicked
        printButton.setOnAction(e -> {
            printCitation();
        });

        return grid;
    }

    private void saveCitation() {
        try {
            int citationNumber = Integer.parseInt(citationNumberField.getText());
            int officerBadgeNumber = Integer.parseInt(officerBadgeNumberField.getText());
            int violationCode = Integer.parseInt(violationCodeField.getText());
            String driverLicenseNumber = driverLicenseNumberField.getText();
            String vehicleID = vehicleIDField.getText();

            // Check if the driver with the provided license number exists
            DriverController driverController = new DriverController();
            if (!driverController.doesDriverExist(driverLicenseNumber)) {
                showAlert("Driver Not Found", "The driver with the provided License Number does not exist.");
                return; // Exit the method without saving the citation
            }

            LocalDate localDate = dateDatePicker.getValue();
            Date date = Date.valueOf(localDate);

            LocalTime localTime = LocalTime.parse(timeTextField.getText());
            Time time = Time.valueOf(localTime);

            String location = locationField.getText();
            String citationType = citationTypeComboBox.getValue();

            Citation newCitation = new Citation(citationNumber, officerBadgeNumber, violationCode,
                    driverLicenseNumber, vehicleID, date, time, location, citationType);

            CitationController citationController = new CitationController();
            citationController.saveCitation(newCitation);

            showAlert("Citation Saved", "The citation has been saved successfully.");
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please enter valid numbers for Citation Number, Officer Badge Number, and Violation Code.");
        } catch (DateTimeParseException e) {
            showAlert("Invalid Date/Time", "Please enter a valid date and time.");
        }
    }


    private int generateRandomCitationNumber() {
        // Generate a random number between 1 and 100000
        return (int) (Math.random() * 100000) + 1;
    }

    private void printCitation() {
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
