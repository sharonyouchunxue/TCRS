package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.DriverController;
import com.schoolproject.tcrs.controllers.VehicleController;
import com.schoolproject.tcrs.models.Driver;
import com.schoolproject.tcrs.models.User;
import javax.management.Query;

import com.schoolproject.tcrs.models.Vehicle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
    public void start(Stage policeActionStage) {
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(30));
        stackPane.setStyle("-fx-background-color: lightblue;");

        // ITEMS

        Button queryButton = new Button("Query Local Agency");
        queryButton.setStyle("-fx-font-size: 15px; -fx-font-weight: 900;");
        queryButton.setPrefSize(250, 80);

        Button logoutButton = new Button("Logout");
        logoutButton.setPrefSize(150, 60);
        logoutButton.setStyle("-fx-font-size: 15px; -fx-font-weight: 900;");

        Button createCitationButton = new Button("Issue Citation");
        createCitationButton.setPrefSize(250, 80);
        createCitationButton.setStyle("-fx-font-size: 15px; -fx-font-weight: 900;");

        Button viewCitationsButton = new Button("View Citations");
        viewCitationsButton.setPrefSize(250, 80);
        viewCitationsButton.setStyle("-fx-font-size: 15px; -fx-font-weight: 900;");

        Label titleLabel = new Label("Choose Your Action");
        titleLabel.setStyle("-fx-font-size: 50px; -fx-font-weight: 900;");

        // BOXES FOR ITEMS
        VBox buttonsBox1 = new VBox(createCitationButton, viewCitationsButton,queryButton, logoutButton);
        buttonsBox1.setAlignment(Pos.CENTER);
        buttonsBox1.setSpacing(30);
        buttonsBox1.setPadding(new Insets(100, 0, 0, 0));



        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setSpacing(20);


        // COMPILE BOXES INTO STACKPANE
        stackPane.getChildren().addAll(titleBox, buttonsBox1);

        Scene scene = new Scene(stackPane, 600, 600);
        policeActionStage.setTitle("Police Action");
        policeActionStage.setScene(scene);
        policeActionStage.show();


        // Set actions for the buttons
        createCitationButton.setOnAction(e -> {
            // Open the citation issuance form
            openCitationForm(policeActionStage);
        });

        viewCitationsButton.setOnAction(e -> {
            // Redirect to the officer's "View Citations" page
            openViewCitationsPage(policeActionStage);
        });

        queryButton.setOnAction(e -> openQueryAgencyForm());

        logoutButton.setOnAction(e -> {
            policeActionStage.close();
        });


    }

    public static void main(String[] args) {
        launch(args);
    }

    private void openCitationForm(Stage primaryStage) {
        CitationFormUI issueCitationForm = new CitationFormUI();
        issueCitationForm.start(primaryStage);
    }

    private void openViewCitationsPage(Stage currentStage) {
        currentStage.close();
        openViewCitationsPage openViewCitationsPage = new openViewCitationsPage();
        openViewCitationsPage.start(currentStage);
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