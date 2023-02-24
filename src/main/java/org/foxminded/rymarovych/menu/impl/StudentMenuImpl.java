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

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentMenuImpl.class);

    private final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService;

    @Autowired
    public StudentMenuImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handlePrintStudentsRelatedToCourse() {
        LOGGER.debug("Print students related to course query received. Asking for params..");

        System.out.println("Type course name:");
        String courseName = scanner.next();

        LOGGER.debug("Print students related to course query params received (courseName: {})", courseName);

        System.out.print(
                studentService.printStudentsRelatedToCourse(courseName)
        );
    }

    @Override
    public void handleStudentAddition() {
        LOGGER.debug("Student addition query received. Asking for params..");

        System.out.println("Type Student's group id:");
        int groupId = scanner.nextInt();

        System.out.println("Type Student's first name:");
        String firstName = scanner.next();

        System.out.println("Type Student's last name:");
        String lastName = scanner.next();

        LOGGER.debug("Student addition query params received (groupId: {}, firstName: {}, lastName: {})",
                groupId, firstName, lastName);

        studentService.studentAddition(groupId, firstName, lastName);
    }

    @Override
    public void handleRemoveStudentById() {
        LOGGER.debug("Student removal query received. Asking for params..");

        System.out.println("Type Student's id:");
        int id = scanner.nextInt();

        LOGGER.debug("Student removal query params received (ID: {})", id);

        studentService.removeStudentById(id);
    }
}
