package org.foxminded.rymarovych.service.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnector {

    private static DatabaseConnector instance;
    private Connection connection;

    private DatabaseConnector() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Properties dbProperties = new Properties();
            dbProperties.load(DatabaseConnector.class.getClassLoader().getResourceAsStream(
                    "properties/db.properties"));

            connection = DriverManager.getConnection(dbProperties.getProperty("URL"),
                    dbProperties.getProperty("USER"), dbProperties.getProperty("PASSWORD"));
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
