package org.foxminded.rymarovych.dao.impl;

import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.dao.rowmapper.StudentRowMapper;
import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.onstartup.tablefiller.TableFiller;
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

    public static final String COUNT_STUDENTS = "SELECT COUNT(id) AS amount from students";

    private int currentStudentMaxId = countStudents();

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Student> studentRowMapper = new StudentRowMapper();

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Student> getStudentsByCourseName(String courseName) {
        return jdbcTemplate.query(GET_STUDENTS_BY_COURSE_NAME, studentRowMapper, courseName);
    }

    public Optional<Student> findStudentById(int id) {
        Student student = jdbcTemplate.query(GET_STUDENT_BY_ID, studentRowMapper, id)
                .stream().findAny().orElse(null);

        if (student == null) {
            return Optional.empty();
        } else {
            return Optional.of(student);
        }
    }

    public void addStudent(Student student) {
        jdbcTemplate.update(ADD_STUDENT_STATEMENT, ++currentStudentMaxId, student.getGroupId(),
                student.getFirstName(), student.getLastName());
    }

    public void deleteStudent(int id) {
        jdbcTemplate.update(DELETE_STUDENT_BY_ID_STATEMENT, id);
    }

    public int countStudents() {
        Integer amount =  jdbcTemplate.queryForObject(COUNT_STUDENTS, Integer.class);

        return Objects.requireNonNullElse(amount, -1);
    }
}
