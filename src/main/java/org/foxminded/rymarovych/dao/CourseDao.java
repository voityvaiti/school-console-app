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

    public void addStudentToTheCourse(int studentId, String courseName) {
        final String ADD_STUDENT_COURSE_STATEMENT =
                "INSERT INTO students_courses (student_id, course_id) VALUES (?," +
                        "(SELECT id FROM courses WHERE name = ?));";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                ADD_STUDENT_COURSE_STATEMENT)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, courseName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromCourse(int studentId, String courseName) {
        final String DElETE_STUDENT_COURSE_BY_ID_STATEMENT =
                "DELETE FROM students_courses WHERE student_id = ? AND course_id = " +
                        "(SELECT id FROM courses WHERE name = ?);";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                DElETE_STUDENT_COURSE_BY_ID_STATEMENT)) {

            preparedStatement.setInt(1, studentId);
            preparedStatement.setString(2, courseName);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
