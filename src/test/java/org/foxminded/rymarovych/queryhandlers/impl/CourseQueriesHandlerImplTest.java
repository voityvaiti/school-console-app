package org.foxminded.rymarovych.queryhandlers.impl;

import org.foxminded.rymarovych.dao.abstractions.CourseDao;
import org.foxminded.rymarovych.models.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@SpringBootTest(classes = {CourseQueriesHandlerImpl.class})
class CourseQueriesHandlerImplTest {

    @MockBean
    CourseDao courseDao;

    @Autowired
    CourseQueriesHandlerImpl courseQueriesHandler;

    @Test
    void shouldAddStudentToTheCourseIfCourseExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course()));

        courseQueriesHandler.studentAdditionToTheCourse(studentId, courseName);

        verify(courseDao).addStudentToTheCourse(anyInt(), anyString());
    }

    @Test
    void shouldNotAddStudentToTheCourseIfCourseNotExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseQueriesHandler.studentAdditionToTheCourse(studentId, courseName);

        verify(courseDao, never()).addStudentToTheCourse(anyInt(), anyString());
    }

    @Test
    void shouldRemoveStudentFromTheCourseIfCourseExist() {

        int studentId = 2;
        String courseName = "Biology";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course()));

        courseQueriesHandler.studentRemovingFromTheCourse(studentId, courseName);

        verify(courseDao).deleteStudentFromCourse(anyInt(), anyString());
    }

    @Test
    void shouldNotRemoveStudentFromTheCourseIfCourseNotExist() {

        int studentId = 2;
        String courseName = "Biology";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseQueriesHandler.studentRemovingFromTheCourse(studentId, courseName);

        verify(courseDao, never()).deleteStudentFromCourse(anyInt(), anyString());
    }


}