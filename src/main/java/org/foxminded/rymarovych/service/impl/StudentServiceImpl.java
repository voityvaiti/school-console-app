package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.StudentRepository;
import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.service.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    protected StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public String printStudentsRelatedToCourse(String courseName) {

        StringBuilder messageBuilder = new StringBuilder();

        List<Student> studentList = studentRepository.getStudentsByCourseName(courseName);

        if (studentList.isEmpty()) {
            messageBuilder.append("No students related to this course or no such course\n");

        } else {

            for (Student student : studentList) {
                messageBuilder.append(student.toString()).append("\n");
            }
        }

        return messageBuilder.toString();
    }

    public void studentAddition(int groupId, String firstName, String lastName) {
        studentRepository.save(
                new Student(groupId, firstName, lastName)
        );
    }

    public void removeStudentById(int id) {
        studentRepository.deleteById(id);
    }
}
