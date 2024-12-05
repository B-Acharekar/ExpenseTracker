package com.expensetracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class ExpenseTrackerApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Debug resource path
        URL resource = getClass().getResource("/resource/Main.fxml");
        if (resource == null) {
            throw new IllegalStateException("FXML file not found at resource/Main.fxml");
        }

        FXMLLoader loader = new FXMLLoader(resource);
        Scene scene = new Scene(loader.load());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Expense Tracker");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
