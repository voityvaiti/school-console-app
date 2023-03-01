package org.foxminded.rymarovych.dao.repository;

import org.foxminded.rymarovych.models.Course;
import org.foxminded.rymarovych.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findCourseByName(String name);

    @Query("SELECT c FROM Course c JOIN c.students s WHERE s.id = :studentId")
    List<Course> getStudentCourses(int studentId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "INSERT INTO students_courses (student_id, course_id) VALUES (:student, :course)")
    void addStudentToTheCourse(@Param("student") int studentId, @Param("course") int courseId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true,
            value = "DELETE FROM students_courses WHERE student_id = :student AND course_id = :course")
    void deleteStudentFromCourse(@Param("student") int studentId, @Param("course") int courseId);

}
