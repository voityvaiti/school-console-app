package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.GroupDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GroupQueriesHandlerTest {

    static GroupDao groupDao;

    static Map<Integer, Integer> groupDaoReturnedMap;

    @BeforeAll
    static void setUp() {
        groupDao = Mockito.mock(GroupDao.class);

        groupDaoReturnedMap = new HashMap<>();
        groupDaoReturnedMap.put(1, 10);
        groupDaoReturnedMap.put(2, 20);
        groupDaoReturnedMap.put(3, 30);
        groupDaoReturnedMap.put(4, 40);

        when(groupDao.getGroupIdToStudentsAmount()).thenReturn(groupDaoReturnedMap);

        when(groupDao.getGroupNameById(1)).thenReturn("GROUP1");
        when(groupDao.getGroupNameById(2)).thenReturn("GROUP2");
        when(groupDao.getGroupNameById(3)).thenReturn("GROUP3");
        when(groupDao.getGroupNameById(4)).thenReturn("GROUP4");
    }

    @Test
    void shouldReturnCorrectMessage_ifSuitableGroupsIsPresent() {
        int requestedStudentsAmount = 21;

        String expected = """
                GROUP1
                GROUP2
                """;

        assertEquals(expected, new GroupQueriesHandler(groupDao).
                getMessageOfGroupsWithLessOrEqualsStudentsAmount(requestedStudentsAmount));

    }

    @Test
    void shouldReturnMessageWithAllGroups_ifQuerySuitsToAllGroups() {

        int requestedStudentsAmount = 55;

        String expected = """
                GROUP1
                GROUP2
                GROUP3
                GROUP4
                """;

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