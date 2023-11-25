package com.schoolproject.tcrs.app;

import com.schoolproject.tcrs.controllers.UserController;
import com.schoolproject.tcrs.models.User;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class MainUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Traffic Citation Reporting System");

        // Load the CSS file and add it to the scene's stylesheets
        String cssPath = Objects.requireNonNull(MainUI.class.getResource("/style.css")).toExternalForm();

        // Create a StackPane and add the main layout to it
        StackPane root = new StackPane();
        root.getChildren().add(createMainStack(primaryStage));

        // Create the scene with the StackPane
        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(cssPath); // Add the CSS file

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private StackPane createMainStack(Stage primaryStage) {
        StackPane stackPane = new StackPane();
        stackPane.setPadding(new Insets(20));

        Button officerButton = new Button("Officer");
        officerButton.setPrefSize(100, 50);

        Button driverButton = new Button("Driver");
        driverButton.setPrefSize(100, 50);

        Label systemLabel = new Label("Traffic Citation Reporting System");
        systemLabel.setStyle("-fx-font-size: 40px; -fx-font-weight: 100;");

        HBox buttonBox = new HBox(40, officerButton, driverButton);
        buttonBox.setAlignment(Pos.CENTER);

        VBox containerBox = new VBox(150, systemLabel, buttonBox);
        containerBox.setAlignment(Pos.CENTER);

        // Adjust the top margin of the systemLabel to move it up
        VBox.setMargin(systemLabel, new Insets(-10, 0, 0, 0)); // Negative top margin

        stackPane.getChildren().add(containerBox);

        officerButton.setOnAction(e -> {
            openOfficerLoginPage(primaryStage);
        });

        driverButton.setOnAction(e -> {
            openDriverPageUI(primaryStage);
        });

        return stackPane;
    }

    private void openOfficerLoginPage(Stage primaryStage) {
        Stage officerLoginStage = new Stage();
        officerLoginStage.setTitle("Officer Login");

        GridPane officerLoginGrid = new GridPane();
        officerLoginGrid.setAlignment(javafx.geometry.Pos.CENTER);
        officerLoginGrid.setHgap(10);
        officerLoginGrid.setVgap(10);
        officerLoginGrid.setPadding(new Insets(25, 25, 25, 25));

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");

        officerLoginGrid.add(new Label("Username:"), 0, 0);
        officerLoginGrid.add(usernameField, 1, 0);
        officerLoginGrid.add(new Label("Password:"), 0, 1);
        officerLoginGrid.add(passwordField, 1, 1);
        officerLoginGrid.add(loginButton, 1, 2);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserController userController = new UserController();
            User authenticatedUser = userController.authenticateUser(username, password);

            if (authenticatedUser != null && "Officer".equals(authenticatedUser.getRole())) {
                openOfficerPage(primaryStage, authenticatedUser);
                officerLoginStage.close();
            } else {
                showAlert("Login Failed", "Invalid username or password.");
            }
        });

        Scene officerLoginScene = new Scene(officerLoginGrid, 400, 200);
        officerLoginStage.setScene(officerLoginScene);
        officerLoginStage.show();
    }

    private void openOfficerPage(Stage primaryStage, User authenticatedUser) {
        Stage officerPageStage = new Stage();
        officerPageStage.setTitle("Officer Page");

        OfficerPageUI officerPageUI = new OfficerPageUI(authenticatedUser);

        // Call the start method of the OfficerPageUI to display the Officer Page
        officerPageUI.start(officerPageStage);

        primaryStage.close();
    }

    private void openDriverPageUI(Stage primaryStage) {
        // Launch the DriverPageUI directly
        DriverPageUI driverPageUI = new DriverPageUI();
        driverPageUI.start(primaryStage);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
