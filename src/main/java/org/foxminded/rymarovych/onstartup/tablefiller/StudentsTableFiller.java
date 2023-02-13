package org.foxminded.rymarovych.onstartup.tablefiller;

import org.foxminded.rymarovych.service.SingleQuotesWrapper;

import java.util.ArrayList;
import java.util.List;

public class StudentsTableFiller extends TableFiller {

    @Override
    protected String generateStatement() {
        StringBuilder statementBuilder = new StringBuilder();

        final String[] FIRST_NAMES = {"John", "Dave", "Seth", "Gilbert", "Jorge",
                "Dan", "Brian", "Roberto", "Ramon", "Miles",
                "Daisy", "Isabel", "Stella", "Debra", "Vera",
                "Lucy", "Loretta", "Tracey", "Molly", "Nicole"};

        final String[] LAST_NAMES = {"Smith", "Jones", "Williams", "Taylor", "Brown",
                "Thomas", "Wilson", "Johnson", "Roberts", "Robinson",
                "Walker", "White", "Edwards", "Green", "Hall",
                "Wright", "Lewis", "Harris", "Hill", "Clark"};

        List<Integer> studentsInGroups = getStudentsGroups();

        final int AMOUNT_OF_FIRST_NAMES = FIRST_NAMES.length;
        final int AMOUNT_OF_LAST_NAMES = LAST_NAMES.length;

        final String STATEMENT_BEGINNING = "INSERT INTO students " +
                "(id, group_id, first_name, last_name) VALUES ";

        statementBuilder.append(STATEMENT_BEGINNING);

        for (int i = 1; i <= STUDENTS_AMOUNT; i++) {
            String firstName = SingleQuotesWrapper.wrapInSingleQuotes(
                    FIRST_NAMES[random.nextInt(AMOUNT_OF_FIRST_NAMES - 1)]
            );

            String lastName = SingleQuotesWrapper.wrapInSingleQuotes(
                    LAST_NAMES[random.nextInt(AMOUNT_OF_LAST_NAMES - 1)]
            );

            int group = studentsInGroups.get(i);


            statementBuilder.append(STATEMENT_ELEM_START).
                    append(i).append(STATEMENT_GAP).
                    append(group).append(STATEMENT_GAP).
                    append(firstName).append(STATEMENT_GAP).
                    append(lastName).
                    append(STATEMENT_ELEM_END);
        }
        statementBuilder.deleteCharAt(statementBuilder.length() - 1);
        statementBuilder.append(STATEMENT_ENDING);

        return statementBuilder.toString();
    }

    private List<Integer> getStudentsGroups() {
        List<Integer> studentsInGroups = new ArrayList<>(200);

        final int MIN_STUDENTS_IN_GROUP = 10;
        final int MAX_STUDENTS_IN_GROUP = 30;


        int amountOfStudentsInCurrentGroup;
        for (int i = 1; i <= GROUPS_AMOUNT; i++) {
            amountOfStudentsInCurrentGroup = random.nextInt(MAX_STUDENTS_IN_GROUP -
                    MIN_STUDENTS_IN_GROUP + 1) + MIN_STUDENTS_IN_GROUP;

            int currentStudentsInGroups = studentsInGroups.size();

            if (currentStudentsInGroups + MIN_STUDENTS_IN_GROUP <= STUDENTS_AMOUNT) {
                for (int j = currentStudentsInGroups; j < currentStudentsInGroups + amountOfStudentsInCurrentGroup; j++) {

                    studentsInGroups.add(i);
                }
            } else {
                break;
            }
        }

        if (studentsInGroups.size() < STUDENTS_AMOUNT) {
            for (int i = studentsInGroups.size() - 1; i < STUDENTS_AMOUNT; i++) {
                studentsInGroups.add(0);
            }
        }
        return studentsInGroups;
    }
}
