package com.boostsolutions.jiraservice.manager;

import com.boostsolutions.jiraservice.controller.LoginPageController;
import com.boostsolutions.jiraservice.controller.MainPageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author viholovko on 24.01.17.
 */
public class Manager {

    private Scene scene;
    private Stage stage;

    public Manager(Scene scene) {
        this.scene = scene;
    }

    /**
     * Callback method invoked to notify that a user has been authenticated.
     * Will show the main application screen.
     */
    public void authenticated(String sessionID) {
        showMainView(sessionID);
    }

    /**
     * Callback method invoked to notify that a user has logged out of the main application.
     * Will show the login application screen.
     */
    public void logout() {
        showLoginScreen();
    }

    public void showLoginScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(LoginPageController.class.getResource("/views/LoginPageLayout.fxml"));
            scene.setRoot(loader.load());
            LoginPageController controller = loader.<LoginPageController>getController();
            controller.initManager(this);
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void showMainView(String sessionID) {
        try {
            FXMLLoader loader = new FXMLLoader(MainPageController.class.getResource("/views/MainPageLayout.fxml"));
            scene.setRoot(loader.load());
            Stage stage = (Stage) scene.getWindow();
            stage.setHeight(500);
            stage.setWidth(800);
            MainPageController controller = loader.<MainPageController>getController();
            controller.initSessionID(this, sessionID);
        } catch (IOException ex) {
            Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    public void showWorkingView() {
//        try {
//            FXMLLoader loader = new FXMLLoader(MainClientViewController.class.getResource("/views/ClientPageLayout.fxml"));
//            scene.setRoot(loader.load());
//            MainClientViewController controller = loader.<MainClientViewController>getController();
//        } catch (IOException ex) {
//            Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

}
