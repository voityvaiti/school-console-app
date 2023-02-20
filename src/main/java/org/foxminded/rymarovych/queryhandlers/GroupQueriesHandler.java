package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.JdbcGroupDao;
import org.foxminded.rymarovych.models.Group;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GroupQueriesHandler {

    private JdbcGroupDao groupDao;

    private final Scanner scanner = new Scanner(System.in);

    protected GroupQueriesHandler() {
    }

    protected GroupQueriesHandler(JdbcGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    protected void handlePrintGroupsWithLessOrEqualsStudentsAmount() {

        System.out.println("Type >=student's amount:");
        int requestedStudentsAmount = scanner.nextInt();

        System.out.print(
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount)
        );
    }

    protected String getMessageOfGroupsWithLessOrEqualsStudentsAmount(int requestedStudentsAmount) {

        StringBuilder messageBuilder = new StringBuilder();

        Map<Integer, Integer> groupIdToStudentsAmount = groupDao.getGroupIdToStudentsAmount();

        List<Map.Entry<Integer, Integer>> filteredEntryList = groupIdToStudentsAmount.entrySet().stream().
                filter(entity -> entity.getValue() <= requestedStudentsAmount).toList();

        if (filteredEntryList.isEmpty()) {
            messageBuilder.append("No such groups\n");
        } else {

            for (Map.Entry<Integer, Integer> entry : filteredEntryList) {
                messageBuilder.append(
                        groupDao.findGroupById(entry.getKey())
                                .stream().findAny().orElse(new Group())
                ).append("\n");
            }
        }

        return messageBuilder.toString();
    }
}
