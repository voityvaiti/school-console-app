package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.StudentMenu;
import org.foxminded.rymarovych.service.abstractions.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentMenuImpl implements StudentMenu {

    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(StudentMenuImpl.class);

    private final StudentService studentService;

    @Autowired
    public StudentMenuImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handlePrintStudentsRelatedToCourse() {
        logger.debug("Print students related to course query received. Asking for params..");

        System.out.println("Type course name:");
        String courseName = scanner.next();

        logger.debug("Print students related to course query params received");

        System.out.print(
                studentService.printStudentsRelatedToCourse(courseName)
        );
    }

    @Override
    public void handleStudentAddition() {
        logger.debug("Student addition query received. Asking for params..");

        System.out.println("Type Student's group id:");
        int groupId = scanner.nextInt();

        System.out.println("Type Student's first name:");
        String firstName = scanner.next();

        System.out.println("Type Student's last name:");
        String lastName = scanner.next();

        logger.debug("Student addition query params received");

        studentService.studentAddition(groupId, firstName, lastName);
    }

    @Override
    public void handleRemoveStudentById() {
        logger.debug("Student removal query received. Asking for params..");

        System.out.println("Type Student's id:");
        int id = scanner.nextInt();

        logger.debug("Student removal query params received");

        studentService.removeStudentById(id);
    }
}
