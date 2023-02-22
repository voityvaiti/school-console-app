package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.CourseMenu;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CourseMenuImpl implements CourseMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final CourseService courseService;

    @Autowired
    public CourseMenuImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        courseService.studentAdditionToTheCourse(studentId, courseName);

    }

    @Override
    public void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        courseService.studentRemovingFromTheCourse(studentId, courseName);
    }
}
