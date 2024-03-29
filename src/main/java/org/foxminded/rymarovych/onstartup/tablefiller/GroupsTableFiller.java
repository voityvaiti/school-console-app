package org.foxminded.rymarovych.onstartup.tablefiller;

import org.foxminded.rymarovych.utility.SingleQuotesWrapper;
import org.springframework.stereotype.Component;

@Component
public class GroupsTableFiller extends TableFiller {

    static final int GROUP_NAME_LENGTH = 5;

    @Override
    protected String generateStatement() {

        StringBuilder statementBuilder = new StringBuilder();

        final String STATEMENT_BEGINNING = """
                DO $$ BEGIN IF NOT EXISTS (SELECT 1 FROM groups LIMIT 1) THEN
                INSERT INTO groups (name) VALUES
                """;

        statementBuilder.append(STATEMENT_BEGINNING);

        for (int i = 1; i <= GROUPS_AMOUNT; i++) {

            String groupName = SingleQuotesWrapper.wrapInSingleQuotes(
                    generateGroupName()
            );

            statementBuilder.append(STATEMENT_ELEM_START).
                    append(groupName).
                    append(STATEMENT_ELEM_END);
        }
        statementBuilder.deleteCharAt(statementBuilder.length() - 1);
        statementBuilder.append(STATEMENT_ENDING);

        return statementBuilder.toString();
    }

    private String generateGroupName() {
        char[] groupNameChars = new char[GROUP_NAME_LENGTH];

        for (int j = 0; j < GROUP_NAME_LENGTH; j++) {
            if (j <= 1) {
                groupNameChars[j] = (char) (random.nextInt(26) + 'A');
            } else if (j == 2) {
                groupNameChars[j] = '-';
            } else {
                groupNameChars[j] = (char) (random.nextInt(9) + '0');
            }
        }

        return String.valueOf(groupNameChars);
    }
}
