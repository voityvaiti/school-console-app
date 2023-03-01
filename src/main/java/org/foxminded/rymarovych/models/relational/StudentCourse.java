package org.foxminded.rymarovych.models.relational;

import jakarta.persistence.*;

@Entity
@Table(name = "students_courses")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "course_id")
    private int courseId;

    public StudentCourse() {
    }

    public StudentCourse(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public StudentCourse(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
