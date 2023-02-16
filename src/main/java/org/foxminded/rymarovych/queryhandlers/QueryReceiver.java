package org.foxminded.rymarovych.queryhandlers;

import java.util.Scanner;

public class QueryReceiver {

    private final StudentQueriesHandler studentQueriesHandler = new StudentQueriesHandler();
    private final GroupQueriesHandler groupQueriesHandler = new GroupQueriesHandler();
    private final StudentCourseQueriesHandler studentCourseQueriesHandler =
            new StudentCourseQueriesHandler();

    private final String MESSAGE_ENDING = "----------\n";

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

            if (query >= 1 && query <= 6) {
                switch (query) {
                    case 1 -> groupQueriesHandler.handlePrintGroupsWithLessOrEqualsStudentsAmount();
                    case 2 -> studentQueriesHandler.handlePrintStudentsRelatedToCourse();
                    case 3 -> studentQueriesHandler.handleStudentAddition();
                    case 4 -> studentQueriesHandler.handleRemoveStudentById();
                    case 5 -> studentCourseQueriesHandler.handleStudentAdditionToTheCourse();
                    case 6 -> studentCourseQueriesHandler.handleStudentRemovingFromTheCourse();
                }
            } else {
                System.out.println("Error! Wrong query");
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
