package org.foxminded.rymarovych.dao.repository;

import org.assertj.core.api.CollectionAssert;
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
        CourseRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/SCHEMA_AND_TABLES_INIT.sql", "/sql/SAMPLE_DATA_FILL.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class CourseRepositoryTest {

    @Autowired
    private CourseRepository courseRepository;

    @Test
    void findCourseById() {
        final int COURSE_ID = 6;

        Course expected = new Course(COURSE_ID, "Chemistry", null);

        assertTrue(courseRepository.findById(COURSE_ID).isPresent());
        assertEquals(expected, courseRepository.findById(COURSE_ID).get());
    }

    @Test
    void findCourseByName() {
        final String COURSE_NAME = "English";

        Course expected = new Course(7, COURSE_NAME, null);

        assertTrue(courseRepository.findCourseByName(COURSE_NAME).isPresent());
        assertEquals(expected, courseRepository.findCourseByName(COURSE_NAME).get());
    }

    @Test
    void getStudentCourses() {
        final int STUDENT_ID = 9;
        List<Course> expectedList = Arrays.asList(
                new Course(8, "P.E.", null),
                new Course(1, "Biology", null)
        );

        CollectionAssert.assertThatCollection(expectedList).containsExactlyInAnyOrderElementsOf(
                courseRepository.getStudentCourses(STUDENT_ID)
        );
    }
}