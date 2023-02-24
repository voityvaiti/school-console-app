package org.foxminded.rymarovych.dao.impl;

import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.dao.rowmapper.CourseRowMapper;
import org.foxminded.rymarovych.models.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

    public static final String FIND_COURSE_BY_ID_STATEMENT = "SELECT * FROM courses WHERE id=?;";
    public static final String FIND_COURSE_BY_NAME_STATEMENT = "SELECT * FROM courses WHERE name=?;";
    public static final String GET_COURSES_BY_STUDENT_ID = """
            SELECT courses.id, courses.name, courses.description FROM courses 
            JOIN students_courses ON courses.id = course_id WHERE student_id = ?;
            """;
    public static final String ADD_STUDENT_COURSE_STATEMENT = """
            INSERT INTO students_courses (student_id, course_id) VALUES (?,
            (SELECT id FROM courses WHERE name = ?));
            """;
    public static final String DELETE_STUDENT_COURSE_BY_ID_STATEMENT = """
            DELETE FROM students_courses WHERE student_id = ?
            AND course_id = (SELECT id FROM courses WHERE name = ?);
            """;


    private static final Logger LOGGER  = LoggerFactory.getLogger(CourseDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();


    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Course> findCourseById(int id) {
        LOGGER.debug("Attempt to find course by ID: {}", id);

        Course course = jdbcTemplate
                .query(FIND_COURSE_BY_ID_STATEMENT, courseRowMapper, id)
                .stream().findAny().orElse(null);

        if (course == null) {
            LOGGER.warn("Course not found by ID: {}", id);
            return Optional.empty();

        } else {
            LOGGER.debug("Found course ({}) by ID: {}", course, id);
            return Optional.of(course);
        }
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        LOGGER.debug("Attempt to find course by name: {}", name);

        Course course = jdbcTemplate
                .query(FIND_COURSE_BY_NAME_STATEMENT, courseRowMapper, name)
                .stream().findAny().orElse(null);

        if (course == null) {
            LOGGER.warn("Course not found by course name: {}", name);
            return Optional.empty();
        } else {
            LOGGER.debug("Found course ({}) by course name: {}", course, name);
            return Optional.of(course);
        }

    }

    @Override
    public List<Course> getStudentCourses(int studentId) {
        LOGGER.debug("Attempt to get list of student courses by student ID: {}", studentId);
        List<Course> studentCourses =
                jdbcTemplate.query(GET_COURSES_BY_STUDENT_ID, courseRowMapper, studentId);

        LOGGER.debug("List of student courses gotten by student ID: {}. Size: {}", studentId, studentCourses.size());
        return studentCourses;
    }

    @Override
    public void addStudentToTheCourse(int studentId, String courseName) {
        LOGGER.debug("Attempt to add student by ID: {}, to the course by name: {}", studentId, courseName);

        jdbcTemplate.update(ADD_STUDENT_COURSE_STATEMENT, studentId, courseName);

        LOGGER.info("Student (ID: {}) addition to the course (name: {}) statement executed", studentId, courseName);
    }

    @Override
    public void deleteStudentFromCourse(int studentId, String courseName) {
        LOGGER.debug("Attempt to delete student by ID: {} from the course by name: {}", studentId, courseName);

        jdbcTemplate.update(DELETE_STUDENT_COURSE_BY_ID_STATEMENT, studentId, courseName);

        LOGGER.info("Student (ID: {}) deletion from the course (name: {}) statement executed", studentId, courseName);
    }
}
