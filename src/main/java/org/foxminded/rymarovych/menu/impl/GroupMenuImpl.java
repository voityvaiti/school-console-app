package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.GroupMenu;
import org.foxminded.rymarovych.service.abstractions.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GroupMenuImpl implements GroupMenu {

    private final Scanner scanner = new Scanner(System.in);

    private final GroupService groupService;

    @Autowired
    public GroupMenuImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void handlePrintGroupsWithLessOrEqualsStudentsAmount() {

        System.out.println("Type >=student's amount:");
        int requestedStudentsAmount = scanner.nextInt();

        System.out.print(
                groupService.getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount)
        );
    }
}
