package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    @Autowired
    protected CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void studentAdditionToTheCourse(int studentId, String courseName) {

        if (courseDao.findCourseByName(courseName).isPresent()) {
            courseDao.addStudentToTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    public void studentRemovingFromTheCourse(int studentId, String courseName) {

        if (courseDao.findCourseByName(courseName).isPresent()) {
            courseDao.deleteStudentFromCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }
}
