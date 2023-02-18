package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.CourseDao;

import java.util.Scanner;

public class CourseQueriesHandler {

    private CourseDao courseDao = new CourseDao();

    private final Scanner scanner = new Scanner(System.in);

    protected CourseQueriesHandler() {}

    protected CourseQueriesHandler(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    protected void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(courseDao.getCourseIdByName(courseName) == -1) {
            System.out.println("No such course");

        } else {
            studentAdditionToTheCourse(studentId, courseName);
        }
    }

    protected void studentAdditionToTheCourse(int studentId, String courseName) {
        courseDao.addStudentToTheCourse(studentId, courseName);
    }

    protected void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(courseDao.getCourseIdByName(courseName) == -1) {
            System.out.println("No such course");

        } else {
            studentRemovingFromTheCourse(studentId, courseName);
        }
    }

    protected void studentRemovingFromTheCourse(int studentId, String courseName) {
        courseDao.deleteStudentFromCourse(studentId, courseName);
    }
}
