package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDao {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    public int getCourseIdByName(String name) {

        final String GET_COURSE_ID_BY_NAME_STATEMENT = "SELECT id FROM courses WHERE name=?";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_COURSE_ID_BY_NAME_STATEMENT)) {

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
