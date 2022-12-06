package com.azamat.user_account.controllers;


import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.azamat.user_account.DB;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class  RegController {

@FXML
private ResourceBundle resources;

@FXML
private URL location;

@FXML
private Button auth_btn;

@FXML
private TextField authlog;

@FXML
private TextField authpass;

@FXML
private Button reg_btn;

@FXML
private TextField regemail;

@FXML
private TextField reglogin;

@FXML
private PasswordField regpass;

@FXML
private CheckBox regrights;

private DB db = new DB();

@FXML
    void initialize() {
    reg_btn.setOnAction(event -> {
        try {
            reg_user();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    });

    auth_btn.setOnAction(actionEvent -> {
        try {
            authUser();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    });

            }

    private void authUser() throws SQLException,ClassNotFoundException {
    String login =  authlog.getCharacters().toString();
    String pass =  authpass.getCharacters().toString();

    authlog.setStyle("-fx-border-color: #fafafa");
    authpass.setStyle("-fx-border-color: #fafafa");

    if(login.length() <= 3)
        authlog.setStyle("-fx-border-color: #e06249");

    else if (pass.length() <= 7)
        authpass.setStyle("-fx-border-color: #e06249");

     else if (!db.authUser(login, m5String(pass)))
        auth_btn.setText("This user doesn't exist!");

     else{
        authlog.setText("");
        authpass.setText("");
        auth_btn.setText("You're almost here :)");
        }

    }

    private void reg_user() throws SQLException,ClassNotFoundException {
        String reg_username = (String) reglogin.getCharacters().toString();
        String reg_email = (String) regemail.getCharacters().toString();
        String reg_pass = (String) regpass.getCharacters().toString();

        reglogin.setStyle("-fx-border-color: #fafafa");
        regemail.setStyle("-fx-border-color: #fafafa");
        regpass.setStyle("-fx-border-color: #fafafa");

        if(reg_username.length() <= 3)
            reglogin.setStyle("-fx-border-color: #e06249");

        else if (reg_email.length() <= 5 || !reg_email.contains("@") || !reg_email.contains("."))
            regemail.setStyle("-fx-border-color: #e06249");

        else if (reg_pass.length() <= 7 )
            regpass.setStyle("-fx-border-color: #e06249");

        else if (!regrights.isSelected())
            reg_btn.setText("Check the box ");

        else if (db.isExistsUser(reg_username))
            reg_btn.setText("Enter another login");

        else{
            db.regUser(reg_username, reg_email, m5String(reg_pass));
            reglogin.setText("");
            regemail.setText("");
            regpass.setText("");

            reg_btn.setText("Everything is ready :) ");
        }

    }

    public static String m5String(String pass)
    {
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(pass.getBytes());
            digest = messageDigest.digest();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        String m5dHex = bigInteger.toString(16);

        while(m5dHex.length() < 32){
            m5dHex = "0" + m5dHex;
        }

        return m5dHex;

    }



}
