package org.foxminded.rymarovych.service;

import org.foxminded.rymarovych.service.impl.CourseServiceImpl;
import org.foxminded.rymarovych.service.impl.GroupServiceImpl;
import org.foxminded.rymarovych.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class QueryReceiver {

    private final StudentServiceImpl studentServiceImpl;
    private final GroupServiceImpl groupServiceImpl;
    private final CourseServiceImpl courseServiceImpl;

    @Autowired
    public QueryReceiver(StudentServiceImpl studentServiceImpl, GroupServiceImpl groupServiceImpl, CourseServiceImpl courseServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;
        this.groupServiceImpl = groupServiceImpl;
        this.courseServiceImpl = courseServiceImpl;
    }

    private static final String MESSAGE_ENDING = "----------\n";

    public void receiveAndHandleQueries() {
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
                    case 1 -> groupServiceImpl.handlePrintGroupsWithLessOrEqualsStudentsAmount();
                    case 2 -> studentServiceImpl.handlePrintStudentsRelatedToCourse();
                    case 3 -> studentServiceImpl.handleStudentAddition();
                    case 4 -> studentServiceImpl.handleRemoveStudentById();
                    case 5 -> courseServiceImpl.handleStudentAdditionToTheCourse();
                    case 6 -> courseServiceImpl.handleStudentRemovingFromTheCourse();
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
