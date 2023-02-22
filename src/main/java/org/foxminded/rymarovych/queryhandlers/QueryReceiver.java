package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.queryhandlers.impl.CourseQueriesHandlerImpl;
import org.foxminded.rymarovych.queryhandlers.impl.GroupQueriesHandlerImpl;
import org.foxminded.rymarovych.queryhandlers.impl.StudentQueriesHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class QueryReceiver {

    private final StudentQueriesHandlerImpl studentQueriesHandlerImpl;
    private final GroupQueriesHandlerImpl groupQueriesHandlerImpl;
    private final CourseQueriesHandlerImpl courseQueriesHandlerImpl;

    @Autowired
    public QueryReceiver(StudentQueriesHandlerImpl studentQueriesHandlerImpl, GroupQueriesHandlerImpl groupQueriesHandlerImpl, CourseQueriesHandlerImpl courseQueriesHandlerImpl) {
        this.studentQueriesHandlerImpl = studentQueriesHandlerImpl;
        this.groupQueriesHandlerImpl = groupQueriesHandlerImpl;
        this.courseQueriesHandlerImpl = courseQueriesHandlerImpl;
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
                    case 1 -> groupQueriesHandlerImpl.handlePrintGroupsWithLessOrEqualsStudentsAmount();
                    case 2 -> studentQueriesHandlerImpl.handlePrintStudentsRelatedToCourse();
                    case 3 -> studentQueriesHandlerImpl.handleStudentAddition();
                    case 4 -> studentQueriesHandlerImpl.handleRemoveStudentById();
                    case 5 -> courseQueriesHandlerImpl.handleStudentAdditionToTheCourse();
                    case 6 -> courseQueriesHandlerImpl.handleStudentRemovingFromTheCourse();
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
