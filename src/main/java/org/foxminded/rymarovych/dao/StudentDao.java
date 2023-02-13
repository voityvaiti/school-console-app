package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.onstartup.tablefiller.StudentsTableFiller;
import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {
    private static StudentDao instance;

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private int currentStudentMaxId = StudentsTableFiller.STUDENTS_AMOUNT;

    private StudentDao() {
    }

    public static StudentDao getInstance() {
        if (instance == null) {
            instance = new StudentDao();
        }
        return instance;
    }

    public Student getSpecificStudent(int id) {
        Student student = new Student();

        try {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM students WHERE id=?");
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
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO students (id, group_id, first_name, last_name) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, ++currentStudentMaxId);
            preparedStatement.setInt(2, student.getGroupId());
            preparedStatement.setString(3, student.getFirstName());
            preparedStatement.setString(4, student.getLastName());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeStudent(int id) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("DELETE FROM students WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
