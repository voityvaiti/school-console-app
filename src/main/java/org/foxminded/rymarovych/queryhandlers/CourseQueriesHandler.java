package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.impl.CourseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CourseQueriesHandler {

    @Autowired
    private CourseDaoImpl courseDaoImpl;

    private final Scanner scanner = new Scanner(System.in);

    protected CourseQueriesHandler() {}
    protected CourseQueriesHandler(CourseDaoImpl courseDaoImpl) {
        this.courseDaoImpl = courseDaoImpl;
    }

    protected void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(courseDaoImpl.findCourseByName(courseName).isPresent()) {
            studentAdditionToTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    protected void studentAdditionToTheCourse(int studentId, String courseName) {
        courseDaoImpl.addStudentToTheCourse(studentId, courseName);
    }

    protected void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        if(courseDaoImpl.findCourseByName(courseName).isPresent()) {
            studentRemovingFromTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    protected void studentRemovingFromTheCourse(int studentId, String courseName) {
        courseDaoImpl.deleteStudentFromCourse(studentId, courseName);
    }
}
