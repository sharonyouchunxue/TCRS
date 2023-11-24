package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.controllers.VehicleController;
import com.schoolproject.tcrs.models.Driver;
import com.schoolproject.tcrs.models.User;
import com.schoolproject.tcrs.models.Vehicle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Objects;

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

        grid.setHgap(100);
        grid.setVgap(70); // Adjust the vertical gap to control the spacing between rows
        grid.setPadding(new Insets(25, 25, 25, 25));

       // Create a label for the system name
        Label systemLabel = new Label("Traffic Citation Reporting System");
        systemLabel.setStyle("-fx-font-size: 30px;");

      // Create buttons for various officer actions
        Button createCitationButton = new Button("Issue Citation");
        createCitationButton.setMinWidth(180);
        Button viewCitationsButton = new Button("View Citations");
        viewCitationsButton.setMinWidth(180);
        Button queryAgencyButton = new Button("Local Agency");
        queryAgencyButton.setMinWidth(180);
        Button logoutButton = new Button("Logout");
        logoutButton.setMinWidth(180);


        // Add labels and buttons to the GridPane
        grid.add(systemLabel, 0, 0, 2, 1);
        // First line of buttons
        grid.add(createCitationButton, 0, 1);
        grid.add(viewCitationsButton, 1, 1);

        // Second line of buttons
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
            // Call the logout method of the User or clear user session, then close the Officer page.
            authenticatedUser.logout();
            primaryStage.close();
        });

        // Create the scene
        Scene scene = new Scene(grid, 800, 600);
        // Add the style.css file
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
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
        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);

        Button submitQueryButton = new Button("Submit");
        submitQueryButton.setMinWidth(150);
        submitQueryButton.setOnAction(e -> {
            String vehiclePlate = vehiclePlateField.getText();
            resultArea.clear();

            new Thread(() -> {
                VehicleController vehicleController = new VehicleController();
                Vehicle vehicle = vehicleController.getVehicleByLicensePlate(vehiclePlate);

                Platform.runLater(() -> {
                    if (vehicle != null) {
                        // Format and display the vehicle information
                        resultArea.setText(formatVehicleDetails(vehicle));
                    } else {
                        resultArea.setText("No results found for the given vehicle plate number.");
                    }
                });
            }).start();
        });

        queryGrid.add(new Label("Vehicle Plate Number:"), 0, 0);
        queryGrid.add(vehiclePlateField, 1, 0);
        queryGrid.add(submitQueryButton, 1, 1);
        queryGrid.add(resultArea, 0, 2, 2, 1); // Span 2 columns for the result area

        Scene queryScene = new Scene(queryGrid, 800, 600);
        // Load the CSS file and add it to the scene's stylesheets
        String cssPath = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        queryScene.getStylesheets().add(cssPath);
        queryStage.setScene(queryScene);
        queryStage.show();
    }

    private String formatVehicleDetails(Vehicle vehicle) {
        return String.format("Vehicle Details:\n" +
                        "ID: %d\n" +
                        "License Plate Number: %s\n" +
                        "Registration Expiry Date: %s\n" +
                        "Make and Model: %s\n" +
                        "Year: %d\n" +
                        "Color: %s\n" +
                        "Status: %s",
                vehicle.getID(),
                vehicle.getLicensePlateNumber(),
                vehicle.getRegistrationExpiryDate().toString(),
                vehicle.getMakeModel(),
                vehicle.getYear(),
                vehicle.getVehicleColour(),
                vehicle.getStatus());
    }



}
