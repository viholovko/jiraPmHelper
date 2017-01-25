package com.boostsolutions.jiraservice;

import com.boostsolutions.jiraservice.manager.Manager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author viholovko on 24.01.17.
 */
public class ClientApp extends Application {


        public static void main(String[] args) { launch(args); }
        @Override public void start(Stage stage) throws IOException {
        Scene scene = new Scene(new StackPane());

        Manager loginManager = new Manager(scene);
        loginManager.showLoginScreen();

        stage.setScene(scene);
        stage.show();
    }

    }
