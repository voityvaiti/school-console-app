package org.foxminded.rymarovych.dao.repository;

import org.foxminded.rymarovych.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s JOIN s.courses c WHERE c.name= :courseName")
    List<Student> getStudentsByCourseName(String courseName);

    @Query("SELECT MAX(s.id) from Student s")
    int getMaxId();
}
