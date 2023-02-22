package org.foxminded.rymarovych.onstartup.tablefiller;

import org.foxminded.rymarovych.service.SingleQuotesWrapper;
import org.springframework.stereotype.Component;

@Component
public class CoursesTableFiller extends TableFiller {

    @Override
    protected String generateStatement() {
        StringBuilder statementBuilder = new StringBuilder();

        final String STATEMENT_BEGINNING = "DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM courses LIMIT 1) THEN " +
                "INSERT INTO courses (id, name) VALUES ";

        String[] coursesNames = {"Biology", "Math", "Art", "History", "Physics",
                "Chemistry", "English", "P.E.", "Algebra", "Geometry"};

        statementBuilder.append(STATEMENT_BEGINNING);

        for (int i = 1; i <= COURSES_AMOUNT; i++) {
            String course = SingleQuotesWrapper.wrapInSingleQuotes(
                    coursesNames[i - 1]
            );

            statementBuilder.append(STATEMENT_ELEM_START).
                    append(i).append(STATEMENT_GAP).
                    append(course).
                    append(STATEMENT_ELEM_END);
        }
        statementBuilder.deleteCharAt(statementBuilder.length() - 1);
        statementBuilder.append(STATEMENT_ENDING);

        return statementBuilder.toString();
    }
}
