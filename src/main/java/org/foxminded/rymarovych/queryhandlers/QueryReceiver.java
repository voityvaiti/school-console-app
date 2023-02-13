package org.foxminded.rymarovych.queryhandlers;

import java.util.Scanner;

public class QueryReceiver {

    private final StudentQueriesHandler studentQueriesHandler = new StudentQueriesHandler();
    private final GroupQueriesHandler groupQueriesHandler = new GroupQueriesHandler();
    private final StudentCourseQueriesHandler studentCourseQueriesHandler =
            new StudentCourseQueriesHandler();

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
                    case 1:
                        groupQueriesHandler.printGroupsWithLessOrEqualsStudentsAmount();
                        break;
                    case 2:
                        studentQueriesHandler.printStudentsRelatedToCourse();
                        break;
                    case 3:
                        studentQueriesHandler.studentAddition();
                        break;
                    case 4:
                        studentQueriesHandler.removeStudentById();
                        break;
                    case 5:
                        studentCourseQueriesHandler.studentAdditionToTheCourse();
                        break;
                    case 6:
                        studentCourseQueriesHandler.studentRemovingFromTheCourse();
                        break;
                }
            } else {
                System.out.println("Error! Wrong query");
            }

            System.out.println(menu);
            System.out.println("To stop program type " + STOP_PROGRAM_COMMAND);

            query = scanner.nextInt();
        }

    }
}
