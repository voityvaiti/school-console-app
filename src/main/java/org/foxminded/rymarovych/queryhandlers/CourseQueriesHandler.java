package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.JdbcCourseDao;

import java.util.Scanner;

public class CourseQueriesHandler {

    private JdbcCourseDao jdbcCourseDao;

    private final Scanner scanner = new Scanner(System.in);

    protected CourseQueriesHandler() {}
    protected CourseQueriesHandler(JdbcCourseDao jdbcCourseDao) {
        this.jdbcCourseDao = jdbcCourseDao;
    }

    protected void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(jdbcCourseDao.findCourseByName(courseName).isPresent()) {
            studentAdditionToTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    protected void studentAdditionToTheCourse(int studentId, String courseName) {
        jdbcCourseDao.addStudentToTheCourse(studentId, courseName);
    }

    protected void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(jdbcCourseDao.findCourseByName(courseName).isPresent()) {
            studentRemovingFromTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    protected void studentRemovingFromTheCourse(int studentId, String courseName) {
        jdbcCourseDao.deleteStudentFromCourse(studentId, courseName);
    }
}
