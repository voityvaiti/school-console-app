package org.foxminded.rymarovych.dao;

import org.assertj.core.api.CollectionAssert;
import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.onstartup.tablefiller.TableFiller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/SAMPLE_DATA.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class JdbcStudentDaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private StudentDao dao;

    @BeforeEach
    void setUp() {
        dao = new JdbcStudentDao(jdbcTemplate);
    }

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

        assertTrue(dao.findStudentById(STUDENT_ID).isPresent());
        assertEquals(expected, dao.findStudentById(STUDENT_ID).get());
    }

    @Test
    void addStudent() {
        final int STUDENT_ID = TableFiller.STUDENTS_AMOUNT + 1;

        Student student = new Student(
                STUDENT_ID,5, "Michael", "Stevenson"
        );

        dao.addStudent(student);

        assertEquals(student, dao.findStudentById(STUDENT_ID).get());
    }

    @Test
    void deleteStudent() {
        dao.deleteStudent(1);
        assertEquals(dao.findStudentById(1), Optional.empty());
    }
}