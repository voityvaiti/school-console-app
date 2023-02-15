package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.onstartup.tablefiller.StudentsTableFiller;
import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private int currentStudentMaxId = StudentsTableFiller.STUDENTS_AMOUNT;

    public Student getSpecificStudent(int id) {
        final String GET_STUDENT_BY_ID_STATEMENT =
                "SELECT * FROM students WHERE id=?";

        Student student = new Student();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_STUDENT_BY_ID_STATEMENT)) {

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getInt("id"));
                student.setGroupId(resultSet.getInt("group_id"));
                student.setFirstName(resultSet.getString("first_name"));
                student.setLastName(resultSet.getString("last_name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return student;
    }

    public void addStudent(Student student) {
        final String ADD_STUDENT_STATEMENT =
                "INSERT INTO students (id, group_id, first_name, last_name) VALUES (?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                ADD_STUDENT_STATEMENT)) {

            preparedStatement.setInt(1, ++currentStudentMaxId);
            preparedStatement.setInt(2, student.getGroupId());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteStudent(int id) {
        final String DELETE_STUDENT_BY_ID_STATEMENT =
                "DELETE FROM students WHERE id=?";

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(DELETE_STUDENT_BY_ID_STATEMENT)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
