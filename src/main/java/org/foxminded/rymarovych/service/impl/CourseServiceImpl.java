package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.service.abstractions.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDao courseDao;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    protected CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public void handleStudentAdditionToTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        studentAdditionToTheCourse(studentId, courseName);

    }

    protected void studentAdditionToTheCourse(int studentId, String courseName) {

        if (courseDao.findCourseByName(courseName).isPresent()) {
            courseDao.addStudentToTheCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }

    public void handleStudentRemovingFromTheCourse() {
        System.out.println("Type student id:");
        int studentId = scanner.nextInt();

        System.out.println("Type course name:");
        String courseName = scanner.next();

        studentRemovingFromTheCourse(studentId, courseName);
    }

    protected void studentRemovingFromTheCourse(int studentId, String courseName) {

        if (courseDao.findCourseByName(courseName).isPresent()) {
            courseDao.deleteStudentFromCourse(studentId, courseName);

        } else {
            System.out.println("No such course");
        }
    }
}
