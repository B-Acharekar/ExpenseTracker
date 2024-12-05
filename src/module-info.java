module ExpenseTracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    // Export the main application package
    exports com.expensetracker;

    // Open the controller package for FXML reflection
    opens com.expensetracker.controller to javafx.fxml;
}
