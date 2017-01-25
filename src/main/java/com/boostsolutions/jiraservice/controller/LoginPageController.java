package com.boostsolutions.jiraservice.controller;

import com.boostsolutions.jiraservice.helpers.ChooseStage;
import com.boostsolutions.jiraservice.manager.Manager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Контроллер авторизації адміністратора
 */
public class LoginPageController {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button loginButton;

    String sessionIDKey;

    /**
     * При успішній авторизації перенаправляє користувача до вікна налаштувань,
     * інакше виводить повідомлення про не коректно введені дані
     *
     * @param loginManager
     */
    public void initManager(final Manager loginManager) {
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (authorize()) {
                    loginManager.authenticated(sessionIDKey);
                } else {
                    String[] buttonsNames = new String[]{"Гаразд"};
                    String[] message = new String[]{"Інформація", "Логін чи пароль введено неправельно. У доступі відмовленно."};
                    new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
                }
            }
        });
    }

    /**
     * Авторизує користувача
     *
     * @return boolean
     */
    private boolean authorize() {
//        AdminUserDao adminUserDao = new AdminUserDaoImpl();
//        sessionIDKey = generateSessionID();
//        return (adminUserDao.findByLoginAndPassword(user.getText(), password.getText()) != null);
        return true;
    }

    private static int sessionID = 0;

    /**
     * Генерує ID сессії
     * @return String
     */
    private String generateSessionID() {
        sessionID++;
        return "xyzzy - session " + sessionID;
    }
}
