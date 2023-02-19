package org.foxminded.rymarovych.dao.abstractions;

import org.foxminded.rymarovych.models.Student;

import java.util.List;

public interface StudentDao {

    List<Student> getStudentsByCourseName(String courseName);

    void addStudent(Student student);

    void deleteStudent(int id);
}
