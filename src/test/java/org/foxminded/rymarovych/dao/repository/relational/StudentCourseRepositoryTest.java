package org.foxminded.rymarovych.dao.repository.relational;

import org.foxminded.rymarovych.models.relational.StudentCourse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest(includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = {
        StudentCourseRepository.class
}))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/SCHEMA_AND_TABLES_INIT.sql", "/sql/SAMPLE_DATA_FILL.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
class StudentCourseRepositoryTest {

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Test
    void addStudentToTheCourse() {
        final int STUDENT_ID = 7;
        final int COURSE_ID = 6;

        assertTrue(studentCourseRepository.findStudentCourseByStudentIdAndCourseId(STUDENT_ID, COURSE_ID).isEmpty());

        studentCourseRepository.save(new StudentCourse(STUDENT_ID, COURSE_ID));

        assertFalse(studentCourseRepository.findStudentCourseByStudentIdAndCourseId(STUDENT_ID, COURSE_ID).isEmpty());
    }

    @Test
    void deleteStudentFromCourse() {
        final int STUDENT_COURSE_ID = 4;

        assertFalse(studentCourseRepository.findById(STUDENT_COURSE_ID).isEmpty());

        studentCourseRepository.deleteById(STUDENT_COURSE_ID);

        assertTrue(studentCourseRepository.findById(STUDENT_COURSE_ID).isEmpty());
    }

}