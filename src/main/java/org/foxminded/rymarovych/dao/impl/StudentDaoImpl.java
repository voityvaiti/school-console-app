package org.foxminded.rymarovych.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoImpl implements StudentDao {

    public static final String GET_STUDENTS_BY_COURSE_NAME = """
            SELECT s FROM Student s JOIN s.courses c WHERE c.name= :name
            """;
    public static final String FIND_STUDENT_BY_ID = "SELECT s FROM Student s WHERE s.id = :id";
    public static final String ADD_STUDENT_STATEMENT =
            "INSERT INTO students (id, group_id, first_name, last_name) VALUES (?, ?, ?, ?)";
    public static final String DELETE_STUDENT_BY_ID_STATEMENT = "DELETE FROM Student WHERE id = :id";

    public static final String GET_MAX_ID = "SELECT MAX(s.id) AS max_id from Student s";

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDaoImpl.class);


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getStudentsByCourseName(String courseName) {
        LOGGER.debug("Attempt to get list of students by course name: {}", courseName);

        List<Student> gottenStudentsByCourseName =
                entityManager.createQuery(GET_STUDENTS_BY_COURSE_NAME, Student.class)
                        .setParameter("name", courseName)
                        .getResultList();

        LOGGER.debug("List of students by course (name: {}) received. Size: {}", courseName, gottenStudentsByCourseName.size());
        return gottenStudentsByCourseName;
    }

    @Override
    public Optional<Student> findStudentById(int id) {
        LOGGER.debug("Attempt to find student by ID: {}", id);

        try {
            Student student = entityManager.createQuery(FIND_STUDENT_BY_ID, Student.class)
                    .setParameter("id", id).getSingleResult();

            LOGGER.debug("Found student ({}) by ID: {}", student, id);
            return Optional.of(student);

        } catch (NoResultException e) {

            LOGGER.warn("Student not found by ID: {}", id);
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public void addStudent(Student student) {
        LOGGER.debug("Attempt to add student: ({})", student);

        int currentStudentsMaxID = getMaxId();

        entityManager.createNativeQuery(ADD_STUDENT_STATEMENT)
                .setParameter(1, ++currentStudentsMaxID)
                .setParameter(2, student.getGroupId())
                .setParameter(3, student.getFirstName())
                .setParameter(4, student.getLastName())
                .executeUpdate();

        LOGGER.info("Student ({}) addition statement executed", student);
    }

    @Override
    @Transactional
    public void deleteStudent(int id) {
        LOGGER.debug("Attempt to delete student (ID: {})", id);

        entityManager.createQuery(DELETE_STUDENT_BY_ID_STATEMENT)
                .setParameter("id", id).executeUpdate();

        LOGGER.info("Student (ID: {}) deletion statement executed", id);
    }

    @Override
    public int getMaxId() {
        LOGGER.debug("Received query for students max ID");

        try {
            int amount = (Integer) entityManager.createQuery(GET_MAX_ID)
                    .getSingleResult();

            LOGGER.debug("Max students ID found: {}. Returning result", amount);
            return amount;

        } catch (NoResultException e) {

            LOGGER.warn("Max students ID not found");
            return -1;
        }
    }
}
