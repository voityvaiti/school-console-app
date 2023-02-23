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


    private static final Logger logger = LoggerFactory.getLogger(CourseDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();


    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Course> findCourseById(int id) {
        logger.debug("Attempt to find course by ID");

        Course course = jdbcTemplate
                .query(FIND_COURSE_BY_ID_STATEMENT, courseRowMapper, id)
                .stream().findAny().orElse(null);

        if (course == null) {
            logger.warn("Course not found by ID");
            return Optional.empty();

        } else {
            logger.debug("Found course by ID");
            return Optional.of(course);
        }
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        logger.debug("Attempt to find course by name");

        Course course = jdbcTemplate
                .query(FIND_COURSE_BY_NAME_STATEMENT, courseRowMapper, name)
                .stream().findAny().orElse(null);

        if (course == null) {
            logger.warn("Course not found by course name");
            return Optional.empty();
        } else {
            logger.debug("Found course by course name");
            return Optional.of(course);
        }

    }

    @Override
    public List<Course> getStudentCourses(int studentId) {
        logger.debug("Attempt to get list of student courses");

        return jdbcTemplate.query(GET_COURSES_BY_STUDENT_ID, courseRowMapper, studentId);
    }

    @Override
    public void addStudentToTheCourse(int studentId, String courseName) {
        logger.debug("Attempt to add student to the course");

        jdbcTemplate.update(ADD_STUDENT_COURSE_STATEMENT, studentId, courseName);

        logger.info("Student addition to the course statement executed");
    }

    @Override
    public void deleteStudentFromCourse(int studentId, String courseName) {
        logger.debug("Attempt to delete student from the course");

        jdbcTemplate.update(DELETE_STUDENT_COURSE_BY_ID_STATEMENT, studentId, courseName);

        logger.info("Student deletion from the course statement executed");
    }
}
