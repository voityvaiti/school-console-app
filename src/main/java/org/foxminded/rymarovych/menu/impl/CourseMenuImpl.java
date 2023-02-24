package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.CourseMenu;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CourseMenuImpl implements CourseMenu {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseMenuImpl.class);

    private final Scanner scanner = new Scanner(System.in);

    private final CourseService courseService;

    @Autowired
    public CourseMenuImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void handleStudentAdditionToTheCourse() {
        LOGGER.debug("Student addition to the course query received. Asking for params..");

        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        LOGGER.debug("Student addition to the course query params received (studentId: {}, courseName: {})", studentId, courseName);

        courseService.studentAdditionToTheCourse(studentId, courseName);

    }

    @Override
    public void handleStudentRemovingFromTheCourse() {
        LOGGER.debug("Student removal from the course query received. Asking for params..");

        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        LOGGER.debug("Student removal from the course query params received (studentId: {}, courseName: {})", studentId, courseName);

        courseService.studentRemovingFromTheCourse(studentId, courseName);
    }
}
