package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.service.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    private final Connection connection = DatabaseConnector.getInstance().getConnection();

    private int currentStudentCourseMaxId = countStudentsCourses();

    public List<Integer> getStudentsWithSpecificCourse(int courseId) {
        final String GET_STUDENTS_ID_BY_COURSE_ID_STATEMENT =
                "SELECT student_id FROM students_courses WHERE course_id=?";

        List<Integer> listOfStudentsId = new ArrayList<>();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(GET_STUDENTS_ID_BY_COURSE_ID_STATEMENT)) {

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
        final String GET_STUDENTS_COURSES_ID_BY_STUDENT_ID_AND_COURSE_ID_STATEMENT =
                "SELECT id FROM students_courses WHERE student_id=? AND course_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                GET_STUDENTS_COURSES_ID_BY_STUDENT_ID_AND_COURSE_ID_STATEMENT)) {

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
        final String ADD_STUDENT_COURSE_STATEMENT =
                "INSERT INTO students_courses (id, student_id, course_id) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                ADD_STUDENT_COURSE_STATEMENT)) {

            preparedStatement.setInt(1, ++currentStudentCourseMaxId);
            preparedStatement.setInt(2, studentId);
            preparedStatement.setInt(3, courseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromCourse(int studentId, int courseId) {
        final String DElETE_STUDENT_COURSE_BY_ID_STATEMENT =
                "DELETE FROM students_courses WHERE id=?";

        int studentCourseId = getStudentCourseId(studentId, courseId);

        try (PreparedStatement preparedStatement = connection.prepareStatement(
                DElETE_STUDENT_COURSE_BY_ID_STATEMENT)) {

            preparedStatement.setInt(1, studentCourseId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int countStudentsCourses() {
        final String GET_AMOUNT_OF_STUDENTS_COURSES_STATEMENT =
                "SELECT COUNT(*) AS amount FROM students_courses";

        try (ResultSet resultSet = connection.createStatement().executeQuery(
                GET_AMOUNT_OF_STUDENTS_COURSES_STATEMENT)) {

            if (resultSet.next()) {
                return resultSet.getInt("amount");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


}
