package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    private static StudentCourseDao instance;

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private int currentStudentCourseMaxId = countStudentsCourses();


    private StudentCourseDao() {
    }

    public static StudentCourseDao getInstance() {
        if (instance == null) {
            instance = new StudentCourseDao();
        }
        return instance;
    }

    public List<Integer> getStudentsWithSpecificCourse(int courseId) {
        List<Integer> listOfStudentsId = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT student_id FROM students_courses WHERE course_id=?");
            preparedStatement.setInt(1, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listOfStudentsId.add(
                        resultSet.getInt("student_id")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfStudentsId;
    }

    public int getStudentCourseId(int studentId, int courseId) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id FROM students_courses WHERE student_id=? AND course_id=?");
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void addStudentToTheCourse(int studentId, int courseId) {
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO students_courses (id, student_id, course_id) VALUES (?, ?, ?)");
            preparedStatement.setInt(1, ++currentStudentCourseMaxId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        int studentCourseId = getStudentCourseId(studentId, courseId);
        System.out.println(studentCourseId);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM students_courses WHERE id=?");
            preparedStatement.setInt(1, studentCourseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countStudentsCourses() {
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(
                    "SELECT COUNT(*) AS amount FROM students_courses"
            );
            if (resultSet.next()) {
                return resultSet.getInt("amount");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


}
