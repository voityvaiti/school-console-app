package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.StudentCourseDao;
import org.foxminded.rymarovych.dao.StudentDao;
import org.foxminded.rymarovych.models.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class StudentQueriesHandlerTest {

    static StudentDao studentDao;

    static List<Student> studentDaoReturnedList;

    @BeforeAll
    static void setUp() {
        studentDao = Mockito.mock(StudentDao.class);
        studentDaoReturnedList = new ArrayList<>();

        when(studentDao.getStudentsByCourseName(anyString())).thenReturn(studentDaoReturnedList);
    }

    @Test
    void shouldReturnMessageWithStudents_ifStudentsWereFound() {
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

        assertEquals(expected, new StudentQueriesHandler(studentDao).
                printStudentsRelatedToCourse(anyString()));
    }

    @Test
    void shouldReturnNotFoundMessage_ifStudentsWereNotFound() {

        String expected = "No students related to this course or no such course\n";

        assertEquals(expected, new StudentQueriesHandler(studentDao).
                printStudentsRelatedToCourse(anyString()));
    }
}