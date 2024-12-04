package com.expensetracker.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.expensetracker.model.Expense;
import com.expensetracker.service.ExpenseService;

public class ExpenseController {

    @FXML
    private TextField amountField;
    @FXML
    private TextField categoryField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Expense> expenseTable;

    private ExpenseService expenseService = new ExpenseService();

    @FXML
    private void handleAddExpense() {
        double amount = Double.parseDouble(amountField.getText());
        String category = categoryField.getText();
        String date = datePicker.getValue().toString();

        Expense newExpense = new Expense(amount, category, date);
        expenseService.addExpense(newExpense);

        // Refresh the table
        expenseTable.getItems().setAll(expenseService.getAllExpenses());
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
