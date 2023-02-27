package org.foxminded.rymarovych.dao.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.models.Course;
import org.foxminded.rymarovych.models.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoImpl implements CourseDao {

    public static final String FIND_COURSE_BY_ID_STATEMENT = "SELECT c FROM Course c WHERE c.id = :id";
    public static final String FIND_COURSE_BY_NAME_STATEMENT = "SELECT c FROM Course c WHERE c.name = :name";
    public static final String GET_COURSES_BY_STUDENT_ID = """
            SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId
            """;

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseDaoImpl.class);
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<Course> findCourseById(int id) {
        LOGGER.debug("Attempt to find course by ID: {}", id);
        try {
            Course course = entityManager.createQuery(FIND_COURSE_BY_ID_STATEMENT, Course.class)
                    .setParameter("id", id)
                    .getSingleResult();

            LOGGER.debug("Found course ({}) by ID: {}", course, id);
            return Optional.of(course);

        } catch (NoResultException e) {

            LOGGER.warn("Course not found by ID: {}", id);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findCourseByName(String name) {
        LOGGER.debug("Attempt to find course by name: {}", name);
        try {
            Course course = entityManager.createQuery(FIND_COURSE_BY_NAME_STATEMENT, Course.class)
                    .setParameter("name", name)
                    .getSingleResult();

            LOGGER.debug("Found course ({}) by course name: {}", course, name);
            return Optional.of(course);

        } catch (NoResultException e) {

            LOGGER.warn("Course not found by course name: {}", name);
            return Optional.empty();
        }
    }

    @Override
    public List<Course> getStudentCourses(int studentId) {
        LOGGER.debug("Attempt to get list of student courses by student ID: {}", studentId);
        List<Course> studentCourses =
                entityManager.createQuery(GET_COURSES_BY_STUDENT_ID, Course.class)
                        .setParameter("studentId", studentId)
                        .getResultList();

        LOGGER.debug("List of student courses gotten by student ID: {}. Size: {}", studentId, studentCourses.size());
        return studentCourses;
    }

    @Override
    @Transactional
    public void addStudentToTheCourse(int studentId, String courseName) {
        LOGGER.debug("Attempt to add student by ID: {}, to the course by name: {}", studentId, courseName);

        Student student = entityManager.find(Student.class, studentId);
        Optional<Course> optionalCourse = findCourseByName(courseName);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            student.addCourse(course);

            LOGGER.info("Student (ID: {}) addition to the course (name: {}) statement executed", studentId, courseName);
        }
    }

    @Override
    @Transactional
    public void deleteStudentFromCourse(int studentId, String courseName) {
        LOGGER.debug("Attempt to delete student by ID: {} from the course by name: {}", studentId, courseName);

        Student student = entityManager.find(Student.class, studentId);
        Optional<Course> optionalCourse = findCourseByName(courseName);

        if (optionalCourse.isPresent()) {
            Course course = optionalCourse.get();

            student.removeCourse(course);

            LOGGER.info("Student (ID: {}) deletion from the course (name: {}) statement executed", studentId, courseName);
        }
    }
}
