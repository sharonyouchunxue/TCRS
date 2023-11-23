package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.models.Citation;
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
public class DriverPageUI extends Application {
    private DriverController driverController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Driver Page");

        // Initialize DriverController
        driverController = new DriverController();

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
            System.out.println("Traffic school for all citations");
        });

        // Create a container for the new buttons
        HBox buttonContainer = new HBox(10, payAllButton, trafficSchoolButton);
        buttonContainer.setAlignment(Pos.CENTER);

        // Create a VBox for layout and add grid and buttonContainer
        VBox vbox = new VBox(10, grid, buttonContainer);
        vbox.setPadding(new Insets(10));

        // Create the scene with VBox as the root
        Scene scene = new Scene(vbox, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}