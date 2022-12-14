package com.azamat.user_account.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import com.azamat.user_account.DB;
import com.azamat.user_account.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ArtControllerPanel {

    @FXML
    private ResourceBundle resources;

    @FXML
    private HBox panelHBox;

    @FXML
    private URL location;

    @FXML
    private Button exitbtn;

    @FXML
    void initialize() throws SQLException, IOException {
        DB db = new DB();
        ResultSet resultSet = db.getCvs();

        while (resultSet.next()) {
            Node node = FXMLLoader.load(Objects.requireNonNull(HelloApplication.class.getResource("article.fxml")));

            Label position = (Label) node.lookup("#position");
            position.setText(resultSet.getString("position"));

            Label stack = (Label) node.lookup("#stack");
            stack.setText(resultSet.getString("Stack"));

            node.setOnMouseEntered(mouseEvent -> {
                node.setStyle("-fx-background-color: #e9f3fb");
            });

            node.setOnMouseExited(mouseEvent -> {
                node.setStyle("-fx-background-color: #f4f2ec");
            });

            panelHBox.getChildren().add(node);
            panelHBox.setSpacing(10);

        }
        exitbtn.setOnAction(actionEvent -> {
            try {
                exitUser(actionEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private void exitUser(ActionEvent actionEvent) throws IOException {
        File file = new File("user.settings");
        file.delete();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        HelloApplication.setScene("main.fxml", stage);
    }


}




