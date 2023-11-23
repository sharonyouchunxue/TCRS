package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.models.Driver;
import com.schoolproject.tcrs.models.User;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class OfficerPageUI extends Application {
    private User authenticatedUser;

    public OfficerPageUI() {
    }

    public OfficerPageUI(User authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Traffic Citation Reporting System - Officer");

        // Create a GridPane for layout
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create a label for the system name
        Label systemLabel = new Label("Traffic Citation Reporting System");
        systemLabel.setStyle("-fx-font-size: 20px;");

        // Create buttons for various officer actions
        Button createCitationButton = new Button("Issue Citation");
        Button viewCitationsButton = new Button("View Citations");
        Button queryAgencyButton = new Button("Query Local Agency");
        Button logoutButton = new Button("Logout");

        // Add labels and buttons to the GridPane
        grid.add(systemLabel, 0, 0, 2, 1);
        grid.add(createCitationButton, 0, 1);
        grid.add(viewCitationsButton, 1, 1);
        grid.add(queryAgencyButton, 0, 2);
        grid.add(logoutButton, 1, 2);

        // Set actions for the buttons
        createCitationButton.setOnAction(e -> {
            // Open the citation issuance form
            openCitationForm(primaryStage);
        });

        viewCitationsButton.setOnAction(e -> {
            // Redirect to the officer's "View Citations" page
            openViewCitationsPage();
        });

        queryAgencyButton.setOnAction(e -> openQueryAgencyForm());

        logoutButton.setOnAction(e -> {
            // Handle the action to logout
            // Call the logout method of the User or clear user session, then close the Officer page.
            authenticatedUser.logout(); // Assuming you have a logout method in your User class.
            primaryStage.close();
        });

        // Create the scene
        Scene scene = new Scene(grid, 800, 600);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void openCitationForm(Stage primaryStage) {
        CitationFormUI issueCitationForm = new CitationFormUI();
        issueCitationForm.start(primaryStage);
    }

    private void openViewCitationsPage() {
        openViewCitationsPage viewCitationsUI = new openViewCitationsPage();
        viewCitationsUI.start(new Stage());
    }

    private void openQueryAgencyForm() {
        Stage queryStage = new Stage();
        queryStage.setTitle("Query Local Agency");

        GridPane queryGrid = new GridPane();
        queryGrid.setAlignment(javafx.geometry.Pos.CENTER);
        queryGrid.setHgap(10);
        queryGrid.setVgap(10);
        queryGrid.setPadding(new Insets(25, 25, 25, 25));

        TextField vehiclePlateField = new TextField();
        vehiclePlateField.setPromptText("Vehicle Plate Number");
        TextField driverNameField = new TextField();
        driverNameField.setPromptText("Driver's Full Name");
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button submitQueryButton = new Button("Submit Query");
        submitQueryButton.setOnAction(e -> {
            String vehiclePlate = vehiclePlateField.getText();
            String driverName = driverNameField.getText();
            resultArea.clear();

            new Thread(() -> {
                DriverController driverController = new DriverController();
                // Assume getDriverDetails method needs modification to accept two parameters
                Driver driverInfo = driverController.getDriverDetails(vehiclePlate, driverName);

                Platform.runLater(() -> {
                    if (driverInfo != null) {
                        // Format and display the driver information
                        resultArea.setText(formatDriverDetails(driverInfo));
                    } else {
                        resultArea.setText("No results found for the given details.");
                    }
                });
            }).start();
        });

        queryGrid.add(new Label("Vehicle Plate Number:"), 0, 0);
        queryGrid.add(vehiclePlateField, 1, 0);
        queryGrid.add(new Label("Driver's Full Name:"), 0, 1);
        queryGrid.add(driverNameField, 1, 1);
        queryGrid.add(submitQueryButton, 1, 2);
        queryGrid.add(resultArea, 0, 3, 2, 1); // Span 2 columns for the result area

        Scene queryScene = new Scene(queryGrid, 800, 600);
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    private String formatDriverDetails(Driver driver) {
        return String.format("Driver Details:\n" +
                        "License Number: %s\n" +
                        "Name: %s %s\n" +
                        "Address: %s\n" +
                        "Phone Number: %s\n" +
                        "Date of Birth: %s\n" +
                        "Height: %s\n" +
                        "Eye Color: %s\n" +
                        "License Expiry Date: %s\n" +
                        "Status: %s",
                driver.getLicenseNumber(),
                driver.getFirstName(),
                driver.getLastName(),
                driver.getAddress(),
                driver.getPhoneNumber(),
                driver.getDob(),
                driver.getHeight(),
                driver.getEyeColour(),
                driver.getLicenseExpiryDate(),
                driver.getStatus());
    }
}
