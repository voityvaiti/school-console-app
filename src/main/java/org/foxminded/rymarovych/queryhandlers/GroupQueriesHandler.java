package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.GroupDao;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GroupQueriesHandler {

    private final GroupDao groupDao = GroupDao.getInstance();

    Scanner scanner = new Scanner(System.in);

    protected void printGroupsWithLessOrEqualsStudentsAmount() {

        System.out.println("Type >=student's amount:");
        int requestedStudentsAmount = scanner.nextInt();

        Map<Integer, Integer> groupIdToStudentsAmount = groupDao.getGroupIdToStudentsAmount();

        List<Map.Entry<Integer, Integer>> filteredEntryList = groupIdToStudentsAmount.entrySet().stream().
                filter(entity -> entity.getValue() <= requestedStudentsAmount).toList();

        if (filteredEntryList.isEmpty()) {
            System.out.println("No such groups");

        } else {

            for (Map.Entry<Integer, Integer> entry : filteredEntryList) {
                System.out.println(
                        groupDao.getGroupNameById(entry.getKey())
                );
            }
        }

        System.out.println("---------");
    }
}
