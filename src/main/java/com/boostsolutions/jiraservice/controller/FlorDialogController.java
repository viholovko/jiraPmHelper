package com.boostsolutions.jiraservice.controller;

import com.boostsolutions.jiraservice.helpers.ChooseStage;
import com.boostsolutions.jiraservice.models.FlorsModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Контроллер керування поверхами
 */
public class FlorDialogController {

    @FXML
    private TextField florNumberTextField;
    @FXML
    private TextField priceTextField;

    private Stage dialogStage;
    private boolean okClicked = false;

    private FlorsModel florsModel;

    public void setFlorsModel(FlorsModel florsModel) {
        this.florsModel = florsModel;

        florNumberTextField.setText(String.valueOf(florsModel.getFlourNuber()));
        priceTextField.setText(String.valueOf(florsModel.getPrice()));

    }

    /**
     * Закриває діалог
     */
    @FXML
    void handleCancel() {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Заповнює дані про поверх
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            florsModel.setFlourNuber(Integer.parseInt(florNumberTextField.getText()));
            florsModel.setPrice(Float.parseFloat(priceTextField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Перевіряє введені дані
     * @return boolean
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            String[] buttonsNames = new String[]{"Гаразд"};
            String[] message = new String[]{"Інформація", "Некоректне значення у полі. Будь-ласка задайте коректне значаення."};

            new ChooseStage(buttonsNames, message[0], message[1], 350, 150).showAndWait();
            return false;
        }
    }


}
