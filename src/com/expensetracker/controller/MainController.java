package com.expensetracker.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private TextField expenseNameField;

    @FXML
    private TextField expenseAmountField;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private ListView<String> expenseListView;

    @FXML
    private Label totalAmountLabel;

    @FXML
    private PieChart expensePieChart;

    private double totalExpenses = 0.0;
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        categoryComboBox.setItems(FXCollections.observableArrayList("Food", "Transport", "Entertainment", "Other"));
        expensePieChart.setData(pieChartData);
    }

    @FXML
    public void addExpense() {
        String name = expenseNameField.getText();
        String category = categoryComboBox.getValue();
        double amount;

        try {
            amount = Double.parseDouble(expenseAmountField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Invalid amount entered!");
            return;
        }

        if (name.isEmpty() || category == null) {
            System.err.println("Expense name or category is empty!");
            return;
        }

        // Update expense list
        expenseListView.getItems().add(String.format("%s - %.2f (%s)", name, amount, category));

        // Update total expenses
        totalExpenses += amount;
        totalAmountLabel.setText(String.format("%.2f", totalExpenses));

        // Update PieChart
        PieChart.Data slice = pieChartData.stream()
                .filter(data -> data.getName().equals(category))
                .findFirst()
                .orElse(null);

        if (slice == null) {
            pieChartData.add(new PieChart.Data(category, amount));
        } else {
            slice.setPieValue(slice.getPieValue() + amount);
        }

        // Clear input fields
        expenseNameField.clear();
        expenseAmountField.clear();
        categoryComboBox.setValue(null);
    }
}
