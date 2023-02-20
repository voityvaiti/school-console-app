package org.foxminded.rymarovych.dao.abstractions;

import org.foxminded.rymarovych.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {

    Optional<Course> findCourseById(int id);

    Optional<Course> findCourseByName(String name);

    List<Course> getStudentCourses(int studentId);

    void addStudentToTheCourse(int studentId, String courseName);

    void deleteStudentFromCourse(int studentId, String courseName);
}
