package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.abstractions.GroupDao;
import org.foxminded.rymarovych.models.Group;
import org.foxminded.rymarovych.service.abstractions.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupDao groupDao;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    protected GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public void handlePrintGroupsWithLessOrEqualsStudentsAmount() {

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
