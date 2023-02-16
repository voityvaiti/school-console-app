package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.StudentCourseDao;

import java.util.Scanner;

public class StudentCourseQueriesHandler {
    private StudentCourseDao studentCourseDao = new StudentCourseDao();

    Scanner scanner = new Scanner(System.in);

    protected StudentCourseQueriesHandler() {
    }

    protected StudentCourseQueriesHandler(StudentCourseDao studentCourseDao) {
        this.studentCourseDao = studentCourseDao;
    }

    protected void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course id:");
        int courseId = scanner.nextInt();

        studentAdditionToTheCourse(studentId, courseId);
    }

    protected void studentAdditionToTheCourse(int studentId, int courseId) {
        studentCourseDao.addStudentToTheCourse(studentId, courseId);
    }

    protected void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course id:");
        int courseId = scanner.nextInt();

        studentRemovingFromTheCourse(studentId, courseId);
    }

    protected void studentRemovingFromTheCourse(int studentId, int courseId) {
        studentCourseDao.deleteStudentFromCourse(studentId, courseId);
    }

}
