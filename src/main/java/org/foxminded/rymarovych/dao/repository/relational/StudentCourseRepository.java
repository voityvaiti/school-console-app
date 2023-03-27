package org.foxminded.rymarovych.dao.repository.relational;

import org.foxminded.rymarovych.models.relational.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCourseRepository extends JpaRepository<StudentCourse, Integer> {
    Optional<StudentCourse> findStudentCourseByStudentIdAndCourseId(int studentId, int courseId);

}
