package com.myproject.service.abstractions;

public interface CourseService {

    void studentAdditionToTheCourse(int studentId, String courseName);

    void studentRemovingFromTheCourse(int studentId, String courseName);
}
