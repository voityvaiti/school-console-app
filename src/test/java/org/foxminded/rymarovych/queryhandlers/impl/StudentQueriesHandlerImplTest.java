package org.foxminded.rymarovych.queryhandlers.impl;

import org.foxminded.rymarovych.dao.abstractions.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {StudentQueriesHandlerImpl.class})
class StudentQueriesHandlerImplTest {

    @MockBean
    StudentDao studentDao;

    @Autowired
    StudentQueriesHandlerImpl studentQueriesHandler;

    @Test
    void shouldReturnMessageWithStudents_ifStudentsWereFound() {

        List<Student> studentDaoReturnedList = new ArrayList<>();

        when(studentDao.getStudentsByCourseName(anyString())).thenReturn(studentDaoReturnedList);

        studentDaoReturnedList.add(
                new Student(1, 1, "John", "Johnson")
        );
        studentDaoReturnedList.add(
                new Student(2, 5, "Mary", "Strode")
        );

        String expected = """
                Student{id=1, groupId=1, firstName='John', lastName='Johnson'}
                Student{id=2, groupId=5, firstName='Mary', lastName='Strode'}
                """;

        Assertions.assertEquals(expected, new StudentQueriesHandlerImpl(studentDao).
                printStudentsRelatedToCourse(anyString()));
    }

    @Test
    void shouldReturnNotFoundMessage_ifStudentsWereNotFound() {

        List<Student> studentDaoReturnedList = new ArrayList<>();

        when(studentDao.getStudentsByCourseName(anyString())).thenReturn(studentDaoReturnedList);

        String expected = "No students related to this course or no such course\n";

        assertEquals(expected, new StudentQueriesHandlerImpl(studentDao).
                printStudentsRelatedToCourse(anyString()));
    }

    @Test
    void shouldAddStudent() {
        int groupId = 4;
        String firstName = "John";
        String lastName = "White";

        Student expectedStudentToAdd = new Student(
                groupId, firstName, lastName
        );

        studentQueriesHandler.studentAddition(
                groupId, firstName, lastName
        );

        verify(studentDao).addStudent(expectedStudentToAdd);
    }

    @Test
    void shouldRemoveStudent() {

        int studentId = 21;

        studentQueriesHandler.removeStudentById(studentId);

        verify(studentDao).deleteStudent(studentId);
    }
}