package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.CourseRepository;
import org.foxminded.rymarovych.models.Course;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    protected CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void studentAdditionToTheCourse(int studentId, String courseName) {

        Optional<Course> optionalCourse = courseRepository.findCourseByName(courseName);

        if (optionalCourse.isPresent()) {
            courseRepository.addStudentToTheCourse(studentId, optionalCourse.get().getId());

        } else {
            System.out.println("No such course");
        }
    }

    public void studentRemovingFromTheCourse(int studentId, String courseName) {

        Optional<Course> optionalCourse = courseRepository.findCourseByName(courseName);

        if (optionalCourse.isPresent()) {
            courseRepository.deleteStudentFromCourse(studentId, optionalCourse.get().getId());

        } else {
            System.out.println("No such course");
        }

    }
}
