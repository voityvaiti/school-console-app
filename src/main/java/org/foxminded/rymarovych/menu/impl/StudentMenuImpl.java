package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.StudentMenu;
import org.foxminded.rymarovych.service.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class StudentMenuImpl implements StudentMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final StudentService studentService;

    @Autowired
    public StudentMenuImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void handlePrintStudentsRelatedToCourse() {
        System.out.println("Type course name:");
        String courseName = scanner.next();

        System.out.print(
                studentService.printStudentsRelatedToCourse(courseName)
        );
    }

    @Override
    public void handleStudentAddition() {
        System.out.println("Type Student's group id:");
        int groupId = scanner.nextInt();

        System.out.println("Type Student's first name:");
        String firstName = scanner.next();

        System.out.println("Type Student's last name:");
        String lastName = scanner.next();

        studentService.studentAddition(groupId, firstName, lastName);
    }

    @Override
    public void handleRemoveStudentById() {
        System.out.println("Type Student's id:");
        int id = scanner.nextInt();

        studentService.removeStudentById(id);
    }
}
