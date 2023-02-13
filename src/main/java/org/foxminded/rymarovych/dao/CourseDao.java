package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDao {

    private static CourseDao instance;

    private final Connection connection = DatabaseConnector.getInstance().getConnection();


    private CourseDao() {
    }

    public static CourseDao getInstance() {
        if (instance == null) {
            instance = new CourseDao();
        }
        return instance;
    }

    public int getCourseIdByName(String name) {
        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT id FROM courses WHERE name=?");
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return -1;
    }
}
