package com.expensetracker;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class MainController {
    @FXML
    private TextField expenseNameField, expenseAmountField;
    @FXML
    private ComboBox<String> categoryComboBox;
    @FXML
    private ListView<String> expenseListView;
    @FXML
    private Label totalAmountLabel;

    private ObservableList<String> expenses = FXCollections.observableArrayList();
    private List<Double> amounts = new ArrayList<>();

    @FXML
    public void initialize() {
        // Populate categoryComboBox with sample categories
        categoryComboBox.getItems().addAll("Food", "Transportation", "Entertainment", "Shopping");

        // Set up the expenseListView
        expenseListView.setItems(expenses);

        // Update total amount whenever the list changes
        expenses.addListener((ListChangeListener<? super String>) c -> {
            double total = 0.0;
            for (Double amount : amounts) {
                total += amount;
            }
            totalAmountLabel.setText(String.format("%.2f", total));
        });
    }

    @FXML
    public void addExpense() {
        String name = expenseNameField.getText();
        double amount = Double.parseDouble(expenseAmountField.getText());
        String category = categoryComboBox.getValue();

        expenses.add(name + " - $" + amount + " (" + category + ")");
        amounts.add(amount);

        // Clear the input fields
        expenseNameField.clear();
        expenseAmountField.clear();
        categoryComboBox.getSelectionModel().clearSelection();
    }
}
