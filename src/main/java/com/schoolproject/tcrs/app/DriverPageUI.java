package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.controllers.TrafficSchoolSessionController;
import com.schoolproject.tcrs.models.Citation;
import com.schoolproject.tcrs.models.TrafficSchoolSession;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DriverPageUI extends Application {
    private DriverController driverController;
    private TrafficSchoolSessionController trafficSchoolSessionController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Driver Page");

        // Initialize DriverController
        driverController = new DriverController();
        trafficSchoolSessionController = new TrafficSchoolSessionController();

        // Create a GridPane for layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Create UI elements
        Label searchLabel = new Label("Enter License Number:");
        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        TableView<Citation> citationTableView = new TableView<>();

        // Set up columns for the TableView
        TableColumn<Citation, Integer> citationNumberCol = new TableColumn<>("Citation Number");
        citationNumberCol.setCellValueFactory(new PropertyValueFactory<>("citationNumber"));

        TableColumn<Citation, Integer> officerBadgeNumberCol = new TableColumn<>("Badge Number");
        officerBadgeNumberCol.setCellValueFactory(new PropertyValueFactory<>("policeOfficerBadgeNumber"));

        TableColumn<Citation, Integer> violationCodeCol = new TableColumn<>("Violation Code");
        violationCodeCol.setCellValueFactory(new PropertyValueFactory<>("violationCode"));

        TableColumn<Citation, String> driverLicenseNumberCol = new TableColumn<>("License Number");
        driverLicenseNumberCol.setCellValueFactory(new PropertyValueFactory<>("driverLicenseNumber"));

        TableColumn<Citation, String> vehicleIDCol = new TableColumn<>("Vehicle ID");
        vehicleIDCol.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<Citation, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Citation, String> timeCol = new TableColumn<>("Time");
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));

        TableColumn<Citation, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Citation, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        citationTableView.getColumns().addAll(citationNumberCol, officerBadgeNumberCol, violationCodeCol,
                driverLicenseNumberCol, vehicleIDCol, dateCol, timeCol, locationCol, typeCol);

        // Add UI elements to the GridPane
        grid.add(searchLabel, 0, 0);
        grid.add(searchField, 1, 0);
        grid.add(searchButton, 2, 0);
        grid.add(citationTableView, 0, 1, 3, 1);


        // Event handler for the search button
        searchButton.setOnAction(e -> {
            String licenseNumber = searchField.getText();
            ObservableList<Citation> citations = driverController.getCitationsByLicenseNumber(licenseNumber);
            citationTableView.setItems(citations);
        });

        // Create the Pay and Traffic School buttons
        Button payAllButton = new Button("Pay All");
        Button trafficSchoolButton = new Button("Traffic School for All");

        // Add actions for the new buttons
        payAllButton.setOnAction(e -> {
            System.out.println("Paying all citations");
        });

        trafficSchoolButton.setOnAction(e -> {
            openTrafficSchoolRegistrationForm();
        });

        // Create a container for the new buttons
        HBox buttonContainer = new HBox(10, payAllButton, trafficSchoolButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // Create a VBox for layout and add grid and buttonContainer
        VBox vbox = new VBox(10, grid, buttonContainer);
        vbox.setPadding(new Insets(10));

        // Create the scene with VBox as the root
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openTrafficSchoolRegistrationForm() {
        Stage registrationStage = new Stage();
        registrationStage.setTitle("Register for Traffic School");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        ComboBox<TrafficSchoolSession> sessionComboBox = new ComboBox<>();
        sessionComboBox.setPromptText("Select a Traffic School Session");
        List<TrafficSchoolSession> sessions = trafficSchoolSessionController.getAllTrafficSchoolSessions();
        //sessionComboBox.getItems().addAll(sessions);

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select a Date");

        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> {
            TrafficSchoolSession selectedSession = sessionComboBox.getValue();
            LocalDate selectedDate = datePicker.getValue();

            if (selectedSession != null && selectedDate != null) {
                // Convert the selected date to a formatted string (e.g., "yyyy-MM-dd")
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                String selectedDateString = selectedDate.format(formatter);

                // Call the method to register for the selected session and date
                boolean registrationSuccess = registerForTrafficSchool(selectedSession, selectedDateString);

                if (registrationSuccess) {
                    showAlert("Successfully registered for Traffic School on " + selectedDateString, Alert.AlertType.INFORMATION);
                } else {
                    showAlert("Failed to register for Traffic School on " + selectedDateString, Alert.AlertType.ERROR);
                }
            } else {
                showAlert("Please select a session and date for registration.", Alert.AlertType.WARNING);
            }
        });

        layout.getChildren().addAll(
                new Label("Available Sessions:"),
                sessionComboBox,
                new Label("Select a Date:"),
                datePicker,
                registerButton
        );

        Scene registrationScene = new Scene(layout, 350, 250);
        registrationStage.setScene(registrationScene);
        registrationStage.show();
    }

    // Method to handle registration for Traffic School
    private boolean registerForTrafficSchool(TrafficSchoolSession session, String selectedDate) {
        // Implement the logic to register for the selected session and date
        // You should insert the registration record into the database
        // Return true if registration is successful, false otherwise
        // You can use your TrafficSchoolSessionController for this purpose

        // For demonstration purposes, return true as a placeholder
        return true;
    }

    // This method would need to be implemented in your TrafficSchoolSessionController
    public List<String> getAvailableSchedulesForSession(int sessionId) {
        // Logic to retrieve schedules from the database or other source
        // and return them as a list of strings
        return new ArrayList<>();
    }

    private void showAlert(String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.showAndWait();
    }

}