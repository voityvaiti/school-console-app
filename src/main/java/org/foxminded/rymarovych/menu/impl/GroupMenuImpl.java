package org.foxminded.rymarovych.menu.impl;

import org.foxminded.rymarovych.menu.abstractions.GroupMenu;
import org.foxminded.rymarovych.service.abstractions.GroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class GroupMenuImpl implements GroupMenu {

    private final Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(GroupMenuImpl.class);

    private final GroupService groupService;

    @Autowired
    public GroupMenuImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public void handlePrintGroupsWithLessOrEqualsStudentsAmount() {

        logger.debug("Print groups with less or equals students amount query received. Asking for params..");

        System.out.println("Type >=student's amount:");
        int requestedStudentsAmount = scanner.nextInt();

        logger.debug("Print groups with less or equals students amount query params received.");

        System.out.print(
                groupService.getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount)
        );
    }
}
