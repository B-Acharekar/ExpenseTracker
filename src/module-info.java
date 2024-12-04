module ExpenseTracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    exports com.expensetracker; // Allow other modules to access this package
    opens com.expensetracker to javafx.fxml, javafx.graphics; // Allow reflection access for FXML and JavaFX runtime
}
