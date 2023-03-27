package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.GroupRepository;
import org.foxminded.rymarovych.models.Group;
import org.foxminded.rymarovych.models.dto.StudentAmountInGroupDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GroupServiceImpl.class})
class GroupServiceImplTest {

    @MockBean
    GroupRepository groupDao;

    @Autowired
    GroupServiceImpl groupService;

    static List<StudentAmountInGroupDto> groupDaoReturnedList;

    static Group group1 = new Group();
    static Group group2 = new Group();
    static Group group3 = new Group();
    static Group group4 = new Group();

    static final String NEWLINE = "\n";

    static {
        groupDaoReturnedList = new ArrayList<>();
        groupDaoReturnedList.add(new StudentAmountInGroupDto(1, 10L));
        groupDaoReturnedList.add(new StudentAmountInGroupDto(2, 20L));
        groupDaoReturnedList.add(new StudentAmountInGroupDto(3, 30L));
        groupDaoReturnedList.add(new StudentAmountInGroupDto(4, 40L));


        group1.setName("GROUP1");
        group2.setName("GROUP2");
        group3.setName("GROUP3");
        group4.setName("GROUP4");
    }

    @BeforeEach
    void setUp() {

        when(groupDao.getGroupIdToStudentsAmount()).thenReturn(groupDaoReturnedList);
        when(groupDao.findById(1)).thenReturn(Optional.of(group1));
        when(groupDao.findById(2)).thenReturn(Optional.of(group2));
        when(groupDao.findById(3)).thenReturn(Optional.of(group3));
        when(groupDao.findById(4)).thenReturn(Optional.of(group4));
    }

    @Test
    void shouldReturnCorrectMessage_ifSuitableGroupsIsPresent() {
        int requestedStudentsAmount = 21;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE;

        assertEquals(expected, groupService.
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnMessageWithAllGroups_ifQuerySuitsToAllGroups() {

        int requestedStudentsAmount = 55;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE +
                group3.toString() + NEWLINE +
                group4.toString() + NEWLINE;

        assertEquals(expected, groupService.
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnNotFoundMessage_ifSuitableGroupsIsAbsent() {

        int requestedStudentsAmount = 9;

        String expected = "No such groups\n";

        assertEquals(expected, groupService.
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }
}