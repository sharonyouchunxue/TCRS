package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.CitationController;
import com.schoolproject.tcrs.models.Citation;
import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;

public class openViewCitationsPage extends Application {
    private CitationController citationController;

    public openViewCitationsPage() {
        // Initialize the CitationController
        citationController = new CitationController();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Citations");

        GridPane grid = createViewCitationsPage(primaryStage);
        Scene scene = new Scene(grid, 900, 600);

        // Load the CSS file and add it to the scene's stylesheets
        String cssPath = Objects.requireNonNull(getClass().getResource("/style.css")).toExternalForm();
        scene.getStylesheets().add(cssPath);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane createViewCitationsPage(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label titleLabel = new Label("View Citations");
        titleLabel.setStyle("-fx-font-size: 20px;");

        // Create a TableView to display citations
        TableView<CitationTableItem> citationTableView = new TableView<>();
        citationTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<CitationTableItem, Integer> citationNumberColumn = new TableColumn<>("Citation #");
        citationNumberColumn.setCellValueFactory(new PropertyValueFactory<>("citationNumber"));
        citationNumberColumn.setPrefWidth(100);

        TableColumn<CitationTableItem, Integer> officerBadgeNumberColumn = new TableColumn<>("Officer Badge #");
        officerBadgeNumberColumn.setCellValueFactory(new PropertyValueFactory<>("officerBadgeNumber"));
        officerBadgeNumberColumn.setPrefWidth(100);

        TableColumn<CitationTableItem, Integer> violationCodeColumn = new TableColumn<>("Violation Code");
        violationCodeColumn.setCellValueFactory(new PropertyValueFactory<>("violationCode"));
        violationCodeColumn.setPrefWidth(100);

        TableColumn<CitationTableItem, String> driverLicenseNumberColumn = new TableColumn<>("Driver License #");
        driverLicenseNumberColumn.setCellValueFactory(new PropertyValueFactory<>("driverLicenseNumber"));
        driverLicenseNumberColumn.setPrefWidth(100);

        TableColumn<CitationTableItem, String> vehicleIDColumn = new TableColumn<>("Vehicle ID");
        vehicleIDColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<CitationTableItem, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setPrefWidth(70);

        TableColumn<CitationTableItem, String> timeColumn = new TableColumn<>("Time");
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        timeColumn.setPrefWidth(70);

        TableColumn<CitationTableItem, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setPrefWidth(70);

        TableColumn<CitationTableItem, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        typeColumn.setPrefWidth(60);

        citationTableView.getColumns().addAll(
                citationNumberColumn, officerBadgeNumberColumn, violationCodeColumn,
                driverLicenseNumberColumn, vehicleIDColumn, dateColumn, timeColumn,
                locationColumn, typeColumn
        );


        // Populate the TableView with citation data from the database
        List<Citation> citations = citationController.getAllCitations();
        ObservableList<CitationTableItem> citationTableItems = FXCollections.observableArrayList();

        for (Citation citation : citations) {
            citationTableItems.add(new CitationTableItem(citation));
        }

        citationTableView.setItems(citationTableItems);

        // Create a button to close the "View Citations" page
        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            primaryStage.close();
        });

        // Add elements to the GridPane
        grid.add(titleLabel, 0, 0, 2, 1);
        grid.add(citationTableView, 0, 1);
        grid.add(closeButton, 0, 2);

        return grid;
    }

    // Helper class to display citation data in the TableView
    public static class CitationTableItem {
        private final SimpleIntegerProperty citationNumber;
        private final SimpleIntegerProperty officerBadgeNumber;
        private final SimpleIntegerProperty violationCode;
        private final SimpleStringProperty driverLicenseNumber;
        private final SimpleStringProperty vehicleID;
        private final SimpleStringProperty date;
        private final SimpleStringProperty time;
        private final SimpleStringProperty location;
        private final SimpleStringProperty type;

        public CitationTableItem(Citation citation) {
            this.citationNumber = new SimpleIntegerProperty(citation.getCitationNumber());
            this.officerBadgeNumber = new SimpleIntegerProperty(citation.getPoliceOfficerBadgeNumber());
            this.violationCode = new SimpleIntegerProperty(citation.getViolationCode());
            this.driverLicenseNumber = new SimpleStringProperty(citation.getDriverLicenseNumber());
            this.vehicleID = new SimpleStringProperty(citation.getVehicleID());
            this.date = new SimpleStringProperty(citation.getDate().toString());
            this.time = new SimpleStringProperty(citation.getTime().toString());
            this.location = new SimpleStringProperty(citation.getLocation());
            this.type = new SimpleStringProperty(citation.getType());
        }

        public int getCitationNumber() {
            return citationNumber.get();
        }

        public int getOfficerBadgeNumber() {
            return officerBadgeNumber.get();
        }

        public int getViolationCode() {
            return violationCode.get();
        }

        public String getDriverLicenseNumber() {
            return driverLicenseNumber.get();
        }

        public String getVehicleID() {
            return vehicleID.get();
        }

        public String getDate() {
            return date.get();
        }

        public String getTime() {
            return time.get();
        }

        public String getLocation() {
            return location.get();
        }

        public String getType() {
            return type.get();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
