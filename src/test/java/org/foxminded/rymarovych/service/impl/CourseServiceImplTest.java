package org.foxminded.rymarovych.service.impl;

import org.foxminded.rymarovych.dao.repository.CourseRepository;
import org.foxminded.rymarovych.dao.repository.relational.StudentCourseRepository;
import org.foxminded.rymarovych.models.Course;
import org.foxminded.rymarovych.models.relational.StudentCourse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@SpringBootTest(classes = {CourseServiceImpl.class})
class CourseServiceImplTest {

    @MockBean
    CourseRepository courseDao;
    @MockBean
    StudentCourseRepository studentCourseDao;

    @Autowired
    CourseServiceImpl courseService;

    @Test
    void shouldAddStudentToTheCourseIfCourseExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course()));

        courseService.studentAdditionToTheCourse(studentId, courseName);

        verify(studentCourseDao).save(any(StudentCourse.class));
    }

    @Test
    void shouldNotAddStudentToTheCourseIfCourseNotExists() {

        int studentId = 5;
        String courseName = "P.E.";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseService.studentAdditionToTheCourse(studentId, courseName);

        verify(studentCourseDao, never()).save(any(StudentCourse.class));
    }

    @Test
    void shouldRemoveStudentFromTheCourseIfCourseExist() {

        int studentId = 2;
        int courseId = 1;
        int studentCourseId = 5;
        String courseName = "Biology";

        StudentCourse expectedStudentCourseToRemove = new StudentCourse(studentCourseId, studentId, courseId);

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.of(new Course(courseId, null, null)));
        when(studentCourseDao.findStudentCourseByStudentIdAndCourseId(studentId, courseId))
                .thenReturn(Optional.of(expectedStudentCourseToRemove));

        courseService.studentRemovingFromTheCourse(studentId, courseName);

        verify(studentCourseDao).delete(expectedStudentCourseToRemove);
    }

    @Test
    void shouldNotRemoveStudentFromTheCourseIfCourseNotExist() {

        int studentId = 2;
        String courseName = "Biology";

        when(courseDao.findCourseByName(anyString())).thenReturn(Optional.empty());

        courseService.studentRemovingFromTheCourse(studentId, courseName);

        verify(studentCourseDao, never()).findStudentCourseByStudentIdAndCourseId(anyInt(), anyInt());
    }


}