package com.azamat.user_account.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.azamat.user_account.DB;
import com.azamat.user_account.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddArticleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField position;

    @FXML
    private TextArea stack_area;

    @FXML
    private TextArea yourself;

    @FXML
    void addCv(ActionEvent event) {
        String pos = position.getCharacters().toString();
        String yours = yourself.getText();
        String stack = stack_area.getText();

        position.setStyle("-fx-border-color: #fafafa");
        yourself.setStyle("-fx-border-color: #fafafa");
        stack_area.setStyle("-fx-border-color: #fafafa");

        if(pos.length() < 5)
            position.setStyle("-fx-border-color: #e06249");
        else if (stack.length() < 5) {
            stack_area.setStyle("-fx-border-color: #e06249");

        } else if (!yours.contains("@") && !yours.contains(".") || yours.length() < 5) {
            yourself.setStyle("-fx-border-color: #e06249");
            yourself.setText("Enter your email \nTell us about yourself");
        } else {

            DB db = new DB();
            db.addcv(pos, yours, stack);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                HelloApplication.setScene("art-panel.fxml", stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {

    }

}
