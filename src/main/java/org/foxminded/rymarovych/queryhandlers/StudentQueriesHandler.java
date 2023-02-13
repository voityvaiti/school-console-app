package org.foxminded.rymarovych.queryhandlers;

import org.foxminded.rymarovych.dao.CourseDao;
import org.foxminded.rymarovych.dao.StudentCourseDao;
import org.foxminded.rymarovych.dao.StudentDao;
import org.foxminded.rymarovych.models.Student;

import java.util.List;
import java.util.Scanner;

public class StudentQueriesHandler {

    private final StudentDao studentsDao = StudentDao.getInstance();

    Scanner scanner = new Scanner(System.in);

    protected void printStudentsRelatedToCourse() {

        System.out.println("Type course name:");
        String courseName = scanner.next();

        CourseDao courseDao = CourseDao.getInstance();
        int courseId = courseDao.getCourseIdByName(courseName);

        StudentCourseDao studentCourseDao = StudentCourseDao.getInstance();
        List<Integer> studentsId = studentCourseDao.getStudentsWithSpecificCourse(courseId);

        if(studentsId.isEmpty()) {
            System.out.println("No students related to this course or no such course");

        } else {

            for(Integer id: studentsId) {
                System.out.println(studentsDao.getSpecificStudent(id));
            }
        }
        System.out.println("------------");
    }

    protected void studentAddition() {
        System.out.println("Type Student's group id:");
        int groupId = scanner.nextInt();

        System.out.println("Type Student's first name:");
        String firstName = scanner.next();

        System.out.println("Type Student's last name:");
        String lastName = scanner.next();

        studentsDao.addStudent(
                new Student(groupId, firstName, lastName)
        );
    }

    protected void removeStudentById() {
        System.out.println("Type Student's id:");
        int id = scanner.nextInt();
        studentsDao.removeStudent(id);
    }
}
