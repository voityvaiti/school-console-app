package org.foxminded.rymarovych.dao;

import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.dao.rowmapper.CourseRowMapper;
import org.foxminded.rymarovych.models.Course;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JdbcCourseDao implements CourseDao {

    public static final String GET_COURSE_ID_BY_NAME_STATEMENT = "SELECT * FROM courses WHERE name=?";
    public static final String ADD_STUDENT_COURSE_STATEMENT = "INSERT INTO students_courses (student_id, course_id) VALUES (?," +
                    "(SELECT id FROM courses WHERE name = ?));";
    public static final String DElETE_STUDENT_COURSE_BY_ID_STATEMENT = "DELETE FROM students_courses WHERE student_id = ? " +
            "AND course_id = (SELECT id FROM courses WHERE name = ?);";


    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Course> courseRowMapper = new CourseRowMapper();


    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<Course> findCourseByName(String name) {
        Course course = jdbcTemplate
                .query(GET_COURSE_ID_BY_NAME_STATEMENT, courseRowMapper, name)
                .stream().findAny().orElse(null);

        if (course == null) {
            return Optional.empty();
        } else {
            return Optional.of(course);
        }

    }

    @Override
    public void addStudentToTheCourse(int studentId, String courseName) {
        jdbcTemplate.update(ADD_STUDENT_COURSE_STATEMENT, studentId, courseName);
    }

    @Override
    public void deleteStudentFromCourse(int studentId, String courseName) {
        jdbcTemplate.update(DElETE_STUDENT_COURSE_BY_ID_STATEMENT, studentId, courseName);
    }
}
