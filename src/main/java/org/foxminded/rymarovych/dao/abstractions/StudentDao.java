package org.foxminded.rymarovych.dao.abstractions;

import org.foxminded.rymarovych.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    List<Student> getStudentsByCourseName(String courseName);

    Optional<Student> findStudentById(int id);

    void addStudent(Student student);

    void deleteStudent(int id);
}
