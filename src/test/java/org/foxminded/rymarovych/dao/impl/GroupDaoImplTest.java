package org.foxminded.rymarovych.dao.impl;

import org.foxminded.rymarovych.dao.abstractions.GroupDao;
import org.foxminded.rymarovych.models.Group;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        GroupDaoImpl.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/SAMPLE_DATA.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class GroupDaoImplTest {

    @Autowired
    private GroupDao dao;

    @Test
    void getGroupIdToStudentsAmount() {
        Map<Integer, Integer> expected = new HashMap<>();
        expected.put(1, 3);
        expected.put(2, 7);
        expected.put(4, 5);
        expected.put(5, 5);

        assertEquals(expected, dao.getGroupIdToStudentsAmount());
    }

    @Test
    void getGroupById() {
        final int GROUP_ID = 4;
        Group expected = new Group(GROUP_ID, "VC-33");

        assertTrue(dao.findGroupById(GROUP_ID).isPresent());
        assertEquals(expected, dao.findGroupById(GROUP_ID).get());
    }
}