package com.expensetracker.service;

import com.expensetracker.model.Expense;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseService {
    private Connection connection;

    public ExpenseService() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expensetracker", "root", "password");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addExpense(Expense expense) {
        String query = "INSERT INTO expenses (amount, category, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, expense.getAmount());
            stmt.setString(2, expense.getCategory());
            stmt.setString(3, expense.getDate());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();
        String query = "SELECT * FROM expenses";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }
}
