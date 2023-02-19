package org.foxminded.rymarovych.dao.abstractions;

import org.foxminded.rymarovych.models.Course;

import java.util.Optional;

public interface CourseDao {

    Optional<Course> findCourseByName(String name);

    void addStudentToTheCourse(int studentId, String courseName);

    void deleteStudentFromCourse(int studentId, String courseName);
}
