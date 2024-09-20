package org.foxminded.rymarovych.dao.repository;

import org.foxminded.rymarovych.models.Group;
import org.foxminded.rymarovych.models.dto.StudentAmountInGroupDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        GroupRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/SCHEMA_AND_TABLES_INIT.sql", "/sql/SAMPLE_DATA_FILL.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class GroupRepositoryTest {
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void getGroupIdToStudentsAmount() {
        List<StudentAmountInGroupDto> expected = new ArrayList<>();
        expected.add(new StudentAmountInGroupDto(1, 3L));
        expected.add(new StudentAmountInGroupDto(2, 7L));
        expected.add(new StudentAmountInGroupDto(4, 5L));
        expected.add(new StudentAmountInGroupDto(5, 5L));

        assertTrue(expected.containsAll(groupRepository.getGroupIdToStudentsAmount()));
    }

    @Test
    void getGroupById() {
        final int GROUP_ID = 4;
        Group expected = new Group(GROUP_ID, "VC-33");

        assertTrue(groupRepository.findById(GROUP_ID).isPresent());
        assertEquals(expected, groupRepository.findById(GROUP_ID).get());
    }
}