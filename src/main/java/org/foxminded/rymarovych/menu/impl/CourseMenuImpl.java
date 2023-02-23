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

    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(CourseMenuImpl.class);

    private final CourseService courseService;

    @Autowired
    public CourseMenuImpl(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void handleStudentAdditionToTheCourse() {
        logger.debug("Student addition to the course query received. Asking for params..");

        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        logger.debug("Student addition to the course query params received");

        courseService.studentAdditionToTheCourse(studentId, courseName);

    }

    @Override
    public void handleStudentRemovingFromTheCourse() {
        logger.debug("Student removal from the course query received. Asking for params..");

        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        logger.debug("Student removal from the course query params received");

        courseService.studentRemovingFromTheCourse(studentId, courseName);
    }
}
