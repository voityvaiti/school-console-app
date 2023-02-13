package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.StudentCourseDao;

import java.util.Scanner;

public class StudentCourseQueriesHandler {
    private final StudentCourseDao studentCourseDao = StudentCourseDao.getInstance();

    Scanner scanner = new Scanner(System.in);

    protected void studentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course id:");
        int courseId = scanner.nextInt();

        studentCourseDao.addStudentToTheCourse(studentId, courseId);
    }

    protected void studentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course id:");
        int courseId = scanner.nextInt();

        studentCourseDao.deleteStudentFromCourse(studentId, courseId);
    }

}
