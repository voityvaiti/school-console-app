package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.service.abstractions.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentsDao;

    @Autowired
    protected StudentServiceImpl(StudentDao studentsDao) {
        this.studentsDao = studentsDao;
    }

    public String printStudentsRelatedToCourse(String courseName) {

        StringBuilder messageBuilder = new StringBuilder();

        List<Student> studentList = studentsDao.getStudentsByCourseName(courseName);

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
        studentsDao.addStudent(
                new Student(groupId, firstName, lastName)
        );
    }

    public void removeStudentById(int id) {
        studentsDao.deleteStudent(id);
    }
}
