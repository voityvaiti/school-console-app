package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.CourseRepository;
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

@SpringBootTest(classes = {CourseServiceImpl.class})
class CourseServiceImplTest {

    @MockBean
    CourseRepository courseDao;

    @Autowired
    CourseServiceImpl courseService;

    @Test
    void shouldAddStudentToTheCourseIfCourseExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course()));

        courseService.studentAdditionToTheCourse(studentId, courseName);

        verify(courseDao).addStudentToTheCourse(anyInt(), anyInt());
    }

    @Test
    void shouldNotAddStudentToTheCourseIfCourseNotExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseService.studentAdditionToTheCourse(studentId, courseName);

        verify(courseDao, never()).addStudentToTheCourse(anyInt(), anyInt());
    }

    @Test
    void shouldRemoveStudentFromTheCourseIfCourseExist() {

        int studentId = 2;
        String courseName = "Biology";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course()));

        courseService.studentRemovingFromTheCourse(studentId, courseName);

        verify(courseDao).deleteStudentFromCourse(anyInt(), anyInt());
    }

    @Test
    void shouldNotRemoveStudentFromTheCourseIfCourseNotExist() {

        int studentId = 2;
        String courseName = "Biology";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseService.studentRemovingFromTheCourse(studentId, courseName);

        verify(courseDao, never()).deleteStudentFromCourse(anyInt(), anyInt());
    }


}