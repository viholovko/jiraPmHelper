package com.boostsolutions.jiraservice.helpers;

import java.sql.*;

public class JdbcHelper {

    private static final String DB_CONNECTION = "jdbc:sqlite:databases/content.sqlite";

    static Connection dbConnection;
    static Statement statement;
    static PreparedStatement preparedStatement;
    static ResultSet rs;

    public static ResultSet execute(String querySQL) throws SQLException {

        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();

        rs = statement.executeQuery(querySQL);


        return rs;
    }

    public static ResultSet execute(String querySQL, String[] params) throws SQLException {

        dbConnection = getDBConnection();

        preparedStatement = dbConnection.prepareStatement(querySQL);
        for (int i = 1; i < params.length; i++) {
            preparedStatement.setString(i, params[i]);
        }

        rs = preparedStatement.executeQuery();


        return rs;
    }

    public static PreparedStatement getPreparedStatement(String querySQL) throws SQLException{
        dbConnection = getDBConnection();

        preparedStatement = dbConnection.prepareStatement(querySQL);

        return preparedStatement;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION);
            dbConnection.setAutoCommit(true);
            return dbConnection;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dbConnection;
    }

    public static void closeContection() {
        try {
            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
