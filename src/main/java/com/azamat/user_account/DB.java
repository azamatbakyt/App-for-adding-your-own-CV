package com.azamat.user_account;

import java.sql.*;

public class DB {
    private String HOST = "localhost";
    private String PORT = "";
    private String DB_NAME = "azamat";
    private String LOGIN = "root";
    private String PASS = "root";

    private Connection db_conn = null;

    private Connection getDbconnection() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
        Class.forName("com.mysql.cj.jdbc.Driver");

        db_conn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return db_conn;

    }

    public void isConnected() throws ClassNotFoundException, SQLException{
        db_conn = getDbconnection();
        System.out.println(db_conn.isValid(1000));
    }

    public Boolean isExistsUser(String login) throws SQLException, ClassNotFoundException{
        String sql = "SELECT `id` FROM `users` WHERE `login` = ? ";
        PreparedStatement preparedStatement = getDbconnection().prepareStatement(sql);
        preparedStatement.setString(1, login);

        ResultSet res = preparedStatement.executeQuery();
        return res.next();
    }
    public void regUser( String login,String email, String pass) {
        String sql = "INSERT INTO `users` (`login` , `email`, `password`) VALUES (?, ?, ?)";
        try {
            PreparedStatement prSt = getDbconnection().prepareStatement(sql);
            prSt.setString(1, login);
            prSt.setString(2, email);
            prSt.setString(3, pass);
            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


    public boolean authUser(String login, String pass) {
        String sql = "SELECT `id` FROM `users` WHERE `login` = ?  AND `password` = ?";
        try {
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, pass);

            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return false;
    }

    public ResultSet getCvs(){
        String sql = "SELECT `position` , `Stack` FROM `cv_s`";
        Statement statement = null;
        try {
            statement = getDbconnection().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void addcv(String pos, String yours, String stack) {
        String sql = "INSERT INTO `cv_s` (`position`, `about_yourself`, `Stack`) VALUES(?, ?, ?)";

        try{
            PreparedStatement preparedStatement = getDbconnection().prepareStatement(sql);
            preparedStatement.setString(1, pos);
            preparedStatement.setString(2, yours);
            preparedStatement.setString(3, stack);

            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
