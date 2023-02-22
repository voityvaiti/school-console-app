package org.foxminded.rymarovych.queryhandlers.impl;

import org.foxminded.rymarovych.dao.abstractions.GroupDao;
import org.foxminded.rymarovych.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {GroupQueriesHandlerImpl.class})
class GroupQueriesHandlerImplTest {

    @MockBean
    GroupDao groupDao;

    @Autowired
    GroupQueriesHandlerImpl groupQueriesHandler;

    static Map<Integer, Integer> groupDaoReturnedMap;

    static Group group1 = new Group();
    static Group group2 = new Group();
    static Group group3 = new Group();
    static Group group4 = new Group();

    static final String NEWLINE = "\n";

    static {
        groupDaoReturnedMap = new HashMap<>();
        groupDaoReturnedMap.put(1, 10);
        groupDaoReturnedMap.put(2, 20);
        groupDaoReturnedMap.put(3, 30);
        groupDaoReturnedMap.put(4, 40);

        group1.setName("GROUP1");
        group2.setName("GROUP2");
        group3.setName("GROUP3");
        group4.setName("GROUP4");
    }

    @BeforeEach
    void setUp() {

        when(groupDao.getGroupIdToStudentsAmount()).thenReturn(groupDaoReturnedMap);
        when(groupDao.findGroupById(1)).thenReturn(Optional.of(group1));
        when(groupDao.findGroupById(2)).thenReturn(Optional.of(group2));
        when(groupDao.findGroupById(3)).thenReturn(Optional.of(group3));
        when(groupDao.findGroupById(4)).thenReturn(Optional.of(group4));
    }

    @Test
    void shouldReturnCorrectMessage_ifSuitableGroupsIsPresent() {
        int requestedStudentsAmount = 21;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE;

        assertEquals(expected, new GroupQueriesHandlerImpl(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnMessageWithAllGroups_ifQuerySuitsToAllGroups() {

        int requestedStudentsAmount = 55;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE +
                group3.toString() + NEWLINE +
                group4.toString() + NEWLINE;

        assertEquals(expected, new GroupQueriesHandlerImpl(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnNotFoundMessage_ifSuitableGroupsIsAbsent() {

        int requestedStudentsAmount = 9;

        String expected = "No such groups\n";

        assertEquals(expected, new GroupQueriesHandlerImpl(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }
}