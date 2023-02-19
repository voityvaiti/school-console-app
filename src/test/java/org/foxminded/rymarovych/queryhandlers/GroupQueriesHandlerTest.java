package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.JdbcGroupDao;
import org.foxminded.rymarovych.models.Group;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupQueriesHandlerTest {

    static JdbcGroupDao groupDao;

    static Map<Integer, Integer> groupDaoReturnedMap;

    static Group group1 = new Group();
    static Group group2 = new Group();
    static Group group3 = new Group();
    static Group group4 = new Group();

    static final String NEWLINE = "\n";

    @BeforeAll
    static void setUp() {
        groupDao = Mockito.mock(JdbcGroupDao.class);

        groupDaoReturnedMap = new HashMap<>();
        groupDaoReturnedMap.put(1, 10);
        groupDaoReturnedMap.put(2, 20);
        groupDaoReturnedMap.put(3, 30);
        groupDaoReturnedMap.put(4, 40);

        group1.setName("GROUP1");
        group2.setName("GROUP2");
        group3.setName("GROUP3");
        group4.setName("GROUP4");

        when(groupDao.getGroupIdToStudentsAmount()).thenReturn(groupDaoReturnedMap);
        when(groupDao.getGroupById(1)).thenReturn(Optional.of(group1));
        when(groupDao.getGroupById(2)).thenReturn(Optional.of(group2));
        when(groupDao.getGroupById(3)).thenReturn(Optional.of(group3));
        when(groupDao.getGroupById(4)).thenReturn(Optional.of(group4));
    }

    @Test
    void shouldReturnCorrectMessage_ifSuitableGroupsIsPresent() {
        int requestedStudentsAmount = 21;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE;

        assertEquals(expected, new GroupQueriesHandler(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnMessageWithAllGroups_ifQuerySuitsToAllGroups() {

        int requestedStudentsAmount = 55;

        String expected = group1.toString() + NEWLINE +
                group2.toString() + NEWLINE +
                group3.toString() + NEWLINE +
                group4.toString() + NEWLINE;

        assertEquals(expected, new GroupQueriesHandler(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnNotFoundMessage_ifSuitableGroupsIsAbsent() {

        int requestedStudentsAmount = 9;

        String expected = "No such groups\n";

        assertEquals(expected, new GroupQueriesHandler(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }
}