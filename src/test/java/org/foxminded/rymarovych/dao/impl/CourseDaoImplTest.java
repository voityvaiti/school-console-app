package org.foxminded.rymarovych.dao.impl;

import org.assertj.core.api.CollectionAssert;
import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.models.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        CourseDaoImpl.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/CREATE_TABLES.sql", "/sql/SAMPLE_DATA.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class CourseDaoImplTest {

    @Autowired
    private CourseDao dao;

    @Test
    void findCourseById() {
        final int COURSE_ID = 6;

        Course expected = new Course(COURSE_ID, "Chemistry", null);

        assertTrue(dao.findCourseById(COURSE_ID).isPresent());
        assertEquals(expected, dao.findCourseById(COURSE_ID).get());
    }

    @Test
    void findCourseByName() {
        final String COURSE_NAME = "English";

        Course expected = new Course(7, COURSE_NAME, null);

        assertTrue(dao.findCourseByName(COURSE_NAME).isPresent());
        assertEquals(expected, dao.findCourseByName(COURSE_NAME).get());
    }

    @Test
    void getStudentCourses() {
        final int STUDENT_ID = 9;
        List<Course> expectedList = Arrays.asList(
                new Course(8, "P.E.", null),
                new Course(1, "Biology", null)
        );

        CollectionAssert.assertThatCollection(expectedList).containsExactlyInAnyOrderElementsOf(
                dao.getStudentCourses(STUDENT_ID)
        );
    }

    @Test
    void addStudentToTheCourse() {
        final int STUDENT_ID = 7;
        final String COURSE_NAME = "Chemistry";

        assertTrue(dao.getStudentCourses(STUDENT_ID).stream().filter(c -> c.getName().equals(COURSE_NAME)).toList().isEmpty());

        dao.addStudentToTheCourse(STUDENT_ID, COURSE_NAME);

        assertFalse(dao.getStudentCourses(STUDENT_ID).stream().filter(c -> c.getName().equals(COURSE_NAME)).toList().isEmpty());
    }

    @Test
    void deleteStudentFromCourse() {
        final int STUDENT_ID = 8;
        final String COURSE_NAME = "Physics";

        assertFalse(dao.getStudentCourses(STUDENT_ID).stream().filter(c -> c.getName().equals(COURSE_NAME)).toList().isEmpty());

        dao.deleteStudentFromCourse(STUDENT_ID, COURSE_NAME);

        assertTrue(dao.getStudentCourses(STUDENT_ID).stream().filter(c -> c.getName().equals(COURSE_NAME)).toList().isEmpty());
    }
}