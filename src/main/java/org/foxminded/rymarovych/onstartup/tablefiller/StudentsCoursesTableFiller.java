package org.foxminded.rymarovych.onstartup.tablefiller;

public class StudentsCoursesTableFiller extends TableFiller {
    @Override
    protected String generateStatement() {
        StringBuilder statementBuilder = new StringBuilder();

        final String STATEMENT_BEGINNING = "INSERT INTO students_courses " +
                "(id, student_id, course_id) VALUES ";

        final int MAX_COURSES_OF_SINGLE_STUDENT = 3;
        final int MIN_COURSES_OF_SINGLE_STUDENT = 1;

        int currentAmountOfStudentsCourses = 0;

        statementBuilder.append(STATEMENT_BEGINNING);

        for(int i = 1; i <= STUDENTS_AMOUNT; i++) {

            int randomStudentCoursesAmount = random.nextInt(
                    MAX_COURSES_OF_SINGLE_STUDENT) + MIN_COURSES_OF_SINGLE_STUDENT;

            for(int j = 1; j <= randomStudentCoursesAmount; j++) {
                currentAmountOfStudentsCourses++;

                int randomCourse = random.nextInt(COURSES_AMOUNT) + 1;

                statementBuilder.append(STATEMENT_ELEM_START).
                        append(currentAmountOfStudentsCourses).append(STATEMENT_GAP).
                        append(i).append(STATEMENT_GAP).
                        append(randomCourse).
                        append(STATEMENT_ELEM_END);
            }
        }
        statementBuilder.deleteCharAt(statementBuilder.length() - 1);
        statementBuilder.append(STATEMENT_ENDING);

        return statementBuilder.toString();
    }
}
