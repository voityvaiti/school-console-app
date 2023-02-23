package org.foxminded.rymarovych.dao.impl;

import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.dao.rowmapper.StudentRowMapper;
import org.foxminded.rymarovych.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    public static final String GET_STUDENTS_BY_COURSE_NAME = """
            SELECT students.id AS id, students.group_id AS group_id,
            students.first_name AS first_name,
            students.last_name AS last_name
            FROM students JOIN students_courses
            ON students.id = students_courses.student_id
            JOIN courses
            ON students_courses.course_id = courses.id
            WHERE courses.name = ?;
            """;
    public static final String GET_STUDENT_BY_ID = "SELECT * FROM students WHERE id=?";
    public static final String ADD_STUDENT_STATEMENT =
            "INSERT INTO students (id, group_id, first_name, last_name) VALUES (?, ?, ?, ?)";
    public static final String DELETE_STUDENT_BY_ID_STATEMENT = "DELETE FROM students WHERE id=?";

    public static final String GET_MAX_ID = "SELECT MAX(id) AS max_id from students";

    private int currentStudentMaxId;


    private static final Logger logger = LoggerFactory.getLogger(StudentDaoImpl.class);
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.currentStudentMaxId = getMaxId();
    }


    public List<Student> getStudentsByCourseName(String courseName) {
        logger.debug("Attempt to get list of students by course name");

        return jdbcTemplate.query(GET_STUDENTS_BY_COURSE_NAME, studentRowMapper, courseName);
    }

    public Optional<Student> findStudentById(int id) {
        logger.debug("Attempt to find student by ID");

        Student student = jdbcTemplate.query(GET_STUDENT_BY_ID, studentRowMapper, id)
                .stream().findAny().orElse(null);

        if (student == null) {
            logger.warn("Student not found by ID");
            return Optional.empty();
        } else {
            logger.debug("Found students by ID");
            return Optional.of(student);
        }
    }

    public void addStudent(Student student) {
        logger.debug("Attempt to add student");

        jdbcTemplate.update(ADD_STUDENT_STATEMENT, ++currentStudentMaxId, student.getGroupId(),
                student.getFirstName(), student.getLastName());

        logger.info("Student addition statement executed");
    }

    public void deleteStudent(int id) {
        logger.debug("Attempt to delete student");

        jdbcTemplate.update(DELETE_STUDENT_BY_ID_STATEMENT, id);

        logger.info("Student deletion statement executed");
    }

    public int getMaxId() {
        logger.debug("Received query for students max ID");

        Integer amount =  jdbcTemplate.queryForObject(GET_MAX_ID, Integer.class);

        if(amount == null) {
            logger.warn("Max students ID not found");
            return -1;

        } else {
            logger.debug("Max students ID found. Returning result");
            return amount;
        }
    }
}
