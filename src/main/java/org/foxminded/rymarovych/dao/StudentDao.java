package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.onstartup.tablefiller.StudentsTableFiller;
import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private int currentStudentMaxId = StudentsTableFiller.STUDENTS_AMOUNT;

    public List<Student> getStudentsByCourseName(String courseName) {
        final String GET_STUDENTS_BY_COURSE_NAME = """
                SELECT students.id AS id, students.group_id AS group_id,
                students.first_name AS first_name,
                students.last_name AS last_name
                FROM students JOIN students_courses
                ON students.id = students_courses.student_id
                JOIN courses
                ON students_courses.course_id = courses.id
                WHERE courses.name = ?;
                """;

        List<Student> studentList = new ArrayList<>();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_STUDENTS_BY_COURSE_NAME)) {

            preparedStatement.setString(1, courseName);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getInt("group_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")
                );
                studentList.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
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
