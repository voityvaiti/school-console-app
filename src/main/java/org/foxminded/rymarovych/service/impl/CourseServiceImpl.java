package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.CourseRepository;
import org.foxminded.rymarovych.dao.repository.relational.StudentCourseRepository;
import org.foxminded.rymarovych.models.Course;
import org.foxminded.rymarovych.models.relational.StudentCourse;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    @Autowired
    protected CourseServiceImpl(CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    public void studentAdditionToTheCourse(int studentId, String courseName) {

        Optional<Course> optionalCourse = courseRepository.findCourseByName(courseName);

        if (optionalCourse.isPresent()) {

            studentCourseRepository.save(
                    new StudentCourse(studentId, optionalCourse.get().getId())
            );

        } else {
            System.out.println("No such course");
        }
    }

    public void studentRemovingFromTheCourse(int studentId, String courseName) {

        Optional<Course> optionalCourse = courseRepository.findCourseByName(courseName);

        if (optionalCourse.isPresent()) {

            Optional<StudentCourse> optionalStudentCourse =
                    studentCourseRepository.findStudentCourseByStudentIdAndCourseId(
                    studentId, optionalCourse.get().getId()
            );

            optionalStudentCourse.ifPresent(studentCourseRepository::delete);

        } else {
            System.out.println("No such course");
        }

    }
}
