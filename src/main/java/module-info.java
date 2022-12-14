module com.azamat.user_account {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.azamat.user_account to javafx.fxml;
    exports com.azamat.user_account;
    exports com.azamat.user_account.controllers;
    opens com.azamat.user_account.controllers to javafx.fxml;
    exports com.azamat.user_account.models;
    opens com.azamat.user_account.models to javafx.fxml;
}