package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.CourseMenu;
import org.foxminded.rymarovych.menu.abstractions.GroupMenu;
import org.foxminded.rymarovych.menu.abstractions.MainMenu;
import org.foxminded.rymarovych.menu.abstractions.StudentMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenuImpl implements MainMenu {

    private final StudentMenu studentMenu;
    private final GroupMenu groupMenu;
    private final CourseMenu courseMenu;

    @Autowired
    public MainMenuImpl(StudentMenu studentMenu, GroupMenu groupMenu, CourseMenu courseMenu) {
        this.studentMenu = studentMenu;
        this.groupMenu = groupMenu;
        this.courseMenu = courseMenu;
    }

    private static final String MESSAGE_ENDING = "----------\n";

    public void runMainMenu() {
        Scanner scanner = new Scanner(System.in);

        final int STOP_PROGRAM_COMMAND = 0;

        String menu = """
                1. Find all groups with less or equal students’ number
                2. Find all students related to the course
                3. Add a new student
                4. Delete a student
                5. Add a student to the course
                6. Remove the student from course
                                """;

        System.out.println("---SCHOOL CONSOLE APP---");
        System.out.println(menu);
        System.out.println("To stop program type " + STOP_PROGRAM_COMMAND);

        int query = scanner.nextInt();

        while (query != STOP_PROGRAM_COMMAND) {

            switch (query) {
                case 1 -> groupMenu.handlePrintGroupsWithLessOrEqualsStudentsAmount();
                case 2 -> studentMenu.handlePrintStudentsRelatedToCourse();
                case 3 -> studentMenu.handleStudentAddition();
                case 4 -> studentMenu.handleRemoveStudentById();
                case 5 -> courseMenu.handleStudentAdditionToTheCourse();
                case 6 -> courseMenu.handleStudentRemovingFromTheCourse();
                default -> System.out.println("Error! Wrong query");
            }

            System.out.println(
                    MESSAGE_ENDING + "\n" +
                            menu + "\n" +
                            "To stop program type " + STOP_PROGRAM_COMMAND + "\n"
            );

            query = scanner.nextInt();
        }

    }
}
