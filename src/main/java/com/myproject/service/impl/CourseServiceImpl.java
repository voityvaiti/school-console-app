package com.myproject.service.impl;

import com.myproject.dao.CourseRepository;
import com.myproject.dao.relational.StudentCourseRepository;
import com.myproject.models.relational.StudentCourse;
import com.myproject.service.abstractions.CourseService;
import com.myproject.models.Course;
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
