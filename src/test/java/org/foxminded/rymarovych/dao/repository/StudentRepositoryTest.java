package org.foxminded.rymarovych.dao.repository;

import org.assertj.core.api.CollectionAssert;
import org.foxminded.rymarovych.models.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        StudentRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/SAMPLE_DATA.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class StudentRepositoryTest {
    @Autowired
    private StudentRepository dao;

    @Test
    void getStudentsByCourseName() {
        List<Student> expectedList = Arrays.asList(
                new Student(3, 1, "Jorge", "Roberts"),
                new Student(14, 4, "Jorge", "Robinson"),
                new Student(15, 4, "John", "Lewis")
        );

        CollectionAssert.assertThatCollection(expectedList).containsExactlyInAnyOrderElementsOf(
                dao.getStudentsByCourseName("Math")
        );

    }

    @Test
    void findStudentById() {
        final int STUDENT_ID = 11;

        Student expected = new Student(STUDENT_ID, 4, "Stella", "Thomas");

        assertTrue(dao.findById(STUDENT_ID).isPresent());
        assertEquals(expected, dao.findById(STUDENT_ID).get());
    }

    @Test
    void addStudent() {
        final int STUDENT_ID = dao.getMaxId() + 1;

        Student studentToAdd = new Student(
                5, "Michael", "Stevenson"
        );

        dao.save(studentToAdd);

        Optional<Student> optionalActualStudent = dao.findById(STUDENT_ID);

        assertTrue(optionalActualStudent.isPresent());
        assertEquals(studentToAdd, optionalActualStudent.get());
    }

    @Test
    void deleteStudent() {
        dao.deleteById(1);
        assertEquals(dao.findById(1), Optional.empty());
    }
}