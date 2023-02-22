package org.foxminded.rymarovych.queryhandlers.impl;

import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.foxminded.rymarovych.queryhandlers.abstractions.StudentQueriesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class StudentQueriesHandlerImpl implements StudentQueriesHandler {

    private final StudentDao studentsDao;

    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    protected StudentQueriesHandlerImpl(StudentDao studentsDao) {
        this.studentsDao = studentsDao;
    }

    public void handlePrintStudentsRelatedToCourse() {
        System.out.println("Type course name:");
        String courseName = scanner.next();

        System.out.print(
                printStudentsRelatedToCourse(courseName)
        );
    }

    protected String printStudentsRelatedToCourse(String courseName) {

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

    public void handleStudentAddition() {
        System.out.println("Type Student's group id:");
        int groupId = scanner.nextInt();

        System.out.println("Type Student's first name:");
        String firstName = scanner.next();

        System.out.println("Type Student's last name:");
        String lastName = scanner.next();

        studentAddition(groupId, firstName, lastName);
    }

    protected void studentAddition(int groupId, String firstName, String lastName) {
        studentsDao.addStudent(
                new Student(groupId, firstName, lastName)
        );
    }

    public void handleRemoveStudentById() {
        System.out.println("Type Student's id:");
        int id = scanner.nextInt();

        removeStudentById(id);
    }

    protected void removeStudentById(int id) {
        studentsDao.deleteStudent(id);
    }
}
