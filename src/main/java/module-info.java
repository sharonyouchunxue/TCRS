module com.example.tcrs {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    // Opens the controllers package to javafx.fxml and javafx.graphics which are required for FXML loaders and JavaFX property access
    opens com.schoolproject.tcrs.controllers to javafx.fxml, javafx.graphics;
    // Opens the models package to javafx.base which is required for reflection in PropertyValueFactory
    opens com.schoolproject.tcrs.models to javafx.base;
    // Opens the app package to javafx.fxml, javafx.base, and javafx.graphics
    opens com.schoolproject.tcrs.app to javafx.fxml, javafx.base, javafx.graphics;

    // Export the necessary packages for use in other modules
    exports com.schoolproject.tcrs.app;
    exports com.schoolproject.tcrs.controllers;
    exports com.schoolproject.tcrs.models;
}
